package com.example.inovationtest;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.inovationtest.constants.Constants;
import com.example.inovationtest.network.ApiManager;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;
import com.example.inovationtest.network.data.MainContentListData;
import com.example.inovationtest.preferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MainModel implements MainContract.Model{
    private MainContract.Presenter mPresenter;

    public int RATIO_MAIN_RECENT = 0;                   // 최근 등록순
    public int RATIO_MAIN_RATE = 1;                     // 평점순

    public int RATIO_SUB_ASCENDING_ORDER = 0;           // 오름차순
    public int RATIO_SUB_DEASCENDINGORDER = 1;          // 내림차순


    public MainModel(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    /**
     * Main Conetnt 리스트 data 요청
     * */
    @Override
    public void requestContentData(Context context, int page) {
        ApiManager.requestMainContentListData(context,
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

    /**
     * 서버값과 로컬 값 비교
     * */
    @Override
    public void checkFavoriteData(Context context, ArrayList<MainListItemData> list) {
        ArrayList<MainListItemData> preferenceList = SharedPreferencesManager.getObjectListPreferences(context,
                Constants.Preference.PREFERENCE_FAVORITE_ADD);
        if(preferenceList == null){
            mPresenter.responseCheckFavoriteData(list);
        }

        HashMap<Integer, MainListItemData> tempMap = new  HashMap<Integer, MainListItemData>();
        for(MainListItemData item : preferenceList){
            tempMap.put(item.getId(), item);
        }
        for(MainListItemData item : list){
            if(tempMap.containsKey(item.getId())){
                item.setFavoriteAdd(true);
            }else{
                item.setFavoriteAdd(false);
            }
        }
        mPresenter.responseCheckFavoriteData(list);
    }

    @Override
    public void requestFavoriteData(Context context) {
        ArrayList<MainListItemData> list = SharedPreferencesManager.getObjectListPreferences(
                context,
                Constants.Preference.PREFERENCE_FAVORITE_ADD);

        mPresenter.responseFavoriteData(list);
    }

    @Override
    public void sortFavoriteData(final int mainType,
                                 final int subType,
                                 ArrayList<MainListItemData> list) {
        Comparator<MainContentListData.MainListItemData> comperator = new Comparator<MainContentListData.MainListItemData>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int compare(MainListItemData object1, MainListItemData object2) {
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
        mPresenter.reponseSortFavoriteData(list);
    }

}
