package com.tryin.servelt;

import java.io.File;

import com.tryin.context.HttpContext;
import com.tryin.http.HttpRequest;
import com.tryin.http.HttpRsponse;

public class HttpServlet {
	/**
	 * 实际开发使用Tomcat,常用于被子类业务继承
	 * @param request
	 * @param response
	 */
	public void service(HttpRequest request,HttpRsponse response) {
	}
	/**
	 * 跳转指定页面
	 */
	public void forward(String uri,HttpRequest request,HttpRsponse response) {
		File page = new File("webapps"+uri);
		response.setStatusCode(HttpContext.STATUS_CODE_OK);
		response.setContentLength(page.length());
		response.setContentType("text/html");
		response.flush();
	}
	
}
