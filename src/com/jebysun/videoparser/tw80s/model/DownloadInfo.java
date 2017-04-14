package com.jebysun.videoparser.tw80s.model;

import java.io.Serializable;
/**
 * 视频资源下载信息
 * @author Jeby Sun
 * @Date 2017-02-20
 */
public class DownloadInfo implements Serializable {
	private static final long serialVersionUID = 6274787417408774389L;

	//连接标题
	private String title;
	private String downloadUrl;
	private String fileSize;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
}
