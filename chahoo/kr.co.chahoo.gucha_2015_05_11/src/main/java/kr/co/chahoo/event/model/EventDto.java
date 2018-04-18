package kr.co.chahoo.event.model;

import java.sql.Timestamp;

public class EventDto {
	
	public int event_id;		//이벤트 아이디
	public String member_id;	//사용자 아이디
	public Timestamp start;		//시작일시
	public Timestamp end;		//종료일시
	public int all_day;			//하루종일
	public String title;		//이벤트 제목
	public String description;	//이벤트 설명
	public String border_color;	//이벤트 테두리 색
	public String color;		//이벤트 배경 색
	public String text_color;	//이벤트 텍스트 색
	public String url;			//이벤트 연결 주소
	public Timestamp reg_date;	//이벤트 등록 일시
	
	public EventDto() {
		super();
	}

	public EventDto(int event_id, String member_id, Timestamp start,
			Timestamp end, int all_day, String title, String description,
			String border_color, String color, String text_color, String url,
			Timestamp reg_date) {
		super();
		this.event_id = event_id;
		this.member_id = member_id;
		this.start = start;
		this.end = end;
		this.all_day = all_day;
		this.title = title;
		this.description = description;
		this.border_color = border_color;
		this.color = color;
		this.text_color = text_color;
		this.url = url;
		this.reg_date = reg_date;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public int getAll_day() {
		return all_day;
	}

	public void setAll_day(int all_day) {
		this.all_day = all_day;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBorder_color() {
		return border_color;
	}

	public void setBorder_color(String border_color) {
		this.border_color = border_color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getText_color() {
		return text_color;
	}

	public void setText_color(String text_color) {
		this.text_color = text_color;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "EventDto [event_id=" + event_id + ", member_id=" + member_id
				+ ", start=" + start + ", end=" + end + ", all_day=" + all_day
				+ ", title=" + title + ", description=" + description
				+ ", border_color=" + border_color + ", color=" + color
				+ ", text_color=" + text_color + ", url=" + url + ", reg_date="
				+ reg_date + "]";
	}

}
