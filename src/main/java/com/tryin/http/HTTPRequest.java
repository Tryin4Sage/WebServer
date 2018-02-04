package com.tryin.http;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.tryin.context.HttpContext;
/**
 * 
 * @TODO 解析请求数据
 * @author Tryin4Sage
 * @date 2018年2月3日下午5:06:54
 */
public class HttpRequest {
	/*
	 * 读取客户端发送的数据输入流
	 */
	private InputStream in;
	private String method;	//请求方法
	private String url;		//请求路径
	private String protocol;//请求协议版本
	//消息头相关信息
	private Map<String,String> headers = new HashMap<String,String>();
	public String getMethod() {
		return method;
	}
	public String getUrl() {
		return url;
	}
	public String getProtocol() {
		return protocol;
	}
	public String getHeader(String name) {
		return headers.get(name);
	}
	public HttpRequest(InputStream in) throws EmptyRequestException {
		System.out.println("开始解析工作");
		this.in = in;
		parseRequestLine();
		parseHeaders();
		System.out.println("解析完毕");
	}
	/*
	 * 循环读取若干行,每一行是一个消息头
	 */
	private void parseHeaders() {
		System.out.println("解析消息头");
		while (true) {
			String line = readLine();
			if("".equals(line)) {
				System.out.println("111");
				break;
			}
			int index = line.indexOf(":");
			String name = line.substring(0,index).trim();
			String value = line.substring(index+1).trim();
			headers.put(name,value);
		}
		System.out.println("==========");
		
	}
	private void parseRequestLine() throws EmptyRequestException{
		String requestLine = readLine();
		if(requestLine.length()==0) {
			throw new EmptyRequestException("空请求");
		}
		
		System.out.println("测试请求行数据"+requestLine);
		
		String[] array = requestLine.split("\\s");	//按照空格拆分
		method = array[0];
		url = array[1];
		protocol = array[2];
	}
	/*
	 * 读取一行请求数据 遇到(CRLF)结尾 
	 */
	private String readLine() {
		StringBuilder builder = new StringBuilder();
		try {
			int d = -1;
			char c1 = 'a';
			char c2 = 'a';
			while ((d=in.read())!=-1) {
				c2 = (char)d;
				if(c1==HttpContext.CR&&c2==HttpContext.LF) {
					break;
				}
				builder.append(c2);
				c1 = c2;
			}
			return builder.toString().trim();
		} catch (Exception e) {
			System.err.println("解析读行出现错误");
		}
		return "";
	}
	
	
}
