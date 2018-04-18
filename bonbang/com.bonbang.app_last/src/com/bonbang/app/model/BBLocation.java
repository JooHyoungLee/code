package com.bonbang.app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BBLocation implements Parcelable {
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

	public BBLocation(Parcel in) {
		// TODO Auto-generated constructor stub
		readFromParcel(in);
	}

	private void readFromParcel(Parcel in) {
		// TODO Auto-generated method stub
		namseo_lng = in.readDouble();
		namseo_lat = in.readDouble();
		bukdong_lng = in.readDouble();
		bukdong_lat = in.readDouble();
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		// TODO Auto-generated method stub
		dest.writeDouble(namseo_lng);
		dest.writeDouble(namseo_lat);
		dest.writeDouble(bukdong_lng);
		dest.writeDouble(bukdong_lat);
	}
	
	 public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public BBLocation createFromParcel(Parcel in) {
              return new BBLocation(in);
        }

        public BBLocation[] newArray(int size) {
             return new BBLocation[size];
        }
    };

}
