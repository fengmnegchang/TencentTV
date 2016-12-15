/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:12:58
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;
import java.util.List;

import com.open.tencenttv.bean.SearchWordsBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 搜索历史
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:12:58
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchWordsJson {
	private List<SearchWordsBean> item = new ArrayList<SearchWordsBean>();

	public List<SearchWordsBean> getItem() {
		return item;
	}

	public void setItem(List<SearchWordsBean> item) {
		this.item = item;
	}
}
