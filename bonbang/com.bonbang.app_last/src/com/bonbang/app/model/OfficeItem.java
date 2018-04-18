package com.bonbang.app.model;

import java.util.ArrayList;

public class OfficeItem {
	public String uid;
	public String image;
	public String office_name;
	public String ceo;
	public String address;
	public String room_cnt;
	public String phone;
	public ArrayList<MapListItem> room_info;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRoom_cnt() {
		return room_cnt;
	}
	public void setRoom_cnt(String room_cnt) {
		this.room_cnt = room_cnt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ArrayList<MapListItem> getRoom_info() {
		return room_info;
	}
	public void setRoom_info(ArrayList<MapListItem> room_info) {
		this.room_info = room_info;
	}
	@Override
	public String toString() {
		return "OfficeItem [uid=" + uid + ", image=" + image + ", office_name="
				+ office_name + ", ceo=" + ceo + ", address=" + address
				+ ", room_cnt=" + room_cnt + ", phone=" + phone
				+ ", room_info=" + room_info + "]";
	}
	public OfficeItem() {
		super();
	}
}
