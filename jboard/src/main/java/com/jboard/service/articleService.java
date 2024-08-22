package com.jboard.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.DAO.articleDAO;
import com.jboard.DTO.article.articleDTO;
import com.jboard.DTO.article.pagegroupDTO;

public enum articleService {
	INSTANCE;

	private articleDAO dao = articleDAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	// 전체 게시물 갯수에서 마지막 페이지 번호 구하기 
	public int getLastPageNum(int total) {
		int lastPageNum = 0;

		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}

	// 페이지 시작번호(limit 용)
	public int getStartNum(int currentPageNum) {
		return (currentPageNum - 1) * 10;
	}

	// 현재 페이지번호 구하기
	public int getCurrentPage(String pg) {
		int currentPageNum = 1;
		if(pg != null) {
			currentPageNum = Integer.parseInt(pg);
		}
		return currentPageNum;
	}

	// 페이지 시작번호
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}

	// 현재 페이지 그룹 구하기
	public pagegroupDTO getCurrentPageGroup(int currentPageNum) {

		int currentPageGroup = (int) Math.ceil(currentPageNum / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;

		return new pagegroupDTO(pageGroupStart, pageGroupEnd);

	}
	public void updateArticleHitCount(String no) {
		dao.updateArticleHitCount(no);
	}
	public int insertArticle(articleDTO dto) {
		return dao.insertArticle(dto);
	}
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	public articleDTO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<articleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	public void updateArticle(articleDAO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
}

