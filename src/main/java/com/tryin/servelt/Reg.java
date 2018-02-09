package com.tryin.servelt;

import com.tryin.http.HttpRequest;
import com.tryin.http.HttpRsponse;
import com.tryin.vo.DateResult;

public class Reg extends HttpServlet{
	public void service(HttpRequest request,HttpRsponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		
		if (DateResult.ifExist(username)) {
			//响应失败页面
			forward("myweb/reg_f.html", request, response);
		} else {
			DateResult.isinFoDate(username, password, phone, nickname);
			//响应成功页面
			forward("/myweb/reg_s.html", request, response);
		}
	}
}
