/**
 * ************* ContactMessageActivity Class   ***********
 * Connected to the AttendeeActivityClass- allows a user to send a message to a person who they just
 * selected via the AttendeeAcitivtyClass. The message is then sent to the person's email with the user's
 * name, email and message.
 * ******************************************************
 */

package edu.upenn.cis350;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
	
	
	
	// SEND BUTTON CLICK for sending the message
	public void onSendButtonClick(View view){
    	EditText senderNameBox = (EditText)findViewById(R.id.contactSenderName);
    	EditText senderEmailBox = (EditText)findViewById(R.id.contactSenderEmailBox);
		EditText messageBox = (EditText)findViewById(R.id.contactMessageBox);
    	String senderName = senderNameBox.getText().toString();
    	String senderEmail = senderEmailBox.getText().toString();
    	String message = messageBox.getText().toString();
    	System.out.println("TO: " + name + " At Position: " + position);								// Log Contact Info
    	System.out.println(" FROM: " + senderName + " Email: " +senderEmail + " Msg: " + message);		// Log Sender Info & Message
    
    }


}
