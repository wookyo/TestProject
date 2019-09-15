package com.example.inovationtest.network;

import android.content.Context;
import android.util.Log;

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

public class ApiManager {
    private static String TAG = ApiManager.class.getSimpleName();

    public interface  OnEventCallbackListener{
        public void onSucess(Object item);
        public void onError(String item);
    }

    // Main 컨텐츠 리스트 Data Request
    public static void requestMainContentListData(final Context context,
                                                  final int pageNum,
                                                  final OnEventCallbackListener callback) {
        if(callback == null){
            return;
        }
        int page = (pageNum == 0)? 1 : pageNum;
        StringBuilder sb = new StringBuilder(Constants.URL.URL_MAIN_CONTENT_BASE);
        sb.append(page);
        sb.append(Constants.URL.URL_MAIN_CONTENT_SUB);
        String url = sb.toString();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null ){
                            return;
                        }
                        MainContentListData result = new Gson().fromJson(response.toString(), MainContentListData.class);
                        if(result == null || result.getData() == null){
                            callback.onError("Data is null");
                        }else{
                            callback.onSucess(result);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null ){
                            callback.onError(error.toString());
                        }
                    }
                }
        );
        RequestQueue  mRequestQueue = Volley.newRequestQueue(context);
        mRequestQueue.add(request);
    }

}
