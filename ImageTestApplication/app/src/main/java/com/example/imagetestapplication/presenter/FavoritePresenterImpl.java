package com.example.imagetestapplication.presenter;

import android.content.Context;

import com.example.imagetestapplication.IContract;
import com.example.imagetestapplication.model.FavoriteModel;
import com.example.imagetestapplication.model.MainModel;
import com.example.imagetestapplication.network.data.MainContentListData;

import java.util.ArrayList;

public class FavoritePresenterImpl implements IContract.FavoritePresenter{
    private FavoriteModel mModel;
    private IContract.FavoriteView mView;

    public FavoritePresenterImpl(IContract.FavoriteView view) {
        mView = view;
        mModel = new FavoriteModel(this);
    }

    /**
     * 즐겨찾기 content request / response
     * */
    @Override
    public void requestFavoriteData(Context context) {
        mModel.requestFavoriteData(context);
    }

    @Override
    public void responseFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {
        mView.onResponseFavoriteData(list);
    }

    /**
     * 즐겨찾기 sort request / response
     * */
    @Override
    public void requestSortFavoriteData(int mainType, int subType, ArrayList<MainContentListData.MainListItemData> list) {
        mModel.requestSortFavoriteData(mainType, subType, list);
    }

    @Override
    public void responseSortFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {
        mView.onReponseSortFavoriteData(list);
    }

}
