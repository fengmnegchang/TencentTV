/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:58:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;

import com.open.androidtvwidget.leanback.mode.ListRow;

/**
 *****************************************************************************************************************************************************************************
 * 频道分类数据
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:58:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ListRowJson extends CommonTJson {
	private ArrayList<ListRow> list = new ArrayList<ListRow>();// 频道分类数据

	public ArrayList<ListRow> getList() {
		return list;
	}

	public void setList(ArrayList<ListRow> list) {
		this.list = list;
	}
	
}
