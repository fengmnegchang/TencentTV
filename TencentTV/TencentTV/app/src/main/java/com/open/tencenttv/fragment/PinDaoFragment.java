package com.open.tencenttv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.tencenttv.R;

/**
 * ****************************************************************************************************************************************************************************
 * 频道内容 fragment
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class PinDaoFragment  extends Fragment {
    public static final String TAG = PinDaoFragment.class.getSimpleName();
    private String pindaoName;
    TextView text_pindao_name;

    public static PinDaoFragment newInstance(String pindaoName){
        PinDaoFragment fragment = new PinDaoFragment();
        fragment.pindaoName = pindaoName;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pindao,container,false);
        text_pindao_name = (TextView) view.findViewById(R.id.text_pindao_name);
        text_pindao_name.setText(pindaoName);
        return view;
    }


    public void setPindaoName(String pindaoName){
        text_pindao_name.setText(pindaoName);
    }

}
