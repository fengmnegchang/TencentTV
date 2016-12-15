/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午5:55:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.UserVipPayInfoBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午5:55:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserVipPayInfoJson {
	private String lowest_month_price;// "14",
	private List<UserVipPayInfoBean> pay_info;// Array[4],
	private String type;// 2

	public String getLowest_month_price() {
		return lowest_month_price;
	}

	public void setLowest_month_price(String lowest_month_price) {
		this.lowest_month_price = lowest_month_price;
	}

	public List<UserVipPayInfoBean> getPay_info() {
		return pay_info;
	}

	public void setPay_info(List<UserVipPayInfoBean> pay_info) {
		this.pay_info = pay_info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
