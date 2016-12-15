/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-6下午5:42:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;
import java.util.List;

import com.open.tencenttv.bean.StarGuestVarietyBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 明星 综艺
 * 
 * @author :fengguangjing
 * @createTime:2016-12-6下午5:42:08
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarGuestVarietyJson extends CommonTJson {
	private List<StarGuestVarietyBean> list = new ArrayList<StarGuestVarietyBean>();

	public List<StarGuestVarietyBean> getList() {
		return list;
	}

	public void setList(List<StarGuestVarietyBean> list) {
		this.list = list;
	}

}
