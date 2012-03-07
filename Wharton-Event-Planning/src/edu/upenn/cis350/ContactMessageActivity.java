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
	    Bundle extras = getIntent().getExtras();
	    name = extras.getString("CONTACT_NAME");
	    position = extras.getString("CONTACT_POSITION");
	    imageID = extras.getInt("CONTACT_IMAGEID");
	    TextView contact_name = (TextView)findViewById(R.id.contactName);
	    contact_name.setText(name);
	    TextView contact_position = (TextView)findViewById(R.id.contactPosition);
	    contact_position.setText(position);
	    ImageView contact_image = (ImageView)findViewById(R.id.contactImage);
	    contact_image.setImageResource(imageID);
	    
	}
	
	
	
	// SEND BUTTON CLICK
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
