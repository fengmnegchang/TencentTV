/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:03:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;

import com.open.tencenttv.bean.NewFeatureBean;

/**
 *****************************************************************************************************************************************************************************
 * 推荐组别
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:03:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class NewFeatureJson extends CommonTJson {
	private ArrayList<NewFeatureBean>  list = new ArrayList<NewFeatureBean>();// 推荐组别

	public ArrayList<NewFeatureBean> getList() {
		return list;
	}

	public void setList(ArrayList<NewFeatureBean> list) {
		this.list = list;
	}
	
}
