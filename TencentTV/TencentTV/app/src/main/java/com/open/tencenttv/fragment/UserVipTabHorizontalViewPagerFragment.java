package com.open.tencenttv.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.OpenTabHost;
import com.open.tencenttv.adapter.OpenTabTitleAdapter;
import com.open.tencenttv.adapter.RankPagerAdapter;
import com.open.tencenttv.bean.RankBean;
import com.open.tencenttv.json.RankJson;

/**
 * *****************************************************************************
 * *****************************************************************************
 * ****************** vip会员
 * 
 * @author :fengguangjing
 * @createTime: 16/11/18
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: 
 *               ****************************************************************
 *               ***************************************************************
 *               *********************************************
 */
public class UserVipTabHorizontalViewPagerFragment extends CommonTabHorizontalViewPagerFragment implements OpenTabHost.OnTabSelectListener {

	public static UserVipTabHorizontalViewPagerFragment newInstance(String url, String selectElement, String liElement, String astrElement, MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mEffectNoDrawBridge) {
		UserVipTabHorizontalViewPagerFragment fragment = new UserVipTabHorizontalViewPagerFragment();
		fragment.url = url;
		fragment.selectElement = selectElement;
		fragment.liElement = liElement;
		fragment.astrElement = astrElement;
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.mEffectNoDrawBridge = mEffectNoDrawBridge;
		return fragment;
	}

	@Override
	public void onCallback(RankJson result) {
		super.onCallback(result);
		titlerankList.clear();
		titlerankList.addAll(result.getList());
		// 初始化标题栏.
		titleList.clear();
		listRankFragment.clear();

		Fragment fragment = null;
		for (int i = 0; i < result.getList().size(); i++) {
			RankBean bean = result.getList().get(i);
			titleList.add(bean.getRankName());
			// 消息通知http://uc.video.qq.com/fcgi-bin/mymessage?otype=json&callback=jQuery19109621792710428871_1481684561390&low_login=1&pagesize=8&g_tk=175866709&cur=0&listtype=0&uin=624926379&list=1&plat=1&clean=1&_=1481684561400
			// 购买记录http://pay.video.qq.com/fcgi-bin/order?otype=json&callback=jQuery19109621792710428871_1481684561390&low_login=1&g_tk=175866709&t=1&uin=624926379&psize=100&pnum=1&_=1481684561401
			// 增片记录http://pay.video.qq.com/fcgi-bin/give_movie?otype=json&callback=jQuery19109621792710428871_1481684561390&low_login=1&g_tk=175866709&_t=3&uin=624926379&psize=50&pnum=1&_=1481684561402
			fragment = UserMyVipFragment.newInstance("", mainUpView1, mEffectNoDrawBridge, mNewFocus);
			listRankFragment.add(fragment);
		}
		mOpenTabTitleAdapter = new OpenTabTitleAdapter(getActivity(), titleList);
		mOpenTabHost.setAdapter(mOpenTabTitleAdapter);

		// mRankViewPagerAdapter = new
		// RankViewPagerAdapter(getActivity(),result.getTitlerankList());
		mRankPagerAdapter = new RankPagerAdapter(getChildFragmentManager(), listRankFragment, titleList);
		viewpager.setAdapter(mRankPagerAdapter);

		mFocusHandler.sendEmptyMessageDelayed(10, 5000);
	}

}
