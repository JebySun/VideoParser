package com.jebysun.videoparser.tw80s.utils;

import com.jebysun.videoparser.tw80s.param.VideoType;

public class Tw80sUtil {
	private Tw80sUtil() {}
	
	
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
	public static String getVideoTypeByURL(String url) {
		if (url.startsWith("/movie/")) {
			return VideoType.DY;
		} else if (url.startsWith("/ju/")) {
			return VideoType.DSJ;
		} else if (url.startsWith("/zy/")) {
			return VideoType.ZY;
		} else if (url.startsWith("/dm/")) {
			return VideoType.DM;
		}
		return VideoType.OTHER;
	}
	

	public static String getVideoTypeByTitle(String title) {
		String typeKey = title.substring(1, title.indexOf("]"));
		String type = null;
		switch (typeKey) {
			case "电影":
				type = VideoType.DY;
				break;
			case "大陆剧":
				type = VideoType.DSJ;
				break;
			case "港台剧":
				type = VideoType.DSJ;
				break;
			case "日韩剧":
				type = VideoType.DSJ;
				break;
			case "欧美剧":
				type = VideoType.DSJ;
				break;
			case "综艺":
				type = VideoType.ZY;
				break;
			case "动漫":
				type = VideoType.DM;
				break;
			default:
				type = VideoType.OTHER;
		}
		return type;
	}
}











