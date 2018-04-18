package com.bonbang.app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class StartActivity extends Activity {

	/** Called when the activity is first created. */
	private int splashShowTime = 1500;
	private Context mContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 13. 화면의 타이틀바를 없애줍니다.
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 14. 화면을 풀 스크린으로 해줍니다.
		setContentView(R.layout.activity_start);  //  01. splash.xml 을 붙여줍니다.
		mContext = getApplicationContext();
		initDB();
		Thread splashThread = new Thread() { // 03. 3초 Thread 를 만들기 위한 작업을 합니다.
			int wait = 0; // 07. wait
			@Override
			public void run() { // 04. Thread 의 run 메소드 
				try {
					while (wait < splashShowTime) { // 06. 3초동안 실행시키기 위한 조건
						sleep(100); // 08. 0.1 초동안 슬립 이때 try-catch 로 감싸준다. 
						wait += 100; // 09. 0.1 초 타이머를 증가시킨다. 
					}
				} catch (Exception e) {

				} finally { //10. while 이 다 돈다면 반드시 처리되어야하는 finally 구문을 작성
					finish(); // 11. 현재 액티비티 finish 를 해준다.
					startActivity(new Intent(StartActivity.this, MainActivity.class)); // 12. MainActivity 로 이동시킨다.
				}
			}
		};
		splashThread.start(); //  05. 만든 스레드를 시작합니다.
	}

	public void initDB()
	{
		try {
			boolean bResult = isCheckDB(mContext);  // DB가 있는지?
			Log.d("MiniApp", "DB Check="+bResult);
			if(!bResult){   // DB가 없으면 복사
				copyDB(mContext);
			}else{

			}
		} catch (Exception e) {

		}

	}
	private String PACKAGE_NAME = "com.bonbang.app";
	private String DB_NAME = "bonbang.db";
	
	// DB가 있나 체크하기
	public boolean isCheckDB(Context mContext){
		String filePath = "/data/data/" + PACKAGE_NAME + "/databases/" + DB_NAME;
		File file = new File(filePath);

		if (file.exists()) {
			return true;
		}

		return false;

	}
	// DB를 복사하기
	// assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기
	public void copyDB(Context mContext){
		Log.d("MiniApp", "copyDB");
		AssetManager manager = mContext.getAssets();
		String folderPath = "/data/data/" + PACKAGE_NAME + "/databases";
		String filePath = "/data/data/" + PACKAGE_NAME + "/databases/" + DB_NAME;
		File folder = new File(folderPath);
		File file = new File(filePath);

		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			InputStream is = manager.open(DB_NAME);
			BufferedInputStream bis = new BufferedInputStream(is);

			if (folder.exists()) {
			}else{
				folder.mkdirs();
			}


			if (file.exists()) {
				file.delete();
				file.createNewFile();
			}

			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			int read = -1;
			byte[] buffer = new byte[1024];
			while ((read = bis.read(buffer, 0, 1024)) != -1) {
				bos.write(buffer, 0, read);
			}

			bos.flush();

			bos.close();
			fos.close();
			bis.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("ErrorMessage : ", e.getMessage());
		} 
	}

}
