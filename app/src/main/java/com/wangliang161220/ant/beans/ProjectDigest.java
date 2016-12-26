package com.wangliang161220.ant.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by wangliang on 2016/12/23.
 */

public class ProjectDigest implements Parcelable{

    public ProjectDigest() {
    }

    @JSONField(name = "")
    private int imgId;
    private String ProjectTitle;
    private String ProjectDigest;
    private String ProjectTime;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public String getProjectTime() {
        return ProjectTime;
    }

    public void setProjectTime(String projectTime) {
        ProjectTime = projectTime;
    }

    public String getProjectDigest() {
        return ProjectDigest;
    }

    public void setProjectDigest(String projectDigest) {
        ProjectDigest = projectDigest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imgId);
        dest.writeString(this.ProjectTitle);
        dest.writeString(this.ProjectDigest);
        dest.writeString(this.ProjectTime);
    }


    protected ProjectDigest(Parcel in) {
        this.imgId = in.readInt();
        this.ProjectTitle = in.readString();
        this.ProjectDigest = in.readString();
        this.ProjectTime = in.readString();
    }

    public static final Creator<ProjectDigest> CREATOR = new Creator<ProjectDigest>() {
        @Override
        public ProjectDigest createFromParcel(Parcel source) {
            return new ProjectDigest(source);
        }

        @Override
        public ProjectDigest[] newArray(int size) {
            return new ProjectDigest[size];
        }
    };
}
