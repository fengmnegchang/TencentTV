/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:09:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.TencentTVSearchActivity;
import com.open.tencenttv.adapter.SearchWordsAdapter;
import com.open.tencenttv.bean.SearchWordsBean;
import com.open.tencenttv.json.SearchWordsJson;

/**
 ***************************************************************************************************************************************************************************** 
 * 搜索历史
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:09:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchWordsFragment extends BaseV4Fragment<SearchWordsJson>implements TextWatcher{
	private ListViewTV listViewTV;
	private SearchWordsAdapter mSearchWordsAdapter;
    private String url = "http://s.video.qq.com/smartbox?plat=2&ver=0&num=10&otype=json&query=";
//	private String url = "http://s.video.qq.com/smartbox?callback=jQuery191005598623925280477_1481099927738&plat=2&ver=0&num=10&otype=json&query=%E5%92%B1&uid=bab5d342-e3fe-4372-b111-b849e126b868&_=1481099927767";
//	private String url = "http://data.video.qq.com/fcgi-bin/dataout?callback=jQuery191005598623925280477_1481099927738&auto_id=938&otype=json&_=1481099927778";
	private List<SearchWordsBean> list = new ArrayList<SearchWordsBean>();
	private EditText edit_search;
	public static SearchWordsFragment newInstance(String url, MainUpView mainUpView1, View mOldView, EffectNoDrawBridge mRecyclerViewBridge) {
		SearchWordsFragment fragment = new SearchWordsFragment();
		// fragment.url = url;
		fragment.mainUpView1 = mainUpView1;
		fragment.mOldView = mOldView;
		fragment.mRecyclerViewBridge = mRecyclerViewBridge;
		return fragment;
	}

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
		View view = inflater.inflate(R.layout.fragment_search_words_list, container, false);
		listViewTV = (ListViewTV) view.findViewById(R.id.listview);
		edit_search = (EditText) view.findViewById(R.id.edit_search);
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
		mSearchWordsAdapter = new SearchWordsAdapter(getActivity(), list);
		listViewTV.setAdapter(mSearchWordsAdapter);
		mSearchWordsAdapter.notifyDataSetChanged();
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
				TencentTVSearchActivity.startTencentTVSearchActivity(getActivity(), list.get((int)id).getWord());
			}
		});
		edit_search.addTextChangedListener(this);
	}

	// // 延时请求初始位置的item.
	Handler mFirstHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			listViewTV.setDefaultSelect(0);
		}
	};
	 
	
	@Override
	public void volleyJson(String href) {
		try {
			href = url + URLEncoder.encode(edit_search.getText().toString(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String,String> headersMap = new HashMap<String, String>();
		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, href, headersMap, new Response.Listener<String>(){
			@Override
			public void onResponse(String response) {
				System.out.println("response=" + response);
				Gson gson = new Gson();
				 
				String responseJson = response.toString().replace("QZOutputJson={", "{").replace("}};", "}}").replace("jQuery191005598623925280477_1481099927738({", "{").replace("}})", "}}");
				SearchWordsJson mSearchWordsJson = gson.fromJson(responseJson, SearchWordsJson.class);
				if (mSearchWordsJson != null && mSearchWordsJson.getItem() != null && mSearchWordsJson.getItem().size() > 0) {
					list.clear();
					list.addAll(mSearchWordsJson.getItem());
					mSearchWordsAdapter.notifyDataSetChanged();
					mFirstHandler.sendMessageDelayed(mFirstHandler.obtainMessage(), 188);
				}
			}
		}, SearchWordsFragment.this);
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

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String search = edit_search.getText().toString();
		if(edit_search.length()>0){
			volleyJson(url);
		}
		// TODO Auto-generated method stub
//		if(mSearchHotPopupWindow==null){
//			mSearchHotPopupWindow = new SearchHotPopupWindow(this, mainUpView1, mOldView, mRecyclerViewBridge);
//		}
//		mSearchHotPopupWindow.showPopupWindow(edit_search);
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
	 */
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

}
