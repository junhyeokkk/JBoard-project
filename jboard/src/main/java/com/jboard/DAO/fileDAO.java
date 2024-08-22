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
	public fileDTO selectFile(String fno) {
		fileDTO fdto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);	
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				fdto = new fileDTO();
				fdto.setFno(rs.getInt(1));
				fdto.setAno(rs.getInt(2));
				fdto.setoName(rs.getString(3));
				fdto.setsName(rs.getString(4));
				fdto.setDownload(rs.getInt(5));
				fdto.setRdate(rs.getString(6));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return fdto;
	}
	public List<fileDTO> selectFiles() {
		return null;
	}
	public void updateFile(fileDTO dto) {
		
	}
	public void updateFileDownloadCount(String fno) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_FILE_DOWNLOAD_COUNT);
			psmt.setString(1, fno);
			psmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
	}
	public void deleteFile(int fno) {
		
	}
}
