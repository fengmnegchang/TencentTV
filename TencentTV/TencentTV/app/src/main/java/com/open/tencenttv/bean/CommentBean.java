/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12下午2:02:38
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12下午2:02:38
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class CommentBean {
	private String targetid;// 1663036976,
	private String appid;// 10098,
	private String parent;// "6213952427956712408",
	private String time;//1481521814,
	private String userid;// 493294704,
	private String nick;// "御守",
	private String head;// "http://q4.qlogo.cn/g?b=qq&k=wSLCLgsnNYsxT924yLUn3Q&s=40&t=1481472000",
	
	private String content;// "噢噢噢",
	private String up;// 0,
	private String poke;// 0,
	private String repnum;// 0,
	// "checkhotscale;//0,
	// "checkstatus;//1,
	// "isdeleted;//0,
	// "address;//"",
	// "location;//"",
	// "richtype;//0,
	// "rootid;//"6213952427956712408",
	private String title;// "",
	private List<UserBean> upusers;
	
	
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUp() {
		return up;
	}
	public void setUp(String up) {
		this.up = up;
	}
	public String getPoke() {
		return poke;
	}
	public void setPoke(String poke) {
		this.poke = poke;
	}
	public String getRepnum() {
		return repnum;
	}
	public void setRepnum(String repnum) {
		this.repnum = repnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<UserBean> getUpusers() {
		return upusers;
	}
	public void setUpusers(List<UserBean> upusers) {
		this.upusers = upusers;
	}

	// "abstract;//"",
	// "busstype;//"default"
	 

}
