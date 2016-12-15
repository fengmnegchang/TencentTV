package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 * *****************************************************************************
 * *****************************************************************************
 * ******************
 * 
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: 
 *               ****************************************************************
 *               ***************************************************************
 *               *********************************************
 */
public class PinDaoBean implements Serializable {
	/**
	 * 0:筛选；1搜索；2正常频道
	 **/
	private int type;
	private String typeName;
	private String pindaoName;// 频道名称
	private String hrefurl;

	private String imagesrc;
	private String alt;
	private String mask_txt;
	private String mod_score;
	private String figure_desc;
	private String figure_info;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPindaoName() {
		return pindaoName;
	}

	public void setPindaoName(String pindaoName) {
		this.pindaoName = pindaoName;
	}

	public String getHrefurl() {
		return hrefurl;
	}

	public void setHrefurl(String hrefurl) {
		this.hrefurl = hrefurl;
	}

	public String getImagesrc() {
		return imagesrc;
	}

	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getMask_txt() {
		return mask_txt;
	}

	public void setMask_txt(String mask_txt) {
		this.mask_txt = mask_txt;
	}

	public String getMod_score() {
		return mod_score;
	}

	public void setMod_score(String mod_score) {
		this.mod_score = mod_score;
	}

	public String getFigure_desc() {
		return figure_desc;
	}

	public void setFigure_desc(String figure_desc) {
		this.figure_desc = figure_desc;
	}

	public String getFigure_info() {
		return figure_info;
	}

	public void setFigure_info(String figure_info) {
		this.figure_info = figure_info;
	}

}
