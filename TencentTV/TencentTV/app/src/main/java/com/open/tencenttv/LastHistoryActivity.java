package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;
import com.open.androidtvwidget.view.SmoothHorizontalScrollView;
import com.open.tencenttv.fragment.LastHistoryStickYGridHeaderFragment;

/**
 * ****************************************************************************************************************************************************************************
 * 最近观看
 *
 * @author :fengguangjing
 * @createTime: 16/11/10
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class LastHistoryActivity extends CommonFragmentActivity implements LastHistoryStickYGridHeaderFragment.Callbacks {

    private ReflectItemView item_login_adv;//登陆有礼 图片广告
    private ReflectItemView item_member;//影视会员
    private ReflectItemView item_sign;//签到抽大奖
    private ReflectItemView item_shop;//积分商城
    private ReflectItemView item_help;//帮助与建议

    //scrollview
    private SmoothHorizontalScrollView hscroll_view;
    private FrameMainLayout main_lay11;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_history);
        init();
    }


    Handler mFirstHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mainUpView1.setFocusView(item_login_adv,mOldView,1.1f);
        }
    };

    /**
     * Callback method from {@link LastHistoryStickYGridHeaderFragment.Callbacks} indicating that
     * the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int id) {
         Log.i(TAG,"onItemSelected == "+id);
    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        item_login_adv = (ReflectItemView) findViewById(R.id.item_login_adv);
        item_member = (ReflectItemView) findViewById(R.id.item_member);
        item_sign = (ReflectItemView) findViewById(R.id.item_sign);
        item_shop = (ReflectItemView) findViewById(R.id.item_shop);
        item_help = (ReflectItemView) findViewById(R.id.item_help);

        hscroll_view = (SmoothHorizontalScrollView) findViewById(R.id.hscroll_view);
        main_lay11 = (FrameMainLayout) findViewById(R.id.main_lay);
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
        FragmentManager manager = getSupportFragmentManager();
        LastHistoryStickYGridHeaderFragment fragment = LastHistoryStickYGridHeaderFragment.newInstance(mainUpView1,mOldView,mRecyclerViewBridge);
        manager.beginTransaction().replace(R.id.item_recycler_history, fragment).commit();

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
}
