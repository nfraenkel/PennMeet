package com.pennapps.pennmeet;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.bumpapi.BumpAPI;
import com.bumptech.bumpapi.BumpAPIListener;
import com.bumptech.bumpapi.BumpConnectFailedReason;
import com.bumptech.bumpapi.BumpConnection;
import com.bumptech.bumpapi.BumpDisconnectReason;
import com.pennapps.pennmeet.helpers.Group;
import com.pennapps.pennmeet.helpers.GroupAdapter;

public class BumpActivity extends Activity implements BumpAPIListener {
	
	static final int SELECT_IMAGE = 0;
	static final String BUMP_API_KEY = "513b4f47da6a49358cade5d254e82e0f";
	static final int BUMP_API_REQUEST_CODE = 1;
	static final int BUMP_RECEIVING = 2;
	static final int BUMP_SENDING = 3;
	private int currentBump;
	private Group testGroup;
	private Group testGroup2;
	private Group testGroup3;
	private Group testGroup4;
	private Group testGroup5;
	private GroupAdapter groupAdapter;
	private ArrayList<Group> groupList;
	private ListView groupListView;
	private ViewFlipper mFlipper;
	private String sendingGroup;
	private TextView header;
	private BumpConnection conn;
	private final Handler handler = new Handler();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bump);
        
        mFlipper				=	(ViewFlipper) findViewById(R.id.flipper);
        header					=	(TextView) findViewById(R.id.header);
        
        header.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        	}
        });
        
        setupGroupList();
        populateGroupList();
    } 
	 
    public void setupGroupList(){
    	groupListView		=	(ListView) findViewById(R.id.group_list);
    	testGroup				=	new Group("1234981234", "Penn Electric Racing", "N/A", "per@seas.upenn.edu", null, "Michael Cera", "William Henry", getResources().getDrawable(R.drawable.per));
    	testGroup2				=	new Group("1234123432", "PennApps", "N/A", "2011@pennapps.com", null, "Pulak Mittal", "Alexey Komissarouk", getResources().getDrawable(R.drawable.pennapps));
    	testGroup3				=	new Group("0713049371", "Extreme Chess Club", "We rock at Chess.", "chess@seas.upenn.edu", null, "Vladimir Belikov", "Utut Adianto", getResources().getDrawable(R.drawable.chess));
    	testGroup4				=	new Group("1206721212", "Penn Model Congress", "Hold annual high school conferences for model congress events", "pennmc@seas.upenn.edu", null, "Robert Robertson", "Andrew Stephenson", 
    											getResources().getDrawable(R.drawable.pennmc));
    	testGroup5				=	new Group("9612403397", "Muse", "N/A", "muse@wharton.upenn.edu", null, "Jon Hunstman", "Donny Trump", getResources().getDrawable(R.drawable.muse));
        groupList 			=	new ArrayList<Group>();
        groupAdapter		= 	new GroupAdapter(this, R.layout.groups_list_item, groupList);
        groupListView.setAdapter(groupAdapter);
        
        groupListView.setOnItemClickListener(new OnItemClickListener(){ 

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				sendingGroup = ((TextView) ((ViewGroup) ((ViewGroup) arg1).getChildAt(1)).getChildAt(0)).getText().toString();
//				clubPhoto = ((ViewGroup) arg1).getChildAt(0).getBackground();
				bump();
				mFlipper.setDisplayedChild(2);
//				setupEventPage();
			}
        	
        });
    }
    
    public void populateGroupList(){
    	groupAdapter.add(testGroup);
    	groupAdapter.add(testGroup2);
    	groupAdapter.add(testGroup3);
    	groupAdapter.add(testGroup4);
    	groupAdapter.add(testGroup5);
    }
       
    public void onClick(View v){
    	switch (v.getId()){
    		case R.id.share_qr_code:
    			Uri qrCode = Uri.parse("android.resource://com.pennapps.pennmeet/drawable/test_qr_code");
    			Intent share = new Intent(Intent.ACTION_SEND);
	    		share.setType("image/png");
	    		share.putExtra(Intent.EXTRA_STREAM, qrCode);
            	startActivity(Intent.createChooser(share, "Select Application For Sharing"));
    			break;
    		case R.id.group_bump_send:
    			mFlipper.setDisplayedChild(1);
    			currentBump = BUMP_SENDING;
    			break;
    		case R.id.group_bump_receive:
    			mFlipper.setDisplayedChild(2);
    			currentBump = BUMP_RECEIVING;
    			bump();
    			break;
    	}
    }
    
    public void bump(){
    	Intent bump = new Intent(this, BumpAPI.class); 
		bump.putExtra(BumpAPI.EXTRA_API_KEY, BUMP_API_KEY);
//		bump.putExtra(BumpAPI.EXTRA_USER_NAME, "John");
		startActivityForResult(bump, BUMP_API_REQUEST_CODE);
    }
    
    @Override
	public void onStop() {
		if (conn != null)
			conn.disconnect();

		super.onStop();
	}
    
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == BUMP_API_REQUEST_CODE) { 
    	if (resultCode == RESULT_OK) {
		    // Bump connected successfully, set this activity as its listener
		    conn = (BumpConnection) data.getParcelableExtra(BumpAPI.EXTRA_CONNECTION);
		    conn.setListener(this, handler);
		    if(currentBump == BUMP_SENDING){
		    	Log.e("Bump", "made it in the if statement, currentBump  = " + currentBump);
		    	try {conn.send(sendingGroup.getBytes("UTF-8"));}
		    	catch(Exception e){ }
		    	Toast.makeText(getApplication(), "Bump was successful!", Toast.LENGTH_SHORT).show();
		    }
		    
    	} 
    	else if (data != null) {
		    // Failed to connect, obtain the reason
		    BumpConnectFailedReason reason = (BumpConnectFailedReason) data.getSerializableExtra(BumpAPI.EXTRA_REASON);
		    Log.e("Bump", "reason for failure: " + reason.toString());
		    Toast.makeText(getApplication(), "Bump was not successful", Toast.LENGTH_SHORT).show();
    	}
    	startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
    }
    
    public void onBackPressed(){
    	super.onBackPressed();
    	startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
    
	@Override
	public void bumpDataReceived(byte[] chunk) {
		try {
			String data = new String(chunk, "UTF-8");
			Toast.makeText(getApplicationContext(), "Successfully added to " + chunk + "!" , Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.e("Bump Chat", "Failed to parse incoming data");
			e.printStackTrace();
		}

		Log.e("Bump", "receive");	
		
	}

	@Override
	public void bumpDisconnect(BumpDisconnectReason arg0) {
		
	}
    
}