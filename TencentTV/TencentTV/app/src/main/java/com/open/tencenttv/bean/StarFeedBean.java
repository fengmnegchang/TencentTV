/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-5下午4:25:02
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-5下午4:25:02
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarFeedBean {
	/**
	 * <div class="feed_item_relative cf"> <span
	 * class="info_figure_date"><i>2016</i>12月01日</span> <i
	 * class="ico_timeline_circle"></i> </div> <strong class="feed_title"><a
	 * href="http://ent.qq.com/a/20161201/000529.htm" target="_blank"
	 * data-boss="title">《那年夏天》发特辑 胡歌出演“最坏的角色”</a></strong> <div
	 * class="feed_info cf"> <div class="figures_scroll"> <div
	 * class="scroll_item" style="position: relative;"> <div
	 * style="width: 99999px;"> <div style=
	 * "display: inline-block;position: relative;left: 0;font-size: 0px;display: inline-block;*zoom: 1;*display: inline;"
	 * class="star-move-slider">
	 * 
	 * <a href="http://ent.qq.com/a/20161201/000529.htm" target="_blank"
	 * style="width:auto;" data-boss="slide"><img
	 * src="http://img1.gtimg.com/18/1833/183396/18339652_980x1200_0.jpg"
	 * style="width: auto;height: 118px; margin-right: 4px;"/></a>
	 * 
	 * <a href="http://ent.qq.com/a/20161201/000529.htm" target="_blank"
	 * style="width:auto;" data-boss="slide"><img
	 * src="http://img1.gtimg.com/18/1833/183396/18339647_980x1200_0.jpg"
	 * style="width: auto;height: 118px; margin-right: 4px;"/></a>
	 * 
	 * <a href="http://ent.qq.com/a/20161201/000529.htm" target="_blank"
	 * style="width:auto;" data-boss="slide"><img
	 * src="http://img1.gtimg.com/18/1833/183396/18339648_980x1200_0.jpg"
	 * style="width: auto;height: 118px; margin-right: 4px;"/></a>
	 * 
	 * <a href="http://ent.qq.com/a/20161201/000529.htm" target="_blank"
	 * style="width:auto;" data-boss="slide"><img
	 * src="http://img1.gtimg.com/18/1833/183396/18339649_980x1200_0.jpg"
	 * style="width: auto;height: 118px; margin-right: 4px;"/></a>
	 * 
	 * <a href="http://ent.qq.com/a/20161201/000529.htm" target="_blank"
	 * style="width:auto;" data-boss="slide"><img
	 * src="http://img1.gtimg.com/18/1833/183396/18339650_980x1200_0.jpg"
	 * style="width: auto;height: 118px; margin-right: 4px;"/></a>
	 * 
	 * <a href="http://ent.qq.com/a/20161201/000529.htm" target="_blank"
	 * style="width:auto;" data-boss="slide"><img
	 * src="http://img1.gtimg.com/18/1833/183396/18339651_980x1200_0.jpg"
	 * style="width: auto;height: 118px; margin-right: 4px;"/></a>
	 * 
	 * </div> </div> </div>
	 */
	private String info_figure_date;
	private String feed_title;
	private String feed_title_href;
	private String figures_scroll_href;
	private List<String> figures_scroll_data_boss;

	/**
	 * <div class="feed_desc"> <div class="tag_list cf"> <a
	 * href="http://ent.qq.com/dc_unique_article/tagsList.htm?tags=那年夏天"
	 * target="_blank" class="tag_item" title="那年夏天" data-boss="tag"><span
	 * class="tag_inner">那年夏天</span></a> </div> <span
	 * class="feed_item_time">腾讯娱乐 2016-12-01 00:29</span> </div>
	 */
	private String feed_desc_title;
	private String feed_desc_href;
	private String feed_item_time;

	public String getInfo_figure_date() {
		return info_figure_date;
	}

	public void setInfo_figure_date(String info_figure_date) {
		this.info_figure_date = info_figure_date;
	}

	public String getFeed_title() {
		return feed_title;
	}

	public void setFeed_title(String feed_title) {
		this.feed_title = feed_title;
	}

	public String getFeed_title_href() {
		return feed_title_href;
	}

	public void setFeed_title_href(String feed_title_href) {
		this.feed_title_href = feed_title_href;
	}

	public String getFigures_scroll_href() {
		return figures_scroll_href;
	}

	public void setFigures_scroll_href(String figures_scroll_href) {
		this.figures_scroll_href = figures_scroll_href;
	}

	public List<String> getFigures_scroll_data_boss() {
		return figures_scroll_data_boss;
	}

	public void setFigures_scroll_data_boss(List<String> figures_scroll_data_boss) {
		this.figures_scroll_data_boss = figures_scroll_data_boss;
	}

	public String getFeed_desc_title() {
		return feed_desc_title;
	}

	public void setFeed_desc_title(String feed_desc_title) {
		this.feed_desc_title = feed_desc_title;
	}

	public String getFeed_desc_href() {
		return feed_desc_href;
	}

	public void setFeed_desc_href(String feed_desc_href) {
		this.feed_desc_href = feed_desc_href;
	}

	public String getFeed_item_time() {
		return feed_item_time;
	}

	public void setFeed_item_time(String feed_item_time) {
		this.feed_item_time = feed_item_time;
	}

}
