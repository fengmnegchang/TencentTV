/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:53:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;
import java.util.List;

import com.open.tencenttv.bean.PinDaoBean;

/**
 ***************************************************************************************************************************************************************************** 
 * x影视检索，右部头v.qq.com/x/movielist/?cate=10001&offset=0&sort=4
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:53:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PinDaoBeanJson extends CommonTJson {
	private List<PinDaoBean> list = new ArrayList<PinDaoBean>();// x影视检索，右部头v.qq.com/x/movielist/?cate=10001&offset=0&sort=4

	public List<PinDaoBean> getList() {
		return list;
	}

	public void setList(List<PinDaoBean> list) {
		this.list = list;
	}

}
