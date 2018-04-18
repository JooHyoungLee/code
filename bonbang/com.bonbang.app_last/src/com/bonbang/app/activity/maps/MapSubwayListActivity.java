package com.bonbang.app.activity.maps;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.MapAsyncTask;
import com.bonbang.app.commons.utils.HttpUtils;
import com.bonbang.app.commons.utils.ImageDownloaderTask;
import com.bonbang.app.model.MapListItem;
import com.bonbang.app.model.OfficeItem;

public class MapSubwayListActivity extends Activity implements OnScrollListener, OnItemClickListener{

	String no = "";
	int page = 0;
	boolean isLastItem = false;
	View footerView;
	
	
	ArrayList<MapListItem> list = new ArrayList<MapListItem>();

	ListView listView;
	CustomListAdapter customListAdapter;
	private LayoutInflater mInflater;
	private boolean mLockListView;
	
	ArrayList<String> extNoList = new ArrayList<String>(); 
	ArrayList<OfficeItem> officeItemList = new ArrayList<OfficeItem>();

	private int office_count = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_list);

		ActionBar actionBar = getActionBar();
        actionBar.setTitle("지하철 주변 목록");
        actionBar.setDisplayHomeAsUpEnabled(true); 
        
		Intent intent = getIntent();
		no = intent.getStringExtra("no");


		listView = (ListView) findViewById(R.id.lv_map_list);
		listView.setOnScrollListener(this);
		listView.setOnItemClickListener(this);
		mLockListView = false;
		
		
		String office_url = new HttpUtils().makeRequestSubwayOfficeBangListPram(getApplicationContext(), no);

		MapAsyncTask listOfficeAsyncTask = new MapAsyncTask(getApplicationContext());
		listOfficeAsyncTask.execute(office_url);
		/*
		  "uid": "k24449@korea.com",
	        "image": "o_19jl1m6m61rnu8ki1jufnul1de9a.jpg",
	        "office_name": "현대시티공인중개사",
	        "ceo": "김형식",
	        "address": "인천시 부평구 부평동",
	        "room_cnt": "4",
	        "phone": "032-330-2555",
	        "room_info": []

		 */
		try {
			JSONArray bangOfficeList_jsonArray = listOfficeAsyncTask.get();
			
			for(int i=0; i<bangOfficeList_jsonArray.length(); i++)
			{
				JSONObject jsonObject = bangOfficeList_jsonArray.getJSONObject(i);
				OfficeItem officeItem = new OfficeItem();
				officeItem.setAddress(jsonObject.getString("address"));
				officeItem.setCeo(jsonObject.getString("ceo"));
				officeItem.setImage(jsonObject.getString("image"));
				officeItem.setOffice_name(jsonObject.getString("office_name"));
				officeItem.setPhone(jsonObject.getString("phone"));
				officeItem.setUid(jsonObject.getString("uid"));
				officeItem.setRoom_cnt(jsonObject.getString("room_cnt"));			
				//office_count = Integer.parseInt(jsonObject.getString("room_cnt"));
				ArrayList<MapListItem> roomList = new ArrayList<MapListItem>();
				
				JSONArray room_info = jsonObject.getJSONArray("room_info");
				for(int j=0; j<room_info.length(); j++)
				{
					JSONObject jsonObjectItem = room_info.getJSONObject(j);
					MapListItem item = new MapListItem();
					item.setAddress(jsonObjectItem.getString("room_address"));
					item.setFloor(jsonObjectItem.getString("floor"));
					item.setGujo(jsonObjectItem.getString("gujo"));
					item.setGunmul(jsonObjectItem.getString("gunmul"));
					if(jsonObjectItem.isNull("room_image"))
					{
						item.setImage("");
					}
					else
					{
						item.setImage(jsonObjectItem.getString("room_image"));
					}
					item.setNo(jsonObjectItem.getString("room_no"));
					extNoList.add(jsonObjectItem.getString("room_no"));
					item.setSubject(jsonObjectItem.getString("subject"));
					item.setBojung(jsonObjectItem.getString("bojung"));
					item.setWolse(jsonObjectItem.getString("wolse"));
					item.setFloor_this(jsonObjectItem.getString("floor_this"));
					item.setType("room");
					roomList.add(item);
				}
				officeItem.setRoom_info(roomList);
				officeItemList.add(officeItem);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		for(int i=0; i<officeItemList.size(); i++)
		{
			MapListItem item = new MapListItem();
			item.setAddress(officeItemList.get(i).getAddress());
			item.setWolse(officeItemList.get(i).getOffice_name()+"("+officeItemList.get(i).getCeo()+")");
			item.setSubject("보유매물: "+officeItemList.get(i).getRoom_cnt());
			//item.setImage("http://bonbangapp.com/PEG/"+officeItemList.get(i).getImage());
			item.setImage(officeItemList.get(i).getImage());
			item.setType("office");
			Log.i("IM", officeItemList.get(i).getImage());
			list.add(item);
			
			for(int j=0; j<officeItemList.get(i).getRoom_info().size(); j++)
			{	
				Log.i("IM", officeItemList.get(i).getRoom_info().get(j).toString());
				if(officeItemList.get(i).getRoom_info().get(j) != null)
				{
					list.add(officeItemList.get(i).getRoom_info().get(j));
				}
			}
		}
		
		
		

		String url = new HttpUtils().makeRequestSubwayBangListPram(getApplicationContext(), no, extNoList, page);

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
				item.setType("room");
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
			e.printStackTrace();
		}
		// 푸터를 등록합니다. setAdapter 이전에 해야 합니다. 
		mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		footerView = mInflater.inflate(R.layout.footer, null);
		listView.addFooterView(footerView);


		customListAdapter = new CustomListAdapter(getApplicationContext(), list);
		listView.setAdapter(customListAdapter);
		Log.i("MapListItem List", list.size()+" / "+ extNo());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_list, menu);
		return true;
	}
	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		// TODO Auto-generated method stub
		if(list.get(pos).getType().equals("room"))
		{
			Intent detailIntent = new Intent(MapSubwayListActivity.this, MapDetailActivity.class);
			detailIntent.putExtra("no", list.get(pos).getNo());
			startActivity(detailIntent);
		}
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
				holder.in_item = (LinearLayout) convertView.findViewById(R.id.in_item);
				holder.item_tv_bojung = (TextView) convertView.findViewById(R.id.item_tv_bojung);
				holder.item_tv_gunmul = (TextView) convertView.findViewById(R.id.item_tv_gunmul);
				holder.item_tv_gujo = (TextView) convertView.findViewById(R.id.item_tv_gujo);
				holder.item_tv_address = (TextView) convertView.findViewById(R.id.item_tv_address);
				holder.item_tv_floor = (TextView) convertView.findViewById(R.id.item_tv_floor);
				holder.item_tv_subject = (TextView) convertView.findViewById(R.id.item_tv_subject);
				holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			MapListItem newsItem = listData.get(position);
			if(newsItem.getBojung() ==  null)
			{
				holder.item_tv_bojung.setText(newsItem.getWolse());
				holder.in_item.setBackgroundColor(Color.parseColor("#effdcb"));
				//
			}
			else
			{
				holder.item_tv_bojung.setText(newsItem.getWolse() +"/"+ newsItem.getBojung());
				holder.in_item.setBackgroundColor(Color.parseColor("#ffffff"));
			}
			if(newsItem.getFloor_this() == null && newsItem.getFloor() == null)
			{
				holder.item_tv_floor.setText("");
			}
			else
			{
				holder.item_tv_floor.setText(newsItem.getFloor_this()+"/"+newsItem.getFloor());
			}
			holder.item_tv_gunmul.setText(newsItem.getGunmul());
			holder.item_tv_gujo.setText(newsItem.getGujo());
			holder.item_tv_address.setText(newsItem.getAddress());
			
			holder.item_tv_subject.setText(newsItem.getSubject());
			Log.e("URL "+position, newsItem.getWolse());
			if (holder.imageView != null && newsItem.getImage() != null && !newsItem.getImage().equals("")) {
				Log.e("URL", newsItem.getImage());
				new ImageDownloaderTask(holder.imageView).execute(newsItem.getImage());
			}

			return convertView;
		}

		class ViewHolder {
			LinearLayout in_item;
			TextView item_tv_bojung;
			TextView item_tv_gunmul;
			TextView item_tv_gujo;
			TextView item_tv_address;
			TextView item_tv_floor;
			TextView item_tv_subject;
			ImageView imageView;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		// 현재 가장 처음에 보이는 셀번호와 보여지는 셀번호를 더한값이
		// 전체의 숫자와 동일해지면 가장 아래로 스크롤 되었다고 가정합니다.
		int count = totalItemCount - visibleItemCount;
		//Log.e("IF", (firstVisibleItem >= count) + " / "+totalItemCount);
		if(firstVisibleItem >= count && totalItemCount != 0
				&& mLockListView == false && isLastItem == false)
		{
			Toast.makeText(getApplicationContext(), "Loading next items", Toast.LENGTH_SHORT).show();
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
				String url = new HttpUtils().makeRequestSubwayBangListPram(getApplicationContext(), no, extNoList, page);

				MapAsyncTask listAsyncTask = new MapAsyncTask(getApplicationContext());
				listAsyncTask.execute(url);

				try {
					JSONArray bangList_jsonArray = listAsyncTask.get();
					//Log.i("bangList_jsonArray", bangList_jsonArray.toString());
					if(bangList_jsonArray.length() < 10)
					{
						isLastItem = true;
						Toast.makeText(getApplicationContext(), "Last items", Toast.LENGTH_SHORT).show();
						listView.removeFooterView(footerView);
						///listView.removeAllViews();
					}
					for(int i=0; i<bangList_jsonArray.length(); i++)
					{
						JSONObject jsonObject = bangList_jsonArray.getJSONObject(i);
						MapListItem item = new MapListItem();
						item.setAddress(jsonObject.getString("address"));
						if(!jsonObject.isNull("floor"))
						{
							item.setFloor(jsonObject.getString("floor"));
						}
						else
						{
							item.setFloor("");
						}
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
