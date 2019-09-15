package com.example.testappliction.codinginterview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testappliction.R;

public class CodeTestInterview4Activity extends Activity {
    private String TAG = "CodeTestInterview4Activity";

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
     *  오름차순으로 정렬된 배열이 있다.
     *  이 배열안에 들어 있는 원소는 정수이며
     *  중복된 값이 없다고 했을때 높이가 최소가 되는
     *  이진탐색 트리를 만드는 알고리즘을 작성하라.
     * */
    private void testCodingInterview42(){
        int[] input = {1,3,5,8,10,12,15};

        TeeNodelink result = createMinBST(input, 0, input.length-1);
    }

    private TeeNodelink createMinBST(int[] input, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (start + end)/2;
        TeeNodelink obj = new TeeNodelink(mid);
        obj.leftLink = createMinBST(input, 0, mid);
        obj.righLink = createMinBST(input, mid + 1, end);
        return obj;
    }

    class TeeNodelink{
        int data;
        TeeNodelink leftLink;
        TeeNodelink righLink;

        public TeeNodelink(int input){
            data = input;
        }
    }

    ///////////////////////////////////////////////////
    /**
     * [Coding Interview 문제]
     *  방향 그래프가 주어 졌을때 두 노드사이에 경로가 존재 하는지
     *  확인하는 알고리즘을 구현하라
     *
     *  조건 : 0->1
     *  1-> 2
     *  2->0
     *  3->2
     *  int input [][] = {{0,1,0,0}, {0,0,1,0}, {1,0,0,0}, {0,0,1,0}};
     * */
    private void testCodingInterview41(){

    }
}
