package com.wangliang161220.ant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.wangliang161220.ant.R;
import com.wangliang161220.ant.api.Login;
import com.wangliang161220.ant.beans.BaseModel;
import com.wangliang161220.ant.utils.AbstractAnimationListener;
import com.wangliang161220.ant.utils.AnimationListener;
import com.wangliang161220.ant.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_img_cancel)
    ImageView loginImgCancel;
    @BindView(R.id.login_et_username)
    EditText loginEtUsername;
    @BindView(R.id.login_et_pwd)
    EditText loginEtPwd;
    @BindView(R.id.login_tv_login)
    TextView loginTvLogin;
    @BindView(R.id.login_loading_indicator)
    AVLoadingIndicatorView loginLoadingIndicator;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        /*不使用统一标题栏*/
        isUseUniversalBar = false;
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_img_cancel, R.id.login_tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_img_cancel:
                cancel();
                break;
            case R.id.login_tv_login:
                login();
                break;
        }
    }

    /*退出该页面*/
    public void cancel(){
        Animation animation_rotate = AnimationUtils.loadAnimation(mContext , R.anim.rotate);
        animation_rotate.setAnimationListener(new AnimationListener(){
            @Override
            public void onAnimationEnd(Animation animation) {
                if(!isFinishing()){
                    finish();
                }
            }
        });
        loginImgCancel.startAnimation(animation_rotate);

    }

    /*开始登陆*/
    public  void login(){

        Retrofit retrofit = Tools.getRetrofit();
        Call<BaseModel> call = retrofit.create(Login.class).login("1" , "2");
        if(!loginLoadingIndicator.isShown())
            loginLoadingIndicator.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                BaseModel baseModel = response.body();
                if(0 == baseModel.getErrorNo()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(mContext , baseModel.getErrorMsg() , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                if(loginLoadingIndicator.isShown())
                    loginLoadingIndicator.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                if(loginLoadingIndicator.isShown())
                    loginLoadingIndicator.setVisibility(View.INVISIBLE);
                Toast.makeText(mContext , "网络出了点小故障" , Toast.LENGTH_LONG).show();
            }
        });
    }

}
