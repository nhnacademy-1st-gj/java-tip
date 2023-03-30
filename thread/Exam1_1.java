public class Exam1_1 {
    static class Task extends Thread{
        private int seconds;
        public Task(int seconds){
            this.seconds = seconds;
        }

        @Override
        public void run() {
            try{
                while(true){
                    Thread.sleep(this.seconds);
                    System.out.println(Thread.currentThread().getName());
                }
            }catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task1 = new Task(2000);
        Task task2 = new Task(3000);

        task1.start();
        task2.start();

        for(int i =0; i<100 ; i++){
            System.out.printf("%s - %s\n", task1.getState(), task2.getState());
            Thread.sleep(1000);
        }
    }

}
