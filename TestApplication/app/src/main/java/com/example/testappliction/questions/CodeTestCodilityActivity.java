package com.example.testappliction.questions;

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

import com.example.testappliction.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static java.util.Arrays.sort;


/**
 * Codility Test 를 위한 액티비티
 *  코딩 TEST 위한 액티비티
 * */
public class CodeTestCodilityActivity extends Activity{
    private String TAG = "CodeTestCodilityActivity";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTest ();
    }

    ///////////////////////////////////////////////////

    private void mainTest(){

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
    /**
     * 반복되는 문자의 갯수를 세는 방식의 기본적인 문자열 압축 메소드를 작성하라
     * 문자열은 대소문자 알파벳으로만 이루어져 있다.
     * 만약 '압축된' 문자열이 기존 문자열보다 길다면 기존 문자열 리턴
     * Ex >
     *  "aabccccaaa" >> a2b1c5a3
     *
     * */
    private String testCompressBad(String input) {
        Log.d(TAG, "[compressBad]");
        String result = "";

//        int index = 0;
//        int indexItem = 1;
//        StringBuilder sb = new StringBuilder();
//        while (index < input.length()) {
//            if (index == input.length() - 1) {
//                break;
//            }
//            if (input.charAt(index) == input.charAt(index + 1)) {
//                indexItem++;
//            }else{
//                indexItem = 1;
//            }
//            String item = String.valueOf(input.charAt(index));
//            sb.append(getStringCount(item, indexItem));
//
//            index++;
//        }
//        String temp = sb.toString();
//        Log.d(TAG, "[test] temp : " + temp);
//        result = (temp.length() > input.length()) ? input : temp;

        // 문제 풀이
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for(int i = 0; i < input.length(); i++){
            countConsecutive ++;
            if(i + 1 >= input.length() || input.charAt(i) != input.charAt(i + 1)){
                compressed.append(input.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        result = (compressed.toString().length()> input.length())? input : compressed.toString();
        return result;
    }

    private String getStringCount(String input, int count){
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.append(count);
        return sb.toString();
    }

////////////////////////////////////
    /**
     * 문자열을 편집하는 방법에는 세가지 종류가 있다.
     * 문자 삽입/문자교체/문자열 2개가 주어 졌을경우
     * 문자열을 같게 만들기 위해 편집한 횟수가 1회 이내 인지 확인하는 메소드를 작성하라
     * ex>
     *  pale, ple >> true;
     *  pales, pale >> true
     *  pale, bale >> true;
     *  pale, bake >> false;
     * */
    private boolean testOneEdittorWay(String input1, String input2){
        Log.d(TAG, "[oneEdittorWay]");
        // 문제 풀이
        if(input1.length() == input2.length()){
            checkOneEdittorWayReplace(input1, input2);

        }else if(input1.length()+ 1 == input2.length()){
            checkOneEdittorWaySize(input1, input2);

        }else if(input1.length()- 1 == input2.length()){
            checkOneEdittorWaySize(input2, input1);
        }
        return false;
    }

    private boolean checkOneEdittorWayReplace(String inputA, String inputB){
        boolean isDifferent = false;
        for(int i = 0 ; i < inputA.length(); i++ ){
            if(inputA.charAt(i) != inputB.charAt(i)){
                if(isDifferent){
                    return false;
                }
                isDifferent = true;
            }
        }
        return true;
    }

    private boolean checkOneEdittorWaySize(String inputA, String inputB){
        int indexA = 0;
        int indexB = 0;

        while(indexB < inputB.length() && indexA < inputA.length()){
            if(inputA.charAt(indexA) != inputB.charAt(indexB)){
                if(indexA != indexB){
                    return false;
                }
                indexB ++;
            }else{
                indexA ++;
                indexB ++;
            }
        }
        return true;
    }

///////////////////////////////////
    /**
     * 주어진 문자열이 회문의 순열인지 아닌지 확인하라
     * ex>  String input = "Tact Coa";
     * True : 'taco cat', 'atco cta'
     * */
    private void testInputPermutationOfPalindrome(String input){
        Log.d(TAG, "[inputPermutationOfPalindrome]");
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

///////////////////////////////////

    /**
     * 문자열에 들어 있는 모든 공백을 '%20' 으로 변환하는 메소드 작성
     * 문자열의 최종길이는 주어진다
     * // JAVA 로 구현시 왠만하면 문자 배열로 사용
     * */
    private void testReplaceSpace() {
        Log.d(TAG, "[replaceSpace]");
        String input = "abcd ef g h i k";
        int trueLength = 30;

//        StringBuilder sb = new StringBuilder();
//        char[] arrayInput = input.toCharArray();
//        for(char c : arrayInput){
//            if(c == ' '){
//                sb.append("%20");
//            }else{
//                sb.append(c);
//            }
//        }
//        String result = sb.toString();
//        Log.d(TAG, "[replaceSpace] result : "+result);

        // 문제 풀이
        char[] str = input.toCharArray();
        int spaceCount = 0, index = 0, i = 0;

        // 문자열내에 공백의 숫자 파악
        for(i = 0; i < trueLength; i++){
            if(str[i] == ' '){
                spaceCount++;
            }
        }
        index = trueLength + spaceCount*2;

        if(trueLength < str.length){
            str[trueLength] = '\0'; // 배열의 끝
        }
        // 문자의 역방향으로 훑으면서 실제 문자열 편집
        // 공백을 만나면 다음위치에 '%20' 복사 , 아니면 원래문자 복사
        for(i = trueLength - 1; i >=0; i--){
            if(str[i] == ' '){
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            }else{
                str[index - 1] = str[i];
                index--;
            }
        }
    }

///////////////////////////////////
    /**
     * 문자열  2개가 주어 졌을경우
     * 이 둘이 서로 순열 관계에 있는지 확인하는 메서드 작성
     * */
    private boolean testPermutation() {
        Log.d(TAG, "[permutation]");
        String inputA = "abcdef";
        String inputB = "fedcba";

        // 문제풀이 1
        // 만일 두 문자열이 서로 순열관계에 있다면
        //이둘은 같은 문자로 구성되어 있고 순서만 다를것이다.
        // 따라서 문자열을 따라서 정렬시 둘다 같은 결과가 나와야 한다.
//        if (inputA.length() != inputB.length()) {
//            return false;
//        }
//        return TextUtils.equals(permutationSort(inputA), permutationSort(inputB));

        // 문제풀이2
        // 순열의 정의 , 즉 두 문자열이 동일한 문자갯수를 가지고 있다는점을 이용
        // 두개의 배열을 사용 각 문자열내의 문자 출현 횟수를 기록후 두배열 비교
        if (inputA.length() != inputB.length()) {
            return false;
        }
        // 가정
        int[] letters = new int[128];
        char[] arrayA = inputA.toCharArray();
        for(char c : arrayA){
            letters[c]++;
        }
        for(int i = 0; i< inputB.length(); i++){
            int count = (int) inputB.charAt(i);
            letters[count] --;
            if(letters[count] < 0){
                return false;
            }
        }
        return true;
    }

    private String permutationSort(String s){
        char[] contetnt = s.toCharArray();
        Arrays.sort(contetnt);
        return new String(contetnt);
    }

    ///////////////////////////////////
    /**
     * 문자열이 주어 졌을경우
     * 이 문자열에 같은 문자가 중복되어 잇는지 확인하는 알고리즘을짜라
     * 자료구조를 추가로 사용하지 않고 풀수 있는 알고리즘 또한 고민하라라
     * */
    private boolean testIsUniqueChars() {
        Log.d(TAG, "[isUniqueChars]");
        String intput = "skfjgorugjabdfg";

        HashMap<Character, Character> tempMap = new HashMap<>();
        char[] tempIntput = intput.toCharArray();
        for (int c = 0; c < tempIntput.length; c++) {
            char temp = tempIntput[c];
            if (tempMap.get(temp) == null) {
                tempMap.put(temp, temp);
            } else {
                Log.e(TAG, "[test2] : " + temp);
                return false;
            }
        }

        // 문제 풀이 1
        // i번째 문자가 배열내에 존재하는지 표시하는 boolean 배열을 사용
        // 같은 원소 2번 접속시 false 리턴
        // 문자열의 길이가 문자 집합보다 클경우 false 리턴
        if (intput.length() > 128) {
            return false;
        }
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < intput.length(); i++) {
            int val = intput.charAt(i);
            // 이 문자는 이미 문자열에 있음
            if (char_set[val]) {
                Log.e(TAG, "[test2] val : " + val);
                return false;
            }
            char_set[val] = true;
        }

        // 문제 풀이 2
        // 문자열이 소문자 a 부터 Z 까지 구성한다고 가정
        int cracker = 0;
        for (int i = 0; i < intput.length(); i++) {
            int val = intput.charAt(i) - 'a';
            // 이 문자는 이미 문자열에 있음
            if ((cracker & (1 << val)) > 0) {
                Log.e(TAG, "[test2] val : " + val);
                return false;
            }
            cracker |= (1 << val);
        }
        return true;
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

    ////////////////////////////////////////
    /**
     * XX 시에는 네트워크 망이 있습니다.
     * 어느 날 악천후로 인하여 몇몇 PC 사이의 네트워크 연결이 끊어지게 되었습니다.
     * 끊어진 네트워크 연결을 복구하여 모든 PC 간 통신이 가능하게 하려 합니다.
     * 이때 필요한 최소한의 비용을 구해주세요.
     *
     * a번 PC부터 b번 PC까지 도달하는 경로가 존재할 때, a번 PC와 b번 PC가 통신이 가능하다고 합니다.
     * 전체 PC 수 n,
     * 현재 네트워크 망 배열 network,
     * 복구하려는 네트워크 배열 repair 가 주어졌을 때,
     * 복구하는데 드는 최소 비용을 return 하도록 solution 함수를 작성해주세요.
     *
     * <제한사항>
     * n은 3 이상 100,000 이하인 정수입니다.
     * network와 repair의 길이는 1 이상 300,000 이하입니다.
     * network의 원소는 [a, b] 형태이며, 이는 a번 PC와 b번 PC가 연결되어 있다는 뜻입니다.
     * a와 b는 1 이상 n이하인 정수입니다.
     * repair의 원소는 [a, b, cost] 형태이며, 이는 a번 PC와 b번 PC의 네트워크 연결을 복구하는데 cost만큼 비용이 든다는 뜻입니다.
     * a와 b는 1 이상 n이하인 정수입니다.
     * cost는 1 이상 100,000 이하인 정수입니다.
     * 모든 PC 간 통신이 불가능한 경우 -1을 return 해주세요.
     *
     * Ex >
     * n	network								repair						return
     * 6	[[1, 2], [3, 5], [4, 2], [5, 6]]	[[3, 2, 10], [5, 4, 15]]	10
     * 4	[[1, 2]]							[[2, 3, 10], [3, 1, 12]]	-1
     *
     * 입출력 예 설명
     * 예제 #1
     * 2번과 3번 PC를 연결하는 네트워크를 복구하는 경우 모든 PC 간 통신이 가능해지며 최소 비용이 듭니다.
     *
     * 예제 #2
     * 어떤 네트워크를 복구해도 4번 PC와 통신이 불가능합니다.
     *
     * */
    private void testNetwork(int n, int[][] network, int[][] repair) {

    }


    ////////////////////////////////////////
    /**
     * r x c 크기의 지도가 주어졌습니다.
     * 지도에는 땅과 바다의 정보가 표시되어 있습니다.
     * 1 x 1 크기의 각 칸에는 1 또는 0으로 표시되어 있고,
     * 1은 땅에 해당하는 공간, 0은 바다에 해당하는 공간입니다.
     * 이때, 땅이 상하좌우로 인접해 있을 때 인접한 땅들을 하나의 섬이라고 합니다.
     *
     * 예를 들어, 4 x 5 크기의 지도가 주어졌을 때,
     * 섬이 2개가 있다고 판단합니다.
     *
     * 이와 같은 지도가 주어졌을 때, 우리는 각 섬의 둘레를 모두 구하여 합하려고 합니다.
     * 각 칸의 모서리의 길이가 1을 나타냅니다.
     * 즉, 위의 지도에서 섬 A의 둘레는 16이 되고, 섬 B의 둘레는 8이 되어, 모두 합하면 24가 됩니다.
     *
     * 지도가 매개변수 maps로 주어졌을 때,
     * 섬들의 둘레를 모두 합하여 return 하는 solution 함수를 완성해 주세요.
     * 위의 예시에서는 24를 return 하면 됩니다.
     *
     * 제한사항>
     * 지도 maps는 2차원 배열로 주어지며, 0과 1 이외에 다른 숫자는 주어지지 않습니다.
     * r, c : 100 이하의 자연수
     *
     * Ex>
     * maps												    answer
     * [[0,0,1,0,0],[0,1,1,0,1],[0,0,1,0,1],[1,1,1,0,1]]	24
     * [[1,0,1,1],[0,0,1,1],[1,1,0,1],[1,1,0,0]]			22
     *
     * 입출력 예 #2
     * 지도에 주어진 섬의 개수는 3개이고, 섬 A의 둘레는 4가 되고,
     * 섬 B의 둘레는 10이 되고, 섬 C의 둘레는 8이 되어 모두 합하면 22가 됩니다.
     * */
    private void testMap(int[][] maps){
        int result = 0;
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j< maps[i].length; j++ ){
                if(maps[i][j] == 1){
                    result = result + getMapSize(maps,i, j );
                }

            }
        }
        Log.d(TAG, "[testMap]" + result);
    }

    /**
     * 각 아이템의 둘레 길이 리턴
     * */
    private int getMapSize(int[][] maps, int i, int j){

        int result = 0;

        if(i == 0 || i == maps.length-1){
            Log.d(TAG, "[getMapSize] - 1");
            result ++;
        }
        if(j == 0 || j == maps[i].length-1){
            Log.d(TAG, "[getMapSize] - 2");
            result ++;
        }
        if(i < maps.length-1 && maps[i+1][j] == 0){
            Log.d(TAG, "[getMapSize] - 3");
            result ++;
        }
        if(i >0 && maps[i-1][j] == 0){
            Log.d(TAG, "[getMapSize] - 3");
            result ++;
        }

        if(j < maps[i].length-1 && maps[i][j+1] == 0 ){
            Log.d(TAG, "[getMapSize] - 4");
            result ++;
        }

        if(j > 0 && maps[i][j-1] == 0 ){
            Log.d(TAG, "[getMapSize] - 4");
            result ++;
        }

        Log.d(TAG, "[getMapSize]" + i +" / "+j + "/ result : "+result);
        return result;
    }
    ////////////////////////////////////////
    /**
     * 임의의 자연수 n으로 나누었을 때,
     * 그 몫과 나머지가 같아지는 자연수들을 모두 더한 값을 반환하도록 solution 함수를 완성하세요.
     *
     * 제한사항>
     * n은 100,000 이하의 자연수입니다.
     *
     * Ex >
     * n	result
     * 2	3
     * 3	12
     *
     * 입출력 예 #1
     * n이 2일 때,
     * 3을 2로 나누면 몫이 1이고, 나머지가 1
     * 나머지가 2 이상일 수 없으므로 3을 반환합니다.
     *
     * 입출력 예 #2
     * n이 3일 때,
     *
     * 4를 3으로 나누면 몫이 1이고, 나머지가 1
     * 8을 3으로 나누면 몫이 2이고, 나머지가 2
     * 나머지가 3 이상일 수 없으므로 4와 8을 더한 12를 반환합니다.
     * */
    private void testDevide(long n){
        Log.d(TAG, "[testDevide]");
        long result = 0;
        int max = 10;

        int cnt = 0;
        while (cnt > (cnt /n)) {
            int temp = (int)(cnt % n);
            int temp2 = (int)(cnt / n);

            Log.d(TAG, "[testDevide] while : "+cnt
                    + " / temp  : "+temp
                    +"  / temp2 : "+temp2);

            if(temp2 == temp && temp < n){
                result = result + cnt;
            }
            cnt ++;
        }


//        for(int count = 0; count<max; count++){
//            int temp = (int)(count % n);
//            int temp2 = (int)(count / n);
//
//            Log.d(TAG, "[testDevide] count : "+count
//                    + " / temp  : "+temp
//                    +"  / temp2 : "+temp2);
//
//            if(n <= temp2){
//                break;
//            }
//            if(temp != 0 && temp2 == temp && temp < n){
//                result = result + count;
//            }
//        }
        Log.d(TAG, "[testDevide] : "+result);
    }

    private void testDevide2(long n){
        Log.d(TAG, "[testDevide]");
        long result = 0;
        int max = Integer.MAX_VALUE;
        for(int count = 0; count<max; count++){
            int temp = (int)(count % n);
            int temp2 = (int)(count / n);

            Log.d(TAG, "[testDevide] count : "+count
                    + " / temp  : "+temp
                    +"  / temp2 : "+temp2);

            if(n <= temp2){
                break;
            }
            if(temp != 0 && temp2 == temp && temp < n){
                result = result + count;
            }
        }
        Log.d(TAG, "[testDevide] : "+result);
    }


////////////////////////////////////////
    /**
     * ○○피자집은 손님이 피자를 한 번 살 때마다 쿠폰 1장을 줍니다.
     * 이 쿠폰을 3장 모으면 다음 주문 때 스파게티를 무료로 받습니다.
     * (쿠폰을 2개 갖고 있을 때 피자를 주문하면 새 쿠폰을 받아 쿠폰이 3개이지만, 이 상황에서는 쿠폰을 쓸 수 없습니다)
     * 무료로 스파게티를 받았을 때는 피자를 사도 쿠폰을 주지 않으며, 손님은 쿠폰을 4개 이상 모을 수 없습니다.
     *
     * 예를 들어, [1,3,3,2,4,3,3,2,4,2,2,4] 순으로 주문했다고 가정해봅시다.
     * 괄호 안의 숫자는 손님 고유의 ID를 나타내며, 왼쪽부터 순서대로 주문한 것을 나타냅니다.
     * 이를 표로 나타내어 첫번째 행을 손님 고유의 ID,
     * 두번째 행을 해당 손님의 쿠폰 개수라 하면 다음과 같습니다.
     *
     *
     * 이 경우엔 3번 손님, 2번 손님은 스파게티를 무료로 받습니다.
     * 4번 손님은 쿠폰 3장을 모았지만, 이 쿠폰은 다음번 피자를 주문할 때 쓸 수 있습니다.
     *
     * 피자를 산 손님들의 ID가 매개변수 people로 주어졌을 때,
     * 스파게티를 무료로 받은 손님들의 ID를 return 하는 solution 함수를 완성해 주세요.
     * 예를 들어, 위의 예시에서는 3번 손님, 2번 손님 순으로 스파게티를 무료로 받았기 때문에 답은 [3,2]입니다.
     * 단, 어떠한 손님도 스파게티를 무료로 받지 못한 경우에는 1차원 배열에 -1을 넣어서 return 해주세요.
     *
     * 제한사항>
     * people의 길이 : 100,000 이하의 자연수
     * 손님 ID : 1,000,000,000 이하의 자연수
     *
     * ex >
     * people						answer
     * [1,3,3,2,4,3,3,2,4,2,2,4]	[3,2]
     * [1,1,3,3,3,3,1,3,3,3,3,2]	[3,3]
     * [1,2,3,4]	[-1]
     *
     * 입출력 예 #2
     * 3번 손님이 두 번 연속으로 스파게티를 받았습니다.
     *
     * 입출력 예 #3
     * 어떠한 손님도 스파게티를 무료로 받지 못한 경우입니다.
     * */
    private void testPizza(int[] people){
        Log.d(TAG, "[testPizza]");
        Map<Integer, Integer> personMap = new HashMap<>();
        ArrayList<Integer> tempArray= new ArrayList<Integer>();
        int[] result = null;

        for(int person : people){
            if(personMap.containsKey(person)){
                int count = personMap.get(person) +1;
                // 쿠폰 받을시
                if(count == 3){
                    tempArray.add(person);
                    count = 0;
                }
                personMap.put(person,count);
            }else{
                personMap.put(person,0);
            }
        }


        //for(int count = 0; count<result){}
//        int[]result = tempArray.toArray(new Integer[]);

        if(tempArray.size() == 0){
            result = new int[1];
            result[0] = -1;
            Log.d(TAG, "[testPizza] item : "+result[0]);
        }else{
            result = new int[tempArray.size()];
            int count = 0;
            for(int item : tempArray){
                Log.d(TAG, "[testPizza] item : "+item);
                result [count] =  item;
                count++;
            }
        }


    }
////////////////////////////////////////
    /**
     * 주어진 행렬중에서 2칸마다 얼마나 성을 지을수 있는지 테스트
     * */
    private void testCastle(int[] A) {
        int result = 0;
        for (int c = 1; c < A.length; c++) {
            if (A[c] - A[c - 1] == 0) {
                result++;
            }
        }
        Log.d(TAG, "[testCastle] : "+result);
    }

////////////////////////////////////////
    /**
     * 주어진 행렬중 얼마나 많은 도시를 다녀오나 테스트
     * */
    Map<Integer, Boolean> mLocationsMap = null;
    private void testVacation2(int[] A) {
        int result = Integer.MAX_VALUE;
        mLocationsMap = new HashMap<>();

        for (int item : A) {
            if (!mLocationsMap.containsKey(item)) {
                mLocationsMap.put(item, false);
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                if (!isVisited(A[j])) {
                    mLocationsMap.put(A[j], true);
                    if(isAllVisited()){
                        if (j - i + 1 < result) {
                            result = j - i + 1;
                        }
                        initLocationMap();
                        break;
                    }
                }
            }
        }
        Log.d(TAG, "[testVacation2] result : "+result);
    }


    /**
     * 해당 로케이션에 방문여부 체크
     * */
    private boolean isVisited(int location) {
        if (mLocationsMap.containsKey(location)) {
            return mLocationsMap.get(location);
        }
        return false;
    }

    /**
     * 모든 지역에 다녀왔는지 체크
     * */
    private boolean isAllVisited() {
        for (Map.Entry<Integer, Boolean> item : mLocationsMap.entrySet()) {
            if (!item.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 지역 map 초기화
     * */
    private void initLocationMap() {
        for (Map.Entry<Integer, Boolean> curr : mLocationsMap.entrySet()) {
            curr.setValue(false);
        }
    }

    ////////////////////////////////////////
    /**
     * 주어진 행렬중 얼마나 많은 도시를 다녀오나 테스트
     * */
    private void testVacation(int[] A){
        Solution obj = new Solution();
        Log.d(TAG, "[testVacation] Result : "+obj.solution(A));
    }

    public class Solution {
        private Map<Integer, Boolean> allDiffLocations;

        public Solution() {
            allDiffLocations = new HashMap<>();
        }

        public int solution(int[] A) {
            excludeDifferentLocations(A);
            int shortestRoad = Integer.MAX_VALUE;
            for (int i = 0; i < A.length; i++) {
                for (int j = i; j < A.length; j++) {
                    if (!isLocationVisited(A[j])) {
                        markLocationAsVisited(A[j]);
                        if (isAllLocationsVisited()) {
                            if (j - i + 1 < shortestRoad) {
                                shortestRoad = j - i + 1;
                            }
                            rollbackAllVisitingMarks();
                            break;
                        }
                    }
                }
            }
            return shortestRoad;
        }



        private void excludeDifferentLocations(int[] locations) {
            for (int curr : locations) {
                if (!allDiffLocations.containsKey(curr)) {
                    allDiffLocations.put(curr, false);
                }
            }
        }

        private void markLocationAsVisited(int location) {
            allDiffLocations.put(location, true);
        }

        private boolean isLocationVisited(int location) {
            if (allDiffLocations.containsKey(location)) {
                return allDiffLocations.get(location);
            }
            return false;
        }

        private boolean isAllLocationsVisited() {
            for (Map.Entry<Integer, Boolean> curr : allDiffLocations.entrySet()) {
                if (!curr.getValue()) {
                    return false;
                }
            }
            return true;
        }

        private void rollbackAllVisitingMarks() {
            for (Map.Entry<Integer, Boolean> curr : allDiffLocations.entrySet()) {
                curr.setValue(false);
            }
        }
    }

    ////////////////////////////////////////
    private int testDistance(int[] A) {
        if (A.length == 1) {
            return -2;
        }
        Arrays.sort(A);

        long minDistance = Long.MAX_VALUE;
        for (int i = 1; i < A.length; i++) {
            long distance = (long) A[i] - A[i - 1];
            if(distance < minDistance){
                minDistance = distance;
            }
        }
        int result = minDistance > 10000 ? -1 : (int)minDistance;
        Log.d(TAG, "[testDistance] result : "+result);
        return result;
    }
///////////////////////////////////////////
    /**
     * 0/1 로 이루어진 코인중 최소한으로 쥐집어야 하는 테스크
     * */
    private void testCoin(int[] A){
        int coninMaxValue = 0;
        for (int i = 0; i < A.length; i++) {
            int[] copy = Arrays.copyOf(A, A.length);
            copy[i] = (copy[i] + 1) % 2;
            coninMaxValue = Math.max(coninMaxValue, coinPair(copy));
        }
        Log.d(TAG, "[testCoin] : "+(A.length - coninMaxValue));

    }

    private int coinPair(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                result = result + 1;
            }
        }
        return result;
    }
/////////////////////////////////////////////////////
    /**
     * 주어진 START/ END TIME ITEM 중에
     * 중복된 시간이 없는지 체크
     * */
    public void testDuplicateTime(){
        Log.d(TAG, "[testDuplicateTime]");
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

            MovieNight niight = new MovieNight();
            ArrayList<Movie> movies = new ArrayList<Movie>();
            movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
            movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
            movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

//            movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 22:30")));
//            movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
//            movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

            Log.d(TAG, "[testDuplicateTime] : "+niight.canViewAll(movies));
        }catch (Exception e){
            Log.d(TAG, "[testDuplicateTime] Exception");
        }
    }

    public class MovieNight {
        public  Boolean canViewAll(Collection<Movie> movies) {
            boolean isOverlap = false;
            List<Movie> movie_list = (List<Movie>) movies;

            for (int i = 0; i < movie_list.size(); i++) {
                for (int j = i + 1; j < movie_list.size(); j++) {
                    Date startA = movie_list.get(i).getStart();
                    Date endA   = movie_list.get(i).getEnd();
                    Date startB = movie_list.get(j).getStart();
                    Date endB   = movie_list.get(j).getEnd();

                    // A StartTime이 B EndTime 보다 전일경우
                    boolean isStartABeforeEndB = (startA.compareTo(endB)) < 0;
                    // A EndTime이 B StartTime이 보다 후일경우
                    boolean isEndAAfterStartB = (endA.compareTo(startB)) > 0;

                    boolean isCurrentPairOverlap = false;
                    isCurrentPairOverlap = isStartABeforeEndB && isEndAAfterStartB;

                    Log.d(TAG, "[MovieNight] isStartABeforeEndB : "+isStartABeforeEndB
                            + "/ isEndAAfterStartB : "+isEndAAfterStartB
                            +" / isCurrentPairOverlap : "+isCurrentPairOverlap);
                    if (isCurrentPairOverlap) {
                        isOverlap = true;
                    }
                }
            }
            return !isOverlap;
        }
    }

    class Movie {
        private Date start, end;

        public Movie(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        public Date getStart() {
            return this.start;
        }

        public Date getEnd() {
            return this.end;
        }
    }

/////////////////////////////////////////////////////
    /**
     * 입력 값의 3자리수씩 짤라 아래와 같이 더한수
     * 총 숫자값을 리턴하는 메소드
     * r: 4
     * w: 2
     * x : 1
     * - : 0
     *
     * ex > "rwxr-x-w-" - > 752 값이 리턴
     * rwx : 7
     * r-x : 5
     * -w- : 2
     * */
    private int testAddValue(String input){
        Log.d(TAG, "[testAddValue]");
        int result = 0;
        int count = 0;
        char[] arrayInput = input.toCharArray();
        ArrayList <Integer>retultArray = new ArrayList<Integer>();

        for(int i = 0; i< arrayInput.length; i++){
            count = count + getValue(arrayInput[i]);
            if(i%3 == 2){
                retultArray.add(count);
                count = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int item : retultArray){
            sb.append(item);
        }
        result = Integer.parseInt(sb.toString());
        return result;
    }

    private int getValue(char intput){
        if(intput == 'r' ){
            return 4;
        }if(intput == 'w' ){
            return 2;
        }if(intput == 'x' ){
            return 1;
        }if(intput == '-' ){
            return 0;
        }
        return 0;
    }
/////////////////////////////////////////////////////

    /**
     * 12시간 단위로 표시되는 시계가 있습니다.
     * 오전일 경우 AM , 오후일 경우 PM으로 표시되며, 시간은 시:분:초로 표시됩니다.
     * 예를 들어, 오전 11시 27분 35초일 경우 AM 11:27:35 로 표시가 되며,
     * 오후 8시 20분 4초일 경우 PM 08:20:04 로 표시가 됩니다.
     *
     * 이제부터, 우리는 시계에 표시된 시간에서 N초 후의 시간을 구하려고 합니다.
     * 단, N초 후의 시간 표시를 12시간 단위에서 24시간 단위 표시로 변경하려고 합니다.
     * 24시간 단위로 표시되므로 오전과 오후를 나타내는 AM, PM과 같은 문자열은 표시되지 않으며, 시:분:초만 표시됩니다.
     *
     * 예를 들어,
     * 12시간 단위 시계로 표시된 PM 01:00:00 에서 10초 후의 시간을 24시간 단위 표시로 변경하면 13:00:10 로 표시하게 됩니다.
     * 12시간 단위 시계로 표시된 PM 11:59:59 에서 1초 후의 시간을 24시간 단위 표시로 변경하면 00:00:00 로 표시하게 됩니다.
     * 12시간 단위로 표시된 현재 시각 P와 N이 매개변수로 주어졌을 때, N초 후의 시간 표시를 24시간 단위 표시로 변경하는 solution 함수를 완성해 주세요.
     * 단, 변경된 시각을 return 할 때는 string형으로 return 해주세요.
     *
     * 제한사항
     * 현재 시각 P는 string형으로 주어집니다. 오전과 오후는 각각 문자열 AM, PM으로 표시하고,
     * 시간은 12시간 단위로 표시되며, :를 기준으로 시, 분, 초를 나눠 시:분:초로 표시됩니다.
     *
     * 시, 분, 초는 각각 두자리 숫자로 이루어져 있으며, 24시간 단위 표시로 변환한 결과도 각각 두자리 숫자여야 합니다.
     * 한자리 숫자일 경우, 앞에 0을 붙이면 됩니다.
     *
     * (ex. 오후 5시 5분 5초를 12시간 단위로 표시하면 PM 5:5:5가 아닌 PM 05:05:05로 표시되며, 24시간 단위로 표시하면 20:05:05로 표시됩니다.)
     * AM 12:01:00은 24시간 단위 표시로 00:01:00 입니다.
     * PM 12:01:00은 24시간 단위 표시로 12:01:00 입니다.
     * N은 200,000 이하인 자연수입니다.
     *
     * 입출력 예 >
     * P	            N	    answer
     * PM 01:00:00	    10	    13:00:10
     * PM 11:59:59	    1	    00:00:00
     *
     * 입출력 예 설명
     * 입출력 예 #1, 2
     * 문제의 예시와 같습니다.
     * */
    private String testAddSeconds(String p, int n){
        Log.d(TAG, "[testAddSeconds]");
        String answer = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("aa hh:mm:ss");
        SimpleDateFormat writeFormat = new SimpleDateFormat( "HH:mm:ss");

        try {
            date = sdf.parse(p);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long inputTime = date.getTime()+ (n*1000);
        date.setTime(inputTime);

        if (date != null) {
            answer = writeFormat.format(date);
        }

        Log.d(TAG, "[testAddSeconds] formattedDate : "+answer);
        return answer;
    }
    ///////////////////////////////////////////////////

    /***
     * 스택은 먼저 넣은 자료가 나중에 나오는 형태의 자료구조입니다. 자료를 넣는 것을 '밀어 넣는다' 하여 푸시(push)라고 하고
     * 반대로 넣어둔 자료를 꺼내는 것을 팝(pop)이라고 합니다.
     * 예를 들어 1, 2, 3 순으로 스택에 자료를 넣고(push) 나서 꺼내기 작업(pop)을 하면 3, 2, 1 순으로 나오게 됩니다.
     * 1부터 N까지 정렬된 숫자가 들어있는 배열을 스택을 이용하여 순서를 바꿔 보려고 합니다.
     *
     * 예를 들어, 1을 push하고 바로 pop → 1
     * 2를 push, 3을 push하고, 2번 pop수행 → 3, 2
     * 위와 같이 진행하면 1, 3, 2의 순서로 배열이 변경됩니다.
     * 이렇게 스택을 이용하여 오름차순으로 정렬된 배열을 주어진 배열 arr로 바꿀 수 있는지 확인하는 solution 함수를 완성해 주세요.
     *
     * 제한사항
     * 배열의 크기 N : 100000이하의 자연수
     * 배열 원소의 크기 : 1이상 N이하의 정수(단, 같은 정수가 두 번 나오지 않는다.)
     *
     * 입출력 예 >>>
     * arr			result
     * [1, 3, 2]	true
     * [3, 1, 2]	false
     *
     * 입출력 예 설명
     * 입출력 예 #1
     * 1을 push하고 pop을 하여 1을 출력합니다. 그 다음 2와 3을 push 한 뒤, pop하여 3을 출력합니다.
     * 마지막으로 pop하여 2를 출력할 수 있으므로 true를 반환하면 됩니다.
     *
     * 입출력 예 #2
     * 1과 2를 push 한 다음, 3을 push하고 pop을 하면 3이 출력됩니다.
     * 그 다음으로 1을 출력해야 하지만 2가 존재하여 1을 먼저 pop을 할 수 없으므로 false를 반환하면 됩니다.
     * */
    private boolean testCheckStack(int[] arr){
        boolean answer = true;

        return answer;
    }

    ///////////////////////////////////////////////////
    /**
     * n x n 크기의 행렬의 각 칸에 1부터 n2 까지의 숫자가 지그재그 방향으로 채워져 있습니다.
     * 다음은 n = 5인 경우의 예시입니다.
     *
     * 이때 r 행, c 열의 칸에 어떤 숫자가 적혀있는지 구하려고 합니다.
     * 예를 들어 위 그림에서 r = 3, c = 2인 경우 3행, 2열의 위치에는 9가 들어있습니다.
     * 지그재그 행렬의 크기 n, 행의 위치 r, 열의 위치 c가 매개변수로 주어질 때,
     * n x n 크기의 지그재그 행렬의 r 행 c 열에 들어있는 숫자를 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     * n은 1 이상 10,000,000 이하의 자연수입니다.
     * r과 c는 각각 1 이상 n 이하의 자연수입니다.
     * 정답이 231 - 1보다 커질 수 있음에 주의하세요.
     *
     * 입출력 예
     * n	r	c	result
     * 5	3	2	9
     * 6	5	4	29
     * */
    private long testZigzag(int n, int r, int c) {
        Log.d(TAG, "[testZigzag]");
        long answer = 0;

        int[][] array = new int[n][n];
        int total = n * n;      // 2차원 배열의 전체 칸 수
        int x = 0, y = -1;
        int i, j;               // for 구문을 돌리기 위해 사용될 변수
        int cnt = 1;            // 초기값을 1로..

        int row = n;
        int column = n;

        int dir = 0;
        int dirUp = 1;
        int dirDown = 2;
        dir = dirUp;

        while (cnt <= total) {
            //2차원 배열의 오른쪽 끝에 도달하는 경우
            if (x == column - 1 && cnt <= total) {
                array[++y][x] = cnt++;
                dir = dirDown;
            }

            //2차원 배열의 아래쪽 끝에 도달하는 경우
            if (y == row - 1 && cnt <= total) {
                array[y][++x] = cnt++;
                dir = dirUp;
            }

            //2차원 배열의 위쪽 끝에 도달하는 경우
            if (y == 0 && cnt <= total) {
                array[y][++x] = cnt++;
                dir = dirDown;
            }

            //2차원 배열의 왼쪽 끝에 도달하는 경우
            if (x == 0 && cnt <= total) {
                array[++y][x] = cnt++;
                dir = dirUp;
            }

            //그 외 dir이 UP인 경우}
            if (dir == dirUp && x != column - 1 && y != 0 && cnt <= total) {
                array[--y][++x] = cnt++;
            }
            //그 외 dir이 DOWN인 경우
            if (dir == dirDown && y != row - 1 && x != 0 && cnt <= total) {
                array[++y][--x] = cnt++;
            }
        }

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                Log.d(TAG, "[testZigzag] array[i][j] : "+array[i][j] +" / i "+i +" / j : "+j);
            }
            Log.d(TAG, "\n");
        }
        answer = array[r-1][c-1];
        return answer;
    }

//    private long testZigzag(int n, int r, int c) {
//        Log.d(TAG, "[testZigzag]");
//        long answer = 0;
//
//        // int n = 5;
//        int[][] array = new int[n][n];
//        // 배열에 입력할 증가값
//        int count = 1;
//        // 배열의 현재 위치
//        int currentX = 0;
//        int currentY = 0;
//
//        // 0, 0 위치를 1로 초기화
//        array[currentX][currentY] = count++;
//
//        while (count <= n * n) {
//            // x가 n보다 작은 동안은 x 증가, 이후에는 y 증가
//            if (currentX + 1 < n) {
//                currentX++;
//            } else {
//                currentY++;
//            }
//
//            array[currentX][currentY] = count++;
//
//            // 우상향으로 이동
//            while (currentX - 1 > -1 && currentY + 1 < n) {
//                array[--currentX][++currentY] = count++;
//            }
//
//            // y가 n 보다 작은 동안은 y 증가, 이후에는 x증가
//            if (currentY + 1 < n) {
//                currentY++;
//            } else {
//                currentX++;
//            }
//
//            array[currentX][currentY] = count++;
//
//            // 좌하향으로 이동
//
//            while (currentY - 1 > -1 && currentX + 1 < n) {
//                array[++currentX][--currentY] = count++;
//            }
//        }
//
//        // 결과 출력
//        for(int i=0; i< array.length; i++){
//            for(int j=0; j<array[i].length; j++){
//                Log.d(TAG, "[testZigzag] array[i][j] : "+array[i][j] +" / i "+i +" / j : "+j);
//            }
//        }
////        for (int[] xx : array) {
////            for (int nn : xx) {
////                System.out.printf("%d ", nn);
////            }
////        }
////        System.out.println();
//        answer = array[2][1];
//        return answer;
//    }

    ///////////////////////////////////////////////////
    /**
     * 다음은 게임 플레이어가 조종하는 유닛들이 2차원 평면 위에 놓여있는 모습을 나타낸 그림입니다.
     * 위 그림에서 각 빨간색 점은 현재 유닛의 위치를 나타냅니다.
     * 플레이어는 특정 영역을 지정하여 여러 개의 유닛을 한꺼번에 선택할 수 있습니다.
     * 위 그림에서 파란색 점은 플레이어가 유닛을 선택하기 위해 지정하는 영역의 시작 지점 s와 끝 지점 e를 나타냅니다.
     * 이때 s와 e를 마주 보는 꼭짓점으로 하며, 각 변이 x축, y축에 평행한 직사각형 모양의 영역 내부에 있는 유닛들이 모두 선택되게 됩니다.
     *
     * 예를 들어 위 그림에서는 (1, 4) 위치에 플레이어가 선택하는 영역의 시작 지점 s가 있으며,(4, 1) 위치에 끝 지점 e가 있습니다.
     * 이때 색칠된 부분이 플레이어가 선택한 영역이 되며, 색칠해진 영역 안에 있는 유닛은 (1, 1), (2, 2), (3, 3) 위치의 3개입니다.
     * (경계선에 위치한 유닛도 선택됩니다)
     *
     * 현재 유닛들의 위치를 담고 있는 배열 location과 플레이어가 선택한 영역의 시작 위치 s, 끝 위치 e가 매개변수로 주어질 때,
     * 선택되는 유닛의 개수를 return 하도록 solution 함수를 완성해 주세요.
     *
     * 제한 사항
     * location은 N(1 ≤ N ≤ 10,000)개의 유닛들 위치를 담고 있는 배열입니다.
     * location의 각 원소는 유닛들이 위치한 좌표(xi, yi)를 나타냅니다.
     * 유닛들이 위치한 좌표 (xi, yi)의 범위 : 0 ≤ xi, yi ≤ 100,000, xi, yi 는 정수
     * 같은 위치에 두 개 이상의 유닛이 있는 경우는 없습니다.
     * s는 플레이어가 선택하는 영역의 시작점 (xs, ys)를, e는 끝점 (xe, ye)를 나타냅니다(0 ≤ xs, ys, xe, ye ≤ 100,000, xs, ys, xe, ye는 정수).
     * s와 e는 xs ≠ xe, ys ≠ ye인 경우만 입력으로 주어집니다.
     *
     * 입출력 예
     * location	s	e	result
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	[1, 4]	[4, 1]	3
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	[3, 4]	[0, 0]	4
     *
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
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	    [1, 4]	[4, 1]	3
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	    [3, 4]	[0, 0]	4
     *
     *
     int[][] location = {
     {0,3},
     {1,1},
     {1,5},
     {2,2},
     {3,3},
     {4,0}
     };
     int[] inputA = {3,4};
     int[] inputB = {0,0};
     int[] inputA = {1,4};
     int[] inputB = {4,1};
     solutionDrag(location, inputA, inputB)
     * */
    public int solutionDrag(int[][] location, int[] s, int[] e) {
        Log.d(TAG, "[solutionDrag] "
                +"\n s : "+s[0] + " / "+s[1]
                +"\n e : "+e[0] + " / "+e[1] );

        int answer = 0;
        int[] arrayX = new int[s.length]; // input X 축 배열
        int[] arrayY = new int[e.length]; // input Y 축 배열

//        arrayX[0] = s[0];
//        arrayX[1] = e[0];
//        arrayY[0] = s[1];
//        arrayY[1] = e[1];
        for(int x = 0; x < arrayX.length; x++){
            arrayX[x] = (x == 0) ? s[0] : e[0];
        }
        for(int y = 0; y < arrayY.length; y++){
            arrayY[y] = (y == 0) ? s[s.length - 1] : e[e.length -1];
        }

        // 오름 차순 정렬
        sort(arrayX);
        sort(arrayY);


        for (int i = 0; i < location.length; i++) {
            int itemX = location[i][0];
            int itemY = location[i][1];

            // x,y 축 비교
            if (arrayX[0] <= itemX && itemX <= arrayX[1]
                    && arrayY[0] <= itemY && itemY <= arrayY[1]) {
                answer++;
            }
        }
        return answer;
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

    //////////////////////////////////////////

}
