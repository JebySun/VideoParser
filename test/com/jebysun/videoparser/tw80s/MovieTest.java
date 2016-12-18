package com.jebysun.videoparser.tw80s;

import java.io.IOException;
import java.util.List;

import com.jebysun.videoparser.tw80s.PostImageParser;
import com.jebysun.videoparser.tw80s.VideoParseClient;
import com.jebysun.videoparser.tw80s.VideoParser;
import com.jebysun.videoparser.tw80s.VideoParseClient.MovieCallBack;
import com.jebysun.videoparser.tw80s.model.DownloadInfo;
import com.jebysun.videoparser.tw80s.model.Video;

public class MovieTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		testMoiveList();
//		testVideoClient();
//		testHDPost();
		testDetail();
		
//		testSearch("死的");
		
//		testTVlist();
//		testMangalist();
//		testVarietylist();
	}
	
	public static void testVideoClient() {
		VideoParseClient client = new VideoParseClient();
		client.listMovieWithHDPoster(3, new MovieCallBack() {

			@Override
			public void onSuccess(List<Video> videoList) {
				System.out.println(videoList.size());
				for (Video v : videoList) {
					System.out.println("==========="+v.getName());
					System.out.println(v.getNote());
					System.out.println(v.getScore());
					System.out.println(v.getHdPosterUrl()!=null ? v.getHdPosterUrl() : v.getPosterUrl());
				}
			}
			
		});
	}
	
	
	private static void testHDPost() {
		String s = PostImageParser.parseHDPostImageUrl("魔兽");
		System.out.println(s);
	}
	
	
	private static void testMoiveList() {
		try {
			List<Video> videoList = VideoParser.listMovie(null, null, null, null, null, 1);
			for (Video v : videoList) {
				System.out.println(v.getName());
				System.out.println(v.getPosterUrl());
				System.out.println(v.getNote());
				System.out.println(v.getScore());
				System.out.println(v.getDetailUrl());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testTVlist() {
		try {
			List<Video> videoList = VideoParser.listTV(null, null, null, null, 1);
			for (Video v : videoList) {
//				v = VideoParser.getVideoDetail(v.getDetailUrl());
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
//		String url = "http://www.80s.tw/movie/19412";
//		String url = "http://www.80s.tw/movie/18728";
		String url = "http://www.80s.tw/movie/19239";
//		String url = "http://www.80s.tw/ju/19249";
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
		System.out.println("=====下载信息");
		printMapByKeySet(v.getDownloadInfoList());
	}
	
	/**
	 * 遍历打印Map
	 * @param map
	 */
    public static void printMapByKeySet(List<DownloadInfo> downloadInfoList) {
        for (DownloadInfo download : downloadInfoList) {  
            System.out.println("下载标题：" + download.getTitle());  
            System.out.println("文件大小：" + download.getFileSize());  
            System.out.println("下载地址：" + download.getDownloadUrl());  
        }  
    }
	

}
