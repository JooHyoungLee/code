package com.bonbang.app.model;

import java.util.ArrayList;

public class BangDetailItem {
	public String uid; 
	public String kind; 
	public String charge; 
	public String office_name; 
	public String office_hphone; 
	public String office_address;
	public String ceo; 
	public String phone;
	public String office_phone;
	public String address; 
	public String lat;
	public String lng; 
	public String option;
	public String gwanri_ext; 
	public String gwanri;
	public String bojung; 
	public String gujo; 
	public String wolse; 
	public String gunmul; 
	public String floor; 
	public String floor_this; 
	public String parking; 
	public String size1; 
	public String size2; 
	public String elevator; 
	public String ipju_date; 
	public String write_date; 
	public String modify_date; 
	public String view; 
	public ArrayList<String> image;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public String getOffice_hphone() {
		return office_hphone;
	}
	public void setOffice_hphone(String office_hphone) {
		this.office_hphone = office_hphone;
	}
	public String getOffice_address() {
		return office_address;
	}
	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getGwanri_ext() {
		return gwanri_ext;
	}
	public void setGwanri_ext(String gwanri_ext) {
		this.gwanri_ext = gwanri_ext;
	}
	public String getGwanri() {
		return gwanri;
	}
	public void setGwanri(String gwanri) {
		this.gwanri = gwanri;
	}
	public String getBojung() {
		return bojung;
	}
	public void setBojung(String bojung) {
		this.bojung = bojung;
	}
	public String getGujo() {
		return gujo;
	}
	public void setGujo(String gujo) {
		this.gujo = gujo;
	}
	public String getWolse() {
		return wolse;
	}
	public void setWolse(String wolse) {
		this.wolse = wolse;
	}
	public String getGunmul() {
		return gunmul;
	}
	public void setGunmul(String gunmul) {
		this.gunmul = gunmul;
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
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getSize1() {
		return size1;
	}
	public void setSize1(String size1) {
		this.size1 = size1;
	}
	public String getSize2() {
		return size2;
	}
	public void setSize2(String size2) {
		this.size2 = size2;
	}
	public String getElevator() {
		return elevator;
	}
	public void setElevator(String elevator) {
		this.elevator = elevator;
	}
	public String getIpju_date() {
		return ipju_date;
	}
	public void setIpju_date(String ipju_date) {
		this.ipju_date = ipju_date;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public ArrayList<String> getImage() {
		return image;
	}
	public void setImage(ArrayList<String> image) {
		this.image = image;
	}
	public BangDetailItem() {
		super();
	}
	@Override
	public String toString() {
		return "BangDetailItem [uid=" + uid + ", kind=" + kind + ", charge="
				+ charge + ", office_name=" + office_name + ", office_hphone="
				+ office_hphone + ", office_address=" + office_address
				+ ", ceo=" + ceo + ", phone=" + phone + ", office_phone="
				+ office_phone + ", address=" + address + ", lat=" + lat
				+ ", lng=" + lng + ", option=" + option + ", gwanri_ext="
				+ gwanri_ext + ", gwanri=" + gwanri + ", bojung=" + bojung
				+ ", gujo=" + gujo + ", wolse=" + wolse + ", gunmul=" + gunmul
				+ ", floor=" + floor + ", floor_this=" + floor_this
				+ ", parking=" + parking + ", size1=" + size1 + ", size2="
				+ size2 + ", elevator=" + elevator + ", ipju_date=" + ipju_date
				+ ", write_date=" + write_date + ", modify_date=" + modify_date
				+ ", view=" + view + ", image=" + image + "]";
	}
	
}
