import java.util.function.Predicate;

public class IsPositive implements Predicate<Integer> {
   public boolean test(Integer t){
      return t >= 0;
   }
}
