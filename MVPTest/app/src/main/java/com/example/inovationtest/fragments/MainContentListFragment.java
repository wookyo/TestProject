package com.example.inovationtest.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inovationtest.MainContract;
import com.example.inovationtest.MainPresenter;
import com.example.inovationtest.adapter.MainContentListAdapter;
import com.example.inovationtest.network.data.MainContentListData;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;
import java.util.ArrayList;

public class MainContentListFragment extends MainListFragment implements MainListFragment.onEventChangeListener{
    private String TAG = MainContentListFragment.class.getSimpleName();
    private ArrayList<MainListItemData> mDataList = new ArrayList<MainListItemData>();
    private int mCurrentPageNum = 1;


    private Handler mHandler = new Handler();
    private Runnable mReqestDataRunnable = new Runnable() {
        @Override
        public void run() {
            mMainPresenter.requestContentData(getContext(), mCurrentPageNum);
        }
    };

    public MainContentListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestMainListData();
    }


    // main 컨텐츠 요청
    private void requestMainListData() {
        mHandler.removeCallbacks(mReqestDataRunnable);
        mHandler.postDelayed(mReqestDataRunnable, 0);
    }

    // view init
    private void initView() {


        setOnEventChangeListener(this);

        mAdapter = new MainContentListAdapter(getContext(), mDataList, this);
    }

    @Override
    public void onScrollStateChanged(boolean isRefresh) {
        if (isRefresh) {
            mCurrentPageNum = 0;
            mDataList.clear();
            mAdapter.notifyDataSetChanged();
            requestMainListData();
        } else {
            mCurrentPageNum++;
            requestMainListData();
        }
    }

    @Override
    public void onItemClicked(Object obj) {
        moveMainItemActivity(obj);
    }

    @Override
    public void onAddFavoriteItem(boolean isAdd) {
        mMainPresenter.checkFavoriteData(getActivity(), mDataList);
    }

    @Override
    public void responseContentData(boolean isSuccess, MainContentListData data) {
        if(!isSuccess || data == null){
            mCurrentPageNum = mCurrentPageNum > 1 ? mCurrentPageNum-- : 1;
        }else{
            mDataList.addAll(data.getData().getItem());
            mMainPresenter.checkFavoriteData(getActivity(), mDataList);
        }
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void responseCheckFavoriteData(ArrayList<MainListItemData> list) {
        mDataList = list;
        mAdapter.notifyDataSetChanged();
    }
}
