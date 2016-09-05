package com.jebysun.videoparser.video80s;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 高清海报解析
 * @author Administrator
 *
 */
public class PostImageParser {
	
	/**
	 * 获取高清海报地址
	 */
	public static String getHDPostImageUrl(String videoName) {
		String hdPostImgUrl = null;
		String queryUrl = "https://movie.douban.com/subject_search?search_text=";
		queryUrl += videoName;
		try {
			Document doc = Jsoup.connect(queryUrl).get();
			Elements es = doc.select("#content .pl2>a");
			for (Element e : es) {
				String names = e.text();
				if (names.contains(videoName)) {
					String href = e.attr("href");
					hdPostImgUrl = getDoubanMovie(href.substring(33, href.lastIndexOf("/")));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hdPostImgUrl;
	}
	
	private static String getDoubanMovie(String videoId) {
		String hdPostImgUrl = null;
		String url = "https://movie.douban.com/subject/$id/photos?type=R";
		url = url.replaceFirst("\\$id", videoId);
		try {
			Document doc = Jsoup.connect(url).get();
			Elements es = doc.select("#content ul.poster-col4>li");
			Element e = chooseImgElement(es);
			e = e.select("div.cover>a").get(0);
			String href = e.attr("href");
			hdPostImgUrl = getImgUrl(href.substring(38, href.lastIndexOf("/")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hdPostImgUrl;
	}
	
	/**
	 * 选择最合适的海报
	 * 海报名称关键字：
	 * 1.正式海报 中国大陆
	 * 2.正式海报
	 * @param e
	 */
	private static Element chooseImgElement(Elements es) {
		Element theEle = null;
		for (Element e : es) {
			if (e.hasClass("sep")) {
				continue;
			}
			//海报尺寸
			String strSize = e.select("div.prop").get(0).text();
			String[] arrStrSize = strSize.split("x");
			float scale = Float.parseFloat(arrStrSize[0]) / Float.parseFloat(arrStrSize[1]);
			if ((scale+0.1>=0.66) && (scale-0.1<=0.66)) {
				//海报名称
				String imgName = e.select("div.name").get(0).text();
				if (imgName.contains("正式海报 中国大陆")) {
					theEle = e;
					break;
				} else if (imgName.contains("正式海报")) {
					theEle = e;
				}
			}
		}
		return theEle;
	}
	
	private static String getImgUrl(String imgId) {
		String url = "https://img1.doubanio.com/view/photo/photo/public/p$imgId.jpg";
		url = url.replaceFirst("\\$imgId", imgId);
		return url;
	}
	
}
