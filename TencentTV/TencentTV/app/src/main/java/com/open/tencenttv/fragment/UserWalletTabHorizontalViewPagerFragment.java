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
public class UserWalletTabHorizontalViewPagerFragment extends CommonTabHorizontalViewPagerFragment implements OpenTabHost.OnTabSelectListener {
	public static final String TAG = UserWalletTabHorizontalViewPagerFragment.class.getSimpleName();

	public static UserWalletTabHorizontalViewPagerFragment newInstance(String url, String selectElement, String liElement,String astrElement,  MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mEffectNoDrawBridge) {
		UserWalletTabHorizontalViewPagerFragment fragment = new UserWalletTabHorizontalViewPagerFragment();
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
			// 我的钱包http://task.video.qq.com/fcgi-bin/gift_list?callback=jQuery19105061520853703716_1481610716027&sort=seckill&otype=json&platform=10&_=1481610716028
			// v币
			// http://buy.video.qq.com/fcgi-bin/paycheck?callback=jQuery19105061520853703716_1481610716023&cmd=59855&platform=2&pidx=0&show_type=1&size=10&otype=json&g_tk=250078081&_=1481610716024
			if (bean.getRankName().contains("我的钱包")) {
				fragment = UserGiftGridFragment.newInstance("", mainUpView1, mEffectNoDrawBridge, mNewFocus);
			} else {
				fragment = UserBillFragment.newInstance("", mainUpView1, mEffectNoDrawBridge, mNewFocus);
			}
			listRankFragment.add(fragment);
		}
		mOpenTabTitleAdapter = new OpenTabTitleAdapter(getActivity(), titleList);
		mOpenTabHost.setAdapter(mOpenTabTitleAdapter);
		
		mRankPagerAdapter = new RankPagerAdapter(getChildFragmentManager(), listRankFragment, titleList);
		viewpager.setAdapter(mRankPagerAdapter);
		mRankPagerAdapter.notifyDataSetChanged();

		mFocusHandler.sendEmptyMessageDelayed(10, 5000);
	}

}
