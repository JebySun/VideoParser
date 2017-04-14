package com.jebysun.videoparser.tw80s.util;

public class Tw80sUtil {
	private Tw80sUtil() {}
	
	
    /**
     * 判断字符串是否为null, "", "       "
     * @param string
     * @return
     */
    public static boolean isEmptyString(String string) {
        if (string == null) {
            return true;
        }
        return string.trim().length()==0;
    }
    
    public static String getDoubanIdFromCommentUrl(String commentUrl) {
    	String sid = null;
    	if (commentUrl == null) {
    		sid = null;
    	}
    	String[] urlSplits = commentUrl.split("/");
    	if (urlSplits.length == 6) {
    		sid = urlSplits[4];
    	}
    	return sid;
    }
}
