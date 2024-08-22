package com.jboard.DTO.article;

public class pagegroupDTO {
	
	private int start;
	private int end;
	
	
	public pagegroupDTO(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "pagegroupDTO [start=" + start + ", end=" + end + "]";
	}
}
