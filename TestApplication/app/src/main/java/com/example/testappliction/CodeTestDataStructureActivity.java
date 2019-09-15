package com.example.testappliction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.Random;

/**
 * 자료구조 관련 activity
 * */
public class CodeTestDataStructureActivity extends Activity {
    private String TAG = "CodeTestDataStructureActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTest ();
    }

    private void mainTest(){
        Log.d(TAG, "[mainTest]");

        int[] input = {35,12,24,45,37,48,67};
        testTeeNodelink(input);
    }

    ///////////////////////////////////////////////////

    /**
     * 이진 탐색 트리
     * 이진트리 생성 후 inoder, preorder, postorder 탐색
     *
     * int[] input = {35,12,24,45,37,48,67}
     * inoder result :
     * preorder result :
     * postorder result :
     * */
    private void testTeeNodelink(int[] input){
        TeeNodelink obj = new TeeNodelink();

        for(int item : input){
            obj.build(obj.T, item);
        }
        Log.e(TAG, "#### [testTeeNodelink] inoder  ");
        obj.inoder(obj.T);

        Log.e(TAG, "#### [testTeeNodelink] preorder  ");
        obj.preorder(obj.T);

        Log.e(TAG, "#### [testTeeNodelink] postorder  ");
        obj.postorder(obj.T);
    }

    class TeeNodelink{
        int data;
        TeeNodelink leftLink;
        TeeNodelink righLink;
        TeeNodelink T;

        public void build(TeeNodelink obj, int value){
            Log.e(TAG, "#### [TeeNodelink] obj : "+obj +" / value : "+value);

            TeeNodelink temp = obj;
            while(obj != null){    // value가 들어갈 위치
                temp = obj;

                if(value < obj.data){   // 왼쪽 트리 탐색
                    obj = obj.leftLink;

                }else{                  // 오른쪽 트리 탐색
                    obj = obj.righLink;
                }

                if(obj == null){
                    obj = new TeeNodelink();
                    obj.data = value;
                    obj.leftLink = null;
                    obj.righLink = null;
                    if(temp != null){
                        if(value < temp.data){  // 왼쪽에 연결
                            temp.leftLink = obj;

                        }else if(value > temp.data){    // 오른쪽에 연결
                            temp.righLink = obj;
                        }
                    }else{
                        T = obj;                    // temp 가 null 일경우 루트노드 설정
                    }
                }
            }
        }

        /**
         * 중위 순회 (LVR)출력
         * */
        public void inoder(TeeNodelink root){
            if(root != null){
                inoder(root.leftLink);
                Log.d(TAG, "[TreeNode] inoder data : "+root.data);
                inoder(root.righLink);
            }
        }

        /**
         * 전위 순회 (VLR)출력
         * */
        public void preorder(TeeNodelink root){
            if(root != null){
                Log.d(TAG, "[TreeNode] preorder data : "+root.data);
                preorder(root.leftLink);
                preorder(root.righLink);
            }
        }

        /**
         * 후위 순회 (LRV)출력
         * */
        public void postorder(TeeNodelink root){
            if(root != null){
                postorder(root.leftLink);
                postorder(root.righLink);
                Log.d(TAG, "[TreeNode] postorder data : "+root.data);
            }
        }
    }
    ///////////////////////////////////////////////////

    /**
     * 이진 트리 Data 정렬 및 출력
     * inoder result : A+B*C
     * preorder result : *+ABC
     * postorder result : AB+C*
     * */
    private void testTreeNode(){
        TreeNode obj = new TreeNode();
        TreeNode nB = obj.build(null, "B", null);
        TreeNode nA = obj.build(null, "A", null);
        TreeNode nC = obj.build(null, "C", null);
        TreeNode n1 = obj.build(nA, "+", nB);
        TreeNode root = obj.build(n1, "*", nC);

        Log.d(TAG, "[testTreeNode] 이진트리 :" );
        obj.postorder(root);
    }

    class TreeNode{
        Object data;
        TreeNode left;
        TreeNode right;

        public TreeNode build(TreeNode treeLeft, Object data, TreeNode treeRight){
            TreeNode temp = new TreeNode();
            temp.data = data;
            temp.left = treeLeft;
            temp.right = treeRight;
            return temp;
        }

        /**
         * 중위 순회 (LVR)출력
         * */
        public void inoder(TreeNode root){
            if(root != null){
                inoder(root.left);
                Log.d(TAG, "[TreeNode] inoder data : "+root.data);
                inoder(root.right);
            }
        }

        /**
         * 전위 순회 (VLR)출력
         * */
        public void preorder(TreeNode root){
            if(root != null){
                Log.d(TAG, "[TreeNode] preorder data : "+root.data);
                preorder(root.left);
                preorder(root.right);
            }
        }

        /**
         * 후위 순회 (LRV)출력
         * */
        public void postorder(TreeNode root){
            if(root != null){
                postorder(root.left);
                postorder(root.right);
                Log.d(TAG, "[TreeNode] postorder data : "+root.data);
            }
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 힙 정렬 Data 정렬 및 출력
     * int[] input = {87,23,40,31,27,4,53,15,72};
     * */
    private void testHeapSort(int[] input){
        Log.d(TAG, "[testHeapSort] input : "+Arrays.toString(input));
        heapSort(input);
    }

    private int[] heapSort(int[] data){
        int size = data.length;
        for(int i = size/2; i > 0; i--){
            makeHeap(data, i, size);
        }
        do{
            int temp = data[0];
            data[0] = data[size - 1];
            data[size-1] = temp;
            size = size -1;
            makeHeap(data, 1, size);
        }
        while(size >1);
        return data;
    }

    private void makeHeap(int[] data, int parent, int size){
        int temp = data[parent - 1];
        while(parent <= size/2){
            int child = 2 * parent;
            if(child < size && data[child -1] < data[child]){
                child ++;
            }
            if(temp >= data[child -1]){
                break;
            }else{
                data[parent -1] = data[child -1];
                parent = child;
            }
        }
        data[parent - 1] = temp;
        Log.d(TAG, "[makeHeap] input : "+Arrays.toString(data));
    }

    ///////////////////////////////////////////////////
    /**
     * 합병 정렬 Data 정렬 및 출력
     * int[] input = {87,23,40,31,27,4,53,15};
     * */
    private int mSortData[] = new int[20];
    private void testMergeSort(int[] input){
        Log.d(TAG, "[testMergeSort] input : "+Arrays.toString(input));
        int size = input.length;
        mergeSort(input, 0, size -1);
    }

    private void mergeSort(int[] data, int low, int high){
        Log.d(TAG,"[mergeSort] low : "+low +" / high : "+high);
        int middle;

        if(low < high){
            middle = (low + high) /2;
            merge(data, low, middle, high);

            mergeSort(data, low, middle);
            mergeSort(data, middle+1, high);
        }
    }

    private void merge(int[] data, int low, int middle, int high){
        Log.e(TAG,"[merge] low : "+low +" / middle : "+middle +" / high : "+high);
        int size = data.length;
        int i = low;
        int j = middle + 1;
        int k = low;

        while(i <= middle && j <= high){
            Log.d(TAG,"[merge] i : "+i +" / data[i] : "+data[i]
                    +" j : "+j +" / data[j] : "+data[j] );

            if(data[i] <= data[j]){
                mSortData[k] = data[i++];
            }else{
                mSortData[k] = data[j++];
            }
            k++;
        }

        Log.e(TAG,"[merge] i : "+i +" / k : "+k );

        if(i > middle){
            for(int ii = j; ii <= high; ii++, k++){
                mSortData[k] = data[ii];
            }
        }else{
            for(int ii = i; ii <= middle; ii++, k++){
                Log.d(TAG,"[merge] ii : "+ii +" / k : "+k );

                mSortData[k] = data[ii];
            }
        }

        for(int ii = low; ii <= high; ii++){

            data[ii] = mSortData[ii];
        }
        Log.d(TAG, "[merge] result : "+Arrays.toString(data));
    }



    ///////////////////////////////////////////////////
    /**
     * 퀵 정렬 Data 정렬 및 출력
     * int[] input = {23,87,31,40,58,27,15,53,72};
     * */
    private void testQuickSort(int[] input){
        Log.d(TAG, "[testQuickSort] input : "+Arrays.toString(input));
        int size = input.length;
        quickSort(input, 0, size -1);
    }

    private void quickSort(int[] input, int start, int end){
        if(start < end){
            int low = start;
            int high = end;
            int pivot = input[(low+high) / 2];

            while(low < high){
                while(input[low] < pivot){
                    low ++;
                }
                while(input[high] > pivot){
                    high --;
                }
                if(low < high){
                    int temp = input[low];
                    input[low] = input[high];
                    input[high] = temp;
                }
            }
            Log.d(TAG, "[quickSort] input : "+Arrays.toString(input));
            quickSort(input, start, low -1);
            quickSort(input, low+1, end);
        }

    }

    ///////////////////////////////////////////////////
    /**
     * 쉘 정렬 Data 정렬 및 출력
     * int[] input = {23,87,31,40,3,27,15,53};
     * */
    private void testShellSort(int[] input){
        int size = input.length;
        int interval = size / 2;
        int t = 0;
        Log.d(TAG, "[testShellSort] input : "+Arrays.toString(input));

        while(interval >= 1){
            for(int i = 0; i< interval; i++){
                intervalSort(input, interval, i, size - 1);
            }
            Log.e(TAG, "[testShellSort] "+(t+1) +" 단계 / interval :  "+interval+" / data :" +Arrays.toString(input));
            interval = interval / 2;
            t++;
        }
    }

    private void intervalSort(int[] data, int interval, int start, int end) {
        Log.d(TAG, "[intervalSort] interval : " + interval + " / start : " + start + " / end : " + end);
        for (int i = start + interval; i <= end; i = i + interval) {
            int j;
            int item = data[i];
            for (j = i - interval; j >= start && item < data[j]; j -= interval) {
                data[j + interval] = data[j];
                data[j + interval] = item;
            }
        }
    }
    ///////////////////////////////////////////////////
    /**
     * 삽입 정렬로 Data 정렬 및 출력
     * */
    private void testInsertionSort(){
        int[] input = {23,87,31,40,3,27,15,53,72};

        InsertionSort sort = new InsertionSort();
        sort.insertionSort(input);
    }

    class InsertionSort{
        public void insertionSort(int[] input){
            Log.d(TAG, "[InsertionSort] input : "+Arrays.toString(input));
            int size = input.length;

            // 삽입 정렬 과정
            for(int i = 1; i< size; i++){
                int temp = input[i];
                int j = i;

                while(( j > 0) && input[j-1] > temp){
                    input[j] = input[j-1];
                    j--;
                }
                input[j] = temp;
                Log.d(TAG, "[SelectionSort] #### "+(i) +"단계 : "+Arrays.toString(input) );
            }
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 선택 정렬로 Data 정렬 및 출력
     * */
    private void testSelectionSort(){
        int[] input = {23,87,31,40,3,27,15,53,72};

        SelectionSort sort = new SelectionSort();
        sort.selectSort(input);
    }

    class SelectionSort{
        public void selectSort(int[] input){
            Log.d(TAG, "[SelectionSort] input : "+Arrays.toString(input));
            int size = input.length;

            // 선택 정렬 과정
            for(int i = 0; i < size-1; i++){
                int min = i;

                for(int j = i+1; j < size; j++){
                    if(input[j] < input[min]){
                        min = j;
                    }
                }
                int temp = input[i];
                input[i] = input[min];
                input[min] = temp;
                Log.d(TAG, "[SelectionSort] #### "+(i+1) +"단계 : "+Arrays.toString(input) );
            }
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 버블 정렬로 Data 정렬 및 출력
     * */
    private void testBubbleSort(){
        int[] input = {23,87,31,40,3,27,15,53,72};

        BubbleSort data = new BubbleSort();
        data.bubbleSort(input);
    }

    class BubbleSort{
        public void bubbleSort(int[] input){
            int size = input.length;
            Log.d(TAG, "[BubbleSort] input : "+Arrays.toString(input));

            // BubbleSort과정
            for(int i= size -1; i > 0; i--){
                boolean change = false;
                Log.d(TAG, "[BubbleSort] ########### "+(size-i) +"단계 : "+Arrays.toString(input) );

                for(int j = 0; j < i; j++){
                    if(input[j] > input[j+1]){
                        int temp = input[j];
                        input[j] = input[j+1];
                        input[j+1] = temp;
                        change = true;
                    }
                    Log.d(TAG, "[BubbleSort] : "+ Arrays.toString(input) );
                }
                if(!change){
                    break;
                }
            }
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 배열을 이용하여 덱 생성 /삽입/ 삭제
     * */
    private void testArrayDeque(){
        ArrayDeque queue = new ArrayDeque();
        queue.insertFirst("Han");
        queue.insertFirst(new Integer(35));
        queue.insertFirst("Park");
        queue.insertFirst(new Integer(20));

        Log.d(TAG, "[testArrayDeque] #### insertFirst 연산후 덱");
        queue.print();

        Log.d(TAG, "[testArrayDeque] #### deleteFirst 연산후 덱");
        queue.deleteFirst();
        queue.print();

        Log.d(TAG, "[testArrayDeque] #### deleteLast 연산후 덱");
        queue.deleteLast();
        queue.print();

        queue.insertLast("Shim");
        queue.insertLast(new Integer(20));

        Log.d(TAG, "[testArrayDeque] #### insertLast 연산후 덱");
        queue.print();
    }

    class ArrayDeque{
        private int front;
        private int rear;
        private int count;
        private int size;
        private int increasement;
        private Object[] itemDequeue;
        private Object emptyItem;

        public ArrayDeque(){
            front = 0;
            rear = 0;
            count  = 0;
            size = 5;
            increasement = 10;
            itemDequeue = new Object[size];
        }

        public void insertLast(Object x){
            itemDequeue[rear] = x;
            rear ++;
            if(rear == size){
                increaseArray();
            }
            count++;
        }

        public void insertFirst(Object x){
            if(count != 0){
                for(int i = count; i>0; i--){
                    itemDequeue[i] = itemDequeue[i - 1];
                }
            }
            itemDequeue[0] = x;
            count++;
            rear = count;
            if(rear == count){
                increaseArray();
            }
        }

        public Object deleteFirst(){
            if(isEmpty()){
                return isEmpty();
            }
            Object tempItem = itemDequeue[0];
            for(int i = 0; i < count; i++ ){
                itemDequeue[i] = itemDequeue[i+1];
            }
            count--;
            rear--;
            return tempItem;
        }

        public Object deleteLast(){
            if(isEmpty()){
                return isEmpty();
            }
            Object tempItem = itemDequeue[rear - 1];
            count--;
            rear--;
            return tempItem;
        }

        public Object getFirst(){
            if(isEmpty()){
                return isEmpty();
            }
            return itemDequeue[front];
        }

        public Object getLast(){
            if(isEmpty()){
                return isEmpty();
            }
            return itemDequeue[rear - 1];
        }

        public void print(){
            if(isEmpty()){
                return;
            }
            for(int i = 0; i < rear; i++){
                Log.d(TAG, "[ArrayDeque] : "+itemDequeue[i]);
            }
        }

        public boolean isEmpty(){
            return count == 0;
        }

        public void increaseArray(){
            size += increasement;                   // 새로운 배열크기
            Object[] tempArray = new Object[size];  // 확장된 임시배열
            for(int i = 0; i<rear; i++){
                tempArray[i] = itemDequeue[i];      // 임시 배열로 원소들 이동
            }
            itemDequeue = tempArray;                // 배열 참조 변수를 변경
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 연결 리스트로 큐의 삽입과 삭제
     * */
    private void testLinkedListQueue(){
        NodeQueue queue = new NodeQueue();
        Node temp;

        queue.front = queue.rear = null;
        queue.insert("Kim");
        queue.insert(new Integer(35));
        queue.insert("Park");
        queue.insert("82");
        queue.insert("Shim");
        queue.insert("38");

        Log.d(TAG, "[testLinkedListQueue] ######### 큐 출력");
        queue.print();

        Log.d(TAG, "[testLinkedListQueue] ######### 큐 삭제 후 출력");
        queue.delete();
        queue.print();

        Log.d(TAG, "[testLinkedListQueue] ######### 큐 삭제 후 출력");
        queue.delete();
        queue.print();
    }

    class Node{
        Object data;
        Node link;
    }

    class NodeQueue{
        Node front, rear;

        public void insert(Object x){
            Node temp;
            if(rear == null){
                rear = new Node();
                rear.data = x;
                front = rear;
            }else{
                temp = new Node();
                temp.data = x;
                rear.link = temp;
                rear = temp;
                rear.link = null;
            }
        }

        public void delete(){
            if(front == null) {
                Log.d(TAG, "[NodeQuee] 공백 리스트입니다.");
            }else{
                front = front.link;
                if(front == null){
                    rear = null;
                }
            }
        }

        public void print(){
            Node temp;
            temp = front;
            while(temp != null){
                Log.d(TAG, "[NodeQuee] temp.data : "+temp.data);
                temp = temp.link;
            }
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 배열로 이루어진 큐의 삽입 /삭제 / 출력 구현
     * */
    private void testArrayQueue(){
        ArrayQueue queue = new ArrayQueue();
        queue.insert("han");
        queue.insert("1234");
        queue.insert("lee");
        queue.insert("5678");
        Log.d(TAG, "[testArrayQueue] ### insert 후 큐");
        queue.print();

        queue.delete();
        Log.d(TAG, "[testArrayQueue] ### delete 후 큐");
        queue.print();

        queue.delete();
        Log.d(TAG, "[testArrayQueue] ### delete 후 큐");
        queue.print();

        queue.delete();
        Log.d(TAG, "[testArrayQueue] ### delete 후 큐");
        queue.print();

        queue.insert("Sho");
        Log.d(TAG, "[testArrayQueue] ### insert 후 큐");
        queue.print();
    }
    /**
     * 배열로 이루어진 큐 삽입 / 삭제
     * */
    public class ArrayQueue{
        private int front;
        private int rear;
        private int count;
        private int size;
        private int increasement;
        private Object[]itemQueue;
        private Object itemEmpty = "empty";

        public ArrayQueue(){
            front = 0;
            rear = 0;
            count = 0;
            size = 50 ;
            increasement = 10;
            itemQueue = new Object[size];
        }
        public boolean isEmpty(){
            return count == 0;
        }
        public void insert(Object x){
            // 배열이 만원일경우 increasement 만큼 증가
            if(count == size){
                int oldSize = size;
                size += increasement;
                Object[] tempArray = new Object[size];
                for(int i = 0; i<count; i++, front = (front + 1) %oldSize){
                    tempArray[i] = itemQueue[front];
                }
                // 배열 참조 변수 변경
                itemQueue = tempArray;
                front = 0;
                rear = count;
            }
            // 새로운 원소 삽입
            itemQueue[rear] = x;
            rear = (rear+1) % size;
            count++;
        }

        public Object delete(){
            // 큐가 공백일경우
            if(isEmpty()){
                return itemEmpty;
            }
            Object tempItem = itemQueue[front];
            front = (front + 1) % size;
            count --;
            return tempItem;
        }

        public Object peek(){
            if(isEmpty()){
                return itemEmpty;
            }else {
                return itemQueue[front];
            }
        }

        public void print(){
            for(int i = front; i< rear; i++){
                Log.d(TAG, "[ArrayQueue] : "+itemQueue[i]);
            }
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 2차원 배열의 행렬과 배열 처치 TEST
     * */
    public void useParseMatrix() {
        Log.d(TAG, "[useParseMatrix]");
        Random r = new Random();
        int val = r.nextInt();

        sparseMatrinx obj = new sparseMatrinx();
        obj.init(0,3,5);
        obj.init(1,1,5);
        obj.init(1,5,5);
        obj.init(2,2,5);
        obj.init(3,3,5);
        obj.init(4,0,5);
        obj.init(1,4,5);
        obj.init(4,1,5);

        Log.d(TAG, "[useParseMatrix] ################# 희소 행렬");
        int n = obj.a.length;
        obj.display(obj.a, n);

        obj.makeSparseArray();
        Log.d(TAG, "[useParseMatrix] ################# 희소 배열");
        obj.display(obj.b, obj.size);

    }

    public class sparseMatrinx{
        int[][] a = new int[10][10];
        int[][] b = new int[10][10];
        int n, m, size = 0;

        // 자료 생성
        public void init(int x, int y, int z){
            int i, j;
            a[x][y] = z;
            size ++;
        }

        public void makeSparseArray(){
            int k = 0;
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(a[i][j] != 0){
                        b[k][0] = i;
                        b[k][1] = i;
                        b[k][2] = a[i][j];
                        k++;
                    }
                }
            }
        }

        // 행렬 출력
        public void display(int a[][], int n){
            int i,j;
            for( i = 0; i < n; i++) {
                for (j = 0; j < a[i].length; j++) {
                    Log.d(TAG, "[display] : "+a[i][j]+"\t");
                }
            }
        }
    }
    ///////////////////////////////////////////////////
    /**
     * 리스트를 연결 리스트로 생성후 연결 리스트의 노드 삽입 . 삭제 TEST
     * input : {15,35,67,98}
     * */
    private void testUseInsDelNode(){
        Node2 obj = new Node2();
        Node2 L = null;

        L = obj.build();
    }

    public class Node2{
        Object data;
        Node2 link;

        public Node2 build(){
            Node2 L = null,temp, end;
            end = L;
            int[] Data = {15,35,67,98};
            int no = Data.length;

            for(int i = 0; i< no; i++){
                temp = new Node2();
                temp.data = Data[i];
                // 공백 리스트일경우
                if(L == null){
                    L = temp;
                    end = L;
                }else{
                    end.link = temp;
                    end = temp;
                }
            }
            L.print(L);
            return L;
        }

        public Node2 search(Node2 input, int x){
            Node2 temp = input;
            Node2 pos = input;

            while(temp!=null && x > (Integer)temp.data){
                pos = temp;
                temp = temp.link;
            }
            return pos;
        }

        public Node2 delSearch(Node2 input, int x){
            Node2 temp = input;
            Node2 pos = input;

            while(temp!=null && x != (Integer)temp.data){
                pos = temp;
                temp = temp.link;
            }
            return pos;
        }

        public Node2 insert(Node2 input, int x){
            Node2 pos, insNode;
            Node2 temp = null;

            insNode = new Node2();
            insNode.data = x;

            // 삽입할 원소 위치가 첫 원소 앞일경우
            if(x < (Integer) input.data){
                insNode.link = input;
                input = insNode;
            }else{
                pos = search(input, x);
                temp = pos.link;
                pos.link = insNode;
                insNode.link = temp;

            }
            return input;
        }

        public Node2 delete(Node2 input, int x){
            Node2 pos, temp ;
            boolean flag = false;

            pos = delSearch(input, x);
            if(x == (Integer)input.data){
                input = input.link;
            }else{
                if(pos.link != null){
                    temp = pos.link;
                    pos.link = temp.link;
                }else{
                    Log.e(TAG, "삭제할 원소가 없습니다.");
                    flag = true;
                }
            }
            if(flag == false){
                print (input);
            }
            return input;
        }

        public void print(Node2 input){
            while(input != null){
                Log.e(TAG, "[print]  : "+input.data);
                input = input.link;
            }
            Log.e(TAG, "[End]");
        }
    }
    ///////////////////////////////////////////////////

    /**
     * 배열을 사용하여 스택 생성 후 push/pop 연산 후 print
     * */
    private void testUseArrayStack(){
        Log.d(TAG, "[testUseArrayStack]");
        Log.d(TAG, "[testUseArrayStack] ####### push 후 스택");
        ArrayStack stack = new ArrayStack();
        stack.push(10);
        stack.push(5);
        stack.push(25);
        stack.push(40);
        stack.push(70);
        stack.print();

        Log.d(TAG, "[testUseArrayStack] ####### Top 원소 : "+stack.peek());

        Log.d(TAG, "[testUseArrayStack] ####### pop 후 스택");
        stack.pop();
        stack.print();
    }

    public class ArrayStack{
        private int top;            // top 원소 가리키는 원소
        private int size;           // 배열의 크기
        private int increasement;    // 배열의 확장 단위
        private Object[] itemStack; // 원소를 실제 저장하는 배열
        private Object itemEmpty = "empty";

        public ArrayStack(){
            top = -1;
            size = 50;
            increasement = 10;
            itemStack = new Object[size];
        }

        public boolean isEmpty(){
            return top == -1;
        }

        // 원소 삽입
        public void push(int input){
            // 스택이 만원일경우 배열크기 확장
            if(top == size -1){
                size = +increasement;
                Object[] tempArr = new Object[size];
                // 새로운 배열로 원소 이동
                for(int i= 0; i < top; i++){
                    tempArr[i] = itemStack[i];
                    itemStack = tempArr;
                }
            }
            itemStack[++top] = input;
        }

        // 원소 삭제
        public Object pop(){
            if(isEmpty()){
                Log.e(TAG, "삭제할 원소가 없습니다.");
                return itemEmpty;
            }
            return itemStack[top--];
        }

        public Object peek(){
            if(isEmpty()){
                Log.e(TAG, "삭제할 원소가 없습니다.");
                return itemEmpty;
            }
            return itemStack[top];
        }

        public void print(){
            for(int i = 0; i< top; i++ ){
                Log.d(TAG, "[print] "+itemStack[i] +" / "+itemStack[top]);
            }
        }

    }

    ///////////////////////////////////////////////////
    /**
     * 연결 리스트로 된 Stack 노드의 삽입 . 삭제 TEST
     * */
    private void testLinkStack(){
        Log.d(TAG, "[testLinkStack]");
        LinkStack stack = new LinkStack();

        Log.d(TAG, "[testLinkStack] ####### push 후 스택");
        stack.push("Kim");
        stack.push("Lee");
        stack.push("Park");
        stack.push("Yoon");
        stack.print();

        Log.d(TAG, "[testLinkStack] ####### pop 후 스택");
        stack.pop();
        stack.print();

        Log.d(TAG, "[testLinkStack] ####### Top 원소 : "+stack.peek());

        Log.d(TAG, "[testLinkStack] ####### push 후 스택");
        stack.push("Oh");
        stack.print();
    }

    /**
     * 연결 리스트로 생성한 스택의 삽입/삭제
     * */
    public class LinkStack{
        LinkStack2 top;
        String itemEmpty = "ItemEmpty";

        public void push(String x){
            LinkStack2 temp = null;

            if(top == null){
                top = new LinkStack2();
                top.data = x;
                top.link = null;
            }else{
                temp = new LinkStack2();
                temp.data = x;
                temp.link = top;
                top = temp;
            }
        }

        public void pop(){
            if(top == null){
                Log.e(TAG, "[stack - pop] pop 수행불가");
            }else{
                top = top.link;
            }
        }

        public Object peek(){
            if(top == null){
                Log.e(TAG, "[stack - pop] 공백 스택");
                return itemEmpty;
            }
            return top.data;
        }

        public void print(){
            LinkStack2 temp;
            temp = top;
            while(temp != null){
                Log.e(TAG, "[stack - print]  : "+temp.data);
                temp = temp.link;
            }
        }
    }

    public class LinkStack2{
        Object data;
        LinkStack2 link;
    }
    ///////////////////////////////////////////////////

    /**
     * n! 을 구하라
     * */
    private int test1(int input){
        int result = 0;
        int fac = 1;
        for(int i = 0; i <= input; i++){
            fac = fac*i;
        }
        return fac;
    }

    private void test(Object x){
        int count = 0;
        int size = 0;
        int incresement = 10;
        int front = 0;


        if(count == size){
            int oldSize = 0;
            size += incresement;
            Object[] temp = new Object[size];
            for(int i = 0; i< count; i++, front = (front+1) %oldSize){

            }
        }
    }
    ///////////////////////////////////////////////////

    /**
     * 입력한 노드자료로 연결 리스트를 생성
     * */
    private void testListTest(){
        Log.d(TAG, "[testListTest]");
        String[] input = {"35", "25", "100", "22"};
        ListTestNode node = new ListTestNode();
        node.build(node,input);
        node.display(node);
    }

    public class ListTestNode{
        Object data;
        ListTestNode link;

        public void build(ListTestNode p, String[] input){
            ListTestNode temp = null;
            temp = p;
            int no = input.length;

            for(int i = 0; i < no; i++ ){
                temp.data = input[i];
                ListTestNode temp1 = new ListTestNode();
                temp.link = temp1;
                temp = temp1;
            }
        }

        public void display(ListTestNode p){
            if(p != null){
                Log.e(TAG, "[display] data : " +p.data +" >>");
                display(p.link);
            }
        }
    }
    ///////////////////////////////////////////////////
}
