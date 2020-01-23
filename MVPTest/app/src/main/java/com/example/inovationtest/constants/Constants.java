package com.example.inovationtest.constants;

public class Constants {
    public static final String CLIENT_ID = "KakaoAK e5ade49ee0ab94df973130a331ae0233";//애플리케이션 클라이언트 아이디값";

    public class URL{
        // 메인 리스트 main Url
//        public static final String URL_MAIN_CONTENT_BASE = "https://withinnovation.co.kr/App/json/";
        public static final String URL_MAIN_CONTENT_BASE = "https://dapi.kakao.com/v2/search/image";

        // 메인 리스트 sub Url
        public static final String URL_MAIN_CONTENT_SUB = ".json";
    }


    public class Intent{
        public static final String KEY_MAIN_LIST_ITEM = "intent.main_list_item";

        public static final String ACTION_BROADCAST_FAVORITE_DATA_CHANGE = "intent.broadcast.favorite.data.chage";

    }

    public class Preference{
        public static final String PREFERENCE_FAVORITE_ADD = "preference_favorite_add";
    }
}

