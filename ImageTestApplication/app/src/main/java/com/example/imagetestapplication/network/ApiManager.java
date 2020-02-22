package com.example.imagetestapplication.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.imagetestapplication.constants.Constants;
import com.example.imagetestapplication.network.data.MainContentListData;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiManager {
    private static String TAG = ApiManager.class.getSimpleName();

    public interface  OnEventCallbackListener{
        public void onSucess(Object item);
        public void onError(String item);
    }

    /**
     *  Main 컨텐츠 리스트 Data Request
     * */
    public static void requestMainContentListData(final Context context,
                                                  final int pageNum,
                                                  final OnEventCallbackListener callback) {
        if (callback == null) {
            return;
        }
        int page = (pageNum == 0) ? 1 : pageNum;
        StringBuilder sb = new StringBuilder(Constants.URL.URL_MAIN_CONTENT_BASE);
        sb.append(page);
        sb.append(Constants.URL.URL_MAIN_CONTENT_SUB);
        String url = sb.toString();

        //  String url = Constants.URL.URL_MAIN_CONTENT_BASE;
        Log.e(TAG, "[requestMainContentListData] url : " + url);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Log.e(TAG, "[requestMainContentListData] : " + response.toString());

                        MainContentListData result = new Gson().fromJson(response.toString(), MainContentListData.class);
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
                        if (error != null) {
                            callback.onError(error.toString());
                        }
                    }
                }
        );
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        mRequestQueue.add(request);
    }

    /**
     *  Main 컨텐츠 리스트 Data Request
     * */
    public static void requestMainContentListDataByHttp(final Context context,
                                                  final int pageNum,
                                                  final OnEventCallbackListener callback) {
        if (callback == null) {
            return;
        }
        final String urlPath = Constants.URL.URL_MAIN_CONTENT_BASE;
        try {
            new AsyncTask<Void, Void, MainContentListData>() {
                // 처리 전에 호출되는 메소드
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                // 처리를 하는 메소드
                @Override
                protected MainContentListData doInBackground(Void... params) {
                    String inputLine = null;
                    String body = null;

                    try {
                        // request
                        URL url = new URL(urlPath);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");

                        // response
                        int responseCode = con.getResponseCode();
                        if(responseCode != 200 ){
                            return null;
                        }

                        // parsing
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        body = response.toString();
                        in.close();

                        // test
                        Document doc = Jsoup.parse(body);
                        Log.d(TAG, "[requestMainContentListData] doc : " + doc);

                    } catch (Exception e) {
                        Log.e(TAG, "[requestMainContentListData] Exception ");
                        e.printStackTrace();
                    }
                    return new Gson().fromJson(inputLine, MainContentListData.class);
                }

                // 처리가 모두 끝나면 불리는 메소드
                @Override
                protected void onPostExecute(MainContentListData response) {
                    super.onPostExecute(response);
                    // 통신 실패로 처리
                    if (response == null) {
                        Log.e(TAG, "[requestMainContentListData] onError ");
                         callback.onError(" response == null");

                    // 통신 결과를 표시
                    } else {
                        Log.e(TAG, "[requestMainContentListData] result :  "+response.toString());
                        callback.onSucess(response);
                    }
                }
            }.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}