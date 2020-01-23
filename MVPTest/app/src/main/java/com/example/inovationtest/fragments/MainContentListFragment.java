package com.example.inovationtest.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inovationtest.R;
import com.example.inovationtest.adapter.MainContentListAdapter;
import com.example.inovationtest.network.data.MainContentListData;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;
import java.util.ArrayList;

public class MainContentListFragment extends MainListFragment implements MainListFragment.onEventChangeListener{
    private String TAG = MainContentListFragment.class.getSimpleName();
//private static String TAG = "Test";

    private ProgressDialog mProgressDialog = null;
    private Toast mToast = null;

    private ArrayList<MainListItemData> mDataList = new ArrayList<MainListItemData>();
    private int mCurrentPageNum = 1;
    private String mCurrentSearchText = "";

    private Handler mHandler = new Handler();
    private Runnable mReqestDataRunnable = new Runnable() {
        @Override
        public void run() {
            if( TextUtils.isEmpty(mCurrentSearchText)){
                clearDataView();
            }else{
                showLoading(true);
                mMainPresenter.requestContentData(getContext(), mCurrentSearchText, mCurrentPageNum);
            }
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

        mProgressDialog = new ProgressDialog(getActivity());
        mMainEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp =  s.toString();
                if(  !TextUtils.equals(mCurrentSearchText, temp)) {
                    clearDataView();

                    if(!TextUtils.isEmpty(temp) ){
                        mCurrentSearchText = temp;
                        requestMainListData(mCurrentSearchText);
                    }
                }
            }
        });
    }


    // main 컨텐츠 요청
    private void requestMainListData(String input) {
        mHandler.removeCallbacks(mReqestDataRunnable);
        mHandler.postDelayed(mReqestDataRunnable, 1000);
    }

    // view init
    private void initView() {
        setOnEventChangeListener(this);

        mAdapter = new MainContentListAdapter(getContext(), mDataList, this);
    }

    // 검색어가 없거나 DATA 가 없을경우
    private void clearDataView(){
        mCurrentSearchText = "";
        mCurrentPageNum = 1;
        mDataList.clear();
        mAdapter.notifyDataSetChanged();
    }

    public void showMessage(final String message) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(mToast != null) mToast.cancel();
                mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
                mToast.show();
            }
        };
        getActivity().runOnUiThread(r);
    }

    // 로딩 화면 show / hide
    private void showLoading(final boolean isShow) {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(isShow){
                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("잠시만 기다려 주세요");
                            mProgressDialog.show();
                        }else{
                            mProgressDialog.dismiss();
                        }

                    }
                }, 0);
    }

    @Override
    public void onScrollStateChanged(boolean isRefresh) {
//        if (isRefresh) {
//            mCurrentPageNum = 1;
//            mDataList.clear();
//            mAdapter.notifyDataSetChanged();
//            requestMainListData(mCurrentSearchText);
//        } else {
//            mCurrentPageNum++;
//            requestMainListData(mCurrentSearchText);
//        }
        mCurrentPageNum++;
        requestMainListData(mCurrentSearchText);
    }

    @Override
    public void onItemClicked(Object obj) {
        moveMainItemActivity(obj);
    }

    @Override
    public void responseContentData(boolean isSuccess, MainContentListData data) {
        showLoading(false);

        if(!isSuccess || data == null || data.getData().size() == 0){
            mCurrentPageNum = mCurrentPageNum > 1 ? mCurrentPageNum-- : 1;
            showMessage(getActivity().getResources().getString(R.string.error_not_search_data));
            clearDataView();
        }else{
            mDataList.addAll(data.getData());
        }
        mAdapter.notifyDataSetChanged();
    }

}
