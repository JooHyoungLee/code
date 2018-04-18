package kr.co.chahoo.board.model;

public class ContentYearDto {
	
	public String content_year;	//게시물 연도
	public int year_count;		//게시물 연도별 수
	public String color;		//연도별 색(미사용)
	
	public ContentYearDto() {
		super();
	}

	public String getContent_year() {
		return content_year;
	}

	public void setContent_year(String content_year) {
		this.content_year = content_year;
	}

	public int getYear_count() {
		return year_count;
	}

	public void setYear_count(int year_count) {
		this.year_count = year_count;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
