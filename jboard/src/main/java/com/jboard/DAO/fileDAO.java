package com.jboard.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DTO.article.fileDTO;
import com.jboard.util.DBHelper;
import com.jboard.util.SQL;

public class fileDAO extends DBHelper{

	private static fileDAO instance = new fileDAO();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static fileDAO getInstance() {
		return instance;
	}
	
	private fileDAO() {};
	
	public void insertFile(fileDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getoName());
			psmt.setString(3, dto.getsName());
			psmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public fileDTO selectFile(int fno) {
		return null;
	}
	public List<fileDTO> selectFiles() {
		return null;
	}
	public void updateFile(fileDTO dto) {
		
	}
	public void deleteFile(int fno) {
		
	}
}
