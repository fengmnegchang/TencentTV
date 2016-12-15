/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午2:36:20
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
 * 球星球队
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9下午2:36:20
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserStarTeamBean {
	private String followID;// 199737",
	private String highlight;// 0,
	private List<UserFollowBean> itemlist;// Array[4],
	private String name;// 埃尔克森-德奥利维拉",
	private String name_id;// 199737,
	private String srctype;// 36
	private String pic;

	public String getFollowID() {
		return followID;
	}

	public void setFollowID(String followID) {
		this.followID = followID;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public List<UserFollowBean> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<UserFollowBean> itemlist) {
		this.itemlist = itemlist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_id() {
		return name_id;
	}

	public void setName_id(String name_id) {
		this.name_id = name_id;
	}

	public String getSrctype() {
		return srctype;
	}

	public void setSrctype(String srctype) {
		this.srctype = srctype;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
