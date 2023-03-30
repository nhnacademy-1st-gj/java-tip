import java.util.EmptyStackException;

public class ArrayStack implements Stack{
   final int defaultSize = 100;
   Integer[] elements; //현재 널...인티져 배열 타입(참조변수)이다
   int top;

   public ArrayStack(){ //생성자로서 Stack을 초기화
         this.elements = new Integer[this.defaultSize]; //100인 인티져 배열을 참조!!!!하는거고. 메모리 주소를 저장하고 ㅇ ㅣㅆ었ㅇ다가
      this.top= -1;
   }

   @Override
   public boolean isEmpty() { //스택이 비워져있는지 확인
      return this.top < 0; //탑이 0보다 작으면 true 반환
   }

   @Override
   public void push(Integer element) { //스택에 데이터 넣기

      //만약 배열이 꽉찬 경우엔 새롭게 길이를 늘려주기
      if (this.elements.length <= this.top + 1) { //elements 배열이 길이가 탑에 1을 더한 값보다 작거나 같다면
         Integer[] newElement = new Integer[this.elements.length + this.defaultSize]; //100+100해서 200으로 늘려줌. 그 다음엔 200+100... 100씩 늘려주는거네
         System.arraycopy(this.elements,0,newElement,0,this.elements.length);
         /*
         src – the source array.
         srcPos – starting position in the source array.
         dest – the destination array.
         destPos – starting position in the destination data.
         length – the number of array elements to be copied.
          */
         this.elements = newElement; //새로운 객체 배열의 메모리 주소를 참조한다
         //객체의 메모리 주소를 저장해주는 부분..
      }
      this.top++; //탑의 인덱스를 더하고
      this.elements[this.top] = element; // 탑의 값을 인덱스로 넣고 요소를 넣어준다.
   }

   @Override
   public Integer pop() { //Stack에서 최상위 데이터를 돌려주고, 배열에서 데이터를 제거함
      if(this.size() == 0){ //stack에 저장되어 있는 데이터의 개수가 0인 경우는 돌려 줄게 없다
         throw new EmptyStackException();//stack이 비워져 있으니 예외 생성
      }
      Integer value; //돌려줄 숫자 선언
      value = this.elements[this.top]; //제일 최근에 들어온 값을 value에 담아줌
      this.top--; //top의 값을 빼줌

      return value; //value 리턴
   }

   @Override
   public Integer peek() {//stack의 최상위 데이터를 돌려준다
      if(this.size() == 0){
         throw new EmptyStackException();
      }
      return this.elements[this.top]; //이거는 그냥 한번 보여주는거기 때문에 pop()처럼 인덱스 값을 빼줄 필요 없음
   }

   @Override
   public int size() {
      return this.top+1;
   }

   public Stack reverse(){
      Integer element;
      Stack newStack = new ArrayStack();
      for(int i =0; i< this.top; i++){
         if(elements[i] != null){
            element = this.elements[i];
            newStack.push(element);
         }
      }
      return newStack;
   }

   public Integer penultimate(){
      this.top--;
      Integer value = elements[this.top];
      return value;
   }

   public Integer bottom(){
      int size = this.top;
      for(int i = 0; i<size; i++){
         this.top--;
      }
      Integer value = elements[this.top];
      return value;
   }
   public Integer popBottom(){
      int size = this.top;
      for(int i = 0; i<size; i++){
         this.top--;
      }
      Integer value = elements[this.top];
      this.top--;
      return value;
   }







}


