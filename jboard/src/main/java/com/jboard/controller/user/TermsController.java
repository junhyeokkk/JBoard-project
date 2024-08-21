package com.jboard.controller.user;

import java.io.IOException;

import com.jboard.DTO.terms.termsDTO;
import com.jboard.service.termsService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{

	
	private static final long serialVersionUID = 4818082532605926530L;

	private termsService service = termsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 수신
		termsDTO terms = service.selectTerms();
		
		req.setAttribute("terms", terms);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/terms.jsp");
		dispatcher.forward(req, resp);
	}
}
