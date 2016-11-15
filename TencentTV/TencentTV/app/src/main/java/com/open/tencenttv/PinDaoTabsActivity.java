//package com.open.tencenttv;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.util.Log;
//
//import com.example.newsinfo.CommonFragmentActivity;
//import com.example.newsinfo.R;
//import com.example.newsinfo.UrlUtils;
//import com.example.newsinfo.bean.CommonT;
//import com.example.newsinfo.bean.NewsBean;
//import com.example.newsinfo.fragment.PinDaoFragment;
//import com.example.newsinfo.indicator.TabPageIndicator;
//
///**
// *
// *****************************************************************************************************************************************************************************
// * 频道页面
// *
// * @author :fengguangjing
// * @createTime:2016-10-28上午10:35:55
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class PinDaoTabsActivity extends CommonFragmentActivity {
//	public static final String TAG = PinDaoTabsActivity.class.getSimpleName();
//	ArrayList<NewsBean> channelList = new ArrayList<NewsBean>();
//	FragmentPagerAdapter adapter;
//	TabPageIndicator indicator;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setCommonActivityLeftCanBack(false);
//		setCommonActivityCenterEditSearch(false);
//		setCommonActivityRightSearch(false);
//
//		addContentView(R.layout.activity_pindao_tabs, UrlUtils.STATUS_TAB_ACTIVITY_MARGIN_TOP);
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.example.newsinfo.CommonActivity#findView()
//	 */
//	@Override
//	protected void findView() {
//		// TODO Auto-generated method stub
//		super.findView();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.example.newsinfo.CommonActivity#initValue()
//	 */
//	@Override
//	protected void initValue() {
//		// TODO Auto-generated method stub
//		super.initValue();
//		text_title.setText("频道");
//		setRightNone();
//		setLeftNone();
//
//		adapter = new GoogleMusicAdapter(getSupportFragmentManager());
//		ViewPager pager = (ViewPager) findViewById(R.id.pager);
//		pager.setAdapter(adapter);
//
//		indicator = (TabPageIndicator) findViewById(R.id.indicator);
//		indicator.setViewPager(pager);
//
//		// new GetDataTask().execute();
//		doAsync(this, this, this);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.example.newsinfo.CommonActivity#bindEvent()
//	 */
//	@Override
//	protected void bindEvent() {
//		// TODO Auto-generated method stub
//		super.bindEvent();
//	}
//
//	class GoogleMusicAdapter extends FragmentPagerAdapter {
//		public GoogleMusicAdapter(FragmentManager fm) {
//			super(fm);
//		}
//
//		@Override
//		public Fragment getItem(int position) {
//			return PinDaoFragment.newInstance(channelList.get(position));
//		}
//
//		@Override
//		public CharSequence getPageTitle(int position) {
//			return channelList.get(position).getTitle();
//		}
//
//		@Override
//		public int getCount() {
//			return channelList.size();
//		}
//	}
//
//	@Override
//	public CommonT call() throws Exception {
//		// TODO Auto-generated method stub
//		CommonT mCommonT = new CommonT();
//		ArrayList<NewsBean> list = new ArrayList<NewsBean>();
//		try {
//			// 解析网络标签
//			list = parseList(UrlUtils.CHANNEL_LIST);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		mCommonT.setNewsBeanList(list);
//		return mCommonT;
//	}
//
//	@Override
//	public void onCallback(CommonT result) {
//		// TODO Auto-generated method stub
//		super.onCallback(result);
//		channelList.addAll(result.getNewsBeanList());
//		adapter.notifyDataSetChanged();
//		indicator.notifyDataSetChanged();
//	}
//
//	public ArrayList<NewsBean> parseList(String href) {
//		ArrayList<NewsBean> list = new ArrayList<NewsBean>();
//		try {
//			href = makeURL(href, new HashMap<String, Object>() {
//				{
//				}
//			});
//			Log.i("url", "url = " + href);
//
//			Document doc = Jsoup.connect(href).userAgent(SettingsActivity.userAgent).cookies(SettingsActivity.getCookies()).timeout(10000).get();
//			Element masthead = doc.select("div.channellist").first();
//			Elements beanElements = masthead.select("div.cate-box");
//
//			/**
//			 * <div class="cate-box"><h3>热门</h3>
//			 */
//			// 解析文件
//			for (int i = 0; i < beanElements.size(); i++) {
//				NewsBean bean = new NewsBean();
//				try {
//					Element imageElement = beanElements.get(i).select("h3").first();
//					String title = imageElement.text();
//					Log.i(TAG, i + "title = " + title);
//					bean.setTitle(title);
//
//
//					Element dataElement = beanElements.get(i).select("div.show-more").first();
//					String jsondataurl = UrlUtils.YI_DIAN_ZI_XUN + "/home/q?type=getmorechannels&cid="+dataElement.attr("data-cid");
//					Log.i(TAG, i + "jsondataurl = " + jsondataurl);
//					bean.setJsondataurl(jsondataurl);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				list.add(bean);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//}
