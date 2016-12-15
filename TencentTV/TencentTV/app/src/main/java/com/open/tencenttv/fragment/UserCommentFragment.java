package com.open.tencenttv.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.UserCommentAdapter;
import com.open.tencenttv.bean.ArticleBean;
import com.open.tencenttv.bean.CommentBean;
import com.open.tencenttv.bean.MessageBean;
import com.open.tencenttv.utils.UrlUtils;

/**
 * *****************************************************************************
 * *****************************************************************************
 * ******************
 * 回复消息
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
public class UserCommentFragment extends BaseV4Fragment {
	private UserCommentAdapter mUserCommentAdapter;
	private List<MessageBean> list = new ArrayList<MessageBean>();
	private ListViewTV listViewTV;

	public static Fragment newInstance(MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
		UserCommentFragment fragment = new UserCommentFragment();
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_listview, container, false);
		listViewTV = (ListViewTV) view.findViewById(R.id.listview);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 * android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mUserCommentAdapter = new UserCommentAdapter(getActivity(), list);
		listViewTV.setAdapter(mUserCommentAdapter);

		volleyJson("http://video.coral.qq.com/user/0/msg/v2?msgtype=reply&callback=messages3&msgid=&pageflag=1&reqnum=10&type=3&_=1481509025355");

	}

	@Override
	public void volleyJson(final String href) {
		System.out.println("href=" + href);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Cookie", UrlUtils.getCookie());
		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, href, headers, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				response = response.replace("messages3({", "{").replace("})", "}");
				System.out.println("response=" + response);

				JSONObject userReplyJson = JSONObject.fromObject(response);
				String errCode = userReplyJson.getString("errCode");
				System.out.println("errCode=" + errCode);

				JSONObject info = userReplyJson.getJSONObject("info");
				String time = info.getString("time");
				System.out.println("time=" + time);

				JSONObject data = userReplyJson.getJSONObject("data");
				String first = data.getString("first");
				String last = data.getString("last");
				String reqnum = data.getString("reqnum");
				String retnum = data.getString("retnum");
				String total = data.getString("total");
				System.out.println("first=" + first);
				System.out.println("last=" + last);
				System.out.println("reqnum=" + reqnum);
				System.out.println("retnum=" + retnum);
				System.out.println("total=" + total);

				JSONObject articles = data.getJSONObject("articles");
				JSONObject comments = data.getJSONObject("comments");
				JSONObject users = data.getJSONObject("users");
				JSONArray messages = data.getJSONArray("messages");
				list.clear();
				for (int i = 0; i < messages.size(); i++) {
					MessageBean msgBean = new MessageBean();
					JSONObject message = messages.getJSONObject(i);
					String id = message.getString("id");
					String parent = message.getString("parent");
					String targetid = message.getString("targetid");
					String tipstype = message.getString("tipstype");
					String tipstime = message.getString("tipstime");
					System.out.println("id=" + id);
					System.out.println("parent=" + parent);
					System.out.println("targetid=" + targetid);
					System.out.println("tipstype=" + tipstype);
					System.out.println("tipstime=" + tipstime);
					msgBean.setId(id);
					msgBean.setParent(parent);
					msgBean.setTargetid(targetid);

					ArticleBean articleBean = new ArticleBean();
					JSONObject article = articles.getJSONObject(targetid+"");
					String appid = article.getString("appid");
					String commentnum = article.getString("commentnum");
					String title = article.getString("title");
					String mediatype = article.getString("mediatype");
					String articleid = article.getString("articleid");
					String url = article.getString("url");
					articleBean.setAppid(appid);
					articleBean.setCommentnum(commentnum);
					articleBean.setTitle(title);
					articleBean.setMediatype(mediatype);
					articleBean.setArticleid(articleid);
					articleBean.setUrl(url);
					msgBean.setArticle(articleBean);

					CommentBean replyComment = new CommentBean();
					JSONObject comment = comments.getJSONObject(id);
					String timec = comment.getString("time");
					String content = comment.getString("content");
					String userid = comment.getString("userid");
					String up = comment.getString("up");
					String poke = comment.getString("poke");
					String repnum = comment.getString("repnum");
					
					replyComment.setContent(content);
					replyComment.setPoke(poke);
					replyComment.setUp(up);
					replyComment.setRepnum(repnum);
					replyComment.setUserid(userid);
					replyComment.setTime(timec);
					
					replyComment.setHead(users.getJSONObject(userid+"").getString("head"));
					replyComment.setNick(users.getJSONObject(userid+"").getString("nick"));
					msgBean.setReplyComment(replyComment);
					
					
					CommentBean myComment = new CommentBean();
					comment = comments.getJSONObject(parent);
					timec = comment.getString("time");
					content = comment.getString("content");
					userid = comment.getString("userid");
					up = comment.getString("up");
					poke = comment.getString("poke");
					repnum = comment.getString("repnum");
					myComment.setContent(content);
					myComment.setPoke(poke);
					myComment.setUp(up);
					myComment.setRepnum(repnum);
					myComment.setUserid(userid);
					myComment.setTime(timec);
					myComment.setHead(users.getJSONObject(userid+"").getString("head"));
					myComment.setNick(users.getJSONObject(userid+"").getString("nick"));
					msgBean.setMyComment(myComment);
					
					list.add(msgBean);
				}
				mUserCommentAdapter.notifyDataSetChanged();

			}
		}, UserCommentFragment.this);
		requestQueue.add(jsonObjectRequest);
	}

}
