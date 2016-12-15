/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午2:44:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.UserBillBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午2:44:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserBillJson extends CommonTJson {
	private List<UserBillBean> billList;// Array[10],
	private int ret;// 0,
	private int total;// 14

	public List<UserBillBean> getBillList() {
		return billList;
	}

	public void setBillList(List<UserBillBean> billList) {
		this.billList = billList;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
