import java.rmi.NoSuchObjectException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exam6_1 {
    static class Market { //마켓
        int size;
        Map<String, Integer> table;
        Lock lock;

        public Market(int size) {
            this.size = size;
            this.table = new HashMap<>();
            this.lock = new ReentrantLock();
        }

        public synchronized void put(String fruit) { //synchronized를 사용하려면 static이어야 하는데 스테틱으로 선언할 경우 this를 사용할 수 없다.
            //여기서 synchronized를 사용한 이유는 밑에서 notify를 사용하기 위해. 동기화의 목적은 없음
            this.lock.lock();
            int stock = 0;
            if (this.table.containsKey(fruit)) {
                stock = this.table.get(fruit) + 1;
                this.table.replace(fruit, stock);
            } else {
                stock = 1;
                this.table.put(fruit, 1);
            }
            System.out.printf("%s의 재고는 %d개 입니다.\n", fruit, stock);
            this.lock.unlock();
            this.notify();
        }

        public synchronized void get(String fruit) throws NoSuchObjectException {
            try {
                this.lock.lock();
                if (this.table.containsKey(fruit) && (this.table.get(fruit) != 0)) {
                    int stock = this.table.get(fruit) - 1;
                    this.table.replace(fruit, stock);
                    System.out.printf("%s의 재고는 %d개 입니다.\n", fruit, stock);
                } else {
                    throw new NoSuchObjectException(fruit + "은 품절입니다.");
                }
            } catch (Exception e) {
                throw e;
            } finally {
                this.lock.unlock();
            }
        }

        public synchronized void waitingForStock() throws InterruptedException {
            this.wait();
        }

        public String toString() {
            StringBuilder line = new StringBuilder();

            for (String fruit : this.table.keySet()) {
                line.append(fruit + " : " + this.table.get(fruit) + " ");
            }
            return line.toString();
        }

    }


    static class Producer implements Runnable {  //마켓에 납품하는 생산자
        //생산자의 작업은 데이터를 생성하고 버퍼에 넣고 일정 시간 대기 후 작업을 다시 시작합니다.
        //생산자가 작업 중 데이터를 넣을 버퍼가 없을 경우 대기 상태로 돌아갑니다
        //생산자는 대기 상태에서 버퍼가 비워졌다는 알람을 받으면 버퍼에 넣는 작업을 다시 시도 합니다.

        Market market;
        String fruit;
        long interval;

        public Producer(Market market, String fruit, long interval) {
            this.market = market;
            this.fruit = fruit;
            this.interval = interval;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            System.out.printf("%s를 시작합니다.\n", Thread.currentThread().getName());
            try {
                while (true) {
                    System.out.printf("%s에서 %s를 납품합니다.\n", Thread.currentThread().getName(), this.fruit);
                    this.market.put(this.fruit);
                    Thread.sleep(this.interval - (System.currentTimeMillis() - startTime) % this.interval);
                }
            } catch (InterruptedException e) {
                System.out.printf("%s에 인터럽트가 발생했습니다.\n", Thread.currentThread().getName());
            }
            System.out.printf("%s를 종료합니다.\n", Thread.currentThread().getName());
        }
    }

    static class Consumer implements Runnable { //소비자
        //소비자는 데이터를 한번에 하나씩 소비합니다
        //소비자가 소비할 데이터를 얻지 못할 경우 대기 상태로 돌아갑니다
        //소비자는 대기 상태에서 데이터가 준비되었다는 알람을 받으면 데이터 가져오기를 다시 시도합니다
        Market market;
        String[] fruits;
        long minInterval;
        long maxInterval;

        public Consumer(Market market, String[] fruits, long minInterval, long maxInterval) {
            this.market = market;
            this.minInterval = minInterval;
            this.maxInterval = maxInterval;
            this.fruits = Arrays.copyOf(fruits, fruits.length);
        }

        @Override
        public void run() {
            Random random = new Random();
            long startTime = System.currentTimeMillis();
            System.out.printf("%s를 시작합니다.\n", Thread.currentThread().getName());
            try {
                while (true) {
                    String fruit = this.fruits[random.nextInt(this.fruits.length)];
                    try {
                        System.out.printf("%s가 %s를 구매하길 원합니다.\n", Thread.currentThread().getName(), fruit);
                        this.market.get(fruit);
                        System.out.printf("%s가 %s를 구매하였습니다..\n", Thread.currentThread().getName(), fruit);
                    } catch (NoSuchObjectException e) {
                        System.out.printf("%s가 품절되었습니다. 물건을 기다립니다.\n", fruit);
                        this.market.waitingForStock();
                        System.out.printf("%s가 입고되었습니다.\n", fruit);
                    }
                    Thread.sleep(this.minInterval + (long) (Math.random() * (this.maxInterval - this.minInterval)));
                }
            } catch (InterruptedException e) {
                System.out.printf("%s에 인터럽트가 발생했습니다.\n", Thread.currentThread().getName());
            }
            System.out.printf("%s를 종료합니다.\n", Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Market maret = new Market(10);
        Thread producer = new Thread(new Producer(maret, "apple", 3000));
        Thread consumer = new Thread(new Consumer(maret, new String[]{"apple"}, 1000, 4000));

        producer.start();
        consumer.start();

        while (true) {
            Thread.sleep(1000);
        }
    }
}