package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import com.jboard.DTO.article.articleDTO;
import com.jboard.DTO.article.fileDTO;
import com.jboard.DTO.user.userDTO;
import com.jboard.service.articleService;
import com.jboard.service.fileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/write.do")
public class WriteController extends HttpServlet{


	private static final long serialVersionUID = 4818082532605926530L;

	private articleService articleservice = articleService.INSTANCE;
	private fileService fileservice = fileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/write.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();

		//파일 업로드
		List<fileDTO> files = fileservice.fileUpload(req);

		articleDTO dto = new articleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(files.size());
		dto.setWriter(writer);
		dto.setRegip(regip);

		//글 등록
		int no = articleservice.insertArticle(dto);

		//파일 등록
		for(fileDTO file : files) {
			file.setAno(no);
			fileservice.insertFile(file);
		}
		resp.sendRedirect("/jboard/article/list.do");
	}

}
