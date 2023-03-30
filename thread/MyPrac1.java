public class MyPrac1{
    private int memory;
    public int getMemory(){
        return memory;
    }

    public synchronized void setMemory1(int memory){ //동기화 메소드
        this.memory = memory; //메모리 값 저장
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+":"+this.memory); //this.memory -> 메모리 값 읽기
        }
    }

    public void setMemory2(int memory){ //동기화 블록
        synchronized (this){ //클래스의 인스턴스를 줌. 이렇게 클래스의 인스턴스를 주면 해당 클래스에 있는 다른 동기화 메소드 또한 사용이 불가하다
            this.memory = memory;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+":"+this.memory); //this.memory -> 메모리 값 읽기
            }
        }
    }

}