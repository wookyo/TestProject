package com.example.inovationtest.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inovationtest.MainPresenter;
import com.example.inovationtest.R;
import com.example.inovationtest.adapter.MainFavoriteListAdapter;
import com.example.inovationtest.constants.Constants;

import com.example.inovationtest.network.data.MainContentListData;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;
import com.example.inovationtest.preferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainFavoriteListFragment extends MainListFragment implements MainListFragment.onEventChangeListener {
    private String TAG = MainFavoriteListFragment.class.getSimpleName();


    public int RATIO_MAIN_RECENT = 0;                   // 최근 등록순
    public int RATIO_MAIN_RATE = 1;                     // 평점순
    private int mMainSortRatio = RATIO_MAIN_RECENT;

    public int RATIO_SUB_ASCENDING_ORDER = 0;           // 오름차순
    public int RATIO_SUB_DEASCENDINGORDER = 1;          // 내림차순
    private int mSubSortRatio = RATIO_SUB_ASCENDING_ORDER;


    private ArrayList<MainListItemData> mDataList = new ArrayList<MainListItemData>();
    private int mCurrentPageNum = 1;

    private Handler mHandler = new Handler();
    private Runnable mReqestDataRunnable = new Runnable() {
        @Override
        public void run() {
            mMainPresenter.requestFavoriteData(getActivity());
        }
    };

    public MainFavoriteListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        requestFavoriteListData();
    }


    /**
     * 상단의 즐겨 찾기 탭 Show / Hide
     * */
    private void showSortTapView(boolean isShow){
        if(isShow){
            mFavoriteSortTapContainer.setVisibility(View.VISIBLE);
            mFavoriteSortTapMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSortTapViewDialog(true);
                }
            });

            mFavoriteSortTapSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSortTapViewDialog(false);
                }
            });
        }else{
            mFavoriteSortTapContainer.setVisibility(View.GONE);

        }
    }

    /**
     * 상단의 즐겨 찾기 탭 클릭시 다이얼로그 출력
     * */
    private void showSortTapViewDialog(final boolean isMain){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        int items = (isMain == true) ? R.array.favorite_tap_main_type_values
                : R.array.favorite_tap_sub_type_values;
        builder.setItems(items,
                new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {
                initSortTapView(isMain, which);
                mMainPresenter.sortFavoriteData(mMainSortRatio, mSubSortRatio, mDataList);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true); // 확인말고 다른 영역 눌러서 alert 닫혔을때 반응없는 이슈 있어서 처리
        if (!getActivity().isFinishing()) {
            alertDialog.show();
        }
    }

    /**
     *
     *  상단의 즐겨 찾기 탭 UI 초기화
     * */
    private void initSortTapView(boolean isMain, int index){
        if(isMain){
            mMainSortRatio = (index == 0 ) ? RATIO_MAIN_RECENT : RATIO_MAIN_RATE;
            mFavoriteSortTapMain.setText((index == 0 ) ? R.string.favorite_tap_recent :  R.string.favorite_tap_rate);
        }else{
            mSubSortRatio = (index == 0 ) ? RATIO_SUB_ASCENDING_ORDER : RATIO_SUB_DEASCENDINGORDER;
            mFavoriteSortTapSub.setText((index == 0 ) ? R.string.favorite_tap_ascending :  R.string.favorite_tap_deascending);
        }
    }

    /**
     * main 컨텐츠 요청
     * */
    private void requestFavoriteListData() {
        mHandler.removeCallbacks(mReqestDataRunnable);
        mHandler.postDelayed(mReqestDataRunnable, 0);
    }


   /**
    * view 초기화
    * */
    private void initView() {
        setOnEventChangeListener(this);
        mAdapter = new MainFavoriteListAdapter(getContext(),  mDataList, this);
    }


    @Override
    public void onScrollStateChanged(boolean isRefresh) {
        if (isRefresh) {
            mCurrentPageNum = 0;
            mDataList.clear();
            mAdapter.notifyDataSetChanged();
            requestFavoriteListData();
        } else {
            mCurrentPageNum++;
            requestFavoriteListData();
        }
    }

    @Override
    public void onItemClicked(Object obj) {
        moveMainItemActivity(obj);
    }

    @Override
    public void onAddFavoriteItem(boolean isAdd) {
        requestFavoriteListData();
    }

    @Override
    public void responseFavoriteData(ArrayList<MainListItemData> list) {
        mDataList.clear();
        if (list == null) {
            showSortTapView(false);
            initSortTapView(true, 0);
            initSortTapView(false, 0);
            return;
        }
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();

        // DATA 상태에 따른 Tap visible 초기화
        if (mDataList.size() > 0) {
            showSortTapView(true);
        } else {
            showSortTapView(false);
            initSortTapView(true, 0);
            initSortTapView(false, 0);
        }
        mMainPresenter.sortFavoriteData(mMainSortRatio, mSubSortRatio, mDataList);
    }

    @Override
    public void reponseSortFavoriteData(ArrayList<MainListItemData> list) {
        mDataList = list;
        mRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }
}