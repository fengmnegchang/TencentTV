package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/2
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class PinDaoBean implements Serializable {
    /**
     * 0:筛选；1搜索；2正常频道
     **/
    private int type;
    private String typeName;
    private String pindaoName;//频道名称

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
}
