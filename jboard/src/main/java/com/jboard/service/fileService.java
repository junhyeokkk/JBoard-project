package com.jboard.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DAO.fileDAO;
import com.jboard.DTO.article.fileDTO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public enum fileService {

	INSTANCE;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private fileDAO dao = fileDAO.getInstance();

	public List<fileDTO> fileUpload(HttpServletRequest req) {

		List<fileDTO> files = new ArrayList<fileDTO>();

		ServletContext ctx = req.getServletContext();
		String uploadPath = ctx.getRealPath("/uploads");
		logger.debug("uploadPath : "+uploadPath);

		try {
			Collection<Part> parts= req.getParts(); // 첨부 파일 정보객체 불러오기

			for(Part part : parts) {

				// 파일명 추출 
				String ofileName = part.getSubmittedFileName();

				// 파일을 첨부했으면 
				if(ofileName != null && !ofileName.isEmpty()) {
					logger.debug("ofileName : " + ofileName);

					// 고유 파일명 생성
					int idx = ofileName.lastIndexOf(".");
					String ext = ofileName.substring(idx);

					String sfileName = UUID.randomUUID().toString() + ext;
					logger.debug("sfileName : "+ sfileName);

					// 파일 저장
					part.write(uploadPath + File.separator+ sfileName);

					//fileDTO생성
					fileDTO fdto = new fileDTO();
					fdto.setoName(ofileName);
					fdto.setsName(sfileName);
					files.add(fdto);

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} 
		return files;
	}
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
		// 공유 참조값 가져오기
		fileDTO fileDto = (fileDTO) req.getAttribute("fileDto");
		
		//response 헤더정보 수정
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDto.getoName(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");

		// 파일 내용 스트림 처리 
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/uploads");
		File file = new File(path + File.separator + fileDto.getsName());

		
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(resp.getOutputStream());
		
			bis.transferTo(bos);
			bos.flush();
		} catch(Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
					
		}
			
	}

	public void insertFile(fileDTO dto) {
		dao.insertFile(dto);
	}
	public fileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	public List<fileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(fileDTO dto) {
		dao.updateFile(dto);
	}
	public void updateFileDownloadCount(String fno) {
		dao.updateFileDownloadCount(fno);
	}
	public void deleteFile(int fno) {
		dao.deleteFile(fno);
	}
}
