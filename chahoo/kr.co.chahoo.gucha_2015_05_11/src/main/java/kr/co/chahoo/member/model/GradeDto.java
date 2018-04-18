package kr.co.chahoo.member.model;

public class GradeDto {
	
	public String grade_id;		//직급 아이디
	public String group_id;		//부서 아이디
	public String grade_name;	//직급 이름
	public String no;			//표시 순서
	public String desc;			//설명
	
	public GradeDto() {
		super();
	}
	
	public GradeDto(String grade_id, String group_id, String grade_name,
			String no, String desc) {
		super();
		this.grade_id = grade_id;
		this.group_id = group_id;
		this.grade_name = grade_name;
		this.no = no;
		this.desc = desc;
	}
	
	public String getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(String grade_id) {
		this.grade_id = grade_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "GradeDto [grade_id=" + grade_id + ", group_id=" + group_id
				+ ", grade_name=" + grade_name + ", no=" + no + ", desc="
				+ desc + "]";
	}
}
