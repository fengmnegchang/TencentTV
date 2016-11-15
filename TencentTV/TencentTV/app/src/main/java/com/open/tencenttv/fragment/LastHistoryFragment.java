package com.open.tencenttv.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.GridViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.LastHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 *
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class LastHistoryFragment extends BaseV4Fragment {

    //gridview
    private List<String> data;
    private GridViewTV gridView;
    private LastHistoryAdapter mAdapter;

    public static LastHistoryFragment newInstance(MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
        LastHistoryFragment fragment = new LastHistoryFragment();
        fragment.mainUpView1 = mainUpView1;
        fragment.mOldView = mOldView;
        fragment.mRecyclerViewBridge = mRecyclerViewBridge;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_history, container, false);
        gridView = (GridViewTV) view.findViewById(R.id.gridView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 加载数据.
        getData(10);
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
//                mFindhandler.removeCallbacksAndMessages(null);
//                initGridViewData(new Random().nextInt(3));
//                mFindhandler.sendMessageDelayed(mFindhandler.obtainMessage(), 111);
                Toast.makeText(getActivity(), "GridView Item " + position , Toast.LENGTH_LONG).show();
            }
        });
//        mFirstHandler.sendMessageDelayed(mFirstHandler.obtainMessage(), 188);
    }

    // 延时请求初始位置的item.
//    Handler mFirstHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            gridView.setDefualtSelect(0);
//        }
//    };

//    // 更新数据后还原焦点框.
//    Handler mFindhandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (mSavePos != -1) {
//                gridView.requestFocusFromTouch();
//                gridView.setSelection(mSavePos);
//            }
//        }
//    };


    public List<String> getData(int count) {
        data = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            String text = "text" + "电影" + i;
            data.add(text);
        }
        return data;
    }

    private void updateGridViewAdapter() {
        mAdapter = new LastHistoryAdapter(getActivity(), data);
        gridView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


}
