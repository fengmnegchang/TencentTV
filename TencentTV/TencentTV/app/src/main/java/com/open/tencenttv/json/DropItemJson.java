/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:06:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;
import java.util.List;

import com.jayfang.dropdownmenu.DropItemBean;

/**
 *****************************************************************************************************************************************************************************
 * 影视列表搜索头部
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:06:39
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DropItemJson extends CommonTJson {
	private List<DropItemBean> list = new ArrayList<DropItemBean>();// 影视列表搜索头部

	public List<DropItemBean> getList() {
		return list;
	}

	public void setList(List<DropItemBean> list) {
		this.list = list;
	}
	
}
