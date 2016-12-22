package com.wangliang161220.ant.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wangliang161220.ant.R;
import com.wangliang161220.ant.fragments.FragmentDevice;
import com.wangliang161220.ant.fragments.FragmentHome;
import com.wangliang161220.ant.fragments.FragmentMine;
import com.wangliang161220.ant.views.PostPosition;
import com.wangliang161220.ant.views.RadioViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.main_radioviewgroup)
    RadioViewGroup mainRadioviewgroup;
    @BindView(R.id.main_tv_title)
    TextView mainTvTitle;
    @BindView(R.id.fragment)
    FrameLayout fragment;

    /*底部Tab数据源*/
    private String[] mTabNames = {"首页", "设备", "我的"};
    private int[] mNormalPics = {R.drawable.home_no, R.drawable.device_no, R.drawable.mine_no};
    private int[] mSelectedPics = {R.drawable.home_yes, R.drawable.device_yes, R.drawable.mine_yes};
    /*Fragment集合*/
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mCurrentFragment;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*不使用统一标题栏*/
        isUseUniversalBar = false;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView(savedInstanceState);

    }

    public void initView(Bundle savedInstanceState) {
        /*底部Tab切换的自定义View*/
        mainRadioviewgroup.setData(mSelectedPics, mNormalPics, mTabNames);
        mainRadioviewgroup.setTextSize(16);
        mainRadioviewgroup.setPadding(5);
        mainRadioviewgroup.setTxtColor(R.color.cambridgeBlack, R.color.blue);
        mainRadioviewgroup.setPostPosition(new PostPosition() {
            @Override
            public void position(int position) {
                switchTab(position);
                switch (position){
                    case 0:
                        mainTvTitle.setText(getResources().getString(R.string.title_home)); break;
                    case 1:
                        mainTvTitle.setText(getResources().getString(R.string.title_device)); break;
                    case 2:
                        mainTvTitle.setText(getResources().getString(R.string.title_mine)); break;
                }
            }
        });
        mainRadioviewgroup.myaddView();
        /*标题栏*/
        mainTvTitle.setText(getResources().getString(R.string.title_home));
        /*创建Fragment*/
        if(savedInstanceState == null) {
            mFragments.add(new FragmentHome());
            mFragments.add(new FragmentDevice());
            mFragments.add(new FragmentMine());
        /*初始化Fragment相关*/
            mFragmentManager = getSupportFragmentManager();

            //默认显示第一页
            switchTab(0);
        }

    }
    /*切换fragment*/
    public void switchTab(int index){

        /*每次commit都要重新开启事务*/
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //添加到回退栈中
        //mFragmentTransaction.addToBackStack(null);
        mCurrentIndex = index;
        /*隐藏当前Fragment*/
        if(null != mCurrentFragment){
            mFragmentTransaction.hide(mCurrentFragment);
        }
        /*看是否已经添加过，如果有就取缓存*/
        Fragment oldFragment = mFragmentManager.findFragmentByTag(mFragments.get(mCurrentIndex).getClass().getName());
        /*如果为空，说明之前没有*/
        if(null == oldFragment){
            oldFragment = mFragments.get(mCurrentIndex);
        }
        mCurrentFragment = oldFragment;
        /*添加到事务中,用类名作为tag*/
        if(!oldFragment.isAdded())
            mFragmentTransaction.add(R.id.fragment , oldFragment , oldFragment.getClass().getName());
        else mFragmentTransaction.show(oldFragment);
        mFragmentTransaction.commit();

    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
