package com.example.imagetestapplication.model;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.imagetestapplication.IContract;
import com.example.imagetestapplication.constants.Constants;
import com.example.imagetestapplication.network.ApiManager;
import com.example.imagetestapplication.network.data.MainContentListData;
import com.example.imagetestapplication.network.data.MainContentListData.MainListItemData;
import com.example.imagetestapplication.preferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MainModel implements IContract.MainModel {
    private IContract.MainPresenter mPresenter;

    public MainModel(IContract.MainPresenter presenter) {
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
    public void requestCheckFavoriteData(Context context, ArrayList<MainListItemData> list) {
        ArrayList<MainListItemData> preferenceList =
                SharedPreferencesManager.getObjectListPreferences(context,
                Constants.Preference.PREFERENCE_FAVORITE_ADD);

        if(preferenceList == null){
            mPresenter.responseCheckFavoriteData(list);
        }

        HashMap<Integer, MainListItemData> tempMap = new  HashMap<Integer, MainListItemData>();
        if(preferenceList == null || list == null){
            mPresenter.responseCheckFavoriteData(null);
            return;
        }
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

}
