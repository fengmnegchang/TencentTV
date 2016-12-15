/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午4:26:07
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.adapter.AllClassAdapter;
import com.open.tencenttv.bean.AllBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 所有类列表
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午4:26:07
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class TencentTVALLActivity extends CommonFragmentActivity implements OnItemClickListener,OnItemSelectedListener{
	private ListViewTV listview;
	private AllClassAdapter mAllClassAdapter;
	private List<AllBean> list = new ArrayList<AllBean>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.CommonFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tencent_tv_all);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.CommonFragmentActivity#findView()
	 */
	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		super.findView();
		weakReferenceHandler = new WeakActivityReferenceHandler(this);
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		listview = (ListViewTV) findViewById(R.id.listview);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();
		// 默认是 OpenEff...，建议使用 NoDraw... ...
		mainUpView1.setEffectBridge(new EffectNoDrawBridge());
		mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
		mRecyclerViewBridge.setTranDurAnimTime(200);
		mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
		mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距

		try {
			//这样就能获取ActivityInfo了，之后可以获得Activity的name  
			ActivityInfo[] activities = getPackageManager().getPackageInfo(getPackageName(), 
					PackageManager.GET_ACTIVITIES).activities;  
			list.clear();
			AllBean allBean;
			for(ActivityInfo info :activities){
				if(!TencentTVALLActivity.class.getName().equals(info.name)){
					allBean = new AllBean(info.name,info.name.replace(getPackageName(), ""));
					list.add(allBean);
				}
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		mAllClassAdapter = new AllClassAdapter(this, list);
		listview.setAdapter(mAllClassAdapter);
		
		weakReferenceHandler.sendEmptyMessageDelayed(1, 2000);
	}
 

	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseFragmentActivity#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handlerMessage(msg);
		if(msg.what==1){
			listview.setDefaultSelect(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.CommonFragmentActivity#bindEvent()
	 */
	@Override
	protected void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		listview.setOnItemClickListener(this);
		listview.setOnItemSelectedListener(this);
		listview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				// 失去焦点时，将子view还原
				Log.i(TAG,"listView item" + view.getId() + " ========onFocusChange " + b);
				if (!b) {
					for (int i = 0; i < listview.getChildCount(); i++) {
						View mvView = listview.getChildAt(i);
						mRecyclerViewBridge.setUnFocusView(mvView);
					}
				}

			}
		});
	}

 

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Log.i(TAG,"listView item" + view.getId() + ";postion=" + (int) id + " ========onItemClick ");
		if (view != null) {
			view.bringToFront();
			mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
			mOldView = view;
		}
		AllBean bean = list.get((int)id);
		if(bean!=null && !TencentTVALLActivity.class.getName().equals(bean.getClassName())){
			Intent intent = new Intent();
			intent.setClassName(getPackageName(), bean.getClassName());
			startActivity(intent);
		}
	}

  

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Log.i(TAG,"listView item" + view.getId() + ";postion=" + (int) id + " ========onItemSelected ");
		if (view != null) {
			view.bringToFront();
			mRecyclerViewBridge.setFocusView(view, mOldView, 1.1f);
			mOldView = view;
		}
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
	 */
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

 

}
