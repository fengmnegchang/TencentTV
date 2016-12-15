/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:04:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.UserMyViewBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午4:04:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserMyViewJson extends CommonTJson {
	private String cur;// 1,
	private List<UserMyViewBean> list;// Array[3],
	private String now;// 18,
	// private String result;//Object{...},
	private String topg;// 1,
	private String tot;// 18

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur;
	}

	public List<UserMyViewBean> getList() {
		return list;
	}

	public void setList(List<UserMyViewBean> list) {
		this.list = list;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getTopg() {
		return topg;
	}

	public void setTopg(String topg) {
		this.topg = topg;
	}

	public String getTot() {
		return tot;
	}

	public void setTot(String tot) {
		this.tot = tot;
	}

}
