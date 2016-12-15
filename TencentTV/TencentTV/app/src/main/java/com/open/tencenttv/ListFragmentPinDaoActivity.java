package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.fragment.PinDaoListFragment;
import com.open.tencenttv.fragment.PinDaoTabHorizontalViewPagerFragment;
import com.open.tencenttv.utils.UrlUtils;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class ListFragmentPinDaoActivity extends CommonFragmentActivity {
    private FrameLayout frame_listview, frame_pindao;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment_pindao);
        init();

    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        frame_listview = (FrameLayout) findViewById(R.id.frame_listview);
        frame_pindao = (FrameLayout) findViewById(R.id.frame_pindao);
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


        PinDaoListFragment leftFragment = PinDaoListFragment.newInstance(mainUpView1, mRecyclerViewBridge, mOldView);
//        PinDaoFragment rightFragment = PinDaoFragment.newInstance(UrlUtils.TENCENT_X_MOVIE_LIST,"电影", mainUpView1, mOldView, mRecyclerViewBridge);
        PinDaoTabHorizontalViewPagerFragment rightFragment = PinDaoTabHorizontalViewPagerFragment.newInstance(UrlUtils.TENCENT_X_MOVIE_LIST,"ul.filter_tabs","li","a","电影", mainUpView1, mOldView, mRecyclerViewBridge);


        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_listview, leftFragment).commit();
        manager.beginTransaction().replace(R.id.frame_pindao, rightFragment).commit();
    }


}
