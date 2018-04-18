package com.bonbang.app.commons.asynctask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bonbang.app.R;
import com.bonbang.app.commons.JSONParser;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class MapProgressSubwayAsyncTask extends AsyncTask<String, Void, JSONArray>{

	private ProgressDialog dialog;
	private Context mContext;
	private int status;
	private GoogleMap googleMap;
	//ProgressDialog dialog;
	
	//init
	public MapProgressSubwayAsyncTask(Context cxt, GoogleMap googleMap, ProgressDialog di, int sat) {
		mContext = cxt;
		dialog = di;
		status = sat;
		this.googleMap = googleMap;
		//dialog = new ProgressDialog(mContext);
	}
	
	//STEP 1
	@Override
	protected void onPreExecute() {
		//dialog.setTitle("Please wait");
		//dialog.show();
		if(status == 1)
		{
			
		}
		Log.e("MapProgressAsyncTask", "show");
		dialog.show();
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
	protected void onPostExecute(JSONArray subjsonArray) {
		//Log.d("map", result.toString());
		super.onPostExecute(subjsonArray);
		for(int i=0; i<subjsonArray.length(); i++)
		{
			try {
				JSONObject jsonObject = (JSONObject) subjsonArray.get(i);
				//Log.e("item", jsonObject.getDouble("lat")+" / "+ jsonObject.getDouble("lng"));
				//Log.i("test",jsonObject.isNull("count")+"");
				if(!jsonObject.isNull("no") && !jsonObject.isNull("lat") && !jsonObject.isNull("lng"))
				{
					//Subway bangCount = new BangCount(jsonObject.getInt("count"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
					/*Subway subway = new Subway(jsonObject.getString("no"), jsonObject.getString("name"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
					//Log.i("subway", subway.toString());
					list.add(subway);*/
					
					/*View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.subway_marker, null);
					TextView numTxt = (TextView) marker.findViewById(R.id.count_marker_subway_text);
					numTxt.setText(String.valueOf(jsonObject.getString("name")));*/
					LatLng bangLoc = new LatLng( jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));   
					Marker subMakr = googleMap.addMarker(new MarkerOptions()
					.position(bangLoc)
					.title("지하철역")
					.snippet(jsonObject.getString("name"))
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_subway)));
					//.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MapActivity.this , marker))));
					//mHashMap.put(subMakr, jsonObject.getString("no"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ck","ck11");
				e.printStackTrace();
			}
		}
		if(status == 2)
		{
			/*try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
			
			
		}
		Log.e("MapProgressAsyncTask", "dismiss");
		dialog.dismiss();
	}
}
