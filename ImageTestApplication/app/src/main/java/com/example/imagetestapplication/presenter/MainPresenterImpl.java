package com.example.imagetestapplication.presenter;

import android.content.Context;
import android.util.Log;

import com.example.imagetestapplication.IContract;
import com.example.imagetestapplication.model.MainModel;
import com.example.imagetestapplication.network.data.MainContentListData;

import java.util.ArrayList;

public class MainPresenterImpl implements IContract.MainPresenter {
    private String TAG = "MainPresenterImpl";
    private MainModel mModel;
    private IContract.MainView mView;

    public MainPresenterImpl(IContract.MainView view) {
        mView = view;
        mModel = new MainModel(this);
    }

    /**
     *  main content request / response
     * */
    @Override
    public void requestContentData(Context context, int page) {
        Log.d(TAG, "[requestContentData]");
        mModel.requestContentData(context,page);
    }

    @Override
    public void responseContentData(boolean isSuccess, MainContentListData data) {
        Log.d(TAG, "[responseContentData] : "+data);
        mView.onResponseMainContentData(isSuccess, data);
    }

    /**
     *  서버값과 로컬 값 비교 request / response
     * */
    @Override
    public void requestCheckFavoriteData(Context context, ArrayList<MainContentListData.MainListItemData> list) {
        Log.d(TAG, "[requestCheckFavoriteData]");
        mModel.requestCheckFavoriteData(context, list);
    }

    @Override
    public void responseCheckFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {
        Log.d(TAG, "[responseCheckFavoriteData]");
        mView.onResponseCheckFavoriteData(list);
    }
}
