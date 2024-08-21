package com.jboard.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DAO.articleDAO;
import com.jboard.DTO.article.articleDTO;

public enum articleService {
	INSTANCE;
	
	private articleDAO dao = articleDAO.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(articleDTO dto) {
		return dao.insertArticle(dto);
	}
	public articleDTO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<articleDTO> selectArticles(articleDTO dto) {
		return dao.selectArticles();
	}
	public void updateArticle(articleDAO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
}

