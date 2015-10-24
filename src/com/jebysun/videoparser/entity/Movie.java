package com.jebysun.videoparser.entity;

import java.util.Date;

/**
 * 电影
 * @author Jeby Sun
 * @Date 2015-09-01
 */
public class Movie {
	
	//电影标题
	private String title;
	//电影名称（如果有多个名称，取第一个）
	private String name;
	//电影年代
	private String year;
	//制作国家（保持原样，斜杠分割多个国家）
	private String location;
	//类别（保持原样，斜杠分割多个类别）
	private String category;
	//影片长度（以分钟为单位，格式可能需要换算）
	private String duration;
	//导演（保持原样，斜杠分割多个导演）
	private String director;
	//主演
	private String roleActor;
	//电影简介
	private String introduction;
	//电影详情地址
	private String detailUrl;
	//电影海报地址
	private String posterUrl;
	//电影截图地址
	private String printscreenUrl;
	//电影下载地址
	private String downloadUrl;
	//上传发布日期
	private Date uploadDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getPosterUrl() {
		return posterUrl;
	}
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	public String getPrintscreenUrl() {
		return printscreenUrl;
	}
	public void setPrintscreenUrl(String printscreenUrl) {
		this.printscreenUrl = printscreenUrl;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getRoleActor() {
		return roleActor;
	}
	public void setRoleActor(String roleActor) {
		this.roleActor = roleActor;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
}
