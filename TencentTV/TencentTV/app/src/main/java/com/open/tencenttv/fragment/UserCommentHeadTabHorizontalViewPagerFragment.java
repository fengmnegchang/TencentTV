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
public class UserCommentHeadTabHorizontalViewPagerFragment extends CommonTabHorizontalViewPagerFragment implements OpenTabHost.OnTabSelectListener {
	public static final String TAG = UserCommentHeadTabHorizontalViewPagerFragment.class.getSimpleName();

	public static UserCommentHeadTabHorizontalViewPagerFragment newInstance(String url, String selectElement, String liElement, String astrElement, MainUpView mainUpView1, View mOldView,
			EffectNoDrawBridge mEffectNoDrawBridge) {
		UserCommentHeadTabHorizontalViewPagerFragment fragment = new UserCommentHeadTabHorizontalViewPagerFragment();
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
			if ("消息".equals(bean.getRankName())) {
				fragment = UserCommentTabHorizontalViewPagerFragment.newInstance(bean.getRankName(), mainUpView1, mNewFocus, mEffectNoDrawBridge);
			} else if ("短评".equals(bean.getRankName())) {
				// 短评
				// http://video.coral.qq.com/user/0/comment/v2?callback=mycomments&type=0&commentid=&pageflag=1&reqnum=10&_=1481532169966
				fragment = UserMyCommentFragment.newInstance(mainUpView1, mNewFocus, mEffectNoDrawBridge);
			} else {
				fragment = RankV4Fragment.newInstance();
			}
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
