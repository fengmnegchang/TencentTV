/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午10:53:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.json;

import java.util.List;

import com.open.tencenttv.bean.UserVFollowlstBean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午10:53:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserVFollowlstJson extends CommonTJson {
	private String hasfollow;// 0,
	private String pagenum;// 1,
	private String pagesize;// 5,
	private String pagetotal;// 1,
	private String s;// o",
	private String total;// 4,
	private List<UserVFollowlstBean> vpp;//

	public String getHasfollow() {
		return hasfollow;
	}

	public void setHasfollow(String hasfollow) {
		this.hasfollow = hasfollow;
	}

	public String getPagenum() {
		return pagenum;
	}

	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getPagetotal() {
		return pagetotal;
	}

	public void setPagetotal(String pagetotal) {
		this.pagetotal = pagetotal;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<UserVFollowlstBean> getVpp() {
		return vpp;
	}

	public void setVpp(List<UserVFollowlstBean> vpp) {
		this.vpp = vpp;
	}

}
