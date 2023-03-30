import java.time.LocalDateTime;
public class Exam4_1 {
    static class Task extends Thread {
        long interval;

        public Task(int interval) {
            this.interval = interval;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long previousPrintTime = 0;
            long targetTime = startTime + this.interval;
            while(true){
                while (System.currentTimeMillis()<targetTime){

                }

                long totalElapsedTime = System.currentTimeMillis() - startTime;
                long elapsedTime = totalElapsedTime - previousPrintTime;
                System.out.printf("[ %02d:%02d.%03d] [%02d.%03d] %s \n",
                        totalElapsedTime / (60 * 1000), (totalElapsedTime / 1000) % 60,
                        totalElapsedTime % 1000, (elapsedTime / 1000), elapsedTime % 1000,
                        Thread.currentThread().getName());

                previousPrintTime = totalElapsedTime;
                targetTime = targetTime+this.interval;
            }
        }
    }
    public static void main(String [] args) throws InterruptedException {
        Task task1 = new Task(3000);
        Task task2 = new Task(5000);
        task1.start();
        task2.start();
        long    startTime = System.currentTimeMillis();
        long    previousPrintTime = 0;
        for(int i = 0 ; i < 100 ; i++) {
            Thread.sleep(1000);
            if (i % 2 == 0) {
                task1.interrupt();
                task2.interrupt();
            }
            long totalElapsedTime = System.currentTimeMillis() - startTime;
            long elapsedTime = totalElapsedTime - previousPrintTime;
            System.out.printf("[ %02d:%02d.%03d ][ %02d.%03d ] %s - %s\n",
                    totalElapsedTime / (60 * 1000), (totalElapsedTime / 1000) % 60,
                    totalElapsedTime % 1000,
                    (elapsedTime / 1000), elapsedTime % 1000,
                    task1.getState(), task2.getState());

            previousPrintTime = totalElapsedTime;
        } }
}