package com.bonbang.app.activity.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.MainActivity;
import com.bonbang.app.R;
import com.bonbang.app.activity.LoginActivity;
import com.bonbang.app.commons.JSONParser;
import com.bonbang.app.commons.asynctask.MapProgressAsyncTask;
import com.bonbang.app.commons.utils.DBAdapter;
import com.bonbang.app.commons.utils.HttpUtils;
import com.bonbang.app.commons.utils.Subway;
import com.bonbang.app.commons.utils.Utils;
import com.bonbang.app.model.BBLocation;
import com.bonbang.app.model.BangCount;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends Activity implements  LocationListener, OnClickListener{

	// Google Map
	private GoogleMap googleMap;
	private LocationManager locationManager;
	private static final long MIN_TIME = 400;
	private static final float MIN_DISTANCE = 1000;

	private static final int SEND_THREAD_MARKER = 0;
	private static final int SEND_THREAD_SUBWAY = 1;
	private static final int SEND_THREAD_START = 5;
	private static final int SEND_THREAD_MARKER_END = 3;
	private static final int SEND_THREAD_SUBWAY_END = 4;
	private static final int SEND_THREAD_STOP_MESSAGE = 2;

	private LinearLayout ln_search_map;
	private LinearLayout ln_search_subway;
	private LinearLayout ln_search_area;
	private LinearLayout ln_search_text;

	private ImageView lv_search_map;
	private ImageView lv_search_subway;
	private ImageView lv_search_area;
	private ImageView lv_search_text;

	private LinearLayout lv_search_result;
	private TextView tv_search_result_text;

	private TextView tv_map_all_count;

	private int visible_count = 0;

	private Button btn_map_serach;

	private DBAdapter dbAdapter;


	//http://map.naver.com/?dlevel=12&pinType=site&pinId=13132858&x=126.7824626&y=37.4854670&enc=b64
	static final LatLng SEOUL = new LatLng( 37.4854670, 126.7824626);   
	private Button btn_map_all_list;
	BBLocation bbLocation;
	ProgressDialog progDialog;
	DisplayMetrics displayMetrics;


	private void initSearchArea()
	{
		ln_search_map = (LinearLayout)findViewById(R.id.ln_search_map);
		ln_search_subway = (LinearLayout)findViewById(R.id.ln_search_subway);
		ln_search_area = (LinearLayout)findViewById(R.id.ln_search_area);
		ln_search_text = (LinearLayout)findViewById(R.id.ln_search_text);
		ln_search_map.setOnClickListener(this);
		ln_search_subway.setOnClickListener(this);
		ln_search_area.setOnClickListener(this);
		ln_search_text.setOnClickListener(this);

		lv_search_map = (ImageView)findViewById(R.id.lv_search_map);
		lv_search_subway = (ImageView)findViewById(R.id.lv_search_subway);
		lv_search_area = (ImageView)findViewById(R.id.lv_search_area);
		lv_search_text = (ImageView)findViewById(R.id.lv_search_text);

		lv_search_map.setVisibility(View.INVISIBLE);
		ln_search_map.setBackgroundColor(Color.parseColor("#fbece1"));

		lv_search_result = (LinearLayout)findViewById(R.id.lv_search_result);
		lv_search_result.setVisibility(View.GONE);
		tv_search_result_text = (TextView)findViewById(R.id.tv_search_result_text);



		btn_map_all_list = (Button) findViewById(R.id.btn_map_all_list);
		tv_map_all_count = (TextView) findViewById(R.id.tv_map_all_count);

		btn_map_serach = (Button)findViewById(R.id.btn_map_serach);
		btn_map_serach.setOnClickListener(this);
	}

	public void searchResultOpen()
	{
		googleMap.setPadding(0, 0, 0, 150);
		lv_search_result.setVisibility(View.VISIBLE);
	}

	private HashMap<Marker, String> mHashMap = new HashMap<Marker, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		setContentView(R.layout.activity_maps);

		initActionbar("지도에서 방 찾기");


		progDialog = new ProgressDialog( MapActivity.this );
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setMessage("please wait...."); 
		progDialog.setCanceledOnTouchOutside(false); 


		dbAdapter = new DBAdapter(getApplicationContext());


		displayMetrics = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		try {
			// Loading map
			initilizeMap();
			googleMap.getUiSettings().setZoomControlsEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initSearchArea();
		//googleMap.setMyLocationEnabled(true);   


		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom( SEOUL, 15));//초기 위치...수정필요   

		//locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER        
		/*
        LatLng  northeast = googleMap.getProjection().getVisibleRegion().latLngBounds.northeast;
		LatLng  southwest = googleMap.getProjection().getVisibleRegion().latLngBounds.southwest;

		bbLocation = new BBLocation(southwest.latitude, southwest.longitude, northeast.latitude, northeast.longitude);
		 */
		/*Marker marker = googleMap.addMarker(new MarkerOptions()
        .position(SEOUL)
        .title("Melbourne")
        .snippet("Population: 4,137,400")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_count_low)));*/

		/*View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.count_marker, null);
		TextView numTxt = (TextView) marker.findViewById(R.id.count_marker_text);
		numTxt.setText("27");

		Marker customMarker = googleMap.addMarker(new MarkerOptions()
		.position(SEOUL)
		.title("Title")
		.snippet("Description")
		.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
		 */

		googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition position) {
				// TODO Auto-generated method stub

				//googleMap.g//
				
				//googleMap.m
				googleMap.clear();

				Log.i("CameraPosition", position.target.latitude +", "+position.target.longitude);
				LatLng  northeast = googleMap.getProjection().getVisibleRegion().latLngBounds.northeast;
				LatLng  southwest = googleMap.getProjection().getVisibleRegion().latLngBounds.southwest;

				bbLocation = new BBLocation(southwest.latitude, southwest.longitude, northeast.latitude, northeast.longitude);
				HttpUtils httpUtils = new HttpUtils();
				String url = httpUtils.makeRequestPram(getApplicationContext(), bbLocation , (int)googleMap.getCameraPosition().zoom);
				String subway_url = httpUtils.makeRequestSubWayPram(getApplicationContext(), bbLocation , (int)googleMap.getCameraPosition().zoom);
				String viewCount = httpUtils.makeRequestMapViewCountPram(getApplicationContext(), bbLocation, (int)googleMap.getCameraPosition().zoom);

				MarkerAsyncTask markerAsyncTask = new MarkerAsyncTask();
				markerAsyncTask.execute(url);

				SubwayAsyncTask subwayAsyncTask = new SubwayAsyncTask();
				subwayAsyncTask.execute(bbLocation);

				ViewCountAsyncTask viewCountAsyncTask = new ViewCountAsyncTask();
				viewCountAsyncTask.execute(viewCount);

			}
		});

		googleMap.setOnMapLoadedCallback(new OnMapLoadedCallback() {

			@Override
			public void onMapLoaded() {
				// TODO Auto-generated method stub

			}
		});

		googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				//marker.get
				if(marker.getTitle().equals("지하철역"))
				{
					//Toast.makeText(getApplicationContext(), "하철", Toast.LENGTH_SHORT).show();
					String id = mHashMap.get(marker);
					marker.hideInfoWindow();
					//Toast.makeText(getApplicationContext(), "하철-"+id, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(MapActivity.this, MapSubwayListActivity.class);
					intent.putExtra("no", id);
					startActivity(intent);
				}
				return false;
			}
		});



		/*
		final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
		if (mapView.getViewTreeObserver().isAlive()) {
			mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
				@SuppressLint("NewApi")
				// We check which build version we are using.
				@Override
				public void onGlobalLayout() {
					LatLngBounds bounds = new LatLngBounds.Builder().include(SEOUL).build();
					if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
						mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					} else {
						mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
					}
					googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
				}
			});
		}*/

		btn_map_all_list.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mapListIntent = new Intent(MapActivity.this, MapListActivity.class);
				mapListIntent.putExtra("bbLocation", bbLocation);
				mapListIntent.putExtra("zoom", (int)googleMap.getCameraPosition().zoom);
				startActivity(mapListIntent);
			}
		});
	}

	private void initActionbar(String title)
	{
		final ActionBar abar = getActionBar();
		//abar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));//line under the action bar
		View viewActionBar = getLayoutInflater().inflate(R.layout.actionbar_main_title, null);
		ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
				ActionBar.LayoutParams.WRAP_CONTENT, 
				ActionBar.LayoutParams.MATCH_PARENT, 
				Gravity.CENTER);
		TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
		textviewTitle.setText(title);
		abar.setCustomView(viewActionBar, params);
		abar.setDisplayShowCustomEnabled(true);
		abar.setDisplayShowTitleEnabled(false);
		abar.setDisplayHomeAsUpEnabled(true);
		abar.setIcon(android.R.color.transparent);
		abar.setHomeButtonEnabled(true);
	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}



	// Convert a view to bitmap
	public Bitmap createDrawableFromView(Context context, View view) {
		Bitmap bitmap = null;
		try {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			
			view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
			view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
			view.buildDrawingCache();
			bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

			Canvas canvas = new Canvas(bitmap);
			view.draw(canvas);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return bitmap;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

		Log.i("onLocationChanged",latLng.latitude+" : "+latLng.longitude);

		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
		googleMap.animateCamera(cameraUpdate);
		locationManager.removeUpdates(this);

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	public class MarkerAsyncTask extends AsyncTask<String, Void, JSONArray>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progDialog.show();
			super.onPreExecute();
		}

		@Override
		protected JSONArray doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			JSONParser jParser = new JSONParser();
			// Getting JSON from URL
			JSONArray jsonArray = jParser.getJSONFromUrl(arg0[0]);
			return jsonArray;
		}

		@Override
		protected void onPostExecute(JSONArray jsonArray) {
			// TODO Auto-generated method stub
			if(jsonArray != null)
			{
				for(int i=0; i<jsonArray.length(); i++)
				{
					try {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						if(!jsonObject.isNull("count") && !jsonObject.isNull("lat") && !jsonObject.isNull("lng"))
						{
							BangCount bangCount = new BangCount(jsonObject.getInt("count"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
							Log.i("bangCount", bangCount.toString());
							View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.count_marker, null);
							TextView numTxt = (TextView) marker.findViewById(R.id.count_marker_text);
							numTxt.setText(String.valueOf(jsonObject.getInt("count")));
							LatLng bangLoc = new LatLng( jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));   
							/*Marker mKer = googleMap.addMarker(new MarkerOptions()
							.position(bangLoc)
							.title("Title")
							.snippet("Description")
							.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getApplicationContext() , marker))));*/
							MarkerOptions markerOptions = new MarkerOptions()
							.position(bangLoc)
							.title("Title")
							.snippet("Description")
							.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getApplicationContext() , marker)));
							googleMap.addMarker(markerOptions);
							//visible_count += jsonObject.getInt("count");
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						Log.e("ck","ck22");
						e.printStackTrace();
					}
				}
			}
			//progDialog.dismiss();
			super.onPostExecute(jsonArray);
		}
	}

	public class SubwayAsyncTask extends AsyncTask<BBLocation, Void, ArrayList<Subway>>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progDialog.show();
			super.onPreExecute();
		}

		@Override
		protected ArrayList<Subway> doInBackground(BBLocation... arg0) {
			// TODO Auto-generated method stub
			ArrayList<Subway> list = new ArrayList<Subway>();
			dbAdapter.open();
			dbAdapter.viewTables();
			list = dbAdapter.getSubway(bbLocation);
			return list;
		}

		@Override
		protected void onPostExecute(ArrayList<Subway> list) {
			// TODO Auto-generated method stub
			
			if(list.size()>0)
			{
				for(int i=0; i<list.size(); i++)
				{
					
					LatLng bangLoc = new LatLng(Double.parseDouble(list.get(i).getLocation_x()) , Double.parseDouble(list.get(i).getLocation_y()));   
					Log.e("subway",list.get(i).getName()+" "+bangLoc.toString());
					View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.subway_marker, null);
					TextView numTxt = (TextView) marker.findViewById(R.id.count_marker_subway_text);
					numTxt.setText(list.get(i).getName());
					/*Marker mKer = googleMap.addMarker(new MarkerOptions()
					.position(bangLoc)
					.title("Title")
					.snippet("Description")
					.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getApplicationContext() , marker))));*/
					MarkerOptions markerOptions = new MarkerOptions()
					.position(bangLoc)
					.title(list.get(i).getName())
					.snippet("결과보기")
					.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getApplicationContext() , marker)));
					googleMap.addMarker(markerOptions);
					/*Marker subMakr = googleMap.addMarker(new MarkerOptions()
					.position(bangLoc)
					.title("지하철역")
					.snippet(list.get(i).getName())
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_subway)));
					mHashMap.put(subMakr, String.valueOf(list.get(i).getNo()));*/
				}
				progDialog.dismiss();
			}
			if(progDialog.isShowing())
			{
				progDialog.dismiss();
			}
			dbAdapter.close();
			super.onPostExecute(list);
		}
	}


	public class ViewCountAsyncTask extends AsyncTask<String, Void, JSONArray>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progDialog.show();
			super.onPreExecute();
		}

		@Override
		protected JSONArray doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			JSONParser jParser = new JSONParser();
			// Getting JSON from URL
			JSONArray jsonArray = jParser.getJSONFromUrl(arg0[0]);
			Log.e("ViewCountAsyncTask", jsonArray.toString());
			return jsonArray;
		}

		@Override
		protected void onPostExecute(JSONArray subjsonArray) {
			// TODO Auto-generated method stub
			if(subjsonArray != null)
			{
				int count = 0;
				for(int i=0; i<subjsonArray.length(); i++)
				{
					try {
						JSONObject jsonObject = (JSONObject) subjsonArray.get(i);
						//Log.e("item", jsonObject.getDouble("lat")+" / "+ jsonObject.getDouble("lng"));
						//Log.i("test",jsonObject.isNull("count")+"");
						if(!jsonObject.isNull("count"))
						{
							count += Integer.parseInt(jsonObject.getString("count"));
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						progDialog.dismiss();
					}
				}

				if(count > 0)
				{
					tv_map_all_count.setText("검색결과: "+count);
					googleMap.setPadding(0, 0, 0, 0);
					lv_search_result.setVisibility(View.GONE);
				}
				else
				{
					tv_map_all_count.setText("검색결과: ");
					searchResultOpen();
				}

			}
			else
			{
				tv_map_all_count.setText("검색결과: ");
				searchResultOpen();
			}
			super.onPostExecute(subjsonArray);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ln_search_map:
			Utils.showToast(getApplicationContext(), "지도");
			break;

		case R.id.ln_search_subway:
			Utils.showToast(getApplicationContext(), "지하철");
			break;

		case R.id.ln_search_area:
			Utils.showToast(getApplicationContext(), "지역");
			break;

		case R.id.ln_search_text:
			Utils.showToast(getApplicationContext(), "조건");
			break;

		case R.id.btn_map_serach:
			Intent search = new Intent(MapActivity.this, MapSearchActivity.class);
			startActivity(search);
			break;


		default:
			break;
		}

	}

}
