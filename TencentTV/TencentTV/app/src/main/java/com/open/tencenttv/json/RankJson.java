/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:08:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.ArrayList;
import java.util.List;

import com.open.tencenttv.bean.RankBean;

/**
 *****************************************************************************************************************************************************************************
 * 播放排行
 * @author :fengguangjing
 * @createTime:2016-11-25下午2:08:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class RankJson extends CommonTJson {
	private List<RankBean> list = new ArrayList<RankBean>();// 播放排行榜第二行标题

	public List<RankBean> getList() {
		return list;
	}

	public void setList(List<RankBean> list) {
		this.list = list;
	}
	
	
}
