package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 * ****************************************************************************************************************************************************************************
 * 头部导航菜单
 * <a href="http://v.qq.com/tv/" class="link_nav" target="_blank" _stat="new_vs_header:pop_电视剧">电视剧</a>
 *
 * @author :fengguangjing
 * @createTime: 16/11/16
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class NavPopPinDaoBean implements Serializable {
    private String pindaoName;
    private String pindaoUrl;


    public String getPindaoName() {
        return pindaoName;
    }

    public void setPindaoName(String pindaoName) {
        this.pindaoName = pindaoName;
    }

    public String getPindaoUrl() {
        return pindaoUrl;
    }

    public void setPindaoUrl(String pindaoUrl) {
        this.pindaoUrl = pindaoUrl;
    }
}
