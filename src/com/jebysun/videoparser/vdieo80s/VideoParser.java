package com.jebysun.videoparser.vdieo80s;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 80s视频资源解析
 * @author Jeby Sun
 * @Date 2015-12-24
 */
public class VideoParser {
	
	private VideoParser() {}
	
	/**
	 * 电影分类列表
	 * @param categroy - 类别，默认为全部
	 * @param area - 地区，默认为全部
	 * @param language - 语言，默认为全部
	 * @param year - 年份，默认为全部
	 * @param sort - 排序方式，默认按照最新视频排序
	 * @param pageIndex - 当前页索引，默认为第一页(p1)
	 * @return
	 * @throws IOException
	 */
	private static List<Video> listMovie(String category, String area, String language, String year, String sort, String pageIndex) throws IOException {
		List<Video> movies = new ArrayList<Video>();
		String queryUrl = buildQueryUrl(category, area, language, year, sort, pageIndex);
		Video v = null;
		Document doc = Jsoup.connect(Config.DOMAIN + queryUrl)
				.timeout(Config.TIMEOUT * 1000)
				.get();
		Elements elements = doc.select("ul.me1.clearfix>li");
		for (Element e : elements) {
			v = new Video();
			String name = e.select("a").get(0).attr("title");
			String note = e.select("span").get(1).text();
			String url = e.select("a").get(0).attr("href");
			String imgUrl = e.select("a>img").get(0).attr("_src");
			v.setName(name);
			v.setNote(note);
			v.setDetailUrl(Config.DOMAIN+url);
			v.setPosterUrl(imgUrl);
			movies.add(v);
		}
		return movies;
	}
	
	private static String buildQueryUrl(String category, String area, String language, String year, String sort, String pageIndex) {
		String queryUrl = Config.MOVIE_PATH;

		queryUrl = paramReplace(queryUrl, "\\$category", category);
		queryUrl = paramReplace(queryUrl, "\\$area", area);
		queryUrl = paramReplace(queryUrl, "\\$language", language);
		queryUrl = paramReplace(queryUrl, "\\$year", year);
		queryUrl = paramReplace(queryUrl, "\\$sort", sort);
		queryUrl = paramReplace(queryUrl, "\\$page", pageIndex);

		return queryUrl;
	}
	
	private static String paramReplace(String url, String pname, String pvalue) {
		pvalue = (pvalue==null) ? "" : pvalue;
		return url.replaceFirst(pname, pvalue);
	}
	
	
	/**
	 * 电影分类列表
	 * @param categroy - 类别，默认为全部
	 * @param area - 地区，默认为全部
	 * @param language - 语言，默认为全部
	 * @param year - 年份，默认为全部
	 * @param sort - 排序方式，默认按照最新视频排序
	 * @param pageIndex - 当前页索引
	 * @return
	 * @throws IOException
	 */
	public static List<Video> listMovie(String category, String area, String language, String year, String sort, int pageIndex) throws IOException {
		return listMovie(category, area, language, year, sort, "p"+pageIndex);
	}
	
	
	/**
	 * 填充电影基本信息
	 * @param v
	 * @param sArr
	 * @return
	 */
	private static Video fillBaseInfo(Video v, String[] sArr) {
		String key = sArr[0];
		String value = sArr[1].trim();
		switch(key) {
		case "又名":
			v.setAlias(value);
			break;
		case "演员":
			v.setActor(value);
			break;
		case "类型":
			v.setCategory(value);
			break;
		case "地区":
			v.setArea(value);
			break;
		case "语言":
			v.setLanguage(value);
			break;
		case "导演":
			v.setDirector(value);
			break;
		case "上映日期":
			v.setReleaseDate(value);
			break;
		case "片长":
			v.setDuration(value);
			break;
		}
		return v;
	}
	
	/**
	 * 获取电影详情
	 * @param url 电影详情地址
	 * @return
	 * @throws IOException
	 */
	public static Video getVideoDetail(String url) throws IOException {
		Video v = new Video();
		//电影详情地址
		v.setDetailUrl(url);
		Document doc = Jsoup.connect(url)
				.timeout(Config.TIMEOUT * 1000)
				.get();
		
		
		//电影海报
		Element imgElement = doc.select("div#minfo>div.img>img").get(0);
		String name = imgElement.attr("title");
		String imgUrl = imgElement.attr("src");
		v.setName(name);
		v.setPosterUrl(imgUrl);
		
		//备注
		Elements noteElems = doc.select("div.info span.tip");
		String note = noteElems.size()==0 ? null : noteElems.get(0).text();
		v.setNote(note);
		
		//基本信息
		Element baseInfoNode = doc.select("div.info div.clearfix").get(0);
		Elements es = baseInfoNode.select(">span");
		for (Element e : es) {
			String[] sArr = e.text().split("：");
			if (sArr.length==2) {
				v = fillBaseInfo(v, sArr);
			}
		}
		
		//影片评分
		Element scoreNode = doc.select("div.info div:nth-child(1)").get(0);
		String scoreInfo = scoreNode.text();
		if (scoreInfo.startsWith("豆瓣评分")) {
			v.setScore(scoreInfo.split("：")[1].trim());
		}
			
		//影片简介
		Element storyNode = doc.select("div.info div.clearfix").get(1);
		//移除“剧情介绍”标题
		storyNode.select(">p>span").get(0).remove();
		String story = storyNode.select(">p").get(0).text().replaceAll("　", "").trim();
		v.setStory(story);
		
		//下载地址
		Element urlNode = doc.select("ul.dllist1>li.backcolor1 span:nth-child(2)>a").get(0);
		String downloadUrl = urlNode.attr("href");
		v.addDownloadUrl(downloadUrl);
		return v;
	}
	
	/**
	 * 获取电影详情
	 * @param v Video实体
	 * @return
	 * @throws IOException
	 */
	public static Video getVideoDetail(Video v) throws IOException {
		Video detailVideo = getVideoDetail(v.getDetailUrl());
		detailVideo.setName(v.getName());
		detailVideo.setNote(v.getNote());
		detailVideo.setPosterUrl(v.getPosterUrl());
		detailVideo.setDetailUrl(v.getDetailUrl());
		return detailVideo;
	}
	
	public static List<Video> searchVideo(String keyword) throws IOException {
		List<Video> movies = new ArrayList<Video>();
		String searchUrl = Config.DOMAIN + Config.VIDEO_SEARCH_PATH;
		Document doc = Jsoup.connect(searchUrl)
				.timeout(Config.TIMEOUT * 1000)
				.data("search_typeid", "1")
				.data("skey", keyword)
				.post();
		
		Video v = null;
		Elements elements = doc.select("ul.me1.clearfix>li");
		for (Element e : elements) {
			v = new Video();
			String name = e.select("a").get(0).attr("title");
			String note = e.select("span").get(1).text();
			String url = e.select("a").get(0).attr("href");
			String imgUrl = e.select("a>img").get(0).attr("_src");
			v.setName(name);
			v.setNote(note);
			v.setDetailUrl(Config.DOMAIN+url);
			v.setPosterUrl(imgUrl);
			movies.add(v);
		}
		return movies;
	}
	
	
	
	
}











