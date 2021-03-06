package com.bonbang.app.activity;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bonbang.app.MainActivity;
import com.bonbang.app.R;
import com.bonbang.app.commons.asynctask.HttpGetAsyncTask;
import com.bonbang.app.commons.asynctask.HttpPostAsyncTask;
import com.bonbang.app.commons.utils.HttpUtils;
import com.bonbang.app.commons.utils.Utils;


public class LoginAuthActivity extends Activity implements OnClickListener{
	
	private EditText et_passwd_login;
	private Button btn_login;
	private Button btn_passwd_find;
	
	String email;
	String auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_auth);
        
        initActionbar("비밀번호 입력");
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        email = pref.getString("email", "");
        auth = pref.getString("auth", "");
        
        et_passwd_login = (EditText)findViewById(R.id.et_passwd_login);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_passwd_find = (Button)findViewById(R.id.btn_passwd_find);
        btn_login.setOnClickListener(this);
        btn_passwd_find.setOnClickListener(this);
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
		case R.id.btn_login:
			if(!et_passwd_login.getText().toString().equals(""))
			{
				//9YfIzzS6XUV6B90Ovg80yQ==
				String encode_email = Utils.encodeASE(et_passwd_login.getText().toString());
				String encode_passwd = Utils.encodeASE(et_passwd_login.getText().toString());
				String url = new HttpUtils().makeRequestAuthLogin(getApplicationContext(), URLEncoder.encode(encode_email), URLEncoder.encode(encode_passwd));
				HttpGetAsyncTask httpAsyncTask = new HttpGetAsyncTask(getApplicationContext());
				httpAsyncTask.execute(url);
				
				Log.e("Login password", Utils.decodeASE(URLDecoder.decode(URLEncoder.encode(encode_passwd))));

				try {
					JSONArray jsonArray = httpAsyncTask.get();
					JSONObject json = jsonArray.getJSONObject(0);
					Log.e("PASSWORD",json.toString());
					// [{"kind":null,"auth":"no","uid":"9YfIzzS6XUV6B90Ovg80yQ==\n"}]
					// [{"kind":"idnotmatch"}]
					if(json.getString("kind").equals("pwnotmatch"))
					{
						Utils.showToast(getApplicationContext(), "비밀번호를 확인해주세요");
						//Intent intent = new Intent(LoginAuthActivity.this, MainActivity.class);
						//intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
						//startActivity(intent);
					}
					else
					{
						if(!json.isNull("uid"))
						{
							String uid = json.getString("uid");
							String auth = json.getString("auth");
							String kind = json.getString("kind");
							//[{"kind":"pwnotmatch"}]n
							if(!uid.equals("") && !auth.equals(""))
							{
								Log.e("uid", uid);
								//로그인 성공
								SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
						        SharedPreferences.Editor editor = pref.edit();
						        editor.putString("uid", uid);
						        editor.putString("email", email);
						        editor.putString("auth", auth);
						        editor.commit();

							
								Utils.showToast(getApplicationContext(), "로그인 성공");
								Intent intent = new Intent(LoginAuthActivity.this, MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
								startActivity(intent);
							}
							else
							{
								Utils.showToast(getApplicationContext(), "로그인 실패 1");
							}
						}
						else
						{
							Utils.showToast(getApplicationContext(), "로그인 실패 2");
						}
					}

					
					
				} catch (Exception e) {
					// TODO: handle exception
					Utils.showToast(getApplicationContext(), "로그인 실패 e");
					e.printStackTrace();
				}
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
			}
				
			
			break;
		case R.id.btn_passwd_find:
			//mjoin_id
			Toast.makeText(getApplicationContext(), "비밀번호 찾기", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
