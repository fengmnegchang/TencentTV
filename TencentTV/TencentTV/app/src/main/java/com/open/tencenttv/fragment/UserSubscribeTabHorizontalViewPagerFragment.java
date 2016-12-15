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
import com.open.tencenttv.utils.UrlUtils;

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
public class UserSubscribeTabHorizontalViewPagerFragment extends CommonTabHorizontalViewPagerFragment implements OpenTabHost.OnTabSelectListener {
	public static final String TAG = UserSubscribeTabHorizontalViewPagerFragment.class.getSimpleName();

	public static UserSubscribeTabHorizontalViewPagerFragment newInstance(String url, String selectElement, String liElement, String astrElement, MainUpView mainUpView1, View mOldView,
			EffectNoDrawBridge mEffectNoDrawBridge) {
		UserSubscribeTabHorizontalViewPagerFragment fragment = new UserSubscribeTabHorizontalViewPagerFragment();
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

		// 推荐订阅http://c.v.qq.com/vfollowlst?otype=json&pagenum=1&pagesize=5&callback=jQuery19105151766284214294_1481251654251&g_tk=1605338694&_=1481251654252
		// 球星球队http://like.video.qq.com/fcgi-bin/flw_new?otype=json&sn=FollowServer&cmd=2563&pidx=0&size=5&dtype=1&type=0&callback=jQuery19105069239718699954_1481264321368&g_tk=1605338694&_=1481264321379
		Fragment fragment = null;
		for (int i = 0; i < result.getList().size(); i++) {
			RankBean bean = result.getList().get(i);
			titleList.add(bean.getRankName());
			if (bean.getRankName().contains("用户")) {
				fragment = UserSubscribeFragment.newInstance(UrlUtils.TENCENT_U_SUBSCRIBE, mainUpView1, mEffectNoDrawBridge, mNewFocus);
			} else {
				fragment = UserStarTeamFragment.newInstance(UrlUtils.TENCENT_U_SUBSCRIBE, mainUpView1, mEffectNoDrawBridge, mNewFocus);
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
