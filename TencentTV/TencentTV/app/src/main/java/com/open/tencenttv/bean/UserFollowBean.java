/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:35:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

/**
 ***************************************************************************************************************************************************************************** 
 * 用户看单
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午9:35:17
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserFollowBean {
	// http://like.video.qq.com/fcgi-bin/flw_new?otype=json&sn=FollowServer&cmd=2562&pidx=0&size=30&dtype=0&type=0&callback=jQuery19106935243788063994_1481188648206&g_tk=629796729&_=1481188648207
	// "c_checkup_time;//"2016-12-08 23:48:28",
	private String c_cover_id;// 6glb931a5u1twhz
								// https://v.qq.com/x/cover/6glb931a5u1twhz.html
	// "c_exclusive;//0,
	// "c_is_trailer;//0,
	// "c_lid;//"a0022y6ceay+3",
	// "c_outsite_flag;//0,
	// c_pay_status;//8,
	// c_pic2_url;//"http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/hori/6/6glb931a5u1twhz.jpg",
	// "c_publish_date;//"2010-01-01 00:00:00",
	// c_second_title;//"献礼长征胜利80周年",
	private String c_timelong;// "更新至33集",
	private String c_title;// "长征大会师",
	// "c_type;//2,
	private String c_type_name;// "电视剧",
	// c_update_desc;//"每周一至周五晚24点更新两集",
	// "c_vclips;//"n0022fscaok+p00227wn4az+u00228gizf2+m0022ydtvua+e002226mndn+h0022t1f54i+j002262nmaf+f0022wagyp5+b0022crdw5x+y0022hkz9bo+b0022ozenv5+z0022j93ist+m0022viufir+t0022c0ywb7+m0022740aeg+l0022vtrvw2+n0022w5449t+w00226yif4i+y0022hcrhkk+m00225mg85l+y0022cp8x6q+u0022hckacu+u00224i374u+c00225lsqt5+v0022i1q0xl+m0022e7symr+b00226lgcoj+p0022oi0mba+f0022jcidtu+q0022e6twdl+s00228pb4kk+f00221yb10t+n0022a6c9vs",
	// "c_vids;//"z0022m21yj0+b0022iz4yz3+a0022y6ceay+f0022bj8fpj+d0022m6chmu+q0022qzga9n+v00226y14ny+n0022dmeetc+g00225x2o2z+n00226pkxjj+s0022tk0g1h+d0022huqem3+l00220phibg+t0022tn5itk+i0022q6mzo1+f0022li00wu+s0022cofn70+q0022jci5dc+o0022ou6n3x+u0022pi89w4+i0022950e70+r002241mvck+e0022jl57dv+i0022514ieq+c0022ia9cb2+w0022msupev+o0022huzfzg+h0022o0j0o9+e0022hfw659+r0022k183s6+q0022067exn+h0022s4anqp+v0022wxjubu+f0022cpqb6g+o0022ub4m52",
	// "cli_imgtag;//"",
	// "followID;//"6glb931a5u1twhz",
	// "highlight;//0,
	// "imgtag;//Object{...},
	// "inserttime;//"2016-12-08 17:16:11",
	private String pic;// "http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/hori/6/6glb931a5u1twhz.jpg",

	private String c_vid;
	// "srctype;//1

	public String getC_timelong() {
		return c_timelong;
	}

	public void setC_timelong(String c_timelong) {
		this.c_timelong = c_timelong;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getC_type_name() {
		return c_type_name;
	}

	public void setC_type_name(String c_type_name) {
		this.c_type_name = c_type_name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getC_cover_id() {
		return c_cover_id;
	}

	public void setC_cover_id(String c_cover_id) {
		this.c_cover_id = c_cover_id;
	}

	public String getC_vid() {
		return c_vid;
	}

	public void setC_vid(String c_vid) {
		this.c_vid = c_vid;
	}

}
