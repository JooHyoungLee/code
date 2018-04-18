package kr.co.chahoo.board.model;

import java.sql.Timestamp;

public class ContentDto {
	
	public int content_id;				//게시물 아이디
	public String board_id;				//게시판 아이디
	public String content_type_id;		//게시물 유형 아이디
	public String content_type_name;	//게시물 유형
	public String content_year;			//게시물 연도
	public String member_id;			//사용자 아이디
	public String name;					//사용자 이름
	public String title;				//게시물 제목
	public String content_text;			//게시물 내용
	public Timestamp reg_date;			//게시물 등록일
	public Timestamp mod_date;			//게시물 수정일
	public int count;					//조회수
	public int is_file;					//파일첨부 여부
	public int is_status;				//사용여부
	
	public ContentDto() {
		super();
	}

	public ContentDto(int content_id, String board_id, String content_type_id,
			String content_type_name, String content_year, String member_id,
			String name, String title, String content_text, Timestamp reg_date,
			Timestamp mod_date, int count, int is_file, int is_status) {
		super();
		this.content_id = content_id;
		this.board_id = board_id;
		this.content_type_id = content_type_id;
		this.content_type_name = content_type_name;
		this.content_year = content_year;
		this.member_id = member_id;
		this.name = name;
		this.title = title;
		this.content_text = content_text;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
		this.count = count;
		this.is_file = is_file;
		this.is_status = is_status;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
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

	public String getContent_year() {
		return content_year;
	}

	public void setContent_year(String content_year) {
		this.content_year = content_year;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public Timestamp getMod_date() {
		return mod_date;
	}

	public void setMod_date(Timestamp mod_date) {
		this.mod_date = mod_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIs_file() {
		return is_file;
	}

	public void setIs_file(int is_file) {
		this.is_file = is_file;
	}

	public int getIs_status() {
		return is_status;
	}

	public void setIs_status(int is_status) {
		this.is_status = is_status;
	}

}
