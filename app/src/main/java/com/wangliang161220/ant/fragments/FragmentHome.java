package com.wangliang161220.ant.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wangliang161220.ant.R;
import com.wangliang161220.ant.activity.LocateActivity;
import com.wangliang161220.ant.activity.TestActivity;
import com.wangliang161220.ant.beans.ProjectDigest;
import com.wangliang161220.rxbus.RxBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by wangliang on 2016/12/21.
 */

public class FragmentHome extends Fragment {

    private BaseQuickAdapter mAdapter;

    @BindView(R.id.fragment_home_rv)
    RecyclerView fragmentHomeRv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        /**/
        final ArrayList<ProjectDigest> arrayList = new ArrayList<>();

        for(int i=0;i<5;i++){
            ProjectDigest projectDigest = new ProjectDigest();
            projectDigest.setImgId(R.drawable.default_portrait);
            projectDigest.setProjectTitle("标题："+i);
            projectDigest.setProjectDigest("我是摘要："+i);
            projectDigest.setProjectTime("2016-08-22");
            arrayList.add(projectDigest);
        }

        RxBus.getIntance().doSubscribe(FragmentHome.class.getName(), new Action1<Object>() {
            @Override
            public void call(Object o) {
                if(o != null){
                    Log.v("outt" , "收到消息"+o.toString());
                    arrayList.remove(0);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
        mAdapter = new BaseQuickAdapter<ProjectDigest , BaseViewHolder>(R.layout.item_list_device , arrayList) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectDigest o) {
                baseViewHolder.setImageResource(R.id.item_list_device_img , o.getImgId())
                        .setText(R.id.item_list_device_tv_title , o.getProjectTitle())
                        .setText(R.id.item_list_device_tv_overview , o.getProjectDigest())
                        .setText(R.id.item_list_device_tv_time , o.getProjectTime());

            }
        };
        fragmentHomeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentHomeRv.setAdapter(mAdapter);
        fragmentHomeRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (i){
                    case 0:
                        Toast.makeText(getContext() , "CurrentItem"+i , Toast.LENGTH_LONG).show(); break;
                    case 1:
                        Toast.makeText(getContext() , "CurrentItem"+i , Toast.LENGTH_LONG).show(); break;
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
