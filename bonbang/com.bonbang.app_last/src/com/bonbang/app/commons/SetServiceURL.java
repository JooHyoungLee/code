package com.bonbang.app.commons;

import com.bonbang.app.R;

import android.content.Context;

public class SetServiceURL {
	private Context mContext;
	private String name;
	
	
	public SetServiceURL(Context context, String serviceName)
	{
		mContext = context;
		name = serviceName;
	}
	
	public String getURL()
	{
		String url = "";
		if(name.equals("login"))
		{
			url = mContext.getResources().getString(R.string.login);
		}
		
		return url;
	}
}
