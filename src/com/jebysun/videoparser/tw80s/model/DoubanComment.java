package com.jebysun.videoparser.tw80s.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 豆瓣短评
 * @author Jeby Sun
 * @Date 2017-04-14
 */
public class DoubanComment implements Serializable {
	private static final long serialVersionUID = 8393228485198006735L;

	//豆瓣用户昵称
	private String userName;
	//豆瓣用户头像URL
	private String userAvatar;
	//豆瓣用户主页
	private String userPageUrl;
	//豆瓣用户评论
	private String comment;
	//豆瓣用户打分
	private RatingLevel ratingLevel;
	//短评创建日期
	private Date createDate;
	//投票数
	private int thumbsUpCount;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getThumbsUpCount() {
		return thumbsUpCount;
	}
	public void setThumbsUpCount(int thumbsUpCount) {
		this.thumbsUpCount = thumbsUpCount;
	}
	
	public RatingLevel getRatingLevel() {
		return ratingLevel;
	}
	public void setRatingLevel(RatingLevel ratingLevel) {
		this.ratingLevel = ratingLevel;
	}
	public String getUserPageUrl() {
		return userPageUrl;
	}
	public void setUserPageUrl(String userPageUrl) {
		this.userPageUrl = userPageUrl;
	}
	
	
	public enum RatingLevel {
		LEVEL_1("很差"),
		LEVEL_2("较差"),
		LEVEL_3("还行"),
		LEVEL_4("推荐"),
		LEVEL_5("力荐");
		private String ratingValue;
		private RatingLevel(String ratingValue) {
			this.ratingValue = ratingValue;
		}
		
		public String getRatingValue() {
			return ratingValue;
		}
		
	}
	
	
	
}






