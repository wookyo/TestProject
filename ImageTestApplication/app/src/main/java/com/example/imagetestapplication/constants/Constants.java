package com.example.imagetestapplication.constants;

public class Constants {
    public class URL{
        // 메인 리스트 main Url

    // public static final String URL_MAIN_CONTENT_BASE = "https://www.gettyimages.com/photos/collaboration";
//public static final String URL_MAIN_CONTENT_BASE = "https://www.gettyimages.com/photos/collaboration?sort=best&mediatype=photography&phrase=collaboration";
public static final String URL_MAIN_CONTENT_BASE = "https://withinnovation.co.kr/App/json/";
        // 메인 리스트 sub Url
        public static final String URL_MAIN_CONTENT_SUB = ".json";
    }


    public class Intent{
        public static final String KEY_MAIN_LIST_ITEM = "intent.main_list_item";

        // 즐겨 찾기 추가 action
        public static final String ACTION_BROADCAST_FAVORITE_DATA_CHANGE = "intent.broadcast.favorite.data.chage";

        // LIST 에서 ITEM 클릭시
        public static final String ACTION_BROADCAST_MOVE_ITEM_INFO = "intent.broadcast.move.iteminfo";

        // 스크롤 변경에 따른 Data refresh
        public static final String ACTION_BROADCAST_NEED_REFRESH_ITEM = "intent.broadcast.need.item_refresh";

    }

    public class Preference{
        public static final String PREFERENCE_FAVORITE_ADD = "preference_favorite_add";
    }

}