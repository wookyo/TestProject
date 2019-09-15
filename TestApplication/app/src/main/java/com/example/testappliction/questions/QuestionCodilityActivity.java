package com.example.testappliction.questions;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.testappliction.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  Codility 기출 액티비티
 *
 * */
public class QuestionCodilityActivity extends Activity {
    private String TAG = "QuestionCodilityActivity";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTest ();
    }

    ///////////////////////////////////////////////////

    private void mainTest(){

    }

    ///////////////////////////////////////////////////
    /**
     * Elevator 문제
     * 총 최소 거리를 구하라
     *   A[K] : elevator 안의 사람들 체중
     *   B[K] : elevator 안의 사람들 대상 층
     *   M    : 건물내 존재하는 층수
     *   X    : elevator 최대 수용 인원
     *   Y    : elevator 무게 제한
     *   (즉, A [0]과 B [0]은 대기열에있는 첫 번째 사람을 나타냅니다.)
     * */
    public int solutionElevator(int[] A, int[] B, int M, int X, int Y) {
        if(A.length != B.length) {
            return 0;
        }
        int result = getResultElevator(A,B,M,X,Y);
        return result;
    }

    /**
     * Elevator 문제
     *
     * */
    private int getResultElevator(int[] weights, int[] targetFloor, int totalFloors, int maxCap, int maxWeight) {
        int length = weights.length;
        int i = 0;
        int count = 0;
        while(i < length) {
            long groupWeight = 0;
            int cap = 0;

            LinkedHashSet floors = new LinkedHashSet();
            while(cap < maxCap
                    && i < length
                    && groupWeight + weights[i] <= maxWeight) {
                groupWeight = groupWeight + weights[i];
                floors.add(targetFloor[i]);
                i++;
                cap++;
            }
            count = count + floors.size() + 1;
        }
        return count;
    }
