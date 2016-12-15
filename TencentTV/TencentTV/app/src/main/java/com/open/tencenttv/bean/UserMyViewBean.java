/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:07:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:07:27
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserMyViewBean {
	private List<UserMyViewGridBean> list;// Array[3],
	private String tot;// 3,
	private String type;// "t"

	public List<UserMyViewGridBean> getList() {
		return list;
	}

	public void setList(List<UserMyViewGridBean> list) {
		this.list = list;
	}

	public String getTot() {
		return tot;
	}

	public void setTot(String tot) {
		this.tot = tot;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
