package com.example.imagetestapplication;

import android.content.Context;

import com.example.imagetestapplication.network.data.MainContentListData;

import java.util.ArrayList;

public interface IContract {

    /////////////////////// [Model] ///////////////////////////
    interface MainModel {
        /**
         *  main content request
         * */
        void requestContentData(Context context, int page);
        /**
         *  서버값과 로컬 값 비교 request
         * */
        void requestCheckFavoriteData(Context context, ArrayList<MainContentListData.MainListItemData> list);

    }

    interface FavoriteModel {
        /**
         *  즐겨찾기 content request
         * */
        void requestFavoriteData(Context context);
        /**
         *  즐겨찾기 sort request
         * */
        void requestSortFavoriteData(int mainType, int subType,ArrayList<MainContentListData.MainListItemData> list );
    }


    /////////////////////// [VIEW] ///////////////////////////
    interface MainView{
        /**
         *  main content response
         * */
        void onResponseMainContentData(boolean isSuccess, MainContentListData data);
        /**
         *  서버값과 로컬 값 비교 response
         * */
        void onResponseCheckFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
    }

    interface FavoriteView{
        /**
         *  즐겨찾기 content response
         * */
        void onResponseFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
        /**
         *  즐겨찾기 sort response
         * */
        void onReponseSortFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
    }

    /////////////////////// [Presenter] ///////////////////////////
    interface MainPresenter{
        /**
         *  Main Content Data request / response
         * */
        void requestContentData(Context context, int page);
        void responseContentData(boolean isSuccess, MainContentListData data);

        /**
         * 서버값과 로컬 값 비교 request / response
         * */
        void requestCheckFavoriteData(Context context, ArrayList<MainContentListData.MainListItemData> list);
        void responseCheckFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
    }

    interface FavoritePresenter{
        /**
         *  즐겨찾기 Data request / response
         * */
        void requestFavoriteData(Context context);
        void responseFavoriteData(ArrayList<MainContentListData.MainListItemData> list);

        /**
         * 즐겨찾기 정렬 data request / response
         * */
        void requestSortFavoriteData(int mainType, int subType,ArrayList<MainContentListData.MainListItemData> list );
        void responseSortFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
    }

}
