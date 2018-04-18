package com.bonbang.app;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bonbang.app.activity.JoinActivity;
import com.bonbang.app.activity.JoinTempActivity;
import com.bonbang.app.activity.LoginActivity;
import com.bonbang.app.activity.LoginAuthActivity;
import com.bonbang.app.activity.PhoneActivity;
import com.bonbang.app.activity.maps.MapActivity;
import com.bonbang.app.commons.asynctask.HttpGetAsyncTask;
import com.bonbang.app.commons.utils.*;

public class MainActivity extends Activity implements OnClickListener, OnGestureListener {


	private static final int SWIPE_MIN_DISTANCE = 120;  //120
	private static final int SWIPE_MAX_OFF_PATH = 250;  //250
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;  //200
	private GestureDetector gestureScanner;

	private Button btn_all_bang;
	private Button btn_all_subway;
	private Button btn_all_area;
	private Button btn_all_find;


	private Button btn_find_station_1;
	private Button btn_find_station_2;
	private Button btn_find_station_3;

	private Button btn_find_shop_1;
	private Button btn_find_shop_2;
	private Button btn_find_shop_3;

	private TextView tv_find_shop_1_station;
	private TextView tv_find_shop_2_station;
	private TextView tv_find_shop_3_station;


	private ImageView iv_main_bang_1;
	private ImageView iv_main_bang_2;
	private ImageView iv_main_bang_3;
	private ImageView iv_main_bang_4;
	private ImageView iv_main_bang_5;
	private ImageView iv_main_bang_6;
	private ImageView iv_main_bang_7;
	private ImageView iv_main_bang_8;
	private ImageView iv_main_bang_9;

	private TextView tv_main_bang_1;
	private TextView tv_main_bang_2;
	private TextView tv_main_bang_3;
	private TextView tv_main_bang_4;
	private TextView tv_main_bang_5;
	private TextView tv_main_bang_6;
	private TextView tv_main_bang_7;
	private TextView tv_main_bang_8;
	private TextView tv_main_bang_9;

	private LinearLayout ln_main_bang_1;
	private LinearLayout ln_main_bang_2;
	private LinearLayout ln_main_bang_3;
	private LinearLayout ln_main_bang_4;
	private LinearLayout ln_main_bang_5;
	private LinearLayout ln_main_bang_6;
	private LinearLayout ln_main_bang_7;
	private LinearLayout ln_main_bang_8;
	private LinearLayout ln_main_bang_9;



	private ImageView main_banner;

	private GpsInfo gps;
	private Location nowLoc;

