package com.wangliang161220.ant.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wangliang161220.ant.R;
import com.wangliang161220.ant.activity.LoginActivity;
import com.wangliang161220.ant.activity.MainActivity;
import com.wangliang161220.ant.api.GetOppointmentDeviceList;
import com.wangliang161220.ant.api.Login;
import com.wangliang161220.ant.beans.BaseModel;
import com.wangliang161220.ant.beans.UserInfo;
import com.wangliang161220.ant.config.Config;
import com.wangliang161220.ant.utils.ACache;
import com.wangliang161220.ant.utils.Tools;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        getData();

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

    /*获取网络数据*/
    public void getData(){

        Retrofit retrofit = Tools.getRetrofit();
        String token = JSON.parseObject(Tools.getData(getContext() , Config.USERINFO), UserInfo.class).getToken();
        if(token == null){
            //跳转到登陆界面
        }else {
            Call<BaseModel> call = retrofit.create(GetOppointmentDeviceList.class).getOppointmentDeviceList(token);
            //弹出提示框，待完成

            call.enqueue(new Callback<BaseModel>() {
                @Override
                public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                    BaseModel baseModel = response.body();
                    if (0 == baseModel.getErrorNo()) {
                        String json = baseModel.getData();
                        Log.v("outt" , "Json"+json);
                    } else {
                        Toast.makeText(getContext(), baseModel.getErrorMsg(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<BaseModel> call, Throwable t) {
                    Toast.makeText(getContext(), "网络出了点小故障", Toast.LENGTH_LONG).show();
                }
            });
        }
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
