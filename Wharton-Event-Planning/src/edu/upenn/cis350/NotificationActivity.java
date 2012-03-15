/**
 * ************* NotificationActivity Class   ***********
 * Serves as the activity class for notifications that are sent out for a Wharton Forumn/Event
 * Allows a users to view the notifications starting with the most recent. The user can then dismiss them
 * or leave them there for future reference.
 * ******************************************************
 */

package edu.upenn.cis350;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class NotificationActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.notification);
	    
	    // TODO: pull notification info from a DB
	    
	    // TODO: create notification items using Notification inner class
	    
	    // TODO: Dynamically add notifications to a ListView
	    
	    // TODO: Create Button Listeners for removing notifications
	    
	}	
	
	/** Private Inner Class that Serves as a Notification Item (set up will occur via the info pull from a SQL table)
	 *  @params
	 *  	forumName - the global event/forum name
	 *  	eventName - the name of the event (occurring the Forum) that the notification is for.
	 *  	time - time at which the notification was received
	 *  	message - the notification message (should we have a limit for this x chars?)
	 *  	inSchedule - true if this event is a member of 'My Schedule
	 *		Context - the context at which this notification is places (pointer to the NotificationActivity) used for button listeners 
	 *  */
	private class Notification{
		private String forumName;
		private String eventName;
		private String time;
		private String message;
		private boolean inSchedule;
		private Context context;
		
		
		public Notification(String _forumName, String _eventName, String _time, String _message, boolean _inSchedule, Context _context){
			forumName = _forumName;
			eventName = _eventName;
			time = _time;
			message = _message;
			inSchedule = _inSchedule;
			context = _context;
		}
		
		// TODO: Add GETTER/SETTER Methods
		// TODO: Add Remove Notification button
		
	}

}