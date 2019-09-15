package com.example.testappliction.codinginterview;
import com.example.testappliction.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 코딩 인터뷰 관련된 클래스
 * 예제 및 풀이 정리
 * */
public class CodeTestInterview1Activity extends Activity {
    private String TAG = "CodeTestInterview1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTest ();
    }

    private void mainTest(){
        Log.d(TAG, "[mainTest]");
        Log.d(TAG, "[mainTest] Result : "+testCodingInterview16_2("aabccccaaa"));

    }
    ///////////////////////////////////
    /**
     * 한단어가 다른 문자열에 포함되어 있는지 판별하는 isSubString이라는 메소드가 있다고 하자
     * s1 과 s2의 두문자열이 주어졌고 ,s2가 s1을 회전시킨 결과인지 판별하고자 한다.
     * isSubString을 한번만 호출하여 판별할수 있는 코드를 작성하라.
     *
     * ex>
     * waterbottle 은 erbottlewat을 회전시켜 얻을수 잇는 문자열이다.
     * */
    private boolean testCodingInterview19(String s1, String s2){
        int length = s1.length();
        if(s2.length() == s1.length() && length > 0){
            String s1s1 = s1+s1;
            return isSubString(s1s1, s2);
        }
        return false;
    }
    private boolean isSubString(String s1, String s2){
        return false;
    }

    ///////////////////////////////////
    /**
     * M x N 행렬의 한 원소가 0일경우 ,
     * 해당 원소가 속한 행과 열의 모든 원소를 0으로 설정하는 알고리즘을 작성하라.
     * */
    private void testCodingInterview18(int[][] input){
        boolean[] row = new boolean[input.length];
        boolean[] culumn = new boolean[input[0].length];

        for(int i = 0; i < input.length; i++){
            for(int j = 0; j <input[i].length; j++){
                if(input[i][j] == 0){
                    row[i] = true;
                    row[j] = true;
                }
            }
        }
        for(int cA = 0 ; cA < row.length; cA++){
            if(row[cA]){
                changeRow(input, cA);
            }
        }
        for(int cB = 0 ; cB < row.length; cB++){
            if(culumn[cB]){
                changeCulumn(input, cB);
            }
        }
    }

    private void changeRow(int[][]  inputA, int inputB){
        for(int c = 0 ; c < inputA.length; c++){
            inputA[inputB][c] = 0;
        }
    }
    private void changeCulumn(int[][]  inputA, int inputB){
        for(int c = 0 ; c < inputA.length; c++){
            inputA[c][inputB] = 0;
        }
    }

    ///////////////////////////////////
    /**
     * 이미지를 표현하는 N x N 행렬이 있다,
     * 이미지의 각 픽셀은 4바이트로 표현된다.
     * 이때 이미지를 90도 회전시키는 메서드를 작성하라.
     * 행렬을 추가로 사용하지 않고서 가능한가 ?
     * */
    private boolean testCodingInterview17(int[][] input){
        if(input.length == 0 || input.length != input[0].length){
            return false;
        }
        int n = input.length;
        for(int layer = 0; layer < n; layer++){
            int first = layer;
            int last = n - 1 - layer;
            for(int i = 0; i< last; i++){
                int offset = i- first;
                 // 윗부분 저장
                int top = input[first][i];
                // 왼쪽 > 위쪽
                input[first][i] = input[last - offset][first];
                // 아래쪽 > 왼쪽
                input[last - offset][first] = input[last][last- offset];
                // 오른쪽 > 아래쪽
                input[last][last- offset] = input[i][last];
                // 위쪽 > 오른쪽
                input[i][last] = top;
            }
        }
        return true;
    }
    ///////////////////////////////////
    /**
     * 반복되는 문자의 갯수를 세는 방식의 기본적인 문자열 압축 메소드를 작성하라
     * 문자열은 대소문자 알파벳으로만 이루어져 있다.
     * 만약 '압축된' 문자열이 기존 문자열보다 길다면 기존 문자열 리턴
     * 문자열은 대소문자 알파벳으로만 이루어져 있다.
     * Ex >
     *  "aabccccaaa" >> a2b1c5a3
     *
     * */
    private String testCodingInterview16(String input) {
        Log.d(TAG, "[testCodingInterview16]");
        String result = "";

        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for(int i = 0; i < input.length(); i++){
            countConsecutive ++;
            // 다음 문자와 현재 문자가 같지 않다면 현재 문자를 결과 문자열에 추가해준다.
            if(i + 1 >= input.length() || input.charAt(i) != input.charAt(i + 1)){
                compressed.append(input.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        result = (compressed.toString().length()> input.length())? input : compressed.toString();
        return result;
    }

    private String testCodingInterview16_2(String input) {
        String result = "";
        StringBuilder sb = new StringBuilder();

        int strintCount = 0;
        for(int i = 0; i < input.length(); i++){
            strintCount++;
            if(i+1 >= input.length() || input.charAt(i) != input.charAt(i+1)){
                sb.append(input.charAt(i));
                sb.append(strintCount);
                strintCount = 0;
            }
        }
        result = sb.toString();
        return result;
    }

    private String testCodingInterview16_1(String input) {
        String result = null;
        int index = 0;
        int indexItem = 1;
        StringBuilder sb = new StringBuilder();
        while (index < input.length()) {
            if (index == input.length() - 1) {
                break;
            }
            if (input.charAt(index) == input.charAt(index + 1)) {
                indexItem++;
            } else {
                indexItem = 1;
            }
            String item = String.valueOf(input.charAt(index));
            sb.append(getStringCount(item, indexItem));

            index++;
        }
        String temp = sb.toString();
        Log.d(TAG, "[test] temp : " + temp);
        result = (temp.length() > input.length()) ? input : temp;
        return result;
    }

    private String getStringCount(String input, int count){
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.append(count);
        return sb.toString();
    }

    ///////////////////////////////////////////////////
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
    private boolean testCodingInterview15(String input1, String input2){
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

    // inputA 문자 하나를 삽입해서 inputB를 만들수 잇는지 확인
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

    ///////////////////////////////////////////////////
    /**
     * 주어진 문자열이 회문의 순열인지 아닌지를 확인하는 메소드를 만들어라.
     * 회문이란 앞으로 읽으나 뒤로 읽으나 같은 단어 혹은 구절을 의미 하며
     * 순열이란 문자열을 재배치 하는 것을 뜻한다.
     * 회문의 꼭 사전에 등장하는 단어로 제한될 필요는 없다.
     *
     * ex>
     * 입력 : tact coa
     * 출력 : True ('taco cat' , 'atco cta')
     * */
    private boolean testCodingInterview14(String input){
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('Z')
                            - Character.getNumericValue('a')+1];
        for(char c : input.toCharArray()){
            int x = getCharNumber(c);
            if(x != -1){
                table[x] ++;
                if(table[x] % 2 == 1){  // 홀수일 경우
                    countOdd ++;
                }else{
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    // 각 문자에 숫자를 대입한다.
    // a->1, b->2
    private int getCharNumber(Character c){
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if(a <= val && val <= z){
            return val - a;
        }
        return -1;
    }

    ///////////////////////////////////////////////////
    /**
     * 문자열에 들어 있는 모든 공백을 %20으로 바꾸는 메서드를 작성하라.
     * 최종적으로 모든문자를 다 담을수 있을만큼 충분한 공간이 이미 확보되어 있으며
     * 문자열의 최종길이가 함께 주어진다고 가정해도 좋다.
     *
     * ex>
     * 입력 : "Mr John Smith", 13
     * 출력 : "Mr%20John%20Smith"
     * */
    private void testCodingInterview13(String inputA, int inputB){
        int space_Count = 0;
        int index = 0;
        char[] str = inputA.toCharArray();

        for(int i = 0; i < inputB; i++){
            if(str[i] == ' '){
                space_Count++;
            }
        }
        index = inputB + (space_Count * 2);
        if(inputB < str.length){
            str[inputB] = '\0'; // 배열의 끝
        }
        for(int i = inputB - 1; i >= 0; i--){
            if(str[i] == ' '){
                str[index -1] = '0';
                str[index -2] = '2';
                str[index -3] = '%';
                index = index -3;
            }else{
                str[index - 1] = str[i];
                index --;
            }
        }
    }
    ///////////////////////////////////////////////////
    /**
     * 문자열 두개가 주어 졋을때
     * 이 둘이 서로 순열관계에 있는지 확인하는 메소드를 작성하라.
     * */
    private boolean testCodingInterview12(String inputA, String inputB){
        if(inputA.length() != inputB.length()){
            return false;
        }
        int[] list = new int[128];
        char[] arrA = inputA.toCharArray();
        for(char itemA : arrA){
            arrA[itemA] ++;
        }

        for(int i = 0; i < inputB.length(); i++){
            char itemB = inputB.charAt(i);
            arrA[itemB] --;
            if(arrA[itemB] < 0 ){
                return false;
            }
        }
        return true;
    }

    ///////////////////////////////////////////////////

    /**
     * 문자열이 주어졌을때 ,
     * 이 문자열에 같은 문자가 중복되어 등장하는지 확인하는 알고리즘을 작성하라
     * 자료구조를 추가로 사용하지 않고 풀수 있는 알고리즘도 생각해보라
     * */
    private boolean testCodingInterview11(String input){
        if(input.length() > 128){
            return false;
        }
        boolean[] arr = new boolean[128];
        for(int i = 0; i < input.length(); i++){
            int charItem =  input.charAt(i);
            // 이미 문자열내에 존재하는지 체크
            if(arr[charItem]){
                return false;
            }
            arr[charItem] = true;
        }

        return true;
    }

    /**
     * 문자열이 주어졌을때 ,
     * 이 문자열에 같은 문자가 중복되어 등장하는지 확인하는 알고리즘을 작성하라
     * 자료구조를 추가로 사용하지 않고 풀수 있는 알고리즘도 생각해보라
     * */
    private boolean testCodingInterview11_1(String input){
        ArrayList<Character> list = new ArrayList<Character>();

        char[] arr = input.toCharArray();
        for(char item : arr){
                if(!list.contains(item)){
                    list.add(item);
                }else {
                    return true;
                }
        }
        return false;
    }
    ///////////////////////////////////////////////////
}
