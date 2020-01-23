package com.example.inovationtest.network.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MainContentListData  {
    @SerializedName("documents")
    private ArrayList<MainListItemData> documents;

    public ArrayList<MainListItemData> getData() {
        return documents;
    }

    public class MainListItemData implements Serializable {
        // 컬렉션
        @SerializedName("collection")
        private String collection;

        // 이미지 썸네일 URL
        @SerializedName("thumbnail_url")
        private String thumbnail_url;

        // 이미지 URL
        @SerializedName("image_url")
        private String image_url;

        // 이미지의 가로 크기
        @SerializedName("width")
        private int width;

        // 이미지의 세로 크기
        @SerializedName("height")
        private int height;

        // 출처명
        @SerializedName("display_sitename")
        private String display_sitename;

        // 문서 URL
        @SerializedName("doc_url")
        private String doc_url;

        // 문서 작성시간. ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
        @SerializedName("datetime")
        private String datetime;

        public String getCollection() {
            return collection;
        }

        public String getThumbnailUrl() {
            return thumbnail_url;
        }

        public String getImageUrl() {
            return image_url;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getDisplaySitename() {
            return display_sitename;
        }

        public String getDocUrl() {
            return doc_url;
        }

        public String getDateTime() {
            return datetime;
        }
    }

}
