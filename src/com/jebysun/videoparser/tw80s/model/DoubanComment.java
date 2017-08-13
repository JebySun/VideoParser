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
	
	private String userName;
	private String userAvatar;
	private String comment;
	private Date createDate;
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
	
}
