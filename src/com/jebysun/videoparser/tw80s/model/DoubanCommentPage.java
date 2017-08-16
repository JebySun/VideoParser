package com.jebysun.videoparser.tw80s.model;

import java.io.Serializable;
import java.util.List;

/**
 * 豆瓣短评页，包括当页短评列表和下一页短评开始序号。
 * @author Administrator
 *
 */
public class DoubanCommentPage implements Serializable {
	private static final long serialVersionUID = -6184521344491581247L;

	//当页短评列表
	private List<DoubanComment> commentList;
	//下一页短评开始序号
	private int nextPageStart;
	
	public DoubanCommentPage(List<DoubanComment> commentList, int nextPageStart) {
		this.commentList = commentList;
		this.nextPageStart = nextPageStart;
	}
	
	public List<DoubanComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<DoubanComment> commentList) {
		this.commentList = commentList;
	}

	public int getNextPageStart() {
		return nextPageStart;
	}
	public void setNextPageStart(int nextPageStart) {
		this.nextPageStart = nextPageStart;
	}
	
	
	
}
