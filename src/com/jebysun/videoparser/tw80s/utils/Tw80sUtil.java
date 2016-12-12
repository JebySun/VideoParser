package com.jebysun.videoparser.tw80s.utils;

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
	
}
