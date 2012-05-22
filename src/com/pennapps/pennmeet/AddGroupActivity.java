package com.pennapps.pennmeet;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pennapps.pennmeet.helpers.Group;

public class AddGroupActivity extends Activity {
	
	static final int SELECT_IMAGE = 0;
	private ImageButton photo;
	private Button createGroupButton;
	private TextView header;
	private TextView title;
	private TextView president;
	private TextView vp;
	private TextView email;
	private TextView description;
	private BackEndConnection be;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        
        photo 					=	(ImageButton) findViewById(R.id.add_photo);
        createGroupButton		=	(Button) findViewById(R.id.create_group_button);
        header					=	(TextView) findViewById(R.id.header);
        title					=	(TextView) findViewById(R.id.add_name);
        president				=	(TextView) findViewById(R.id.add_president);
        vp						=	(TextView) findViewById(R.id.add_vice);
        email					=	(TextView) findViewById(R.id.add_email);
        description				=	(TextView) findViewById(R.id.add_description);
        
        header.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        	}
        });
        
        photo.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		selectPhoto();
        	}
        });
        
        createGroupButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		createGroup();
        		Toast.makeText(getApplicationContext(), "Your club profile was created!", Toast.LENGTH_LONG).show();
        		startActivity(new Intent(getApplicationContext(), GroupsActivity.class)); 
        	}
        });
    }
 
    public void createGroup(){
    	String id = Long.toHexString(Double.doubleToLongBits(Math.random()));
    	ArrayList<String> members = new ArrayList<String>();
    	members.add("empty_member");
    	Group newGroup = new Group(id, title.getText().toString(), description.getText().toString(), 
    			email.getText().toString(), members, vp.getText().toString(), 
    			president.getText().toString(), photo.getBackground());
    	be = new BackEndConnection();
    	be.putGroup(id, newGroup);
    	
    }
    
    public void selectPhoto(){
    	startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), SELECT_IMAGE);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == SELECT_IMAGE)
        if (resultCode == Activity.RESULT_OK) {
          Uri selectedImage = data.getData();
          photo.setImageURI(selectedImage);
        } 
    }
    
}