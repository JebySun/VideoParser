package com.jebysun.videoparser.tw80s;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jebysun.videoparser.tw80s.model.Video;
/**
 * 获取豆瓣高清海报
 * 原理：使用影视名称搜索豆瓣电影，获取海报。海报匹配准确率并不是100%。
 * @author Jeby Sun
 *
 */
public class VideoParseClient {
	
	private int finishedCount = 0;
	private List<Video> videoList;
	private ExecutorService fixedThreadPool;
	private MovieCallBack callBack;
	
	public List<Video> listMovie(int index) {
		List<Video> videoList = null;
		try {
			videoList = VideoParser.listMovie(null, null, null, null, null, index);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return videoList;
	}

	public void listMovieWithHDPoster(int index, MovieCallBack callBack) {
		this.callBack = callBack;
		try {
			videoList = VideoParser.listMovie(null, null, null, null, null, index);
			fixedThreadPool = Executors.newFixedThreadPool(6);
			for (final Video v : videoList) {
				fixedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						String keyName = v.getName()
								.replaceFirst("DVD版", "")
								.replaceFirst("抢先版", "")
								.replaceFirst("枪版", "")
								.replaceFirst("\\[", "")
								.replaceFirst("\\]", "");
						String hdUrl = PostImageParser.parseHDPostImageUrl(keyName);
						//设置高清海报地址
//						v.setHdPosterUrl(hdUrl);
						
						finishedCount++;
						if (finishedCount == videoList.size()) {
							fixedThreadPool.shutdown();
							VideoParseClient.this.callBack.onSuccess(videoList);
						}
					}
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public interface MovieCallBack {
		public void onSuccess(List<Video> videoList);
	}
	
	
}
