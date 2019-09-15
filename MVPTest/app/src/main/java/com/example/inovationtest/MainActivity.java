package com.example.inovationtest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.inovationtest.fragments.MainContentListFragment;
import com.example.inovationtest.fragments.MainFavoriteListFragment;
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
        adapter.addFragment(new MainFavoriteListFragment(), getString(R.string.main_tap_favorite));
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.center_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if(! NetworkUtil.checkNetworkState(this)){
            Toast.makeText(this, R.string.erroe_network_not_connect, Toast.LENGTH_LONG).show();
        }
    }

}

