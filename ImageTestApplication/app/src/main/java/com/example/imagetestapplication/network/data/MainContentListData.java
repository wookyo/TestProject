package com.example.imagetestapplication.network.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MainContentListData  {
    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private MainListInfoData data;

    public String getMsg() {
        return msg;
    }

    public MainListInfoData getData() {
        return data;
    }

    public class MainListInfoData{
        @SerializedName("totalCount")
        private String totalCount;

        @SerializedName("product")
        private ArrayList<MainListItemData> item;

        public String getTotalCount() {
            return totalCount;
        }

        public ArrayList<MainListItemData> getItem() {
            return item;
        }
    }

    public class MainListItemData implements Serializable {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("thumbnail")
        private String thumbnail;

        @SerializedName("description")
        private MainListItemDescription description;

        @SerializedName("rate")
        private String rate;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        private boolean favoriteAdd = false;

        private long favoriteAddTime =  0;

        public MainListItemDescription getDescription() {
            return description;
        }

        public String getRate() {
            return rate;
        }


        public boolean getIsFavoriteAdd() {
            return favoriteAdd;
        }

        public void setFavoriteAdd(boolean favoriteAdd) {
            this.favoriteAdd = favoriteAdd;
        }

        public long getFavoriteAddTime() {
            return favoriteAddTime;
        }

        public void setFavoriteAddTime(long favoriteAddTime) {
            this.favoriteAddTime = favoriteAddTime;
        }


    }

    public class MainListItemDescription implements Serializable{
        @SerializedName("imagePath")
        private String imagePath;

        @SerializedName("subject")
        private String subject;

        @SerializedName("price")
        private int price;

        public String getImagePath() {
            return imagePath;
        }

        public String getSubject() {
            return subject;
        }

        public int getPrice() {
            return price;
        }
    }

}