/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:45:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.io.Serializable;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午1:45:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class CommonTJson implements Serializable {
	private int costtime;
	private String modify_time;
	private int returncode;
	private String returnmsg;
	private String write_time;

	public int getCosttime() {
		return costtime;
	}

	public void setCosttime(int costtime) {
		this.costtime = costtime;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public int getReturncode() {
		return returncode;
	}

	public void setReturncode(int returncode) {
		this.returncode = returncode;
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}

	public String getWrite_time() {
		return write_time;
	}

	public void setWrite_time(String write_time) {
		this.write_time = write_time;
	}

}
