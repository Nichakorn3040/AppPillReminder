package com.pillreminder.pillreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class Database_pill extends SQLiteOpenHelper {
	
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "databasesavefile";
    // Table Name
    private static final String TABLE_NAME = "databasesavefiletable";

	public Database_pill(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	  public static final String COLUMN_ID ="Code";
	    public static final String COL_1 = "col1";
	    public static final String COL_2 = "col2";
	    public static final String COL_3 = "col3";
	    public static final String COL_4 = "col4";
	    public static final String COL_5 = "col5";
	    public static final String COL_6 = "col6";
	    public static final String COL_7 = "col7";
	    public static final String COL_8 = "col8";
	    public static final String COL_9 = "col9";
	   
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+ TABLE_NAME +" (Code INTEGER PRIMARY KEY AUTOINCREMENT, " 
        		+ COL_1 + " TEXT(100), " 
				+ COL_2 + " TEXT(100), " 
        		+ COL_3 + " TEXT(100), "
				+ COL_4 + " TEXT(100), " 
				+ COL_5 + " TEXT(100), "
				+ COL_6 + " TEXT(100), "
				+ COL_7 + " TEXT(100), "
				+ COL_8 + " TEXT(100), "
        		+ COL_9 + " TEXT(100));");
	    Log.d("CREATE TABLE","Create Table Successfully.");
	}

	//Insert data
		public long InsertData(String Code, String Pill_name, String Pill_amount, String Pill_dose,
				String Pill_mode, String Pill_date, String Pill_morning, String Pill_lunch, String Pill_evening, String Pill_beforebed){
			try { 
	SQLiteDatabase db;
			db = this.getWritableDatabase(); //write data
			
			ContentValues Val = new ContentValues();
			Val.put(COLUMN_ID, Code);
			Val.put(COL_1, Pill_name);
			Val.put(COL_2, Pill_amount);
			Val.put(COL_3, Pill_dose);
			Val.put(COL_4, Pill_mode);
			Val.put(COL_5, Pill_date);
			Val.put(COL_6, Pill_morning);
			Val.put(COL_7, Pill_lunch);
			Val.put(COL_8, Pill_evening);
			Val.put(COL_9, Pill_beforebed);
			
			long rows = db.insert(TABLE_NAME, null, Val);
			db.close();
			return rows; //return rows inserted.
			} catch (Exception e) {
				return -1;
			}
		}
		
		//select data
				public String[] SelectDataCode(String Code) {
					try {
						String arrData[] = null;
						SQLiteDatabase db;
						db = this.getReadableDatabase(); // Read Data
						
						Cursor cursor = db.query(TABLE_NAME, new String[]{ "*" },
								"Code=?", 
								new String[] {String.valueOf(Code)}, null, null, null, null);
						if(cursor != null){
							if (cursor.moveToFirst()){
								arrData = new String[cursor.getColumnCount()];
								
								arrData[0] = cursor.getString(0);
								arrData[1] = cursor.getString(1);
								arrData[2] = cursor.getString(2);
								arrData[3] = cursor.getString(3);
								arrData[4] = cursor.getString(4);
								arrData[5] = cursor.getString(5);
								arrData[6] = cursor.getString(6);
								arrData[7] = cursor.getString(7);
								arrData[8] = cursor.getString(8);
								arrData[9] = cursor.getString(9);
							}
						}
						cursor.close();
						db.close();
						return arrData;
					} 
					catch (Exception e) {
					return null;
					 }
				}
				
				//select data
				public String[] SelectDataUser_accout(String User_accout) {
					try {
						String arrData[] = null;
						SQLiteDatabase db;
						db = this.getReadableDatabase(); // Read Data
						
						Cursor cursor = db.query(TABLE_NAME, new String[]{ "*" },
								"col2=?", 
								new String[] {String.valueOf(User_accout)}, null, null, null, null);
						if(cursor != null){
							if (cursor.moveToFirst()){
								arrData = new String[cursor.getColumnCount()];
								/***
								 *  0 = code
								 *  1 = Name
								 *  2 = User
								 */
								arrData[0] = cursor.getString(0);
								arrData[1] = cursor.getString(1);
								arrData[2] = cursor.getString(2);
								arrData[3] = cursor.getString(3);
								arrData[4] = cursor.getString(4);
								arrData[5] = cursor.getString(5);
								arrData[6] = cursor.getString(6);
								arrData[7] = cursor.getString(7);
								arrData[8] = cursor.getString(8);
								arrData[9] = cursor.getString(9);
							}
						}
						cursor.close();
						db.close();
						return arrData;
					} 
					
					catch (Exception e) {
					return null;
					 }

				}//select data
				public String[] SelectDataUser_password(String strpassword) {
					try {
						String arrData[] = null;
						SQLiteDatabase db;
						db = this.getReadableDatabase(); // Read Data
						
						Cursor cursor = db.query(TABLE_NAME, new String[]{ "*" },
								"user_password=?", 
								new String[] {String.valueOf(strpassword)}, null, null, null, null);
						if(cursor != null){
							if (cursor.moveToFirst()){
								arrData = new String[cursor.getColumnCount()];
						
								arrData[0] = cursor.getString(0);
								arrData[1] = cursor.getString(1);
								arrData[2] = cursor.getString(2);
								arrData[3] = cursor.getString(3);
								arrData[4] = cursor.getString(4);
								arrData[5] = cursor.getString(5);
								arrData[6] = cursor.getString(6);
								arrData[7] = cursor.getString(7);
								arrData[8] = cursor.getString(8);
								arrData[9] = cursor.getString(9);
							}
						}
						cursor.close();
						db.close();
						return arrData;
					} 
					
					catch (Exception e) {
					return null;
					 }

				}

		// Show All Data type
		public ArrayList<HashMap<String, String>> SelectAllData() {
			// TODO Auto-generated method stub
			
			 try {
				 ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
				 HashMap<String, String> map;
				 SQLiteDatabase db;
				 db = this.getReadableDatabase(); // Read Data
				 String strSQL = "SELECT  * FROM " + TABLE_NAME;
				 Cursor cursor = db.rawQuery(strSQL, null);
				 	if(cursor != null)
				 	{
				 	    if (cursor.moveToFirst()) {
				 	        do {
				 	        	map = new HashMap<String, String>();
				 	        	map.put(COLUMN_ID, cursor.getString(0));
					 	        map.put(COL_1, cursor.getString(1));
					 	        map.put(COL_2, cursor.getString(2));
					 	        map.put(COL_3, cursor.getString(3));
					 	        map.put(COL_4, cursor.getString(4));
					 	        map.put(COL_5, cursor.getString(5));
					 	        map.put(COL_6, cursor.getString(6));
					 	       	map.put(COL_7, cursor.getString(7));
					 	      	map.put(COL_8, cursor.getString(8));
					 	      	map.put(COL_9, cursor.getString(9));
					 	        MyArrList.add(map);
				 	        } while (cursor.moveToNext());
				 	    }
				 	}
				 	cursor.close();
				 	db.close();
					return MyArrList;
			 } catch (Exception e) {
			    return null;
			 }
		}
		
		// Update Data
		public long UpdateData(String Code, String Pill_name, String Pill_amount, String Pill_dose,String Pill_mode, String Pill_date, String Pill_morning, String Pill_lunch, String Pill_evening, String Pill_beforebed) {
			// TODO Auto-generated method stub
			 try {
				
				SQLiteDatabase db;
	     		db = this.getWritableDatabase(); // Write Data
	            ContentValues Val = new ContentValues();
	        	//Val.put("Code", Code);
				 Val.put(COL_1, Pill_name);
				 Val.put(COL_2, Pill_amount);
				 Val.put(COL_3, Pill_dose);
				 Val.put(COL_4, Pill_mode);
				 Val.put(COL_5, Pill_date);
				 Val.put(COL_6, Pill_morning);
				 Val.put(COL_7, Pill_lunch);
				 Val.put(COL_8, Pill_evening);
				 Val.put(COL_9, Pill_beforebed);
	     
	            long rows = db.update(TABLE_NAME, Val, " Code = ?",
	                    new String[] { String.valueOf(Code) });
	            
	     		db.close();
	     		return rows; // return rows updated.
					
			 } catch (Exception e) {
			    return -1;
			 }

		}
		
		// Delete Data
		public long DeleteData(String Code) {
			// TODO Auto-generated method stub
			 try {
				SQLiteDatabase db;
	     		db = this.getWritableDatabase(); // Write Data
	     		long rows = db.delete(TABLE_NAME, "Code = ?",new String[] { String.valueOf(Code) });
	     		db.close();
	     		return rows; // return rows deleted.
			 } catch (Exception e) {
			    return -1;
			 }

		}
		public void removeAll() 
		{ 
		    // db.delete(String tableName, String whereClause, String[] whereArgs); 
		    // If whereClause is null, it will delete all rows. 
		    SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
		    db.delete(TABLE_NAME, null, null);
		} 
		
		public Cursor readAllDATA() {
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT  * FROM " + TABLE_NAME;
			 Cursor cursor = db.rawQuery(strSQL, null);
			Cursor mCursor = db.query(TABLE_NAME, new String[] {
					COLUMN_ID, COL_1, COL_2 ,COL_3,COL_4,COL_5,COL_6,COL_7,COL_8,COL_9}, null, null, null,
					null, null);
			if (mCursor != null) {
				mCursor.moveToFirst();
					}
			return mCursor;
				}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	        // Re Create on method  onCreate
	        onCreate(db);
		}

	}