////////////////////////////////////////

    // elevator 안의 사람들 체중 = A[K]
    // elevator 안의 사람들 대상 층 = B [K]
    // 건물내 존재하는 층수 = M
    // elevator 최대 수용 인원 : X명
    // elevator 무게 제한 : Y
    // (즉, A [0]과 B [0]은 대기열에있는 첫 번째 사람을 나타냅니다.)
    public int getResultElevator2(int[] A, int[] B, int M, int X, int Y) {
        ArrayList<Person> list = new ArrayList<Person>();
        for(int c = 0; c < A.length; c++){
            list.add(new Person(A[c], B[c]));
        }
        return getElevatorInfo(list, X, Y,0);

//        Queue<Person> waiting = new LinkedList<>();
//        for (int i = 0; i < A.length; i++) {
//            waiting.offer(new Person(A[i], B[i]));
//        }
//        return getElevatorInfo(waiting, X, Y,0);
    }

    /**
     * Person 객체로 이루어진 Queue 로
     * 각각의 Eevator 상태와 비교
     * */
    private int getElevatorInfo( ArrayList<Person> list, int maxCount, int maxWeight, int count) {
        int sumCount = 0;    // 누적 인원수
        int sumWeight = 0;   // 누적 인원 몸무게
        int floor = 1;
        System.out.println("########### [getElevatorInfo] ######### ");
        Log.d("Test", "[getElevatorInfo - case 1] " +
                " \n  sumCount   :" + sumCount
                +"\n / sumWeight : "+sumWeight
                +"\n / count     : "+count
                +"\n / maxCount  : "+maxCount
                +"\n / maxWeight : "+maxWeight);

        int tempWeight=0;
        for(int c = 0 ; c< list.size() ; c++){
            tempWeight = tempWeight +list.get(c).weight;
        }
        Log.d("Test", "[test] tempWeight :" + tempWeight +" / list.size : "+list.size());
        if(list.size() > maxCount || tempWeight >  maxWeight){
            return count;
        }

        for(Person waitPerson : list){
            Queue<Person> elevatorQueue = new LinkedList<>();
            elevatorQueue.add(waitPerson);

            if (sumCount + 1 <= maxCount
                    || sumWeight + waitPerson.getWeight() <= maxWeight) {
                sumCount += 1;
                sumWeight += waitPerson.getWeight();
            }


            if (floor != waitPerson.getFloor()) {
                floor = waitPerson.getFloor();
                count++;
            }
            System.out.println("########### [test] sumCount :" + sumCount +" / sumWeight : "+sumWeight + count);
            Log.d("Test", "[test] sumCount :" + sumCount +" / sumWeight : "+sumWeight + " count : "+count);
        }
        return count;
    }


    /**
     * Person 객체로 이루어진 Queue 로
     * 각각의 Eevator 상태와 비교
     * */
    private int getElevatorInfo(Queue<Person> queue, int maxCount, int maxWeight, int count) {
        int sumCount = 0;    // 누적 인원수
        int sumWeight = 0;   // 누적 인원 몸무게
        int floor = 1;

        Log.d("Test", "[getElevatorInfo - case 1] " +
                " \n  sumCount   :" + sumCount
                +"\n / sumWeight : "+sumWeight
                +"\n / count     : "+count
                +"\n / maxCount  : "+maxCount
                +"\n / maxWeight : "+maxWeight);

        while (!queue.isEmpty()) {
            Queue<Person> elevatorQueue = new LinkedList<>();
            // 엘레베이터 용량 확인
            Person waitPerson = queue.poll();
            elevatorQueue.add(waitPerson);
            if (sumCount + 1 <= maxCount || sumWeight + waitPerson.getWeight() <= maxWeight) {
                sumCount += 1;
                sumWeight += waitPerson.getWeight();
            }

            while (!elevatorQueue.isEmpty()) {
                Person p = elevatorQueue.poll();
                if (floor != p.getFloor()) {
                    floor = p.getFloor();
                    count++;
                }

            }
            // 1층으로 돌아가기
            if (elevatorQueue.isEmpty()) {
                count++;
                return getElevatorInfo(queue, maxCount, maxWeight, count);
            }
        }
        return count;
    }

//    /**
//     * Person 객체로 이루어진 Queue 로
//     * 각각의 Eevator 상태와 비교
//     * */
//    private int getElevatorInfo(Queue<Person> queue, int maxCount, int maxWeight, int count) {
//        int sumCount = 0;    // 누적 인원수
//        int sumWeight = 0;   // 누적 인원 몸무게
//        int floor = 1;
//
//        while (!queue.isEmpty()) {
//            Queue<Person> elevatorQueue = new LinkedList<>();
//            // 엘레베이터 용량 확인
//            Person waitPerson = queue.poll();
//            elevatorQueue.add(waitPerson);
//            if (sumCount + 1 <= maxCount || sumWeight + waitPerson.getWeight() <= maxWeight) {
//                sumCount += 1;
//                sumWeight += waitPerson.getWeight();
//            }
//
//            while (!elevatorQueue.isEmpty()) {
//                Person p = elevatorQueue.poll();
//                if (floor != p.getFloor()) {
//                    floor = p.getFloor();
//                    count++;
//                }
//            }
//            // 1층으로 돌아가기
//            if (elevatorQueue.isEmpty()) {
//                count++;
//                return getElevatorInfo(queue, maxCount, maxWeight, count);
//            }
//        }
//        return count;
//    }

    /**
     * 엘레 베이터 안의 사람당 객체 클래스
     * */
    public class Person {
        private int weight;
        private int floor;

        public Person(int weight, int floor) {
            this.weight = weight;
            this.floor = floor;
        }

        public int getWeight() {
            return weight;
        }

        public int getFloor() {
            return floor;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "weight=" + weight +
                    ", floor=" + floor +
                    '}';
        }
    }

