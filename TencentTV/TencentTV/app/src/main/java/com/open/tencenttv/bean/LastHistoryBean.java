package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 16/11/11
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class LastHistoryBean implements Serializable {
    private String dramaName;//电视名称
    private String dramaCount;//观看集数
    private String date;//日期
    private String itemTypeName;
    private int itemType;//1:观看2；应用;3:大家都在看

    public String getDramaName() {
        return dramaName;
    }

    public void setDramaName(String dramaName) {
        this.dramaName = dramaName;
    }

    public String getDramaCount() {
        return dramaCount;
    }

    public void setDramaCount(String dramaCount) {
        this.dramaCount = dramaCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

}
