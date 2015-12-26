package com.jebysun.videoparser.ygdy8;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jebysun.videoparser.ygdy8.Config;

/**
 * 电影解析客户端类
 * @author Jeby Sun
 * @Date 2015-09-01
 */
public class MovieParser {
	
	/**
	 * 获取最新发布的170部电影列表
	 * @return 
	 */
	public static List<Movie> getLastestPublic170() {
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		try {
			Document doc = Jsoup.connect(Config.DOMAIN).get();
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
	 */
	public static List<Movie> getMovieList(int pageIndex) {
		String queryUrl = Config.MOVIE_PATH.replaceFirst("\\$", String.valueOf(pageIndex));
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		try {
			Document doc = Jsoup.connect(Config.DOMAIN + queryUrl)
					.timeout(Config.TIMEOUT*1000)
					.get();
			Elements elements = doc.select("div.co_content8 ul table tr:nth-child(2) td:nth-child(2) a");
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
	 * 电影搜索
	 * @param key 搜索关键字
	 * @param pageLength 每页结果大小
	 * @param pageIndex 当前页索引
	 * @return
	 */
	public static List<Movie> searchMovie(String key, int pageLength, int pageIndex) {
		List<Movie> movies = new ArrayList<Movie>();
		String queryUrl = Config.SEARCH_URL;
		Movie movie = null;
		try {
			key = URLEncoder.encode(key, "gb2312");
			queryUrl = queryUrl.replaceFirst("\\$", key);
			queryUrl = queryUrl.replaceFirst("\\$", String.valueOf(pageLength));
			queryUrl = queryUrl.replaceFirst("\\$", String.valueOf(pageIndex));
			Document doc = Jsoup.connect(queryUrl)
					.timeout(Config.TIMEOUT*1000)
					.get();
			Elements elements = doc.select("div.co_content8 ul table tr:nth-child(1) td:nth-child(2) a");
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
	 * 根据电影详情页解析电影信息
	 * @param url
	 * @return
	 */
	public static Movie parseMovieByURL(String url) {
		Movie movie = new Movie();
		try {
			Document doc = Jsoup.connect(url)
					.timeout(Config.TIMEOUT*1000)
					.get();
			movie.setTitle(doc.select(".title_all>h1").get(0).text());
			//上传时间
			String uploadDate = doc.select(".co_content8>ul").get(0).text();
			if (uploadDate.contains("发布时间")) {
				uploadDate = uploadDate.substring(5, 16);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				movie.setUploadDate(sdf.parse(uploadDate));
			}
			String html = doc.select("#Zoom").get(0).html();
			String text = doc.select("#Zoom").get(0).text();
			int startIndex = html.indexOf("◎简　　介");
			int posterImgIndex = html.indexOf("<img");
			int endIndex = html.indexOf("<img", posterImgIndex+1);
			//发现有视频根本没有截图,以红色的“下载地址”作为简介结束标记
			if (endIndex == -1) {
				endIndex = html.indexOf("<font color=\"#ff0000\">", posterImgIndex+1);
			}
			//去掉"◎简介"
			String introduction = html.substring(startIndex+5, endIndex);
			//去掉"<br><br>"之前的全角空格
			while (introduction.charAt(0)==12288) {
				introduction = introduction.substring(1);
			}
			//去掉"<br><br>"
			introduction = introduction.substring(8);
			movie.setIntroduction(introduction);
			//视频海报
			if (doc.select("#Zoom img").size() != 0) {
				movie.setPosterUrl(doc.select("#Zoom img").get(0).attr("src"));
			}
			//视频截图
			if (doc.select("#Zoom img").size() > 1) {
				movie.setPrintscreenUrl(doc.select("#Zoom img").get(1).attr("src"));
			}
			movie.setDownloadUrl(doc.select("table[width=95%] tr td a").get(0).text());
			movie.setDetailUrl(url);
			
			movie = fillMovie(text, movie);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return movie;
	}
	
	/**
	 * 填充电影额外基础信息
	 * @param text
	 * @param movie
	 * @return
	 */
	private static Movie fillMovie(String text, Movie movie) {
		String[] infos = text.split("◎");
		
		String temp = findString(infos, "译　　名").split("/")[0].replaceFirst("　", "").replaceFirst(" ", "");
		movie.setName(temp);
		
		temp = findString(infos, "年　　代").replaceFirst("　", "").replaceFirst(" ", "");
		movie.setYear(temp);
		
		temp = findString(infos, "国　　家").replaceFirst("　", "").replaceFirst(" ", "");
		movie.setLocation(temp);
		
		temp = findString(infos, "类　　别").replaceFirst("　", "").replaceFirst(" ", "");
		movie.setCategory(temp);
		
		temp = findString(infos, "片　　长").replaceFirst("　", "").replaceFirst(" ", "");
		movie.setDuration(temp);
		
		temp = findString(infos, "导　　演").replaceFirst("　", "").replaceFirst(" ", "").replaceAll("　　　　　", ",");
		movie.setDirector(temp);
		
		temp = findString(infos, "主　　演").replaceFirst("　", "").replaceAll("　　　　　", ",");
		movie.setRoleActor(temp);
		return movie;
	}
	
	private static String findString(String[] strs, String key) {
		String str = null;
		for (String s : strs) {
			if (s.indexOf(key) != -1) {
				str = s.replaceFirst(key, "");
				break;
			}
		}
		return str;
	}

	/**
	 * 解析电影部分信息
	 * 有些电影信息出现错乱，需要改进优化。
	 * @param doc
	 * @param movie
	 * @return
	 */
	private static Movie fillMovieInfo(Document doc, Movie movie) {
		if (hasSpanTag(doc)) {
			movie = fillMovieInfoWithSpan(doc, movie);
		} else {
			movie = fillMovieInfoWithoutSpan(doc, movie);
		}
		return movie;
	}
	
	private static Movie fillMovieInfoWithoutSpan (Document doc, Movie movie) {
		String strInfo = doc.select("#Zoom span p:nth-child(1)").get(0).html();
		String[] infos = strInfo.split("<br>");
		//注意：替换奇怪的空字符，只能拷贝而来，手动输入全角或者半角空格均无效。
		//经再次检查，“奇怪的空字符”是一个全角空格（unicode编码为12288）。
		movie.setName(infos[4].replaceFirst("◎译　　名", "").split("/")[0].replaceFirst("　", ""));
		movie.setYear(infos[5].replaceFirst("◎年　　代", "").replaceFirst("　", ""));
		movie.setLocation(infos[6].replaceFirst("◎国　　家", "").replaceFirst("　", ""));
		movie.setCategory(infos[7].replaceFirst("◎类　　别", "").replaceFirst("　", ""));
		movie.setDuration(infos[14].replaceFirst("◎片　　长", "").replaceFirst("　", ""));
		movie.setDirector(infos[15].replaceFirst("◎导　　演", "").replaceFirst("　", "").replaceAll("&middot;", "·"));
		movie.setRoleActor(infos[16].replaceFirst("◎主　　演", "").replaceFirst("　", "").replaceAll("&middot;", "·"));
		return movie;
	}
	
	private static Movie fillMovieInfoWithSpan (Document doc, Movie movie) {
		String strInfo = doc.select("#Zoom span p:nth-child(1) span").get(0).html();
		String[] infos = strInfo.split("<br>");
		//注意：替换奇怪的空字符，只能拷贝而来，手动输入全角或者半角空格均无效。
		//经再次检查，“奇怪的空字符”是一个全角空格（unicode编码为12288）。
		movie.setName(infos[0].replaceFirst("◎译　　名", "").split("/")[0].replaceFirst("　", ""));
		movie.setYear(infos[2].replaceFirst("◎年　　代", "").replaceFirst("　", ""));
		movie.setLocation(infos[3].replaceFirst("◎国　　家", "").replaceFirst("　", ""));
		movie.setCategory(infos[4].replaceFirst("◎类　　别", "").replaceFirst("　", ""));
		movie.setDuration(infos[11].replaceFirst("◎片　　长", "").replaceFirst("　", ""));
		movie.setDirector(infos[12].replaceFirst("◎导　　演", "").replaceFirst("　", "").replaceAll("&middot;", "·"));
		movie.setRoleActor(infos[14].replaceFirst("◎主　　演", "").replaceFirst("　", "").replaceAll("&middot;", "·"));
		return movie;
	}
	
	private static boolean hasSpanTag(Document doc) {
		boolean hasSpanTag = doc.select("#Zoom span p:nth-child(1) span").isEmpty();
		return !hasSpanTag;
	}

}




