package com.bonbang.app.commons.utils;

import java.net.URL;

import android.content.Context;
import android.widget.Toast;

public class Utils {
	
	public static String encodeASE(String text)
	{
		String result = "";
		try {
			result = AES256Cipher.AES_Encode(text);
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}
		return result;
	}
	
	public static String decodeASE(String text)
	{
		String result = "";
		try {
			result = AES256Cipher.AES_Decode(text);
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}
		return result;
	}
	
	
	public static String getFileName(URL extUrl) {
		String filename = "";
		String path = extUrl.getPath();
		//Checks for both forward and/or backslash 
		//NOTE:**While backslashes are not supported in URL's 
		//most browsers will autoreplace them with forward slashes
		//So technically if you're parsing an html page you could run into 
		//a backslash , so i'm accounting for them here;
		String[] pathContents = path.split("[\\\\/]");
		if(pathContents != null){
			int pathContentsLength = pathContents.length;
			System.out.println("Path Contents Length: " + pathContentsLength);
			for (int i = 0; i < pathContents.length; i++) {
				System.out.println("Path " + i + ": " + pathContents[i]);
			}
			//lastPart: s659629384_752969_4472.jpg
			String lastPart = pathContents[pathContentsLength-1];
			String[] lastPartContents = lastPart.split("\\.");
			if(lastPartContents != null && lastPartContents.length > 1){
				int lastPartContentLength = lastPartContents.length;
				System.out.println("Last Part Length: " + lastPartContentLength);
				//filenames can contain . , so we assume everything before
				//the last . is the name, everything after the last . is the 
				//extension
				String name = "";
				for (int i = 0; i < lastPartContentLength; i++) {
					System.out.println("Last Part " + i + ": "+ lastPartContents[i]);
					if(i < (lastPartContents.length -1)){
						name += lastPartContents[i] ;
						if(i < (lastPartContentLength -2)){
							name += ".";
						}
					}
				}
				String extension = lastPartContents[lastPartContentLength -1];
				filename = name + "." +extension;
				System.out.println("Name: " + name);
				System.out.println("Extension: " + extension);
				System.out.println("Filename: " + filename);
			}
		}
		return filename;
	}


	public static float dpToPx(Context context, float dp) {
		if (context == null) {
			return -1;
		}
		return dp * context.getResources().getDisplayMetrics().density;
	}

	public static float pxToDp(Context context, float px) {
		if (context == null) {
			return -1;
		}
		return px / context.getResources().getDisplayMetrics().density;
	}
	
	public static void showToast(Context context, String text)
	{
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