///////////////////////////////////////////////////////////////////
//    int solution(int[] A, int X) {
//        int N = A.length;
//        if (N == 0) {
//            return 0;
//        }
//        int l = 0;
//        int r = N - 1;
//        while (l < r) {
//            int m = (l + r) / 2;
//            if (A[m] > X) {
//                r = m - 1;
//            } else {
//                l = m;
//            }
//        }
//        if (A[l] == X) {
//            return l;
//        }
//        return -1;
//    }

    /////////////////////////////////////////////////
//    // elevator 안의 사람들 체중 = A[K]
//    // elevator 안의 사람들 대상 층 = B [K]
//    // 건물내 존재하는 층수 = M
//    // elevator 최대 수용 인원 : X명
//    // elevator 무게 제한 : Y
//    // (즉, A [0]과 B [0]은 대기열에있는 첫 번째 사람을 나타냅니다.)
//    public int solution(int[] A, int[] B, int M, int X, int Y) {
//        Queue<Person> waiting = new LinkedList<>();
//        for (int i = 0; i < A.length; i++) {
//            waiting.offer(new Person(A[i], B[i]));
//        }
//        return getElevatorInfo(waiting, X, Y, 0);
//    }
//
//
//    /**
//     * Person 객체로 이루어진 Queue 로
//     * 각각의 Eevator 상태와 비교
//     * */
//    private int getElevatorInfo(Queue<Person> queue, int elevatorCount, int elevatorWeight, int count) {
//        int xSum = 0;
//        int ySum = 0;
//        int floor = 1;
//
//        while (!queue.isEmpty()) {
//            Queue<Person> elevatorQueue = new LinkedList<>();
//            // 엘레베이터 용량 확인
//            Person waitPerson = queue.poll();
//            elevatorQueue.add(waitPerson);
//            if (xSum + 1 <= elevatorCount || ySum + waitPerson.getWeight() <= elevatorWeight) {
//                xSum += 1;
//                ySum += waitPerson.getWeight();
//            }
//
//            while (!elevatorQueue.isEmpty()) {
//                Person p = elevatorQueue.poll();
//                if (floor != p.getFloor()) {
//                    floor = p.getFloor();
//                    count++;
//                }
//            }
//            // 1층으로 돌아가기
//            if (elevatorQueue.isEmpty()) {
//                count++;
//                return getElevatorInfo(queue, elevatorCount, elevatorWeight, count);
//            }
//        }
//        return count;
//    }
//
//    public class Person {
//        private int weight;
//        private int floor;
//
//        public Person(int weight, int floor) {
//            this.weight = weight;
//            this.floor = floor;
//        }
//
//        public int getWeight() {
//            return weight;
//        }
//
//        public int getFloor() {
//            return floor;
//        }
//
//        @Override
//        public String toString() {
//            return "Person{" +
//                    "weight=" + weight +
//                    ", floor=" + floor +
//                    '}';
//        }
//    }
//////////////////////////////////////////////////////////////
    int solutionCheckValue(String s) {
        if(s == null || TextUtils.isEmpty(s) || s.length() ==0){
            return 0;
        }
        // 2진법의 String 을 10 진수로 변환
        int temp = Integer.parseInt(s, 2);
        return  getCheckValue(temp, 0);
    }

    /**
     * 입력값이 0 이 될떄까지 반복
     * 입력값이 홀수일경우 : 입력값 /2 리턴
     * 입력값이 짝수일경우 : 입력값 -1 리턴
     * */
    private int getCheckValue(int input, int count) {
        if (input == 0) {
            return count;
        }
        count++;

        if (input % 2 == 0) {
            return getCheckValue(input / 2, count);
        } else {
            return getCheckValue(input - 1, count);
        }
    }

    ///////////////////////////////////
    /**
     * int[][] location :
     * 입력값 〉	[[0, 3],
     * [1, 1],
     * [1, 5],
     * [2, 2],
     * [3, 3],
     * [4, 0]],
     * [3, 4],
     * [0, 0]
     * 기댓값 〉	4
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	[1, 4]	[4, 1]	3
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	[3, 4]	[0, 0]	4
     * */
    public int testSolutionA(int[][] location, int[] s, int[] e) {
        Log.d(TAG, "[solutionA] : "+location.length);
        int answer = -1;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");
//        Arrays.sort(location, new Comparator<int[]>() {
//            @Override
//
//            public int compare(int[] arr1, int[] arr2) {
//                if( ((Comparable)arr1[1]).compareTo(arr2[1]) < 0 )
//                    return 1;
//                else
//                    return -1;
//            }
//        });

        boolean[][] arrayInput = new boolean[2][location.length]; // input X 축 배열
        int[] arrayX = new int[s.length]; // input X 축 배열
        int[] arrayY = new int[s.length]; // input Y 축 배열

        for(int c = 0; c < arrayX.length;c++){
            arrayX[c] =  s[c] ;
            arrayX[c] =  e[c] ;
        }

        for (int i = 0; i < location.length; i++) {
            for (int j = 0; j < location[i].length; j++) {
                Log.d(TAG, "[solutionA]" +location[i][j] + " i : " + i +" / j :"+j);
            }

            System.out.println();
        }

        return answer;
    }

    ///////////////////////////////////
    /*
     * 홀수 문자가 한개 이상 존재 하는지 체크
     * */
    private boolean  testCheckMaxOneOdd(int[] table){
        Log.d(TAG, "[checkMaxOneOdd]");
        boolean result = false;

        return true;
    }

    private String permutationSort(String s){
        char[] contetnt = s.toCharArray();
        Arrays.sort(contetnt);
        return new String(contetnt);
    }

