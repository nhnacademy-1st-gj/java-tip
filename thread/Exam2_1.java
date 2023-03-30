public class Exam2_1 {
    static class Task implements Runnable {
        private int interval;
        private boolean stopFlag;

        public Task(int interval) {
            this.interval = interval;
            this.stopFlag = false;
        }
        public void stop() {
            this.stopFlag = true;
        }


        @Override
        public void run() {
            try{
                System.out.println(Thread.currentThread().getName()+" 을 시작합니다.");
                while(true){
                    Thread.sleep(this.interval);
                    System.out.println(Thread.currentThread().getName()+ " 동작 중");
                    Thread.dumpStack();
                }
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+" 인터럽트 발생");
            }finally {
                this.stopFlag = false;
            }
            System.out.println(Thread.currentThread().getName()+" 종료합니다");
        }

    }
    public static void main(String [] args) throws InterruptedException {
        Task task = new Task(1000);
        Thread taskThread = new Thread(task);

        //Runnable 인터페이스의 문제점: 시작과 끝이 다름. 이렇게 되면 관리하기 어려워진다.
        taskThread.start(); //taskThread 시작

        Thread.sleep(5000);

        task.stop(); //task에서 멈춤

        taskThread.join();
    }
}