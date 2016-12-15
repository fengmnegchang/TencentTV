/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午5:52:50
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午5:52:50
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserVipPayInfoBean {
	// private String c_order;//1,
	// private String drm_id;//"",
	// private String extends;//"",
	// private String group_id;//"",
	private String lowest_price;// "16.5",
	private String month;// "1",
	private String month_price;// "25",
	// private String order_count;//0,
	// private String order_extend;//"",
	private String original_price;// "30",
	// private String price_desc;//"",
	// private String price_id;//"79142",
	private String product_desc;// "1个月",
	private String product_id;// "com.tenvideoios.OneMonthPay1",
	// private String save_desc;//"",
	private String save_price;// "5",
	// private String save_style;//"",
	private String total_price;// "25"

	public String getLowest_price() {
		return lowest_price;
	}

	public void setLowest_price(String lowest_price) {
		this.lowest_price = lowest_price;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth_price() {
		return month_price;
	}

	public void setMonth_price(String month_price) {
		this.month_price = month_price;
	}

	public String getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getSave_price() {
		return save_price;
	}

	public void setSave_price(String save_price) {
		this.save_price = save_price;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

}
