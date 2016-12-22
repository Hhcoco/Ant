package com.wangliang161220.ant.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wangliang161220.ant.R;
import com.wangliang161220.ant.fragments.FragmentOppointment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LocateActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private int mCurrentIndex;
    private Fragment mCurrentFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);
        setTitle("定位");
        setNavigationIcon(R.drawable.back);

        /*HashMap<String , HashMap<String , HashMap<String  , ArrayList<String>>>> maps = new HashMap<>();
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("101");
        arrayList1.add("102");
        arrayList1.add("103");
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("201");
        arrayList2.add("202");
        arrayList2.add("203");
        HashMap<String , ArrayList<String>> hashMap1 = new HashMap<>();
        hashMap1.put("文殊院1栋" , arrayList1);
        hashMap1.put("文殊院2栋" , arrayList2);
        HashMap<String , ArrayList<String>> hashMap2 = new HashMap<>();
        hashMap2.put("孵化园1栋" , arrayList1);
        hashMap2.put("孵化园2栋" , arrayList2);
        HashMap<String , HashMap<String  , ArrayList<String>>> hashMap3 = new HashMap<>();
        hashMap3.put("文殊院" , hashMap1);
        hashMap3.put("孵化园" , hashMap2);
        maps.put("高新区" , hashMap3);
        String json = JSON.toJSON(maps).toString();
        Log.v("out" , "json-----:"+json);*/

        mFragments.add(new FragmentOppointment());
        switchTab(0);

    }

    public void switchTab(int index){

        if(null == mFragmentManager)
            mFragmentManager = getSupportFragmentManager();
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
            mFragmentTransaction.add(R.id.locate_fragment , oldFragment , oldFragment.getClass().getName());
        else mFragmentTransaction.show(oldFragment);
        mFragmentTransaction.commit();

    }

}
