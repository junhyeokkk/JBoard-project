package com.jboard.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DTO.article.articleDTO;
import com.jboard.util.DBHelper;
import com.jboard.util.SQL;

public class articleDAO extends DBHelper{
	private static articleDAO instance = new articleDAO();
	
	public static articleDAO getInstance() {
		return instance;
	}
	private articleDAO() {};
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(articleDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			conn.commit();
	
			closeAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return no;
	}
	public articleDTO selectArticle(int no) {
		return null;
	}
	public List<articleDTO> selectArticles() {
		return null;
	}
	public void updateArticle(articleDAO dto) {
		
	}
	public void deleteArticle(int no) {
		
	}
}
