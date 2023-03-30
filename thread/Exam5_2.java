
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Exam5_2{
    static class Task extends Thread {
        long interval;
        boolean stopFlag;

        public Task(int interval) {
            this.interval = interval;
        }

        public void stop2(){
            this.stopFlag = true;
        }

        public boolean isStop(){
            return this.stopFlag == true;
        }

        static synchronized void print(String message, long interval) throws InterruptedException{
            Thread.sleep(interval);
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long previousPrintTime = 0;
            this.stopFlag = false;
            while(!this.isStop()){
                try{

                    long totalElapsedTime = System.currentTimeMillis() - startTime;
                    long elapsedTime = totalElapsedTime - previousPrintTime;
                    System.out.printf("[ %02d:%02d.%03d] [%02d.%03d] %s \n",
                            totalElapsedTime / (60 * 1000), (totalElapsedTime / 1000) % 60,
                            totalElapsedTime % 1000, (elapsedTime / 1000), elapsedTime % 1000,
                            Thread.currentThread().getName());
                    previousPrintTime = totalElapsedTime;
                    Thread.sleep(this.interval);
                    //lock.unlock();
                    Thread.yield();
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
