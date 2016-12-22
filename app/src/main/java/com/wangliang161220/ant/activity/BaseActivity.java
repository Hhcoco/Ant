package com.wangliang161220.ant.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangliang161220.ant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangliang on 2016/12/20.
 */

public class BaseActivity extends AppCompatActivity {

    /*是否使用统一标题栏，默认使用*/
    public boolean isUseUniversalBar = true;

    private Toolbar universalBarToolbar;
    private TextView universalBarTvTitle;

    @Override
    public void setContentView(int layoutResID) {
        if (isUseUniversalBar) {
            /*额外添加的布局*/
            View rootView = LayoutInflater.from(this).inflate(R.layout.universal_bar, null);
            universalBarToolbar = (Toolbar) rootView.findViewById(R.id.universal_bar_toolbar);
            universalBarTvTitle = (TextView) rootView.findViewById(R.id.universal_bar_tv_title);
            setSupportActionBar(universalBarToolbar);
            /*默认Toolbar背景*/
            universalBarToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
            universalBarToolbar.setTitleTextColor(getResources().getColor(R.color.white));
            /*真实的布局*/
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            LinearLayout rootLinearLayout = (LinearLayout) rootView.findViewById(R.id.universal_bar_ll);
            rootLinearLayout.addView(view);
            setContentView(rootLinearLayout);
        } else {
            super.setContentView(layoutResID);
        }
        setColor(this, R.color.blue);
    }

    //设置toolbar
    public void setNavigationIcon(int id){
        universalBarToolbar.setNavigationIcon(id);
    }
    public void setTitle(String title){
        universalBarTvTitle.setText(title);
        universalBarToolbar.setTitle("");
    }
    public void setTitleTextColor(int id){
        universalBarToolbar.setTitleTextColor(id);
    }

    //设置状态栏颜色
    public void setColor(Activity activity, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //不同rom的透明效果不一样，所以清除掉
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            return;
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条 *
     * * @param activity 需要设置的activity
     * * @param color 状态栏颜色值
     * * @return 状态栏矩形条
     */
    private View createStatusView(Activity activity, int color) {

        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(activity.getResources().getColor(color));
        return statusView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
