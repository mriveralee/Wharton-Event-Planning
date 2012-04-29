package edu.upenn.cis350;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;

/* Data Access Object (DAO) for local SQLite DB */
public class DataAccessObject {
	
	private SQLiteDatabase db;
	private MySQLiteHelper dbh;
	
	// Initialize arrays of column names for each table for later reference
	private String[] PERSON_COLS = { MySQLiteHelper.COLUMN_PID, MySQLiteHelper.COLUMN_PNAME, MySQLiteHelper.COLUMN_PIC, MySQLiteHelper.COLUMN_POSITION };
	private String[] EVENT_COLS = { MySQLiteHelper.COLUMN_EID, MySQLiteHelper.COLUMN_ENAME, MySQLiteHelper.COLUMN_TIME, MySQLiteHelper.COLUMN_LOC, MySQLiteHelper.COLUMN_GLOBAL_ID };
	private String[] GLOBAL_EVENT_COLS = { MySQLiteHelper.COLUMN_GID, MySQLiteHelper.COLUMN_GNAME };

	// Initialize DAO with a DB Helper
	public DataAccessObject(Context context) {
		dbh = new MySQLiteHelper(context);
	}

	// Open DB
	public void open() throws SQLException {
		db = dbh.getWritableDatabase();
	}

	// Close DB
	public void close() {
		dbh.close();
	}

	// Create Person in DB
	public Person createPerson(String name, String pic, String position) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_PNAME, name);
		values.put(MySQLiteHelper.COLUMN_PIC, pic);
		values.put(MySQLiteHelper.COLUMN_POSITION, position);
		long pid = db.insert(MySQLiteHelper.TABLE_PERSON, null, values);
		Cursor cursor = db.query(MySQLiteHelper.TABLE_PERSON,
				PERSON_COLS, MySQLiteHelper.COLUMN_PID + " = " + pid, null,
				null, null, null);
		cursor.moveToFirst();
		Person person = initPerson(cursor);
		cursor.close();
		return person;
	}

	// Helper: Initialize Person for creation
	private Person initPerson(Cursor cursor) {
		Person p = new Person();
		// 0/1 are magic constants (0 = id, 1 = name)
		p.setId(cursor.getLong(0));
		p.setName(cursor.getString(1));
		return p;
	}
	
	// Delete Person from DB
	public void deletePerson(Person p) {
		long id = p.getId();
		System.out.println("Person deleted with id: " + id);
		db.delete(MySQLiteHelper.TABLE_PERSON, MySQLiteHelper.COLUMN_PID
				+ " = " + id, null);
	}

	// List All People in DB
	public List<Person> getAllPeople() {
		List<Person> allPeople = new ArrayList<Person>();

		Cursor cursor = db.query(MySQLiteHelper.TABLE_PERSON,
				PERSON_COLS, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Person p = initPerson(cursor);
			allPeople.add(p);
			cursor.moveToNext();
		}
		
		cursor.close();
		return allPeople;
	}
	
	// Create Event in DB
	public Event createEvent(String name, String time, String loc, long global_id) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_PNAME, name);
		values.put(MySQLiteHelper.COLUMN_TIME, time);
		values.put(MySQLiteHelper.COLUMN_LOC, loc);
		values.put(MySQLiteHelper.COLUMN_GLOBAL_ID, global_id);
		long eid = db.insert(MySQLiteHelper.TABLE_EVENT, null, values);
		Cursor cursor = db.query(MySQLiteHelper.TABLE_EVENT,
				EVENT_COLS, MySQLiteHelper.COLUMN_EID + " = " + eid, null,
				null, null, null);
		cursor.moveToFirst();
		Event event = initEvent(cursor);
		cursor.close();
		return event;
	}
	
	// Helper: Initialize Event for creation	
	private Event initEvent(Cursor cursor) {
		Event e = new Event();
		// 0/1 are magic constants (0 = id, 1 = name)		
		e.setId(cursor.getLong(0));
		e.setName(cursor.getString(1));
		return e;
	}
	
	// Delete Event from DB
	public void deleteEvent(Event e) {
		long id = e.getId();
		System.out.println("Event deleted with id: " + id);
		db.delete(MySQLiteHelper.TABLE_EVENT, MySQLiteHelper.COLUMN_EID
				+ " = " + id, null);
	}
	
	// List All Events in DB
	public List<Event> getAllEvents() {
		List<Event> allEvents = new ArrayList<Event>();

		Cursor cursor = db.query(MySQLiteHelper.TABLE_EVENT,
				EVENT_COLS, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Event e = initEvent(cursor);
			allEvents.add(e);
			cursor.moveToNext();
		}
		
		cursor.close();
		return allEvents;
	}
	
	// Create Global Event in DB
	public GlobalEvent createGlobalEvent(String name) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_GNAME, name);
		long gid = db.insert(MySQLiteHelper.TABLE_GLOBAL_EVENT, null, values);
		Cursor cursor = db.query(MySQLiteHelper.TABLE_GLOBAL_EVENT,
				GLOBAL_EVENT_COLS, MySQLiteHelper.COLUMN_GID + " = " + gid, null,
				null, null, null);
		cursor.moveToFirst();
		GlobalEvent globalEvent = initGlobalEvent(cursor);
		cursor.close();
		return globalEvent;
	}
	
	// Helper: Initialize GlobalEvent for creation
	private GlobalEvent initGlobalEvent(Cursor cursor) {
		GlobalEvent g = new GlobalEvent();
		// 0/1 are magic constants (0 = id, 1 = name)		
		g.setId(cursor.getLong(0));
		g.setName(cursor.getString(1));
		return g;
	}
	
	// Delete GlobalEvent from DB (and all sub-events, to prevent orphaning data)
	public void deleteGlobalEvent(GlobalEvent g) {
		long id = g.getId();
		
		System.out.println("GlobalEvent deleted with id: " + id);
		db.delete(MySQLiteHelper.TABLE_GLOBAL_EVENT, MySQLiteHelper.COLUMN_GID
				+ " = " + id, null);
		
		System.out.println("All Events deleted with global_id: " + id);
		for(Event e : getAllEvents()) {
			if(e.getGId() == id) {
				// event is sub-event of global event being deleted
				deleteEvent(e);
			}
		}
	}
	
	// List All Global Events in DB
	public List<GlobalEvent> getAllGlobalEvents() {
		List<GlobalEvent> allGEvents = new ArrayList<GlobalEvent>();

		Cursor cursor = db.query(MySQLiteHelper.TABLE_GLOBAL_EVENT,
				GLOBAL_EVENT_COLS, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			GlobalEvent g = initGlobalEvent(cursor);
			allGEvents.add(g);
			cursor.moveToNext();
		}
		
		cursor.close();
		return allGEvents;
	}
	
}