package com.jebysun.videoparser.cfg;

/**
 * 项目配置
 * @author Jeby Sun
 * @Date 2015-09-01
 */
public final class Config {
	
	private Config() {}
	
	//电影库域名
	public static final String DOMAIN = "http://www.ygdy8.com";

	//电影查询路径
	public static final String MOVIE_PATH = "/html/gndy/dyzz/list_23_$.html";
	
	//电视剧查询路径
	public static final String TV_SERIE_PATH = "/html/tv/hytv/list_71_$.html";

	//综艺查询路径
	public static final String ZONGYI_PATH = "/html/zongyi2013/list_99_$.html";
	
	//动漫查询路径
	public static final String DONGMAN_PATH = "/html/dongman/list_16_$.html";

	//电影关键字搜索地址
	public static final String SEARCH_URL = "http://www.ygdy8.com/plus/search.php?keyword=$&searchtype=title&channeltype=0&orderby=&kwtype=0&pagesize=$&typeid=0&PageNo=$";

	//获取信息超时时间(秒)
	public static final int TIMEOUT = 7;

}
