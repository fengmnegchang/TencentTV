/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:10:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 ***************************************************************************************************************************************************************************** 
 * 搜索历史
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:10:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchHotBean implements Serializable {
	private String c_pos;
	private String c_title;
	private String mark;
	private String order_change;

	public String getC_pos() {
		return c_pos;
	}

	public void setC_pos(String c_pos) {
		this.c_pos = c_pos;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getOrder_change() {
		return order_change;
	}

	public void setOrder_change(String order_change) {
		this.order_change = order_change;
	}

}
