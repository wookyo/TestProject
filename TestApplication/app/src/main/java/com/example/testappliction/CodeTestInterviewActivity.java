package com.example.testappliction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.testappliction.codinginterview.CodeTestInterview1Activity;
import com.example.testappliction.codinginterview.CodeTestInterview2Activity;
import com.example.testappliction.codinginterview.CodeTestInterview5Activity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * 코딩 인터뷰 관련된 클래스
 * 예제를 제외한 메소드들 정리
 * */
public class CodeTestInterviewActivity extends Activity {
    private String TAG = "CodeTestInterviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTest ();
    }

    private void mainTest(){
        Log.d(TAG, "[mainTest]");
        goChapterActivity();
    }

    private void goChapterActivity(){
        Intent intent = new Intent(CodeTestInterviewActivity.this,
                CodeTestInterview2Activity.class);
        startActivity(intent);
    }

    ///////////////////////////////////////////////////
    /**
     * BIT 의 주어진 인텍스 변경
     * */
    private int chapter5_updataBit(int num, int i){
        int mask = ~(1 << i);
        return (num & mask) | (0 << i);
    }

    ///////////////////////////////////////////////////
    /**
     * BIT 의 주어진 인텍스 삭제
     * */
    private int chapter5_clearBit(int num, int i){
        int mask = ~ (1 << i);
        return num & mask;
    }

    /**
     * 최상위 비트에서 BIT 의 주어진 인텍스까지 삭제
     * */
    private int chapter5_clearBitMSthroughtI(int num, int i){
        int mask = (1 << i) -1;
        return num & mask;
    }

    /**
     * 0번째 부터  비트에서 BIT 의 주어진 인텍스까지 삭제
     * */
    private int chapter5_clearBitMSthrought0(int num, int i){
        int mask = -1 << (i + 1);
        return num & mask;
    }

    ///////////////////////////////////////////////////
    /**
     * BIT 의 주어진 인텍스 채워 넣기
     * */
    private int chapter5_setBit(int num, int i){
        return num | (0 << i);
    }

    ///////////////////////////////////////////////////
    /**
     * BIT 의 주어진 인텍스 확인
     * */
    private boolean chapter5_getBit(int num, int i){
        return ((num & (1 << i)) != 0);
    }


    ///////////////////////////////////////////////////
    /**
     * 누 노드사이의 모든경로를 출력하라
     *
     * DFSAlgorithm d = new DFSAlgorithm();
     *         d.inputData(1, 2);
     *         d.inputData(1, 3);
     *         d.inputData(2, 3);
     *         d.inputData(2, 5);
     *         d.inputData(3, 4);
     *         d.inputData(3, 5);
     *         d.inputData(4, 5);
     *
     * //        d.DFS(1);
     *
     *         d.DFS(1,4);
     *
     * */
    private void findDFSPath(){
        DFSAlgorithm d = new DFSAlgorithm();
        d.inputData(0, 1);
        d.inputData(1, 2);
        d.inputData(2, 0);
        d.inputData(3, 2);


//        d.DFS(1);
        d.DFS(1,0);
    }


    class DFSAlgorithm{
        private int maps[][] = new int [6][6];      //DFS 인접행렬
        private boolean visit[] = new boolean[6];   //방문했나 안했나 판단할 변수
        private Stack<Integer> stack;

        public DFSAlgorithm(){
            //클래스 생성자
            //스택을 초기화하고
            //table 및  visit 변수를 할당 한다.

            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){
                    maps[i][j] = 0;
                }
            }

            for(int i=0;i<6;i++){
                visit[i] = false;
            }

            stack = new Stack();
        }

        public void inputData(int i,int j){
            //데이터를 집어넣는 함수
            //i,j를 넣으면 인접행렬에 값을 넣어준다.
            //무방향 그래프이므로 대칭해서 넣어준다.
            maps[i][j] = 1;
            maps[j][i] = 1; // 주석이면 단방향 그래프

        }

        public void DFS(int v){
            //DFS 구현 부분
            visit[v] = true;
            System.out.print(v); //방문노드를 보여주기위한 출력
            for(int i=0;i < 6; i++){
                if(maps[v][i] == 1 && !visit[i]){
                    //노드가 이어져있고 방문을 하지 않았으면
                    Log.e(TAG,"[findDFS] " +  "->" + i + "로 이동합니다");
                    DFS(i);
                }
            }
        }

        //v :출발노드 goal:도착노드
        public void DFS(int v, int goal){
            //DFS 구현 부분
            visit[v] = true;
            stack.push(v); //스택에 값을 넣어준다.

            //목표노드에 왔으면
            if(v == goal){

                //// 스택값 출력
                int count = stack.size(); //스택의 크기를 받을 변수
                for(int i=0;  i< count; i++){
                    Log.d(TAG,"[findDFS] " + stack.elementAt(i));
                }
                Log.e(TAG,"[findDFS]출력완료 ");
                //// 스택값 출력

                stack.pop(); //DFS에서 빠져나올땐  pop을 합니다.
                return;
            }


            for(int i = 0; i < 6; i++){
                if(maps[v][i] == 1 && !visit[i]){
                    //노드가 이어져있고 방문을 하지 않았으면
                    DFS(i,goal);
                    //DFS에서 빠져나오면 해당노드는 방문하지 않은 것으로 한다.
                    visit[i] = false;
                }
            }
            stack.pop(); //DFS에서 빠져나올땐 pop을 합니다.
        }
    }
    ///////////////////////////////////////////////////
    /**
     * 깊이 우선 탐색
     * 예제 >
     * // 노드의 수
     * 	int n = 7;
     * 	int input[][] = {{0,0,0,0,0,0,0,0},
     *                        {0,0,1,1,0,0,0,0},
     *            {0,1,0,0,1,1,0,0},
     *            {0,1,0,0,0,0,0,0},
     *            {0,0,1,0,0,0,0,1},
     *            {0,0,1,0,0,0,1,0},
     *            {0,0,0,0,0,1,0,0},
     *            {0,0,0,0,1,0,0,0}};
     *
     * int input [][] = {{0,1,0,0}, {0,0,1,0}, {1,0,0,0}, {0,0,1,0}};
     * */
    int n = 7;
    int[] find = new int[n+1];
    int arr[][] = {{0,0,0,0,0,0,0,0},
                        {0,0,1,1,0,0,0,0},
                        {0,1,0,0,1,1,0,0},
                        {0,1,0,0,0,0,0,0},
                        {0,0,1,0,0,0,0,1},
                        {0,0,1,0,0,0,1,0},
                        {0,0,0,0,0,1,0,0},
                        {0,0,0,0,1,0,0,0}};

    private void testDFS(){
        int input [][] = {{0,1,0,0}, {0,0,1,0}, {1,0,0,0}, {0,0,1,0}};
        DepthFirstSearch obj = new DepthFirstSearch();
        obj.build(input, 0);
    }

