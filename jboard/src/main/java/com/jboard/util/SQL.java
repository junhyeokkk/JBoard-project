package com.jboard.util;

public class SQL {
	
	public static final String SELECT_TERMS = "select * from terms";
	public static final String SELECT_COUNT_USER = "SELECT COUNT(*) FROM `user` ";
	public static final String WHERE_UID  = "WHERE `uid`=?";
	public static final String WHERE_NICK = "WHERE `nick`=?";
	public static final String WHERE_EMAIL = "WHERE `email`=?";
	public static final String WHERE_HP = "WHERE `hp`=?";
	
	public static final String SELELT_USER = "select * from user where uid =? and pass=SHA2(?,256)";
	public static final String INSERT_USER = "insert into user set "
											+ "`uid` = ?, "
											+ "`pass` = SHA2(?, 256), "
											+ "`name` = ?, "
											+ "`nick` = ?, "
											+ "`email` = ?, "
											+ "`hp` = ?, "
											+ "`zip` = ?, "
											+ "`addr1` = ?, "
											+ "`addr2` = ?, "
											+ "`regip` = ?, "
											+ "`regDate` = NOW()";
	// article
	public static final String INSERT_ARTICLE = "insert into article set "
												+ "`title`=?,"
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	public static final String SELECT_MAX_NO = "select MAX(no) from article";
	
	//file
	public static final String INSERT_FILE = "insert into file set "
											+ "ano = ?,"
											+ "oName = ?,"
											+ "sName = ?,"
											+ "rdate = NOW()";
		
}
