/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午3:10:01
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.WeakReferenceHandler;
import com.open.tencenttv.adapter.UserStarTeamListViewAdapter;
import com.open.tencenttv.bean.UserStarTeamBean;
import com.open.tencenttv.json.UserStarTeamJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午3:10:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class UserStarTeamFragment extends BaseV4Fragment<UserStarTeamJson>{
	private String url = "http://like.video.qq.com/fcgi-bin/flw_new?otype=json&sn=FollowServer&cmd=2563&pidx=0&size=5&dtype=1&type=0&g_tk=1605338694";
	private ListViewTV listViewTV;
	private UserStarTeamListViewAdapter mUserStarTeamListViewAdapter;
	private List<UserStarTeamBean> list = new ArrayList<UserStarTeamBean>();

	public static UserStarTeamFragment newInstance(String url, MainUpView mainUpView1, EffectNoDrawBridge mRecyclerViewBridge, View mOldView) {
		UserStarTeamFragment fragment = new UserStarTeamFragment();
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		fragment.mainUpView1 = mainUpView1;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_listview, container, false);
		listViewTV = (ListViewTV) view.findViewById(R.id.listview);
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

		mUserStarTeamListViewAdapter = new UserStarTeamListViewAdapter(getActivity(), list);
		listViewTV.setAdapter(mUserStarTeamListViewAdapter);
		volleyJson(url);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		weakReferenceHandler = new WeakReferenceHandler(this);
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
				UserStarTeamJson mUserStarTeamJson = gson.fromJson(responseJson, UserStarTeamJson.class);
				if (mUserStarTeamJson != null && mUserStarTeamJson.getFollow() != null) {
					list.clear();
					list.addAll(mUserStarTeamJson.getFollow());
					mUserStarTeamListViewAdapter.setIsRmd(Integer.parseInt(mUserStarTeamJson.getIsRmd()));
					mUserStarTeamListViewAdapter.notifyDataSetChanged();
				}
			}
		}, UserStarTeamFragment.this);
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
