package com.wangliang161220.ant.utils;

import com.wangliang161220.ant.config.Config;

import retrofit2.Retrofit;

/**
 * Created by wangliang on 2016/12/26.
 */

public class Tools {
    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.PATH)
                .addConverterFactory(new MyConverter.ArbitraryResponseBodyConverterFactory())
                .build();
        return retrofit;
    }
}
