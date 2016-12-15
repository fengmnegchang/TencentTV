/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8上午10:11:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

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
import com.open.tencenttv.BaseAppFragment;
import com.open.tencenttv.R;
import com.open.tencenttv.adapter.SearchHotAdapter;
import com.open.tencenttv.bean.SearchHotBean;
import com.open.tencenttv.json.SearchHotJson;
import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8上午10:11:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchHotPopupFragment extends BaseAppFragment<SearchHotJson> {
	private ListViewTV listViewTV;
	private SearchHotAdapter mSearchHistoryAdapter;
	// private String url =
	// "http://s.video.qq.com/smartbox?callback=jQuery191005598623925280477_1481099927738&plat=2&ver=0&num=10&otype=json&query=%E5%92%B1&uid=bab5d342-e3fe-4372-b111-b849e126b868&_=1481099927767";
	private String url = "http://data.video.qq.com/fcgi-bin/dataout?callback=jQuery191005598623925280477_1481099927738&auto_id=938&otype=json&_=1481099927778";
	private List<SearchHotBean> list = new ArrayList<SearchHotBean>();

	// public static SearchHotFragment newInstance(String url, MainUpView
	// mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
	// SearchHotFragment fragment = new SearchHotFragment();
	// // fragment.url = url;
	// fragment.mainUpView1 = mainUpView1;
	// fragment.mOldView = mOldView;
	// fragment.mRecyclerViewBridge = mRecyclerViewBridge;
	// return fragment;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_search_hot_list, container, false);
		listViewTV = (ListViewTV) view.findViewById(R.id.listview);
		mainUpView1 = (MainUpView) view.findViewById(R.id.mainUpView1);

		// 默认是 OpenEff...，建议使用 NoDraw... ...
		mainUpView1.setEffectBridge(new EffectNoDrawBridge());
		mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
		mRecyclerViewBridge.setTranDurAnimTime(200);
		mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
		mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距
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
		super.onViewCreated(view, savedInstanceState);
		// doAsync(this, this, this);
		mSearchHistoryAdapter = new SearchHotAdapter(getActivity(), list);
		listViewTV.setAdapter(mSearchHistoryAdapter);
		mSearchHistoryAdapter.notifyDataSetChanged();
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
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url, UrlUtils.getHeaders(), new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				System.out.println("response=" + response);
				Gson gson = new Gson();
				/**
				 * jQuery191005598623925280477_1481099927738({"costtime":1549,
				 * "modify_time"
				 * :"2016-12-07 04:40:03","returncode":0,"returnmsg"
				 * :"ok","words" :[{"c_pos"
				 * :"1","c_title":"锦绣未央","mark":"no","order_change"
				 * :"0"},{"c_pos" :"2","c_title"
				 * :"咱们相爱吧","mark":"no","order_change"
				 * :"0"},{"c_pos":"3","c_title" :"如果蜗牛有爱情"
				 * ,"mark":"pc","order_change"
				 * :"0"},{"c_pos":"4","c_title":"熊出没之秋日团团转" ,"mark"
				 * :"no","order_change"
				 * :"0"},{"c_pos":"5","c_title":"维多利亚的秘密","mark"
				 * :"no","order_change"
				 * :"0"},{"c_pos":"6","c_title":"爱情保卫战","mark":"no",
				 * "order_change"
				 * :"1"},{"c_pos":"7","c_title":"小猪佩奇第4季","mark":"no"
				 * ,"order_change"
				 * :"1"},{"c_pos":"8","c_title":"爱情珠宝","mark":"pc"
				 * ,"order_change":"1"},{ "c_pos"
				 * :"9","c_title":"蓝色大海的传说","mark"
				 * :"no","order_change":"1"},{"c_pos" :"10"
				 * ,"c_title":"喜剧总动员 第1季"
				 * ,"mark":"no","order_change":"-1"},{"c_pos": "11",
				 * "c_title":"不可能完成的任务"
				 * ,"mark":"no","order_change":"-1"},{"c_pos":"12" ,"c_title"
				 * :"2016维多利亚的秘密","mark":"no","order_change":"1"},{"c_pos":"13"
				 * ,"c_title"
				 * :"第22条婚规2","mark":"no","order_change":"0"},{"c_pos"
				 * :"14","c_title" :"定制幸福"
				 * ,"mark":"no","order_change":"1"},{"c_pos"
				 * :"15","c_title":"青云志", "mark":
				 * "no","order_change":"-1"},{"c_pos"
				 * :"16","c_title":"缘来非诚勿扰","mark"
				 * :"no","order_change":"-1"},{"c_pos"
				 * :"17","c_title":"九九","mark":"no", "order_change"
				 * :"1"},{"c_pos"
				 * :"18","c_title":"乱世佳人","mark":"no","order_change" :"1"},{
				 * "c_pos"
				 * :"19","c_title":"真正男子汉 第2季","mark":"no","order_change":"-1"
				 * },{"c_pos"
				 * :"20","c_title":"从你的全世界路过","mark":"no","order_change":"1"},{
				 * "c_pos":"21"
				 * ,"c_title":"木兰妈妈","mark":"no","order_change":"1"},{"c_pos"
				 * :"22","c_title"
				 * :"周星驰","mark":"no","order_change":"-1"},{"c_pos"
				 * :"23","c_title" :"耳边疯 第1季"
				 * ,"mark":"no","order_change":"1"},{"c_pos"
				 * :"24","c_title":"兰陵王妃" ,"mark"
				 * :"no","order_change":"0"},{"c_pos"
				 * :"25","c_title":"你的名字。","mark" :"no",
				 * "order_change":"-1"},{"c_pos"
				 * :"26","c_title":"陈二狗的妖孽人生 第二季","mark" :"pc"
				 * ,"order_change":"-1"
				 * },{"c_pos":"27","c_title":"完美告白","mark":"no", "order_change"
				 * :"1"},{"c_pos":"28","c_title":"芈月传","mark":"no",
				 * "order_change" :"1"},{"c_pos"
				 * :"29","c_title":"天籁之战 第1季","mark":"no","order_change":"-1"
				 * },{"c_pos":"30"
				 * ,"c_title":"饭局的诱惑 第1季","mark":"no","order_change":"-1"}
				 * ],"write_time":"2016-12-07 16:41:21"})
				 */
				String responseJson = response.toString().replace("jQuery191005598623925280477_1481099927738({\"", "{\"").replace("\"})", "\"}");
				SearchHotJson mSearchHistoryJson = gson.fromJson(responseJson, SearchHotJson.class);
				if (mSearchHistoryJson != null && mSearchHistoryJson.getWords() != null && mSearchHistoryJson.getWords().size() > 0) {
					list.clear();
					list.addAll(mSearchHistoryJson.getWords());
					mSearchHistoryAdapter.notifyDataSetChanged();
					mFirstHandler.sendMessageDelayed(mFirstHandler.obtainMessage(), 188);
				}
			}
		}, SearchHotPopupFragment.this);
		requestQueue.add(jsonObjectRequest);

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