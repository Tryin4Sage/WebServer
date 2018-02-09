package com.tryin;

import java.io.File;
import java.net.Socket;

import com.tryin.context.HttpContext;
import com.tryin.context.ServerContent;
import com.tryin.http.EmptyRequestException;
import com.tryin.http.HttpRequest;
import com.tryin.http.HttpRsponse;
import com.tryin.servelt.HttpServlet;

/**
 * 
 * @TODO 该线程任务供主类使用,负责指定客户端交互
 * @author Tryin4Sage
 * @date 2018年2月3日下午4:25:10
 */
public class ClientHandle implements Runnable{
	private Socket socket; 
	
	public ClientHandle(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			//解析请求信息
			HttpRequest request = new HttpRequest(socket.getInputStream());	
			//创建响应对象
			HttpRsponse response = new HttpRsponse(socket.getOutputStream());
			
			String requestURI = request.getRequestURI();
			String servletName = ServerContent.getServletNameByURI(requestURI);
			if (servletName!=null) {
				Class cls = Class.forName(servletName);
				HttpServlet ser = (HttpServlet)cls.newInstance();
				ser.service(request, response);
			} else {
				
				File file = new File("webapps"+request.getUrl());
				System.err.println(file);
				if (file.exists()) {
					System.err.println("------------");
					String name = file.getName();
					String extension = name.substring(name.lastIndexOf(".")+1);
					String contentType = HttpContext.getMimeType(extension);
					
					response.setStatusCode(HttpContext.STATUS_CODE_OK);
					response.setContentType(contentType);
					response.setContentLength(file.length());
					
					response.setEntity(file);
					response.flush();
				}else {
					System.out.println("文件不存在");
				}
			}
		} catch (EmptyRequestException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.err.println("响应期间离奇异常");
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				System.err.println("关闭异常");
			}
		}
	}
}
