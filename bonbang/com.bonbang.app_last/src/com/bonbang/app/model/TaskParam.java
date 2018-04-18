package com.bonbang.app.model;

import android.content.Context;

public class TaskParam {
	public Context context;
	public String url;
	
	public TaskParam() {
		super();
	}

	public TaskParam(Context context, String url) {
		super();
		this.context = context;
		this.url = url;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
