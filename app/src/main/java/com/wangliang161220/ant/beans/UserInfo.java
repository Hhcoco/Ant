package com.wangliang161220.ant.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ProjectName: Ant .
 * Created by wangliang on 2017/1/4.
 */

public class UserInfo implements Parcelable {

    //用户Id
    @JSONField(name = "userId")
    private String userId;
    //用户类型
    @JSONField(name = "type")
    private String type;
    //用户名称
    @JSONField(name = "username")
    private String username;
    //token
    @JSONField(name = "token")
    private String token;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.type);
        dest.writeString(this.username);
        dest.writeString(this.token);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.userId = in.readString();
        this.type = in.readString();
        this.username = in.readString();
        this.token = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
