/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午2:45:59
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
import com.open.tencenttv.adapter.UserBillAdapter;
import com.open.tencenttv.bean.UserBillBean;
import com.open.tencenttv.json.UserBillJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午2:45:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class UserBillFragment extends BaseV4Fragment<UserBillJson>{
	private UserBillAdapter mUserBillAdapter;
	private List<UserBillBean> list = new ArrayList<UserBillBean>();
	private ListViewTV listViewTV;
	private String url = "http://buy.video.qq.com/fcgi-bin/paycheck?callback=jQuery19105061520853703716_1481610716023&cmd=59855&platform=2&pidx=0&show_type=1&size=10&otype=json&g_tk=250078081&_=1481610716024";
                        //http://buy.video.qq.com/fcgi-bin/paycheck?callback=jQuery19105061520853703716_1481610716014&cmd=59855&platform=2&pidx=1&show_type=1&size=10&otype=json&g_tk=250078081&_=1481610716048
	public static UserBillFragment newInstance(String url, MainUpView mainUpView1, EffectNoDrawBridge mRecyclerViewBridge, View mOldView) {
		UserBillFragment fragment = new UserBillFragment();
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

		mUserBillAdapter = new UserBillAdapter(getActivity(), list);
		listViewTV.setAdapter(mUserBillAdapter);
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

				String responseJson = response.toString().replace("QZOutputJson={", "{").replace("}};", "}}").replace("};", "}").replace("jQuery19105061520853703716_1481610716023({", "{")
						.replace("})", "}");
				UserBillJson mUserBillJson = gson.fromJson(responseJson, UserBillJson.class);
				if (mUserBillJson != null && mUserBillJson.getBillList() != null) {
					list.clear();
					list.addAll(mUserBillJson.getBillList());
					mUserBillAdapter.notifyDataSetChanged();
				}
			}
		}, UserBillFragment.this);
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
