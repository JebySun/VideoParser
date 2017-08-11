package com.jebysun.videoparser.tw80s;

/**
 * 资源配置
 * 80s手机电影http://www.80s.tw
 * @author Jeby Sun
 * @Date 2015-12-14
 */
final class Config {	
	
	private Config() {}
	
	//影视资源域名
	public static final String DOMAIN = "http://www.80s.tw";
	
	//电影分类 参数依次为类别，年份，地区，语言，排序，分页序号
	public static final String MOVIE_QUERY_PATH = "/movie/list/$category-$year-$area-$language-$sort-$page";
	
	//电视剧分类 参数依次为类别，年代，地区，排序，分页序号
	public static final String TV_QUERY_PATH = "/ju/list/$category---$year-$area-$sort-$page";
	
	//综艺分类
	public static final String VARIETY_QUERY_PATH = "/zy/list/----4-$sort-$page";

	//动漫分类
	public static final String MANGA_QUERY_PATH = "/dm/list/$type----14-$sort-$page";
	
	//影视搜索
	//POST请求
	//参数keyword搜索关键字
	public static final String VIDEO_SEARCH_PATH = "/search";
	
	//获取信息超时时间(秒)
	public static final int TIMEOUT = 10;
	

	

}
