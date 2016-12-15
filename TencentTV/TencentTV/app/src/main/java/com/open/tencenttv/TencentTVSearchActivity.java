/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午1:42:33
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.tencenttv.fragment.SearchTabHorizontalViewPagerFragment;

/**
 ***************************************************************************************************************************************************************************** 
 * 腾讯视频 搜索
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午1:42:33
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class TencentTVSearchActivity extends CommonFragmentActivity {
	private String url = "http://v.qq.com/x/search/?&q=";
	private TextView txt_search_words;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tencent_tv_search);
		init();

	}

	@Override
	protected void findView() {
		super.findView();
		this.mInflater = LayoutInflater.from(getApplicationContext());
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		txt_search_words = (TextView) findViewById(R.id.txt_search_words);
	}

	@Override
	protected void initValue() {
		super.initValue();
        String words = getIntent().getStringExtra("WORDS");
        if(words==null){
        	words = "咱";
        }
        txt_search_words.setText(words);
        try {
        	url = url + URLEncoder.encode(words,"UTF-8")+"&stag=2&smartbox_ab=";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 默认是 OpenEff...，建议使用 NoDraw... ...
		mainUpView1.setEffectBridge(new EffectNoDrawBridge());
		mRecyclerViewBridge = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
		mRecyclerViewBridge.setTranDurAnimTime(200);
		mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
		mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距

		SearchTabHorizontalViewPagerFragment fragment = SearchTabHorizontalViewPagerFragment.newInstance(url,"div.search_tools","div.filter_item","a", words, mainUpView1, mOldView, mRecyclerViewBridge);

		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.frame_search, fragment).commit();
	}
	 
	public static void startTencentTVSearchActivity(Context context,String words){
		Intent intent = new Intent();
		intent.setClass(context, TencentTVSearchActivity.class);
		intent.putExtra("WORDS", words);
		context.startActivity(intent);
	}
}
