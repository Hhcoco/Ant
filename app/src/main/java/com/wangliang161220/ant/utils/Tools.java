package com.wangliang161220.ant.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wangliang161220.ant.config.Config;

import retrofit2.Retrofit;

/**
 * Created by wangliang on 2016/12/26.
 */

public class Tools {

    /*获取网络接口*/
    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.PATH)
                .addConverterFactory(new MyConverter.ArbitraryResponseBodyConverterFactory())
                .build();
        return retrofit;
    }
    /**/
    public static void saveData(Context context , String key , String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SP , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key , value);
        editor.commit();
    }

    /**/
    public static  String getData(Context context , String key ){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SP , Context.MODE_PRIVATE);
        return sharedPreferences.getString(key , null);
    }
}
