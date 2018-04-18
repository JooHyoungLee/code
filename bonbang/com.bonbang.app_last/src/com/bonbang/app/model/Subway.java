package com.bonbang.app.model;

public class Subway {
	 /*
    [
   {
       "no": "98",
       "name": "가산디지털단지역",
       "lat": "37.4815593",
       "lng": "126.882654"
   },
   {
       "no": "440",
       "name": "가양역",
       "lat": "37.5615634",
       "lng": "126.8542615"
   },
   ]
    */
	public String no;
	public String name;
	public double lat;
	public double lng;
	
	public Subway(String no, String name, double lat, double lng) {
		super();
		this.no = no;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Subway [no=" + no + ", name=" + name + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}
	
	
}
