package com.bonbang.app.model;

public class BangItem {
	public String wolse;
	public String floor;
	public String gunmul;
	public String address;
	public String subject;
	public String no;
	public String floor_this;
	public String gujo;
	public String bool_company;
	public String iamge;
	public String bojung;
	public String lat;
	public String lng;
	public String getWolse() {
		return wolse;
	}
	public void setWolse(String wolse) {
		this.wolse = wolse;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getGunmul() {
		return gunmul;
	}
	public void setGunmul(String gunmul) {
		this.gunmul = gunmul;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getFloor_this() {
		return floor_this;
	}
	public void setFloor_this(String floor_this) {
		this.floor_this = floor_this;
	}
	public String getGujo() {
		return gujo;
	}
	public void setGujo(String gujo) {
		this.gujo = gujo;
	}
	public String getBool_company() {
		return bool_company;
	}
	public void setBool_company(String bool_company) {
		this.bool_company = bool_company;
	}
	public String getIamge() {
		return iamge;
	}
	public void setIamge(String iamge) {
		this.iamge = iamge;
	}
	public String getBojung() {
		return bojung;
	}
	public void setBojung(String bojung) {
		this.bojung = bojung;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public BangItem(String wolse, String floor, String gunmul, String address,
			String subject, String no, String floor_this, String gujo,
			String bool_company, String iamge, String bojung, String lat,
			String lng) {
		super();
		this.wolse = wolse;
		this.floor = floor;
		this.gunmul = gunmul;
		this.address = address;
		this.subject = subject;
		this.no = no;
		this.floor_this = floor_this;
		this.gujo = gujo;
		this.bool_company = bool_company;
		this.iamge = iamge;
		this.bojung = bojung;
		this.lat = lat;
		this.lng = lng;
	}
	public BangItem() {
		super();
	}
	@Override
	public String toString() {
		return "BangItem [wolse=" + wolse + ", floor=" + floor + ", gunmul="
				+ gunmul + ", address=" + address + ", subject=" + subject
				+ ", no=" + no + ", floor_this=" + floor_this + ", gujo="
				+ gujo + ", bool_company=" + bool_company + ", iamge=" + iamge
				+ ", bojung=" + bojung + ", lat=" + lat + ", lng=" + lng + "]";
	}
}
