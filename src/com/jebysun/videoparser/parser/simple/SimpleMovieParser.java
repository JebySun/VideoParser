package com.jebysun.videoparser.parser.simple;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jebysun.videoparser.cfg.Config;
import com.jebysun.videoparser.entity.Movie;

/**
 * 简单电影解析客户端类
 * @author Jeby Sun
 * @Date 2015-09-01
 */
public class SimpleMovieParser {
	
	private static Document doc;
	
	/**
	 * 获取最新发布的170部电影列表
	 * @return 
	 */
	public static List<Movie> getLastestPublic170() {
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		try {
			doc = Jsoup.connect(Config.DOMAIN).get();
			Elements elements = doc.select(".bd3 .bd3l .co_area2:first-child .co_content2 ul a");
			for (Element e : elements) {
				movie = new Movie();
				movie.setTitle(e.text());
				movie.setDetailUrl(Config.DOMAIN + e.attr("href"));
				movies.add(movie);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	/**
	 * 最新电影
	 * @param pageIndex 当前页索引
	 * @return
	 * @throws IOException 
	 */
	public static List<Movie> getMovieList(int pageIndex) throws IOException, SocketTimeoutException {
		String queryUrl = Config.MOVIE_PATH.replaceFirst("\\$", String.valueOf(pageIndex));
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		doc = Jsoup.connect(Config.DOMAIN + queryUrl)
				.timeout(Config.TIMEOUT * 1000)
				.get();
		Elements elements = doc.select("div.co_content8 ul table tr:nth-child(2) td:nth-child(2) a");
		for (Element e : elements) {
			movie = new Movie();
			movie.setTitle(e.text());
			movie.setDetailUrl(Config.DOMAIN + e.attr("href"));
			movies.add(movie);
		}
		return movies;
	}
	
	/**
	 * 电视剧列表
	 * @param pageIndex
	 * @return
	 * @throws IOException
	 * @throws SocketTimeoutException
	 */
	public static List<Movie> getTVSeriesList(int pageIndex) throws IOException, SocketTimeoutException {
		String queryUrl = Config.TV_SERIE_PATH.replaceFirst("\\$", String.valueOf(pageIndex));
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		doc = Jsoup.connect(Config.DOMAIN + queryUrl)
				.timeout(Config.TIMEOUT * 1000)
				.get();
		Elements elements = doc.select("div.co_content8 ul table tr:nth-child(2) td:nth-child(2) a");
		for (Element e : elements) {
			movie = new Movie();
			movie.setTitle(e.text());
			movie.setDetailUrl(Config.DOMAIN + e.attr("href"));
			movies.add(movie);
		}
		return movies;
	}

	/**
	 * 综艺列表
	 * @param pageIndex
	 * @return
	 * @throws IOException
	 * @throws SocketTimeoutException
	 */
	public static List<Movie> getZongYiList(int pageIndex) throws IOException, SocketTimeoutException {
		String queryUrl = Config.ZONGYI_PATH.replaceFirst("\\$", String.valueOf(pageIndex));
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		doc = Jsoup.connect(Config.DOMAIN + queryUrl)
				.timeout(Config.TIMEOUT * 1000)
				.get();
		Elements elements = doc.select("div.co_content8 ul table tr:nth-child(2) td:nth-child(2) a:nth-child(2)");
		for (Element e : elements) {
			movie = new Movie();
			movie.setTitle(e.text());
			movie.setDetailUrl(Config.DOMAIN + e.attr("href"));
			movies.add(movie);
		}
		return movies;
	}
	
	/**
	 * 动漫列表
	 * @param pageIndex
	 * @return
	 * @throws IOException
	 * @throws SocketTimeoutException
	 */
	public static List<Movie> getDongManList(int pageIndex) throws IOException, SocketTimeoutException {
		String queryUrl = Config.DONGMAN_PATH.replaceFirst("\\$", String.valueOf(pageIndex));
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		doc = Jsoup.connect(Config.DOMAIN + queryUrl)
				.timeout(Config.TIMEOUT * 1000)
				.get();
		Elements elements = doc.select("div.co_content8 ul table tr:nth-child(2) td:nth-child(2) a:nth-child(2)");
		for (Element e : elements) {
			movie = new Movie();
			movie.setTitle(e.text());
			movie.setDetailUrl(Config.DOMAIN + e.attr("href"));
			movies.add(movie);
		}
		return movies;
	}
	
	/**
	 * 电影搜索
	 * @param key 搜索关键字
	 * @param pageLength 每页结果大小
	 * @param pageIndex 当前页索引
	 * @return
	 * @throws IOException 
	 */
	public static List<Movie> searchMovie(String key, int pageLength, int pageIndex) throws IOException, SocketTimeoutException {
		List<Movie> movies = new ArrayList<Movie>();
		String queryUrl = Config.SEARCH_URL;
		Movie movie = null;
		key = URLEncoder.encode(key, "gb2312");
		queryUrl = queryUrl.replaceFirst("\\$", key);
		queryUrl = queryUrl.replaceFirst("\\$", String.valueOf(pageLength));
		queryUrl = queryUrl.replaceFirst("\\$", String.valueOf(pageIndex));
		doc = Jsoup.connect(queryUrl)
				.timeout(Config.TIMEOUT*1000)
				.get();
		Elements elements = doc.select("div.co_content8 ul table tr:nth-child(1) td:nth-child(2) a");
		for (Element e : elements) {
			String name = e.text();
			//如果不是视频
			if (isNotVideo(name)) {
				continue;
			}
			movie = new Movie();
			movie.setTitle(name);
			movie.setDetailUrl(Config.DOMAIN + e.attr("href"));
			movies.add(movie);
		}
		return movies;
	}
	
	/**
	 * 根据标题关键字判断是否不是视频
	 * @param titleName
	 * @return
	 */
	private static boolean isNotVideo(String titleName) {
		if (titleName.indexOf("单机游戏") != -1 || titleName.indexOf("硬盘版") != -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 根据地址获取电影详细介绍信息
	 * @param url
	 * @return
	 */
	public static String getMovieDetail(String url) throws IOException, SocketTimeoutException {
		String htmlDetail = null;
		doc = Jsoup.connect(url)
				.timeout(Config.TIMEOUT*1000)
				.get();

		//调整图片宽度为屏幕宽度，高度自动等比缩放。
		doc.select("#Zoom img").attr("style", "width:100%; height:auto;");
		Elements eles = doc.select("#Zoom p:nth-child(1)");
		if (eles.size()==0) {
			return "获取视频详情失败。<br/>" + url;
		}
		htmlDetail = eles.get(0).html();
		//如果内容没有多余的杂乱信息，则直接返回
		if (htmlDetail.startsWith("<img")) {
			return htmlDetail;
		}
		return htmlDetail.substring(htmlDetail.indexOf("<img"));
	}
	
	/**
	 * 通用视频详情信息获取
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws SocketTimeoutException
	 */
	public static String getVideoDetail(String url) throws IOException, SocketTimeoutException {
		String htmlDetail = null;
		doc = Jsoup.connect(url)
				.timeout(Config.TIMEOUT*1000)
				.get();

		//调整图片宽度为屏幕宽度，高度自动等比缩放。
		doc.select("#Zoom img").attr("style", "width:100%; height:auto;");
		Elements zoom = doc.select("#Zoom");
		if (zoom.size()==0) {
			return "获取视频详情失败。<br/>" + url;
		}
		htmlDetail = zoom.get(0).html();
		
		return dealHTML(htmlDetail);
	}
	
	/**
	 * 截取视频详情信息
	 * @param html
	 * @return
	 */
	private static String dealHTML(String html) {
		String result = null;
		//处理开头（海报之前）多余的杂乱信息
		int index = html.indexOf("<img");
		if (index != -1) {
			result = html.substring(index);
		} else {
			result = html;
		}
		
		//截掉下载地址信息列表
		if ((index = result.indexOf("下载地址")) != -1) {
			result = result.substring(0, index);
			if (result.endsWith("【") || result.endsWith("[") || result.endsWith("(")) {
				result = result.substring(0, result.length()-1);
			}
		//截掉少部分没有“下载地址关键字”的ftp下载地址
		} else if ((index = result.indexOf("ftp")) != -1) {
			result = result.substring(0, index);
		//终极办法：截取到每个页面都包含的下载地址2结束。
		} else if ((index = result.indexOf("<font color=\"red\">下载地址2：")) != -1) {
			result = result.substring(0, index);
		}
		return result;
	}
	
	/**
	 * 根据电影详细页地址获取电影下载地址
	 * @param url
	 * @return
	 */
	public static String getMovieDownloadUrl(String url) throws IOException, SocketTimeoutException {
		doc = Jsoup.connect(url)
				.timeout(Config.TIMEOUT*1000)
				.get();
		return doc.select("table[width=95%] tr td a").get(0).text();
	}
	
	/**
	 * 根据当前加载的文档获取电影下载地址
	 * @param doc 
	 * @return
	 */
	public static List<String> getMovieDownloadUrl() {
		List<String> downloadList = new ArrayList<String>(); 
		if (doc == null) {
			return downloadList;
		}
		Elements elements = doc.select("table[width=95%] tr td a");
		for (Element e : elements) {
			downloadList.add(e.text());
		}
		return downloadList;
	}
	
	
	
}




