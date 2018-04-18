package com.bonbang.app.model;

public class MapListItem {
	public String no;
	public String image;
	public String address;
	public String floor;
	public String floor_this;
	public String gujo;
	public String gunmul;
	public String subject;
	public String bojung;
	public String wolse;
	public String type;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
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
	public String getGunmul() {
		return gunmul;
	}
	public void setGunmul(String gunmul) {
		this.gunmul = gunmul;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBojung() {
		return bojung;
	}
	public void setBojung(String bojung) {
		this.bojung = bojung;
	}
	public String getWolse() {
		return wolse;
	}
	public void setWolse(String wolse) {
		this.wolse = wolse;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MapListItem(String no, String image, String address, String floor,
			String floor_this, String gujo, String gunmul, String subject,
			String bojung, String wolse) {
		super();
		this.no = no;
		this.image = image;
		this.address = address;
		this.floor = floor;
		this.floor_this = floor_this;
		this.gujo = gujo;
		this.gunmul = gunmul;
		this.subject = subject;
		this.bojung = bojung;
		this.wolse = wolse;
	}
	public MapListItem() {
		super();
	}
	@Override
	public String toString() {
		return "MapListItem [no=" + no + ", image=" + image + ", address="
				+ address + ", floor=" + floor + ", floor_this=" + floor_this
				+ ", gujo=" + gujo + ", gunmul=" + gunmul + ", subject="
				+ subject + ", bojung=" + bojung + ", wolse=" + wolse + "]";
	}
	
	
	
}
