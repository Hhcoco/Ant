package com.wangliang161220.ant.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wangliang161220.ant.R;
import com.wangliang161220.ant.activity.LocateActivity;
import com.wangliang161220.ant.activity.TestActivity;
import com.wangliang161220.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangliang on 2016/12/21.
 */

public class FragmentDevice extends Fragment {
    @BindView(R.id.fragment_device_rv)
    RecyclerView fragmentDeviceRv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        ButterKnife.bind(this, view);
        /*初始化RV*/
        fragmentDeviceRv.setLayoutManager(new GridLayoutManager(getContext() , 3));
        ArrayList<Status> data = new ArrayList<>();
        data.add(new Status(R.drawable.device , "设备安装"));
        data.add(new Status(R.drawable.locate , "定位"));
        data.add(new Status(R.drawable.fault_analysis , "故障排查"));
        data.add(new Status(R.drawable.monitor , "实时监控"));
        QuickAdapter adapter = new QuickAdapter(R.layout.fragment_device_item , data);
        fragmentDeviceRv.setAdapter(adapter);

        RxBus.getIntance().sendObject(FragmentHome.class.getName() , "你好");

        fragmentDeviceRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity() , TestActivity.class)); break;
                    case 1:
                        startActivity(new Intent(getActivity() , LocateActivity.class)); break;
                }
            }
        });

        return view;
    }

    /*创建Adapter*/
    public class QuickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
        public QuickAdapter(int layoutResId, List<Status> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, Status item) {
            viewHolder.setText(R.id.item_device_tv, item.txt);
            Drawable  drawable = getResources().getDrawable(item.imgId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView)viewHolder.getView(R.id.item_device_tv))
                    .setCompoundDrawables(null , drawable , null , null);
        }
    }

    static class Status{
        public Status(int id , String txt){
            this.imgId = id;
            this.txt = txt;
        }
        public int imgId;
        public String txt;
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
