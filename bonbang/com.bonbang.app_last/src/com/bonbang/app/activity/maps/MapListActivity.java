package com.bonbang.app.activity.maps;

import java.util.ArrayList;

import net.daum.mf.map.api.MapCircle;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.MapAsyncTask;
import com.bonbang.app.commons.utils.HttpUtils;
import com.bonbang.app.commons.utils.ImageDownloaderTask;
import com.bonbang.app.model.BBLocation;
import com.bonbang.app.model.MapListItem;

public class MapListActivity extends Activity implements OnScrollListener, OnItemClickListener{

	BBLocation bbLocation;
	int zoom  = 8;
	int page = 0;
	boolean isLastItem = false;
	View footerView;


	ArrayList<MapListItem> list = new ArrayList<MapListItem>();
	String extno = "";

	ListView listView;
	CustomListAdapter customListAdapter;
	private LayoutInflater mInflater;
	private boolean mLockListView;

	

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_list);
	
		initActionbar("지역 목록");

		Intent intent = getIntent();
		bbLocation = intent.getParcelableExtra("bbLocation");
		zoom = intent.getIntExtra("zoom", 8);

		Log.i("Map List bbLocation", bbLocation.toString());

		listView = (ListView) findViewById(R.id.lv_map_list);
		listView.setOnScrollListener(this);
		listView.setOnItemClickListener(this);
		mLockListView = false;


		String url = new HttpUtils().makeRequestDefaultBangListPram(getApplicationContext(), bbLocation , page, "");

		MapAsyncTask listAsyncTask = new MapAsyncTask(getApplicationContext());
		listAsyncTask.execute(url);

		try {
			JSONArray bangList_jsonArray = listAsyncTask.get();
			//Log.i("bangList_jsonArray", bangList_jsonArray.toString());
			for(int i=0; i<bangList_jsonArray.length(); i++)
			{
				JSONObject jsonObject = bangList_jsonArray.getJSONObject(i);
				MapListItem item = new MapListItem();
				item.setAddress(jsonObject.getString("address"));
				item.setFloor(jsonObject.getString("floor"));
				item.setGujo(jsonObject.getString("gujo"));
				item.setGunmul(jsonObject.getString("gunmul"));
				item.setImage(jsonObject.getString("image"));
				item.setNo(jsonObject.getString("no"));
				item.setSubject(jsonObject.getString("subject"));
				item.setBojung(jsonObject.getString("bojung"));
				item.setWolse(jsonObject.getString("wolse"));
				item.setFloor_this(jsonObject.getString("floor_this"));

				Log.e("item",item.toString());

				list.add(item);
			}
			if(bangList_jsonArray.length() < 10)
			{
				isLastItem = true;
				Toast.makeText(getApplicationContext(), "Last items", Toast.LENGTH_SHORT).show();
				listView.removeFooterView(footerView);
				///listView.removeAllViews();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		// 푸터를 등록합니다. setAdapter 이전에 해야 합니다. 
		mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		footerView = mInflater.inflate(R.layout.footer, null);
		listView.addFooterView(footerView);


		customListAdapter = new CustomListAdapter(getApplicationContext(), list);
		listView.setAdapter(customListAdapter);
		Log.i("MapListItem List", list.size()+" / "+ extNo());

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

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		// TODO Auto-generated method stub
		Intent detailIntent = new Intent(MapListActivity.this, MapDetailWebActivity.class);
		detailIntent.putExtra("no", list.get(pos).getNo());
		startActivity(detailIntent);
	}

	public class CustomListAdapter extends BaseAdapter {
		private ArrayList<MapListItem> listData;
		private LayoutInflater layoutInflater;

		public CustomListAdapter(Context context, ArrayList<MapListItem> listData) {
			this.listData = listData;
			layoutInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return listData.size();
		}

		@Override
		public Object getItem(int position) {
			return listData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.item_map_list, null);
				holder = new ViewHolder();
				holder.item_tv_bojung = (TextView) convertView.findViewById(R.id.item_tv_bojung);
				holder.item_tv_gunmul = (TextView) convertView.findViewById(R.id.item_tv_gunmul);
				holder.item_tv_gujo = (TextView) convertView.findViewById(R.id.item_tv_gujo);
				holder.item_tv_address = (TextView) convertView.findViewById(R.id.item_tv_address);
				holder.item_tv_floor = (TextView) convertView.findViewById(R.id.item_tv_floor);
				holder.item_tv_subject = (TextView) convertView.findViewById(R.id.item_tv_subject);
				holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
				holder.itme_office_icon = (ImageView) convertView.findViewById(R.id.itme_office_icon);
				holder.itme_office_home = (ImageView) convertView.findViewById(R.id.itme_office_home);
				holder.itme_office_tel = (ImageView) convertView.findViewById(R.id.itme_office_tel);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			MapListItem newsItem = listData.get(position);
			holder.item_tv_bojung.setText(newsItem.getWolse() +"/"+ newsItem.getBojung());
			holder.item_tv_gunmul.setText(newsItem.getGunmul());
			holder.item_tv_gujo.setText(newsItem.getGujo());
			holder.item_tv_address.setText(newsItem.getAddress());
			holder.item_tv_floor.setText(newsItem.getFloor_this()+"/"+newsItem.getFloor());
			holder.item_tv_subject.setText(newsItem.getSubject());

			/*
			holder.item_tv_bojung.setTextColor(color.white);
			holder.item_tv_gunmul.setTextColor(color.black);
			holder.item_tv_gujo.setTextColor(color.black);
			holder.item_tv_address.setTextColor(color.white);
			holder.item_tv_floor.setTextColor(color.black);
			holder.item_tv_subject.setTextColor(color.black);
			 */
			if (holder.imageView != null) {
				new ImageDownloaderTask(holder.imageView).execute(newsItem.getImage());
			}

			return convertView;
		}

		class ViewHolder {
			TextView item_tv_bojung;
			TextView item_tv_gunmul;
			TextView item_tv_gujo;
			TextView item_tv_address;
			TextView item_tv_floor;
			TextView item_tv_subject;
			ImageView imageView;
			ImageView itme_office_icon;
			ImageView itme_office_home;
			ImageView itme_office_tel;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		// 현재 가장 처음에 보이는 셀번호와 보여지는 셀번호를 더한값이
		// 전체의 숫자와 동일해지면 가장 아래로 스크롤 되었다고 가정합니다.
		int count = totalItemCount - visibleItemCount;
		Log.e("IF", (firstVisibleItem >= count) + " / "+totalItemCount);
		if(firstVisibleItem >= count && totalItemCount != 0
				&& mLockListView == false && isLastItem == false)
		{
			//Toast.makeText(getApplicationContext(), "Loading next items", Toast.LENGTH_SHORT).show();
			Log.i("ADD", "Loading next items");
			addItems();
		}  


	}
	private void addItems()
	{
		Log.i("ADD", "start");
		// 아이템을 추가하는 동안 중복 요청을 방지하기 위해 락을 걸어둡니다.
		mLockListView = true;

		Runnable run = new Runnable()
		{
			@Override
			public void run()
			{
				page += 1;
				String url = new HttpUtils().makeRequestDefaultBangListPram(getApplicationContext(), bbLocation , page, extNo());

				MapAsyncTask listAsyncTask = new MapAsyncTask(getApplicationContext());
				listAsyncTask.execute(url);

				try {
					JSONArray bangList_jsonArray = listAsyncTask.get();
					//Log.i("bangList_jsonArray", bangList_jsonArray.toString());
					if(bangList_jsonArray.length() < 10)
					{
						isLastItem = true;
						//Toast.makeText(getApplicationContext(), "Last items", Toast.LENGTH_SHORT).show();
						listView.removeFooterView(footerView);
						///listView.removeAllViews();
					}
					for(int i=0; i<bangList_jsonArray.length(); i++)
					{
						JSONObject jsonObject = bangList_jsonArray.getJSONObject(i);
						MapListItem item = new MapListItem();
						item.setAddress(jsonObject.getString("address"));
						item.setFloor(jsonObject.getString("floor"));
						item.setGujo(jsonObject.getString("gujo"));
						item.setGunmul(jsonObject.getString("gunmul"));
						item.setImage(jsonObject.getString("image"));
						item.setNo(jsonObject.getString("no"));
						item.setSubject(jsonObject.getString("subject"));
						item.setBojung(jsonObject.getString("bojung"));
						item.setWolse(jsonObject.getString("wolse"));
						item.setFloor_this(jsonObject.getString("floor_this"));
						list.add(item);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				// 모든 데이터를 로드하여 적용하였다면 어댑터에 알리고
				// 리스트뷰의 락을 해제합니다.
				customListAdapter.notifyDataSetChanged();
				mLockListView = false;
			}
		};

		// 속도의 딜레이를 구현하기 위한 꼼수
		Handler handler = new Handler();
		handler.postDelayed(run, 1000);
	}

	public String extNo()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("(");

		for(int i=0; i<list.size(); i++)
		{
			if(i == list.size()-1)
			{
				sb.append(list.get(i).getNo());
			}
			else if(i<10)
			{
				sb.append(list.get(i).getNo()).append(",");
			}
		}

		sb.append(")");

		return sb.toString();
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) { }

}
