package com.example.testappliction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.*;
import java.text.SimpleDateFormat;

import static java.util.Arrays.sort;


/**
 * 개인적인  TEST 위한 Activity
 * */
public class CodingTestActivity extends Activity {
    private String TAG = "CodingTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTest ();
    }

    private void mainTest(){
        Log.d(TAG, "[mainTest]");


    }



}
