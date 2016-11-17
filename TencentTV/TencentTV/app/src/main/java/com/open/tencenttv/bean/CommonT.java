package com.open.tencenttv.bean;

import android.graphics.Bitmap;

import com.open.androidtvwidget.leanback.mode.ListRow;

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
    private ArrayList<ListRow> mediumlist = new ArrayList<ListRow>();//频道分类数据
    private ArrayList<SliderNavBean> sliderNavlist = new ArrayList<SliderNavBean>();//导航大图
    private Bitmap bitmap;

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

    public ArrayList<ListRow> getMediumlist() {
        return mediumlist;
    }

    public void setMediumlist(ArrayList<ListRow> mediumlist) {
        this.mediumlist = mediumlist;
    }

    public ArrayList<SliderNavBean> getSliderNavlist() {
        return sliderNavlist;
    }

    public void setSliderNavlist(ArrayList<SliderNavBean> sliderNavlist) {
        this.sliderNavlist = sliderNavlist;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
