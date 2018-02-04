package com.tryin.http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tryin.context.HttpContext;
/**
 * 
 * @TODO 发送请求数据
 * @author Tryin4Sage
 * @date 2018年2月3日下午5:22:19
 */
public class HttpRsponse {
	private OutputStream out;
	private File  entity;
	//响应消息头
	private Map<String,String> headers = new HashMap<String,String>();
	public File getEntity() {
		return entity;
	}
	public void setEntity(File entity) {
		this.entity = entity;
	}
	public HttpRsponse(OutputStream out) {
		this.out = out;
	}
	public void setContentType(String contentType) {
		this.headers.put(HttpContext.HEADER_CONTENT_TYPE, contentType);
	}
	public void setContentLength(long length) {
		this.headers.put(HttpContext.HEADER_CONTENT_TYPE, length+"");
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
			for(Entry<String,String> e : headers.entrySet()) {
				String line = e.getKey()+":"+e.getValue();
				System.out.println("header:"+line);
				println(line);
			}
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
			out.write(HttpContext.CR);
			out.write(HttpContext.LF);
		} catch (Exception e) {
			System.err.println("发送数据异常");
		}
	}
	
}
