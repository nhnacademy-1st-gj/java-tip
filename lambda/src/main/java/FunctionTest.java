import java.util.function.Function;

public class FunctionTest implements Function<Integer, Integer>{
      public Integer apply(Integer t){
         return t*t;
      }
   }

