package edu.upenn.cis350;
import edu.upenn.cis350.R;  // Remember to add this to all activity files
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WhartonEventMainActivity extends Activity {
   
	public static final int ACTIVITY_CreateAttendee = 1;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
    }
    
    // Launch Activity for Attendee Listing for Milan 2012
    public void onAttendeeButtonClick(View view){
    	Intent i = new Intent(this, AttendeeActivity.class);
    	startActivity(i);
    	
    }
}