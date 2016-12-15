/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:49:45
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;

import com.open.tencenttv.bean.SliderNavBean;

/**
 *****************************************************************************************************************************************************************************
 * 导航大图
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:49:45
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SliderNavJson extends CommonTJson {
	private ArrayList<SliderNavBean> list = new ArrayList<SliderNavBean>();

	public ArrayList<SliderNavBean> getList() {
		return list;
	}

	public void setList(ArrayList<SliderNavBean> list) {
		this.list = list;
	}
	
}
