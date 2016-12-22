package com.wangliang161220.ant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wangliang161220.ant.R;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    WheelView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        wheelView = (WheelView) findViewById(R.id.test_wv);
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0;i<10;i++){
            arrayList.add("item"+i);
        }

        wheelView.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView.setSkin(WheelView.Skin.Holo); // common皮肤
        wheelView.setWheelData(arrayList);  // 数据集合
    }
}
