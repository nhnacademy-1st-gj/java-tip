public class Exam1_2 {
    static class Task extends Thread{
        private int seconds;
        private boolean stop;

        public Task(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            try{
                System.out.println(Thread.currentThread().getName()+" 을 시작합니다.");
                while(!stop){
                    Thread.sleep(this.seconds); //지금은 sleep 시간이 짧지만 시간을 길게 설정해줄 경우 (ex)5분을 준다면? 5분 후에 끝남.)
                    //끝날 때까지 뒤에 있는 프로세스는 대기해야 함. 스레드가 종료된 다음까지 기다려야 해서.

                    System.out.println(Thread.currentThread().getName()+ " 동작 중");
                }
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+" 인터럽트 발생");
            }
            System.out.println(Thread.currentThread().getName()+" 종료합니다");
        }

        public void stop2() {
            this.stop = !stop;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task(1000);
        task.start();
        Thread.sleep(5000);
        
        task.stop2();
        task.join();
    }
}
