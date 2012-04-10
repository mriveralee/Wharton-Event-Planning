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
		Person p = null;
		
		// Determine Add/Delete and perform proper function
		switch (view.getId()) {
		case R.id.db_person_add:	
			// Sample Data - Will connect remote DB later!
			String name = "John Smith";
			String pic = "";
			String position = "";
			// Save Person to DB
			p = dao.createPerson(name, pic, position);
			adapter.add(p);
			break;
		case R.id.db_person_delete:
			// Delete first person found, if they exist
			if(getListAdapter().getCount() > 0) {
				p = (Person) getListAdapter().getItem(0);
				dao.deletePerson(p);
				adapter.remove(p);
			}		
			break;
		}
		adapter.notifyDataSetChanged();	
	}
	
	// Respond to Event Add/Delete from UI
	public void onClickEventUpdate(View view) {
		ArrayAdapter<Event> adapter = (ArrayAdapter<Event>) getListAdapter();
		Event e = null;
		
		// Determine Add/Delete and perform proper function
		switch (view.getId()) {
		case R.id.db_event_add:	
			// Sample Data - Will connect remote DB later!
			String name = "Coffee with Microsoft";
			String time = "";
			String loc = "";
			long gid = 0;
			// Save Person to DB
			e = dao.createEvent(name, time, loc, gid);
			adapter.add(e);
			break;
		case R.id.db_event_delete:
			// Delete first person found, if they exist
			if(getListAdapter().getCount() > 0) {
				e = (Event) getListAdapter().getItem(0);
				dao.deleteEvent(e);
				adapter.remove(e);
			}		
			break;
		}
		adapter.notifyDataSetChanged();			
	}
	
	// Respond to GlobalEvent Add/Delete from UI
	public void onClickGEventUpdate(View view) {
		ArrayAdapter<GlobalEvent> adapter = (ArrayAdapter<GlobalEvent>) getListAdapter();
		GlobalEvent g = null;
		
		// Determine Add/Delete and perform proper function
		switch (view.getId()) {
		case R.id.db_gevent_add:	
			// Sample Data - Will connect remote DB later!
			String name = "Jakarta 2012";
			// Save Person to DB
			g = dao.createGlobalEvent(name);
			adapter.add(g);
			break;
		case R.id.db_gevent_delete:
			// Delete first person found, if they exist
			if(getListAdapter().getCount() > 0) {
				g = (GlobalEvent) getListAdapter().getItem(0);
				dao.deleteGlobalEvent(g);
				adapter.remove(g);
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