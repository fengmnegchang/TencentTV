/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:12:32
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import com.open.tencenttv.bean.StarBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 明星简介head
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:12:32
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarJson extends CommonTJson {
	private StarBean starbean;

	public StarBean getStarbean() {
		return starbean;
	}

	public void setStarbean(StarBean starbean) {
		this.starbean = starbean;
	}

}
