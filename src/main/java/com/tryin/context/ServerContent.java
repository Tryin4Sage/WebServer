package com.tryin.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
	private static Map<String,String> servletMapping = new HashMap<String,String>();
	
	static {
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
	
}
