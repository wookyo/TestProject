package com.example.imagetestapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.imagetestapplication.adapter.MainSectionPageAdapter;
import com.example.imagetestapplication.fragments.MainListFragment;
import com.example.imagetestapplication.fragments.FavoriteListFragment;
import com.example.imagetestapplication.utils.NetworkUtil;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.center_viewpager);

        MainSectionPageAdapter adapter = new MainSectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainListFragment(), getString(R.string.main_tap_main));
        adapter.addFragment(new FavoriteListFragment(), getString(R.string.main_tap_favorite));
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.center_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if(! NetworkUtil.checkNetworkState(this)){
            Toast.makeText(this, R.string.erroe_network_not_connect, Toast.LENGTH_LONG).show();
        }
    }
}
