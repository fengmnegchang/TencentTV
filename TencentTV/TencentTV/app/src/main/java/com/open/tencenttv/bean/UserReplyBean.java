/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12上午10:21:18
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
 * @createTime:2016-12-12上午10:21:18
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserReplyBean {
	// private String first;//"1481508938.8248",
	// private String last;//"1481508938.8248",
	private String reqnum;// 10,
	private String retnum;// 1,
	private String total;// 1,
	private List<MessageBean> messages;// Array[1],
	// private UserMetaBean usermeta;//Object{...},
	//
	// private String comments;//Object{...},
	// private String articles;//Object{...},
	// private String users;//Object{...}

	public String getReqnum() {
		return reqnum;
	}

	public void setReqnum(String reqnum) {
		this.reqnum = reqnum;
	}

	public String getRetnum() {
		return retnum;
	}

	public void setRetnum(String retnum) {
		this.retnum = retnum;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<MessageBean> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageBean> messages) {
		this.messages = messages;
	}

}
