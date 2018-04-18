package com.bonbang.app.activity;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.HttpGetAsyncTask;
import com.bonbang.app.commons.utils.HttpUtils;
import com.bonbang.app.commons.utils.Utils;


public class PhoneActivity extends Activity implements OnClickListener{
	

	
	private TextView tv_phone_number;
	private EditText et_rq_number;
	
	private Button btn_request_number;
	private Button btn_rq_number_ok;
	private Button btn_rq_login;
	private Button btn_rq_off_join;
	
	private String response_auth_num = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        
        initActionbar("본방 회원가입");
        
        tv_phone_number = (TextView)findViewById(R.id.tv_phone_number);
        et_rq_number = (EditText)findViewById(R.id.et_rq_number);
        btn_request_number = (Button)findViewById(R.id.btn_request_number);
        btn_rq_number_ok = (Button)findViewById(R.id.btn_rq_number_ok);
        btn_rq_login = (Button)findViewById(R.id.btn_rq_login);
        btn_rq_off_join = (Button)findViewById(R.id.btn_rq_off_join);
        
        btn_request_number.setOnClickListener(this);
        btn_rq_number_ok.setOnClickListener(this);
        btn_rq_login.setOnClickListener(this);
        btn_rq_off_join.setOnClickListener(this);
        
        TelephonyManager telManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE); 
		String phoneNum = telManager.getLine1Number();	
		tv_phone_number.setText(phoneNum.replaceAll("-", ""));
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


	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.btn_request_number:
			//인증번호 요청'
			SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
	        String email_et = pref.getString("email", "");
			String encode_email = Utils.encodeASE(email_et);
			String encode_phone = Utils.encodeASE(tv_phone_number.getText().toString());
			
			String url = new HttpUtils().makeRequestSMS(getApplicationContext(), URLEncoder.encode(encode_phone), URLEncoder.encode(encode_email));
			HttpGetAsyncTask httpAsyncTask = new HttpGetAsyncTask(getApplicationContext());
			httpAsyncTask.execute(url);
			
			try {
				JSONArray jsonArray = httpAsyncTask.get();
				JSONObject jsonOBJ = jsonArray.getJSONObject(0);
				if(!jsonOBJ.isNull("auth_num"))
				{
					/* 이미 인증받은 번호
					if(jsonOBJ.getString("auth_num").equals("prebeingnumber"))
					{
						
					}*/
					response_auth_num = String.valueOf(jsonOBJ.getInt(("auth_num")));
					//et_rq_number.setText(response_auth_num);
				}
				
				Log.e("PhoneActivity", jsonArray.toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			btn_request_number.setClickable(false);
			Toast.makeText(getApplicationContext(), "입력한 번호로 인증번호를 요청합니다.", Toast.LENGTH_LONG).show();
			break;
			
		case R.id.btn_rq_number_ok:
			//인증번호를 포함하여 서버에 인증요청
			if(et_rq_number.getText().length() == 0)
			{
				Toast.makeText(getApplicationContext(), "인증번호를 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else
			{
				//인증검증
				SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
		        String emails = prefs.getString("email", "");
		        if(!emails.equals(""))
		        {
		        	String encode_uemail = Utils.encodeASE(emails);
					
					String uurl = new HttpUtils().makeRequestSMSResult(getApplicationContext(), URLEncoder.encode(encode_uemail), et_rq_number.getText().toString()); 
					HttpGetAsyncTask uhttpAsyncTask = new HttpGetAsyncTask(getApplicationContext());
					uhttpAsyncTask.execute(uurl);
					try {
					JSONArray jsonArray = uhttpAsyncTask.get();
					Log.e("PhoneActivity", jsonArray.toString());
					JSONObject json = jsonArray.getJSONObject(0);
					//[{"result":"timeover"}]
					String resultMsg = "";
					//String auth_num = 
					if(!json.isNull("result"))
					{
						resultMsg = json.getString("result");
						if(resultMsg.equals("timeover"))
						{
							Toast.makeText(getApplicationContext(), "인증시간 만료", Toast.LENGTH_SHORT).show();
						}
						else if(resultMsg.equals("authfail"))
						{
							Toast.makeText(getApplicationContext(), "인증번호 오류", Toast.LENGTH_SHORT).show();
						}
						else if(resultMsg.equals("ok"))
						{
							Toast.makeText(getApplicationContext(), "인증완료", Toast.LENGTH_SHORT).show();
							Intent joinIntent = new Intent(PhoneActivity.this, JoinActivity.class);
							startActivity(joinIntent);
							finish();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "알수없는 오류", Toast.LENGTH_SHORT).show();
							//exception
						}
					}
					
					
					
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
		        }
		        else
		        {
		        	Toast.makeText(getApplicationContext(), "로그인이 필요합니다", Toast.LENGTH_SHORT).show();
		        }
				
			}
			break;
			
		case R.id.btn_rq_login:
			//로그인페이지 이동
			Intent login_intent = new Intent(PhoneActivity.this, LoginActivity.class);
			startActivity(login_intent);
			break;
			
		case R.id.btn_rq_off_join:
			//중계소회원 가입화기
			Intent office_intent = new Intent(PhoneActivity.this, OfficeJoinActivity.class);
			startActivity(office_intent);
			break;
			
		case R.id.btn_login_join:
			//mjoin_id
			/*if(et_email_login.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(et_name_login.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(!et_password_login.getText().toString().equals(et_password_ck_login.getText().toString()) || et_password_ck_login.getText().toString().length() ==0 || et_password_login.getText().toString().length() == 0)
			{
				Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(!ck_agree.isChecked())
			{
				Toast.makeText(getApplicationContext(), "약관동의가 필요합니다", Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				
				String encode_email = Utils.encodeASE(et_email_login.getText().toString());
				String encode_passwd = Utils.encodeASE(et_password_login.getText().toString());
				TelephonyManager telManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE); 
				String phoneNum = telManager.getLine1Number();	
				String encode_phoneNum = Utils.encodeASE(phoneNum);
				
				String url = new HttpUtils().makeRequestJoin(getApplicationContext(), URLEncoder.encode(encode_email), URLEncoder.encode(et_name_login.getText().toString()), URLEncoder.encode(encode_passwd), URLEncoder.encode(encode_phoneNum.replaceAll("-", "")));
				HttpGetAsyncTask httpAsyncTask = new HttpGetAsyncTask(getApplicationContext());
				httpAsyncTask.execute(url);
				
				try {
					JSONArray jsonArray = httpAsyncTask.get();
					JSONObject json = jsonArray.getJSONObject(0);
					Log.e("JoinActivity", jsonArray.toString());
					
					if(json.getString("kind").equals("general"))
					{
						if(!json.isNull("uid"))
						{
							String uid = json.getString("uid");
							if(!uid.equals(""))
							{
								Log.e("uid", uid);
								//임시 회원가입 성공
								Utils.showToast(getApplicationContext(), "회원가입 완료");
								Intent intent = new Intent(PhoneActivity.this, MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
								startActivity(intent);
							}
							else
							{
								Utils.showToast(getApplicationContext(), "회원가입 실패");
							}
						}
						else
						{
							Utils.showToast(getApplicationContext(), "회원가입 실패");
						}
					}
					else
					{
						Utils.showToast(getApplicationContext(), "회원가입 실패");
					}
					//
					//[{"kind":"general","auth":"no","uid":"66Xmy9kG0LkHTlln4taFDA==\n"}]
					
				
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					Utils.showToast(getApplicationContext(), "회원가입 오류");
					finish();
				}
				
				
			}*/
			
			break;
		default:
			break;
		}
	}
}
