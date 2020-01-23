package com.example.inovationtest.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesManager {
    private static final String TAG = SharedPreferencesManager.class.getSimpleName();

    public static final String PREFERENCES_NAME = "inovation_preference";

    private static final String     DEFAULT_STRING    = "";
    private static final boolean    DEFAULT_BOOLEAN   = false;
    private static final int        DEFAULT_INT       = 0;
    private static final long       DEFAULT_LONG      = 0L;
    private static final float      DEFAULT_FLOAT     = 0F;


    /**
     * Preferences를 얻어온다.
     * @param context
     * @return
     */
    public static SharedPreferences getPreferences(Context context) {
        if(context != null){
            return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        } else {
            return null;
        }
    }

    /**
     * Preferences 초기화.
     * @param context
     */
    public static void clearPreferences(Context context) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
        }
    }

    /*-----------------------------------
     * Set Preference
     ------------------------------------*/

    /**
     * 객체 리스트 저장
     */
    public static void setObjectListPreferences(Context context, String key, ArrayList<MainListItemData> data){
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            SharedPreferences.Editor prefsEditor = prefs.edit();
            Gson gson = new Gson();

            String connectionsJSONString = gson.toJson(data);
            prefsEditor.putString(key, connectionsJSONString);
            prefsEditor.commit();
        }
    }


//    /**
//     * 객체 삭제
//     *  ID 기준으로 삭제
//     */
//    public static void deleteObjectPreferences(Context context, String key, MainListItemData data){
//        data.setFavoriteAddTime(0);
//        data.setFavoriteAdd(false);
//        ArrayList<MainListItemData> list = getObjectListPreferences(context, key);
//        for(MainListItemData item : list){
//            if(item.getId() == data.getId()){
//                list.remove(item);
//                break;
//            }
//        }
//        setObjectListPreferences(context, key, list);
//    }

//    /**
//     * 객체 저장
//     */
//    public static void setObjectPreferences(Context context, String key, MainListItemData data){
//        ArrayList<MainListItemData> list = getObjectListPreferences(context, key);
//        if(list == null){
//            list = new ArrayList<MainListItemData>();
//        }
//        data.setFavoriteAddTime(System.currentTimeMillis());
//        list.add(data);
//        setObjectListPreferences(context, key, list);
//    }

    /**
     * String 저장
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }
    /**
     * boolean 저장
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }
    /**
     * int 저장
     */
    public static void setInt(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }
    /**
     * long 저장
     */
    public static void setLong(Context context, String key, long value) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }
    /**
     * float 저장
     */
    public static void setFloat(Context context, String key, float value) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }

    /*-----------------------------------
     * Get Preference
     ------------------------------------*/
    /**
     * 객체 리스트 가져오기
     */
    public static ArrayList<MainListItemData> getObjectListPreferences(Context context, String key){
        SharedPreferences prefs = getPreferences(context);
        if(prefs != null){
            Gson gson = new Gson();
            String json = prefs.getString(key, "");

            String connectionsJSONString = prefs.getString(key, null);
            Type type = new TypeToken< List < MainListItemData >>() {}.getType();
            ArrayList< MainListItemData > connections = new Gson().fromJson(connectionsJSONString, type);

            return connections;
        } else {
            return null;
        }
    }

    /**
     * 객체 가져오기
     */
    public static MainListItemData setObjectPreferences(Context context, String key){
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            Gson gson = new Gson();
            String json = prefs.getString(key, "");
            MainListItemData obj = gson.fromJson(json, MainListItemData.class);
            return obj;
        } else {
            return null;
        }
    }

    /**
     * String 가져오기
     */
    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            return prefs.getString(key, DEFAULT_STRING);
        } else {
            return DEFAULT_STRING;
        }
    }


    /**
     * boolean 가져오기
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            return prefs.getBoolean(key, DEFAULT_BOOLEAN);
        } else{
            return DEFAULT_BOOLEAN;
        }
    }

    /**
     * int 가져오기
     */
    public static int getInt(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            return prefs.getInt(key, DEFAULT_INT);
        } else {
            return DEFAULT_INT;
        }
    }

    /**
     * long 가져오기
     */
    public static long getLong(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            return prefs.getLong(key, DEFAULT_LONG);
        } else {
            return DEFAULT_LONG;
        }
    }

    /**
     * float 가져오기
     */
    public static float getFloat(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        if(prefs!=null){
            return prefs.getFloat(key, DEFAULT_FLOAT);
        } else {
            return DEFAULT_FLOAT;
        }
    }

    /**
     * 키에 맞는 값의 존재여부를 반환한다.
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences preferences = getPreferences(context);
        if(preferences!=null){
            return preferences.contains(key);
        } else {
            return false;
        }
    }

    public static void remove(Context context, String key) {
        SharedPreferences preferences = getPreferences(context);
        if(preferences!=null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            editor.apply();
        }
    }

}
