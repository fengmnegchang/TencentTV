/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25下午4:29:50
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
 * @createTime:2016-11-25下午4:29:50
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class AllBean implements Serializable {
	public AllBean(String className, String functionDesp) {
		super();
		this.className = className;
		this.functionDesp = functionDesp;
	}

	private String className;
	private String functionDesp;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFunctionDesp() {
		return functionDesp;
	}

	public void setFunctionDesp(String functionDesp) {
		this.functionDesp = functionDesp;
	}

}
