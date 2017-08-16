package com.jebysun.videoparser.tw80s.utils;

import com.jebysun.videoparser.tw80s.model.SearchKeyword.KeywordType;
import com.jebysun.videoparser.tw80s.model.Video.VideoType;

public class Tw80sUtil {
	private Tw80sUtil() {}
	
	/**
	 * 从视频的豆瓣短评url获取豆瓣视频ID
	 * @param commentUrl 豆瓣短评url
	 * @return 豆瓣视频ID
	 */ 
	public static String getDoubanIdFromCommentUrl(String commentUrl) {
    	String sid = null;
    	if (commentUrl == null) {
    		sid = null;
    	}
    	String[] urlSplits = commentUrl.split("/");
    	if (urlSplits.length == 6) {
    		sid = urlSplits[4];
    	}
    	return sid;
    }
	
	
	/**
	 * 根据视频URL片段判断视频类型
	 * @param url
	 * @return
	 */
	public static VideoType getVideoTypeByURL(String url) {
		if (url.contains("/movie/")) {
			return VideoType.MOVIE;
		} else if (url.contains("/ju/")) {
			return VideoType.TV;
		} else if (url.contains("/zy/")) {
			return VideoType.VARIETY;
		} else if (url.contains("/dm/")) {
			return VideoType.MANGA;
		}
		return VideoType.OTHER;
	}
	
	/**
	 * 根据视频标题判断视频类型
	 * @param title
	 * @return
	 */
	public static VideoType getVideoTypeByTitle(String title) {
		String typeKey = title.substring(1, title.indexOf("]"));
		VideoType type = null;
		switch (typeKey) {
			case "电影":
				type = VideoType.MOVIE;
				break;
			case "大陆剧":
				type = VideoType.TV;
				break;
			case "港台剧":
				type = VideoType.TV;
				break;
			case "日韩剧":
				type = VideoType.TV;
				break;
			case "欧美剧":
				type = VideoType.TV;
				break;
			case "综艺":
				type = VideoType.VARIETY;
				break;
			case "动漫":
				type = VideoType.MANGA;
				break;
			default:
				type = VideoType.OTHER;
		}
		return type;
	}
	
	/**
	 * 根据关键字url判断关键字类型
	 * @param url
	 * @return
	 */
	public static KeywordType getKeywordTypeByURL(String urlStr) {
		if (urlStr.contains("/movie/")) {
			return KeywordType.MOVIE;
		} else if (urlStr.contains("/ju/")) {
			return KeywordType.TV;
		} else if (urlStr.contains("/zy/")) {
			return KeywordType.VARIETY;
		} else if (urlStr.contains("/dm/")) {
			return KeywordType.MANGA;
		} else if (urlStr.contains("/tag/")) {
			return KeywordType.TOPIC;
		}
		return KeywordType.OTHER;
	}
	
	/**
	 * 从下一页URL字符串中获取下一页开始序号，第一页开始序号默认是0。
	 * @param urlStr 下一页URL字符串
	 * @return 下一页开始序号
	 */
	public static int getPageStartFromUrl(String urlStr) {
		String paramStr = urlStr.substring(urlStr.indexOf("?") + 1);
		String[] paramArr = paramStr.split("&");
		for (String p : paramArr) {
			if (p.split("=")[0].equals("start")) {
				return Integer.parseInt(p.split("=")[1]);
			}
		}
		return 0;
	}
	
	
}











