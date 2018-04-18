package com.bonbang.app.commons.utils;

import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.bonbang.app.R;
import com.bonbang.app.model.BBLocation;

public class HttpUtils {
	/*
	namseo: (37.48704426855774, 126.87119058350137)
	bukdong: (37.63065738675385, 127.09715518602563)
	level: 8
	w_sell_kind: 
	w_bojung1: all
	w_bojung2: all
	w_wolse1: all
	w_wolse2: all
	*/
	
	
	public String makeRequestAuthLogin(Context context, String email, String passwd)
	{
		//http://bonbangapp.com/app/app_get_pw_sink.php?email=암호화&passwd=암호화
		///app/app_get_temp_login_pass.php
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.login_auth));
		sb.append("?");
		sb.append("email=").append(email);
		sb.append("&passwd=").append(passwd);
		return sb.toString();
	}
	
	public String makeOfficeJoin(Context context, String email, String name, String passwd, String ceo, String address, 
			String phone, String hphone, String fax)
	{
		//http://bonbangapp.com/gsms/app_sms_send.php?pnumber=암호화&email=암호화
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.office_join));
		sb.append("?");
		sb.append("email=").append(email);
		sb.append("&name=").append(name);
		sb.append("&passwd=").append(passwd);
		sb.append("&ceo=").append(ceo);
		sb.append("&address=").append(address);
		sb.append("&phone=").append(phone);
		sb.append("&hphone=").append(hphone);
		sb.append("&fax=").append(fax);
		
		return sb.toString();
	}
	
	/**
	 * 인증번호 요청
	 * @param context
	 * @param pnumber
	 * @param email
	 * @return
	 */
	public String makeRequestSMS(Context context, String pnumber, String email)
	{
		//http://bonbangapp.com/gsms/app_sms_send.php?pnumber=암호화&email=암호화
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.req_sms));
		sb.append("?");
		sb.append("pnumber=").append(pnumber);
		sb.append("&email=").append(email);
		return sb.toString();
	}
	
	public String makeRequestSMSResult(Context context, String urlEncode_email, String auth_number)
	{
		//http://bonbangapp.com/app/app_join_sms_result.php?email=%EC%95%94%ED%98%B8%ED%99%94?auth_number=9017
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.req_sms_result));
		sb.append("?");
		sb.append("email=").append(urlEncode_email);
		sb.append("&auth_number=").append(auth_number);
		return sb.toString();
	}
	
	/**
	 * 정회원가입
	 * @param context
	 * @param email
	 * @param not_encode_name
	 * @param passwd
	 * @param hphone
	 * @return
	 */
	public String makeRequestJoin(Context context, String email, String not_encode_name, String passwd, String hphone)
	{
		//http://bonbangapp.com/app/app_get_mjoin.php?email=암호화&kind=general&name=%EA%B9%80%EC%84%B1%EC%A7%84&passwd=암호화&hphone=암호화
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.join));
		sb.append("?");
		sb.append("email=").append(email);
		sb.append("&kind=").append("general");
		sb.append("&name=").append(not_encode_name);
		sb.append("&passwd=").append(passwd);
		sb.append("&hphone=").append(hphone);
		
		return sb.toString();
	}
	/**
	 * 임시회원가입
	 * @param context
	 * @param email
	 * @return
	 */
	public String makeRequestTempJoin(Context context, String email)
	{
		//http://bonbangapp.com/app/app_get_temp_mjoin.php?email=암호화&kind=general
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.join_temp));
		sb.append("?");
		sb.append("email=").append(email);
		sb.append("&kind=").append("general");
		
		return sb.toString();
	}
	
	public String makeRequestTempLogin(Context context, String email)
	{
		//http://bonbangapp.com/app/app_get_temp_mjoin.php?email=암호화&kind=general
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.login_temp));
		sb.append("?");
		sb.append("email=").append(email);
		
		return sb.toString();
	}
	
	public  String makeRequestMapViewCountPram(Context context, BBLocation bbLocation, int level)
	{
		//http://bonbangapp.com/app/app_get_main_recom_estate.php?lat=126.7245994&lng=37.4894690
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.map_view_count));
		sb.append("?");
		sb.append("namseo").append("=(").append(bbLocation.getNamseo_lat()).append("%2C+").append(bbLocation.getNamseo_lng()).append(")&");
		sb.append("bukdong").append("=(").append(bbLocation.getBukdong_lat()).append("%2C+").append(bbLocation.getBukdong_lng()).append(")&");
		sb.append("level").append("=").append(level).append("&");
		sb.append("w_sell_kind").append("=").append("").append("&");
		sb.append("w_bojung1").append("=").append("all").append("&");
		sb.append("w_bojung2").append("=").append("all").append("&");
		sb.append("w_wolse1").append("=").append("all").append("&");
		sb.append("w_wolse2").append("=").append("all");
		//Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * 메인화면 주변 지하철역
	 * @param context
	 * @param loc
	 * @return
	 */
	public static String makeRequestMainSubwayListPram(Context context, Location loc)
	{
		//http://bonbangapp.com/app/app_get_main_recom_estate.php?lat=126.7245994&lng=37.4894690
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.main_fav_subway));
		sb.append("?");
		sb.append("lng").append("=").append(loc.getLatitude()).append("&");
		sb.append("lat").append("=").append(loc.getLongitude());
		//Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 메인 인근 추천방 max9
	 * @param context
	 * @param loc
	 * @return
	 */
	public static String makeRequestMainFavBangListPram(Context context, Location loc)
	{
		//http://bonbangapp.com/app/app_get_main_recom_estate.php?lat=126.7245994&lng=37.4894690
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.main_fav_max_9));
		sb.append("?");
		sb.append("lng").append("=").append(loc.getLatitude()).append("&");
		sb.append("lat").append("=").append(loc.getLongitude());
		//Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 인근 추천 공인중계사
	 * @param context
	 * @param loc
	 * @return
	 */
	public static String makeRequestMainOfficeListPram(Context context, String no)
	{
		//http://bonbangapp.com/app/app_get_main_recom_estate.php?lat=126.7245994&lng=37.4894690
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.main_fav_office));
		sb.append("?");
		sb.append("no").append("=").append(no);
		//Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
	
	
	
	public String makeRequestPram(Context context, BBLocation bbLocation, int level)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.url_map));
		sb.append("?");
		sb.append("namseo").append("=(").append(bbLocation.getNamseo_lat()).append("%2C+").append(bbLocation.getNamseo_lng()).append(")&");
		sb.append("bukdong").append("=(").append(bbLocation.getBukdong_lat()).append("%2C+").append(bbLocation.getBukdong_lng()).append(")&");
		sb.append("level").append("=").append(level).append("&");
		sb.append("w_sell_kind").append("=").append("").append("&");
		sb.append("w_bojung1").append("=").append("all").append("&");
		sb.append("w_bojung2").append("=").append("all").append("&");
		sb.append("w_wolse1").append("=").append("all").append("&");
		sb.append("w_wolse2").append("=").append("all");
		return sb.toString();
	}
	
	public String makeRequestSubWayPram(Context context, BBLocation bbLocation, int level)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.subway));
		sb.append("?");
		sb.append("namseo").append("=(").append(bbLocation.getNamseo_lat()).append("%2C+").append(bbLocation.getNamseo_lng()).append(")&");
		sb.append("bukdong").append("=(").append(bbLocation.getBukdong_lat()).append("%2C+").append(bbLocation.getBukdong_lng()).append(")");
		/*sb.append("level").append("=").append(level).append("&");
		sb.append("w_sell_kind").append("=").append("").append("&");
		sb.append("w_bojung1").append("=").append("all").append("&");
		sb.append("w_bojung2").append("=").append("all").append("&");
		sb.append("w_wolse1").append("=").append("all").append("&");
		sb.append("w_wolse2").append("=").append("all");*/
		return sb.toString();
	}
	
	public String makeRequestDefaultBangListPram(Context context, BBLocation bbLocation, int page, String extNo)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.bang_list));
		sb.append("?");
		sb.append("namseo").append("=(").append(bbLocation.getNamseo_lat()).append("%2C+").append(bbLocation.getNamseo_lng()).append(")&");
		sb.append("bukdong").append("=(").append(bbLocation.getBukdong_lat()).append("%2C+").append(bbLocation.getBukdong_lng()).append(")&");
		sb.append("ext_no").append("=").append(extNo).append("&");
		sb.append("w_sell_kind").append("=").append("").append("&");
		sb.append("w_bojung1").append("=").append("all").append("&");
		sb.append("w_bojung2").append("=").append("all").append("&");
		sb.append("w_wolse1").append("=").append("all").append("&");
		sb.append("w_wolse2").append("=").append("all").append("&");
		sb.append("page").append("=").append(page);
		//Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
	
	public String makeRequestBangDetailPram(Context context, String no)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.bang_detail));
		sb.append("?");
		sb.append("no").append("=").append(no);
		//Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
	
	//http://bonbangapp.com/app/get_subway_list.php?no=49&ext_no[]=65&ext_no[]=94&ext_no[]=93&w_sell_kind=&w_bojung1=all&w_bojung2=all&w_wolse1=all&w_wolse2=all&page=2
	public String makeRequestSubwayBangListPram(Context context, String no,  ArrayList<String> extNo ,int page)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.subway_list));
		sb.append("?");
		sb.append("no").append("=").append(no).append("&");
		for(int i=0; i<extNo.size(); i++)
		{
			sb.append("ext_no[]").append("=").append(extNo.get(i)).append("&");
		}
		sb.append("w_sell_kind").append("=").append("").append("&");
		sb.append("w_bojung1").append("=").append("all").append("&");
		sb.append("w_bojung2").append("=").append("all").append("&");
		sb.append("w_wolse1").append("=").append("all").append("&");
		sb.append("w_wolse2").append("=").append("all").append("&");
		sb.append("page").append("=").append(page);
		Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
	
	public String makeRequestSubwayOfficeBangListPram(Context context, String no)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(context.getString(R.string.root_url));
		sb.append(context.getString(R.string.subway_recom));
		sb.append("?");
		sb.append("no").append("=").append(no).append("&");
		sb.append("w_sell_kind").append("=").append("").append("&");
		sb.append("w_bojung1").append("=").append("all").append("&");
		sb.append("w_bojung2").append("=").append("all").append("&");
		sb.append("w_wolse1").append("=").append("all").append("&");
		sb.append("w_wolse2").append("=").append("all");
		//Log.i("sb.toString", sb.toString());
		return sb.toString();
	}
}
