package com.jebysun.videoparser.tw80s.exception;

/**
 * 豆瓣访问权限异常
 * @author Administrator
 *
 */
public class PermissionException extends Exception {
	private static final long serialVersionUID = -5804073161266378219L;
	
	public PermissionException(String msg) {
		super(msg);
	}
	
}
