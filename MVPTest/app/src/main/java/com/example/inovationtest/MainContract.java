package com.example.inovationtest;

import android.content.Context;

import com.example.inovationtest.network.data.MainContentListData;

import java.util.ArrayList;

public interface MainContract {

    interface Model {
        void requestContentData(Context context, int page);
        void checkFavoriteData(Context context, ArrayList<MainContentListData.MainListItemData> list);
        void requestFavoriteData(Context context);
        void sortFavoriteData(int mainType, int subType,ArrayList<MainContentListData.MainListItemData> list );
    }

    interface View {
        void responseContentData(boolean isSuccess, MainContentListData data);
        void responseCheckFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
        void responseFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
        void reponseSortFavoriteData(ArrayList<MainContentListData.MainListItemData> list);
    }

    interface Presenter {
        void requestContentData(Context context, int page);

        void responseContentData(boolean isSuccess, MainContentListData data);

        void checkFavoriteData(Context context, ArrayList<MainContentListData.MainListItemData> list);
        void responseCheckFavoriteData(ArrayList<MainContentListData.MainListItemData> list);

        void requestFavoriteData(Context context);
        void responseFavoriteData(ArrayList<MainContentListData.MainListItemData> list);

        void sortFavoriteData(int mainType, int subType,ArrayList<MainContentListData.MainListItemData> list );
        void reponseSortFavoriteData(ArrayList<MainContentListData.MainListItemData> list);

//        void result(int requestCode, int resultCode);
//        void loadTasks(boolean forceUpdate);
//        void addNewTask();
//        void setFiltering(TasksFilterType requestType);
//        void openTaskDetails(Task requestedTask);
    }
}
