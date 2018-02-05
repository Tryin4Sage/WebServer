package com.tryin;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @TODO 服务器主类
 * @author Tryin4Sage
 * @date 2018年2月3日下午4:23:40
 */
public class WebServer {
	/**
	 * 与客户端进行TCP连接的服务器
	 */
	private ServerSocket server;
	/**
	 * 初始化服务器
	 */
	public WebServer() {
		try {
			server = new ServerSocket(8088);
			System.out.println("服务器3.0已启动");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 开始工作的方法
	 */
	public void start() {
		try {
			while (true) {
				Socket socket = server.accept();
				ClientHandle handle = new ClientHandle(socket);
				Thread thread = new Thread(handle);
				thread.start();
				System.out.println("连接一枚客户端");
				System.err.println(socket.getInetAddress());
			}
		} catch (Exception e) {
			System.err.println("坑呢,连接异常了");
		}
	}
	
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}
	
}
