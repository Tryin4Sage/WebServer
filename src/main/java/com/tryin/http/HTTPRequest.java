package com.tryin.http;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.tryin.context.HttpContext;
import com.tryin.context.ServerContent;
/**
 * 
 * @TODO 解析请求数据
 * @author Tryin4Sage
 * @date 2018年2月3日下午5:06:54
 */
public class HttpRequest {
	private InputStream in;	//读取客户端发送的数据输入流
	private String method;	//请求方法
	private String url;		//请求路径
	private String protocol;//请求协议版本
	
	private String requestURI;//请求路径前面部分
	private String queryString;//请求附带参数部分
	//存储参数
	private Map<String,String> parameters = new HashMap<String,String>();
	
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
		parseContent();
		System.out.println("解析完毕");
	}
	/**
	 * 解析消息正文
	 */
	private void parseContent() {
		if (headers.containsKey(HttpContext.HEADER_CONTENT_LENGTH)) {
			try {
				System.out.println("解析消息正文");
				/*
				 * 判断是否为from表单对象数据
				 */
				String contentType = headers.get(HttpContext.HEADER_CONTENT_TYPE);
				if ("application/x-www-form-urlencoded".equals(contentType)) {
					System.out.println("处理表单数据中...");
					int length = Integer.parseInt(headers.get(HttpContext.HEADER_CONTENT_LENGTH));
					byte[] date = new byte[length];
					in.read(date);
					String line = new String(date);
					parseQueryString(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	private void parseQueryString(String line) {
		try {
			line = URLDecoder.decode(line, ServerContent.URIEncoding);
			String[] paraArray = line.split("&");
			for (String string : paraArray) {
				String[] arr = string.split("=");
				if (arr.length==2) {
					this.parameters.put(arr[0], arr[1]);
				} else {
					this.parameters.put(arr[0], "");
				}
			}
			System.out.println(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 循环读取若干行,每一行是一个消息头
	 */
	private void parseHeaders() {
		System.out.println("解析消息头");
		while (true) {
			String line = readLine();
			if("".equals(line)) {
				break;
			}
			int index = line.indexOf(":");
			String name = line.substring(0,index).trim();
			String value = line.substring(index+1).trim();
			headers.put(name,value);
		}
		System.out.println("==========================");
		
	}
	/**
	 * 解析请求行数据
	 * @throws EmptyRequestException
	 */
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
		
		parseUrl();
	}
	/**
	 * 解析地址中部分
	 */
	private void parseUrl() {
		int index = this.url.indexOf("?");//没有则返回-1;
		if (index ==-1) {
			this.requestURI = this.url;
		} else {
			this.requestURI = this.url.substring(0, index);
			this.queryString = this.url.substring(index+1);
			
			parseQueryString(queryString);
		}
	}
	/**
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
	
	public String getRequestURI(){
		return this.requestURI;
	}
	public String getQueryString(){
		return this.queryString;
	}
	public String getParameter(String name){
		return this.parameters.get(name);
	}
}
