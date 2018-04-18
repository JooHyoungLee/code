package kr.co.chahoo.member.model;

import java.sql.Timestamp;

public class MemberDto {
	
	public String member_id;	//사용자 고유 아이디
	public String name;			//사용자 이름
	public String password;		//비밀번호
	public String group_id;		//부서 아이디
	public String group_name;	//부서 이름
	public String grade_id;		//직급 아이디
	public String grade_name;	//직급 이름
	public String email;		//이메일
	public String cell_phone;	//휴대전화
	public String tele_phone;	//내선번호
	public Timestamp reg_date;	//가입일
	public int is_status;		//회원 상태	0:미사용 ,1:사용
	
	public MemberDto() {
		super();
	}

	public MemberDto(String member_id, String name, String password,
			String group_id, String group_name, String grade_id,
			String grade_name, String email, String cell_phone,
			String tele_phone, Timestamp reg_date, int is_status) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.password = password;
		this.group_id = group_id;
		this.group_name = group_name;
		this.grade_id = grade_id;
		this.grade_name = grade_name;
		this.email = email;
		this.cell_phone = cell_phone;
		this.tele_phone = tele_phone;
		this.reg_date = reg_date;
		this.is_status = is_status;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(String grade_id) {
		this.grade_id = grade_id;
	}

	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getTele_phone() {
		return tele_phone;
	}

	public void setTele_phone(String tele_phone) {
		this.tele_phone = tele_phone;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getIs_status() {
		return is_status;
	}

	public void setIs_status(int is_status) {
		this.is_status = is_status;
	}

	@Override
	public String toString() {
		return "MemberDto [member_id=" + member_id + ", name=" + name
				+ ", password=" + password + ", group_id=" + group_id
				+ ", group_name=" + group_name + ", grade_id=" + grade_id
				+ ", grade_name=" + grade_name + ", email=" + email
				+ ", cell_phone=" + cell_phone + ", tele_phone=" + tele_phone
				+ ", reg_date=" + reg_date + ", is_status=" + is_status + "]";
	}
}
