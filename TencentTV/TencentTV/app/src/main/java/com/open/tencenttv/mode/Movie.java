package com.open.tencenttv.mode;

/**
 * 用于Leanback 的数据测试.
 *  /***
 * <li class="list_item">
 <a href="http://v.qq.com/cover/8/8479ahinkqm7czh.html" target="_blank" class="figure" tabindex="-1" title="锦绣未央" _hot="tv.global.small_img1">
 <img lz_src="http://puui.qpic.cn/tv/0/5793858_175100/0" alt="锦绣未央" onerror="picerr(this,16)">
 <span class="figure_mask"><em class="mask_txt">更新至11集</em></span>
 </a>
 <strong class="figure_title"><a href="http://v.qq.com/cover/8/8479ahinkqm7czh.html" target="_blank" title="锦绣未央" _hot="tv.global.small_img1">锦绣未央</a></strong>
 <p class="figure_desc">唐嫣KO李氏兄妹绝处逢生</p>
 </li>
 */
public class Movie {
    private int mRes;
    private String mTitle;
    private String hrefurl;
    private String lz_srcurl;
    private String figure_mask;//更新至11集
    private String figure_desc;//唐嫣KO李氏兄妹绝处逢生


    public Movie(){

    }

    public Movie(int res, String title) {
        this.mRes = res;
        this.mTitle = title;
    }

    public int getRes() {
        return this.mRes;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getmRes() {
        return mRes;
    }

    public void setmRes(int mRes) {
        this.mRes = mRes;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getHrefurl() {
        return hrefurl;
    }

    public void setHrefurl(String hrefurl) {
        this.hrefurl = hrefurl;
    }

    public String getLz_srcurl() {
        return lz_srcurl;
    }

    public void setLz_srcurl(String lz_srcurl) {
        this.lz_srcurl = lz_srcurl;
    }

    public String getFigure_mask() {
        return figure_mask;
    }

    public void setFigure_mask(String figure_mask) {
        this.figure_mask = figure_mask;
    }

    public String getFigure_desc() {
        return figure_desc;
    }

    public void setFigure_desc(String figure_desc) {
        this.figure_desc = figure_desc;
    }
}
