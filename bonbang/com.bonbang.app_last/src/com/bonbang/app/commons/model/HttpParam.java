package com.bonbang.app.commons.model;

public class HttpParam {
	public String key;
	public String value;
	
	public HttpParam() {
		super();
	}
	
	
	public HttpParam(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}


	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "HttpParam [key=" + key + ", value=" + value + "]";
	}
	
}
