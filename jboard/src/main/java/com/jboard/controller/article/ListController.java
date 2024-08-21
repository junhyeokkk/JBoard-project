package com.jboard.controller.article;

import java.io.IOException;

import com.jboard.DTO.user.userDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet{


	private static final long serialVersionUID = 4818082532605926530L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/list.jsp");
		dispatcher.forward(req, resp);

	}
}
