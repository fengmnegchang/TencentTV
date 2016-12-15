/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午5:49:21
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.FilmShow;
import com.open.tencenttv.bean.UserVipMonthBean;
import com.open.tencenttv.bean.VipAct;
import com.open.tencenttv.bean.VipPrivilege;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午5:49:21
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserVipJson extends CommonTJson {
	private String act_check_msg;// "",
	private String act_check_result;// 0,
	private String hlw_params;// "policy_id%3d79139",
	private UserVipMonthBean month_price;// Object{...},
	private UserVipMonthBean month_price_iap;// Object{...},
	// private String result;//Object{...}
	private List<VipPrivilege> vip_privilege_list;
	private List<FilmShow> film_show_list;
	private List<VipAct> vip_act_list;

	public List<VipPrivilege> getVip_privilege_list() {
		return vip_privilege_list;
	}

	public void setVip_privilege_list(List<VipPrivilege> vip_privilege_list) {
		this.vip_privilege_list = vip_privilege_list;
	}

	public List<FilmShow> getFilm_show_list() {
		return film_show_list;
	}

	public void setFilm_show_list(List<FilmShow> film_show_list) {
		this.film_show_list = film_show_list;
	}

	public List<VipAct> getVip_act_list() {
		return vip_act_list;
	}

	public void setVip_act_list(List<VipAct> vip_act_list) {
		this.vip_act_list = vip_act_list;
	}

	public String getAct_check_msg() {
		return act_check_msg;
	}

	public void setAct_check_msg(String act_check_msg) {
		this.act_check_msg = act_check_msg;
	}

	public String getAct_check_result() {
		return act_check_result;
	}

	public void setAct_check_result(String act_check_result) {
		this.act_check_result = act_check_result;
	}

	public String getHlw_params() {
		return hlw_params;
	}

	public void setHlw_params(String hlw_params) {
		this.hlw_params = hlw_params;
	}

	public UserVipMonthBean getMonth_price() {
		return month_price;
	}

	public void setMonth_price(UserVipMonthBean month_price) {
		this.month_price = month_price;
	}

	public UserVipMonthBean getMonth_price_iap() {
		return month_price_iap;
	}

	public void setMonth_price_iap(UserVipMonthBean month_price_iap) {
		this.month_price_iap = month_price_iap;
	}

}
