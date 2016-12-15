/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午2:13:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;
import java.util.List;

import com.open.tencenttv.bean.SearchBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午2:13:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchJson extends CommonTJson {
	private List<SearchBean> list = new ArrayList<SearchBean>();

	public List<SearchBean> getList() {
		return list;
	}

	public void setList(List<SearchBean> list) {
		this.list = list;
	}

}
