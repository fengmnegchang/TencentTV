/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12上午10:23:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.tencenttv.bean;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2016-12-12上午10:23:23
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MessageBean {
	private String id;// "6213898866371509311",
	private String parent;// "6213897320984392608",
	private String targetid;// "1663035439",
	private String tipstype;// 3,
	private String tipstime;// 1481508938.8248

	private CommentBean myComment;
	private CommentBean replyComment;
	private ArticleBean article;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getTargetid() {
		return targetid;
	}

	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}

	public String getTipstype() {
		return tipstype;
	}

	public void setTipstype(String tipstype) {
		this.tipstype = tipstype;
	}

	public String getTipstime() {
		return tipstime;
	}

	public void setTipstime(String tipstime) {
		this.tipstime = tipstime;
	}
 
	public ArticleBean getArticle() {
		return article;
	}

	public void setArticle(ArticleBean article) {
		this.article = article;
	}

	public CommentBean getMyComment() {
		return myComment;
	}

	public void setMyComment(CommentBean myComment) {
		this.myComment = myComment;
	}

	public CommentBean getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(CommentBean replyComment) {
		this.replyComment = replyComment;
	}

}
