package com.example.imagetestapplication.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagetestapplication.IContract;

import com.example.imagetestapplication.adapter.MainContentListAdapter;
import com.example.imagetestapplication.network.data.MainContentListData;
import com.example.imagetestapplication.network.data.MainContentListData.MainListItemData;
import com.example.imagetestapplication.presenter.MainPresenterImpl;

import java.util.ArrayList;

public class MainListFragment extends BaseListFragment implements BaseListFragment.onEventChangeListener,
                                                                            IContract.MainView{
    private String TAG = MainListFragment.class.getSimpleName();

    protected MainPresenterImpl mMainPresenter;
    private Handler mHandler = new Handler();
    private Runnable mReqestDataRunnable = new Runnable() {
        @Override
        public void run() {
            mMainPresenter.requestContentData(getContext(), mCurrentPageNum);
        }
    };

    private ArrayList<MainContentListData.MainListItemData> mDataList = new ArrayList<MainListItemData>();
    private int mCurrentPageNum = 1;

    public MainListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestMainListData();
    }


    //
    /**
     *  main Content Data reauest
     * */
    private void requestMainListData() {
        mHandler.removeCallbacks(mReqestDataRunnable);
        mHandler.postDelayed(mReqestDataRunnable, 0);
    }

    /**
     * main Content View 초기화
     * */
    private void initView() {

        // Presenter 초기화
        mMainPresenter = new MainPresenterImpl(this);

        // data 변경 이벤트 callback 리스너 등록
        setOnEventChangeListener(this);

        // adapter 초기화
        mAdapter = new MainContentListAdapter(getContext(), mDataList, this);
    }


    @Override
    public void onScrollStateChanged(boolean isRefresh) {
        mRefreshLayout.setRefreshing(false);
        if(mDataList == null || mDataList.size() == 0){
            return;
        }
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
        // 서버값과 로컬 값 비교 request / response
        mMainPresenter.requestCheckFavoriteData(getActivity(), mDataList);
    }

    @Override
    public void onResponseMainContentData(boolean isSuccess, MainContentListData data) {

        if(!isSuccess || data == null){
            mCurrentPageNum = mCurrentPageNum > 1 ? mCurrentPageNum-- : 1;
        }else{
            mDataList.addAll(data.getData().getItem());
            mMainPresenter.requestCheckFavoriteData(getActivity(), mDataList);
        }
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseCheckFavoriteData(ArrayList<MainListItemData> list) {
        mRefreshLayout.setRefreshing(false);
        mDataList = list;
        mAdapter.notifyDataSetChanged();
    }

}
