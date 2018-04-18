package com.bonbang.app.activity.maps;

import net.daum.mf.map.api.MapLayout;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bonbang.app.R;
public class DaumMapActivity extends Activity implements  MapView.MapViewEventListener{

	private MapView mMapView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 13. 화면의 타이틀바를 없애줍니다.
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 14. 화면을 풀 스크린으로 해줍니다.
		setContentView(R.layout.daum_mapview);

		MapLayout mapLayout = new MapLayout(this);
		mMapView = mapLayout.getMapView();

		mMapView.setDaumMapApiKey("530f561cd6337e49a22fdc6f638d06f7");
		//      mMapView.setOpenAPIKeyAuthenticationResultListener(this);
		mMapView.setMapViewEventListener(this);
		mMapView.setMapType(MapView.MapType.Standard);

		ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
		mapViewContainer.addView(mapLayout);
	}

	@Override
	public void onMapViewCenterPointMoved(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewDoubleTapped(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewDragEnded(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewDragStarted(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewInitialized(MapView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewLongPressed(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewMoveFinished(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewSingleTapped(MapView arg0, MapPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapViewZoomLevelChanged(MapView arg0, int arg1) {
		// TODO Auto-generated method stub

	}
}
