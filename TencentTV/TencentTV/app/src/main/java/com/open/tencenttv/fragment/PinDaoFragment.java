package com.open.tencenttv.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.VedioPreViewActivity;
import com.open.tencenttv.adapter.PinDaoFragmentAdapter;
import com.open.tencenttv.widget.HeaderGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
public class PinDaoFragment  extends BaseV4Fragment {
    private String pindaoName;
    TextView text_pindao_name;

    //gridview
    private List<String> data;
    private HeaderGridView gridView;
    private PinDaoFragmentAdapter mAdapter;
    private int mSavePos = -1;
    private int mCount = 50;
    private View  headerView;

    public static PinDaoFragment newInstance(String pindaoName,MainUpView mainUpView1,View mOldView,EffectNoDrawBridge mRecyclerViewBridge){
        PinDaoFragment fragment = new PinDaoFragment();
        fragment.pindaoName = pindaoName;
        fragment.mainUpView1 = mainUpView1;
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pindao,container,false);
        gridView = (HeaderGridView) view.findViewById(R.id.gridView);


        headerView = inflater.inflate(R.layout.adapter_pindao_fragment_headview,null);
        text_pindao_name = (TextView) headerView.findViewById(R.id.text_pindao_name);
        text_pindao_name.setText(pindaoName);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.addHeaderView(headerView);
        // 加载数据.
        getData(20);
        //
        updateGridViewAdapter();

        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        //
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 这里注意要加判断是否为NULL.
                 * 因为在重新加载数据以后会出问题.
                 */
                if (view != null) {
                    mainUpView1.setFocusView(view, mOldView, 1.2f);
                }
                mOldView = view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mFindhandler.removeCallbacksAndMessages(null);
                mSavePos = position; // 保存原来的位置(不要按照我的抄，只是DEMO)
//                initGridViewData(new Random().nextInt(3));
//                mFindhandler.sendMessageDelayed(mFindhandler.obtainMessage(), 111);
                Toast.makeText(getActivity(), "GridView Item " + position + " pos:" + mSavePos, Toast.LENGTH_LONG).show();

                //进入频道
                Intent intent = new Intent();
                intent.setClass(getActivity(), VedioPreViewActivity.class);
                startActivity(intent);
            }
        });
        initGridViewData(new Random().nextInt(4));
//        mFirstHandler.sendMessageDelayed(mFirstHandler.obtainMessage(), 188);
    }

//    // 延时请求初始位置的item.
//    Handler mFirstHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            gridView.setDefualtSelect(2);
//        }
//    };

    // 更新数据后还原焦点框.
    Handler mFindhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mSavePos != -1) {
                gridView.requestFocusFromTouch();
                gridView.setSelection(mSavePos);
            }
        }
    };

    private void initGridViewData(int position) {
        String text = "position:" + position;
        // 测试数据更新.
        if (position == 0) {
            mCount += 10;
            getData(mCount);
            updateGridViewAdapter();
            text += "-->更新数据3个";
        } else if (position == 1) {
            mCount += 20;
            getData(mCount);
            updateGridViewAdapter();
            text += "-->更新数据100个";
        } else if (position == 2) {
            mCount += 30;
            getData(mCount);
            updateGridViewAdapter();
            text += "-->更新数据2000个";
        } else {
            // ... ...
        }
    }

    public List<String> getData(int count) {
        data = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            String text = "text" + "电影" + i;
            data.add(text);
        }
        return data;
    }

    private void updateGridViewAdapter() {
        mAdapter = new PinDaoFragmentAdapter(getActivity(), data);
        gridView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }



    public void setPindaoName(String pindaoName){
        text_pindao_name.setText(pindaoName);
    }

}
