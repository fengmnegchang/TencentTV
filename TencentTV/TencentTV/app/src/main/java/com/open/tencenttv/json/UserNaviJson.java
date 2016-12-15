/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:31:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.UserNaviBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:31:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserNaviJson extends CommonTJson {

	private List<UserNaviBean> list;

	public List<UserNaviBean> getList() {
		return list;
	}

	public void setList(List<UserNaviBean> list) {
		this.list = list;
	}

}
