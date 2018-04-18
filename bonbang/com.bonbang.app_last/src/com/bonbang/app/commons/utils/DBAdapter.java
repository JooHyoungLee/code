package com.bonbang.app.commons.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bonbang.app.model.BBLocation;
import com.bonbang.app.model.Rankup_Category;
public class DBAdapter {

	private static final String TAG = "DBAdapter"; //used for logging database version changes

	// Field Names:
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TASK = "task";
	public static final String KEY_DATE = "date";

	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_TASK, KEY_DATE};

	// Column Numbers for each Field Name:
	public static final int COL_ROWID = 0;
	public static final int COL_TASK = 1;
	public static final int COL_DATE = 2;

	// DataBase info:
	public static final String DATABASE_NAME = "bonbang.db";
	public static final String DATABASE_TABLE_SUBWAY = "ab_subway";
	public static final String DATABASE_TABLE = "mainToDo";
	public  static final int DATABASE_VERSION = 2; // The version number must be incremented each time a change to DB structure occurs.

	//SQL statement to create database
	private static final String DATABASE_CREATE_SQL = 
			"CREATE TABLE " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_TASK + " TEXT NOT NULL, "
			+ KEY_DATE + " TEXT"
			+ ");";

	private final Context context;
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;


	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}

	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}

	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}

	public void viewTables()
	{
		Log.e("viewTables","---------------------------------------------------");
		
		ArrayList<String> arrTblNames = new ArrayList<String>();
		Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

		if (c.moveToFirst()) {
			while ( !c.isAfterLast() ) {
				arrTblNames.add( c.getString( c.getColumnIndex("name")) );
				Log.d("viewTables", c.getString( c.getColumnIndex("name")) );
				
				c.moveToNext();
			}
		}
	}
	
	public ArrayList<Rankup_Category> getArea1()
	{
		ArrayList<Rankup_Category> list = new ArrayList<Rankup_Category>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from rankup_category WHERE parent_no=0");
		
		Cursor results = db.rawQuery(sb.toString(), null);
		results.moveToFirst();
		while(!results.isAfterLast()){
			
			Rankup_Category rankup_Category = new Rankup_Category();
			rankup_Category.setNo(results.getInt(0));
			rankup_Category.setParent_no(results.getInt(1));
			rankup_Category.setList_name(results.getString(3));
			results.moveToNext();
			list.add(rankup_Category);
		}
		results.close();
		return list;
	}
	
	public ArrayList<Rankup_Category> getArea2(int parent_no)
	{
		ArrayList<Rankup_Category> list = new ArrayList<Rankup_Category>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from rankup_category WHERE parent_no=").append(parent_no);
		
		Cursor results = db.rawQuery(sb.toString(), null);
		results.moveToFirst();
		while(!results.isAfterLast()){
			
			Rankup_Category rankup_Category = new Rankup_Category();
			rankup_Category.setNo(results.getInt(0));
			rankup_Category.setParent_no(results.getInt(1));
			rankup_Category.setList_name(results.getString(3));
			results.moveToNext();
			list.add(rankup_Category);
		}
		results.close();
		return list;
	}

	public ArrayList<Subway> getSubway(BBLocation bbLocation)
	{
		ArrayList<Subway> list = new ArrayList<Subway>();

		StringBuffer sb = new StringBuffer();
		sb.append("select * from " + DATABASE_TABLE_SUBWAY+" WHERE ");
		sb.append("cast(location_x AS Double) < ").append(bbLocation.getBukdong_lat());
		sb.append(" AND  cast(location_x AS Double) > ").append(bbLocation.getNamseo_lat());

		sb.append(" AND  cast(location_y AS Double) < ").append(bbLocation.getBukdong_lng());
		sb.append(" AND  cast(location_y AS Double) > ").append(bbLocation.getNamseo_lng());
		/*
		 * 
		    select * from ab_subway 
			WHERE
			cast(location_x AS Double) < 37.63065738675385 
			AND  cast(location_x AS Double) > 37.48704426855774 

			AND  cast(location_y AS Double) < 127.09715518602563 
			AND  cast(location_y AS Double) > 126.87119058350137 
		 */

		Cursor results = db.rawQuery(sb.toString(), null);

		results.moveToFirst();

		while(!results.isAfterLast()){
			Subway subway = new Subway();
			subway.setNo(results.getInt(0));
			subway.setParent_no(results.getInt(1));
			subway.setName(results.getString(2));
			subway.setLocation_x(results.getString(3));
			subway.setLocation_y(results.getString(4));
			results.moveToNext();
			list.add(subway);
		}
		results.close();

		return list;
	}


	// Add a new set of values to be inserted into the database.
	public long insertRow(String task, String date) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TASK, task);
		initialValues.put(KEY_DATE, date);

		// Insert the data into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}

	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String task, String date) {
		String where = KEY_ROWID + "=" + rowId;
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_TASK, task);
		newValues.put(KEY_DATE, date);
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}


	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			//_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");

			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

			// Recreate new database:
			onCreate(_db);
		}
	}


}

