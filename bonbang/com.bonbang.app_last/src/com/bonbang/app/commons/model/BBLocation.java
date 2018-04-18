package com.bonbang.app.commons.model;

public class BBLocation {
	public double namseo_lng;
	public double namseo_lat;
	public double bukdong_lng;
	public double bukdong_lat;
	
	public BBLocation(double latitude, double longitude, double latitude2,
			double longitude2) {
		super();
		this.namseo_lng = longitude;
		this.namseo_lat = latitude;
		this.bukdong_lng = longitude2;
		this.bukdong_lat = latitude2;
	}

	public double getNamseo_lng() {
		return namseo_lng;
	}

	public void setNamseo_lng(double namseo_lng) {
		this.namseo_lng = namseo_lng;
	}

	public double getNamseo_lat() {
		return namseo_lat;
	}

	public void setNamseo_lat(double namseo_lat) {
		this.namseo_lat = namseo_lat;
	}

	public double getBukdong_lng() {
		return bukdong_lng;
	}

	public void setBukdong_lng(double bukdong_lng) {
		this.bukdong_lng = bukdong_lng;
	}

	public double getBukdong_lat() {
		return bukdong_lat;
	}

	public void setBukdong_lat(double bukdong_lat) {
		this.bukdong_lat = bukdong_lat;
	}

	@Override
	public String toString() {
		return "BBLocation [namseo_lng=" + namseo_lng + ", namseo_lat="
				+ namseo_lat + ", bukdong_lng=" + bukdong_lng
				+ ", bukdong_lat=" + bukdong_lat + "]";
	}

}
