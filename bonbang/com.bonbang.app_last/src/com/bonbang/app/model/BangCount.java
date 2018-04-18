package com.bonbang.app.model;

public class BangCount {
	public int count;
	public double lat;
	public double lon;
	
	
	
	public BangCount(int count, double lat, double lon) {
		super();
		this.count = count;
		this.lat = lat;
		this.lon = lon;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	@Override
	public String toString() {
		return "BangCount [count=" + count + ", lat=" + lat + ", lon=" + lon
				+ "]";
	}
	
	
}
