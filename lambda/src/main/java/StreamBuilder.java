import java.util.Arrays;
import java.util.stream.Stream;

public class StreamBuilder {
   public static void main(String[] args) {
      Stream stream = Stream.builder()
              .add("red")
              .add("blue")
              .add("white")
              .add("pink")
              .build();

      //System.out.println(Arrays.toString(stream.toArray()));

      Stream stream1 = Stream.generate(Math::random).limit(5);
      System.out.println("stream1: "+Arrays.toString(stream1.toArray()));

      Stream stream2 = Stream.generate(()-> {
         return Math.random();
              }).limit(5);
      System.out.println("stream2: "+Arrays.toString(stream2.toArray()));

   }
}
