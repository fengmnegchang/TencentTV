//package com.open.tencenttv.fragment;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//import org.json.JSONObject;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.format.DateUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.newsinfo.BaseV4Fragment;
//import com.example.newsinfo.CommonFragmentActivity;
//import com.example.newsinfo.R;
//import com.example.newsinfo.UrlUtils;
//import com.example.newsinfo.activity.SettingsActivity;
//import com.example.newsinfo.activity.WebViewActivity;
//import com.example.newsinfo.adapter.NewsAdapter;
//import com.example.newsinfo.bean.CommonT;
//import com.example.newsinfo.bean.NewsBean;
//import com.example.newsinfo.json.NewsBeanJson;
//import com.google.gson.Gson;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//
///**
// *
// *****************************************************************************************************************************************************************************
// * 收藏
// *
// * @author :fengguangjing
// * @createTime:2016-10-28上午10:36:57
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class CollectionFragment extends BaseV4Fragment {
//	PullToRefreshListView mPullRefreshListView;
//	ArrayList<NewsBean> newsBeanList = new ArrayList<NewsBean>();
//	NewsAdapter mNewsAdapter;
//	NewsBean newsBean;
//
//	// 热点
//	String href = UrlUtils.PROFILE;
//	int pageNo = 1;
//	String JSONDataUrl = "http://www.yidianzixun.com/api/q?path=interact|get-like&fields=title&fields=image&fields=source&fields=date&fields=channel_name&&fields=summary&fields=comment_count&fields=url&fields=like&max-related=10&infinite=true&fields=is_like&always_summary=true&count=10&last_docid=0E0dtaGQ";
//
//	public static CollectionFragment newInstance(NewsBean newsBean) {
//		CollectionFragment fragment = new CollectionFragment();
//		fragment.newsBean = newsBean;
//		return fragment;
//	}
//
//	private String mContent = "";
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_collection_list, null);
//		mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
//		mPullRefreshListView.setMode(Mode.BOTH);
//		mNewsAdapter = new NewsAdapter(getActivity(), newsBeanList, mContent);
//		// Set a listener to be invoked when the list should be refreshed.
//		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
//			@Override
//			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//				String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
//				// Update the LastUpdatedLabel
//				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//				// Do work to refresh the list here.
//				if (mPullRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
//					// new GetDataTask().execute();
//					doAsync(CollectionFragment.this, CollectionFragment.this, CollectionFragment.this);
//				} else if (mPullRefreshListView.getCurrentMode() == Mode.PULL_FROM_END) {
//					volleyJson();
//				}
//			}
//		});
//		mPullRefreshListView.setAdapter(mNewsAdapter);
//		mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Intent intent = new Intent();
//				intent.setClass(getActivity(), WebViewActivity.class);
//				intent.putExtra("NEWSBEAN", newsBeanList.get((int) id));
//				intent.putExtra("TITLE", mContent);
//				startActivity(intent);
//			}
//		});
//		// Do work to refresh the list here.
//		return view;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.example.newsinfo.BaseV4Fragment#call()
//	 */
//	@Override
//	public CommonT call() throws Exception {
//		// TODO Auto-generated method stub
//		// Simulates a background job.
//		CommonT mCommonT= new CommonT();
//		ArrayList<NewsBean> list = new ArrayList<NewsBean>();
//		try {
//			// 解析网络标签
//			list = parseList(href);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		mCommonT.setNewsBeanList(list);
//		return mCommonT;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * com.example.newsinfo.BaseV4Fragment#onCallback(com.example.newsinfo.bean
//	 * .NewsBean[])
//	 */
//	@Override
//	public void onCallback(CommonT result) {
//		// TODO Auto-generated method stub
//		super.onCallback(result);
//		Log.i(TAG, "getMode ===" + mPullRefreshListView.getCurrentMode());
//		if (mPullRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
//			newsBeanList.clear();
//			newsBeanList.addAll(result.getNewsBeanList());
//			pageNo = 1;
//		}
//		mNewsAdapter.notifyDataSetChanged();
//		// Call onRefreshComplete when the list has been refreshed.
//		mPullRefreshListView.onRefreshComplete();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.example.newsinfo.BaseV4Fragment#onCallEarliest()
//	 */
//	@Override
//	public void onCallEarliest() throws Exception {
//		// TODO Auto-generated method stub
//		super.onCallEarliest();
//	}
//
//	// private class GetDataTask extends AsyncTask<Void, Void, NewsBean[]> {
//	//
//	// @Override
//	// protected NewsBean[] doInBackground(Void... params) {
//	// // Simulates a background job.
//	// ArrayList<NewsBean> list = new ArrayList<NewsBean>();
//	// try {
//	// // 解析网络标签
//	// list = parseList(href);
//	// } catch (Exception e) {
//	// e.printStackTrace();
//	// }
//	// return list.toArray(new NewsBean[0]);
//	// }
//	//
//	// @Override
//	// protected void onPostExecute(NewsBean[] result) {
//	// Log.i(TAG, "getMode ===" + mPullRefreshListView.getCurrentMode());
//	// if (mPullRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
//	// newsBeanList.clear();
//	// newsBeanList.addAll(Arrays.asList(result));
//	// pageNo = 1;
//	// }
//	// mNewsAdapter.notifyDataSetChanged();
//	// // Call onRefreshComplete when the list has been refreshed.
//	// mPullRefreshListView.onRefreshComplete();
//	// super.onPostExecute(result);
//	// }
//	// }
//
//	/**
//	 * 请求网络数据
//	 * **http://www.yidianzixun.com/api/q/?path=channel|news-list-for-keyword
//	 * &display
//	 * =%E7%83%AD%E7%82%B9&word_type=token&fields=docid&fields=category&fields
//	 * =date
//	 * &fields=image&fields=image_urls&fields=like&fields=source&fields=title
//	 * &fields=url&fields=comment_count&fields=summary&fields=up
//	 * &cstart=10&cend=20&version=999999&infinite=true cstart=20&cend=30
//	 */
//	public void volleyJson() {
//		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//		if (pageNo > 0) {
//			JSONDataUrl = JSONDataUrl + "&cstart=" + pageNo * 10 + "&cend=" + (pageNo + 1) * 10;
//		}
//		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSONDataUrl, SettingsActivity.getHeaders(), null, this, this);
//		requestQueue.add(jsonObjectRequest);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.example.newsinfo.BaseV4Fragment#onResponse(org.json.JSONObject)
//	 */
//	@Override
//	public void onResponse(JSONObject response) {
//		// TODO Auto-generated method stub
//		super.onResponse(response);
//		System.out.println("response=" + response);
//		Gson gson = new Gson();
//		NewsBeanJson mNewsBeanJson = gson.fromJson(response.toString(), NewsBeanJson.class);
//		if (mNewsBeanJson != null && mNewsBeanJson.getResult() != null && mNewsBeanJson.getResult().size() > 0) {
//			newsBeanList.addAll(mNewsBeanJson.getResult());
//			pageNo++;
//			mNewsAdapter.notifyDataSetChanged();
//		}
//		mPullRefreshListView.onRefreshComplete();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * com.example.newsinfo.BaseV4Fragment#onErrorResponse(com.android.volley
//	 * .VolleyError)
//	 */
//	@Override
//	public void onErrorResponse(VolleyError error) {
//		// TODO Auto-generated method stub
//		super.onErrorResponse(error);
//		System.out.println("sorry,Error");
//		mPullRefreshListView.onRefreshComplete();
//	}
//
//	public ArrayList<NewsBean> parseList(String href) {
//		ArrayList<NewsBean> list = new ArrayList<NewsBean>();
//		try {
//			href = ((CommonFragmentActivity) getActivity()).makeURL(href, new HashMap<String, Object>() {
//				{
//				}
//			});
//			Log.i("url", "url = " + href);
//
//			/**
//			 * <div class="article" data-docid="{{docid}}"> {{{oneimg}}} <div
//			 * class="{{floatcls}}"> <h3>
//			 * <a href="/home?page=article&id={{docid}}&up={{up}}"
//			 * target="_blank">{{title}}</a></h3> {{{mulimg}}} <div
//			 * class="article-info"> <div class="article-opts"> <div
//			 * data-docid="{{docid}}" data-datatype="0" class="slide-del"></div>
//			 * {{{likestr}}} <a
//			 * href="/home?page=article&id={{docid}}&up={{up}}#comment"
//			 * class="slide-comment" target="_blank">{{comment_count}}</a> <div
//			 * data-sharetitle="{{title}}" class="slide-share"
//			 * data-shareimage="{{shareimage}}" data-shareurl=
//			 * "http://www.yidianzixun.com/article/{{docid}}?s=4")>转发</div>
//			 * </div> <span class="article-source">{{source}}</span> <span
//			 * class="article-date">{{date}}</span> <div class="clear"></div>
//			 * </div> </div> <div class="clear"></div> </div>
//			 */
//
//			Document doc = Jsoup.connect(href).userAgent(SettingsActivity.userAgent).cookies(SettingsActivity.getCookies()).timeout(10000).get();
//			Element masthead = doc.select("div.main-section").first();
//			Elements beanElements = masthead.select("li.article");
//
//			/**
//			 * <li class="article" data-docid="{{docid}}">
//			 * <div class="like-hd"> <span class="like"></span> <span
//			 * class="date">{{date}}</span> </div> <div class="like-bd">
//			 * {{{oneimg}}} <div class="article-nofloat">
//			 * <h3>
//			 * <a href="/home?page=article&id={{docid}}&up={{up}}"
//			 * target="_blank">{{title}}</a></h3>
//			 * <p>
//			 * {{summary}}
//			 * </p>
//			 * <div class="article-info"> <div class="article-opts"> <div
//			 * data-docid="{{docid}}" data-datatype="8" class="slide-del"></div>
//			 * <div data-docid="{{docid}}" data-datatype="0"
//			 * class="slide-like slide-liked">{{like}}</div> <a
//			 * href="/home?page=article&id={{docid}}&up={{up}}#comment"
//			 * target="_blank" class="slide-comment"></a> <div
//			 * data-sharetitle="{{title}}" data-shareimage="{{shareimg}}"
//			 * data-shareurl="http://www.yidianzixun.com/article/{{docid}}?s=4"
//			 * class="slide-share">转发</div> </div> <span
//			 * class="article-source">{{source}}</span> <div
//			 * class="clear"></div> </div> </div> <div class="clear"></div>
//			 * </div></li>
//			 */
//			// 解析文件
//			for (int i = 0; i < beanElements.size(); i++) {
//				NewsBean bean = new NewsBean();
//				try {
//					// 图片
//					Element imageElement = beanElements.get(i);
//					Element astyle = imageElement.select("a").first();
//					String nexthref = UrlUtils.YI_DIAN_ZI_XUN + astyle.attr("href");
//					// 3imageurl =
//					// background-image:url('http://i1.go2yd.com/image.php?url=http://si1.go2yd.com/get-image/07pb1QpTNho&type=thumbnail_200x140');
//					// ;nexthref =
//					// http://www.yidianzixun.com//home?page=article&id=0Ehnnyy6&up=403
//					Log.i(TAG, i + "nexthref = " + nexthref);
//					bean.setUrl(nexthref);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				try {
//					// 图片
//					Element imageElement = beanElements.get(i);
//					Elements aElements = imageElement.select("a");
//
//					// <a
//					// style="background-image:url(http://i1.go2yd.com/image.php?url=http://static.yidianzixun.com/beauty/imgs/i_000cNMqU.jpg&amp;type=thumbnail_200x140);"
//					ArrayList<String> imgurlList = new ArrayList<String>();
//					for (int y = 0; y < aElements.size(); y++) {
//						Element astyle = aElements.get(y);
//						// 5=====0imageurl =
//						String imageurl = astyle.attr("style");
//						if (imageurl != null && imageurl.length() >= 0 && imageurl.contains("background-image")) {
//							Log.i(TAG, i + "=====" + y + "imageurl=" + imageurl);
//							imgurlList.add(imageurl.replace("background-image:url(", "").replace(");", ""));
//						}
//					}
//					bean.setImage_urls(imgurlList);
//					/**
//					 * <li data-docid="0ElbPwQA" class="article"><div
//					 * class="like-hd"><span class="like"></span><span
//					 * class="date">2016-10-27 14:14:05</span></div><div
//					 * class="like-bd"><a style=
//					 * "background-image:url(http://i1.go2yd.com/image.php?url=http://static.yidianzixun.com/beauty/imgs/i_000cNMqU.jpg&amp;type=thumbnail_200x140);"
//					 * href="/home?page=article&amp;id=0ElbPwQA&amp;up=3629"
//					 * target="_blank" class="article-img"></a><div
//					 * class="article-nofloat">
//					 * <h3><a
//					 * href="/home?page=article&amp;id=0ElbPwQA&amp;up=3629"
//					 * target="_blank">短裤有魔力，荷尔蒙瞬间被唤醒了</a></h3><div
//					 * class="article-info"><div class="article-opts"><div
//					 * data-docid="0ElbPwQA" data-datatype="8"
//					 * class="slide-del"></div><div data-docid="0ElbPwQA"
//					 * data-datatype="0"
//					 * class="slide-like slide-liked">58</div><a href=
//					 * "/home?page=article&amp;id=0ElbPwQA&amp;up=3629#comment"
//					 * target="_blank" class="slide-comment">8</a><div
//					 * data-sharetitle="短裤有魔力，荷尔蒙瞬间被唤醒了" data-shareimage=""
//					 * data-shareurl=
//					 * "http://www.yidianzixun.com/article/0ElbPwQA?s=4"
//					 * class="slide-share">转发</div></div><span
//					 * class="article-source">一点资讯</span><div
//					 * class="clear"></div></div></div><div
//					 * class="clear"></div></div></li>
//					 **/
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				try {
//					// 标题
//					Element titleElement = beanElements.get(i);
//					Element h3 = titleElement.select("h3 a").first();
//					String title = h3.text();
//					Log.i(TAG, i + "title = " + title);
//					bean.setTitle(title);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				try {
//					// 内容
//					Element contentElement = beanElements.get(i);
//					Element p = contentElement.select("div.article-nofloat p").first();
//					String content = p.text();
//					Log.i(TAG, i + "content = " + content);
//					bean.setSummary(content);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				try {
//					// 其他
//					Element otherElement = beanElements.get(i);
//					Element otherE = otherElement.select("div.article-info").first();
//					String other = otherE.text();
//					Log.i(TAG, i + "other = " + other);
//					bean.setOther(other);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				list.add(bean);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see android.support.v4.app.Fragment#setUserVisibleHint(boolean)
//	 */
//	@Override
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		// TODO Auto-generated method stub
//		super.setUserVisibleHint(isVisibleToUser);
//		initUI(isVisibleToUser);
//	}
//
//	@Override
//	protected void initUI(final boolean isVisibleToUser) {
//		super.initUI(isVisibleToUser);
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				if (mPullRefreshListView == null || getActivity() == null || !isVisibleToUser) {
//					initUI(isVisibleToUser);
//				} else {
//					mPullRefreshListView.setRefreshing(true);
//				}
//			}
//		}, 500);
//	}
//}
