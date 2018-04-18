package kr.co.chahoo.member.model;

public class GroupDto {
	
	public String group_id;			//부서 아이디
	public String group_name;		//부서 이름
	public String desc;				//부서 설명
	
	public GroupDto() {
		super();
	}

	public GroupDto(String group_id, String group_name, String desc) {
		super();
		this.group_id = group_id;
		this.group_name = group_name;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "GroupDto [group_id=" + group_id + ", group_name=" + group_name
				+ ", desc=" + desc + "]";
	}
	
}
