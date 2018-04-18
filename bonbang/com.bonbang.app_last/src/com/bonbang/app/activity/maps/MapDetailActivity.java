package com.bonbang.app.activity.maps;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.MapAsyncTask;
import com.bonbang.app.commons.utils.DetailImageDownloaderTask;
import com.bonbang.app.commons.utils.HttpUtils;

public class MapDetailActivity extends Activity {

	ViewFlipper viewFlipper;
	private float lastX;
	private float lastY;
	ArrayList<String> imageURL;
	int fliperCount = 0;
	ScrollView detail_ScrollView;
	
	private LayoutInflater mInflater;
	
	private TextView tv_bang_title;//제목
	private TextView tv_bang_bojung;//보증금
	private TextView tv_bang_wolse;//월세
	private TextView tv_bang_no; //방번호
	
	private TextView tv_bang_address; //주소
	private TextView tv_bang_floor; //층수
	private TextView tv_bang_size; //크기
	private TextView tv_bang_gujo; //구조
	private TextView tv_bang_gunmul; //형태
	
	private TextView tv_bang_option; //옵션
	private TextView tv_bang_gwanri; //관리비
	private TextView tv_bang_gwanri_ext;  //관리비 포함
	private TextView tv_bang_parking; //주차
	private TextView tv_bang_elevator; //엘레베이터
	private TextView tv_bang_ipju_date; //입주가능일
	
