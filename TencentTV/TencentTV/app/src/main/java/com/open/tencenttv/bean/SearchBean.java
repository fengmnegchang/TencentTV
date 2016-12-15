/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午2:13:54
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
 * 
 * @author :fengguangjing
 * @createTime:2016-12-7下午2:13:54
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SearchBean implements Serializable {

	/**
	 * <a href="http://v.qq.com/x/page/l0022wd3x1u.html"
	 * class="figure result_figure" target="_blank" _stat="video:poster_h"> <img
	 * src="//puui.qpic.cn/qqvideo_ori/0/l0022wd3x1u_496_280/0" r-imgerr="h"
	 * alt="《如果蜗牛有爱情》18王子文cut" /> <span class="figure_caption"><span
	 * class="figure_info">0:28:34</span></span> </a>
	 */
	private String figure_info;
	private String result_figure_href;
	private String result_figure_src;
	private String result_figure_alt;

	/**
	 * <h2 class="result_title"><a
	 * href="http://v.qq.com/x/page/l0022wd3x1u.html" target="_blank"
	 * _stat="video:poster_h_title">《<em class="hl">如果蜗牛有爱情</em>》18王子文cut</a></h2>
	 */
	/**
	 * <div class="result_info"> <div class="info_line"> <div
	 * class="info_item info_item_odd"> <span class="label">时　间：</span> <span
	 * class="content">2016-12-05</span> </div> <div
	 * class="info_item info_item_odd"> <span class="label">播放量：</span> <span
	 * class="content">829</span> </div> </div> <div class="info_line"> <div
	 * class="info_item"> <span class="label">主　题：</span> <span class="content"
	 * title="王凯王子文玩命缉凶">王凯王子文玩命缉凶</span> </div> </div> </div> </div>
	 */
	private List<StarInfo> result_info;

	public String getFigure_info() {
		return figure_info;
	}

	public void setFigure_info(String figure_info) {
		this.figure_info = figure_info;
	}

	public String getResult_figure_href() {
		return result_figure_href;
	}

	public void setResult_figure_href(String result_figure_href) {
		this.result_figure_href = result_figure_href;
	}

	public String getResult_figure_src() {
		return result_figure_src;
	}

	public void setResult_figure_src(String result_figure_src) {
		this.result_figure_src = result_figure_src;
	}

	public String getResult_figure_alt() {
		return result_figure_alt;
	}

	public void setResult_figure_alt(String result_figure_alt) {
		this.result_figure_alt = result_figure_alt;
	}

	public List<StarInfo> getResult_info() {
		return result_info;
	}

	public void setResult_info(List<StarInfo> result_info) {
		this.result_info = result_info;
	}

}
