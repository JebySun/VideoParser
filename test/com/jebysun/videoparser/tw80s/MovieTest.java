package com.jebysun.videoparser.tw80s;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jebysun.videoparser.tw80s.VideoParserImp;
import com.jebysun.videoparser.tw80s.bak.PostImageParser;
import com.jebysun.videoparser.tw80s.bak.VideoParseClient;
import com.jebysun.videoparser.tw80s.bak.VideoParseClient.MovieCallBack;
import com.jebysun.videoparser.tw80s.exception.PermissionException;
import com.jebysun.videoparser.tw80s.model.DoubanComment;
import com.jebysun.videoparser.tw80s.model.DoubanCommentPage;
import com.jebysun.videoparser.tw80s.model.DownloadInfo;
import com.jebysun.videoparser.tw80s.model.SearchKeyword;
import com.jebysun.videoparser.tw80s.model.Video;
import com.jebysun.videoparser.tw80s.param.MovieQueryParam;

public class MovieTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		testMoiveList();
//		testVideoClient();
//		testHDPost();
//		testDetail();
//		testSearch("小新");
//		testTVlist();
//		testMangalist();
//		testVarietylist();
		
//		testRecommend();
//		testListTopKeywords();
		
		testDoubanComment();
	}
	
	/*
	 * TODO 设计最佳接口
	public static void bestClientAPI() {
		MovieQueryParam map = new MovieQueryParam();
		VideoParser.doAction(ParseAction.LIST_MOVIE)
				.paramObject(MovieQueryParam)
				.list(new VideoListCallback() {
					public void success(List<Video> list) {
						
					}
					public void fail(Exception e) {
						
					}
				});
		
		VideoParser.doAction(ParseAction.VIDEO_DETAIL)
				.param(url)
				.get(new DetailCallback() {
					public void success(List<Video> list) {
						
					}
					public void fail(Exception e) {
						
					}
				});
		
		VideoParser.doAction(ParseAction.SEARCH)
				.param(keyword)
				.search(new SearchCallback() {
					public void success(List<Video> list) {
						
					}
					public void fail(Exception e) {
						
					}
				});
	}
	*/
	
	public static void testDoubanComment() {
		VideoParser vp = new VideoParserImp();
		try {
			DoubanCommentPage page = vp.listDoubanComment("26363254", 0);
			List<DoubanComment> list = page.getCommentList();
			System.out.println("本页大小：" + list.size());
			for (DoubanComment comment : list) {
				System.out.println("=====================================");
				printDoubanComment(comment);
			}
			System.out.println("下一页开始序号：" + page.getNextPageStart());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PermissionException e) {
			e.printStackTrace();
		}
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
					System.out.println(v.getPosterUrl());
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
			VideoParser vp = new VideoParserImp();
			List<Video> videoList = vp.listMovie(MovieQueryParam.CATEGORY_DONGZUO, null, null, null, null, 1);
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
			VideoParser vp = new VideoParserImp();
			List<Video> videoList = vp.listTV(null, null, null, null, 1);
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
			VideoParser vp = new VideoParserImp();
			List<Video> videoList = vp.listManga(null, null, 1);
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
			VideoParser vp = new VideoParserImp();
			List<Video> videoList = vp.listVariety(null, 1);
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
			VideoParser vp = new VideoParserImp();
			List<Video> videoList = vp.searchVideo(keyword);
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
//		String url = "http://www.80s.tw/movie/21252";
//		String url = "http://www.80s.tw/ju/19249";
//		String url = "http://www.80s.tw/dm/3108";
		String url = "http://www.80s.tw/zy/21216";
		try {
			VideoParser vp = new VideoParserImp();
			Video v = vp.getVideoDetail(url);
			printVideo(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testRecommend() {
		String url = "http://www.80s.tw/movie/21252";
		try {
			VideoParser vp = new VideoParserImp();
			List<Video> videoList = vp.listRecommendVideo(url);
			for (Video v : videoList) {
				printVideo(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testListTopKeywords() {
		try {
			VideoParser vp = new VideoParserImp();
			List<SearchKeyword> keywordList = vp.listTopKeyword();
			for (SearchKeyword v : keywordList) {
				System.out.println("标题：" + v.getTitle());
				System.out.println("类型：" + v.getType());
				System.out.println("链接：" + v.getLink());
			}
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
		System.out.println("豆瓣影视Id:"+v.getDoubanVideoId());
		System.out.println("剧情简介:"+v.getStory());
		System.out.println("视频截图:"+v.getScreenShotUrl());
		System.out.println("=====下载信息");
		printMapByKeySet(v.getDownloadInfoList());
	}
	
    private static void printMapByKeySet(List<DownloadInfo> downloadInfoList) {
        for (DownloadInfo download : downloadInfoList) {  
            System.out.println("下载标题：" + download.getTitle());  
            System.out.println("文件大小：" + download.getFileSize());  
            System.out.println("下载地址：" + download.getDownloadUrl());  
        }  
    }
    
    private static void printDoubanComment(DoubanComment comment) {
		System.out.println("用户名：" + comment.getUserName());
		System.out.println("头像：" + comment.getUserAvatar());
		System.out.println("用户主页：" + comment.getUserPageUrl());
		System.out.println("打分：" + comment.getRatingLevel().getRatingValue());
		System.out.println("评论：" + comment.getComment());
		System.out.println("投票数：" + comment.getThumbsUpCount());
		System.out.println("日期：" + new SimpleDateFormat("yyyy-MM-dd").format(comment.getCreateDate()));
    }
    

}
