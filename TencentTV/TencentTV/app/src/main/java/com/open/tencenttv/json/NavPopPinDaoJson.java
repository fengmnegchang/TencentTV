/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:44:50
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;

import com.open.tencenttv.bean.NavPopPinDaoBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 头部导航菜单
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:44:50
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class NavPopPinDaoJson extends CommonTJson {
	ArrayList<NavPopPinDaoBean> list = new ArrayList<NavPopPinDaoBean>();

	public ArrayList<NavPopPinDaoBean> getList() {
		return list;
	}

	public void setList(ArrayList<NavPopPinDaoBean> list) {
		this.list = list;
	}

}
