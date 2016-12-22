package com.wangliang161220.ant.views;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.wangliang161220.ant.R;

/**
 * Created by wangliang on 2016/12/22.
 */

public class GeneralDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.general_dialog, container);
        return view;
    }
}
