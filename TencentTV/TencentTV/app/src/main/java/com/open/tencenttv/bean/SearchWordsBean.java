/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:10:23
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
 * 搜索内容
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午4:10:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchWordsBean implements Serializable {
	private String dc;// http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/hori/r/rz4mhb6494f12co.jpg
	private String pa;// 张静初 张歆艺 秦岚
	private String url;// http://v.qq.com/cover/r/rz4mhb6494f12co.html
	private String word;// 咱们相爱吧
	private String sn;

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
