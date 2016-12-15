/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:53:54
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
import java.util.Map;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
import com.open.androidtvwidget.view.GridViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.WeakReferenceHandler;
import com.open.tencenttv.adapter.UserFollowGridViewAdapter;
import com.open.tencenttv.bean.UserFollowBean;
import com.open.tencenttv.json.UserFollowJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:53:54
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserFollowGridFragment extends BaseV4Fragment<UserFollowJson> {
	private String url = "http://like.video.qq.com/fcgi-bin/flw_new?otype=json&sn=FollowServer&cmd=2562&pidx=0&size=30";
	private GridViewTV gridViewTV;
	private UserFollowGridViewAdapter mUserFollowGridViewAdapter;
	private List<UserFollowBean> list = new ArrayList<UserFollowBean>();

	public static UserFollowGridFragment newInstance(String url, MainUpView mainUpView1, EffectNoDrawBridge mRecyclerViewBridge, View mOldView) {
		UserFollowGridFragment fragment = new UserFollowGridFragment();
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		fragment.mainUpView1 = mainUpView1;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_follow_grid, container, false);
		gridViewTV = (GridViewTV) view.findViewById(R.id.gridview);
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
		
		UserFollowHeadFragment fragment = UserFollowHeadFragment.newInstance(weakReferenceHandler,UrlUtils.TENCENT_USER_PLAYLIST,mainUpView1,mOldView,mRecyclerViewBridge);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.layout_follow_head, fragment).commit();
		
		mUserFollowGridViewAdapter = new UserFollowGridViewAdapter(getActivity(), list);
		gridViewTV.setAdapter(mUserFollowGridViewAdapter);
		volleyJson(url);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		weakReferenceHandler = new WeakReferenceHandler(this);
	}
	
	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4Fragment#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		super.handlerMessage(msg);
		Log.i(TAG,";datatype=="+msg.obj+";what=="+msg.what);
		String href = url +"&dtype="+msg.arg1+"&type="+msg.arg2;
		volleyJson(href);
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

				String responseJson = response.toString().replace("QZOutputJson={", "{").replace("}};", "}}").replace("};", "}").replace("jQuery19106935243788063994_1481188648206({", "{").replace("})", "}");
				UserFollowJson mUserFollowJson = gson.fromJson(responseJson, UserFollowJson.class);
				if (mUserFollowJson != null && mUserFollowJson.getFollow() != null) {
					list.clear();
					list.addAll(mUserFollowJson.getFollow());
					mUserFollowGridViewAdapter.notifyDataSetChanged();
				}
			}
		}, UserFollowGridFragment.this);
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
