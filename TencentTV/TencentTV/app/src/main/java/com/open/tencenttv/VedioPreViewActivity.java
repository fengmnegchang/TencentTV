package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.fragment.VedioPreViewFragment;

/**
 * ****************************************************************************************************************************************************************************
 * 视频预览
 *
 * @author :fengguangjing
 * @createTime: 2016/11/5
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class VedioPreViewActivity  extends FragmentActivity {
    private static final String TAG = VedioPreViewActivity.class.getSimpleName();
    private MainUpView mainUpView1;
    private LayoutInflater mInflater;
    private View mOldView;
    private EffectNoDrawBridge mRecyclerViewBridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedion_preview);
        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        // 默认是 OpenEff...，建议使用 NoDraw... ...
        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
        mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setTranDurAnimTime(200);
        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距

        //设置fragment
        FragmentManager manager = getSupportFragmentManager();
        VedioPreViewFragment fragment = VedioPreViewFragment.newInstance(mainUpView1,mOldView,mRecyclerViewBridge);
        manager.beginTransaction().replace(R.id.layout_vedio_preview, fragment).commit();

    }



    public float getDimension(int id) {
        return getResources().getDimension(id);
    }

}
