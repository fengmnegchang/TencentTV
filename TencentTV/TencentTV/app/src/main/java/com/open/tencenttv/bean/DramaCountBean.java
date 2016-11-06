package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 * ****************************************************************************************************************************************************************************
 *  分页处理
 *
 * @author :fengguangjing
 * @createTime: 2016/11/6
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class DramaCountBean implements Serializable {
    private int pagerSize;//页数
    private int pagerCount;//每页条数
    private String dramaName;//第几集
    private int dramaType;//0 : 页数；1：集数


    public int getPagerSize() {
        return pagerSize;
    }

    public void setPagerSize(int pagerSize) {
        this.pagerSize = pagerSize;
    }

    public int getPagerCount() {
        return pagerCount;
    }

    public void setPagerCount(int pagerCount) {
        this.pagerCount = pagerCount;
    }

    public String getDramaName() {
        return dramaName;
    }

    public void setDramaName(String dramaName) {
        this.dramaName = dramaName;
    }

    public int getDramaType() {
        return dramaType;
    }

    public void setDramaType(int dramaType) {
        this.dramaType = dramaType;
    }
}
