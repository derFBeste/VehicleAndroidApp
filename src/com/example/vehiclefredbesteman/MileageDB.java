package com.example.vehiclefredbesteman;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MileageDB {
	public static final String DB_NAME = "mileage.db";
	public static final int DB_VERSION = 1;
	
	public static final String MILEAGE_TABLE = "mileage";
	
	public static final String MILEAGE_ID = "_id";
	public static final int MILEAGE_ID_COL = 0;
	
	public static final String DATE = "date";
	public static final int DATE_COL = 1;
	
	public static final String PRICE = "price";
	public static final int PRICE_COL = 2;
	
	public static final String GALLONS = "gallons";
	public static final int GALLONS_COL = 3;
	
	public static final String MILES = "miles";
	public static final int MILES_COL = 4;
	
	public static final String CREATE_MILEAGE_TABLE =
			"CREATE TABLE " + MILEAGE_TABLE + " (" +
					MILEAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					DATE + " TEXT 	NOT NULL, " +
					PRICE + " REAL NOT NULL," +
					GALLONS + " REAL NOT NULL, " +
					MILES + " REAL NOT NULL);";
	
	public static final String DROP_MILEAGE_TABLE = "DROP TABLE IF EXISTS " + MILEAGE_TABLE;
	
	private static class DBHelper extends SQLiteOpenHelper{
		
		public DBHelper(Context context, String name, CursorFactory factory, int version){
			super(context, name, factory, version);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_MILEAGE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d("*** Mileage ***", "Upgrading db from version " + oldVersion + " to " + newVersion);
			db.execSQL(MileageDB.DROP_MILEAGE_TABLE);
			onCreate(db);
		}
		
	}//end class DBHelper
	
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	public MileageDB(Context context){
		dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
	}
	
	private void openReadableDB(){
		db = dbHelper.getReadableDatabase();
	}
	
	private void openWritableDB(){
		db = dbHelper.getWritableDatabase();
	}
	
	private void closeDB(){
		if (db != null)
			db.close();
	}
	
	//inserts a mileage object into the db
	public long insertMileage(Mileage mileage){
		ContentValues cv = new ContentValues();
		cv.put(MILEAGE_ID, mileage.getId());
		cv.put(DATE, mileage.getDate());
		cv.put(PRICE, mileage.getPrice());
		cv.put(GALLONS, mileage.getGallons());
		cv.put(MILES, mileage.getMiles());
		
		this.openWritableDB();
		long rowID = db.insert(MILEAGE_TABLE,  null, cv);
		this.closeDB();
		
		return rowID;
	}
	
	/*
	 * The id field in the Mileage object is ignored -- the system will assign the next
	 * valid value.
	 */
	public long insertMileageAutoId(Mileage mileage){
		ContentValues cv = new ContentValues();
		cv.put(DATE, mileage.getDate());
		cv.put(PRICE, mileage.getPrice());
		cv.put(GALLONS, mileage.getGallons());
		cv.put(MILES, mileage.getMiles());
		
		this.openWritableDB();
		long rowID = db.insert(MILEAGE_TABLE,  null, cv);
		this.closeDB();
		
		return rowID;		
	}
	
	
	public int updateMileage(Mileage mileage){
		ContentValues cv = new ContentValues();
		cv.put(MILEAGE_ID, mileage.getId());
		cv.put(DATE, mileage.getDate());
		cv.put(PRICE, mileage.getPrice());
		cv.put(GALLONS, mileage.getGallons());
		cv.put(MILES, mileage.getMiles());
		
		String where = MILEAGE_ID + "= ?";
		String[] whereArgs = { String.valueOf(mileage.getId())};
		
		this.openWritableDB();
		int rowCount = db.delete(MILEAGE_TABLE, where, whereArgs);
		this.closeDB();
		
		return rowCount;
		
	}
	
	/*
	 *Retrieves the mileage records and returns them as an ArrayList of Mileage objects. 
	 */
	
	public ArrayList<Mileage> getMileageRecords(){
		this.openReadableDB();
		Cursor cursor = db.query(MILEAGE_TABLE, null, null, null, null, null, null);
		ArrayList<Mileage> mileage = new ArrayList<Mileage>();
		while(cursor.moveToNext()){
			mileage.add(getMileageFromCursor(cursor));
		}
		if(cursor != null)
			cursor.close();
		this.closeDB();
		
		return mileage;
	}
	
	public Cursor getMileageAsCursor(){
		this.openReadableDB();
		Cursor cursor = db.query(MILEAGE_TABLE, null, null, null, null, null, null);
		return cursor;
	}
	
	public Mileage getMileage(int id){
		String where = MILEAGE_ID + "= ?";
		String[] whereArgs = {Integer.toString(id)};
		Cursor cursor = db.query(MILEAGE_TABLE, null, null, null, null, null, null);
		this.openReadableDB();
		
		cursor.moveToFirst();
		Mileage mileage = getMileageFromCursor(cursor);
		
		if(cursor != null)
			cursor.close();
		this.closeDB();
		
		return mileage;
	}
	
	private static Mileage getMileageFromCursor(Cursor cursor){
		if(cursor == null || cursor.getCount() == 0){
			return null;
		}
		else{
			try{
				Mileage mileage = new Mileage(
						cursor.getLong(MILEAGE_ID_COL),
						cursor.getString(DATE_COL),
						cursor.getDouble(PRICE_COL),
						cursor.getDouble(GALLONS_COL),
						cursor.getDouble(MILES_COL));
				
				return mileage;
				}
			catch(Exception e){
				return null;
			}
		}
	}
	
	public void resetMileageDB(){
		db.execSQL(MileageDB.DROP_MILEAGE_TABLE);
		db.execSQL(CREATE_MILEAGE_TABLE);
	}
	
}//end class MileageDB
