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


public class JoinTempActivity extends Activity implements OnClickListener{
	
	private EditText et_email_login;
	private CheckBox ck_agree;
	private Button btn_login_join;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_temp);
        
        initActionbar("회원가입");
        
        et_email_login = (EditText)findViewById(R.id.et_email_login);
        btn_login_join = (Button)findViewById(R.id.btn_login_join);
        ck_agree = (CheckBox)findViewById(R.id.ck_agree);
        btn_login_join.setOnClickListener(this);
        
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("uid", "");
        editor.putString("auth", "");
        editor.putString("email", "");
        editor.commit();
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
			else if(!ck_agree.isChecked())
			{
				Toast.makeText(getApplicationContext(), "약관동의가 필요합니다", Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				
				String encode = Utils.encodeASE(et_email_login.getText().toString());
					
				String url = new HttpUtils().makeRequestTempJoin(getApplicationContext(), URLEncoder.encode(encode));
				HttpGetAsyncTask httpAsyncTask = new HttpGetAsyncTask(getApplicationContext());
				httpAsyncTask.execute(url);
				
				try {
					JSONArray jsonArray = httpAsyncTask.get();
					JSONObject json = jsonArray.getJSONObject(0);
					Log.e("JoinTEMPActivity", jsonArray.toString());
					
					if(json.getString("kind").equals("prebeingid"))
					{
						Utils.showToast(getApplicationContext(), "사용중인 이메일입니다");
					}
					else
					{
						if(!json.isNull("uid"))
						{
							String uid = json.getString("uid");
							if(!uid.equals(""))
							{
								Log.e("uid", uid);
								SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
						        SharedPreferences.Editor editor = pref.edit();
						        editor.putString("uid", uid);
						        editor.putString("auth", "no");
						        editor.putString("email", et_email_login.getText().toString().trim());
						        editor.commit();
						        
								//임시 회원가입 성공
								Utils.showToast(getApplicationContext(), "회원가입 완료");
								Intent intent = new Intent(JoinTempActivity.this, MainActivity.class);
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