	private TextView tv_bang_detail_office_name;
	private TextView tv_bang_detail_office_user;
	private TextView tv_bang_detail_office_number;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_detail);
		
		ActionBar actionBar = getActionBar();
        actionBar.setTitle("방주소");
        actionBar.setDisplayHomeAsUpEnabled(true); 

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        detail_ScrollView = (ScrollView)findViewById(R.id.detail_ScrollView);
		//footerView = mInflater.inflate(R.layout.footer, null);
        
        tv_bang_title = (TextView) findViewById(R.id.tv_bang_title);
        tv_bang_bojung = (TextView) findViewById(R.id.tv_bang_bojung);
        tv_bang_wolse = (TextView) findViewById(R.id.tv_bang_wolse);
        tv_bang_no = (TextView) findViewById(R.id.tv_bang_no);

        tv_bang_address = (TextView) findViewById(R.id.tv_bang_address);
        tv_bang_floor = (TextView) findViewById(R.id.tv_bang_floor);
        tv_bang_size = (TextView) findViewById(R.id.tv_bang_size);
        tv_bang_gujo = (TextView) findViewById(R.id.tv_bang_gujo);
        tv_bang_gunmul = (TextView) findViewById(R.id.tv_bang_gunmul);

        tv_bang_option = (TextView) findViewById(R.id.tv_bang_option);
        tv_bang_gwanri = (TextView) findViewById(R.id.tv_bang_gwanri);
        tv_bang_gwanri_ext = (TextView) findViewById(R.id.tv_bang_gwanri_ext);
        tv_bang_parking = (TextView) findViewById(R.id.tv_bang_parking);
        tv_bang_elevator = (TextView) findViewById(R.id.tv_bang_elevator);
        tv_bang_ipju_date = (TextView) findViewById(R.id.tv_bang_ipju_date);
        
        tv_bang_detail_office_name = (TextView) findViewById(R.id.tv_bang_detail_office_name);
        tv_bang_detail_office_user = (TextView) findViewById(R.id.tv_bang_detail_office_user);
        tv_bang_detail_office_number = (TextView) findViewById(R.id.tv_bang_detail_office_number);
        
        imageURL = new ArrayList<String>();
        
        Intent intent = getIntent();
        String no = intent.getStringExtra("no");
        
        String url = new HttpUtils().makeRequestBangDetailPram(getApplicationContext(), no);
        /*
		MapAsyncTask detailAsyncTask = new MapAsyncTask(getApplicationContext());
		detailAsyncTask.execute(url);
		try {
			JSONArray bang_jsonArray = detailAsyncTask.get();
			if(bang_jsonArray.length() == 1)
			{
				JSONObject json = bang_jsonArray.getJSONObject(0);

				
				if(!json.isNull("bojung"))
				{
					tv_bang_bojung.setText(json.getString("bojung"));
				}
				else
				{
					//Line Disable
				}
				if(!json.isNull("wolse"))
				{
					tv_bang_wolse.setText(json.getString("wolse"));
				}
				else
				{
					//Line Disable
				}
				tv_bang_no.setText(no);
				
				if(!json.isNull("address"))
				{
					tv_bang_address.setText(json.getString("address"));
					actionBar.setTitle(json.getString("address"));
			        actionBar.setDisplayHomeAsUpEnabled(true); 
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("floor_this") && !json.isNull("floor"))
				{
					tv_bang_floor.setText(json.getString("floor_this")+"/"+json.getString("floor"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("size2") && !json.isNull("size1"))
				{
					tv_bang_size.setText(json.getDouble("size2")+"/"+json.getDouble("size1"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("gujo"))
				{
					tv_bang_gujo.setText(json.getString("gujo"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("gunmul"))
				{
					tv_bang_gunmul.setText(json.getString("gunmul"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("option"))
				{
					tv_bang_option.setText(json.getString("option"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("gwanri"))
				{
					tv_bang_gwanri.setText(json.getString("gwanri"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("gwanri_ext"))
				{
					tv_bang_gwanri_ext.setText(json.getString("gwanri_ext"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("parking"))
				{
					tv_bang_parking.setText(json.getString("parking"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("elevator"))
				{
					tv_bang_elevator.setText(json.getString("elevator"));
				}
				else
				{
					//Line Disable
				}
				
				if(!json.isNull("ipju_date"))
				{
					tv_bang_ipju_date.setText(json.getString("ipju_date"));
				}
				else
				{
					//Line Disable
				}
				
				
				if(!json.isNull("office_name"))
				{
					tv_bang_detail_office_name.setText(json.getString("office_name"));
				}
				else
				{
					//Line Disable
				}
				if(!json.isNull("ceo"))
				{
					tv_bang_detail_office_user.setText(json.getString("ceo"));
				}
				else
				{
					//Line Disable
				}
				if(!json.isNull("phone"))
				{
					tv_bang_detail_office_number.setText(json.getString("phone"));
				}
				else
				{
					//Line Disable
				}
				
				JSONArray imageList = json.getJSONArray("image");
				for(int i=0; i<imageList.length(); i++)
				{
					imageURL.add(imageList.getString(i));
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Log.i("imageURL.size()",imageURL.size()+"");
		for(int i=0; i<imageURL.size(); i++)
		{
			if(!imageURL.get(i).equals("http://bonbangapp.com/PEG/"))
			{
				Log.i("imageURL "+i,imageURL.get(i));
				ImageView image = new ImageView(getApplicationContext());
				DetailImageDownloaderTask detailImageDownloaderTask = new DetailImageDownloaderTask(image);
				detailImageDownloaderTask.execute(imageURL.get(i));
				viewFlipper.addView(image);
				fliperCount++;
			}
		}
		
		tv_bang_title.setText("방제목(미정) - 이미지 수:"+fliperCount);
		*/
		/*detail_ScrollView.setOnGenericMotionListener(new OnGenericMotionListener() {
			
			@Override
			public boolean onGenericMotion(View view, MotionEvent mEvent) {
				// TODO Auto-generated method stub
				if(view.getId() == R.id.flipper)
				{
					Log.i("flipper", "isTouch");
				}
				
				return false;
			}
		});*/
		detail_ScrollView.setOnTouchListener(new OnTouchListener() {
			int dragthreshold = 30;
			@Override
			public boolean onTouch(View view, MotionEvent touchevent) {
				// TODO Auto-generated method stub
				if(fliperCount>1)
		    	{
		    		switch (touchevent.getAction()) {
		            
		            case MotionEvent.ACTION_DOWN: 
		            	lastX = touchevent.getX();
		            	lastY = touchevent.getY();
		                break;
		            case MotionEvent.ACTION_UP: 
		                float currentX = touchevent.getX();
		                float currentY = touchevent.getY();
		                
		                int distanceX = Math.abs((int) touchevent.getRawX() - (int)lastX);
		                int distanceY = Math.abs((int) touchevent.getRawY() - (int)lastY);

		                // Handling left to right screen swap.
		                //Log.i("motion", "lastY - currentY = "+(lastY - currentY)+" / currentY-lastY="+(currentY-lastY));
		                if (lastX < currentX && (distanceY > distanceX && distanceY > dragthreshold)) {
		                	
		                	// If there aren't any other children, just break.
		                    if (viewFlipper.getDisplayedChild() == 0)
		                    	break;
		                    
		                    // Next screen comes in from left.
		                    viewFlipper.setInAnimation(MapDetailActivity.this, R.anim.slide_in_from_left);
		                    // Current screen goes out from right. 
		                    viewFlipper.setOutAnimation(MapDetailActivity.this, R.anim.slide_out_to_right);
		                    
		                    // Display next screen.
		                    viewFlipper.showNext();
		                 }
		                                         
		                // Handling right to left screen swap.
		                 if (lastX > currentX && (distanceX > distanceY && distanceX > dragthreshold)) {
		                	 
		                	 // If there is a child (to the left), kust break.
		                	 if (viewFlipper.getDisplayedChild() == 1)
		                		 break;
		        
		                	 // Next screen comes in from right.
		                	 viewFlipper.setInAnimation(MapDetailActivity.this, R.anim.slide_in_from_right);
		                	// Current screen goes out from left. 
		                	 viewFlipper.setOutAnimation(MapDetailActivity.this, R.anim.slide_out_to_left);
		                     
		                	// Display previous screen.
		                     viewFlipper.showPrevious();
		                 }
		                 break;
		        	 }
		    	}
				
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_detail, menu);
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
	 // Using the following method, we will handle all screen swaps.
    public boolean onTouchEvent(MotionEvent touchevent) {
    	Log.i("onTouchEvent", "isTouch");
    	if(fliperCount>1)
    	{
    		switch (touchevent.getAction()) {
            
            case MotionEvent.ACTION_DOWN: 
            	lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP: 
                float currentX = touchevent.getX();
                
                // Handling left to right screen swap.
                if (lastX < currentX) {
                	
                	// If there aren't any other children, just break.
                    if (viewFlipper.getDisplayedChild() == 0)
                    	break;
                    
                    // Next screen comes in from left.
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
                    // Current screen goes out from right. 
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);
                    
                    // Display next screen.
                    viewFlipper.showNext();
                 }
                                         
                // Handling right to left screen swap.
                 if (lastX > currentX) {
                	 
                	 // If there is a child (to the left), kust break.
                	 if (viewFlipper.getDisplayedChild() == 1)
                		 break;
        
                	 // Next screen comes in from right.
                	 viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
                	// Current screen goes out from left. 
                	 viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
                     
                	// Display previous screen.
                     viewFlipper.showPrevious();
                 }
                 break;
        	 }
    	}
    	
         return false;
    }
}
