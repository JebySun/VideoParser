package com.jebysun.videoparser.video80s;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.jebysun.videoparser.video80s.Video;
import com.jebysun.videoparser.video80s.VideoParser;
import com.jebysun.videoparser.video80s.param.TVQueryParam;

public class MovieTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		testMoiveList();
//		testDetail();
		
//		testSearch("死的");
		
//		testTVlist();
//		testMangalist();
//		testVarietylist();
	}
	
	
	private static void testMoiveList() {
		try {
			List<Video> videoList = VideoParser.listMovie(null, null, null, null, null, 1);
			for (Video v : videoList) {
				v = VideoParser.getVideoDetail(v.getDetailUrl());
				printVideo(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testTVlist() {
		try {
			List<Video> videoList = VideoParser.listTV(TVQueryParam.CATEGORY_DUSHI, TVQueryParam.AREA_RIHANJU, null, null, 1);
			for (Video v : videoList) {
				v = VideoParser.getVideoDetail(v.getDetailUrl());
				printVideo(v);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testMangalist() {
		try {
			List<Video> videoList = VideoParser.listManga(null, null, 1);
			for (Video v : videoList) {
//				v = VideoParser.getVideoDetail(v.getDetailUrl());
				printVideo(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testVarietylist() {
		try {
			List<Video> videoList = VideoParser.listVariety(null, 1);
			for (Video v : videoList) {
//				v = VideoParser.getVideoDetail(v.getDetailUrl());
				printVideo(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testSearch(String keyword) {
		try {
			List<Video> videoList = VideoParser.searchVideo(keyword);
			for (Video v : videoList) {
//				v = VideoParser.getVideoDetail(v.getDetailUrl());
				printVideo(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private static void testDetail() {
//		String url = "http://www.80s.la/movie/15429";
//		String url = "http://www.80s.la/movie/15728";
//		String url = "http://www.80s.la/movie/15991";
//		String url = "http://www.80s.la/movie/585";
//		String url = "http://www.80s.la/movie/15993";
//		String url = "http://www.80s.la/movie/14635";
		
		//电视剧_秦时明月
		String url = "http://www.80s.la/ju/14030";
		//电视剧_芈月传
//		String url = "http://www.80s.la/ju/15853";
		
//		String url = "http://www.80s.la/ju/16023";
		
		//动漫_名侦探柯南 
//		String url = "http://www.80s.la/dm/3109";
//		String url = "http://www.80s.la/dm/3107";
		try {
			Video v = VideoParser.getVideoDetail(url);
			printVideo(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void printVideo(Video v) {
		System.out.println("========视频详情========");

		System.out.println("类型:"+v.getVideoType());
		System.out.println("名称:"+v.getName());
		System.out.println("别名:"+v.getAlias());
		System.out.println("演员:"+v.getActor());
		System.out.println("地区:"+v.getArea());
		System.out.println("类别:"+v.getCategory());
		System.out.println("详情地址:"+v.getDetailUrl());
		System.out.println("导演:"+v.getDirector());
		System.out.println("时长:"+v.getDuration());
		System.out.println("语言:"+v.getLanguage());
		System.out.println("标注:"+v.getNote());
		System.out.println("海报地址:"+v.getPosterUrl());
		System.out.println("上映日期:"+v.getReleaseDate());
		System.out.println("豆瓣评分:"+v.getScore());
		System.out.println("剧情简介:"+v.getStory());
		System.out.println("视频截图:"+v.getScreenShotUrl());
		System.out.println("下载地址:");
		printMapByKeySet(v.getDownloadMap());
	}
	
	/**
	 * 遍历打印Map
	 * @param map
	 */
    public static void printMapByKeySet(Map<String, String> map) {
    	Set<Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {  
            System.out.println(entry.getKey() + "：" + entry.getValue());  
        }  
    }
	

}
