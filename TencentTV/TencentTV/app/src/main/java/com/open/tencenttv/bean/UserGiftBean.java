/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午3:07:37
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
 * @createTime:2016-12-13下午3:07:37
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserGiftBean {
	// "bcode;//1,
	// "coin_type;//2,
	private String desc;// "持3元代金券可以在开通腾讯视频VIP时抵扣3元金额，订单满20元可使用，价格可能根据运营情况而调整。（每月限兑1次）",
	// "exchange_num;//988154,
	// "h5_desc1;//"",
	// "h5_desc2;//"",
	// "h5_desc3;//"",
	// "h5_desc4;//"",
	// "h5_desc5;//"",
	private String id;// 1007,
	// "isseckill;//0,
	// "isvip;//0,
	private String left_num;// 1211846,
	// "my_discount;//100,
	private String my_discount_txt;// "原价",
	// "my_exchange_month_num;//0,
	// "my_exchange_total_num;//0,
	private String my_vip_vcoin;// 5,
	private String pic;// "http://i.gtimg.cn/qqlive/images/20160728/i1469689133_1.jpg",
	// "seckill_discount;//0,
	// "seckill_discount_txt;//"",
	// "seckill_finish_time;//0,
	// "seckill_start_time;//0,
	// "seckill_title;//"",
	// "seckill_total_num;//0,
	// "seckill_vcoin;//0,
	// "seckilled_num;//0,
	// "status;//0,
	private String status_name;// "可兑换",
	private String title;// "腾讯视频VIP3元代金券",
	private String url;// "http://film.qq.com/weixin/login.html?ru=http%3a%2f%2ffilm.qq.com%2fweixin%2fupay.html%3faid%3dV0%24%244%3a95",
	private String url_pic;// "http://i.gtimg.cn/qqlive/images/20160728/i1469696507_1.jpg",
	// "vcoin;//5,
	// "vip_level;//0

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLeft_num() {
		return left_num;
	}

	public void setLeft_num(String left_num) {
		this.left_num = left_num;
	}

	public String getMy_discount_txt() {
		return my_discount_txt;
	}

	public void setMy_discount_txt(String my_discount_txt) {
		this.my_discount_txt = my_discount_txt;
	}

	public String getMy_vip_vcoin() {
		return my_vip_vcoin;
	}

	public void setMy_vip_vcoin(String my_vip_vcoin) {
		this.my_vip_vcoin = my_vip_vcoin;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl_pic() {
		return url_pic;
	}

	public void setUrl_pic(String url_pic) {
		this.url_pic = url_pic;
	}

}