//    // 인접리스트을 통해 구현한 DFS
//    public class DfsTest02 {
//         int Nv;
//         int Ne;
//         boolean[] visit;
//         ArrayList<ArrayList<Integer>> ad;
//
//        public  void dfs(int i){
//            visit[i] = true;
//            System.out.print(i);
//
//            for(int j : ad.get(i)){  // 배열 null check
//                if(visit[j] == false){
//                    dfs(j);
//                }
//            }
//        }
//
//
//        public void build(int [][] input, int start) {
//
//            Nv = input.length;
//            Ne = start;
//            ad = new ArrayList(Nv+1);   // 인접 리스트 초기화
//            visit = new boolean[Nv+1];              // visit 배열 초기화
//
//            for(int i = 0; i < Nv+1; i++){ // 인접 리스트 속의 리스트 초기화
//                ad.add(new ArrayList());
//            }
//
//            for(int i = 0; i < Ne; i++){
//                int t1 = scan.nextInt();
//                int t2 = scan.nextInt();
//
//                ad.get(t1).add(t2);
//                ad.get(t2).add(t1);
//            }
//
//            dfs(1);
//        }
//
//    }



    /**
     * 인접행렬을 통해 구현한 DFS
     * */
    public class DepthFirstSearch {
         int vertex;            //정점의 개수
         int startVertex;       //시작 정점
         int[][] vertexMap;     //인접 행렬 생
         int[] vertexVisit;     //정점의 방문 여부를 가리키는 배열


        public void build(int [][] input, int start) {
            //정점의 개수 입력
            vertex = input.length;
            //시작할 정점입력
            startVertex = start;
            //+1을 시킨 이후는 정점의 시작을 0이아닌 1로시작하기 위해서이다.!
            vertexMap = new int[vertex+1][vertex+1];
            //+1을 시킨 이후는 정점의 시작을 0이아닌 1로시작하기 위해서이다.!
            vertexVisit = new int[vertex+1];

            for(int i = 0; i< input.length; i++){
                for(int j = 0; j < input[i].length; j++){
                    if(input[i][j] == 1){
                        vertexMap[i][j] = vertexMap[j][i] = 1;
                    }
                }
            }
            dfs(startVertex); //dfs를 시작합니다.

        }

        public  void dfs(int start) {
            // 함수 호출 시, visit 했음을 표시
            vertexVisit[start] = 1;

            for(int i = 1; i <= vertex; i++) {
                if(vertexMap[start][i] == 1 && vertexVisit[i] == 0) {
                    // j를 방문하지 않았으면 j를 방문한다.
                    Log.e(TAG,"[dfs] " + start + "->" + i + "로 이동합니다");
                    dfs(i);
                }
            }
        }
    }
    /////////////////////////
    /**
     * 간단한 스택 구현
     * */
    private void chapter3_CreateStack(){

    }

    public class MyStack{
        private class StackNode{
            private Object data;
            private StackNode next;

            public StackNode(Object data){
                this.data = data;
            }
        }

        private StackNode top;
        public void push(Object item){
            StackNode t = new StackNode(item);
            t.next = top;
            top = t;
        }

        public Object pop(){
            if(top == null){
                return null;
            }
            Object item = top.data;
            top = top.next;
            return item;
        }

        public Object peek(){
            if(top == null){
                return null;
            }
            return top.data;
        }

        public boolean isEmpty(){
            return top == null;
        }
    }
    /////////////////////////
    /**
     * 간단한 큐 구현
     * */
    private void chapter3_CreateQueue(){

    }

    public class MyQueue1{
        private QueueNode first;
        private QueueNode last;

        public void add(Object item){
            QueueNode temp = new QueueNode(item);
            if(last != null){
                last.next = temp;
            }
            last = temp;
            if(first == null){
                first = temp;
            }
        }

        private Object remove(){
            Object temp = first.data;
            first = first.next;
            if(first == null){
                last = null;
            }
            return temp;
        }

        public Object peek(){
            if(first == null){
                return new NoSuchElementException();
            }
            return first.data;
        }

        public boolean idEmpty(){
            return first == null;
        }

        private class QueueNode{
            private Object data;
            private QueueNode next;

            public QueueNode(Object data){
                this.data = data;
            }
        }
    }

    ////////////////////////////
    // 노드 삭제
    private void chapter2_delNode(Node input){
        if(input == null || input.next == null){
            return;
        }
        Node next = input.next;
        input.data = next.data;
        input.next = next.next;
    }
    ////////////////////////////
    // 노드 뒤집기
    private Node chapter2_reverseNode(Node input){
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

    ////////////////////////////
    // 노드값 비교
    private boolean chapter2_isEqualNode(Node inputA, Node inputB){
        while(inputA != null && inputB != null){
            if(inputA.data != inputB.data){
                return false;
            }
            inputA = inputA.next;
            inputB = inputB.next;
        }
        return (inputA == null) && (inputB == null);
    }

    ////////////////////////////
    /**
     * 단방향 연결 리스트에서 노드 삭제
     * */
    private Node chapter2_deleteNode(Node head, int data){
        Node n = head;
        // head를 움직인다.
        if((int)n.data == data){
            return head.next;
        }
        while(n.next != null){
            if((int)n.next.data == data){
                n.next = n.next.next;
                return head; // head는 변하지 않는다.
            }
            n = n.next;
        }
        return head;
    }
////////////////////////////
    /**
     * 연결 리스트 만들기
     *
     * */
    private void chapter2_CreateNode(){
    }

    /**
     *  단방향 연결리스트 구현
     * */
    public class Node{
        Object data;
        Node next;

        public Node(){
        }

        public Node(int data){
            this.data = data;
        }

        public void appendToTail(int data){
            Node end = new Node(data);
            Node n = this;

            while(n.next != null){
                n = n.next;
            }
            n.next = end;
        }
    }
    ////////////////////////////
}
