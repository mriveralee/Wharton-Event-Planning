/**
 * ************************ AttendeeActivity Class   ***********
 * Shows all of the attendees in a scrollView for a clicked global forum/event
 * Allows the user to see their name, position, and picture and then send a message.
 * Linked to the ContactMessageActivity class through this.
 * *************************************************************
 */


package edu.upenn.cis350;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import edu.upenn.cis350.R;  							// Remember to add this to all activity files
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

@SuppressWarnings("unused")
public class ScheduleActivity extends Activity {
	private String[] EVENT_TITLE;					// Holds our persons' names
	private String[] TIME;					// Holds references to our persons' pictures
	private String[] LOCATION;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	        setContentView(R.layout.schedule);
	        // Locate the TableLayout defined in our attendee.xml
	        TableLayout scheduleTable = (TableLayout)this.findViewById(R.id.scheduleTableLayout);	// Locate the TableLayout defined in our attendee.xml
	        
	        Bundle extras = getIntent().getExtras();
	        fillEventInfo(extras.getString("MAIN_EVENT_FULLSCHEDULE")); 																	// Fill Test NAMES & IMAGES array
	        for (int i =0; i < EVENT_TITLE.length; i++){													// Now add rows [Note: String name : NAMES]
	        	int bgColor;
	        	if(i % 2 == 0) bgColor = Color.argb(255, 30, 30, 30);								// Set transparency, red, green, blue | 0 = transparent, 255 = opaque
	        	else bgColor = Color.argb(255, 20, 20, 20);											// Two grays for dividers
	        	
	        	TableRow event = new TableRow(this);
	        	event.setBackgroundColor(bgColor);													// We divide different rows by alternating colors
	        	event.setPadding(5, 15, 5, -3);													// Positions images correctly inside row
	        	event.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));	
	        	//ImageView picture = new ImageView(this);											// Create a picture for the person
	        	//spicture.setImageResource(TIME[i]);												// Set the image of the person
	        	//person.addView(picture);															// Add person's picture to the row
	        	
	        	//Add event button
	        	ImageButton addEventButton = new ImageButton(this);														// Create Contact ImageButton in this view
	        	addEventButton.setImageResource(R.drawable.plus_button);												// Contact Button Image
	        	addEventButton.setOnClickListener(new EventInfoListener(EVENT_TITLE[i], TIME[i], LOCATION[i], this));	// Private Class Listen for Custon OnClick listener that passes all info and context for messaging
	        	event.addView(addEventButton);																			// Add Contact Button to row
	        	
	        	//Event name
	        	TextView eventNameView = new TextView(this);										// Create a TextView for a name
	        	eventNameView.setText(EVENT_TITLE[i]);												// set NameView to the person's name
	        	eventNameView.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
	        	eventNameView.setBackgroundColor(Color.TRANSPARENT);
	        	event.addView(eventNameView);														// Add Name view to this row
	        	
	        	//Location + Time
	        	TextView locationView = new TextView(this);
	        	locationView.setText(LOCATION[i] + " " + TIME[i]);	
	        	locationView.setTextSize((float) 8);
	        	locationView.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, 1));
	        	//locationView.setPadding(0, 1, 0, 0);
	        	event.addView(locationView);
	        	
	        	/*
	        	//Time
	        	TextView eventTimeLayout = new TextView(this);		// Linear Layout Positions the name and position appropriately
	        	eventTimeLayout.setText(TIME[i]);
	        	eventNameView.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
	        	eventNameView.setBackgroundColor(Color.TRANSPARENT);
	        	event.addView(eventTimeLayout);								// Add the linear layout to the person row  
	        	*/     	
	        	
	        	//Show more details button
	        	ImageButton showDetailsButton = new ImageButton(this);														// Create Show Details ImageButton in this view
	        	showDetailsButton.setImageResource(R.drawable.email);														// Show Details Button Image
	        	showDetailsButton.setOnClickListener(new EventInfoListener(EVENT_TITLE[i], TIME[i], LOCATION[i], this));	// Private Class Listen for Custon OnClick listener that passes all info and context for messaging
	        	event.addView(showDetailsButton);																			// Add Contact Button to row
	        	
	        	scheduleTable.addView(event, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));	        		
	        }      
	}
	
	/*|************ FILL NAMES AND IMAGES METHOD ***************|
	 *|     Method for adding Names to the array on Load		|
	 *|   Serves as a test until we get the SQL DB running 		|
	 *|*********************************************************|
	 */
	public void fillEventInfo(String eventName){
		
		// For MILAN2012
		if (eventName.equals("Milan2012")){
			this.setTitle("Milan 2012 - Full Schedule");
			EVENT_TITLE = new String[] {"Opening Plenary", "Coffee Break", "Concurrent Panel Discussion"};
			TIME = new String[] {"10:00am - 11:00am", "12:30pm - 1:00pm", "2:00pm - 3:30pm"};
			LOCATION = new String[]{"Palazzo Mezzanotte", "Roman Ruins", "Parterre Hall" };
		}
		else if (eventName.equals("Jakarta2012")){
			this.setTitle("Jakarta 2012 - Full Schedule");
			EVENT_TITLE = new String[] {"Breakfast", "Opening Plenary", "Keynote Speaker"};
			TIME = new String[] {"9:00am - 10:00am", "10:30am - 12:00pm", "1:00pm - 2:30pm"};
			LOCATION = new String[]{"Grand Hyatt Jakarta", "Grand Hyatt Jakarta", "Grand Hyatt Jakarta" };
		}
		else 
			finish();
		}	
	
	/* ************************ CONTACT LISTENER CLASS *********************************
	 * Private Inner Class for Passing Contact Information Through OnClickListener 
	 * **********************************************************************************/
	
	private class EventInfoListener implements OnClickListener{
		private String name;
		private String description;
		private String time;
		private Context context;
		
		public EventInfoListener(String _name, String _description, String _time, Context _context){
			name = _name;
			description = _description;
			time = _time;
			context = _context;
		}
		
		public void onClick(View v) {
			Intent j =  new Intent(context, ContactMessageActivity.class);
	    	j.putExtra("EVENT_NAME", name);
	    	j.putExtra("EVENT_DESCRIPTION", description);
	    	j.putExtra("EVENT_TIME", time);
			//System.out.println("CLICKED: PERSON- " +name+", "+ position +", " + imageID);
	    	startActivity(j);	
		}
	} // END CONTACT LISTENER INNER CLASS

	
} // End ATTENDEE ACTIVTY Class
