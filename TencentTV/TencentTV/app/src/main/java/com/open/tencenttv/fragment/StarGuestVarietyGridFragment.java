/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-6下午5:41:12
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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.GridViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.StarGuestVarietyGridViewAdapter;
import com.open.tencenttv.bean.StarGuestVarietyBean;
import com.open.tencenttv.json.StarGuestVarietyJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 明星 综艺
 * @author :fengguangjing
 * @createTime:2016-12-6下午5:41:12
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class StarGuestVarietyGridFragment extends BaseV4Fragment<StarGuestVarietyJson> {
	private String url;
	private GridViewTV gridViewTV;
	private StarGuestVarietyGridViewAdapter mStarGuestVarietyGridViewAdapter;
	private List<StarGuestVarietyBean> list = new ArrayList<StarGuestVarietyBean>();

	public static StarGuestVarietyGridFragment newInstance(String url, MainUpView mainUpView1, EffectNoDrawBridge mRecyclerViewBridge, View mOldView) {
		StarGuestVarietyGridFragment fragment = new StarGuestVarietyGridFragment();
		fragment.url = url;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		fragment.mainUpView1 = mainUpView1;
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_star_guest_variety_grid, container, false);
		gridViewTV = (GridViewTV) view.findViewById(R.id.gridview);
		doAsync(this, this, this);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mStarGuestVarietyGridViewAdapter = new StarGuestVarietyGridViewAdapter (getActivity(),list);
		gridViewTV.setAdapter(mStarGuestVarietyGridViewAdapter);
	}

	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4Fragment#call()
	 */
	@Override
	public StarGuestVarietyJson call() throws Exception {
		// TODO Auto-generated method stub
		StarGuestVarietyJson mStarGuestVarietyJson = new StarGuestVarietyJson();
		List<StarGuestVarietyBean> list = parseStarGuestVariety(url);
		mStarGuestVarietyJson.setList(list);
		return mStarGuestVarietyJson;
	}

	

	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4Fragment#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(StarGuestVarietyJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		list.clear();
		list.addAll(result.getList());
		mStarGuestVarietyGridViewAdapter.notifyDataSetChanged();
	}
	
	private List<StarGuestVarietyBean> parseStarGuestVariety(String href) {
		List<StarGuestVarietyBean> list = new ArrayList<StarGuestVarietyBean>();
		try {
			href = makeURL(href, new HashMap<String, Object>() {
				{
				}
			});
			Log.i(TAG, "url = " + href);
			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			Element masthead = doc.select("div.video_tab").first();
			Elements liElements = masthead.select("li.feed_item");
			/**
			 *  <li class="feed_item" data-id="4x7rdcy3g8n0asu" data-comp="variety-guest" data-boss="poster">
    <a href="http://v.qq.com/cover/4/4x7rdcy3g8n0asu.html" target="_blank" title="综艺大爆炸" class="figure">
        <img alt="综艺大爆炸" 
        	r-imgerror="hori"
        	r-lazyload="http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/hori/4/4x7rdcy3g8n0asu_b.jpg"
        >
        <span class="figure_mask"><em class="mask_txt">2016-11-14</em></span>
    </a>
    <p class="figure_desc">综艺大爆炸</p>
			 */
			// 解析文件
			for (int i = 0; i < liElements.size(); i++) {
				StarGuestVarietyBean bean = new StarGuestVarietyBean();
				try {

					try {
						Element aElement = liElements.get(i).select("a").first();
						String feed_item_title = aElement.attr("title");
						String feed_item_href = aElement.attr("href");
						Log.i(TAG, "i==" + i + ";feed_item_title==" + feed_item_title+";feed_item_href="+feed_item_href);
						bean.setFeed_item_title(feed_item_title);
						bean.setFeed_item_href(feed_item_href);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						Element imgElement = liElements.get(i).select("img").first();
						String r_lazyload = imgElement.attr("r-lazyload");
						String feed_item_title = imgElement.attr("alt");
						if(feed_item_title!=null){
							bean.setFeed_item_title(feed_item_title);
						}
						if(r_lazyload==null){
							r_lazyload = imgElement.attr("src");
						}
						if(r_lazyload==null){
							r_lazyload = imgElement.attr("data-src");
						}
						Log.i(TAG, "i==" + i + ";r_lazyload==" + r_lazyload);
						bean.setR_lazyload(r_lazyload);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						Element spanElement = liElements.get(i).select("span.figure_mask").first();
						String figure_mask = spanElement.text();
						Log.i(TAG, "i==" + i + ";figure_mask==" + figure_mask);
						bean.setFigure_mask(figure_mask);
					} catch (Exception e) {
						e.printStackTrace();
					}
 
					list.add(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
