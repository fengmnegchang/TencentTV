package com.open.tencenttv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.OpenTabHost;
import com.open.tencenttv.adapter.OpenTabTitleAdapter;
import com.open.tencenttv.adapter.RankPagerAdapter;
import com.open.tencenttv.json.RankJson;

/**
 * *****************************************************************************
 * *****************************************************************************
 * ******************
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
public class UserCommentTabHorizontalViewPagerFragment extends CommonTabHorizontalViewPagerFragment implements OpenTabHost.OnTabSelectListener {
	public static final String TAG = UserCommentTabHorizontalViewPagerFragment.class.getSimpleName();
	String rankName;

	public static UserCommentTabHorizontalViewPagerFragment newInstance(String rankName, MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mEffectNoDrawBridge) {
		UserCommentTabHorizontalViewPagerFragment fragment = new UserCommentTabHorizontalViewPagerFragment();
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.rankName = rankName;
		fragment.mEffectNoDrawBridge = mEffectNoDrawBridge;
		return fragment;
	}

	@Override
	public RankJson call() throws Exception {
		return null;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// 初始化标题栏.
		titleList.clear();
		listRankFragment.clear();

		Fragment fragment = null;
		titleList.add("回复消息");
		fragment = UserCommentFragment.newInstance(mainUpView1, view, mEffectNoDrawBridge);
		listRankFragment.add(fragment);

		titleList.add("赞消息");
		fragment = UserCommentUpFragment.newInstance(mainUpView1, view, mEffectNoDrawBridge);
		listRankFragment.add(fragment);

		titleList.add("举报消息");
		fragment = RankV4Fragment.newInstance();
		listRankFragment.add(fragment);

		mOpenTabTitleAdapter = new OpenTabTitleAdapter(getActivity(), titleList);
		mOpenTabHost.setAdapter(mOpenTabTitleAdapter);

		// mRankViewPagerAdapter = new
		// RankViewPagerAdapter(getActivity(),result.getTitlerankList());
		mRankPagerAdapter = new RankPagerAdapter(getChildFragmentManager(), listRankFragment, titleList);
		viewpager.setAdapter(mRankPagerAdapter);

		mFocusHandler.sendEmptyMessageDelayed(10, 5000);

	}

}
