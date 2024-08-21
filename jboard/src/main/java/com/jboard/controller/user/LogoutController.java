package com.jboard.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/logout.do")
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 6648416039311225836L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그아웃
		HttpSession session = req.getSession();
		session.removeAttribute("sessUser");
		session.invalidate();
		
		resp.sendRedirect("/jboard/user/login.do?success=101");
	}
}
