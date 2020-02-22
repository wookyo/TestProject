package com.example.imagetestapplication.model;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.imagetestapplication.IContract;
import com.example.imagetestapplication.constants.Constants;
import com.example.imagetestapplication.network.ApiManager;
import com.example.imagetestapplication.network.data.MainContentListData;
import com.example.imagetestapplication.preferences.SharedPreferencesManager;
import com.example.imagetestapplication.presenter.FavoritePresenterImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FavoriteModel implements IContract.FavoriteModel {
    private FavoritePresenterImpl mPresenter;

    public int RATIO_MAIN_RECENT = 0;                   // 최근 등록순
    public int RATIO_MAIN_RATE = 1;                     // 평점순

    public int RATIO_SUB_ASCENDING_ORDER = 0;           // 오름차순
    public int RATIO_SUB_DEASCENDINGORDER = 1;          // 내림차순


    public FavoriteModel(FavoritePresenterImpl presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void requestFavoriteData(Context context) {
        ArrayList<MainContentListData.MainListItemData> list = SharedPreferencesManager.getObjectListPreferences(
                context,
                Constants.Preference.PREFERENCE_FAVORITE_ADD);

        mPresenter.responseFavoriteData(list);
    }

    @Override
    public void requestSortFavoriteData(final int mainType,
                                 final int subType,
                                 ArrayList<MainContentListData.MainListItemData> list) {
        Comparator<MainContentListData.MainListItemData> comperator = new Comparator<MainContentListData.MainListItemData>() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int compare(MainContentListData.MainListItemData object1, MainContentListData.MainListItemData object2) {
                if (mainType == RATIO_MAIN_RECENT) {
                    return Long.compare(object1.getFavoriteAddTime(), object2.getFavoriteAddTime());
                } else {
                    return Double.compare(Double.parseDouble(object2.getRate()), Double.parseDouble(object1.getRate()));
                }
            }
        };
        Collections.sort(list, comperator);
        if(subType == RATIO_SUB_DEASCENDINGORDER){
            Collections.reverse(list);
        }
        mPresenter.responseSortFavoriteData(list);
    }
}
