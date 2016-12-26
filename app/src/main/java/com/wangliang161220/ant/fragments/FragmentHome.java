package com.wangliang161220.ant.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangliang161220.ant.R;
import com.wangliang161220.ant.beans.ProjectDigest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangliang on 2016/12/21.
 */

public class FragmentHome extends Fragment {
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

        fragmentHomeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentHomeRv.setAdapter(new BaseQuickAdapter<ProjectDigest , BaseViewHolder>(R.layout.item_list_device , arrayList) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, ProjectDigest o) {
                baseViewHolder.setImageResource(R.id.item_list_device_img , o.getImgId())
                              .setText(R.id.item_list_device_tv_title , o.getProjectTitle())
                              .setText(R.id.item_list_device_tv_overview , o.getProjectDigest())
                              .setText(R.id.item_list_device_tv_time , o.getProjectTime());

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
