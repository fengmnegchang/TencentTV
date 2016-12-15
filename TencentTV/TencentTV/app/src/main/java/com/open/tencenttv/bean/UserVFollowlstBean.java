/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午10:50:42
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
 * 向您推荐这些热门V+用户
 * 
 * @author :fengguangjing
 * @createTime:2016-12-9上午10:50:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class UserVFollowlstBean {
	private String avatar;// https://puui.qpic.cn/video_caps_enc/Ybwz6W2HZ457mria4tmEptkPWlWvzKFm12qMWoPN8ibv5Pk4eOVDWY0Q/0",
	private String followcount;// 0,
	private String isvplus;// 1,
	private String nick;// 冷知识",
	private String uin;// 2769020667",
	private String url;// cool",
	private List<VSbean> vs;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFollowcount() {
		return followcount;
	}

	public void setFollowcount(String followcount) {
		this.followcount = followcount;
	}

	public String getIsvplus() {
		return isvplus;
	}

	public void setIsvplus(String isvplus) {
		this.isvplus = isvplus;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<VSbean> getVs() {
		return vs;
	}

	public void setVs(List<VSbean> vs) {
		this.vs = vs;
	}

	public class VSbean {
		private String duration;// 00:03:29",
		private String pic;// http://vpic.video.qq.com/90336074/n0353gs1tzx_160_90_3.jpg",
		private String title;// 《冷知识第八季》第8期：脸越揉越小，胸越揉越大？",
		private String url;// http://v.qq.com/page/n/z/x/n0353gs1tzx.html",
		private String vid;// n0353gs1tzx"

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getVid() {
			return vid;
		}

		public void setVid(String vid) {
			this.vid = vid;
		}

	}

}
