/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-11-25上午9:50:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

import java.io.Serializable;
import java.util.List;

/**
 ***************************************************************************************************************************************************************************** 
 * 明星简介head
 * @author :fengguangjing
 * @createTime:2016-11-25上午9:50:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class StarBean implements Serializable {
	/**
	 * <div class="star_current"> <div class="star_info"
	 * r-component="c-starinfo" r-class="{star_info_open: showDetail}"
	 * data-region="starInfo"> <div class="head_portrait"> <img
	 * src="http://puui.qpic.cn/vstar_pic/0/name_77904_688t1467970955.jpg/0"
	 * alt=""> </div> <div class="starIntro"> <div class="starIntro_top"> <h4>胡歌
	 * </h4>
	 * <p class="function_group">
	 * <!-- <a href="javascript:;" class="editBtn"><i
	 * class="edit_icon"></i>编辑</a> --> <!-- <a href="javascript:;"
	 * class="subBtn"><i class="sub_icon"></i>订阅</a> -->
	 * </p>
	 * </div>
	 * <p class="star_briefIntro">
	 * <span>O型</span> <i>|</i> <span>处女座</span> <i>|</i> <span>185cm</span>
	 * </p>
	 * <p class="star_description" r-show="{!showDetail}">
	 * 胡歌，1982年9月20日生于上海，中国大陆影视男演员、歌手。14岁便成为上海教育电视台的小主持人，2001年考入上海戏剧学院表演系。
	 * 2005年因在电视剧《仙剑奇 ...<a href="javascript:;" class="details"
	 * r-on="{click: onUnfold}" data-boss="detail">详细<i></i></a>
	 * </p>
	 * <p class="star_description star_des_marginB" style="display: none" r-show="{showDetail}">
	 * 胡歌，1982年9月20日生于上海，中国大陆影视男演员、歌手。14岁便成为上海教育电视台的小主持人，2001年考入上海戏剧学院表演系。
	 * 2005年因在电视剧
	 * 《仙剑奇侠传》中成功塑造了豪爽深情的“李逍遥”一角而成名，并演唱插曲《六月的雨》《逍遥叹》而踏入歌坛。2009年在“80后新生代娱乐大明星
	 * ”评选中获封“四大小生”之一，并先后两次荣获华鼎奖传奇类电视剧最佳男演员。2010年，胡歌领衔主演的《神话》打破央视八套收视纪录。2011年，
	 * 胡歌在百年献礼大片《辛亥革命》中饰演“林觉民”一角，入围第31届百花奖最佳新人奖。2013年，胡歌成立了自己的工作室。
	 * </p>
	 * <ul class="starInfo_list" style="display: none" r-show="{showDetail}">
	 * <li>
	 * <span class="listLeft">英文名</span> <span class="listRight">Hugh</span></li>
	 * <li>
	 * <span class="listLeft">别名</span> <span class="listRight"> <i>胡柯</i>
	 * </span></li>
	 * <li>
	 * <span class="listLeft">性别</span> <span class="listRight">男</span></li>
	 * <li>
	 * <span class="listLeft">出生日期</span> <span
	 * class="listRight">1982-09-20</span></li>
	 * <li>
	 * <span class="listLeft">民族</span> <span class="listRight">汉族</span></li>
	 * <li>
	 * <span class="listLeft">地区</span> <span class="listRight">内地</span></li>
	 * <li>
	 * <span class="listLeft">职业 </span> <span class="listRight"> <i>演员</i>
	 * <i>歌手</i> <i>制片人</i> <i>餐厅老板</i> </span></li>
	 * <li>
	 * <span class="listLeft">爱好 </span> <span class="listRight"> <i>旅游</i>
	 * <i>篮球</i> <i>读书</i> <i>摄影</i> </span></li>
	 * </ul>
	 * <p class="pack_up_Btn" style="display: none" r-show="{showDetail}">
	 * <span r-on="{click: onFold}">收起<i class="pack_up_icon"></i></span>
	 * </p>
	 * </div> </div>
	 */
	private String head_portrait;
	private String starIntro_top;
	private String star_briefIntro;
	private String star_description;
	private List<StarInfo> starInfo_list;
	private List<StarRelateBean> starRelate_list;

	public String getHead_portrait() {
		return head_portrait;
	}

	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

	public String getStarIntro_top() {
		return starIntro_top;
	}

	public void setStarIntro_top(String starIntro_top) {
		this.starIntro_top = starIntro_top;
	}

	public String getStar_briefIntro() {
		return star_briefIntro;
	}

	public void setStar_briefIntro(String star_briefIntro) {
		this.star_briefIntro = star_briefIntro;
	}

	public String getStar_description() {
		return star_description;
	}

	public void setStar_description(String star_description) {
		this.star_description = star_description;
	}

	public List<StarInfo> getStarInfo_list() {
		return starInfo_list;
	}

	public void setStarInfo_list(List<StarInfo> starInfo_list) {
		this.starInfo_list = starInfo_list;
	}

	public List<StarRelateBean> getStarRelate_list() {
		return starRelate_list;
	}

	public void setStarRelate_list(List<StarRelateBean> starRelate_list) {
		this.starRelate_list = starRelate_list;
	}

	

}
