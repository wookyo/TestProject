package com.example.testappliction.codinginterview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testappliction.R;

import java.util.ArrayList;
import java.util.Stack;

public class CodeTestInterview3Activity extends Activity {
    private String TAG = "CodeTestInterview1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTest ();
    }

    private void mainTest(){
        Log.d(TAG, "[mainTest]");
    }

    ///////////////////////////////////////////////////
    /**
     * [Coding Interview 문제]
     *  가장 작은값이 위로 오도록 스택을 정렬하는 프로그램을 작성하라
     *  추가적으로 하나정도의 스택을 사용해도 되지만
     *  스택에 보관된 요소를 배열등의 다른 자료로 복사 할수는 없다
     *  스택은 push / pop / peek / isEmpty 연산을 제공해야 한다.
     * */
    private void testCodingInterview35(){

    }

    private void sortStack(Stack input){
        Stack temp = new Stack();
        while(!input.isEmpty()){
            // input 의 원소를 정렬된 순서로 temp 에 삽입
            int tmp = (int)input.pop();
            while(!temp.isEmpty() && (int)temp.peek() > tmp){
                input.push(temp.pop());
            }
            temp.push(tmp);
        }
        // temp 의 원소를 정렬된 순서로 input 에 삽입
        while(!temp.isEmpty()){
            input.push(temp.pop());
        }
    }

    public class SortStack extends Stack{
        Stack temp = new Stack();

        public void push(int input){
            if(input <= getMin(input)){
                temp.push(input);
            }
            super.push(input);

        }

        public Integer pop(){
            int value = (int)super.pop();
            return value;
        }

        public Integer peek(){
            return (int)super.peek();
        }

        public boolean isEmpty(){
            return super.isEmpty();
        }

        private int getMin(int input){
            if(temp.isEmpty()){
                return Integer.MAX_VALUE;
            }
            return (int)temp.peek();
        }
    }


    ///////////////////////////////////////////////////
    /**
     * [Coding Interview 문제]
     *  스택 두개로 큐 하나를 구현한 MyQueue 클래스를 구현하라
     * */
    private void testCodingInterview34(){
        Log.d(TAG, "[testCodingInterview34]");
        MyQueue obj = new MyQueue();
        obj.add(5);
        obj.add(6);
        obj.add(3);
        obj.add(7);
        obj.add(8);

        obj.add(4);
        obj.add(15);
        obj.add(20);
        obj.add(2);
        Log.d(TAG, "[testCodingInterview34] pushItem Result : "+obj.peek());

        obj.remove();
        Log.d(TAG, "[testCodingInterview34] removeItem Result : "+obj.peek());
    }

    public class MyQueue{
        Stack stackNew, stackOld;

        public MyQueue(){
            stackNew = new Stack();
            stackOld = new Stack();
        }

        public int size(){
            return stackNew.size() + stackOld.size();
        }

        public void add(Object input){
            stackNew.push(input);
        }

        public Object remove(){
            shiftStack();
            return stackOld.pop();
        }

        public Object peek(){
            shiftStack();
            return stackOld.peek();
        }

        private void shiftStack(){
            if(stackOld.isEmpty()){
                while(!stackNew.isEmpty()){
                    stackOld.push(stackNew.pop());
                }
            }
        }
    }

