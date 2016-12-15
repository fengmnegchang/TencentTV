package com.open.tencenttv.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.RankFragmentAdapter;
import com.open.tencenttv.bean.RankBean;
import com.open.tencenttv.json.RankJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 * *****************************************************************************
 * *****************************************************************************
 * ****************** 播放排行榜
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
public class RankAllFragment extends BaseV4Fragment<RankJson> {
	private String rankname;
	private List<RankBean> data = new ArrayList<RankBean>();
	private ListViewTV mListViewTV;
	private RankFragmentAdapter mAdapter;
	String title;

	public static RankAllFragment newInstance(String title, String rankname, MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
		RankAllFragment fragment = new RankAllFragment();
		fragment.rankname = rankname;
		fragment.title = title;
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_rank, container, false);
		mListViewTV = (ListViewTV) view.findViewById(R.id.listview);
		doAsync(this, this, this);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mAdapter = new RankFragmentAdapter(getActivity(), data);
		mListViewTV.setAdapter(mAdapter);
	}

	@Override
	public RankJson call() throws Exception {
		RankJson mCommonT = new RankJson();
		List<RankBean> list = parseRankList(UrlUtils.TENCENT_RANK_ALL_URL);
		mCommonT.setList(list);
		return mCommonT;
	}

	@Override
	public void onCallback(RankJson result) {
		super.onCallback(result);
		data.clear();
		data.addAll(result.getList());
		mAdapter.notifyDataSetChanged();
		// // 延时请求其它位置的item.
		// Handler handler = new Handler() {
		// @Override
		// public void handleMessage(Message msg) {
		// mListViewTV.requestFocusFromTouch();
		// mListViewTV.setSelection(0);
		// }
		// };
		// handler.sendMessageDelayed(handler.obtainMessage(), 188);
	}

	public ArrayList<RankBean> parseRankList(String href) {
		ArrayList<RankBean> list = new ArrayList<RankBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);

			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("div.container_inner").first();
			Elements divElements = masthead.select("div.wrapper_rank ");
			if (divElements != null && divElements.size() > 0) {
				for (int y = 0; y < divElements.size(); y++) {
					Element h2Elements = divElements.get(y).select("h2.rank_title").first();
					if (h2Elements.text().equals(title)) {
						Elements ulElements = divElements.get(y).select("ul.rank_list");
						if (ulElements != null && ulElements.size() > 0) {
							Elements liElements = divElements.get(y).select("li");
							int start = 1;
							int end = 10;
							if (rankname.equals("欧美")) {
								 start = 12;
								 end = 21;
							}else if (rankname.equals("大陆")) {
								 start = 1;
								 end = 10;
							}else if (rankname.equals("港台")) {
								 start = 23;
								 end = 32;
							}else if (rankname.equals("日韩")) {
								 start = 34;
								 end = 43;
							}
							for (int i = start; i <=end; i++) {
							RankBean bean = new RankBean();
							try {
								try {
									Element aElement = liElements.get(i).select("a").first();
									String hrefurl = aElement.attr("href");
									String title = aElement.attr("title");
									Log.i(TAG,"y===" + y + ";i===" + i + ";hrefurl ===" + hrefurl + ";title===" + title);
									bean.setActorName(title);
									bean.setRankurl(hrefurl);
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element iElement = liElements.get(i).select("i.num").first();
									String count = iElement.text();
									Log.i(TAG,"y===" + y + ";i===" + i + ";count ===" + count);
									bean.setCount(count);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							list.add(bean);
						}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
