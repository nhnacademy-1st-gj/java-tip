import java.util.NoSuchElementException;

public class LinkedQueue implements Queue{
   static class LinkedNode{
      Integer element;
      LinkedNode next;

      public LinkedNode(Integer element, LinkedNode next){
         this.element = element;
         this.next = next;
      }

      public Integer getElement(){
         return this.element;
      }

      public LinkedNode getNext(){
         return this.next;
      }

      public void setNext(LinkedNode next){
         this.next = next;
      }
   }

   LinkedNode head;
   LinkedNode tail;
   int count;

   public LinkedQueue(){
      this.head = null;
      this.tail = null;
      this.count = 0;
   }

   @Override
   public boolean isEmpty() {
      return this.count == 0;
   }

   @Override
   public void add(Integer element) {
      if(this.isEmpty()){ //node가 하나도 없는 경우, 새롭게 설정해준다
         this.head = new LinkedNode(element,null);
         this.tail= this.head;
      }else{ //node가 있는 경우, tail 뒤에 붙이고 새로운 Node를 tail로 함
         this.tail.setNext(new LinkedNode(element,null));
         this.tail = this.tail.getNext();
      }
      this.count++;
   }

   @Override
   public Integer element() {
      if(this.isEmpty()){
         throw new NoSuchElementException();
      }
      return this.head.getElement();
   }

   @Override
   public Integer remove() {
      if(this.isEmpty()){
         throw new NoSuchElementException();
      }
      Integer element = this.head.getElement();
      if(this.count==1){
         this.head = null;
         this.tail = null;
      }else{
         this.head = this.head.getNext();
      }
      this.count--;

      return element;
   }

   @Override
   public int size() {
      return this.count;
   }

   @Override
   public String toString() {
      StringBuilder line = new StringBuilder();
      LinkedNode node = this.head;
      while(node != null){
         line.append((line.length()!= 0? " " :"")+node.getElement());
         node= node.getNext();
      }
      return line.toString();
   }

   public void reverse(){
      LinkedStack stack = new LinkedStack();
      while(!this.isEmpty()){
         Integer element = this.remove();
         stack.push(element);
      }
      while(!stack.isEmpty()){
         Integer element = stack.pop();
         this.add(element);
      }
   }

   public Integer second(){
      if(this.size()<2){
         throw new NoSuchElementException();
      }
      Integer value = null;
      for(int i=0; i<this.size();i++){
         Integer element = this.remove();
         if(i==1){
            value = element;
         }
         this.add(value);
      }

      return value;
   }

   public Integer removeSecond(){
      Integer value = null;
      if(this.size() > 1){
         int size = this.size(); //두번째 것을 지울 경우, queue.size()가 달라짐
         for(int i =0; i<size; i++){
            Integer element = this.remove();
            if(i != 1){
               this.add(value);
            }
         }
      }
      return value;
   }




}