//    public class MyQueue{
//        private int maxCapacity = 5;
//        private ArrayList<Stack> stackList = new ArrayList<Stack>();
//
//        public void add(int input){
//            Stack item = getLastStack();
//            Log.d(TAG, "######### [testCodingInterview34] push : "+input);
//            item.push(input);
//        }
//
//        public void remove(){
//             ArrayList<Integer> itemList = new ArrayList<Integer>();
//            for(Stack item : stackList){
//                int count = item.size();
//                for(int c = 0; c < count; c++){
//                    int addItem = (int)item.peek();
//                    Log.d(TAG, "######### [testCodingInterview34] addItem : "+addItem + " / "+item.size());
//                    itemList.add(addItem);
//                    item.pop();
//                }
//            }
//
//            stackList.clear();
//            for(int i = itemList.size()-2 ; i > -1 ; i-- ){
////                Log.d(TAG, "######### [testCodingInterview34] itemList.get(i) : "+itemList.get(i));
//                pushItem(itemList.get(i));
//            }
//        }
//
//        public int getLastItem(){
//            return (int)getLastStack().peek();
//        }
//
//        private Stack getLastStack() {
//            if (stackList.size() == 0 || stackList.get(stackList.size() - 1).size() >= maxCapacity) {
//                Stack result = new Stack();
//                stackList.add(result);
//                return result;
//            }
//            return stackList.get(stackList.size() - 1);
//        }
//    }






    ///////////////////////////////////////////////////
    /**
     * [Coding Interview 문제]
     * 접시 무더기를 생각해보라
     * 현실에서는 접시를 쌓다가 일정높이 이상 쌓이면 새로운 무더기를 만든다
     * 이것을 흉내내는 자료구조 SetOfStack을 구현하라
     *
     * setOfStack은 여러개의 스택으로 구성되며
     * 이전 스택이 지정된 용량을 초과 하는 경우 새로운 스택을 생성해야 한다
     * SetOfStack.push() 와 SetOfStack.pop()은 동일하게 동작해야 한다.
     * (다시 말해 pop()은 정확히 하나의 스택이 있을때와 동일한 값을 반환해야 한다)
     * */
    private void testCodingInterview33(){
        Log.d(TAG, "[testCodingInterview33]");
        SetOfStack obj = new SetOfStack();
        obj.push(5);
        obj.push(6);
        obj.push(3);
        obj.push(7);
        obj.push(8);

        obj.push(4);
        obj.push(15);
        obj.push(20);
        obj.push(2);
        obj.push(100);

        obj.push(200);
        obj.push(300);
        obj.push(110);

        Log.e(TAG, "[testCodingInterview33] push Result : " +obj.getStackCount());

        obj.pop();
        obj.pop();
        obj.pop();
        obj.pop();
        Log.e(TAG, "[testCodingInterview33] pop Result : " +obj.getStackCount() +" / "+obj.peek());
    }

    public class SetOfStack extends Stack{
        private int maxCapacity = 5;
        private ArrayList<Stack> stackList = new ArrayList<Stack>();

        public void push(int input){
            Stack temp = getLastStack();
            if(isOverSize(temp)){
                Stack newStack = new Stack();
                newStack.push(input);
                stackList.add(newStack);
            }else{
                temp.push(input);
            }

            super.push(input);
        }

        public Integer pop(){
            Stack temp = getLastStack();
            if(temp.size() == 0){
                return 0;
            }

            if(isMinSize(temp)){
                stackList.remove(stackList.size() -1);
            }else{
                temp.pop();
            }
            return (int)super.pop();
        }

        public Integer peek(){
            return (int)getLastStack().peek();
        }

        public int getStackCount(){
            return stackList.size();
        }

        private Stack getLastStack() {
            if (stackList.size() == 0) {
                Stack result = new Stack();
                stackList.add(result);
                return result;
            }
            return stackList.get(stackList.size() - 1);
        }

        private boolean isMinSize(Stack input){
            return input.size() == 1 ;
        }

        private boolean isOverSize(Stack input){
            return input.size() >= maxCapacity;
        }
    }
    ///////////////////////////////////////////////////
    /**
     * [Coding Interview 문제]
     * 기본적인 push 와 pop 기능이 구현된 스택에서
     * 최솟값을 반환하는 min 메소드를 작성하라
     * */
    private void testCodingInterview32(){
        Log.d(TAG, "[testCodingInterview32]");
        NodeWithMinStack obj = new NodeWithMinStack();
        obj.push(5);
        obj.push(6);
        obj.push(3);
        obj.push(7);
        obj.push(8);
        obj.push(4);
        obj.push(15);
        obj.push(20);
        obj.push(2);
        obj.push(100);

        obj.pop();
        obj.pop();

        Log.e(TAG, "[testCodingInterview32] Result : "+obj.min() + " / "+obj.peek() +" / "+obj.getTempSize());
    }

    public class NodeWithMinStack extends Stack {
        private Stack temp;

        public NodeWithMinStack(){
            temp = new Stack();
        }

        public void push(int input){
            if(input <= min()){
                temp.push(input);
            }
            super.push(input);
        }

        public Integer pop(){
            int value = (int)super.pop();
            if(value == min()){
                temp.pop();
            }
            return value;
        }

        private int min(){
            if(temp.isEmpty()){
                return Integer.MAX_VALUE;
            }
            return (int)temp.peek();
        }

        public int getTempSize(){
            return temp.size();
        }
    }

    ///////////////////////////////////////////////////
    /**
     * [Coding Interview 문제]
     * 배열 한개로 스택 세개를 구현하라
     * */
    private void testCodingInterview31(){

    }

    public class FixedMultiStack{
        private int numberOfStack = 3;
        private int stackCapacity;
        private int[] values;
        private int[] sizes;

        public FixedMultiStack(int stackSize){
            stackCapacity = stackSize;
            values = new int[stackSize * numberOfStack];
            sizes = new int[numberOfStack];
        }

        public void push(int stackNum, int value){
            if(isFull(stackNum)){
                return;
            }
            // 스택 포인터를 증가 , 가장 꼭대기 값을 갱신
            sizes[stackNum]++;
            values[indexOfTop(stackNum)] = value;
        }

        public int pop(int stackNum){
            if(isEmpty(stackNum)){
                return 0;
            }
            int topIndex = indexOfTop(stackNum);
            int value  = values[topIndex];  // 가장 꼭대기 값리턴
            values[topIndex] = 0;           // 꼭대기 값 삭제
            sizes[stackNum]--;              // 스택의 사이즈 줄임
            return value;
        }

        private boolean isEmpty(int stackNum){
            return sizes[stackNum] == 0;
        }

        private int indexOfTop(int stackNum){
            int offSet = stackNum * stackCapacity;
            int size = sizes[stackNum];
            return offSet + size -1;
        }

        private boolean isFull(int stackNum){
            return sizes[stackNum] == stackCapacity;
        }

    }

    public class MyStack{
        private class MyStackNode{
            private Object data;
            private MyStackNode next;

            public MyStackNode(Object data){
                this.data = data;
            }
        }

        MyStackNode top;
        public void add(Object data){
            MyStackNode temp = new MyStackNode(data);
            temp.next = top;
            top = temp;
        }
    }

    ///////////////////////////////////////////////////
}
