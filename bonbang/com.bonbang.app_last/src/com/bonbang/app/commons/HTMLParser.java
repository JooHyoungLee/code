package com.bonbang.app.commons;

import java.io.IOException;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLParser {
	public void getBangList()
	{
		HttpClient httpClient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet("http://bonbangapp.com/view/view_list_ajax.php?namseo=%2837.505503698735%2C+126.71173633191182%29&bukdong=%2837.551107625745%2C+126.94223966949995%29&level=7&w_sell_kind=&w_bojung1=all&w_bojung2=all&w_wolse1=all&w_wolse2=all");
	    try {
	      httpClient.execute(httpget, new BasicResponseHandler() {
	        @Override
	        public String handleResponse(HttpResponse response) throws HttpResponseException,
	            IOException {
	          // 웹페이지를 그냥 갖어오면 한글이 깨져요. 인코딩 처리를 해야해요.
	          String res = new String(super.handleResponse(response).getBytes("8859_1"), "euc-kr");
	          Document doc = Jsoup.parse(res);
	          Elements rows = doc.select("table.table_board2 tbody tr");
	          String[] items = new String[] { "순위", "팀", "경기수", "승", "패", "무", "승률", "연속",
	              "최근 10경기" };
	          for (Element row : rows) {
	            Iterator<Element> iterElem = row.getElementsByTag("td").iterator();
	            StringBuilder builder = new StringBuilder();
	            for (String item : items) {
	              builder.append(item + ": " + iterElem.next().text() + "   \t");
	            }
	            System.out.println(builder.toString());
	          }
	 
	          return res;
	        }
	      });
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	}
}
