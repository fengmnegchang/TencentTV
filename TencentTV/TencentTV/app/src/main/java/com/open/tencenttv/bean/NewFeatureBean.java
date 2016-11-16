package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 * ****************************************************************************************************************************************************************************
 * 为你精选 等组别
 *
 * @author :fengguangjing
 * @createTime: 16/11/16
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class NewFeatureBean implements Serializable {
    private String title;//标题
    private String alt;//副标题
    private String imageUrl;//图片地址
    private String hrefUrl;//点击观看url
    private String figure_desc;//提示


    private int itemType;
    private String itemTypeName;
    private String itemTypeUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }

    public String getFigure_desc() {
        return figure_desc;
    }

    public void setFigure_desc(String figure_desc) {
        this.figure_desc = figure_desc;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getItemTypeUrl() {
        return itemTypeUrl;
    }

    public void setItemTypeUrl(String itemTypeUrl) {
        this.itemTypeUrl = itemTypeUrl;
    }
}
