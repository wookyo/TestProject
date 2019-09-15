package com.example.testappliction.codinginterview;
import com.example.testappliction.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 코딩 인터뷰 관련된 클래스
 * 예제 및 풀이 정리
 * */
public class CodeTestInterview5Activity extends Activity {
    private String TAG = "CodeTestInterview5Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTest ();
    }

    private void mainTest(){
        Log.d(TAG, "[mainTest]");
        Log.e(TAG, "[mainTest] Result : "+testCodingInterview53(1775));
    }
    ///////////////////////////////////////////////////
    /**
     * 흑백 모니터 화면은 하나의 바이트 배열에 저장되는데
     * 인접한 픽셀 8개를 한바이트에 묶어서 저장한다.
     * 화면의 폭은 w이며, w는 8로 나누어 떨어진다.
     * (따라서 어떤 바이트도 두행에 걸치지 않는다.)
     * 물론, 화면 높이는 배열의 길이와 화면 폭을 통해 유도 할수 있다.
     * 이때 (x1,y) 에서 (x2,y) 까지 수평선을 그려주는 함수를 작성하라.
     **/
    private void testCodingInterview58(byte[] screen, int width, int x1, int x2, int y){
        int start_Offset = x1 %8;
        int first_full_byte = x1 / 8;
        if(start_Offset != 0){
            first_full_byte ++;
        }

        int end_offset = x2 % 8;
        int last_full_byte = x2 / 8;
        if(end_offset != 7){
            last_full_byte --;
        }
        // 모든 비트가 1인 바이트
        for(int b = first_full_byte; b < last_full_byte ; b++){
            screen[(width/8) * y + b] = (byte) 0XFF;
        }
        // 시작 부분과 끝부분의 남은 비트들을 설정하기 위한 마스크
        byte start_mask = (byte) (0XFF >> start_Offset);
        byte end_mask = (byte) ~(0XFF >> (end_offset+1));

        // 선의 시작 부분과 끝부분 설정
        if((x1 / 8) == (x2 / 8)){ // x1 과 x2가 같은 바이트 내에 있을때
            byte mask = (byte) (start_mask & end_mask);
            screen[width / 8 * y + (x1 / 8)] |= mask;
        }else{
            if(start_Offset != 0){
                int byte_num = (width / 8) * y + first_full_byte -1;
                screen[byte_num] |= start_mask;
            }
            if(end_offset != 7){
                int byte_num = (width / 8) * y + last_full_byte +1;
                screen[byte_num] |= end_mask;
            }
        }
    }

    ///////////////////////////////////////////////////
    /**
     * 명령어를 가능한 적게 사용하여
     * 주어진 정수의 짝수 번째 비트값과
     * 홀수번째 비트 값을 바꾸는 메소드를 작성하라.
     * ex >
     * 0번째 비트와 1번째 비트를 바꾸고 2번째 비트와 3번째 비트를 바꾸는 식
     **/
    private int testCodingInterview57(int num){
        return (((num & 0xaaaaaaaa) >>> 1) | (num & 0x55555555) << 1);

    }
    ///////////////////////////////////////////////////
    /**
     * 정수 A 와 B 를 2진수로 표현했을때
     * A를 B로 바꾸기 위해 뒤집어야 하는 비트의 갯수를 구하여라
     * Ex >
     * 입력 : 29(11101), 15(01111)
     * 출력 : 2
     **/
    private int testCodingInterview56(int numA, int numB){
        int count = 0;
        for(int c = numA^numB; c !=0; c = c & (c-1)){
            count ++;
        }
        return count;
    }

    ///////////////////////////////////////////////////
    /**
     * 다음 코드가 하는일을 설명하라
     * (( num & (num-1)) ==0
     **/
    private void testCodingInterview55(int num){

    }

    ///////////////////////////////////////////////////
    /**
     * 양의 정수가 하나 주어졌다.
     * 이 숫자를 2진수로 표기 했을때 1비트의 갯수가 같은 숫자 중에서
     * 가장의 작은수와 가장 큰수를 구하라.
     **/
    private void testCodingInterview54(int num){
        testCodingInterview54_getNext(num);
        testCodingInterview54_gePrev(num);
    }

    private int testCodingInterview54_getNext(int num){
        int c = num;
        int c0 = 0;
        int c1 = 0;
        while(((c & 1) == 0 )&& (c != 0)){
            c0 ++;
            c >>= 1;
            while((c & 1) == 1){
                c1 ++;
                c >>=1;
            }
            if(c0 + c1 == 31 || c0+c1 == 0){
                return -1;
            }
            int p = c0 + c1;
            num |= (1 << p);
            num &= ~((1 << p) - 1);
            num |= (1<< (c1 - 1)) -1;

        }
        return num;
    }

    private int testCodingInterview54_gePrev(int num){
        int temp = num;
        int c0 = 0;
        int c1 = 0;

        while((temp & 1) == 1){
            c1++;
            temp >>= 1;
        }
        if(temp == 0){
            return -1;
        }
        while((temp & 1) == 0 && (temp != 0)){
            c0 ++;
            temp >>=1;
        }
        int p = c0 + c1;
        num &= ((~0) << (p+1));
        int mask = (1 << c1+1) -1;
        num |= mask <<(c0 -1);
        return num;
    }

    ///////////////////////////////////////////////////
    /**
     * 어떤 정수가 주어 졌을경우
     * 여러분은 이 정수의 비트 하나를 0에서 1로 변경이 가능하다.
     * 이때 연속으로 1이나올수 잇는 가장 긴 길이를 구하는 메소드를 작성하라
     *
     * ex>
     * 입력 : 1775 (or 11011101111)
     * 출력 8
     * * */
    private int testCodingInterview53(int num){
        if(~num == 0){  // 전부1이면 그자체가 가장 긴수열
            return Integer.BYTES *8;
        }
        int current = 0;
        int previous = 0;
        int maxLength = 1; // 적어도 1수열이 하나는 존재한다.

        while(num != 0){
            if((num & 1) == 1){ // 현재 비트가 1인경우
                current ++;

            }else if((num & 1) == 0){ // 현재 비트가 0인경우
                // 0으로 갱신 (다음 비트가 0일때) 혹은 current 으로 갱신 (다음비트가 1일경우 )
                previous = (num & 2 ) == 0 ? 0 : current;
                current = 0;
            }
            Log.d(TAG, "[mainTest]" + (current + previous +1) +" / "+maxLength);
            maxLength = Math.max(current + previous +1, maxLength);
            num >>= 1;
        }
        return maxLength;
    }

    private int testCodingInterview53_1(int num){
        String binaryString = Integer.toBinaryString(num); // 10 -> 2진수
        Log.d(TAG, "[testCodingInterview53] : "+binaryString +" / "+~num);
        char[] arr = binaryString.toCharArray();

        int count = 0;
        boolean needChange = false;
        for(int i = 0; i < arr.length - 1; i++ ){
            if(arr[i] == arr[i+1]){
                count ++;
            }else{

            }
        }
        return count;
    }

    ///////////////////////////////////////////////////
    /**
     * double 로 주어진 소수 값을 이진수로 변환하라
     * 길이가 32 이상이면 'error' 를 출력하라
     * ex >
     *  입력 : 0.25
     *  출력 : 0.01
     * */
    private String testCodingInterview52(double num){
        if(num > 1 || num <= 0){
            return "Error case 1";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(".");

        while(num > 0 ){
            if(sb.length() > 32){
                return "Error - case 2 : "+sb.toString(); // 32 비트 길이 제한
            }
            double temp = num *2;
            if(temp >= 1){
                sb.append(1);
                num = temp -1;
            }else{
                sb.append(0);
                num = temp;
            }
            Log.d(TAG, "[testCodingInterview52] num : "+num);
        }
        return sb.toString();
    }
    ///////////////////////////////////////////////////
    /**
     * 두개의 32 비트 수N과 M 이 주어지고
     * 비트위치가 i와 j 가 주어 졌을경우
     * M을 N에 삽입하는 메소드를 구현하라
     * M은 N의 j번째 비트부터 시작하여 i 번째 비트에서 끝난다
     *
     * EX >
     * 입력  : N = 10000000000, M= 10011, i = 2, j=4
     * 출력  : 10001001100
     * */
    private int testCodingInterview51(int n, int m, int i, int j){
        int allOne = ~0;
        int left = allOne << (j + 1);
        int right = (1 << i) - 1;
        int mask = left | right;

        int nClear = n & mask;
        int mShift = m << i;
        return nClear | mShift;
    }


}
