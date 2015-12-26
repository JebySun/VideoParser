package com.jebysun.videoparser.vdieo80s;

/**
 * 资源配置
 * 80s手机电影http://www.80s.la
 * @author Jeby Sun
 * @Date 2015-12-14
 */
final class Config {	
	
	private Config() {}
	
	//影视资源域名
	public static final String DOMAIN = "http://www.80s.la";
	
	//电影分类 参数依次为类别，年份，地区，语言，排序，分页序号
	public static final String MOVIE_PATH = "/movie/list/$category-$year-$area-$language-$sort-$page";
	
	//电视剧分类
	public static final String TV_SERIE_PATH = "/ju/list/------";
	
	//综艺分类
	public static final String ZONGYI_PATH = "/zy/list/------";

	//动漫分类
	public static final String DONGMAN_PATH = "/dm/list/------";
	
	//影视搜索
	//POST请求
	//参数1，search_typeid搜索类型（本站搜索search_typeid=1）
	//参数2，skey关键字
	public static final String VIDEO_SEARCH_PATH = "/movie/search";
	
	//获取信息超时时间(秒)
	public static final int TIMEOUT = 7;
	

	

}
