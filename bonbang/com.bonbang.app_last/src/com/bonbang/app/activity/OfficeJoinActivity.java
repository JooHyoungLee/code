package com.bonbang.app.activity;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.MainActivity;
import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.HttpGetAsyncTask;
import com.bonbang.app.commons.asynctask.HttpPostAsyncTask;
import com.bonbang.app.commons.utils.HttpUtils;
import com.bonbang.app.commons.utils.Utils;


public class OfficeJoinActivity extends Activity implements OnClickListener{
	
	private EditText et_office_fullname;
	private EditText et_office_name;
	private EditText et_office_email;
	
	private EditText et_office_address;
	private EditText et_office_phone;
	private EditText et_office_cellphone;
	private EditText et_office_fax;
	
	private EditText et_office_password;
	private EditText et_office_password_ck;
	
	private Button btn_office_join;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_join);
        
        initActionbar("중개사무소 가입신청");
        
        et_office_fullname = (EditText)findViewById(R.id.et_office_fullname);
        et_office_name = (EditText)findViewById(R.id.et_office_name);
        et_office_email = (EditText)findViewById(R.id.et_office_email);
        et_office_address = (EditText)findViewById(R.id.et_office_address);
        et_office_phone = (EditText)findViewById(R.id.et_office_phone);
        et_office_cellphone = (EditText)findViewById(R.id.et_office_cellphone);
        et_office_fax = (EditText)findViewById(R.id.et_office_fax);
        et_office_password = (EditText)findViewById(R.id.et_office_password);
        et_office_password_ck = (EditText)findViewById(R.id.et_office_password_ck);
        
        
        btn_office_join = (Button)findViewById(R.id.btn_office_join);
        btn_office_join.setOnClickListener(this);
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


	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_office_join:
			//mjoin_id
			if(et_office_fullname.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "공인중개사 상호명을 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(et_office_name.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "대표자명을 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(et_office_email.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(et_office_address.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "중개사무소 주소를 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(et_office_phone.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "유선전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(et_office_cellphone.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "휴대전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(et_office_fax.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "FAX번호를 입력해주세요", Toast.LENGTH_SHORT).show();
			}
			else if(!et_office_password.getText().toString().equals(et_office_password_ck.getText().toString()) || et_office_password_ck.getText().toString().length() ==0 || et_office_password_ck.getText().toString().length() == 0)
			{
				Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
			}
			else
			{
				
				String email = URLEncoder.encode(Utils.encodeASE(et_office_email.getText().toString()));
				String name = URLEncoder.encode(Utils.encodeASE(et_office_fullname.getText().toString()));
				String passwd = URLEncoder.encode(Utils.encodeASE(et_office_password.getText().toString()));
				String ceo = URLEncoder.encode(Utils.encodeASE(et_office_name.getText().toString()));
				String address = URLEncoder.encode(Utils.encodeASE(et_office_address.getText().toString()));
				String phone = URLEncoder.encode(Utils.encodeASE(et_office_phone.getText().toString()));
				String hphone = URLEncoder.encode(Utils.encodeASE(et_office_cellphone.getText().toString()));
				String fax = URLEncoder.encode(Utils.encodeASE(et_office_fax.getText().toString()));
				
				
				String url = new HttpUtils().makeOfficeJoin(getApplicationContext(), email, name, passwd, ceo, address, phone, hphone, fax);
				HttpPostAsyncTask httpAsyncTask = new HttpPostAsyncTask(getApplicationContext());
				httpAsyncTask.execute(url);
				
				try {
					JSONArray jsonArray = httpAsyncTask.get();
					
					Log.e("JoinActivity", jsonArray.toString());
					//[{"kind":null,"auth":"yes","uid":null}]
					JSONObject json = jsonArray.getJSONObject(0);
					if(!json.isNull("auth"))
					{
						if(json.getString("auth").equals("yes"))
						{
							Toast.makeText(getApplicationContext(), "가입신청 완료", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(OfficeJoinActivity.this, MainActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
							startActivity(intent);
							finish();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "가입신청 실패", Toast.LENGTH_SHORT).show();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				/*
				String encode_email = Utils.encodeASE(et_email_login.getText().toString());
				String encode_passwd = Utils.encodeASE(et_password_login.getText().toString());
				TelephonyManager telManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE); 
				String phoneNum = telManager.getLine1Number();	
				String encode_phoneNum = Utils.encodeASE(phoneNum);
				
				
				
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
								Intent intent = new Intent(OfficeJoinActivity.this, MainActivity.class);
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
				
				*/
			}
			
			break;
		default:
			break;
		}
	}
}
