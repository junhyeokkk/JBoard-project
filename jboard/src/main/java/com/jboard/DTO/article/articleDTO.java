package com.jboard.DTO.article;

import java.util.List;

public class articleDTO {
	
	private int no;
	private String cate;
	private String title;
	private String content;
	private int comment;
	private int file;
	private int hit;
	private String writer;
	private String regip;
	private String rdate;

	// 추가 필드
	private String nick;
	private List<fileDTO> files;
	
	public List<fileDTO> getFiles() {
		return files;
	}
	public void setFiles(List<fileDTO> files) {
		this.files = files;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public void setRdateSub(String rdate) {
		this.rdate = rdate.substring(0,10);
	}
	@Override
	public String toString() {
		return "articleDTO [no=" + no + ", cate=" + cate + ", title=" + title + ", content=" + content + ", comment="
				+ comment + ", file=" + file + ", hit=" + hit + ", writer=" + writer + ", regip=" + regip + ", rdate="
				+ rdate + "]";
	}
	
	
}
