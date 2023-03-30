import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2 {
   public static void main(String[] args){
      Stream.Builder<String> streamBuilder = Stream.builder();
      //raw type이라서 뒤에 전부 object가 된다. 
      //object라서 

      streamBuilder.add("blue");

      Stream<String> stream = streamBuilder.build();
      List<String> collect = stream.collect(Collectors.toList());
      stream.toArray(String[]::new)
      Collector<Object, ?, List<Object>> objectListCollector = Collectors.toList();

      //내부적으로 오브젝트를 만들고 내부 스트림에서 흘러나오는 데이터를 객체로 만들어준다?........
      List<Integer> numbers = Arrays.asList(10,2,4,5,5,45,456,6,78,54,3,3);
      List<Integer> result = numbers.stream()
              .filter(x -> x>50)
              .filter(x-> x<100)
              .map(x->{
                 return x*10;
              }).sorted().collect(Collectors.toList());

      List<Integer> collect2 = numbers.stream().collect(Collectors.toList());
      System.out.println(result);

      }
   }