	private ViewFlipper vf_main_bang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
		initGPS();
		initMainBanner();
	}

	private void init()
	{
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);

		/*
		final ActionBar abar = getActionBar();
		//abar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));//line under the action bar
		View viewActionBar = getLayoutInflater().inflate(R.layout.actionbar_title, null);
		ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
		        ActionBar.LayoutParams.WRAP_CONTENT, 
		        ActionBar.LayoutParams.MATCH_PARENT, 
		        Gravity.LEFT);
		ImageView logo = (ImageView) viewActionBar.findViewById(R.id.bar_logo);
		abar.setCustomView(viewActionBar, params);
		abar.setDisplayShowCustomEnabled(true);
		abar.setDisplayShowTitleEnabled(false);
		abar.setDisplayHomeAsUpEnabled(false);
		abar.setDisplayShowTitleEnabled(false);
		abar.setIcon(android.R.color.transparent);
		abar.setHomeButtonEnabled(true);
		 */




		btn_all_bang = (Button) findViewById(R.id.btn_all_bang);
		btn_all_subway = (Button) findViewById(R.id.btn_all_subway);
		btn_all_area = (Button) findViewById(R.id.btn_all_area);
		btn_all_find = (Button) findViewById(R.id.btn_all_find);

		btn_find_shop_1 = (Button) findViewById(R.id.btn_find_shop_1);
		btn_find_shop_2 = (Button) findViewById(R.id.btn_find_shop_2);
		btn_find_shop_3 = (Button) findViewById(R.id.btn_find_shop_3);
		btn_find_station_1 = (Button) findViewById(R.id.btn_find_staion_1);
		btn_find_station_2 = (Button) findViewById(R.id.btn_find_staion_2);
		btn_find_station_3 = (Button) findViewById(R.id.btn_find_staion_3);
		tv_find_shop_1_station = (TextView) findViewById(R.id.tv_find_shop_1_station);
		tv_find_shop_2_station = (TextView) findViewById(R.id.tv_find_shop_2_station);
		tv_find_shop_3_station = (TextView) findViewById(R.id.tv_find_shop_3_station);

		iv_main_bang_1 = (ImageView) findViewById(R.id.iv_main_bang_1);
		iv_main_bang_2 = (ImageView) findViewById(R.id.iv_main_bang_2);
		iv_main_bang_3 = (ImageView) findViewById(R.id.iv_main_bang_3);
		iv_main_bang_4 = (ImageView) findViewById(R.id.iv_main_bang_4);
		iv_main_bang_5 = (ImageView) findViewById(R.id.iv_main_bang_5);
		iv_main_bang_6 = (ImageView) findViewById(R.id.iv_main_bang_6);
		iv_main_bang_7 = (ImageView) findViewById(R.id.iv_main_bang_7);
		iv_main_bang_8 = (ImageView) findViewById(R.id.iv_main_bang_8);
		iv_main_bang_9 = (ImageView) findViewById(R.id.iv_main_bang_9);

		tv_main_bang_1 = (TextView)findViewById(R.id.tv_main_bang_1);
		tv_main_bang_2 = (TextView)findViewById(R.id.tv_main_bang_2);
		tv_main_bang_3 = (TextView)findViewById(R.id.tv_main_bang_3);
		tv_main_bang_4 = (TextView)findViewById(R.id.tv_main_bang_4);
		tv_main_bang_5 = (TextView)findViewById(R.id.tv_main_bang_5);
		tv_main_bang_6 = (TextView)findViewById(R.id.tv_main_bang_6);
		tv_main_bang_7 = (TextView)findViewById(R.id.tv_main_bang_7);
		tv_main_bang_8 = (TextView)findViewById(R.id.tv_main_bang_8);
		tv_main_bang_9 = (TextView)findViewById(R.id.tv_main_bang_9);

		ln_main_bang_9 = (LinearLayout)findViewById(R.id.ln_main_bang_9);
		ln_main_bang_9.setVisibility(View.INVISIBLE);

		main_banner = (ImageView) findViewById(R.id.main_banner);
		vf_main_bang = (ViewFlipper) findViewById(R.id.vf_main_bang);

		btn_all_bang.setOnClickListener(this);
		btn_all_subway.setOnClickListener(this);
		btn_all_area.setOnClickListener(this);
		btn_all_find.setOnClickListener(this);



		btn_find_shop_1.setOnClickListener(this);
		btn_find_shop_2.setOnClickListener(this);
		btn_find_shop_3.setOnClickListener(this);
		btn_find_station_1.setOnClickListener(this);
		btn_find_station_2.setOnClickListener(this);
		btn_find_station_3.setOnClickListener(this);

		gestureScanner = new GestureDetector(this);

	}
	public void initGPS()
	{
		gps = new GpsInfo(MainActivity.this);
		// GPS 사용유무 가져오기
		if (gps.isGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			nowLoc = gps.getLocation();
			if(nowLoc != null)
			{
				nowLoc.setLatitude(37.4894690);
				nowLoc.setLongitude(126.7245994);
				Toast.makeText(
						getApplicationContext(),
						"당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,
						Toast.LENGTH_LONG).show();


				getSubwayStationList();
				getSubwayList("46");
				getFavBangList();

			}
			else
			{
				gps.showSettingsAlert();
				nowLoc = null;
			}
		} else {
			// GPS 를 사용할수 없으므로
			gps.showSettingsAlert();
			nowLoc = null;
		}
	}

	public void initMainBanner()
	{
		main_banner.setAdjustViewBounds(true);
	}

	/**
	 * 주변공인중개사
	 */
	public void getSubwayList(String no)
	{
		Log.d("getSubwayList", "init");
		//[{"uid":"ojh7752@naver.com","name":"\ud5c8\ube0c\uacf5\uc778\uc911\uac1c\uc0ac","subway":"\ubd80\ud3c9\uc5ed(\uac00\ud1a8\ub9ad\ub300\uc778\ucc9c\uc131\ubaa8\ubcd1\uc6d0)"},
		//{"uid":"k24449@korea.com","name":"\ud604\ub300\uc2dc\ud2f0\uacf5\uc778\uc911\uac1c\uc0ac","subway":"\ubd80\ud3c9\uc5ed(\uac00\ud1a8\ub9ad\ub300\uc778\ucc9c\uc131\ubaa8\ubcd1\uc6d0)"},
		//{"uid":"pepcon@hanmail.net","name":"\ub2e4\uc74c\uacf5\uc778\uc911\uac1c\uc0ac","subway":"\ubd80\ud3c9\uc5ed(\uac00\ud1a8\ub9ad\ub300\uc778\ucc9c\uc131\ubaa8\ubcd1\uc6d0)"}]
		String params = HttpUtils.makeRequestMainOfficeListPram(getApplicationContext(),  no);
		HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask(getApplicationContext());
		httpGetAsyncTask.execute(params);

		try {
			JSONArray jsonArray = httpGetAsyncTask.get();

			//Log.d("getSubwayList",jsonArray.toString());

			if(jsonArray == null)
			{
				return;
			}


			for(int i=0; i<jsonArray.length(); i++)
			{
				JSONObject item = (JSONObject) jsonArray.get(i);
				if(i == 0)
				{
					//btn_find_station_1
					if(!item.isNull("uid") && !item.isNull("name") && !item.isNull("subway"))
					{
						btn_find_shop_1.setVisibility(View.VISIBLE);
						tv_find_shop_1_station.setVisibility(View.VISIBLE);

						btn_find_shop_1.setText(item.getString("name"));
						tv_find_shop_1_station.setText(item.getString("subway"));

						btn_find_shop_2.setVisibility(View.INVISIBLE);
						tv_find_shop_2_station.setVisibility(View.INVISIBLE);

						btn_find_shop_3.setVisibility(View.INVISIBLE);
						tv_find_shop_3_station.setVisibility(View.INVISIBLE);
					}
				}
				else if(i == 1)
				{
					if(!item.isNull("uid") && !item.isNull("name") && !item.isNull("subway"))
					{
						btn_find_shop_2.setVisibility(View.VISIBLE);
						tv_find_shop_2_station.setVisibility(View.VISIBLE);

						btn_find_shop_2.setText(item.getString("name"));
						tv_find_shop_2_station.setText(item.getString("subway"));

						btn_find_shop_3.setVisibility(View.INVISIBLE);
						tv_find_shop_3_station.setVisibility(View.INVISIBLE);
					}
				}
				else if(i == 2)
				{
					if(!item.isNull("uid") && !item.isNull("name")&& !item.isNull("subway"))
					{
						btn_find_shop_3.setVisibility(View.VISIBLE);
						tv_find_shop_3_station.setVisibility(View.VISIBLE);

						btn_find_shop_3.setText(item.getString("name"));
						tv_find_shop_3_station.setText(item.getString("subway"));
					}
				}
				else
				{
					break;
				}
			}
			Log.d("getSubwayList",jsonArray.length()+"");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}

	public void getFavBangList()
	{
		/**
		 * {
		        "room_no": "96",
		        "room_image": "http://bonbangapp.com/PEG/o_19mp2b1ogppo1r2r14n91hm6heqa.JPG",
		        "room_address": "인천광역시 부평구"
		    },
		 */
		if(nowLoc != null)
		{
			String params = HttpUtils.makeRequestMainFavBangListPram(getApplicationContext(), nowLoc);
			HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask(getApplicationContext());
			httpGetAsyncTask.execute(params);

			try {
				JSONArray jsonArray = httpGetAsyncTask.get();

				for(int i=0; i<jsonArray.length(); i++)
				{
					JSONObject item = (JSONObject) jsonArray.get(i);
					if(!item.isNull("room_no") && !item.isNull("room_address"))
					{
						//room_image
						switch (i) {
						case 1:
							tv_main_bang_1.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_1).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_1.setImageResource(R.drawable.placeholder);
							}
							break;
						case 2:
							tv_main_bang_2.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_2).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_2.setImageResource(R.drawable.placeholder);
							}
							break;
						case 3:
							tv_main_bang_3.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_3).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_3.setImageResource(R.drawable.placeholder);
							}
							break;
						case 4:
							tv_main_bang_4.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_4).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_4.setImageResource(R.drawable.placeholder);
							}
							break;
						case 5:
							tv_main_bang_5.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_5).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_5.setImageResource(R.drawable.placeholder);
							}
							break;
						case 6:
							tv_main_bang_6.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_6).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_6.setImageResource(R.drawable.placeholder);
							}
							break;

						case 7:
							tv_main_bang_7.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_7).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_7.setImageResource(R.drawable.placeholder);
							}
							break;
						case 8:
							tv_main_bang_8.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_8).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_8.setImageResource(R.drawable.placeholder);
							}
							break;
						case 9:
							tv_main_bang_9.setText(item.getString("room_address"));
							if(!item.isNull("room_image"))
							{
								new ImageDownloaderTask(iv_main_bang_9).execute(item.getString("room_image"));
							}
							else
							{
								iv_main_bang_9.setImageResource(R.drawable.placeholder);
							}
							break;

						default:
							break;
						}

					}
				}

				Log.d("getFavBangList",jsonArray.length()+"");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void getSubwayStationList()
	{
		Log.d("getSubwayStationList", "init");
		//[{"no":"45","name":"\ubd80\ud3c9\uc5ed(\uac00\ud1a8\ub9ad\ub300\uc778\ucc9c\uc131\ubaa8\ubcd1\uc6d0)"},
		//{"no":"578","name":"\ubd80\ud3c9\uc5ed"},
		//{"no":"577","name":"\ub3d9\uc218\uc5ed"}]
		if(nowLoc != null)
		{
			String params = HttpUtils.makeRequestMainSubwayListPram(getApplicationContext(), nowLoc);
			HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask(getApplicationContext());
			httpGetAsyncTask.execute(params);

			try {
				JSONArray jsonArray = httpGetAsyncTask.get();

				for(int i=0; i<jsonArray.length(); i++)
				{
					JSONObject item = (JSONObject) jsonArray.get(i);
					if(i == 0)
					{
						//btn_find_station_1
						if(!item.isNull("no") && !item.isNull("name"))
						{
							if(item.getString("name").length() >8)
							{
								btn_find_station_1.setText(item.getString("name").substring(0, 7)+"..");
							}
							else
							{
								btn_find_station_1.setText(item.getString("name").replaceAll("\\(", "\n("));
							}
						}
					}
					else if(i == 1)
					{
						if(!item.isNull("no") && !item.isNull("name"))
						{
							btn_find_station_2.setText(item.getString("name"));
						}
					}
					else if(i == 2)
					{
						if(!item.isNull("no") && !item.isNull("name"))
						{
							btn_find_station_3.setText(item.getString("name"));
						}
					}
					else
					{
						break;
					}
				}

				Log.d("getSubwayStationList",jsonArray.length()+"");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	/*
	public void getSubwayOfficeList()
	{
		int no = 45;
		if(nowLoc != null)
		{
			String params = HttpUtils.makeRequestMainOfficeListPram(getApplicationContext(), no);
			HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask(getApplicationContext());
			httpGetAsyncTask.execute(params);

			try {
				JSONArray jsonArray = httpGetAsyncTask.get();

				Log.d("getSubwayOfficeList",jsonArray.toString());

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	 */


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*int id = item.getItemId();
		if (id == R.id.action_event) {
			return true;
		}*/
		SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        String email = pref.getString("email", "");
        String auth = pref.getString("auth", "");
        
		switch (item.getItemId()) {
		case R.id.action_fav:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_join:
			Intent login_intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(login_intent);
			
			break;
		case R.id.action_rbang:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_recent:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_reg:
			//Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
	        	
	        if((email.equals("") && auth.equals("")) || (!email.equals("") && auth.equals("yes")))
	        {
	        	Toast.makeText(getApplicationContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
	        	Intent login_intent_reg = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(login_intent_reg);
	        }
	        else if(!email.equals("") && auth.equals("no"))
	        {
	        	Toast.makeText(getApplicationContext(), "정회원가입페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
	        	Intent reg_intent = new Intent(MainActivity.this, PhoneActivity.class);
				startActivity(reg_intent);
	        }
	        else 
	        {
	        	Toast.makeText(getApplicationContext(), "회원가입페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
	        	Intent reg_intent = new Intent(MainActivity.this, JoinTempActivity.class);
				startActivity(reg_intent);
	        }
			break;
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_all_bang:
			Intent all_bang_intent = new Intent(MainActivity.this, MapActivity.class);
			startActivity(all_bang_intent);
			break;
		case R.id.tv_all_subway:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_all_area:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_all_find:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_find_shop_1:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_find_shop_2:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_find_shop_3:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_find_staion_1:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_find_staion_2:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_find_staion_3:
			Toast.makeText(getApplicationContext(), "개발예정", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

	}

	/*@Override
	public boolean onTouchEvent(MotionEvent me) {
		return gestureScanner.onTouchEvent(me);
	}*/
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean handled = super.dispatchTouchEvent(ev);
		handled = gestureScanner.onTouchEvent(ev);    
		return handled;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		//Log.d("onDown", "isWork");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		try {
			//Log.d("onFling", "isWork");
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
				return false;

			// right to left swipe
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				//Toast.makeText(getApplicationContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
				MoveNextView();
			}
			// left to right swipe
			else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				//Toast.makeText(getApplicationContext(), "Right Swipe", Toast.LENGTH_SHORT).show();
				MovewPreviousView();
			}
			// down to up swipe
			else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
				//Toast.makeText(getApplicationContext(), "Swipe up", Toast.LENGTH_SHORT).show();
			}
			// up to down swipe
			else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
				//Toast.makeText(getApplicationContext(), "Swipe down", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {

		}
		return true;
	}


	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		//Log.d("onLongPress", "isWork");
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		//Log.d("onScroll", "isWork");
		return true;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		//Log.d("onShowPress", "isWork");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		//Log.d("onSingleTapUp", "isWork");
		return true;
	}

	private void MoveNextView()
	{
		vf_main_bang.setInAnimation(AnimationUtils.loadAnimation(this,	R.anim.appear_from_right));
		vf_main_bang.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.disappear_to_left));
		vf_main_bang.showNext();
	}

	private void MovewPreviousView()
	{
		vf_main_bang.setInAnimation(AnimationUtils.loadAnimation(this,	R.anim.appear_from_left));
		vf_main_bang.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.disappear_to_right));
		vf_main_bang.showPrevious();
	}




}

