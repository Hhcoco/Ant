package com.wangliang161220.ant.api;

import com.wangliang161220.ant.beans.BaseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wangliang on 2016/12/26.
 */

public interface ChangePwd {
    @FormUrlEncoded
    @POST("api/user/changePass")
    Call<BaseModel> sound(@Field("token") String token, @Field("oldPass") String oldPass , @Field("newPass") String newPass);
}
