package com.jebysun.videoparser.tw80s;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jebysun.videoparser.utils.JavaUtil;

/**
 * 高清海报解析
 * @author Administrator
 *
 */
public class PostImageParser {
	
	/**
	 * 获取高清海报地址
	 */
	public static String parseHDPostImageUrl(String videoName) {
		String hdPostImgUrl = null;
		String queryUrl = "https://movie.douban.com/subject_search?search_text=";
		queryUrl += videoName;
		try {
			Document doc = Jsoup.connect(queryUrl)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.89 Safari/537.36")
					.header("Host", "movie.douban.com")
					.header("Cookie", "bid=nLs7K-JJT60; ll=\"118163\"; gr_user_id=78535e2b-4e26-4fba-a19c-4e74589d860e; viewed=\"11597324_10517156_5317075_26259017\"; ps=y; _ga=GA1.2.768980729.1463900105; ap=1; push_noty_num=0; push_doumail_num=1; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1473143266%2C%22https%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3DUTF-8%26wd%3Ddouban%22%5D; _vwo_uuid_v2=331F3C2609E4B76F45BBC2B11386E4A7|4684479ffad3a154de67dcecd171b0fc; _pk_id.100001.4cf6=366d4ffd8e378277.1463900106.28.1473146479.1472544605.; _pk_ses.100001.4cf6=*; __utma=30149280.768980729.1463900105.1472719026.1473143266.59; __utmb=30149280.0.10.1473143266; __utmc=30149280; __utmz=30149280.1472719026.58.40.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=30149280.14862; __utma=223695111.1046344479.1463900106.1472544397.1473143266.28; __utmb=223695111.0.10.1473143266; __utmc=223695111; __utmz=223695111.1473143266.28.25.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=douban")
					.get();
			Elements es = doc.select("#content .pl2>a");
			for (Element e : es) {
				String names = e.text();
				if (JavaUtil.isContainsIgnoreEmptyChar(videoName, names)) {
//					if (names.contains(videoName)) {
					String href = e.attr("href");
					hdPostImgUrl = getDoubanMovie(href.substring(33, href.lastIndexOf("/")));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(videoName+" "+ hdPostImgUrl);
		return hdPostImgUrl;
	}
	
	private static String getDoubanMovie(String videoId) {
		String hdPostImgUrl = null;
		String url = "https://movie.douban.com/subject/$id/photos?type=R";
		url = url.replaceFirst("\\$id", videoId);
		try {
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.89 Safari/537.36")
					.header("Host", "movie.douban.com")
					.header("Cookie", "bid=nLs7K-JJT60; ll=\"118163\"; gr_user_id=78535e2b-4e26-4fba-a19c-4e74589d860e; viewed=\"11597324_10517156_5317075_26259017\"; ps=y; _ga=GA1.2.768980729.1463900105; ap=1; push_noty_num=0; push_doumail_num=1; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1473143266%2C%22https%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3DUTF-8%26wd%3Ddouban%22%5D; _vwo_uuid_v2=331F3C2609E4B76F45BBC2B11386E4A7|4684479ffad3a154de67dcecd171b0fc; _pk_id.100001.4cf6=366d4ffd8e378277.1463900106.28.1473146479.1472544605.; _pk_ses.100001.4cf6=*; __utma=30149280.768980729.1463900105.1472719026.1473143266.59; __utmb=30149280.0.10.1473143266; __utmc=30149280; __utmz=30149280.1472719026.58.40.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=30149280.14862; __utma=223695111.1046344479.1463900106.1472544397.1473143266.28; __utmb=223695111.0.10.1473143266; __utmc=223695111; __utmz=223695111.1473143266.28.25.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=douban")
					.get();
			Elements es = doc.select("#content ul.poster-col4>li");
			Element e = chooseImgElement(es);
			if (e != null) {
				e = e.select("div.cover>a").get(0);
				String href = e.attr("href");
				hdPostImgUrl = getImgUrl(href.substring(38, href.lastIndexOf("/")));
			}
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
			//海报尺寸比例筛选
			if ((scale+0.11>=0.66) && (scale-0.11<=0.66)) {
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
