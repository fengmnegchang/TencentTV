package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.fragment.MediumDirectionViewPagerFragment;
import com.open.tencenttv.fragment.MediumHorizontalViewPagerFragment;
import com.open.tencenttv.utils.UrlUtils;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/17
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class MediumFragmentDirectionActivity extends CommonFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_fragment_direction);
        init();
    }

    @Override
    protected void findView() {
        super.findView();
        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
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

        MediumDirectionViewPagerFragment fragment = MediumDirectionViewPagerFragment.newInstance(UrlUtils.TENCENT_TV_URL,mainUpView1,mOldView,mRecyclerViewBridge);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.lay_direction, fragment).commit();
    }


}
