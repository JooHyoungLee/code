package com.bonbang.app.activity;

import java.net.URLDecoder;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.MainActivity;
import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.HttpGetAsyncTask;
import com.bonbang.app.commons.utils.HttpUtils;
import com.bonbang.app.commons.utils.Utils;


public class JoinActivity extends Activity implements OnClickListener{
	
	private TextView et_email_login;
	private EditText et_name_login;
	private EditText et_password_login;
	private EditText et_password_ck_login;
	
	private CheckBox ck_agree;
	private Button btn_login_join;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        
        initActionbar("정회원가입");
        
        et_email_login = (TextView)findViewById(R.id.et_email_login);
        et_name_login = (EditText)findViewById(R.id.et_name_login);
        et_password_login = (EditText)findViewById(R.id.et_password_login);
        et_password_ck_login = (EditText)findViewById(R.id.et_password_ck_login);
        
        btn_login_join = (Button)findViewById(R.id.btn_login_join);
        ck_agree = (CheckBox)findViewById(R.id.ck_agree);
        btn_login_join.setOnClickListener(this);
        
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        String email = pref.getString("email", "");
        et_email_login.setText(email);
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
		case R.id.btn_login_join:
			//mjoin_id
			if(et_email_login.getText().toString().equals(""))
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
				
				String url = new HttpUtils().makeRequestJoin(getApplicationContext(), URLEncoder.encode(encode_email), URLEncoder.encode(et_name_login.getText().toString()), 
						URLEncoder.encode(encode_passwd), URLEncoder.encode(encode_phoneNum.replaceAll("-", "")));
				HttpGetAsyncTask httpAsyncTask = new HttpGetAsyncTask(getApplicationContext());
				httpAsyncTask.execute(url);
				Log.e("join password", Utils.decodeASE(URLDecoder.decode(URLEncoder.encode(encode_passwd))));
				try {
					JSONArray jsonArray = httpAsyncTask.get();
					JSONObject json = jsonArray.getJSONObject(0);
					Log.e("JoinActivity", jsonArray.toString());
					//[{"kind":"general","auth":"no","uid":"BAphz01j1pvw\/2vvHcifEg==\n"}]
					if(json.getString("kind").equals("general"))
					{
						if(!json.isNull("uid"))
						{
							String auth = json.getString("auth");
							String uid = json.getString("uid");
							SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
					        SharedPreferences.Editor editor = pref.edit();
					        editor.putString("uid", uid);
					        editor.putString("auth", auth);
					        editor.putString("email", et_email_login.getText().toString().trim());
					        editor.commit();
					        //[{"kind":"general","auth":"yes","uid":"jjxpMvtnlEuzR91wIeP9NA==\n"}]
							if(!uid.equals(""))
							{
								Log.e("uid", uid);
								//임시 회원가입 성공
								Utils.showToast(getApplicationContext(), "회원가입 완료");
								Intent intent = new Intent(JoinActivity.this, MainActivity.class);
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
					else if(json.getString("kind").equals("prebeingid"))
					{
						Utils.showToast(getApplicationContext(), "가입된 이메일입니다.");
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
				
				
			}
			
			break;
		default:
			break;
		}
	}
}
