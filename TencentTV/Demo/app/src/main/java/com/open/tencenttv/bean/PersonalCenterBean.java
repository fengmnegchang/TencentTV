package com.open.tencenttv.bean;

import java.io.Serializable;

/**
 * ****************************************************************************************************************************************************************************
 * 左下角 个人中心+最近观看+我的应用
 * @author :fengguangjing
 * @createTime: 16/11/1
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class PersonalCenterBean implements Serializable {
    /**
     * 0上传观看；1最近观看；2我的应用
     **/
    private int type;
    private String typeName;//上传观看；最近观看；我的应用
    private String content;


    public int getType() {
        return type;
    }

    /**
     * 0上传观看；1最近观看；2我的应用
     **/
    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
