package com.tryin.http;

import java.io.InputStream;
/**
 * 
 * @TODO 解析请求数据
 * @author Tryin4Sage
 * @date 2018年2月3日下午5:06:54
 */
public class HTTPRequest {
	/*
	 * 读取客户端发送的数据输入流
	 */
	private InputStream in;
	private String method;	//请求方法
	private String url;		//请求路径
	private String protocol;//请求协议版本
	public String getMethod() {
		return method;
	}
	public String getUrl() {
		return url;
	}
	public String getProtocol() {
		return protocol;
	}
	
	public HTTPRequest(InputStream in) {
		System.out.println("开始解析工作");
		this.in = in;
		parsetRequestLine();
		System.out.println("解析完毕");
	}

	private void parsetRequestLine() {
		String requestLine = readLine();
		System.out.println("测试数据"+requestLine);
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
				if(c2==13&&c1==10)break;
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
