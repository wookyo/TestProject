package com.example.inovationtest.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.inovationtest.constants.Constants;
import com.example.inovationtest.network.data.MainContentListData;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ApiManager {
    private static String TAG = ApiManager.class.getSimpleName();
//private static String TAG = "Test";


    public interface OnEventCallbackListener {
        public void onSucess(Object item);
        public void onError(String item);
    }

    //   Main 컨텐츠 리스트 Data Request
    public static void requestMainContentListData(final Context context,
                                                  final String text,
                                                  final int pageNum,
                                                  final OnEventCallbackListener callback) {
        if (callback == null) {
            return;
        }
        int page = (pageNum == 0) ? 1 : pageNum;
        StringBuilder sb = new StringBuilder(Constants.URL.URL_MAIN_CONTENT_BASE);
        try {
            // 검색어
            String query = URLEncoder.encode(text, "UTF-8");
            sb.append("?query=");
            sb.append(query);

            // 결과 문서 정렬 방식, accuracy (정확도순) or recency (최신순)
//            sb.append("?sort=");
//            sb.append("recency");

            // 결과 페이지 번호, 1-50 사이 Integer (기본 1))
            sb.append("?page=");
            sb.append(page);

            // 한 페이지에 보여질 문서의 개수, 	1-80 사이 Integer (기본 80)
            sb.append("?size=");
            sb.append("10");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String apiUrl = sb.toString();

        Log.d(TAG, "[requestMainContentListData] text : "+text +" / apiUrl : "+apiUrl);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,

                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "[onResponse]");
                        if (response == null) {
                            return;
                        }
                        MainContentListData result = new Gson().fromJson(response.toString(), MainContentListData.class);
                        Log.d(TAG, "[onResponse] result : "+result.getData().size());

                        if (result == null || result.getData() == null) {
                            callback.onError("Data is null");
                        } else {
                            callback.onSucess(result);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "[onErrorResponse] : " + error.toString());
                        if (error != null) {
                            callback.onError(error.toString());
                        }
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                // headers.put("Content-Type", "application/json");
                headers.put("Authorization", Constants.CLIENT_ID);
                return headers;
            }
        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        mRequestQueue.add(request);
    }


//    // Main 컨텐츠 리스트 Data Request
//    public static void requestMainContentListData(final Context context,
//                                                  final int pageNum,
//                                                  final OnEventCallbackListener callback) {
//        Log.d(TAG, "[requestMainContentListData] ");
//
//        if(callback == null){
//            return;
//        }
//        int page = (pageNum == 0)? 1 : pageNum;
//        final String clientId = "KakaoAK e5ade49ee0ab94df973130a331ae0233";//애플리케이션 클라이언트 아이디값";
//
//        new Thread() {
//            public void run() {
//                try {
//                    StringBuilder sb = new StringBuilder("https://dapi.kakao.com/v2/search/image");
//                    // 검색어
//                    String query = URLEncoder.encode("설현", "UTF-8");
//                    sb.append("?query=");
//                    sb.append(query);
//
////                    // 결과 문서 정렬 방식, accuracy (정확도순) or recency (최신순)
////                    sb.append("?sort=");
////                    sb.append("recency");
//
//                    // 결과 페이지 번호, 1-50 사이 Integer (기본 1))
//                    sb.append("?page=");
//                    sb.append("1");
//
//                    // 한 페이지에 보여질 문서의 개수, 	1-80 사이 Integer (기본 80)
//                    sb.append("?size=");
//                    sb.append("1");
//
//                    String apiURL = sb.toString();
//                    Log.d(TAG, "[requestMainContentListData] apiURL : "+apiURL);
//
//                    URL url = new URL(apiURL);
//                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
//                    con.setRequestMethod("GET");
//                    con.setRequestProperty("Authorization", clientId);
//
//                    int responseCode = con.getResponseCode();
//                    Log.e(TAG, "[requestMainContentListData] : "+responseCode);
//
//                    BufferedReader br;
//                    if(responseCode==200) { // 정상 호출
//                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    } else {  // 에러 발생
//                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//                    }
//                    String inputLine;
//                    StringBuffer response = new StringBuffer();
//                    while ((inputLine = br.readLine()) != null) {
//                        response.append(inputLine);
//                    }
//                    br.close();
//                    System.out.println(response.toString());
//
//                    //parsing
////                    JSONParser jsonParser = new JSONParser();
////                    JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
//                    JSONObject responseJSON = new JSONObject(response.toString());
//                    Log.e(TAG, "[requestMainContentListData] responseJSON : "+response.toString());
//
//                } catch (Exception e) {
//                    Log.e(TAG, "[requestMainContentListData] Exception : "+e);
//
//                    System.out.println(e);
//                }
//
//            }
//        }.start();
//    }

}
