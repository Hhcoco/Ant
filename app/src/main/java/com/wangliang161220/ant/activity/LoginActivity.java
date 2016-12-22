package com.wangliang161220.ant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangliang161220.ant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_img_cancel)
    ImageView loginImgCancel;
    @BindView(R.id.login_et_username)
    EditText loginEtUsername;
    @BindView(R.id.login_et_pwd)
    EditText loginEtPwd;
    @BindView(R.id.login_tv_login)
    TextView loginTvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*不使用统一标题栏*/
        isUseUniversalBar = false;
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_img_cancel, R.id.login_tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_img_cancel:

                break;
            case R.id.login_tv_login:
                startActivity(new Intent(LoginActivity.this , MainActivity.class));
                finish();
                break;
        }
    }
}
