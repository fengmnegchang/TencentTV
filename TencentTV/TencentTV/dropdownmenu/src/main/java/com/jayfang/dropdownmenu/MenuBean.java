/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-23上午10:02:49
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.jayfang.dropdownmenu;

import java.io.Serializable;

/**
 ***************************************************************************************************************************************************************************** 
 * * <span class="label">地区：</span> <div class="tags_list"> <a _boss="area"
	 * href="?cate=10001&#38;offset=0&#38;sort=4&#38;area=-1"
	 * class="tag selected" data-index="-1" data-type="area" >全部</a>
 * @author :fengguangjing
 * @createTime:2016-11-23上午10:02:49
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MenuBean implements Serializable {

	/**
	 * <span class="label">地区：</span> <div class="tags_list"> <a _boss="area"
	 * href="?cate=10001&#38;offset=0&#38;sort=4&#38;area=-1"
	 * class="tag selected" data-index="-1" data-type="area" >全部</a>
	 */
	private String boss;
	private String href;
	private String dataindex;
	private String datatype;
	private String menuname;// 全部

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getDataindex() {
		return dataindex;
	}

	public void setDataindex(String dataindex) {
		this.dataindex = dataindex;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

}
