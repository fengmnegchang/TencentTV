/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午3:11:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.UserGiftBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 热门兑换腾讯视频VIP专享优惠折扣，去 开通腾讯视频VIP

 * @author :fengguangjing
 * @createTime:2016-12-13下午3:11:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserGiftJson extends CommonTJson {
	private String errmsg;// "OK",
	private List<UserGiftBean> gift;// Array[19],
	private int ret;// 0,
	private int total;// 19

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<UserGiftBean> getGift() {
		return gift;
	}

	public void setGift(List<UserGiftBean> gift) {
		this.gift = gift;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
