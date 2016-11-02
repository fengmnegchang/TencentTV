package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.fragment.PinDaoFragment;
import com.open.tencenttv.fragment.PinDaoListFragment;

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
public class ListFragmentPinDaoActivity extends FragmentActivity {
    private static final String TAG = ListFragmentPinDaoActivity.class.getSimpleName();
    private MainUpView mainUpView1;
    private LayoutInflater mInflater;
    private View mOldView;
    private EffectNoDrawBridge mRecyclerViewBridge;
    private FrameLayout frame_listview,frame_pindao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment_pindao);

        this.mInflater = LayoutInflater.from(getApplicationContext());
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        frame_listview = (FrameLayout) findViewById(R.id.frame_listview);
        frame_pindao = (FrameLayout) findViewById(R.id.frame_pindao);

        // 默认是 OpenEff...，建议使用 NoDraw... ...
        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
        mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setTranDurAnimTime(200);
        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距

        PinDaoListFragment leftFragment =  PinDaoListFragment.newInstance(mRecyclerViewBridge,mOldView);
        PinDaoFragment rightFragment = PinDaoFragment.newInstance("ListFragmentPinDaoActivity");


        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_listview, leftFragment).commit();
        manager.beginTransaction().replace(R.id.frame_pindao, rightFragment).commit();

    }


    public float getDimension(int id) {
        return getResources().getDimension(id);
    }
}
