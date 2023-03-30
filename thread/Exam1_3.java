
public class Exam1_3 {
    static class Task extends Thread {
        private int seconds;
        public Task(int seconds) {
            this.seconds = seconds;
        }
        public void stop2() {
            interrupt();
        }

        @Override
        public void run() {
            try{
                System.out.println(Thread.currentThread().getName()+" 을 시작합니다.");
                while(true){
                    Thread.sleep(this.seconds);
                    System.out.println(Thread.currentThread().getName()+ " 동작 중");
                }
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+" 인터럽트 발생");
            }
            System.out.println(Thread.currentThread().getName()+" 종료합니다");
        }
    }

    public static void main(String [] args) throws InterruptedException {
        Task task = new Task(1000);

        task.start();
        Thread.sleep(5000);;
        task.stop2();
        task.join();
    }
}