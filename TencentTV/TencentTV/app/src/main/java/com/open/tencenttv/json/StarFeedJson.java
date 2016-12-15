/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-5下午4:31:37
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;
import java.util.List;

import com.open.tencenttv.bean.StarFeedBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-5下午4:31:37
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarFeedJson extends CommonTJson {
	private List<StarFeedBean> list = new ArrayList<StarFeedBean>();

	public List<StarFeedBean> getList() {
		return list;
	}

	public void setList(List<StarFeedBean> list) {
		this.list = list;
	}

}
