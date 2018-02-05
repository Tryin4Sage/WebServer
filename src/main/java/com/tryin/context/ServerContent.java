package com.tryin.context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 服务器配置信息
 * @author Tryin4Sage
 * @date 2018年2月4日下午11:07:58
 */
public class ServerContent {
	public static String protocol;	//协议版本
	public static String URIEncoding;//URI字符集
	public static int threadPoolSum;//线程池数量
	public static int prot;			//服务端口
	//请求映射关系表
	private static Map<String,String> servletMappings = new HashMap<String,String>();
	
	public static String getServletNameByURI(String uri) {
		return servletMappings.get(uri);
	}
	
	static {
		initServerConfig();
		initServletMapping();
	}
	/**
	 * 初始化服务端基础配置信息
	 */
	private static void initServerConfig() {
		try {
			Properties cfg = new Properties();
			InputStream in = ServerContent.class.getClassLoader()
					.getResourceAsStream("config.properties");
			cfg.load(in);
			
			protocol = cfg.getProperty("protocol");
			URIEncoding = cfg.getProperty("URIEncoding");
			threadPoolSum = Integer.parseInt(cfg.getProperty("threadPool"));
			prot = Integer.parseInt(cfg.getProperty("prot"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化Servlet映射
	 */
	@SuppressWarnings("unchecked")
	private static void initServletMapping() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new FileInputStream("conf/servlets.xml"));
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for (Element ele : list) {
				String uri = ele.attributeValue("uri");
				String classname = ele.attributeValue("class");
				servletMappings.put(uri, classname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
