/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午5:02:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.UserMyViewExpandableListAdapter;
import com.open.tencenttv.bean.UserMyViewBean;
import com.open.tencenttv.json.UserMyViewJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午5:02:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class UserMyViewExpandableListFragment extends BaseV4Fragment<UserMyViewJson>{
	private String url = "http://ncgi.video.qq.com/tvideo/fcgi-bin/myview?pn=25&otype=json&t=1&plat=2&pver=4&cur=1&g_tk=1605338694";
	private ExpandableListView expendablelistview;
	private UserMyViewExpandableListAdapter mUserMyViewExpandableListAdapter;
	private List<UserMyViewBean> list = new ArrayList<UserMyViewBean>();

	public static UserMyViewExpandableListFragment newInstance(String url, MainUpView mainUpView1, EffectNoDrawBridge mRecyclerViewBridge, View mOldView) {
		UserMyViewExpandableListFragment fragment = new UserMyViewExpandableListFragment();
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		fragment.mainUpView1 = mainUpView1;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_expandable_listview, container, false);
		expendablelistview = (ExpandableListView) view.findViewById(R.id.expendablelistview);
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
		expendablelistview.setGroupIndicator(null);
		mUserMyViewExpandableListAdapter = new UserMyViewExpandableListAdapter(getActivity(), list);
		expendablelistview.setAdapter(mUserMyViewExpandableListAdapter);
		volleyJson(url);
	}
 

	@Override
	public void volleyJson(final String href) {
		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, href, UrlUtils.getHeaders(), new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println("href=" + href);
				System.out.println("response=" + response);
				Gson gson = new Gson();

				String responseJson = response.toString().replace("QZOutputJson={", "{").replace("}};", "}}").replace("};", "}").replace("jQuery19108518000963786472_1481267066958({", "{")
						.replace("})", "}");
				UserMyViewJson mUserMyViewJson = gson.fromJson(responseJson, UserMyViewJson.class);
				if (mUserMyViewJson != null && mUserMyViewJson.getList() != null) {
					list.clear();
					list.addAll(mUserMyViewJson.getList());
					mUserMyViewExpandableListAdapter.notifyDataSetChanged();
					for(int i=0;i<mUserMyViewExpandableListAdapter.getGroupCount();i++){
						expendablelistview.expandGroup(i);
					}
				}
			}
		}, UserMyViewExpandableListFragment.this);
		requestQueue.add(jsonObjectRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.tencenttv.BaseV4Fragment#onErrorResponse(com.android.volley.
	 * VolleyError)
	 */
	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub
		super.onErrorResponse(error);
		System.out.println(error);
	}
}
