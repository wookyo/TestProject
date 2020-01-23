package com.example.inovationtest;

import android.content.Context;

import com.example.inovationtest.network.data.MainContentListData;

public interface MainContract {

    interface Model {
        void requestContentData(Context context, String text, int page);
    }

    interface View {
        void responseContentData(boolean isSuccess, MainContentListData data);
    }

    interface Presenter {
        void requestContentData(Context context, String text, int page);
        void responseContentData(boolean isSuccess, MainContentListData data);
    }
}
