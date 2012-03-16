/**
 * ************* MyScheduleActivity Class   ***********
 * Serves as the activity class for notifications that are sent out for a Wharton Forumn/Event
 * Allows a users to view the notifications starting with the most recent. The user can then dismiss them
 * or leave them there for future reference.
 * ******************************************************
 */

package edu.upenn.cis350;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class MyScheduleActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.myschedule);
	    this.setTitle("My Events");
	    
	    // TODO: connect to DB
	    
	}	
	
	/** Private Inner Class that Serves as a MySchedule Item (set up will occur via the info pull from a SQL table)
	 *  @params
	 *  	eventName - the name of the event (occurring the Forum) that the notification is for.
	 *  	time - time at which the notification was received
	 *  	message - the notification message (should we have a limit for this x chars?)
	 *		Context - the context at which this notification is placed (pointer to the MyScheduleActivity) used for button listeners 
	 *  */
	private class MySchedule {
		private String eventName;
		private String time;
		private String message;
		private Context context;
		
		
		public MySchedule(String _eventName, String _time, String _message, Context _context){
			eventName = _eventName;
			time = _time;
			message = _message;
			context = _context;
		}
		
		// TODO: Add GETTER/SETTER Methods
		// TODO: Add Remove Notification button
		
	}

}