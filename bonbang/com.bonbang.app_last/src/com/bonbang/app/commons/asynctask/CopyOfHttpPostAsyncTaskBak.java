package com.bonbang.app.commons.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bonbang.app.commons.SetServiceURL;
import com.bonbang.app.model.HttpResponseResult;

public class CopyOfHttpPostAsyncTaskBak extends AsyncTask<Map<String, String>, Void, HttpResponseResult>{

	private Context mContext;
	private String serviceName;
	//ProgressDialog dialog;
	
	//init
	public CopyOfHttpPostAsyncTaskBak(Context cxt, String serviceName) {
		this.mContext = cxt;
		this.serviceName = serviceName;
		//dialog = new ProgressDialog(mContext);
	}
	
	//STEP 1
	@Override
	protected void onPreExecute() {
		//dialog.setTitle("Please wait");
		//dialog.show();
		super.onPreExecute();
	}
	
	//STEP 2
	@Override
	protected HttpResponseResult doInBackground(Map<String, String>... params) {
		InputStream is = null;
		String result = "";
		// Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            
            HttpPost httpPost = new HttpPost(new SetServiceURL(mContext, serviceName).getURL());
            
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry: params[0].entrySet())
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            
            httpPost.setEntity(new UrlEncodedFormEntity(paramList));
            
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            result = sb.toString();
            Log.e("result", result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpResponseResult httpResponseResult = new HttpResponseResult();
		return httpResponseResult;
	}
	
	//STEP 3
	@Override
	protected void onPostExecute(HttpResponseResult result) {
		super.onPostExecute(result);
	}
}
