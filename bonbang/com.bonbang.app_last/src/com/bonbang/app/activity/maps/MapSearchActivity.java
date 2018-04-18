package com.bonbang.app.activity.maps;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bonbang.app.R;
import com.bonbang.app.commons.utils.DBAdapter;
import com.bonbang.app.commons.utils.Utils;
import com.bonbang.app.model.Rankup_Category;




public class MapSearchActivity extends Activity{

	private Spinner area_1;
	private Spinner area_2;
	private Spinner area_3;

	private ArrayAdapter<String> area_1_ad;
	private ArrayAdapter<String> area_2_ad;
	private ArrayAdapter<String> area_3_ad;

	private ArrayList<Rankup_Category> area_1_list;
	private ArrayList<Rankup_Category> area_2_list;
	private ArrayList<Rankup_Category> area_3_list;

	private DBAdapter dbAdapter;
	
	private SeekBar seek_money;


	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_search);

		initActionbar("지도에서 방찾기");
		init();
		
		

		area_1.setOnItemSelectedListener(new OnItemSelectedListener( ) {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				// TODO Auto-generated method stub
				if(area_1_list.size() > 0)
				{
					initArea2(area_1_list.get(pos).getNo());
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				Utils.showToast(getApplicationContext(), "test");
			}
		});
		area_2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				// TODO Auto-generated method stub
				if(area_2_list.size()>0)
				{
					initArea3(area_2_list.get(pos).getNo());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		area_3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				// TODO Auto-generated method stub
				Utils.showToast(getApplicationContext(), area_3_list.get(pos).getList_name());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	private void init()
	{
		dbAdapter = new DBAdapter(getApplicationContext());
		area_1 = (Spinner)findViewById(R.id.area_1);
		area_2 = (Spinner)findViewById(R.id.area_2);
		area_3 = (Spinner)findViewById(R.id.area_3);

		area_1_ad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
		area_2_ad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
		area_3_ad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);

		area_1_list = new ArrayList<Rankup_Category>();
		area_2_list = new ArrayList<Rankup_Category>();
		area_3_list = new ArrayList<Rankup_Category>();

		dbAdapter.open();
		area_1_list = dbAdapter.getArea1();
		for(int i=0; i<area_1_list.size(); i++)
		{
			area_1_ad.add(area_1_list.get(i).getList_name());
		}
		area_1.setAdapter(area_1_ad);
		dbAdapter.close();
	}


	private void initArea2(int no)
	{
		area_2_ad.clear();
		area_2_list.clear();
		dbAdapter.open();
		area_2_list = dbAdapter.getArea2(no);
		if(area_2_list.size() > 0)
		{
			for(int i=0; i<area_2_list.size(); i++)
			{
				area_2_ad.add(area_2_list.get(i).getList_name());
			}
		}
		else
		{
			area_2_ad.add("선택");
		}
		area_2.setAdapter(area_2_ad);
		dbAdapter.close();
	}

	private void initArea3(int no)
	{
		area_3_ad.clear();
		area_3_list.clear();
		dbAdapter.open();
		area_3_list = dbAdapter.getArea2(no);
		if(area_3_list.size() > 0)
		{
			for(int i=0; i<area_3_list.size(); i++)
			{
				area_3_ad.add(area_3_list.get(i).getList_name());
			}
		}
		else
		{
			area_3_ad.add("선택");
		}

		area_3.setAdapter(area_3_ad);
		dbAdapter.close();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_list, menu);
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

}
