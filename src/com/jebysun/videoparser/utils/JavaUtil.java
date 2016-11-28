package com.jebysun.videoparser.utils;

public final class JavaUtil {
	
	private JavaUtil () {}
	
	/**
	 * 判断字符串是否相等，比较时忽略空字符
	 * @param left
	 * @param right
	 * @return
	 */
	public static boolean isEqualsIgnoreEmptyChar(String leftStr, String rightStr) {
		if (leftStr == null || rightStr == null) {
			return false;
		}
		return leftStr.replaceAll(" ", "").equals(rightStr.replaceAll(" ", ""));
	}
	
	/**
	 * 判断字符串是否包含子字符串，忽略空字符
	 * @param keyStr
	 * @param contentStr
	 * @return
	 */
	public static boolean isContainsIgnoreEmptyChar(String keyStr, String contentStr) {
		if (keyStr == null || contentStr == null) {
			return false;
		}
		return contentStr.replaceAll(" ", "").contains(keyStr.replaceAll(" ", ""));
	}
	
	
}
