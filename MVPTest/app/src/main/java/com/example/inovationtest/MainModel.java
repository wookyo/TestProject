package com.example.inovationtest;

import android.content.Context;
import com.example.inovationtest.network.ApiManager;
import com.example.inovationtest.network.data.MainContentListData;

public class MainModel implements MainContract.Model{
    private MainContract.Presenter mPresenter;

    public MainModel(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    /**
     * Main Conetnt 리스트 data 요청
     * */
    @Override
    public void requestContentData(Context context, String text, int page) {
        ApiManager.requestMainContentListData(context,
                text,
                page,
                new ApiManager.OnEventCallbackListener() {
                    @Override
                    public void onSucess(Object data) {
                        if (data != null) {
                            MainContentListData temp = (MainContentListData) data;
                            mPresenter.responseContentData(true, temp);
                        }
                    }

                    @Override
                    public void onError(String item) {
                        mPresenter.responseContentData(false, null);
                    }
                });
    }

}
