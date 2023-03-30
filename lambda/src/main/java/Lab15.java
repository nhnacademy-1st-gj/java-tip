import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Lab15 {
   public static void main(String[] args){

      /*
      //15-1
      System.out.println("15-1");
      Function<Integer,Integer> square = new FunctionTest();
      Function<Integer,Integer> square2 = (x)->x*x;

      System.out.println("Class: 2*2= "+ square.apply(2));
      System.out.println("Lambda: 2*2= "+ square2.apply(2));


      //15-2
      System.out.println("\n15-2");
      Predicate<Integer> isPositive = new IsPositive();
      Predicate<Integer> isPositive2 = (x)-> x >= 0;

      System.out.println("Class: 1 = "+isPositive.test(1)+
              ", -1= "+isPositive.test(-1));
      System.out.println("Lambda : 1 = " + isPositive2.test(1) +
              ", -1 = " + isPositive2.test(-1));

*/

      //15-3
      //파라미터 x, 리턴 o
      System.out.println("\n15-3");
      int[] fibs = {0,1};

//      Supplier<Integer> fibonacci2 = ()->{ //get의 구현이다!!
//         int result = fibs[1];
//         int fib3 = fibs[0]+fibs[1];
//         fibs[0]=fibs[1];
//         fibs[1]=fib3;
//         return result;
//      };
//
//
//      for(int i =0; i<10; i++){
//         System.out.print(fibonacci2.get()+" ");
//      }


      //스트림과 연동하기
      Stream<Integer> fibonacci2 = Stream.generate(()->{
         int result = fibs[1];
         int fib3 = fibs[0]+fibs[1];
         fibs[0]=fibs[1];
         fibs[1]=fib3;
         return result;
      });

      Iterator<?> it = fibonacci2.iterator();
      while (it.hasNext()){
         System.out.print(it.next()+"");
      }

      System.out.println(" ");


      //15-4
      System.out.println("\n15-4");
      Random random = new Random();
//      Consumer<Integer> positivePrint = (x)->{if (x >= 0)
//         System.out.println(x+" ");};
//      for(int i=0 ; i<10 ; i++){
//         positivePrint.accept(random.nextInt());
//      }

      //random::n
      Stream<Integer> positiveRandomStream = Stream.generate(random::nextInt).filter((x)->x>=0).limit(10);
      positiveRandomStream.forEach((x)-> System.out.println(x+" "));
      //generate은 Supplier<T>를 인수로 받아 Stream<T>을 생성하는 생성자 메소드
      //filter를 이용해 양수만을 추출
      //limit을 이용해 출력할 개수를 정함
      //생성된 객체에 forEach를 이용해 추출된 정수를 출력

      //15-5
      System.out.println("\n15-5");
      AreaCalculator circularAreaCalculator = (r) -> 2*Math.PI*r;
      System.out.println("Circular Area(Radius 2)= "+ circularAreaCalculator.getArea(2.0));

      AreaCalculator squareAreaCalculator = (r) -> r*r;
      System.out.println("Square Area(Length 2)= "+ squareAreaCalculator.getArea(2.0));






   }
}
