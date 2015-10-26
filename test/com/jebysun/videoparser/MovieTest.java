package com.jebysun.videoparser;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import com.jebysun.videoparser.entity.Movie;
import com.jebysun.videoparser.parser.MovieParser;
import com.jebysun.videoparser.parser.simple.SimpleMovieParser;

public class MovieTest {

	public static void main(String[] args) throws SocketTimeoutException, IOException {
		
//		testLastestPublic170();
//		testLastest();
//		testSearch();
//		testParseMovie();
		testGetDetail();
		
//		testTv();
	}
	
	public static void testTv() {
		try {
			List<Movie> movies = SimpleMovieParser.getZongYiList(1);
			for (Movie m : movies) {
				System.out.println(m.getTitle());
				System.out.println(m.getDetailUrl());
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testLastestPublic170() {
		List<Movie> movies = MovieParser.getLastestPublic170();
		print(movies);
	}
	
	public static void testLastest() {
		List<Movie> movies = MovieParser.getMovieList(1);
		print(movies);
	}
	
	public static void testSearch() {
		List<Movie> movies = MovieParser.searchMovie("科幻", 100, 1);
		print(movies);
	}
	
	private static void print(List<Movie> movies) {
		for (Movie m : movies) {
			System.out.println(m.getTitle());
			System.out.println(m.getDetailUrl());
		}
	}
	
	private static void testParseMovie() {
//		Movie m = MovieParser.parseMovieByURL("http://www.ygdy8.com/html/gndy/dyzz/20150816/48818.html");
//		Movie m = MovieParser.parseMovieByURL("http://www.ygdy8.com/html/gndy/dyzz/20150904/48964.html");
//		Movie m = MovieParser.parseMovieByURL("http://www.ygdy8.com/html/gndy/dyzz/20111005/34499.html");
//		Movie m = MovieParser.parseMovieByURL("http://www.ygdy8.com/html/gndy/dyzz/20140527/45248.html");
//		Movie m = MovieParser.parseMovieByURL("http://www.ygdy8.com/html/gndy/dyzz/20090716/20357.html");
//		Movie m = MovieParser.parseMovieByURL("http://www.ygdy8.com/html/3gp/3gpmovie/20091028/22545.html");
		Movie m = MovieParser.parseMovieByURL("http://ygdy8.com/html/gndy/dyzz/20151022/49348.html");
		
		System.out.println("上传日期："+m.getUploadDate());
		System.out.println("标题："+m.getTitle());
		System.out.println("名称："+m.getName());
		System.out.println("年份："+m.getYear());
		System.out.println("地区："+m.getLocation());
		System.out.println("类别："+m.getCategory());
		System.out.println("时长："+m.getDuration());
		System.out.println("导演："+m.getDirector());
		System.out.println("主演："+m.getRoleActor());
		System.out.println("电影简介："+m.getIntroduction());
		System.out.println("电影详情地址："+m.getDetailUrl());
		System.out.println("海报地址："+m.getPosterUrl());
		System.out.println("截图地址："+m.getPrintscreenUrl());
		System.out.println("下载地址："+m.getDownloadUrl());
	}
	
	private static void testGetDetail() throws SocketTimeoutException, IOException {
		String s = SimpleMovieParser.getMovieDetail("http://www.ygdy8.com/html/dongman/haizeiwangqu/20120506/37572.html");
		List<String> downloads = SimpleMovieParser.getMovieDownloadUrl();
//		System.out.println(s);
		for (String sd : downloads) {
			System.out.println(sd);
		}
	}

}
