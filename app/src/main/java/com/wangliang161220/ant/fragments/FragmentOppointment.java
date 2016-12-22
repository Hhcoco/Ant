package com.wangliang161220.ant.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangliang161220.ant.R;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangliang on 2016/12/21.
 */

public class FragmentOppointment extends Fragment {

    @BindView(R.id.wheelview_district)
    WheelView wheelviewDistrict;
    @BindView(R.id.wheelview_street)
    WheelView wheelviewStreet;
    @BindView(R.id.wheelview_plot)
    WheelView wheelviewPlot;
    @BindView(R.id.wheelview_device)
    WheelView wheelviewDevice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_noise, container, false);
        ButterKnife.bind(this, view);
        final ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0;i<10;i++){
            arrayList.add("D"+i);
        }

        wheelviewDistrict.setWheelAdapter(new ArrayWheelAdapter(getContext())); // 文本数据源
        wheelviewDistrict.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelviewDistrict.setWheelData(arrayList);  // 数据集合
        wheelviewDistrict.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                ArrayList<String> arrayList2 = new ArrayList<>();
                for(int i=0;i<10;i++){
                    arrayList2.add(arrayList.get(position)+"new"+i);
                }
                wheelviewStreet.setWheelData(arrayList2);
            }
        });
/*        *//*添加二级联动*//*
        HashMap<String , List<String>> hashMap = new HashMap<>();
        wheelviewDistrict.join(wheelviewStreet);
        for(String district:arrayList){
            ArrayList<String> streetList = new ArrayList<>();
            for(int i=0;i<5;i++)
                streetList.add(district+":street"+i);
            hashMap.put(district , streetList);
        }
        wheelviewDistrict.joinDatas(hashMap);*/

        wheelviewStreet.setWheelAdapter(new ArrayWheelAdapter(getContext())); // 文本数据源
        wheelviewStreet.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelviewStreet.setWheelData(arrayList);  // 数据集合

        wheelviewPlot.setWheelAdapter(new ArrayWheelAdapter(getContext())); // 文本数据源
        wheelviewPlot.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelviewPlot.setWheelData(arrayList);  // 数据集合

        wheelviewDevice.setWheelAdapter(new ArrayWheelAdapter(getContext())); // 文本数据源
        wheelviewDevice.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelviewDevice.setWheelData(arrayList);  // 数据集合
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
