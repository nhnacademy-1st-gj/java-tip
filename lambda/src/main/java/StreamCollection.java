import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;

public class StreamCollection {
   public static void main(String[] args) {
      /*
      System.out.println("---List to Stream---");
      List<String> list = List.of("white","red","blue","pink","green");
      Stream<String> stream1 = list.stream();
//      List<String> collect = stream1.collect(Collectors.toList());
//      collect.forEach(System.out::println);

      //stream1.forEach(System.out::println);

      System.out.println("---Set to Stream---");
      Set<String> set = Set.of("white","red","blue","pink","green");
      Stream<String> stream2 = set.stream();
      stream2.forEach(System.out::println);


      Stream stream1 = Stream.of("white","red","blue","pink","green");
      IntStream intStream = IntStream.of(1,2,3,4,5);
      LongStream longStream = LongStream.of(11,21,31,41,51);
      DoubleStream doubleStream = DoubleStream.of(1.1,2.3,4.3,2.2,4.3);

      System.out.println("stream.of: "+ Arrays.toString(stream1.toArray()));
      System.out.println("intStream.of: "+ Arrays.toString(intStream.toArray()));
      System.out.println("longStream.of: "+ Arrays.toString(longStream.toArray()));
      System.out.println("doubleStream.of: "+Arrays.toString(doubleStream.toArray()));

      Stream stream2 = Arrays.stream(new String[] {"white","red","blue","pink"});
      System.out.println("Arrays.stream: "+ Arrays.toString((stream2.toArray())));
       */

      //empty는 스트림은 있지만 안에 아무것도 없는 스트림을 반환한다. 스트림이 필요한데 null이 반환되면 안되니까.. 비어있는 스트림을 만들어서
      //리턴해줌. null, " " 과는 다르다. 비어있는 스트림.
      //static으로 만든 이유: 한번 만들면 업데이트 될 일이 없고 여러군데서 쓸 수 있어야 하기 때문에.
      Stream stream1 = Stream.empty();
      //stream 아무것도 없다
      System.out.println("empty - count: "+stream1.count());

      Stream stream2 = Stream.of();
      System.out.println("of-count: "+ stream2.count());

      //널이 들어가 있는거랑 비어있는거랑 차이점
      //널은 어떠한 값으로도 초기화되지 않음. 참조형이지만 힙 메모리 상에 데이터를 만들어 내지 않는다.
      //그럼 of 아닌 empty로 만들면 "" 가 들어간다는건가???? ("" 은 메모리에 올라감)
      //비어있다?..?

      //static으로 만들어진 비어진 stream을 반환한다 ???


   }
}
