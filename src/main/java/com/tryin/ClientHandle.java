package com.tryin;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import com.tryin.http.HTTPRequest;
import com.tryin.http.HTTPResponse;

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
			HTTPRequest request = new HTTPRequest(socket.getInputStream());	
			//创建响应对象
			HTTPResponse response = new HTTPResponse(socket.getOutputStream());
			
			File file = new File("webapps"+request.getUrl());
			if (file.exists()) {
				response.setEntity(file);
				response.flush();
			}
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
