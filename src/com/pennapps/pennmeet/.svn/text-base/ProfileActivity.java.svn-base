package com.pennapps.pennmeet;

import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.pennapps.pennmeet.helpers.User;

public class ProfileActivity extends Activity {
	
    private TextView header;
    private EditText nameEdit;
    private EditText emailEdit;
    private EditText phoneEdit;
    private EditText schoolEdit;
    private EditText majorEdit;
    private EditText yearEdit;
    private EditText notesEdit;
    private boolean isEdit = false;
    private RelativeLayout viewRel;
    private RelativeLayout editRel;
    private LinearLayout viewLin;
    private ScrollView editLin;
    private Button saveChanges;
    private String userID;
    private BackEndConnection be = new BackEndConnection();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        isEdit				=	getIntent().getBooleanExtra("isFirstTime", false);
        header				=	(TextView) findViewById(R.id.header);
        viewRel				=	(RelativeLayout) findViewById(R.id.top_layout);
        editRel				=	(RelativeLayout) findViewById(R.id.top_layout_edit);
        viewLin				=	(LinearLayout) findViewById(R.id.porfile_view);
        editLin				=	(ScrollView) findViewById(R.id.porfile_edit);
        saveChanges			=	(Button) findViewById(R.id.profile_save_changes_edit);
        nameEdit			=	(EditText) findViewById(R.id.profile_name_edit);
        emailEdit			=	(EditText) findViewById(R.id.profile_email_edit);
        phoneEdit			=	(EditText) findViewById(R.id.profile_phone_edit);
        schoolEdit			=	(EditText) findViewById(R.id.profile_school_edit);
        majorEdit			=	(EditText) findViewById(R.id.profile_major_edit);
        yearEdit			=	(EditText) findViewById(R.id.profile_year_edit);
        notesEdit			=	(EditText) findViewById(R.id.profile_notes_edit);
        
        
        SharedPreferences data = getSharedPreferences("pennmeet_user_id", MODE_PRIVATE);
        userID = data.getString("user_id", null);
        if(userID == null){
        	userID = Long.toHexString(Double.doubleToLongBits(Math.random()));
        	saveID();
        }
        
        if(isEdit)
        	openCorrectEdit();
        
        saveChanges.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		if(isEdit){
        			saveUser();
    	        	viewRel.setVisibility(View.VISIBLE);
    	        	editRel.setVisibility(View.INVISIBLE);
    	        	viewLin.setVisibility(View.VISIBLE);
    	        	editLin.setVisibility(View.INVISIBLE);
    	        	isEdit = false;
    	        }
        		Toast.makeText(getApplicationContext(), "Changes Saved!", Toast.LENGTH_SHORT).show();
        	}
        });
        
        header.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        	}
        });
    }
    
    public void saveUser(){
    	String firstName = " ";
    	String lastName = " ";
    	String number = " ";
    	String email = " ";
    	String year = " ";

    	if(nameEdit.getText().toString().contains(" ")){
    		firstName = nameEdit.getText().toString().substring(0, nameEdit.getText().toString().indexOf(" "));
    		lastName = nameEdit.getText().toString().substring(nameEdit.getText().toString().indexOf(" ")+ 1);
    	}
    	else if(nameEdit.getText().toString() != null)
    		firstName = nameEdit.getText().toString();
    	
    	if(phoneEdit.getText().toString() != null)
    		number = phoneEdit.getText().toString();
    	
    	if(emailEdit.getText().toString() != null)
    		email = emailEdit.getText().toString();
    	
    	if(yearEdit.getText().toString() != null)
    		year = yearEdit.getText().toString();
    	
    	HashSet<String> groupIDS = new HashSet<String>();
    	groupIDS.add("empty_group");
    	be.putUser(userID, new User(userID, firstName, lastName, number, false, email, year, groupIDS));
    	
    	saveID();
    }
    
	public void saveID(){
        SharedPreferences data = getSharedPreferences("pennmeet_user_id", MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("user_id", userID);
        editor.commit();
	}
    
    public void openCorrectEdit(){
        	viewRel.setVisibility(View.INVISIBLE);
        	editRel.setVisibility(View.VISIBLE);
        	viewLin.setVisibility(View.INVISIBLE);
        	editLin.setVisibility(View.VISIBLE);
        	isEdit = true;

    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.home_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.edit_profile:
	        if(!isEdit)
	        	openCorrectEdit();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}