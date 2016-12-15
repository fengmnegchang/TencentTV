/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-23上午10:06:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.jayfang.dropdownmenu;

import java.io.Serializable;
import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-11-23上午10:06:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class DropItemBean implements Serializable {
	private List<MenuBean> menulist;
	private String label;

	public List<MenuBean> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<MenuBean> menulist) {
		this.menulist = menulist;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
