package com.bonbang.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DailogActivity extends Activity implements OnClickListener{

	private String tel;
	private String no;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dailog);

		Intent intent = getIntent();
		tel = intent.getStringExtra("tel");
		no = intent.getStringExtra("no");

		Button btn_call = (Button) findViewById(R.id.btn_call);
		Button btn_msg = (Button) findViewById(R.id.btn_msg);
		Button btn_close = (Button)findViewById(R.id.btn_close);
		btn_call.setOnClickListener(this);
		btn_msg.setOnClickListener(this);
		btn_close.setOnClickListener(this);

		TextView tv_dialog_msg = (TextView)findViewById(R.id.tv_dialog_msg);
		TextView tv_dialog_no = (TextView)findViewById(R.id.tv_dialog_no);
		tv_dialog_msg.setText("\"본방에서 보고 연락한다\"라고\n 말씀해주세요!");
		tv_dialog_no.setText("[등록번호 : "+no+"]");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_call:
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
			startActivity(intent);
			break;

		case R.id.btn_msg:

			Intent intent_msg = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + tel));
			intent_msg.putExtra("sms_body", "본방앱 보고 연락드립니다.");
			//intent_msg.setType("vnd.android-dir/mms-sms");   
			startActivity(intent_msg);
			break;
		case R.id.btn_close:
			finish();
			break;

		default:
			break;
		}
	}

}
