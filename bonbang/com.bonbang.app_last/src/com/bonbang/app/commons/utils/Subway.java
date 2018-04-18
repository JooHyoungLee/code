package com.bonbang.app.commons.utils;

public class Subway {
	public int no;
	public int parent_no;
	public String name;
	public String location_x;
	public String location_y;
	
	public Subway()
	{
		
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation_x() {
		return location_x;
	}
	public void setLocation_x(String location_x) {
		this.location_x = location_x;
	}
	public String getLocation_y() {
		return location_y;
	}
	public void setLocation_y(String location_y) {
		this.location_y = location_y;
	}

	@Override
	public String toString() {
		return "Subway [no=" + no + ", parent_no=" + parent_no + ", name="
				+ name + ", location_x=" + location_x + ", location_y="
				+ location_y + "]";
	}
	
}
