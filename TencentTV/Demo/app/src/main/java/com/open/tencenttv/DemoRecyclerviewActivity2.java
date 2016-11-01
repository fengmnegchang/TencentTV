package com.open.tencenttv;

import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.LinearLayoutManagerTV;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.adapter.RecyclerViewPresenter;

/**
 * recyclerview Demo.
 * setSelectedItemAtCentered 设置一直在中间. (如果设置 false，那么请使用setSelectedItemOffset来设置相差的边距)
 */
public class DemoRecyclerviewActivity2 extends Activity implements RecyclerViewTV.OnItemListener {

    private Context mContext;
    private RecyclerViewTV mRecyclerView;
    private MainUpView mainUpView1;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_recyclerview_activity2);
        mContext = DemoRecyclerviewActivity2.this;
        mRecyclerView = (RecyclerViewTV) findViewById(R.id.recyclerView);
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.video_cover_cursor);
        float density = getResources().getDisplayMetrics().density;
        RectF receF = new RectF(getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density,
                getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density);
        mRecyclerViewBridge.setDrawUpRectPadding(receF);

        testRecyclerViewLinerLayout(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setOnItemListener(this);
        // item 单击事件处理.
        mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                System.out.println("item" + position + " ========onItemClick ");
            }
        });
    }

    /**
     * 测试LinerLayout.
     */
    private void testRecyclerViewLinerLayout(int orientation) {
        LinearLayoutManagerTV layoutManager = new LinearLayoutManagerTV(this);
        layoutManager.setOrientation(orientation);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setFocusable(false);
        mRecyclerViewPresenter = new RecyclerViewPresenter(20);
        mGeneralAdapter = new GeneralAdapter(mRecyclerViewPresenter);
        mRecyclerView.setAdapter(mGeneralAdapter);
        mRecyclerView.setSelectedItemOffset(111, 111); // 测试移动间距.
        mRecyclerView.setPagingableListener(new RecyclerViewTV.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
//                // 加载更多测试.
////                moreHandler.removeCallbacksAndMessages(null);
//                Message msg = moreHandler.obtainMessage();
//                msg.arg1 = 10;
//                moreHandler.sendMessageDelayed(msg, 3000);
//                load_more_pb.setVisibility(View.VISIBLE);
            }
        });
        mFocusHandler.sendEmptyMessageDelayed(10, 1000);
    }

    private int mSavePos = 0;

    Handler mFocusHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mRecyclerView.setDefaultSelect(mSavePos);
        }
    };

    RecyclerViewPresenter mRecyclerViewPresenter;
    GeneralAdapter mGeneralAdapter;
//
//    Handler moreHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            mRecyclerViewPresenter.addDatas(msg.arg1);
//            mSavePos = mRecyclerView.getSelectPostion();
//            mRecyclerView.setOnLoadMoreComplete(); // 加载更多完毕.
//            mFocusHandler.sendEmptyMessageDelayed(10, 10); // 延时请求焦点.
//            load_more_pb.setVisibility(View.GONE);
//        }
//    };


    public float getDimension(int id) {
        return getResources().getDimension(id);
    }


    @Override
    public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setUnFocusView(oldView);

    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setFocusView(itemView, 1.1f);
        oldView = itemView;
        System.out.println("item" + position + " ========onItemSelected ");
    }

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setFocusView(itemView, 1.1f);
        oldView = itemView;
    }

}
