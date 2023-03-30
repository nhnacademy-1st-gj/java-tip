public class MyPrac4 {
  public static void main(String[] args) {
    MyPrac1 calculator = new MyPrac1();

    MyPrac2 user1Thread = new MyPrac2();
    user1Thread.setCalculator(calculator);
    user1Thread.start();

    MyPrac3 user2Thread = new MyPrac3();
    user2Thread.setCalculator(calculator);
    user2Thread.start();

  }
}
