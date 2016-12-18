package com.jebysun.videoparser.tw80s;

import java.util.List;

import com.jebysun.videoparser.tw80s.model.Video;

public interface Parser {
	
	public List<Video> listMovie(String category, String area, String language, String year, String sort, int pageIndex);
	
	public List<Video> listTV(String category, String area, String year, String sort, int pageIndex);
}
