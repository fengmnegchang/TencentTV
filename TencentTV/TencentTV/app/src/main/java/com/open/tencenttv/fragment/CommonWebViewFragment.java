/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13上午10:58:29
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.fragment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.open.tencenttv.BaseV4Fragment;
import com.open.tencenttv.R;
import com.open.tencenttv.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13上午10:58:29
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class CommonWebViewFragment extends BaseV4Fragment<String> {
	public String url;
	public WebView webview;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_tencent_tv_webview, container, false);
		webview = (WebView) view.findViewById(R.id.webview);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 * android.os.Bundle)
	 */
	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		Log.i(TAG, "url===" + url);
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webSettings.setUseWideViewPort(true);
		// 设置出现缩放工具
		webSettings.setBuiltInZoomControls(true);
		// 扩大比例的缩放
		webSettings.setUseWideViewPort(true);
		Date date = new Date();
		Map<String, String> header = new HashMap<String, String>();
		header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		header.put("Accept-Encoding", "gzip, deflate, sdch");
		header.put("Accept-Language", "zh-CN,zh;q=0.8");
		header.put("Connection", "keep-alive");
		header.put("Cookie", UrlUtils.getWebCookies());
		header.put("Host", "v.qq.com");
		header.put("If-Modified-Since", date.toGMTString());
		header.put("Referer", url);
		header.put("Upgrade-Insecure-Requests", "1");
		header.put("User-Agent", UrlUtils.tencentAgent);

		// 自适应屏幕
		if (Build.VERSION.SDK_INT >= 21) {
			webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		webview.setWebViewClient(mWebViewClientBase);
		webview.setWebChromeClient(mWebChromeClientBase);
		// webSettings.setUserAgentString(UrlUtils.tencentAgent);

		if (url == null) {
			url = UrlUtils.TENCENT_X_MOVIE_LIST;
		}
		synCookies(getActivity(), url);
		webview.loadUrl(url, header);
	}

	public static void synCookies(Context context, String url) {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.removeSessionCookie();// 移除
		cookieManager.setCookie(url, UrlUtils.getWebCookies());// cookies是在HttpClient中获得的cookie
		CookieSyncManager.getInstance().sync();
	}

	public WebViewClientBase mWebViewClientBase = new WebViewClientBase();

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

		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			// handler.cancel(); 默认的处理方式，WebView变成空白页
			// handler.process();接受证书
			// handleMessage(Message msg); 其他处理
		}
	}

	public WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();

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

	
	/* (non-Javadoc)
	 * @see com.open.tencenttv.BaseV4Fragment#onBackPressed()
	 */
	@Override
	public boolean onBackPressed() {
		if(webview.canGoBack()){
			webview.goBack();
			return true;
		}else{
			return super.onBackPressed();
		}
	}
}
