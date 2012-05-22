package com.pennapps.pennmeet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends Activity {
	private boolean isFirstTime = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences data = getSharedPreferences("pennmeet_isfirstime", MODE_PRIVATE);
        isFirstTime = data.getBoolean("isFirstTime", true);
        
        if(isFirstTime){
        	Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        	intent.putExtra("isFirstTime", true);
        	startActivity(intent);
        	Toast.makeText(getApplicationContext(), "Please create your profile", Toast.LENGTH_SHORT).show();
        	saveIsFirstTime();
        }
    }
    
    public void onPause(){
    	super.onPause();
    	saveIsFirstTime();
    }
    
    public void saveIsFirstTime(){
    	SharedPreferences data = getSharedPreferences("pennmeet_isfirstime", MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putBoolean("isFirstTime", false);
        editor.commit();
    }
    
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dashboard_groups:
            	startActivity(new Intent(getApplicationContext(), GroupsActivity.class)); 
            	break;
            case R.id.dashboard_scan:
            	startActivity(new Intent(getApplicationContext(), ScanActivity.class));
            	break;
            case R.id.dashboard_bump:
            	startActivity(new Intent(getApplicationContext(), BumpActivity.class));
            	break;
            case R.id.dashboard_profile:
            	startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            	break;
        }
    }
    
    public void onBackPressed(){
    	super.onBackPressed();
    	moveTaskToBack(true);
    }
    
}