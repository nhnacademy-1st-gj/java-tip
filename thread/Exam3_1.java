
public class Exam3_1 {
    static class Task implements Runnable {
        int time;
        int innerTime;
        Task childTask;
        Thread thread;


        boolean stopFlag;

        public Task(int time){
            this.time=time;
            this.stopFlag = true;
            this.thread = new Thread(this);
        }

        public Task(int time, int innerTime) {
            this.stopFlag = true;
            this.time = time;
            this.thread = new Thread(this);
            this.innerTime = innerTime;
        }

        public boolean isStop(){
            return this.stopFlag == true;
        }

        @Override
        public void run() {
            if(this.innerTime != 0){
                this.childTask = new Task(innerTime);
                this.childTask.start();
            }
            this.stopFlag = false;
            System.out.println("스레드 ["+Thread.currentThread().getName()+"] 을 시작합니다.");
            try{
                while(!this.isStop()){
                    Thread.sleep(this.time);
                    System.out.println("스레드 ["+Thread.currentThread().getName()+ "] 동작 중");
                }
            }catch (InterruptedException e){
                System.out.println("스레드 ["+Thread.currentThread().getName()+"] 인터럽트 발생");
            }finally {
                this.stopFlag = false;
            }
            System.out.println("스레드 ["+Thread.currentThread().getName()+"] 종료합니다");
            this.childTask.stop();
            try {
                this.childTask.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void start() {
            this.thread.start();
        }

        public void stop() {
            this.stopFlag = true;
        }

        public void join() throws InterruptedException{
            this.thread.join();
        }
    }

    public static void main(String [] args) throws InterruptedException {
        Task task = new Task(2000, 1000);
        task.start();
        Thread.sleep(10000);
        task.stop();
        task.join();
    }
}
