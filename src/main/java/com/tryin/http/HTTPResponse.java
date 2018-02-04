package com.tryin.http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
/**
 * 
 * @TODO 发送请求数据
 * @author Tryin4Sage
 * @date 2018年2月3日下午5:22:19
 */
public class HTTPResponse {
	private OutputStream out;
	private File  entity;
	public File getEntity() {
		return entity;
	}
	public void setEntity(File entity) {
		this.entity = entity;
	}
	
	public HTTPResponse(OutputStream out) {
		this.out = out;
	}
	/*
	 * 回复客户端
	 */
	public void flush() {
		sendStatusLine();
		sendHeaders();
		sendContent();
	}
	/*
	 * 发送状态行
	 */
	private void sendStatusLine() {
		System.out.println("发送状态行");
		try {
			String line = "HTTP/1.1 200 OK";
			println(line);
		} catch (Exception e) {
			System.err.println("发送状态行异常");
		}
		
	}
	/*
	 * 发送响应头
	 */
	private void sendHeaders() {
		System.out.println("HttpResponse:发送响应头");
		try {
			String line = "Content-Type:text/html";
			System.out.println("header:"+line);
			println(line);
			
			line = "Content-Length:"+entity.length();
			System.out.println("header:"+line);
			println(line);
			//单独发送CRLF
			println("");
		} catch (Exception e) {
			System.err.println("发送响应头异常");
		}
	}
	/*
	 * 发送响应正文
	 */
	private void sendContent() {
		System.out.println("发送响应正文");
		try (
			FileInputStream fis = new FileInputStream(entity);
			BufferedInputStream bis = new BufferedInputStream(fis);
		){
			byte[] buf = new byte[1024*10];
			int len = -1;
			while ((len=bis.read(buf))!=-1) {
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			System.err.println("发送响应正文异常");
		}
		
	}
	
	private void println(String line) {
		try {
			out.write(line.getBytes("ISO8859-1"));
			out.write(13);
			out.write(10);
		} catch (Exception e) {
			System.err.println("发送数据异常");
		}
	}
	
}
