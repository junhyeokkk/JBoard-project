package com.jboard.controller.article;

import java.io.IOException;

import com.jboard.DTO.article.articleDTO;
import com.jboard.service.articleService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/view.do")
public class ViewController extends HttpServlet{

	
	private static final long serialVersionUID = 4818082532605926530L;
	private articleService service = articleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String pg = req.getParameter("pg");
		
		// 데이터 조회
		articleDTO articleDto = service.selectArticle(Integer.parseInt(no));
		
		// 공유 참조
		req.setAttribute("articleDto", articleDto);
		req.setAttribute("pg", pg);
		
		// 조회수 증가 
		service.updateArticleHitCount(no);
		
		// 포워드 (화면출력)
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/view.jsp");
		dispatcher.forward(req, resp);
	}
}
