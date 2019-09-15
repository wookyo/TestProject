package com.example.testappliction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.testappliction.questions.CodeTestCodilityActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }

//        });

        goCodeTestInterviewActivity();
    }

    private void goToCodingTestActivity(){
        Intent intent = new Intent(MainActivity.this,CodingTestActivity.class);
        startActivity(intent);
    }

    private void gotoCodeTestDataStructureActivity() {
            Intent intent=new Intent(MainActivity.this,CodeTestDataStructureActivity.class);
            startActivity(intent);

    }

    private void goCodeTestInterviewActivity(){
        Intent intent = new Intent(MainActivity.this,CodeTestInterviewActivity.class);
        startActivity(intent);
    }

    private void goToCodeTestCodilityActivity(){
        Intent intent = new Intent(MainActivity.this, CodeTestCodilityActivity.class);
        startActivity(intent);
    }

}
