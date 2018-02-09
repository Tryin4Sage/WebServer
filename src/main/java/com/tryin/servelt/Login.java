package com.tryin.servelt;

import com.tryin.http.HttpRequest;
import com.tryin.http.HttpRsponse;
import com.tryin.vo.DateResult;

public class Login extends HttpServlet{
	@Override
	public void service(HttpRequest request, HttpRsponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (DateResult.ifTrue(username, password)) {
			response.sengRedirect("login_suc.html");
		} else {
			response.sengRedirect("login_fail.html");
		}
	}
}
