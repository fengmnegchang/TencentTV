/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午2:12:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVWebViewActivity;
import com.open.tencenttv.adapter.SearchAdapter;
import com.open.tencenttv.bean.SearchBean;
import com.open.tencenttv.bean.StarInfo;
import com.open.tencenttv.json.SearchJson;
import com.open.tencenttv.utils.UrlUtils;
import com.open.tencenttv.widget.ScrollListViewTV;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午2:12:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchListFragment extends BaseV4Fragment<SearchJson> {
	public static final String TAG = SearchListFragment.class.getSimpleName();
	private ScrollListViewTV listViewTV;
	private SearchAdapter mSearchAdapter;
	private String url;
	private List<SearchBean> list = new ArrayList<SearchBean>();

	public static SearchListFragment newInstance(String url, MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
		SearchListFragment fragment = new SearchListFragment();
		fragment.url = url;
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_search_list, container, false);
		listViewTV = (ScrollListViewTV) view.findViewById(R.id.listview);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		doAsync(this, this, this);
		mSearchAdapter = new SearchAdapter(getActivity(), list);
		listViewTV.setAdapter(mSearchAdapter);
		mSearchAdapter.notifyDataSetChanged();
		listViewTV.setSelector(new ColorDrawable(Color.TRANSPARENT));
		listViewTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				/**
				 * 这里注意要加判断是否为NULL. 因为在重新加载数据以后会出问题.
				 */
				if (view != null) {
					mainUpView1.setFocusView(view, mOldView, 1.2f);
				}
				mOldView = view;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		listViewTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), "OnItemClickListener Item " + position, Toast.LENGTH_LONG).show();
				TencentTVWebViewActivity.startTencentTVWebViewActivity(getActivity(), list.get((int)id).getResult_figure_href());
			}
		});

	}

	// // 延时请求初始位置的item.
	Handler mFirstHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			listViewTV.setDefaultSelect(0);
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.tencenttv.BaseV4Fragment#call()
	 */
	@Override
	public SearchJson call() throws Exception {
		// TODO Auto-generated method stub
		SearchJson mCommonT = new SearchJson();
		List<SearchBean> list = parseSearchlist(url);
		mCommonT.setList(list);
		return mCommonT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.BaseV4Fragment#onCallback(com.open.tencenttv.bean.
	 * CommonT)
	 */
	@Override
	public void onCallback(SearchJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mSearchAdapter.notifyDataSetChanged();
		mFirstHandler.sendMessageDelayed(mFirstHandler.obtainMessage(), 188);

	}

	public ArrayList<SearchBean> parseSearchlist(String href) {
		ArrayList<SearchBean> list = new ArrayList<SearchBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);
			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("div.wrapper_main").first();
			Elements result_itemElements = masthead.select("div.result_item");

			/**
			 * <div class="result_item result_item_h" _oldstat=
			 * "action=3&qid=ZnMigsAE6y0x88YEiRFhxBEvTINrIw2TSKYMTiHQiM7giQ%5FLM5%2DG%2DA&platform=10201&uin=0&ival=2&ival1=18&ival2=1&ival4=1&ival5=110&ival7=11&ival8=0&ival9=0&sval=self&sval2=%E5%A6%82%E6%9E%9C%E8%9C%97%E7%89%9B%E6%9C%89%E7%88%B1%E6%83%85&sval4=10638823820231394308&sval5=l0022wd3x1u&sval8=duration%3D1714%26edit%3D0%26cur%3D0%26&sval7=smartbox%5Fabtest%3D1073741824%26hit%5Fcate%3D11%26query%5Ffrom%3D101%26"
			 * rep-tpl="true" _oldexpose=
			 * "action=103&platform=10201&qid=ZnMigsAE6y0x88YEiRFhxBEvTINrIw2TSKYMTiHQiM7giQ%5FLM5%2DG%2DA&ival5=110&sval=%E5%A6%82%E6%9E%9C%E8%9C%97%E7%89%9B%E6%9C%89%E7%88%B1%E6%83%85&sval2=%E5%A6%82%E6%9E%9C%E8%9C%97%E7%89%9B%E6%9C%89%E7%88%B1%E6%83%85&sval4=10638823820231394308"
			 * r-notemplate="true"> <a
			 * href="http://v.qq.com/x/page/l0022wd3x1u.html"
			 * class="figure result_figure" target="_blank"
			 * _stat="video:poster_h"> <img
			 * src="//puui.qpic.cn/qqvideo_ori/0/l0022wd3x1u_496_280/0"
			 * r-imgerr="h" alt="《如果蜗牛有爱情》18王子文cut" /> <span
			 * class="figure_caption"><span
			 * class="figure_info">0:28:34</span></span> </a>
			 * 
			 * <h2 class="result_title">
			 * <a href="http://v.qq.com/x/page/l0022wd3x1u.html" target="_blank"
			 * _stat="video:poster_h_title">《<em class="hl">如果蜗牛有爱情</em>
			 * 》18王子文cut</a></h2>
			 * 
			 * <div class="result_info"> <div class="info_line"> <div
			 * class="info_item info_item_odd"> <span class="label">时　间：</span>
			 * <span class="content">2016-12-05</span> </div> <div
			 * class="info_item info_item_odd"> <span class="label">播放量：</span>
			 * <span class="content">829</span> </div> </div>
			 * 
			 * <div class="info_line"> <div class="info_item"> <span
			 * class="label">主　题：</span> <span class="content"
			 * title="王凯王子文玩命缉凶">王凯王子文玩命缉凶</span> </div> </div> </div> </div>
			 */
			// 解析文件
			for (int i = 0; i < result_itemElements.size(); i++) {
				SearchBean bean = new SearchBean();
				try {
					try {
						Element aElement = result_itemElements.get(i).select("a.result_figure").first();
						String hrefurl = aElement.attr("href");
						Log.i(TAG, "i===" + i + ";hrefurl ===" + hrefurl);
						bean.setResult_figure_href(hrefurl);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						/**
						 * <img src=
						 * "//puui.qpic.cn/qqvideo_ori/0/l0022wd3x1u_496_280/0"
						 * r-imgerr="h" alt="《如果蜗牛有爱情》18王子文cut" />
						 */
						Element imgElement = result_itemElements.get(i).select("a.result_figure").first().select("img").first();
						String src = imgElement.attr("src");
						String alt = imgElement.attr("alt");
						Log.i(TAG, "i===" + i + ";src ===" + src + ";alt=" + alt);
						bean.setResult_figure_alt(alt);
						bean.setResult_figure_src(src);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						Element result_infoElement = result_itemElements.get(i).select("div.result_info").first();
						Elements info_itemElements = result_infoElement.select("div.info_item");
						if (info_itemElements != null && info_itemElements.size() > 0) {
							List<StarInfo> result_info = new ArrayList<StarInfo>();
							for (int y = 0; y < info_itemElements.size(); y++) {
								StarInfo info = new StarInfo();
								try {
									/**
									 * <span class="label">主　题：</span> <span
									 * class="content"
									 * title="王凯王子文玩命缉凶">王凯王子文玩命缉凶</span>
									 */
									Element spanlabel = info_itemElements.get(y).select("span.label").first();
									Element spancontent = info_itemElements.get(y).select("span.content").first();
									info.setListLeft(spanlabel.text());
									info.setListRight(spancontent.text());
									result_info.add(info);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
							bean.setResult_info(result_info);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void refreshHandlerMessage(String url) {
		doAsync(this, this, this);
	}

}
