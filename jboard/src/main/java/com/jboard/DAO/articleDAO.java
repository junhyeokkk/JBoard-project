package com.jboard.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DTO.article.articleDTO;
import com.jboard.DTO.article.fileDTO;
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
	public int selectCountTotal() {
		int total = 0 ;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_COUNT_TOTAL);

			if(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return total;
	}
	public articleDTO selectArticle(int no) {
		articleDTO dto =  null;
		List<fileDTO> files = new ArrayList<fileDTO>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setInt(1, no);

			rs = psmt.executeQuery();

			while(rs.next()) {
				// 한개의 게시물에 2개이상에 파일이 있을때 다시 dto가 초기화되므로 자원낭비 방지
				if(dto == null) {
					dto =  new articleDTO();
					dto.setNo(rs.getInt(1));
					dto.setCate(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setComment(rs.getInt(5));
					dto.setFile(rs.getInt(6));
					dto.setHit(rs.getInt(7));
					dto.setWriter(rs.getString(8));
					dto.setRegip(rs.getString(9));
					dto.setRdate(rs.getString(10));
				}
				
				fileDTO fdto = new fileDTO();
				fdto.setFno(rs.getInt(11));
				fdto.setAno(rs.getInt(12));
				fdto.setoName(rs.getString(13));
				fdto.setsName(rs.getString(14));
				fdto.setDownload(rs.getInt(15));
				fdto.setRdate(rs.getString(16));
				files.add(fdto);
			}
			dto.setFiles(files); 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}

		return dto;
	}
	public List<articleDTO> selectArticles(int start) {
		List<articleDTO> dtos = new ArrayList<articleDTO>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();

			while(rs.next()) {
				articleDTO dto = new articleDTO();
				dto.setNo(rs.getInt(1));
				dto.setCate(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setComment(rs.getInt(5));
				dto.setFile(rs.getInt(6));
				dto.setHit(rs.getInt(7));
				dto.setWriter(rs.getString(8));
				dto.setRegip(rs.getString(9));
				dto.setRdateSub(rs.getString(10));
				dto.setNick(rs.getString(11));
				dtos.add(dto);
			}
			closeAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return dtos;
	}
	public void updateArticleHitCount(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_HIT_COUNT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
	}
	public void updateArticle(articleDAO dto) {

	}
	public void deleteArticle(int no) {

	}
}
