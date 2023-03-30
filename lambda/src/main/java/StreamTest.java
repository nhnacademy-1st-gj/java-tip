import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
   public static void main(String[] args){
      List<String> places = new ArrayList<>(){
         {
            add("Gyeonggi-do");
            add("Gangwon-do");
            add("Chungcheongnam-do");
            add("Chungcheongbuk-do");
            add("Jeollanam-do");
            add("Jeollabuk-do");
            add("Gyeongsangnam-do");
            add("Gyeongsangbuk-do");
            add("Jeju-do");
         }
      };


      //places.stream().filter((p) -> p.endsWith("do")).forEach((p)-> System.out.println(p));
      //places.stream().filter((p)->p.endsWith("do")).map((p)->p.toUpperCase()).forEach((p)->System.out.println(p));
     places.stream().filter((p)->p.endsWith("do")).map((p)->p.toUpperCase()).sorted().forEach((p)-> System.out.println(p));

      List<String> colors = Arrays.asList("red","green","blue","white","pink");
      List<String> newColors = colors.stream().sorted().collect(Collectors.toList());
      System.out.println("-Colors-");
      //colors.stream().forEach((x)-> System.out.println(x));
      colors.stream().forEach(System.out::println);
      System.out.println("-New Colors");
      newColors.stream().forEach(System.out::println);

      for(int i=0; i<newColors.size();i++){
         String color = newColors.get(i);
         System.out.println(color);
      }
      for(String color : newColors){
         System.out.println(color);
      }

      Iterator iterator = newColors.iterator();
      while(iterator.hasNext()){
         String color = (String)iterator.next();
         System.out.println(color);
      }

      newColors.forEach(System.out::println);




   }
}
