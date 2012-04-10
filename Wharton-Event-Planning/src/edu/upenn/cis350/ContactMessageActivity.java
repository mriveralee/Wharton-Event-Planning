/**
 * ************* ContactMessageActivity Class   ***********
 * Connected to the AttendeeActivityClass- allows a user to send a message to a person who they just
 * selected via the AttendeeAcitivtyClass. The message is then sent to the person's email with the user's
 * name, email and message.
 * ******************************************************
 */

package edu.upenn.cis350;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactMessageActivity extends Activity {
	private String name;
	private String position;
	private int imageID;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.contactmessage);
	    
	    // Grab Intent's Extras (Stored Values)
	    Bundle extras = getIntent().getExtras();
	    name = extras.getString("CONTACT_NAME");									// Get the Contact's Name
	    position = extras.getString("CONTACT_POSITION");							// Get the Contact's Position
	    imageID = extras.getInt("CONTACT_IMAGEID");									// Get the Contact's ImageID
	    TextView contact_name = (TextView)findViewById(R.id.contactName); 			// Set up the name in a TextView
	    contact_name.setText(name);										
	    TextView contact_position = (TextView)findViewById(R.id.contactPosition);	// Set up the position in a TextView
	    contact_position.setText(position);	
	    ImageView contact_image = (ImageView)findViewById(R.id.contactImage);		// Set up the image in an ImageView
	    contact_image.setImageResource(imageID);	    
	}
	
	// SEND BUTTON CLICK for sending the message through email client
	public void onSendButtonClick(View view){
    	// Grab senderNameBox, senderEmailBox, messageBox
		EditText senderNameBox = (EditText)findViewById(R.id.contactSenderName);
    	EditText senderEmailBox = (EditText)findViewById(R.id.contactSenderEmailBox);
		EditText messageBox = (EditText)findViewById(R.id.contactMessageBox);
		
    	// Grab senderName, senderEmail, message
    	String senderName = senderNameBox.getText().toString();
    	String senderEmail = senderEmailBox.getText().toString();
    	String message = messageBox.getText().toString();

    	// test if all of the values are valid to send the message
    	boolean isValidSend = isValidSend(senderName, senderEmail, message);
    	
    	// Added Client for sending email | if validSend then send else don't.
    	if(isValidSend) {
    		sendMessage(senderName, senderEmail, message);
	    }
	}
	
	private boolean isValidSend(String name, String email, String message) {
		if(name == null || name.equals("")) {
			Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
			return false;
		} else if(email == null || email.equals("")) {
    		Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_SHORT).show();
    		return false;
		} else if(message == null || message.equals("")) {
    		Toast.makeText(this, "Please enter a message.", Toast.LENGTH_SHORT).show();
    		return false;
		} // implicit else
			return true;
	}
	
	private void sendMessage(String senderName, String senderEmail, String message) {
    	final Intent i = new Intent(android.content.Intent.ACTION_SEND);
    	i.setType("text/plain");
    	i.putExtra(android.content.Intent.EXTRA_EMAIL  , new String[]{"rimic@wharton.upenn.edu"});
    	i.putExtra(android.content.Intent.EXTRA_SUBJECT, ("[Wharton Events] Contact From " + senderName));
    	i.putExtra(android.content.Intent.EXTRA_TEXT   , message);
    	i.setData(Uri.parse("mailto:"+senderEmail)); // or just "mailto:" for blank
    	//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
    	i.setType("message/rfc822");
    	try {
        	//startActivity(i);
    		startActivity(Intent.createChooser(i, "Send mail..."));	    	}
    	catch (android.content.ActivityNotFoundException ex) {
    	    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
    	}		
	}
}
