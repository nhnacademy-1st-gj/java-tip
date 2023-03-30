public class MyPrac3 extends Thread{
  private MyPrac1 calculator;

  public MyPrac3(){
    setName("User2Thread"); //스레드 이름 변경
  }

  public void setCalculator(MyPrac1 calculator){ //외부에서 공유 객체인 MyPrac1을 받아 필드에 저장
    this.calculator = calculator;
  }

  @Override
  public void run() {
    calculator.setMemory2(50); //동기화 메소드 호출
  }
}
