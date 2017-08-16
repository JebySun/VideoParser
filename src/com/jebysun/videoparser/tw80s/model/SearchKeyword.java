package com.jebysun.videoparser.tw80s.model;

import java.io.Serializable;
/**
 * 热门搜索关键字
 * @author Administrator
 *
 */
public class SearchKeyword implements Serializable {
	private static final long serialVersionUID = 2389063484827433001L;
	
	private String title;
	private String link;
	private KeywordType type;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public KeywordType getType() {
		return type;
	}
	
	public void setType(KeywordType type) {
		this.type = type;
	}



	/**
	 * 关键字类型
	 * @author Administrator
	 *
	 */
	public enum KeywordType {
		MOVIE,   //电影
		TV,      //电视剧
		MANGA,   //动漫
		VARIETY, //综艺
		OTHER,   //其他
		TOPIC    //专题
	}

}
