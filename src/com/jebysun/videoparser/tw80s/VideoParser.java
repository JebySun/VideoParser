package com.jebysun.videoparser.tw80s;

import java.io.IOException;
import java.util.List;

import com.jebysun.videoparser.tw80s.model.Video;

/**
 * 视频解析接口
 * @author Jeby Sun
 *
 */
public interface VideoParser {
	
	/**
	 * 电影查询列表
	 * @param categroy - 类别，默认为全部
	 * @param area - 地区，默认为全部
	 * @param language - 语言，默认为全部
	 * @param year - 年份，默认为全部
	 * @param sort - 排序方式，默认按照最新视频排序
	 * @param pageIndex - 当前页索引
	 * @return 电影列表
	 * @throws IOException
	 */
	List<Video> listMovie(String category, String area, String language, String year, String sort, int pageIndex) throws IOException;
	
	/**
	 * 电视剧查询列表
	 * @param category - 类别，默认为全部
	 * @param area - 地区，默认为全部
	 * @param year - 年份，默认为全部
	 * @param sort - 排序方式，默认按照最新视频排序
	 * @param pageIndex - 当前页索引
	 * @return 电视剧列表
	 * @throws IOException
	 */
	List<Video> listTV(String category, String area, String year, String sort, int pageIndex) throws IOException;
	
	/**
	 * 动漫查询列表
	 * @param type - 类型，默认全部
	 * @param sort - 排序方式，默认按照最新视频排序
	 * @param pageIndex - 当前页索引
	 * @return 动漫列表
	 * @throws IOException
	 */
	List<Video> listManga(String type, String sort, int pageIndex) throws IOException;
	
	/**
	 * 综艺查询列表
	 * @param sort - 排序方式，默认按照最新视频排序
	 * @param pageIndex - 当前页索引
	 * @return 综艺列表
	 * @throws IOException
	 * @author Jeby Sun
	 */
	List<Video> listVariety(String sort, int pageIndex) throws IOException;
	
	/**
	 * 获取视频详情
	 * @param url 视频详情地址
	 * @return 视频详情对象
	 * @throws IOException
	 */
    Video getVideoDetail(String url) throws IOException;
    
	/**
	 * 视频搜索
	 * @param keyword - 搜索关键字
	 * @return - 视频搜索结果列表
	 * @throws IOException
	 */
    List<Video> searchVideo(String keyword) throws IOException;
    
    /**
     * 获取热门搜索关键字列表
     * @return
     * @throws IOException
     */
    List<String> listTopKeyword() throws IOException;
    
}






