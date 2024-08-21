package com.jboard.controller.user;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DTO.user.userDTO;
import com.jboard.service.userService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet{

	
	private static final long serialVersionUID = 4818082532605926530L;
	
	private userService service = userService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		
		req.setAttribute("success", success);
		
		HttpSession session = req.getSession();
		userDTO sessUser = (userDTO) session.getAttribute("sessUser");
		
		if(sessUser != null) {
			//로그인 상태이면 게시판 목록 이동
			resp.sendRedirect("/jboard/article/list.do");
		}else {
			//로그인 상태가 아니면
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/login.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		logger.debug("uid : " + uid + ", pass : "+ pass);
		
		userDTO user = service.selectUser(uid, pass);
		
		if(user != null) {
			//회원이 맞을 경우 -> 세션처리 후 리다이렉트
			HttpSession session = req.getSession();
			session.setAttribute("sessUser", user);
			
			resp.sendRedirect("/jboard/article/list.do");
			
		}else {
			//회원이 아닐 경우
			resp.sendRedirect("/jboard/user/login.do?success=100");
		}
	}
}
