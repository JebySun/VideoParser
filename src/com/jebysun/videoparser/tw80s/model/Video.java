package com.jebysun.videoparser.tw80s.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频
 * @author Jeby Sun
 * @Date 2015-12-24
 */
public class Video implements Serializable {
	private static final long serialVersionUID = -2764906880573396559L;

	//视频类别：电影，电视剧，综艺，动漫
	private VideoType videoType;
	
	//视频名称
	private String name;
	
	//别名
	private String alias;

	//视频标注
	private String note;

	//视频详情地址
	private String detailUrl;
	
	//视频海报地址
	private String posterUrl;
	
	//视频截图地址
	private String screenShotUrl;

	//导演
	private String director;
	//演员
	private String actor;
	//类别
	private String category;
	//地区
	private String area;
	//语言
	private String language;
	//上映日期
	private String releaseDate;
	//影片时长
	private String duration;
	//评分
	private String score;
	//剧情简介
	private String story;
	//豆瓣影视ID
	private String doubanVideoId;
	
	//下载地址信息列表
	private List<DownloadInfo> downloadInfoList = new ArrayList<DownloadInfo>();
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}
	

	public List<DownloadInfo> getDownloadInfoList() {
		return downloadInfoList;
	}

	public void setDownloadInfoList(List<DownloadInfo> downloadInfoList) {
		this.downloadInfoList = downloadInfoList;
	}

	public VideoType getVideoType() {
		return videoType;
	}

	public void setVideoType(VideoType videoType) {
		this.videoType = videoType;
	}
	public String getScreenShotUrl() {
		return screenShotUrl;
	}

	public void setScreenShotUrl(String screenShotUrl) {
		this.screenShotUrl = screenShotUrl;
	}
	
	public String getDoubanVideoId() {
		return doubanVideoId;
	}

	public void setDoubanVideoId(String doubanVideoId) {
		this.doubanVideoId = doubanVideoId;
	}


	/**
	 * 视频类型
	 * @author Administrator
	 *
	 */
	public enum VideoType {
		MOVIE,   //电影
		TV,      //电视剧
		MANGA,   //动漫
		VARIETY, //综艺
		OTHER    //其他
	}

	
}







