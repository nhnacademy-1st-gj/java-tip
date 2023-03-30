
public class Exam2_2 {
    static class Task implements Runnable {
        Thread thread; //스레드를 여기서 선언
        int interval;
        boolean stopFlag;

        public Task(int interval) {
            this.interval = interval;
            this.stopFlag = true;
        }
        public boolean isStop(){
            return this.stopFlag == true;
        }

        @Override
        public void run() {
            this.stopFlag = false;
            System.out.println("스레드 ["+Thread.currentThread().getName()+"] 을 시작합니다.");
            try{
                while(!this.isStop()){
                    Thread.sleep(this.interval);
                    System.out.println("스레드 ["+Thread.currentThread().getName()+ "] 동작 중");
                }
            }catch (InterruptedException e){
                System.out.println("스레드 ["+Thread.currentThread().getName()+"] 인터럽트 발생");
            }finally {
                this.stopFlag = false;
            }
            System.out.println("스레드 ["+Thread.currentThread().getName()+"] 종료합니다");
            this.thread = null; //스레드 관계를 끊어버림. run이 종료되면 runnable의 관계를 끊음. 종료되면 가비지컬렉터가 수거해감.
        }

        public void start() {
            this.thread = new Thread(this); // start하면 스레드 생성
            this.thread.start(); //start 해줌
        }

        public void stop() {
            this.stopFlag = true;
        }

        public void join() throws InterruptedException{
            this.thread.join();
        }
    }

    public static void main(String [] args) throws InterruptedException {
        Task task = new Task(1000);

        task.start();
        Thread.sleep(5000);
        task.stop();
        task.join();
    }
}