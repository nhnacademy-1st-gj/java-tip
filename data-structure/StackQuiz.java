public class StackQuiz {
   public Stack reversed(Stack stack){ //3
      Stack newStack = new ArrayStack();
      while(!stack.isEmpty()){
         Integer element = stack.pop();
         newStack.push(element);
      }
      return newStack;
   }

   public Stack reversed2(Stack stack){
      Stack newStack = new ArrayStack(); //결과 반환을 위한 stack
      Stack tempStack = new ArrayStack(); //원본을 원래대로 돌려 두기 위한 Stack

      while(!stack.isEmpty()){ //stack이 비어있지 않다면
         Integer element = stack.pop(); //element에 stack.pop한 거를 넣고
         newStack.push(element); //newStack에 푸시
         tempStack.push(element); //tempStack에 푸시
      }//원본의 데이터를 newStackrhk tempStack에 옮겨서 빈 stack 됨
      //tempStack에 저장해둔 데이터를 이용해 원본 Stack 복구함

      while(!tempStack.isEmpty()){ //tempStack이 비어있지 않다면
         Integer element = tempStack.pop();
         stack.push(element);
      }
      return newStack;
   }

   public Stack reversed3(Stack stack){
      Stack newStack = new ArrayStack();
      Stack tempStack = new ArrayStack();
      Stack tempStack2 = new ArrayStack();

      while (!stack.isEmpty()){
         Integer element = stack.pop();
         newStack.push(element);
         tempStack.push(element);
      }

      while(!tempStack.isEmpty()){
         Integer element = tempStack.pop();
         tempStack2.push(element);
      }

      while ((!tempStack2.isEmpty())){
         Integer element = tempStack2.pop();
         stack.push(element);
      }

      return stack;
   }

   public static Integer penultimate(Stack stack){ //6번
      stack.pop();
      Integer element = stack.peek();
      return element;
   }
   public static Integer popPenultimate(Stack stack){ //7번
      stack.pop();
      Integer element = stack.pop();
      return element;
   }

   public static Integer bottom(Stack stack){ //8번
      int size = stack.size();
      for(int i =0; i<size-1 ; i++){
         stack.pop();
      }
      Integer element = stack.peek();
      return element;
   }
   public static Integer popBottom(Stack stack){ //9번
      int size = stack.size();
      for(int i =0; i<size-1 ; i++){
         stack.pop();
      }
      Integer element = stack.pop();
      return element;
   }


}
