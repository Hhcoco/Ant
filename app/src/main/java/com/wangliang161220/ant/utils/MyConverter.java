package com.wangliang161220.ant.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wangliang161220.ant.beans.BaseModel;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by wangliang on 2016/8/16.
 */
public class MyConverter {
    public static class ArbitraryResponseBodyConverterFactory extends Converter.Factory{
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            //return super.responseBodyConverter(type, annotations, retrofit);
            return new ArbitraryResponseBodyConverter();

        }
    }

    public static class ArbitraryResponseBodyConverter implements Converter<ResponseBody, BaseModel>{

        @Override
        public BaseModel convert(ResponseBody value) throws IOException {
            String originalString =  value.string();
            try{
                BaseModel baseModel = JSON.parseObject(originalString , BaseModel.class);
                return baseModel;
            }catch (Exception e){
                Log.d("wl" , "json转base_model出错.");
                return  null;
            }
        }
    }
}
