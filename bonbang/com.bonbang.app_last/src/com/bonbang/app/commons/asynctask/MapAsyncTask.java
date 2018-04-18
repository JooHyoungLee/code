package com.bonbang.app.commons.asynctask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bonbang.app.commons.JSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class MapAsyncTask extends AsyncTask<String, Void, JSONArray>{

	private Context mContext;
	//ProgressDialog dialog;
	
	//init
	public MapAsyncTask(Context cxt) {
		mContext = cxt;
		//dialog = new ProgressDialog(mContext);
	}
	
	//STEP 1
	@Override
	protected void onPreExecute() {
		//dialog.setTitle("Please wait");
		//dialog.show();
		super.onPreExecute();
	}
	
	//STEP 2
	@Override
	protected JSONArray doInBackground(String... params) {
		// TODO Auto-generated method stub
		 // Creating new JSON Parser
		JSONParser jParser = new JSONParser();
		// Getting JSON from URL
		JSONArray json = jParser.getJSONFromUrl(params[0]);
		return json;
	}
	
	//STEP 3
	@Override
	protected void onPostExecute(JSONArray result) {
		//Log.d("map", result.toString());
		super.onPostExecute(result);
	}
}
