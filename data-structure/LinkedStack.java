import java.util.EmptyStackException;

public class LinkedStack implements Stack{
   static class LinkedNode{
      Integer element; //노드 안에 있는 요소
      LinkedNode next; //링크드 노드 타입의 넥스트.

      public LinkedNode(Integer element, LinkedNode next){ //링크드 노드 초기화
         this.element = element;
         this.next = next;
      }

      public Integer getElement(){ //요소를 반환한다
         return this.element;
      }

      public LinkedNode getNext(){ //다음 노드를 반환한다
         return this.next;
      }
   }

   LinkedNode head; //head는 요소가 들어간다
   int count;

   public LinkedStack(){ //stack 초기화
      this.head = null;
      this.count = 0;
   }

   @Override
   public boolean isEmpty() { //stack이 비워져 있는지 확인
      return this.count == 0; //count가 0이면 true를 리턴
   }

   @Override
   public void push(Integer element) {
     this.head = new LinkedNode(element,this.head);
      //제일 처음 노드의 헤드는 null (요소가 없음)
      //그 다음에 새로 생긴 1번 노드는 요소 값과 head값(null을 가진다)
      //그리고 head는 1번 노드가 됨
      //두 번째로 생성한 2번 노드는 요소 값과 기존에 있던 head값(1번 노드)를 가짐
      //그리고 head는 2번 노드가 됨
      //이런 식으로 연결해주는 거

     this.count++;
   }

   @Override
   public Integer pop() {
      if(this.size()==0){
         throw new EmptyStackException();
      }
      Integer value;
      value = this.head.getElement(); //요소를 가져옴
      this.head = this.head.getNext(); //헤드를 그 이전 노드로 바꿔줘서 링크를 끊음
      this.count--;
      return value;
   }

   @Override
   public Integer peek() {
      if(this.size()==0){
         throw new EmptyStackException();
      }
      return this.head.getElement();
   }

   @Override
   public int size() {
      return this.count;
   }

   @Override
   public String toString() {
      StringBuilder line = new StringBuilder();//스트링빌더를 쓰면 속도가 빠르고 메모리도 절약된다
      //스트링으로 출력하면 계속 객체를 생성해야 하므로 메모리 낭비가 심하다

      LinkedNode node = this.head;
      while(node != null){
         line.append(((line.length()!=0 ) ? " ": " ")+node.getElement());
         node = node.getNext();
      }
      return line.reverse().toString();
   }

   public Stack reverse(){
      Stack newStack = new LinkedStack();
      while (this.count == 0) {
         newStack.push(head.getElement());
         this.head = this.head.getNext();
         this.count --;
      }
      return  newStack;
   }

   public Integer penultimate(){
      this.head.getElement();
      this.head = this.head.getNext();
      Integer value = this.head.getElement();
      return value;
   }

   public Integer popPenultimate(){
      this.head.getElement();
      this.head = this.head.getNext();
      Integer value = this.head.getElement();
      this.head = this.head.getNext();
      return value;
   }

   public Integer bottom(){
      int size = count-1;
      for(int i=0; i<size;i++){
         this.head.getElement();
         this.head = this.head.getNext();
      }
      Integer value = this.head.getElement();
      return value;
   }
   public Integer popBottom(){
      int size = count-1;
      for(int i=0; i<size;i++){
         this.head.getElement();
         this.head = this.head.getNext();
      }
      Integer value = this.head.getElement();
      this.head = this.head.getNext();
      return value;
   }

}
