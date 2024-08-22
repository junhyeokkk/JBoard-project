package com.jboard.controller.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.jboard.DTO.article.articleDTO;
import com.jboard.DTO.article.pagegroupDTO;
import com.jboard.DTO.user.userDTO;
import com.jboard.service.articleService;

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

	private articleService service = articleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pg = req.getParameter("pg");
		
		// 현재 페이지 번호 구하기
		int currentPageNum = service.getCurrentPage(pg);
		
		// 현재 페이지 그룹 구하기
		pagegroupDTO pageGroup = service.getCurrentPageGroup(currentPageNum);
		
		// 전체 게시물 갯수 구하기 
		int total = service.selectCountTotal();

		// 마지막 페이지 번호 
		int pageLastNum = service.getLastPageNum(total);
		
		// 페이지 시작 번호 구하기
		int start = service.getStartNum(currentPageNum);
		
		// 페이지 시작 번호 구하기(목록에서 순서번호로 활용)
		int pageStartNum = service.getPageStartNum(total, currentPageNum);
		
		//데이터 조회
		List<articleDTO> articles = service.selectArticles(start);
		
		//공유 참조
		req.setAttribute("articles", articles);
		req.setAttribute("currentPageNum", currentPageNum);
		req.setAttribute("pageLastNum", pageLastNum);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("pageGroup", pageGroup);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/list.jsp");
		dispatcher.forward(req, resp);

	}
}
