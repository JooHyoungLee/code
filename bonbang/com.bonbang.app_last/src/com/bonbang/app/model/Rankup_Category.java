package com.bonbang.app.model;

public class Rankup_Category {
	
	public int no;
	public String code;
	public int parent_no;
	public String list_name;
	
	public Rankup_Category()
	{
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public String getList_name() {
		return list_name;
	}
	public void setList_name(String list_name) {
		this.list_name = list_name;
	}
	@Override
	public String toString() {
		return "Rankup_Category [no=" + no + ", code=" + code + ", parent_no="
				+ parent_no + ", list_name=" + list_name + "]";
	}
}
