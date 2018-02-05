package com.tryin.context;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * http中一些参数
 * @TODO
 * @author Tryin4Sage
 * @date 2018年2月4日下午2:04:18
 */
public class HttpContext {
	public static final int CR = 13;
	public static final int LF = 10;
	public static final int STATUS_CODE_OK = 200;		//状态码:正常
	public static final int STATUS_CODE_FOUND = 404;	//状态码:未发现资源
	public static final int STATUS_CODE_ERROR = 500;	//状态码:请求错误
	public static final int STATUS_CODE_REIRECT = 302;	//状态码:重定向
	
	private static Map<Integer,String> codeReasonMap = new HashMap<>();
	
	//http协议中信息头定义
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CONTENT_LENGTH = "Content-Length";
	public static final String HEADER_LOCALTION = "Loaction";
	
	//介质类型映射信息
	private static Map<String,String> mimeTypeMapping = new HashMap<>();
	
	public static String getMimeType(String extension) {
		return mimeTypeMapping.get(extension);
	}
	public static String getStatusReasonByStatueCode(int code) {
		return codeReasonMap.get(code);
	}
	
	//静态初始化参数
	static {
		init();
	}

	private static void init() {
		initMimeTypeMapping();
		initCodeReason();
	}
	/**
	 * 初始化介质类型映射信息
	 * 读取web.xml文档,将<mime-mapping>的子标签解析出来
	 */
	private static void initMimeTypeMapping() {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File("conf/web.xml"));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> mimeList = root.elements("mime-mapping");
			for (Element ele : mimeList) {
				String ex = ele.elementText("extension");
				String type = ele.elementText("mime-type");
				mimeTypeMapping.put(ex, type);
			}
		} catch (Exception e) {
			System.err.println("解析web.xml出错");
		}
	}
	/**
	 * 初始化状态码描述信息
	 */
	private static void initCodeReason() {
		codeReasonMap.put(200, "OK");
		codeReasonMap.put(404, "NOT FOUND");
		codeReasonMap.put(302, "MOVED PERMANENTLY");
		codeReasonMap.put(500, "INTERNAL SERVER ERROR");
	}
	
}
