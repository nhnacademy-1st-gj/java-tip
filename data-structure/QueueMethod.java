import java.util.NoSuchElementException;


public class QueueMethod {
   public static Queue reversed(Queue queue){ //3. 인수로 주어진 queue와 같은 요소를 가지는 새로운 queue 생성
      LinkedStack stack = new LinkedStack();
      for(int i =0; i<queue.size();i++){
         Integer element = queue.remove();
         stack.push(element);
         queue.add(element);
      }
      LinkedQueue newQueue = new LinkedQueue();
      while(!stack.isEmpty()){
         Integer element = stack.pop();
         newQueue.add(element);
      }
      return newQueue;
   }

   public static Queue reverse(Queue queue){
      LinkedStack stack = new LinkedStack();
      while(!queue.isEmpty()){
         Integer element = queue.remove();
         stack.push(element);
      }
      while(!stack.isEmpty()){
         Integer element = stack.pop();
         queue.add(element);
      }
      return queue;
   }

   public static Integer secondElement(Queue queue){
      if(queue.size() < 2){
         throw new NoSuchElementException();
      }
      Integer  value = null;
      for(int i =0; i<queue.size();i++){
         Integer element = queue.remove();
         if( i == 1){
            value = element;
         }
         queue.add(value);
      }
      return value;
   }

   public static Integer lastElement(Queue queue){
      Integer value = null;
      for(int i =0; i< queue.size();i++){
         Integer element = queue.remove();
         if(i == queue.size()){ //마지막 요소를 저장해 둔다
            value = element;
         }
         queue.add(value);
      }
      return value;
   }


   public static void removeLastElement(Queue queue){
      for(int i = 0; i< queue.size();i++){
         Integer element = queue.remove();
         if(i != queue.size()){
            queue.add(element);
         }
      }
   }


   public static Queue merge(Queue q1,Queue q2){ //인수로 주어진 두개의 큐를 이용해 하나의 큐를 생성합니다. 큐의 요소는 유지됩니다.
      Queue newQueue = new LinkedQueue();
      for(int i =0 ; i<q1.size();i++){
         Integer value = q1.remove(); //q1의 값을 remove해서 value에 담아줌
         newQueue.add(value); //newQueue에 담아줌
         q1.add(value); //원래 큐 유지를 위해 넣어줌
      }

      for(int i =0; i<q2.size();i++){
         Integer value = q2.remove();
         newQueue.add(value);
         q2.add(value);
      }
      return newQueue;
   }











}
