package com.jboard.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelper {

	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;

	protected Connection getConnection() throws NamingException, SQLException {
		//1단계 - JNDI 서비스 객체 생성
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");


		//2단계 - 커넥션 풀에서 커넥션 객체 가져오기
		DataSource ds = (DataSource) ctx.lookup("jdbc/jboard"); //커넥션 풀 이름 
		return ds.getConnection();
	}

	protected void closeAll()  {


		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
