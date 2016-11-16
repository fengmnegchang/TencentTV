package com.open.tencenttv.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * T公共类封装
 * 
 * @author think
 * 
 */
public class CommonT implements Serializable {
    private ArrayList<NavPopPinDaoBean> navpoplist = new ArrayList<NavPopPinDaoBean>();//头部导航菜单
    private ArrayList<NewFeatureBean> newfeaturelist = new ArrayList<NewFeatureBean>();//推荐组别


    public ArrayList<NavPopPinDaoBean> getNavpoplist() {
        return navpoplist;
    }

    public void setNavpoplist(ArrayList<NavPopPinDaoBean> navpoplist) {
        this.navpoplist = navpoplist;
    }


    public ArrayList<NewFeatureBean> getNewfeaturelist() {
        return newfeaturelist;
    }

    public void setNewfeaturelist(ArrayList<NewFeatureBean> newfeaturelist) {
        this.newfeaturelist = newfeaturelist;
    }
}
