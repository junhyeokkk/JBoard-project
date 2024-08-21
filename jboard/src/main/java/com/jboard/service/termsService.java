package com.jboard.service;

import com.jboard.DAO.termsDAO;
import com.jboard.DTO.terms.termsDTO;
import com.jboard.util.DBHelper;

public enum termsService{

	INSTANCE;
	
	private termsDAO dao = termsDAO.getInstance();
	
	// CRUD메서드
		public termsDTO selectTerms() {
			
			return dao.selectTerms();
		}
}
