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

import com.open.tencenttv.bean.SearchHotBean;

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
public class SearchHotJson extends CommonTJson {
	private List<SearchHotBean> words = new ArrayList<SearchHotBean>();

	public List<SearchHotBean> getWords() {
		return words;
	}

	public void setWords(List<SearchHotBean> words) {
		this.words = words;
	}

}
