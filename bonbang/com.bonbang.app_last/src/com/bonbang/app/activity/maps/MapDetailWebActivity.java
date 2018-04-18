package com.bonbang.app.activity.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.DailogActivity;
import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.MapAsyncTask;
import com.bonbang.app.commons.utils.HttpUtils;

public class MapDetailWebActivity extends Activity implements OnClickListener{

	WebView webView;
	private String no;
	private String lat;
	private String lng;
	private String thisTelePhone = "";
	private TextView tv_web_detail_office_name;
	private TextView tv_web_detail_office_tel;
	private Button btn_web_detail_call_1;
	private Button btn_web_detail_call_2;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_detail_web);
		
		initActionbar("자세히보기");

		Intent intent = getIntent();
		no = intent.getStringExtra("no");

		webView = (WebView)findViewById(R.id.detail_webview);
		webView.addJavascriptInterface(new WebAppInterface(this), "Android");
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		//webView.loadUrl("file:///android_asset/test.html");
		String url = getString(R.string.root_url) + getString(R.string.bang_detail_web) +no;
		webView.loadUrl(url);

		btn_web_detail_call_1 = (Button) findViewById(R.id.btn_web_detail_call_1);
		btn_web_detail_call_2 = (Button) findViewById(R.id.btn_web_detail_call_2);
		btn_web_detail_call_1.setOnClickListener(this);
		btn_web_detail_call_2.setOnClickListener(this);

		tv_web_detail_office_name = (TextView)findViewById(R.id.tv_web_detail_office_name);
		tv_web_detail_office_tel = (TextView)findViewById(R.id.tv_web_detail_office_tel); 

		getData();
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

	public void getData()
	{
		String url = new HttpUtils().makeRequestBangDetailPram(getApplicationContext(), no);
		MapAsyncTask detailAsyncTask = new MapAsyncTask(getApplicationContext());
		detailAsyncTask.execute(url);
		try {
			JSONArray bang_jsonArray = detailAsyncTask.get();
			if(bang_jsonArray.length() == 1)
			{
				JSONObject json = bang_jsonArray.getJSONObject(0);

				if(!json.isNull("office_name"))
				{
					tv_web_detail_office_name.setText(json.getString("office_name"));
				}
				if(!json.isNull("phone"))
				{
					thisTelePhone = json.getString("phone");
					tv_web_detail_office_tel.setText(thisTelePhone);
				}
				if(!json.isNull("lat"))
				{
					lat = json.getString("lat");
				}
				if(!json.isNull("lng"))
				{
					lng = json.getString("lng");
					//"lat": "37.48642849650689",
					// "lng": "126.78787107488193",
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(tv_web_detail_office_name.getText().toString().length() == 0)
		{
			tv_web_detail_office_name.setText("이름이 없습니다");
		}

		if(!thisTelePhone.equals(""))
		{
			tv_web_detail_office_tel.setText("전화번호가 없습니다");
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_web_detail_call_1:
			//연락받기
			Toast.makeText(this, "연락요청이 등록되었습니다", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn_web_detail_call_2:
			//연락하기
			if(!thisTelePhone.equals(""))
			{
				Intent intent = new Intent(MapDetailWebActivity.this, DailogActivity.class);
				intent.putExtra("tel", thisTelePhone);
				intent.putExtra("no", no);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(this, "전화번호가 없습니다", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_detail_web, menu);
		return true;
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

	public class WebAppInterface {

		Context mContext;
		/** Instantiate the interface and set the context */
		WebAppInterface(Context c) {

			mContext = c;

		}
		/** Show a toast from the web page */

		@JavascriptInterface
		public void app_report() {
			Toast.makeText(mContext, "신고하기", Toast.LENGTH_SHORT).show();
		}
		@JavascriptInterface
		public void app_dmapv() {
			//Toast.makeText(mContext, "지도보기", Toast.LENGTH_SHORT).show();
			//daummaps://look?p=37.537229,127.005515
			if(lat.equals("") || lng.equals(""))
			{
				Toast.makeText(mContext, "지도보기 - 좌표없음", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("daummaps://look?p="+lat+","+lng));
				startActivity(intent);
			}
		}
		@JavascriptInterface
		public void app_drdview() {
			//Toast.makeText(mContext, "로드뷰보기", Toast.LENGTH_SHORT).show();
			if(lat.equals("") || lng.equals(""))
			{
				Toast.makeText(mContext, "로드뷰보기 - 좌표없음", Toast.LENGTH_SHORT).show();
			}
			else
			{
				//Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("daummaps://roadView?p="+lat+","+lng));

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("daummaps://roadView?p="+lat+","+lng));
				startActivity(intent);
			}
			//"daummaps://roadView?p=37.537229,127.005515"

		}
		@JavascriptInterface
		public void app_call(String tel) {
			//Toast.makeText(mContext, "app_call "+tel, Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
			startActivity(intent);
		}
	}

}
