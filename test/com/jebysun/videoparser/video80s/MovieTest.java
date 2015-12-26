package com.jebysun.videoparser.video80s;

import java.io.IOException;
import java.util.List;

import com.jebysun.videoparser.vdieo80s.Video;
import com.jebysun.videoparser.vdieo80s.VideoParser;

public class MovieTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		listMoive();
//		testDetail();
		
		testSearch("秦时明月");
	}
	
	private static void testSearch(String keyword) {
		try {
			List<Video> videoList = VideoParser.searchVideo(keyword);
			for (Video v : videoList) {
				v = VideoParser.getVideoDetail(v.getDetailUrl());
				printVideo(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void listMoive() {
		try {
//			List<Video> videoList = VideoParser.listMovie(null, null, null, null, null, 1);
			List<Video> videoList = VideoParser.listMovie(null, null, null, null, null, 100);
			for (Video v : videoList) {
				v = VideoParser.getVideoDetail(v.getDetailUrl());
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
//		String url = "http://www.80s.la/movie/15994";
		
//		String url = "http://www.80s.la/ju/14030";
//		String url = "http://www.80s.la/ju/15961";
//		String url = "http://www.80s.la/ju/15853";
		
		String url = "http://www.80s.la/dm/3108";
		try {
			Video v = VideoParser.getVideoDetail(url);
			printVideo(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void printVideo(Video v) {
		System.out.println(v.getName());
		System.out.println(v.getAlias());
		System.out.println(v.getActor());
		System.out.println(v.getArea());
		System.out.println(v.getCategory());
		System.out.println(v.getDetailUrl());
		System.out.println(v.getDirector());
		System.out.println(v.getDuration());
		System.out.println(v.getLanguage());
		System.out.println(v.getNote());
		System.out.println(v.getPosterUrl());
		System.out.println(v.getReleaseDate());
		System.out.println(v.getScore());
		System.out.println(v.getStory());
		for (String downloadUrl : v.getDownloadUrlList()) {
			System.out.println(downloadUrl);
		}
	}
	

}
