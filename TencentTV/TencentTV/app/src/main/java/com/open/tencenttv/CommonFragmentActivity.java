/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-10-26上午10:09:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;


/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-10-26上午10:09:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class CommonFragmentActivity extends BaseFragmentActivity  {
	public static final String TAG = CommonFragmentActivity.class.getSimpleName();
	public MainUpView mainUpView1;
	public LayoutInflater mInflater;
	public View mOldView;
	public EffectNoDrawBridge mRecyclerViewBridge;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(@Nullable Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.BaseActivity#findView()
	 */
	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		super.findView();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.BaseActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.BaseActivity#bindEvent()
	 */
	@Override
	protected void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.BaseActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
	}



	public float getDimension(int id) {
		return getResources().getDimension(id);
	}






}
