package com.example.testappliction.codinginterview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testappliction.R;

import java.util.HashSet;
import java.util.Stack;

public class CodeTestInterview2Activity extends Activity {
    private String TAG = "CodeTestInterview2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTest ();
    }

    private void mainTest(){
        Log.d(TAG, "[mainTest]");
        int[] inputA = {7,1,6};
        Node nodeA = new Node();
        nodeA.build(nodeA, inputA);

        int[] inputB = {5,9,2};
        Node nodeB = new Node();
        nodeB.build(nodeB, inputB);

        String[] inputC = {"A","B","C","D","E","C"};
        Node nodeC = new Node();
        nodeC.build(nodeC, inputC);

//        Log.d(TAG, "[mainTest] Result : "+testCodingInterview28(nodeC));
        Node result = testCodingInterview28(nodeC);
        Log.d(TAG, "[mainTest] Result : "+result);
        if(result != null){
            result.display(result);
        }
    }
    ///////////////////////////////////
    /**
     * 순환 연결리스트가 주어 졌을때 ,
     * 순환되는 부분의 첫째 노드를 반환하는 알고리즘을 작성하라.
     * 순환연결 리스트란 노드의 next포인터가 앞선 노드들 가운데
     * 어느하나를 가리키도록 설정되어 있는
     * 엄밀히 말해서 변질된 방식의 리스트를 의미 한다.
     * ex>
     * 입력 : A -> B -> C -> D -> E -> C
     * 출력 : C
     */
    private Node testCodingInterview28(Node input){
        Node slow = input;
        Node fast = input;

        // 만나는 지점을 체크, 연결 리스트에서 loop size -k 만큼 들어간 상태이다.
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){ // 충돌
                break;
            }
        }
        // 에러 체크, 만나는 지점이 없으면 루프도 없다.
        if(fast == null || fast.next == null){
            return null;
        }
        // slow를 input으로 옮기고 fast 는 그대로 둔다.
        // 이 둘은 루프시작 시점에서 k 만큼 떨어져 있다.
        // 이 둘이 같은 속도로 움직인다면 시작 시점에서 만나게 된다.
        slow = input;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        // 둘다 루프의 시작지점을 가르킨다.
        return fast;
    }

    private Node testCodingInterview28_1(Node input){
        HashSet map = new HashSet();

        while(input != null){
            if(map.contains((String)input.data)){
                return input;
            }else{
                map.add(input.data);
            }
            input = input.next;
        }
        return input;
    }

    ///////////////////////////////////
    /**
     * 단 방향 연결 리스트가 주어 졌을때
     * 이 리스트의 교집합 노드를 찾은뒤 반환하는 메소드를 작성하라.
     * 여기서 교집합이란 노드의 값이 아니라 노드의 주소가 완전히 같은 경우를 말한다.
     * 즉, 첫번째 리스트에 있는 k번째 노드와 두번째 리스트에 있는 j번째 노드가
     * 주소까지 완전히 같다면 이 노드는 교집합의 원소가 된다.
     */
    private Node testCodingInterview27(Node inputA, Node inputB){
        if(inputA == null ||inputB == null ){
            return null;
        }
        Result tempA = getTailAndSize(inputA);
        Result tempB = getTailAndSize(inputB);

        // 마지막 노드가 다르면 교집합이 다르다는것이다.
        if(tempA.tail != tempB.tail){
            return null;
        }

        // 각 연결리스트의 시작점에 포인트를 둔다.
        Node shorter = tempA.size < tempB.size ? inputA : inputB;
        Node longer  = tempA.size < tempB.size ? inputB : inputA;

        // 길이가 긴 연결 리스트의 포인터를 길이의 차만큼 앞으로 움직인다.
        longer = getKthNode(longer, Math.abs(tempA.size - tempB.size));

        // 두 포인터가 만날때까기 앞으로 움직인다.
        while(shorter != longer){
            shorter = shorter.next;
            longer = longer.next;
        }
        return longer;
    }

    // 길이가 긴 연결 리스트의 포인터를 길이의 차만큼 움직인다.
    private Node getKthNode(Node intputA, int inputB){
        while(inputB > 0 && intputA != null){
            intputA = intputA.next;
            inputB --;
        }
        return intputA;
    }

    // 마지막 노드와 그 길이를 구한다.
    private Result getTailAndSize(Node input){
        if(input == null ){
            return null;
        }
        int count = 0;
        Node temp = input;
        while(temp != null ){
            count++;
            temp = temp.next;
        }
        return new Result(temp, count);
    }

    private Node testCodingInterview27_1(Node inputA, Node inputB){
        HashSet set = new HashSet();
        while(inputA != null){
            set.add(inputA);
            inputA = inputA.next;
        }
        Node result = null;

        while(inputB != null){
            if(set.contains(inputB)){
                if(result == null){
                    result = inputB;
                    result.next = result;
                }else{
                    result.next = inputB;
                    result = inputB;
                }
            }
            inputB = inputB.next;
        }
        return result;
    }

    ///////////////////////////////////
    /**
     * 주어진 연결 리스트가 회문인지 검사하는 메소드를 작성하라.
     * ex>
     * 입력 : 0 -> 1 -> 2 -> 1 -> 0
     * 출력 : true
     * */
    // fast runner 와 slow runner 기법 사용하여 list 순회
    private boolean testCodingInterview26(Node input){
        Node fast = input;
        Node slow = input;
        Stack stack = new Stack();

        // 연결 리스트의 반을 스택에 쌓는다.
        // fast runner (2배빠른) 가 연결 리스트끝에 도달시 slow runner가 절반에 도달
        while(fast != null && fast.next!= null){
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }
        // 원소의 갯수가 홀수일 경우
        if(fast != null){
            slow = slow.next;
        }
        // 값 비교
        while(slow != null){
            int top = (int)stack.pop();
            if((int)slow.data != top){
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    private boolean testCodingInterview26_1(Node input){
        Node temp = reverseNode(input);
        return isEqualNode(input, temp);
    }

    // 노드 뒤집기
    private Node reverseNode(Node input){
        Node head = null;
        while(input != null){
            Node n = new Node();
            n.data = input.data;
            n.next = head;
            head = n;
            input = input.next;
        }
        return head;
    }

    // 노드값 비교
    private boolean isEqualNode(Node inputA, Node inputB){
        while(inputA != null && inputB != null){
            if(inputA.data != inputB.data){
                return false;
            }
            inputA = inputA.next;
            inputB = inputB.next;
        }
        return (inputA == null) && (inputB == null);
    }

    ///////////////////////////////////
    /**
     * 연결 리스트로 숫자를 표현할때 각 노드가 자릿수 하나를 가르키는 방식으로 표현할수 있다.
     * 각 숫자는 역순으로 배열되어 잇는데 ,
     * 즉 첫번째 자리수가 리스트의 맨앞에 위치 하도록 배열된다는 뜻이다.
     * 이와 같은 방식으로 표현된 숫자 두개가 있을경우
     * 이 두수를 더하여 그합을 연결 리스트로 반환하는 메소드를 작성하라.
     * ex>
     * 입력 : (7 -> 1 -> 6) + (5 -> 9 -> 2), 즉 617+295
     * 출력 : 2 -> 1 -> 9, 즉 912
     * */
    private Node testCodingInterview25(Node inputA, Node inputB, int carry){
        if(inputA == null && inputB == null && carry == 0 ){
            return null;
        }
        Node result = new Node();
        int value = carry;

        if(inputA != null
                && inputA.data != null){
            value += (int)inputA.data;
        }
        if(inputB != null
                && inputB.data!= null){
            value += (int)inputB.data;
        }
        // 두번째 자리 숫자
        result.data = value % 10;
        if(inputA != null || inputB != null) {
            Node more = testCodingInterview25(
                    inputA == null ? null : inputA.next,
                    inputB == null ? null : inputB.next,
                    value >= 10 ? 1 : 0);
            result.next = more;
        }
        return result;
    }
    ///////////////////////////////////
    /**
     * 값 X 가 주어졌을경우
     * x보다 작은 노드들을 x 보다 크거나 같은 노드들보다 앞에 오는 메소드를 작성하라.
     * 만약 x가 리스트에 잇다면 x는 그보다 작은 원소들보다 뒤에 나오기만 하면 된다.
     * 즉 원소 x는 '오른쪽 그룹' 어딘가에만 존재하면 된다.
     * 왼쪽과 오른쪽 그룹사이에 있을필요는 없다.
     * ex>
     * 입력 : 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [분할값 x = 5]
     * 출력 : 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
     * */
    private Node testCodingInterview24(Node inputA, int inputB){
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        // 리스트 분활
        while(inputA != null){
            Node next = inputA.next;
            inputA.next = null;

            if(inputA.data != null){
                if((int)inputA.data < inputB){
                    // before 리스트 끝에 원소 삽입
                    if(beforeStart == null){
                        beforeStart = inputA;
                        beforeEnd = beforeStart;

                    }else{
                        beforeEnd.next = inputA;
                        beforeEnd = inputA;
                    }
                }else{
                    // after 리스트 끝에 원소 삽입
                    if(afterStart == null){
                        afterStart = inputA;
                        afterEnd = afterStart;

                    }else{
                        afterEnd.next = inputA;
                        afterEnd = inputA;
                    }
                }
            }
            inputA = next;
        }
        if(beforeStart == null){
            return afterStart;
        }
        beforeEnd.next = afterStart;
        return beforeStart;
    }

    private Node testCodingInterview24_1(Node inputA, int inputB){
        Log.d(TAG, "[testCodingInterview24]");
        Node before = new Node();
        Node after = new Node();

        while(inputA != null){
            if(inputA.data != null){
                if((int)inputA.data < inputB){
                    before.next = inputA;
                }else{
                    after.next = inputA;
                }
            }
            inputA = inputA.next;
        }
        before.next = after;
        return before;

    }

    ///////////////////////////////////
    /**
     * 단방향 연결 리스트가 주어 졌을때
     * 중간(정확히 가운데 노드일필요는 없고 처음과 끝만 아니면 된다.)에
     * 있는 노드 하나를 삭제 할수 있는 알고리즘을 구현하라.
     * 단 삭제할 노드에만 접근 가능하다
     * ex>
     * 입력 : a -> b -> c -> d ->e 에서 c 노드 삭제
     * 결과 : a -> b -> d -> e
     * */
    private void testCodingInterview23(Node input){
        int midTemp = 0;
        Node temp = input;
        while(temp != null){
            midTemp ++;
            temp = temp.next;
        }
        int mid = midTemp / 2;

        int count= 0;
        Node previous = null;
        while(input != null){
            count++;
            if(count == mid) {
                testCodingInterview23_del(input);
            }
            input = input.next;
        }
    }
    // 노드 삭제
    private void testCodingInterview23_del(Node input){
        if(input == null || input.next == null){
            return;
        }
        Node next = input.next;
        input.data = next.data;
        input.next = next.next;
    }

    private void testCodingInterview23_1(Node input){
        int midTemp = 0;
        Node temp = input;
        while(temp != null){
            midTemp ++;
            temp = temp.next;
        }
        int mid = midTemp / 2;

        int count= 0;
        Node previous = null;
        while(input != null){
            count++;
            if(count == mid){
                previous.next = input.next;
            }else{
                previous = input;
            }
            input = input.next;
        }
    }


    ///////////////////////////////////
    /**
     * 단방향 연결리스트가 주어 졌을때
     * 뒤에서 k 번째 원소를 찾는 알고리즘을 구현하라.
     * */
    // 포인터 2개 사용
    private int testCodingInterview22(Node inputA, int inputB){
        Node p1 = inputA;
        Node p2 = inputA;
        for(int i = 0; i < inputB; i++){
            if(p1.next == null){
                return 0;
            }
            p1 = p1.next;
        }
        while(p1 != null){
            p2  = p2.next;
            p1  = p1.next;
        }
        return (int)p2.data;
    }

    private int testCodingInterview22_1(Node inputA, int inputB){
      int index = testCodingInterview22_1(inputA.next, inputB)+1;
      if(index == inputB){
          Log.d(TAG, "[testCodingInterview22] Result : "+inputA.data);
      }
      return index;
    }
    ///////////////////////////////////
    /**
     * 정렬되어 있지 않는 연결리스트가 주어 졌을때,
     * 이 리스트에서 중복되는 원소를 제거하는 코드를 작성하라.
     * */
    private void testCodingInterview21(Node input){
        HashSet set = new HashSet();
        Node previous = null;
        while(input != null){
            if(set.contains(input.data)){
                previous.next = input.next;
            }else{
                set.add(input.data);
                previous = input;
            }
            input = input.next;
        }
    }

    public class Result{
        Node tail;
        int size;
        public Result(Node node, int size) {
            tail = node;
            this.size = size;
        }

    }
    /**
     *  단방향 연결리스트 구현
     * */
    public class Node{
        Object data;
        Node next;
        int size;

        public Node() {
        }




        public Node(Object data){
            this.data = data;
        }

        public void build(Node node, int[] input) {
            Node temp = null;
            temp = node;
            for (int i = 0; i < input.length; i++) {
                int item = input[i];
                temp.data = item;
                if (i != input.length - 1) {
                    Node itemTemp = new Node();
                    temp.next = itemTemp;
                    temp = itemTemp;
                }
            }
//            for(int item : input){
//                temp.data = item;
//                Node itemTemp = new Node();
//                temp.next = itemTemp;
//                temp = itemTemp;
//            }
        }

        public void build(Node node, String[] input) {
            Log.e(TAG, "[build]");
            Node temp = null;
            temp = node;

            for (int i = 0; i < input.length; i++) {
                String item = input[i];
                temp.data = item;
                if (i != input.length - 1) {
                    Node itemTemp = new Node();
                    temp.next = itemTemp;
                    temp = itemTemp;
                }
            }
//            for(String item : input){
//                temp.data = item;
//                Node itemTemp = new Node();
//                temp.next = itemTemp;
//                temp = itemTemp;
//            }
        }

        public void display(Node input){
            if(input != null){
                Log.d(TAG, "[display] : "+input.data);
                display(input.next);
            }
        }

        public void appendToTail(Object data){
            Node end = new Node(data);
            Node n = this;

            while(n.next != null){
                n = n.next;
            }
            n.next = end;
        }
    }
}
