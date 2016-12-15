/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-13下午2:42:50
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

/**
 ***************************************************************************************************************************************************************************** 
 * V币
 * @author :fengguangjing
 * @createTime:2016-12-13下午2:42:50
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserBillBean {
	private String consumeDesc;// "+5",
	private String consumeDescColor;// "#222222",
	private String consumeEvent;// "打开并登录腾讯视频app",
	private String ctx;// "taskid=7",
	private String date;// "2016-12-13 09:51:10",
	private String receiver;// "",
	private int receiverType;// 0

	public String getConsumeDesc() {
		return consumeDesc;
	}

	public void setConsumeDesc(String consumeDesc) {
		this.consumeDesc = consumeDesc;
	}

	public String getConsumeDescColor() {
		return consumeDescColor;
	}

	public void setConsumeDescColor(String consumeDescColor) {
		this.consumeDescColor = consumeDescColor;
	}

	public String getConsumeEvent() {
		return consumeEvent;
	}

	public void setConsumeEvent(String consumeEvent) {
		this.consumeEvent = consumeEvent;
	}

	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(int receiverType) {
		this.receiverType = receiverType;
	}

}
