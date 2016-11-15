package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;

/**
 * ****************************************************************************************************************************************************************************
 * 设置
 *
 * @author :fengguangjing
 * @createTime: 16/11/11
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class SettingsActivity extends CommonFragmentActivity {
    //scrollview
    private SmoothHorizontalScrollView hscroll_view;
    private FrameMainLayout main_lay11;

    private ReflectItemView item_wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        hscroll_view = (SmoothHorizontalScrollView) findViewById(R.id.hscroll_view);
        main_lay11 = (FrameMainLayout) findViewById(R.id.main_lay);
        item_wifi =  (ReflectItemView)findViewById(R.id.item_wifi);
    }

    @Override
    protected void initValue() {
        super.initValue();
        // 默认是 OpenEff...，建议使用 NoDraw... ...
        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
        mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setTranDurAnimTime(200);
        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距

        hscroll_view.setFadingEdge((int) getDimension(R.dimen.w_100)); // 滚动窗口也需要适配.
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        main_lay11.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {
                if (newFocus != null)
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
                float scale = 1.1f;
                mainUpView1.setFocusView(newFocus, mOldView, scale);
                mOldView = newFocus; // 4.3以下需要自己保存.
            }
        });
        for (int i = 0; i < main_lay11.getChildCount(); i++) {
            main_lay11.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
//						v.performClick();
                        v.requestFocus();
                    }
                    return false;
                }
            });
        }
        mFirstHandler.sendMessageDelayed(mFirstHandler.obtainMessage(), 188);
    }

    Handler mFirstHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mainUpView1.setFocusView(item_wifi, mOldView, 1.1f);
        }
    };
}
