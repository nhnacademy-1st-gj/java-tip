
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Exam5_1 {
    static class Task extends Thread {
            long interval;
            static Lock lock = new ReentrantLock();//lock이 각자 하나씩 있어서. 하나라는건 특정 영역에 하나라는 의미.
            // static으로 선언하면 됨.

            public Task(int interval) {
                this.interval = interval;
            }

            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long previousPrintTime = 0;
                while(true){
                    try{
                        lock.lock();
                        long totalElapsedTime = System.currentTimeMillis() - startTime;
                        long elapsedTime = totalElapsedTime - previousPrintTime;
                        System.out.printf("[ %02d:%02d.%03d] [%02d.%03d] %s \n",
                                totalElapsedTime / (60 * 1000), (totalElapsedTime / 1000) % 60,
                                totalElapsedTime % 1000, (elapsedTime / 1000), elapsedTime % 1000,
                                Thread.currentThread().getName());
                        previousPrintTime = totalElapsedTime;
                        Thread.sleep(this.interval);
                        lock.unlock();
                        Thread.yield(); //yield는 내가 사용하고 있어도 잠시 나의 제어권을 내어주는 것. 잠시 그 스레드를 놓아주고 양보하는것.
                        //대기하고 있던 프로세스 중에 우선순위가 높은게 차지하게 된다.
                    }catch (InterruptedException e){

                    }
                }
            }

    }

    public static void main(String [] args) throws InterruptedException {
        Task task1 = new Task(3000);
        Task task2 = new Task(5000);
        task1.start();
        task2.start();
        task1.join();

    }
}
