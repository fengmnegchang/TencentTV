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
public class TencentTVWebViewFragment extends CommonWebViewFragment {

	public static TencentTVWebViewFragment newInstance(String url) {
		TencentTVWebViewFragment fragment = new TencentTVWebViewFragment();
		fragment.url = url;
		return fragment;
	}
 

}
