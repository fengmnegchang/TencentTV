package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.bean.RankBean;
import com.open.tencenttv.fragment.RankListFragment;
import com.open.tencenttv.fragment.RankTabHeadHorizontalViewPagerFragment;
import com.open.tencenttv.fragment.RankTabHorizontalViewPagerFragment;
import com.open.tencenttv.utils.UrlUtils;

/**
 * ****************************************************************************************************************************************************************************
 * 播放排行
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class ListFragmentRankActivity extends CommonFragmentActivity {
    private FrameLayout frame_listview, frame_rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment_rank);
        init();

    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        frame_listview = (FrameLayout) findViewById(R.id.frame_listview);
        frame_rank = (FrameLayout) findViewById(R.id.frame_rank);
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


        RankListFragment leftFragment = RankListFragment.newInstance(mainUpView1, mRecyclerViewBridge, mOldView);
        RankTabHeadHorizontalViewPagerFragment rightFragment = RankTabHeadHorizontalViewPagerFragment.newInstance(UrlUtils.TENCENT_RANK_ALL_URL,mainUpView1, mOldView, mRecyclerViewBridge);


        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_listview, leftFragment).commit();
        manager.beginTransaction().replace(R.id.frame_rank, rightFragment).commit();
    }


}
