/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:27:12
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-8下午4:27:12
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserNaviBean implements Serializable {

	/**
	 * <li class='item ' >
	 * <a href="/u/shoppingcart/" class="navi_inner _shopcar"
	 * _hot="uc.side._shopcar"> <i class="icon icon_shopcar"> <svg
	 * class="svg_icon svg_icon_shopcar" viewBox="0 0 20 20"><use
	 * xmlns:xlink="http://www.w3.org/1999/xlink"
	 * xlink:href="#svg_icon_shopcar"></use></svg> </i> <span
	 * class="text">我的购物车</span></a></li>
	 */
	private String navi_href;
	private String navi_text;

	public String getNavi_href() {
		return navi_href;
	}

	public void setNavi_href(String navi_href) {
		this.navi_href = navi_href;
	}

	public String getNavi_text() {
		return navi_text;
	}

	public void setNavi_text(String navi_text) {
		this.navi_text = navi_text;
	}

}
