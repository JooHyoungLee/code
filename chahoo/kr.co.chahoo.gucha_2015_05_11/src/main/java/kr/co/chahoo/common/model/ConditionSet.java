package kr.co.chahoo.common.model;

import java.sql.Timestamp;

public class ConditionSet {
	
	private String key;			//게시판 아이디
	private String userid;		//사용자 고유 아이디
	private int offset;
	private int limit;
	private Timestamp dateFrom;	//시작일시
	private Timestamp dateTo;	//종료일시
	private int intParam;		//제목,내용 검색
	private String ctype;		//게시물 유형
	private String year;		//게시물 연도
	private String strParam;	//검색어
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Timestamp getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Timestamp getDateTo() {
		return dateTo;
	}
	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}
	public int getIntParam() {
		return intParam;
	}
	public void setIntParam(int intParam) {
		this.intParam = intParam;
	}
	public String getStrParam() {
		return strParam;
	}
	public void setStrParam(String strParam) {
		this.strParam = strParam;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "ConditionSet [key=" + key + ", userid=" + userid + ", offset="
				+ offset + ", limit=" + limit + ", dateFrom=" + dateFrom
				+ ", dateTo=" + dateTo + ", intParam=" + intParam + ", ctype="
				+ ctype + ", year=" + year + ", strParam=" + strParam + "]";
	}
}
