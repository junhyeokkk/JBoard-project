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
	public static final String SELECT_ARTICLES = "select a.*, b.nick from article as a "
												+"join user as b on a.writer = b.uid "
												+ "order by no desc limit ?, 10" ;
	public static final String SELECT_COUNT_TOTAL = "select count(*) from article";
	public static final String SELECT_ARTICLE = "select * from article as a "
												+ "left join file as b on a.no = b.ano "
												+ "where a.no = ?";
	public static final String UPDATE_ARTICLE_HIT_COUNT = "update article set hit = hit + 1 "
												+ "where no = ?";
	//file
	public static final String INSERT_FILE = "insert into file set "
											+ "ano = ?,"
											+ "oName = ?,"
											+ "sName = ?,"
											+ "rdate = NOW()";
	public static final String SELECT_FILE = "select * from file where fno=?";	
	public static final String UPDATE_FILE_DOWNLOAD_COUNT = "update file set download = download + 1 "
															+ "where fno = ?";
}
