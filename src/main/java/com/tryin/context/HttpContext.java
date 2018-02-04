package com.tryin.context;

import java.util.HashMap;
import java.util.Map;
/**
 * http中一些参数
 * @TODO
 * @author Tryin4Sage
 * @date 2018年2月4日下午2:04:18
 */
public class HttpContext {
	public static final int CR = 13;
	public static final int LF = 10;
	
	//http协议中信息头定义
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CONTENT_LENGTH = "Content-Length";
	
	//介质类型映射信息
	private static Map<String,String> mimeTypeMapping = new HashMap<>();
	public static String getMimeType(String extension) {
		return mimeTypeMapping.get(extension);
	}

	//静态初始化参数
	static {
		init();
	}

	private static void init() {
		/*
		 * 临时先写死几个Content-Type对应的介质类型
		 * 后期会改为读取web.xml文件,将所有的介质类型
		 * 读取出来设置到这个map中
		 */
		mimeTypeMapping.put("html", "text/html");
		mimeTypeMapping.put("css", "text/css");
		mimeTypeMapping.put("jpg", "image/jpg");
		mimeTypeMapping.put("png", "image/png");
		mimeTypeMapping.put("js", "application/javascript");
	}
}
