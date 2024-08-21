package com.jboard.service;

import java.io.File;
import java.io.IOException;
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
	public void fileDownload() {
		
	}
	
	public void insertFile(fileDTO dto) {
		dao.insertFile(dto);
	}
	public fileDTO selectFile(int fno) {
		return dao.selectFile(fno);
	}
	public List<fileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(fileDTO dto) {
		dao.updateFile(dto);
	}
	public void deleteFile(int fno) {
		dao.deleteFile(fno);
	}
}
