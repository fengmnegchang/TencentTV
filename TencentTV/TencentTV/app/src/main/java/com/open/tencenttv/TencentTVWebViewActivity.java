/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-10-17下午5:20:10
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 内嵌webview页面
 * 
 * @author :fengguangjing
 * @createTime:2016-10-17下午5:20:10
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class TencentTVWebViewActivity extends BaseFragmentActivity {
	private static final String TAG = TencentTVWebViewActivity.class.getSimpleName();
	private WebView webview;
	private String url;
	private String host;
	private String cookies;
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tencent_tv_webview);
		init();

	}

	public static void synCookies(Context context, String url,String cookies) {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.removeSessionCookie();// 移除
		cookieManager.setCookie(url, cookies);// cookies是在HttpClient中获得的cookie
		CookieSyncManager.getInstance().sync();
	}

 

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.CommonActivity#findView()
	 */
	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		super.findView();
		webview = (WebView) findViewById(R.id.webview);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.newsinfo.CommonActivity#initValue()
	 */
	@SuppressLint("NewApi") @Override
	protected void initValue() {
		// TODO Auto-generated method stub
		super.initValue();
		Intent intent = getIntent();
		url = intent.getStringExtra("URL");
		host = intent.getStringExtra("HOST");
		cookies = intent.getStringExtra("COOKIES");
		
		Log.i(TAG, "url===" + url+"host="+host+";cookies="+cookies);
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webSettings.setUseWideViewPort(true);
		// 设置出现缩放工具
		webSettings.setBuiltInZoomControls(true);
		// 扩大比例的缩放
		webSettings.setUseWideViewPort(true);
		// 自适应屏幕
		if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        }
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		webview.setWebViewClient(mWebViewClientBase);
		webview.setWebChromeClient(mWebChromeClientBase);
//		webSettings.setUserAgentString(UrlUtils.tencentAgent);
		
		if(url == null){
			url = UrlUtils.TENCENT_X_MOVIE_LIST;
		}
		 
		 Map<String, String>  header = new HashMap<String, String>();
		 Date date = new Date();
		 header.put("If-Modified-Since", date.toGMTString());
		 header.put("Upgrade-Insecure-Requests","1");
		 header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		 header.put("Accept-Encoding","gzip, deflate, sdch");
		 header.put("Accept-Language","zh-CN,zh;q=0.8");
		 header.put("Cache-Control","max-age=0");
		 header.put("Connection","keep-alive");
//		 header.put("User-Agent",UrlUtils.tencentAgent);
		 if(host!=null){
			 header.put("Host",host);
		 }else{
			 header.put("Host",UrlUtils.HOST);
		 }
		  
		synCookies(TencentTVWebViewActivity.this,url,UrlUtils.getWebCookies());
		webview.loadUrl(url,header);
		

	}

	private WebViewClientBase mWebViewClientBase = new WebViewClientBase();

	private class WebViewClientBase extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
			// TODO Auto-generated method stub
			super.doUpdateVisitedHistory(view, url, isReload);
		}
		 public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){  
			//handler.cancel(); 默认的处理方式，WebView变成空白页
				//handler.process();接受证书
				//handleMessage(Message msg); 其他处理
	     }  
	}

	private WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();

	private class WebChromeClientBase extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			// TODO Auto-generated method stub
			super.onReceivedTitle(view, title);
		}

		@Override
		public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
			// TODO Auto-generated method stub
			super.onReceivedTouchIconUrl(view, url, precomposed);
		}

		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
			// TODO Auto-generated method stub
			return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (webview.canGoBack()) {
			webview.goBack();
		} else {
			super.onBackPressed();
		}
	}
	
	
	public static void startTencentTVWebViewActivity(Context context,String url){
		Intent intent = new Intent();
		intent.setClass(context, TencentTVWebViewActivity.class);
		intent.putExtra("URL", url);
		context.startActivity(intent);
	}
	
	public static void startTencentTVWebViewActivity(Context context,String url,String host,String cookies){
		Intent intent = new Intent();
		intent.setClass(context, TencentTVWebViewActivity.class);
		intent.putExtra("URL", url);
		intent.putExtra("HOST", host);
		intent.putExtra("COOKIES", cookies);
		context.startActivity(intent);
	}

}
