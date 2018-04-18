package com.bonbang.app.activity;

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


public class LoginActivity extends Activity implements OnClickListener{
	
	private EditText et_email_login;
	private Button btn_login;
	private Button btn_login_join;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initActionbar("로그인");
        
        
        et_email_login = (EditText)findViewById(R.id.et_email_login);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login_join = (Button)findViewById(R.id.btn_login_join);
        btn_login.setOnClickListener(this);
        btn_login_join.setOnClickListener(this);
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
			if(!et_email_login.getText().toString().equals(""))
			{
				//9YfIzzS6XUV6B90Ovg80yQ==
				String encode = Utils.encodeASE(et_email_login.getText().toString());
				String url = new HttpUtils().makeRequestTempLogin(getApplicationContext(), URLEncoder.encode(encode));
				HttpGetAsyncTask httpAsyncTask = new HttpGetAsyncTask(getApplicationContext());
				httpAsyncTask.execute(url);

				try {
					JSONArray jsonArray = httpAsyncTask.get();
					JSONObject json = jsonArray.getJSONObject(0);
					
					String uid = json.getString("uid");
					String auth = json.getString("auth");
					
					Log.e("login result",json.toString());
					// [{"kind":null,"auth":"no","uid":"9YfIzzS6XUV6B90Ovg80yQ==\n"}]
					// [{"kind":"idnotmatch"}]
					//{"kind":"authno","auth":"no","uid":"oZy7Ymk72d6+Kvf3tTPHFPZGg1ztRePj\/6IDi\/jHE28=\n"}
					if(json.getString("kind").equals("idnotmatch"))
					{
						Utils.showToast(getApplicationContext(), "회원가입이 필요합니다");
					}
					else if(json.getString("kind").equals("authno"))
					{
						if(!json.isNull("uid"))
						{
							SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
					        SharedPreferences.Editor editor = pref.edit();
					        editor.putString("uid", uid);
					        editor.putString("auth", auth);
					        editor.putString("email", et_email_login.getText().toString());
					        editor.commit();
							if(!uid.equals("") && auth.equals("no"))
							{
								Log.e("uid", uid);
								//로그인 성공
								Utils.showToast(getApplicationContext(), "로그인 성공");
								Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
					else
					{
						if(!uid.equals("") && auth.equals("yes"))
						{
							Intent login_intent = new Intent(LoginActivity.this, LoginAuthActivity.class);
							startActivity(login_intent);
						}
						else
						{
							Utils.showToast(getApplicationContext(), "로그인 실패 3");
						}
					}

					
					
				} catch (Exception e) {
					// TODO: handle exception
					Utils.showToast(getApplicationContext(), "로그인 실패");
					e.printStackTrace();
				}
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
			}
				
			
			break;
		case R.id.btn_login_join:
			//mjoin_id
			Intent intent = new Intent(LoginActivity.this, JoinTempActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