///////////////////////////////////
    /**
     * a,b,c,d, 가 1에서 1000 사이에 있는 정수값 중  중하나일때
     * a(3제곱) + b(3제곱) = c(3제곱) +d(3제곱) 을 만족 시키는 모든 자연수를 출력하라
     * */
    private void test1(){
        Log.d(TAG,"[test1] : "+Math.pow(2, 2));
        int n = 1000;
//        // 무식한 방법
//        for (int a= 0; a <= n; a++){
//            for (int b= 0; b <= n; b++){
//                for (int c= 0; c <= n; c++){
//                    for (int d= 0; d <= n; d++){
//                        Log.d(TAG,"[mainTest] "
//                                + "/ a: "+a
//                                + "/ b :"+b
//                                + "/ c: "+c
//                                + "/ d: "+d);
//                        if(Math.pow(a, 3) + Math.pow(b, 3)  == Math.pow(c, 3) + Math.pow(d, 3)){
//                            Log.e(TAG,"[mainTest - Result] "
//                                    + "/ a: "+a
//                                    + "/ b :"+b
//                                    + "/ c: "+c
//                                    + "/ d: "+d);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
        // 해결법 1
        HashMap<Integer, Test1> tempMap = new HashMap<>();

        for (int c = 1; c <= n; c++) {
            for (int d = 1; d <= n; d++) {
                int temp1 = (int) Math.pow(c, 3) + (int) Math.pow(d, 3);
                tempMap.put(temp1, new Test1(c,d));
            }
        }

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int temp2 = (int) Math.pow(a, 3) + (int) Math.pow(b, 3);
                Test1 temp = tempMap.get(temp2);
                Log.d(TAG,"[test1] temp : "+temp);
                if (temp != null) {
                    Log.e(TAG, "[mainTest - Result] "
                            + "/ a: " + a
                            + "/ b :" + b
                            + "/ c: " + temp.getC()
                            + "/ d: " + temp.getD());
                    break;
                }
            }
        }
    }


    /////////////////////////////////////////
    public class Test1{
        private int c;
        private int d;

        public Test1(int c, int d){
            this.c = c;
            this.d = d;
        }

        public int getC(){
            return c;
        }
        public int getD(){
            return d;
        }
    }

}
