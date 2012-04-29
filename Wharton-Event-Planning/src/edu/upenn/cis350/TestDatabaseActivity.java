package edu.upenn.cis350;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class TestDatabaseActivity extends ListActivity {
	private DataAccessObject dao;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.database);

		dao = new DataAccessObject(this);
		dao.open();

		List<Person> allPeople = dao.getAllPeople();
		List<Event> allEvents = dao.getAllEvents();
		List<GlobalEvent> allGEvents = dao.getAllGlobalEvents();

		ArrayAdapter<Person> peopleAdapter = new ArrayAdapter<Person>(this,
				android.R.layout.simple_list_item_1, allPeople);
		setListAdapter(peopleAdapter);
	}

	// Respond to Person Add/Delete from UI
	public void onClickPersonUpdate(View view) {
		ArrayAdapter<Person> adapter = (ArrayAdapter<Person>) getListAdapter();
		Person person = null;
		
		// Determine Add/Delete and perform proper function
		switch (view.getId()) {
		case R.id.db_person_add:	
			// Sample Data - Will connect remote DB later!
			String name = "John Smith";
			String pic = "";
			String position = "";
			// Save Person to DB
			person = dao.createPerson(name, pic, position);
			adapter.add(person);
			break;
		case R.id.db_person_delete:
			// Delete first person found, if they exist
			if(getListAdapter().getCount() > 0) {
				person = (Person) getListAdapter().getItem(0);
				dao.deletePerson(person);
				adapter.remove(person);
			}		
			break;
		}
		adapter.notifyDataSetChanged();	
	}
	
	// Respond to Event Add/Delete from UI
	public void onClickEventUpdate(View view) {
		ArrayAdapter<Event> adapter = (ArrayAdapter<Event>) getListAdapter();
		Event event = null;
		
		// Determine Add/Delete and perform proper function
		switch (view.getId()) {
		case R.id.db_event_add:	
			// Sample Data - Will connect remote DB later!
			String name = "Coffee with Microsoft";
			String time = "";
			String loc = "";
			long gid = 0;
			// Save Person to DB
			event = dao.createEvent(name, time, loc, gid);
			adapter.add(event);
			break;
		case R.id.db_event_delete:
			// Delete first person found, if they exist
			if(getListAdapter().getCount() > 0) {
				event = (Event) getListAdapter().getItem(0);
				dao.deleteEvent(event);
				adapter.remove(event);
			}		
			break;
		}
		adapter.notifyDataSetChanged();			
	}
	
	// Respond to GlobalEvent Add/Delete from UI
	public void onClickGEventUpdate(View view) {
		ArrayAdapter<GlobalEvent> adapter = (ArrayAdapter<GlobalEvent>) getListAdapter();
		GlobalEvent g_event = null;
		
		// Determine Add/Delete and perform proper function
		switch (view.getId()) {
		case R.id.db_gevent_add:	
			// Sample Data - Will connect remote DB later!
			String name = "Jakarta 2012";
			// Save Person to DB
			g_event = dao.createGlobalEvent(name);
			adapter.add(g_event);
			break;
		case R.id.db_gevent_delete:
			// Delete first person found, if they exist
			if(getListAdapter().getCount() > 0) {
				g_event = (GlobalEvent) getListAdapter().getItem(0);
				dao.deleteGlobalEvent(g_event);
				adapter.remove(g_event);
			}		
			break;
		}
		adapter.notifyDataSetChanged();			
	}
	
	@Override
	protected void onResume() {
		dao.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		dao.close();
		super.onPause();
	}

}