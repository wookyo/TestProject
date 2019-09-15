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
     * main 즐겨찾기값과 비교 요청
     * */
    @Override
    public void checkFavoriteData(Context context, ArrayList<MainContentListData.MainListItemData> list) {
        mModel.checkFavoriteData(context, list);
    }

    /**
     * main list  Data 요청
     * */
    @Override
    public void requestContentData(Context context, int page) {
        mModel.requestContentData(context,page);
    }

    /**
     * main 즐겨찾기  Data 요청
     * */
    @Override
    public void requestFavoriteData(Context context) {
        mModel.requestFavoriteData(context);
    }

    /**
     * main list 즐겨찾기 재정렬 Data 요청
     * */
    @Override
    public void sortFavoriteData(int mainType, int subType, ArrayList<MainContentListData.MainListItemData> list) {
        mModel.sortFavoriteData(mainType, subType, list);
    }

    ////////////////////[response]//////////
    /**
     * main list 요청 응답
     * */
    @Override
    public void responseContentData(boolean isSuccess, MainContentListData data) {
        mView.responseContentData(isSuccess, data);
    }

    /**
     * main list 즐겨찾기값과 비교 요청 응답
     * */
    @Override
    public void responseCheckFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {
        mView.responseCheckFavoriteData(list);
    }

    /**
     * main list 즐겨찾기값 Data 요청 응답
     * */
    @Override
    public void responseFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {
        mView.responseFavoriteData(list);
    }

    /**
     * main list 즐겨찾기 재정렬 Data 요청 응답
     * */
    @Override
    public void reponseSortFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {
        mView.reponseSortFavoriteData(list);
    }
}
