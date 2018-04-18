package com.bonbang.app.activity.maps;

import java.util.ArrayList;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonbang.app.R;
import com.bonbang.app.model.BangCount;
import com.bonbang.app.model.Subway;
import com.google.android.gms.maps.model.Marker;

public final class MapUtils {
	public static ArrayList<BangCount> makeMarkerItem (JSONArray jsonArray, Context context, int zoom)
	{
		ArrayList<BangCount> list =  new ArrayList<BangCount>();
		for(int i=0; i<jsonArray.length(); i++)
		{
			try {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				//Log.e("item", jsonObject.getDouble("lat")+" / "+ jsonObject.getDouble("lng"));
				//Log.i("test",jsonObject.isNull("count")+"");
				if(!jsonObject.isNull("count") && !jsonObject.isNull("lat") && !jsonObject.isNull("lng"))
				{
					BangCount bangCount = new BangCount(jsonObject.getInt("count"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
					Log.i("bangCount", bangCount.toString());
					list.add(bangCount);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ck","ck22");
				e.printStackTrace();
			}
		}
		return list;	
	}
	public static ArrayList<Subway> makeSubwayItem (JSONArray jsonArray, Context context, int zoom)
	{
		ArrayList<Subway> list =  new ArrayList<Subway>();
		//Log.i("subway length", jsonArray.length()+"");
		for(int i=0; i<jsonArray.length(); i++)
		{
			try {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				//Log.e("item", jsonObject.getDouble("lat")+" / "+ jsonObject.getDouble("lng"));
				//Log.i("test",jsonObject.isNull("count")+"");
				if(!jsonObject.isNull("no") && !jsonObject.isNull("lat") && !jsonObject.isNull("lng"))
				{
					//Subway bangCount = new BangCount(jsonObject.getInt("count"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
					Subway subway = new Subway(jsonObject.getString("no"), jsonObject.getString("name"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
					//Log.i("subway", subway.toString());
					list.add(subway);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ck","ck11");
				e.printStackTrace();
			}
		}
		return list;	
	}
	public static BitmapDrawable writeOnDrawable(Context context,int drawableId, String text){

		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);

		Paint paint = new Paint(); 
		paint.setStyle(Style.FILL);  
		paint.setColor(Color.BLACK); 
		paint.setTextSize(20); 

		Canvas canvas = new Canvas(bm);
		canvas.drawText(text, bm.getWidth()/2, bm.getHeight()/2, paint);

		BitmapDrawable bt = new BitmapDrawable(bm);
		Log.i("writeOnDrawable",bt.getBitmap().getByteCount()+"");
		return bt;
	}

	// CalloutBalloonAdapter 인터페이스 구현
	class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
		private final View mCalloutBalloon;

		public CustomCalloutBalloonAdapter(Context context) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mCalloutBalloon = inflater.inflate(R.layout.count_marker, null);
			// mCalloutBalloon = getLayoutInflater().inflate(R.layout.activity_login, null);
		}

		@Override
		public View getCalloutBalloon(MapPOIItem poiItem) {
			View ImageView;
			ImageView img = (ImageView) mCalloutBalloon.findViewById(R.id.count_marker_bg);
			img.setBackgroundResource(R.drawable.ic_launcher);
			//((ImageView) mCalloutBalloon.findViewById(R.id.count_marker_bg)).setBackgroundResource(R.drawable.ic_launcher);
			((TextView) mCalloutBalloon.findViewById(R.id.count_marker_text)).setText(poiItem.getItemName());

			return mCalloutBalloon;
		}

		@Override
		public View getPressedCalloutBalloon(MapPOIItem poiItem) {
			return null;
		}
	}

	public static int getHighBg(Context context, String str)
	{
		String resName = "@drawable/icon_count_high_"+str;
		String packName = context.getPackageName(); // 패키지명
		int resID = context.getResources().getIdentifier(resName, "drawable", packName);
		Log.i("getHighBg resID", resID+"");
		return resID;
	}
}
