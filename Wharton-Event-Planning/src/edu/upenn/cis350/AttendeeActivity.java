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

public class AttendeeActivity extends Activity {
	private String[] NAMES;					// Holds our persons' names
	private int[] IMAGES;					// Holds references to our persons' pictures
	private String[] POSITIONS;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);	        
	    	setContentView(R.layout.attendee);
	        Bundle extras = getIntent().getExtras();
	        fillAttendeeInfo(extras.getString("MAIN_EVENT_ATTENDEES")); 							// Fill Test NAMES & IMAGES array
	        showAttendees();																		
	}
	
	// display all attendees of a given conference
	public void showAttendees() {
        // Locate the TableLayout defined in our attendee.xml
        TableLayout attendeesTable = (TableLayout)this.findViewById(R.id.attendeeTableLayout);	// Locate the TableLayout defined in our attendee.xml
        
		for (int i =0; i < NAMES.length; i++){													// Now add rows [Note: String name : NAMES]
        	int bgColor;
        	if(i % 2 == 0) bgColor = Color.argb(255, 30, 30, 30);								// Set transparency, red, green, blue | 0 = transparent, 255 = opaque
        	else bgColor = Color.argb(255, 20, 20, 20);											// Two grays for dividers
        	
        	TableRow person = new TableRow(this);
        	person.setBackgroundColor(bgColor);													// We divide different rows by alternating colors
        	person.setPadding(5, 15, 5, -3);													// Positions images correctly inside row
        	person.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));	
        	
        	ImageView picture = new ImageView(this);											// Create a picture for the person
        	picture.setImageResource(IMAGES[i]);												// Set the image of the person
        	person.addView(picture);															// Add person's picture to the row
        	
        	TextView personNameView = new TextView(this);										// Create a TextView for a name
        	personNameView.setText(" "+NAMES[i]);												// set NameView to the person's name
        	personNameView.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
        	personNameView.setBackgroundColor(Color.TRANSPARENT);
        	//person.addView(personNameView);														// Add Name view to this row
        	
        	TextView positionView = new TextView(this);
        	positionView.setText("  "+POSITIONS[i]);	
        	positionView.setTextSize((float) 8);
        	positionView.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, 1));
        	positionView.setPadding(0, 1,0, 0);
        	//person.addView(positionView);
        	
        	LinearLayout personInfoLayout = new LinearLayout(this);								// Linear Layout Positions the name and position appropriately
        	personInfoLayout.setOrientation(LinearLayout.VERTICAL);
        	personInfoLayout.addView(personNameView);											// Add our name to the linear layout
        	personInfoLayout.addView(positionView);												// Add our position to the linear layout
        	person.addView(personInfoLayout);													// Add the linear layout to the person row
        	
        	ImageButton contactButton = new ImageButton(this);									// Create Contact ImageButton in this view
        	contactButton.setImageResource(R.drawable.email);									// Contact Button Image
        	contactButton.setBackgroundColor(0);
        	contactButton.setOnClickListener(new ContactListener(NAMES[i], POSITIONS[i], IMAGES[i], this));	// Private Class Listen for Custon OnClick listener that passes all info and context for messaging
        	person.addView(contactButton);														// Add Contact Button to row
        	attendeesTable.addView(person, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));	        		
        }      
	}
	
	/*|************ FILL NAMES AND IMAGES METHOD ***************|
	 *|     Method for adding Names to the array on Load		|
	 *|   Serves as a test until we get the SQL DB running 		|
	 *|*********************************************************|
	 */
	public void fillAttendeeInfo(String eventName){
		
		// For MILAN2012
		if (eventName.equals("Milan2012")){
			this.setTitle("Milan 2012 - Attendees");
			NAMES = new String[] {"Alex Rodriguez", "Amy Tan", 
					"Bob Danel", "Brad Pittman", "James Wayne", 
					"Jessica Aly",  "Jasmin Banks", "Michael Murray",
					"Michelle Philmore", "Sarah Ryme"	 
			};
			IMAGES = new int[] {R.drawable.alex_rodriguez, R.drawable.amy_tan, 
					R.drawable.bob_danel, R.drawable.brad_pittman,
					R.drawable.james_wayne, R.drawable.jasmin_banks, R.drawable.jessica_aly,
					R.drawable.michael_murray, R.drawable.michelle_philmore, R.drawable.sarah_ryme
			};	
			POSITIONS = new String[]{"IT Director, Columbia University", "CEO of Enterprise Industries",
					"CFO of Coca Cola", "MBA Student, Wharton School",
					"Fundraising Chair, Grass Roots", "Professor of Marketing, Harvard University",
					"Founder of InstaPics", "Program Manager, Microsoft", "Promotions Specialist, Zynga", 
					"Entreprenuer"
			};
		}
		else if (eventName.equals("Jakarta2012")){
			this.setTitle("Jakarta 2012 - Attendees");
			NAMES = new String[] { "Bob Danel", "James Wayne", 
					"Jessica Aly",  "Jasmin Banks",
					"Michelle Philmore", "Sarah Ryme"	 
			};
			IMAGES = new int[] { R.drawable.bob_danel,
					R.drawable.james_wayne, R.drawable.jasmin_banks, R.drawable.jessica_aly,
					R.drawable.michelle_philmore, R.drawable.sarah_ryme
			};	
			POSITIONS = new String[]{
					"CFO of Coca Cola", "Fundraising Chair, Grass Roots", "Professor of Marketing, Harvard University",
					"Founder of InstaPics", "Promotions Specialist, Zynga", 
					"Entreprenuer"
			};
		}
		else 
			finish();
		}	
	
	/* ************************ CONTACT LISTENER CLASS *********************************
	 * Private Inner Class for Passing Contact Information Through OnClickListener 
	 * **********************************************************************************/
	
	private class ContactListener implements OnClickListener{
		private String name;
		private String position;
		private int imageID;
		private Context context;
		
		public ContactListener(String _name, String _position, int _imageID, Context _context){
			name = _name;
			position = _position;
			imageID = _imageID;
			context = _context;
		}
		
		public void onClick(View v) {
			Intent j =  new Intent(context, ContactMessageActivity.class);
	    	j.putExtra("CONTACT_NAME", name);
	    	j.putExtra("CONTACT_POSITION", position);
	    	j.putExtra("CONTACT_IMAGEID", imageID);
			//System.out.println("CLICKED: PERSON- " +name+", "+ position +", " + imageID);
	    	startActivity(j);	
		}
	} // END CONTACT LISTENER INNER CLASS

	
} // End ATTENDEE ACTIVTY Class
