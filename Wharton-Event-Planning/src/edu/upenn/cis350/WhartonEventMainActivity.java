/**
 * ************* WhartonEventMainActivity Class   ***********
 * The Home Screen of Wharton Events currently going on. Allows a user to see their
 * event notifications, schedule (a list of events they have added), and the current forums/global events and 
 * their mini-events. It also permits view the users who have decided to attend the global events and send messaages to them
 * ******************************************************
 */

package edu.upenn.cis350;
import edu.upenn.cis350.R;  // Remember to add this to all activity files
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class WhartonEventMainActivity extends Activity {
   
	public static final int ACTIVITY_CreateAttendee = 1;
	public static final int ACTIVITY_CreateEvent = 2; 
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.findViewById(R.id.milan12_attendees).setOnClickListener(new AttendeeButtonListener("Milan2012", this));		// Set button for  'Who's attending' milan2012
        this.findViewById(R.id.jakarta12_attendees).setOnClickListener(new AttendeeButtonListener("Jakarta2012", this));	// Set button listener for jakarta2012
        this.findViewById(R.id.milan12_fullSchedule).setOnClickListener(new FullScheduleButtonListener("Milan2012", this));		
        this.findViewById(R.id.jakarta12_fullSchedule).setOnClickListener(new FullScheduleButtonListener("Jakarta2012", this));
    }
    
    // My Notifications Button Listener
    public void onClickMyNotificationsButton(View v){
    	Intent i = new Intent(this, NotificationActivity.class);
		startActivity(i);
    }
    
    // My Schedule Button Listener
    public void onClickMyScheduleButton(View v){
    	Intent i = new Intent(this, MyScheduleActivity.class);
		startActivity(i);
    }
    
    // Test Database Butotn Listener
    public void onClickTestDBButton(View v){
    	Intent i = new Intent(this, TestDatabaseActivity.class);
		startActivity(i);
    }
    
    /*********** PRIVATE INNER-CLASS FOR ATTENDEE BUTTON LISTENER ******************
     * Allows each attendee list to have a custom button listener that will send the name of the event
     * that a user wishes to see the attendees for.
     * *****************************************************************************/
    private class AttendeeButtonListener implements OnClickListener{
    	private String eventName;
    	private Context context;
    	
    	// Constructor
    	AttendeeButtonListener(String _eventName, Context _context){
    		eventName = _eventName;
    		context = _context;
    	}
    	
    	// Basic onClick function
		public void onClick(View v) {
			Intent i = new Intent(context, AttendeeActivity.class);
	    	i.putExtra("MAIN_EVENT_ATTENDEES", eventName );
	    	startActivity(i);
		}
    } // End AttendeeButtonListener Class
    
    private class FullScheduleButtonListener implements OnClickListener{
    	private String eventName;
    	private Context context;
    	
    	// Constructor
    	FullScheduleButtonListener (String _eventName, Context _context){
    		eventName = _eventName;
    		context = _context;
    	}
    	
    	// Basic onClick function
		public void onClick(View v) {
			Intent i = new Intent(context, GlobalScheduleActivity.class);
	    	i.putExtra("MAIN_EVENT_FULLSCHEDULE", eventName );
	    	startActivity(i);
		}
    } // End FullScheduleButtonListener Class
    
} // End Wharton Event Main Activity