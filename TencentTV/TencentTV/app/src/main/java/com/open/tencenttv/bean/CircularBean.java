package com.open.tencenttv.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 * 弹出菜单bean
 *
 * @author :fengguangjing
 * @createTime: 16/11/14
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class CircularBean implements Serializable {
    private List<PanBean> panList;
    private int xoff;//x坐标位置
    private int yoff;//y坐标位置
    private String centerValue;


    public List<PanBean> getPanList() {
        return panList;
    }

    public void setPanList(List<PanBean> panList) {
        this.panList = panList;
    }

    public int getXoff() {
        return xoff;
    }

    public void setXoff(int xoff) {
        this.xoff = xoff;
    }

    public int getYoff() {
        return yoff;
    }

    public void setYoff(int yoff) {
        this.yoff = yoff;
    }

    public String getCenterValue() {
        return centerValue;
    }

    public void setCenterValue(String centerValue) {
        this.centerValue = centerValue;
    }
}
