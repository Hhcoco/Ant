package com.wangliang161220.ant.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.wangliang161220.ant.beans.BaseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wangliang on 2016/12/26.
 */

public interface Oppointment {
    @FormUrlEncoded
    @POST("api/order")
    Call<BaseModel> oppointment(@Field("token") String token, @Field("device_code") String password , @Field("order_time") String ordertime);
}
