package com.pennapps.pennmeet;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.pennapps.pennmeet.helpers.Group;
import com.pennapps.pennmeet.helpers.IntentIntegrator;
import com.pennapps.pennmeet.helpers.IntentResult;
import com.pennapps.pennmeet.helpers.User;

public class ScanActivity extends Activity {
	
	private TextView header;
	private String scannedID;
	private Group newGroup;
	private User user;
	BackEndConnection be = new BackEndConnection();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        IntentIntegrator.initiateScan(this);
        
        header				=	(TextView) findViewById(R.id.header);
        
        if (header != null){
	        header.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v){
	        		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
	        	}
	        });
        }
    }
    
    public void onBackPressed(){
    	super.onBackPressed();
    	startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	 IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	 if (scanResult != null) {
    		 try{
    			 Log.e("ScanActivity", scanResult.getContents());
    			 scannedID = scanResult.getContents();
    			 getGroupFromID();
    			 addGrouptoUser();
    		 }
    		 catch(Exception e){
    			 Log.e("ScanActivity Exception", "Scan result = " + e.getMessage());
    		 }
    	   }
    	 startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    	  }
    
    public void addGrouptoUser(){
    	SharedPreferences data = getSharedPreferences("pennmeet_user_id", MODE_PRIVATE);
    	String userID = data.getString("user_id", null);
    	Log.e("ScanActivity", "addGrouptoUserCalled");
    	
    	if (userID != null){
    		try {
				user = be.getUser(userID);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ScanActivity user JSONException", e.getMessage());
				Toast.makeText(getApplicationContext(), "Unable to Add Group!", Toast.LENGTH_SHORT).show();
			}
    	}
    	if(user != null && newGroup != null)
			try {
				be.addGroupToUser(userID, newGroup.getGroupID());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ScanActivity add group to user exception", "exception: " + e.getMessage() + ",  groupid = " + newGroup.getGroupID());
			}
    	
			try {
				user = be.getUser(userID);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("ScanActivity user final JSONException", e.getMessage());
			}
			Log.e("ScanActivity after group added", user.getGroups().toString());
    	
    }
    
    public void getGroupFromID(){
    	if (be != null && scannedID != null){
    		try {
				newGroup = be.getGroup(scannedID);
				Log.e("ScanActivity", "new group title = " + newGroup.getTitle());
			} catch (JSONException e) {
				Log.e("ScanActivity JSONException", e.getMessage());
				Toast.makeText(getApplicationContext(), "Group Not Found!", Toast.LENGTH_SHORT).show();
			}
    		}
    	}
}