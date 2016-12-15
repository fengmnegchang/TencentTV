/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25上午10:19:31
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
 * @createTime:2016-11-25上午10:19:31
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarRelateBean implements Serializable {
	private String star_relatedPic;
	private String star_name;
	private String hrefurl;
	private String relation;

	public String getStar_relatedPic() {
		return star_relatedPic;
	}

	public void setStar_relatedPic(String star_relatedPic) {
		this.star_relatedPic = star_relatedPic;
	}

	public String getStar_name() {
		return star_name;
	}

	public void setStar_name(String star_name) {
		this.star_name = star_name;
	}

	public String getHrefurl() {
		return hrefurl;
	}

	public void setHrefurl(String hrefurl) {
		this.hrefurl = hrefurl;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
