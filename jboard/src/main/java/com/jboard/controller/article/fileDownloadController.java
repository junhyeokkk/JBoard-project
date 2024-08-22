package com.jboard.controller.article;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DTO.article.fileDTO;
import com.jboard.service.fileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/fileDownload.do")
public class fileDownloadController extends HttpServlet{


	private static final long serialVersionUID = 4818082532605926530L;

	private fileService service = fileService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fno = req.getParameter("fno");

		// 파일 조회
		fileDTO fileDto = service.selectFile(fno);

		// 공유 참조 (service -> fileDownload 메서드에서 getAttribute로 가져감)
		req.setAttribute("fileDto", fileDto);

		// 파일 다운로드 카운트 업데이트
		service.updateFileDownloadCount(fno);
		
		// 파일 다운로드
		service.fileDownload(req, resp);
	}
}
