package com.example.inovationtest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.inovationtest.fragments.MainContentListFragment;
import com.example.inovationtest.adapter.MainSectionPageAdapter;
import com.example.inovationtest.network.data.MainContentListData;
import com.example.inovationtest.utils.NetworkUtil;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private MainContentListData mMainContentListData;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.center_viewpager);

        MainSectionPageAdapter adapter = new MainSectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainContentListFragment(), getString(R.string.main_tap_main));
        mViewPager.setAdapter(adapter);


        if(! NetworkUtil.checkNetworkState(this)){
            Toast.makeText(this, R.string.error_network_not_connect, Toast.LENGTH_LONG).show();
        }
    }

}

