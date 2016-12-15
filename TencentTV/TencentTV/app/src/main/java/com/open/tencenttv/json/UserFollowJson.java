/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:39:47
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.UserFollowBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 用户看单
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:39:47
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserFollowJson extends CommonTJson {
	private String dv;// 1481247241,
	private List<UserFollowBean> follow;// Array[7],
	private String msg;// "",
	private String ret;// 0,
	private String total;// 7,
	private String updateflag;// 1

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public List<UserFollowBean> getFollow() {
		return follow;
	}

	public void setFollow(List<UserFollowBean> follow) {
		this.follow = follow;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUpdateflag() {
		return updateflag;
	}

	public void setUpdateflag(String updateflag) {
		this.updateflag = updateflag;
	}

}
