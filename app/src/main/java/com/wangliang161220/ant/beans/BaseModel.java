package com.wangliang161220.ant.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangliang on 2016/12/26.
 */

public class BaseModel implements Parcelable {

    /*错误码*/
    @JSONField(name = "")
    private int errorNo;
    /*错误信息*/
    @JSONField(name = "")
    private String errorMsg;
    /*返回信息*/
    @JSONField(name = "")
    private String data;
    /*响应时间*/
    @JSONField(name = "")
    private int responseTime;

    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.errorNo);
        dest.writeString(this.errorMsg);
        dest.writeString(this.data);
        dest.writeInt(this.responseTime);
    }

    public BaseModel() {
    }

    protected BaseModel(Parcel in) {
        this.errorNo = in.readInt();
        this.errorMsg = in.readString();
        this.data = in.readString();
        this.responseTime = in.readInt();
    }

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel source) {
            return new BaseModel(source);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };
}
