package com.example.inovationtest;

import android.content.Context;

import com.example.inovationtest.network.data.MainContentListData;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.Model mModel;
    private MainContract.View mView;

    public MainPresenter( MainContract.View view) {
        mView = view;
        mModel = new MainModel(this);
    }


    ///////////////////[request]//////////

    /**
     * main list  Data 요청
     * */
    @Override
    public void requestContentData(Context context, String input, int page) {
        mModel.requestContentData(context, input, page);
    }

    ////////////////////[response]//////////
    /**
     * main list 요청 응답
     * */
    @Override
    public void responseContentData(boolean isSuccess, MainContentListData data) {
        mView.responseContentData(isSuccess, data);
    }

}
