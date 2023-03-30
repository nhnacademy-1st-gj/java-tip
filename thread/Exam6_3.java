import java.rmi.NoSuchObjectException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exam6_3{
    static class Producer implements Runnable{
        //생산자가 있어서 일정 주기마다 일정 수량을 공급합니다
        //생산자는 생산 주기 및 생산 수량을 설정할 수 있습니다
        Market market;
        String fruit;
        long interval;

        public Producer(Market market, String fruit, int interval) {
            this.market = market;
            this.fruit = fruit;
            this.interval = interval;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            System.out.printf("%s를 시작합니다.\n",Thread.currentThread().getName());
            try{
                while(true){
                    System.out.printf("%s에서 %s를 납품합니다.\n",Thread.currentThread().getName(),this.fruit);
                    this.market.put(this.fruit);
                    Thread.sleep(this.interval-(System.currentTimeMillis()-startTime % this.interval));
                }
            }catch (InterruptedException e){
                System.out.printf("%s에 인터럽트가 발생했습니다.",Thread.currentThread().getName());
            }
            System.out.printf("%s를 종료합니다.",Thread.currentThread().getName());
        }
    }
    static class Consumer implements Runnable{
        //마켓에는 5명의 고객이 있습니다
        //고객은 마켓에서 일정시간 마다 3가지 물건중 한가지를 구매합니다
        //구매할 물건이 없을 경우, 일정 시간 대기를 하지만 대기 시간을 초과할 경우 해당 물건 구매는 포기합니다
        Market market;
        String[] fruits;
        long minInterval;
        long maxInterval;

        public Consumer(Market market, String[] fruits, long minInterval, long maxInterval){
            this.market = market;
            this.fruits = Arrays.copyOf(fruits, fruits.length);
            this.minInterval = minInterval;
            this.maxInterval = maxInterval;
        }

        @Override
        public void run() {
            Random random = new Random();
            long startTime = System.currentTimeMillis();
            System.out.printf("%s를 시작합니다.\n",Thread.currentThread().getName());
            try{
                while(true){
                    String fruit = this.fruits[random.nextInt(this.fruits.length)];
                    try{
                        System.out.printf("%s가 %s를 구매하길 원합니다.\n",Thread.currentThread().getName(),fruit);
                        this.market.get(fruit);
                        System.out.printf("%s가 %s를 구매하였습니다..\n",Thread.currentThread().getName(),fruit);
                    }catch (NoSuchObjectException e){
                        System.out.printf("%s가 품절되었습니다. 물건을 기다립니다.\n",fruit);
                        this.market.waitingForStock();
                        System.out.printf("%s가 입고되었습니다.\n",fruit);
                    }
                    Thread.sleep(this.minInterval+(long)(Math.random()*(this.maxInterval-this.minInterval)));
                }
            }catch (InterruptedException e){
                System.out.printf("%s에 인터럽트가 발생했습니다.\n", Thread.currentThread().getName());
            }
            System.out.printf("%s를 종료합니다.\n",Thread.currentThread().getName());
        }
    }
    static class Market{ //마켓에는 3종류의 물건을 구매할 수 있다.
        int size;

        Map<String, Integer> table;
        Lock lock;

        public Market(int size) {
            this.size = size;
            this.table = new HashMap<>();
            this.lock = new ReentrantLock();
        }

        public synchronized void put(String fruit){ //이걸로 요리 만들자
            this.lock.lock();
            int stock = 0; //요리의 개수
            if(this.table.containsKey(fruit)){
                stock = this.table.get(fruit)+1;
                this.table.replace(fruit, stock);
            }else{
                stock = 1;
                this.table.put(fruit,1);
            }
            System.out.printf("%s의 재고는 %d개 입니다.\n",fruit, stock);
            this.lock.unlock();
            this.notify();
        }

        public synchronized void get(String fruit) throws NoSuchObjectException{
            try{
                this.lock.lock();
                if(this.table.containsKey(fruit) && (this.table.get(fruit)!=0)){
                    int stock = this.table.get(fruit) -1;
                    this.table.replace(fruit, stock);
                    System.out.printf("%s의 재고는 %d개 입니다.\n",fruit, stock);
                }else{
                    throw new NoSuchObjectException(fruit+"은 품절입니다.");
                }
            }catch (Exception e){
                throw e;
            }finally{
                this.lock.unlock();
            }
        }

        public synchronized void waitingForStock() throws InterruptedException{
            this.wait();
        }

        public String toSting(){
            StringBuilder line = new StringBuilder();
            for(String fruit : this.table.keySet()){
                line.append(fruit + " : "+ this.table.get(fruit)+" ");
            }
            return line.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Market market = new Market(10);
        Thread producer = new Thread(new Producer(market, "apple",3000));
        Thread consumer = new Thread(new Consumer(market, new String[]{"apple"},1000,4000));

        producer.start();
        consumer.start();

        while(true){
            Thread.sleep(1000);
        }
    }
}
