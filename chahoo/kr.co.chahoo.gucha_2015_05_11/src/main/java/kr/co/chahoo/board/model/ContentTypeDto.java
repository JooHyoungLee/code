package kr.co.chahoo.board.model;

public class ContentTypeDto {
	
	public int no;						//게시물 유형 표시순서
	public String content_type_id;		//게시물 유형 아이디
	public String content_type_name;	//게시물 유형 
	public String desc;					//게시물 유형 설명
	
	public ContentTypeDto() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent_type_id() {
		return content_type_id;
	}

	public void setContent_type_id(String content_type_id) {
		this.content_type_id = content_type_id;
	}

	public String getContent_type_name() {
		return content_type_name;
	}

	public void setContent_type_name(String content_type_name) {
		this.content_type_name = content_type_name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	
}
