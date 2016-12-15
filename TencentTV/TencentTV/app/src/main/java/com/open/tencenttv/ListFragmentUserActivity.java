/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:21:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.fragment.RankV4Fragment;
import com.open.tencenttv.fragment.UserListFragment;

/**
 ***************************************************************************************************************************************************************************** 
 * 用户中心 钱包、会员、观看历史
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:21:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class ListFragmentUserActivity extends CommonFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_fragment_user);
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

		UserListFragment leftFragment = UserListFragment.newInstance(mainUpView1, mRecyclerViewBridge, mOldView);
		RankV4Fragment rightFragment = RankV4Fragment.newInstance();

		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.frame_listview, leftFragment).commit();
		manager.beginTransaction().replace(R.id.frame_user, rightFragment).commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		FragmentManager fm = getSupportFragmentManager();
		BaseV4Fragment fragment = (BaseV4Fragment) fm.findFragmentById(R.id.frame_user);
		if(fragment==null || !fragment.onBackPressed()){
			super.onBackPressed();
		}
	}

}
