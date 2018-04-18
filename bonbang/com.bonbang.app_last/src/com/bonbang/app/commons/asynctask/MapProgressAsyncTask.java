package com.bonbang.app.commons.asynctask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bonbang.app.R;
import com.bonbang.app.activity.maps.MapActivity;
import com.bonbang.app.commons.JSONParser;
import com.bonbang.app.model.BangCount;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class MapProgressAsyncTask extends AsyncTask<String, Void, JSONArray>{

	private ProgressDialog dialog;
	private Context mContext;
	private int status;
	private GoogleMap googleMap;
	//ProgressDialog dialog;
	
	//init
	public MapProgressAsyncTask(Context cxt, GoogleMap googleMap, ProgressDialog di, int sat) {
		mContext = cxt;
		//dialog = di;
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
			dialog = new ProgressDialog(mContext);
			dialog.setTitle("Load");
			dialog.setCancelable(false);
			Log.e("MapProgressAsyncTask", "show");
			dialog.show();
		}
		super.onPreExecute();
	}
	
	//STEP 2
	@Override
	protected JSONArray doInBackground(String... params) {
		// TODO Auto-generated method stub
		 // Creating new JSON Parser
		JSONParser jParser = new JSONParser();
		// Getting JSON from URL
		JSONArray jsonArray = jParser.getJSONFromUrl(params[0]);
		
		return jsonArray;
	}
	
	//STEP 3
	@Override
	protected void onPostExecute(JSONArray jsonArray) {
		//Log.d("map", result.toString());
		super.onPostExecute(jsonArray);
		for(int i=0; i<jsonArray.length(); i++)
		{
			try {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				if(!jsonObject.isNull("count") && !jsonObject.isNull("lat") && !jsonObject.isNull("lng"))
				{
					BangCount bangCount = new BangCount(jsonObject.getInt("count"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
					Log.i("bangCount", bangCount.toString());
					View marker = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.count_marker, null);
					TextView numTxt = (TextView) marker.findViewById(R.id.count_marker_text);
					numTxt.setText(String.valueOf(jsonObject.getInt("count")));
					LatLng bangLoc = new LatLng( jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));   
					Marker mKer = googleMap.addMarker(new MarkerOptions()
					.position(bangLoc)
					.title("Title")
					.snippet("Description"));
					//icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(mContext , marker))));
					//visible_count += jsonObject.getInt("count");
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ck","ck22");
				e.printStackTrace();
			}
		}
		if(status == 1)
		{
			/*try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
			
			Log.e("MapProgressAsyncTask", "dismiss");
			dialog.dismiss();
		}
	}
 	public static Bitmap createDrawableFromView(Context context, View view) {
 		DisplayMetrics displayMetrics = new DisplayMetrics();
 		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
 		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
 		view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
 		view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
 		view.buildDrawingCache();
 		Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
  
 		Canvas canvas = new Canvas(bitmap);
 		view.draw(canvas);
 		return bitmap;
 	}
}
