package edu.upenn.cis350;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Person Table
	public static final String TABLE_PERSON = "Person";
	public static final String COLUMN_PID = "_id";
	public static final String COLUMN_PNAME = "name";
	public static final String COLUMN_PIC = "pic";
	public static final String COLUMN_POSITION = "position";
	
	// Event Table
	public static final String TABLE_EVENT = "Event";
	public static final String COLUMN_EID = "_id";
	public static final String COLUMN_ENAME = "name";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_LOC = "loc";
	public static final String COLUMN_GLOBAL_ID = "global_id"; // foreign key to reference global event
	
	// GlobalEvent Table
	public static final String TABLE_GLOBAL_EVENT = "GlobalEvent";
	public static final String COLUMN_GID = "_id";
	public static final String COLUMN_GNAME = "name";
	
	// Database Information
	private static final String DATABASE_NAME = "wharton_it.db";
	private static final int DATABASE_VERSION = 1;

	// SQL to create Person table
	private static final String PERSON_CREATE = "create table "
			+ TABLE_PERSON + " ("
			+ COLUMN_PID + " integer primary key autoincrement, " 
			+ COLUMN_PNAME + " text not null, "
			+ COLUMN_PIC + " text not null, "
			+ COLUMN_POSITION + " text not null"
			+ ");";
	
	// SQL to create Event table
	private static final String EVENT_CREATE = "create table "
			+ TABLE_EVENT + " ("
			+ COLUMN_EID + " integer primary key autoincrement, " 
			+ COLUMN_ENAME + " text not null, "
			+ COLUMN_TIME + " text not null, "
			+ COLUMN_LOC + " text not null, "
			+ COLUMN_GLOBAL_ID + " integer"
			+ ");";
	
	// SQL to create GlobalEvent table
	private static final String GLOBAL_EVENT_CREATE = "create table "
			+ TABLE_GLOBAL_EVENT + " ("
			+ COLUMN_GID + " integer primary key autoincrement, " 
			+ COLUMN_GNAME + " text not null"
			+ ");";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// Create all tables
		database.execSQL(PERSON_CREATE);
		database.execSQL(EVENT_CREATE);
		database.execSQL(GLOBAL_EVENT_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		// Drop all tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GLOBAL_EVENT);		
		// Re-create database and tables
		onCreate(db);
	}

}