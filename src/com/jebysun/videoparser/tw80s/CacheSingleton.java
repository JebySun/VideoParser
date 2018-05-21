package com.jebysun.videoparser.tw80s;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jebysun.videoparser.tw80s.model.SearchKeyword;

/**
 * 缓存单例
 * @author Administrator
 *
 */
public class CacheSingleton {
	
	private static final String SEARCH_KEYWORD = "search_keyword";

	private static volatile CacheSingleton singleton;

	private Map<String, Object> cacheMap = new HashMap<>();
	
	private CacheSingleton() {}
	
	public static CacheSingleton getInstance() {
		if (singleton == null) {
			synchronized(CacheSingleton.class) {
				if (singleton == null) {
					singleton = new CacheSingleton();
				}
			}
		}
		return singleton;
	}
	
	public static void put(String key, Object value) {
		getInstance().cacheMap.put(key, value);
	}
	
	public static Object get(String key) {
		return getInstance().cacheMap.get(key);
	}
	
	public static void putVideoSearchWord(List<SearchKeyword> value) {
		put(SEARCH_KEYWORD, value);
	}
	
	public static List<SearchKeyword> getVideoSearchWord() {
		return (List) get(SEARCH_KEYWORD);
	}
	
	
}
