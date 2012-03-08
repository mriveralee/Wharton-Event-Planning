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
	 
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.findViewById(R.id.milan12_attendees).setOnClickListener(new AttendeeButtonListener("Milan2012", this));		// Set button for  'Who's attending' milan2012
        this.findViewById(R.id.jakarta12_attendees).setOnClickListener(new AttendeeButtonListener("Jakarta2012", this));	// Set button listener for jakarta2012
    }
    
    

    // Launch Activity for Attendee Listing for Milan 2012
    public void onAttendeeButtonClick(View view){
    	Intent i = new Intent(this, AttendeeActivity.class);
    	i.putExtra("MAIN_EVENT_ATTENDEES", "Jakarta2012" );
    	i.putExtra("MAIN_EVENT_ATTENDEES", "Milan2012" );
    	startActivity(i);
    }
    
    /*********** PRIVATE INNER CLASS FOR ATTENDEE BUTTON LISTENER ***********/
    private class AttendeeButtonListener implements OnClickListener{
    	private String eventName;
    	private Context context;
    	
    	AttendeeButtonListener(String _eventName, Context _context){
    		eventName = _eventName;
    		context = _context;
    	}
    	
		public void onClick(View v) {
			Intent i = new Intent(context, AttendeeActivity.class);
	    	i.putExtra("MAIN_EVENT_ATTENDEES", eventName );
	    	startActivity(i);
		}
    } // End AttendeeButtunListener Class
    
} // End Wharton Event Main Activity