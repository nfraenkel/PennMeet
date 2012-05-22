package com.pennapps.pennmeet;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.pennapps.pennmeet.helpers.Group;
import com.pennapps.pennmeet.helpers.GroupAdapter;
import com.pennapps.pennmeet.helpers.RosterAdapter;
import com.pennapps.pennmeet.helpers.User;

public class GroupsActivity extends TabActivity {
	
	private TabHost tabHost;
	private Group testMGroup;
	private Group testMGroup2;
	private Group testMGroup3;
	private Group testMGroup4;
	private Group testMGroup5;
	private GroupAdapter groupMemberAdapter;
	private ArrayList<Group> groupMemberList;
	private ListView groupMemberListView;
	private GroupAdapter groupAdminAdapter;
	private ArrayList<Group> groupAdminList;
	private ListView groupAdminListView;
	private RosterAdapter rosterAdapter;
	private ArrayList<User> rosterList;
	private ListView rosterListView;
	private boolean isProfileOpen;
	private ImageView profilePhoto;
	private TextView profileName;
	private TextView profilePresident;
	private TextView profileVP;
	private TextView profileEmail;
	private TextView profileDescription;
	private TextView header;
	private ImageView qrView;
	private Button shareQR;
	private boolean isGroupFocused;
	private Dialog profileDialog;
	private Dialog rosterDialog;
	private User user;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups);
        
        isGroupFocused = false;
        
        setupTabs();
        
        setupGroupLists();
    	populateGroupLists();
    }

    public void onResume(){
    	super.onResume();
    }
    
    public void setupGroupLists(){
    	groupMemberListView		=	(ListView) findViewById(R.id.group_member_list);
    	groupAdminListView		=	(ListView) findViewById(R.id.group_admin_list);
    	testMGroup				=	new Group("1234981234", "Penn Electric Racing", "N/A", "per@seas.upenn.edu", null, "Michael Cera", "William Henry", getResources().getDrawable(R.drawable.per));
    	//testMGroup2				=	new Group("1234123432", "PennApps", "N/A", "2011@pennapps.com", null, "Pulak Mittal", "Alexey Komissarouk", getResources().getDrawable(R.drawable.pennapps));
    	//testMGroup3				=	new Group("0713049371", "Extreme Chess Club", "We rock at Chess.", "chess@seas.upenn.edu", null, "Vladimir Belikov", "Utut Adianto", getResources().getDrawable(R.drawable.chess));
    	//testMGroup4				=	new Group("1206721212", "Penn Model Congress", "Hold annual high school conferences for model congress events", "pennmc@seas.upenn.edu", null, "Robert Robertson", "Andrew Stephenson", 
    	//										getResources().getDrawable(R.drawable.pennmc));
      	//testMGroup5				=	new Group("9612403397", "Muse", "N/A", "muse@wharton.upenn.edu", null, "Jon Hunstman", "Donny Trump", getResources().getDrawable(R.drawable.muse));
        groupMemberList			=	new ArrayList<Group>();
        groupAdminList			=	new ArrayList<Group>();
        groupMemberAdapter		= 	new GroupAdapter(this, R.layout.groups_list_item, groupMemberList);
        groupAdminAdapter		= 	new GroupAdapter(this, R.layout.groups_list_item, groupAdminList);
        isProfileOpen			=	false;
        groupMemberListView.setAdapter(groupMemberAdapter);
        groupAdminListView.setAdapter(groupAdminAdapter);
        
        groupMemberListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				isProfileOpen = true;
				createProfileDialog(((TextView) ((ViewGroup) ((ViewGroup) arg1).getChildAt(1)).getChildAt(0)).getText().toString());
			}
        });
        
        groupAdminListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				isProfileOpen = true;
				generateQRCode();
			}
        });        
    }
    
    public void generateQRCode(){
    	LayoutInflater inflater 		= 	(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	final Dialog profileDialog	 	= 	new Dialog(this, R.style.GroupProfile);
		View pview 						= 	inflater.inflate(R.layout.event_qr_code, null, false);
		profileDialog.setContentView(pview);
    	
		
    	qrView			=	(ImageView) pview.findViewById(R.id.qr_view);
    	header			=	(TextView) pview.findViewById(R.id.header);
    	shareQR			=	(Button) pview.findViewById(R.id.share_qr_code);
        
        header.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        	}
        });
        
        shareQR.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Uri qrCode = Uri.parse("android.resource://com.pennapps.pennmeet/drawable/test_qr_code");
    			Intent share = new Intent(Intent.ACTION_SEND);
	    		share.setType("image/png");
	    		share.putExtra(Intent.EXTRA_STREAM, qrCode);
            	startActivity(Intent.createChooser(share, "Select Application For Sharing"));
        	}
        });
        
        profileDialog.show();
    }
    
    public void createProfileDialog(String clubName){
    	LayoutInflater inflater 		= 	(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	profileDialog	 				= 	new Dialog(this, R.style.GroupProfile);
		View pview 						= 	inflater.inflate(R.layout.group_profile, null, false);
		profileDialog.setContentView(pview);
		
		setupProfilePage(pview, clubName);
		
		profileDialog.setOnDismissListener(new OnDismissListener(){

			@Override
			public void onDismiss(DialogInterface dialog) {
				setupGroupLists();
		    	populateGroupLists();				
			}
		});
		
		header			=	(TextView) pview.findViewById(R.id.header);
        
        header.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        	}
        });
        profileDialog.show();
	}
    
    public void createRosterPopup(){
    	LayoutInflater inflater 		=	(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	rosterDialog					=	new Dialog(this, R.style.GroupProfile);
    	View pview						=	inflater.inflate(R.layout.roster_view, null, false);
    	rosterListView					=	(ListView) pview.findViewById(R.id.roster_listview);
    	rosterDialog.setContentView(pview);
    	
        rosterList				=	new ArrayList<User>();
    	rosterAdapter 			=	new RosterAdapter(this, R.layout.roster_list_item, rosterList);
    	rosterListView.setAdapter(rosterAdapter);
    	
    	populateRoster();
    	
    	header			=	(TextView) pview.findViewById(R.id.header);
        
        header.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        	}
        });
        
        rosterDialog.show();
    }
    
    public void populateRoster(){
    	if (rosterAdapter != null){
    		rosterAdapter.add(new User("1038316", "Jason", "Fraenkel", "3057749281", false, "jfraenk@seas.upenn.edu", "2014", new HashSet<String>()));
    		rosterAdapter.add(new User("1235124", "Mark", "Zucks", "3057749281", false, "mzucksk@seas.upenn.edu", "2013", new HashSet<String>()));
    		rosterAdapter.add(new User("6342642", "Drew", "Taylor", "3057749281", false, "dtay@seas.upenn.edu", "2012", new HashSet<String>()));
    		rosterAdapter.add(new User("1274314", "Boris", "Zhang", "3057749281", false, "bzhang@seas.upenn.edu", "2011", new HashSet<String>()));
    		rosterAdapter.add(new User("1708743", "Nick", "Franklin", "3057749281", false, "nickf@seas.upenn.edu", "2014", new HashSet<String>()));
    		rosterAdapter.add(new User("9397404", "Don", "Zaou", "3057749281", false, "donz@seas.upenn.edu", "2014", new HashSet<String>()));
    		rosterAdapter.add(new User("8723456", "Mike", "Generic", "3057749281", false, "fake@seas.upenn.edu", "2013", new HashSet<String>()));
    		rosterAdapter.add(new User("1423989", "Anne", "Droid", "3057749281", false, "anned@seas.upenn.edu", "2013", new HashSet<String>()));
    		rosterAdapter.add(new User("1349863", "Hugh", "Jass", "3057749281", false, "jass@seas.upenn.edu", "2015", new HashSet<String>()));
    		
    	}
    }
    
    public void setupProfilePage(View v, String clubName){
    	profilePhoto			=	(ImageView) v.findViewById(R.id.group_profile_photo);
    	profileName				=	(TextView) v.findViewById(R.id.group_profile_name);
    	profilePresident		=	(TextView) v.findViewById(R.id.group_profile_president);
    	profileVP				=	(TextView) v.findViewById(R.id.group_profile_vp);
    	profileEmail			=	(TextView) v.findViewById(R.id.group_profile_email);
    	profileDescription		=	(TextView) v.findViewById(R.id.group_profile_description);
    	Button roster 			=	(Button) v.findViewById(R.id.view_roster);
    	
    	GroupAdapter tempAdapter;
    	Group tempGroup = new Group("fail", "fail", "fail", "fail", null, "fail", "fail", null);
    	if (tabHost.getCurrentTabTag().equals("member"))
    		tempAdapter = groupMemberAdapter;
    	else
    		tempAdapter = groupAdminAdapter;

    	for (int i = 0; i < tempAdapter.getCount(); i++){
    			if (tempAdapter.getItem(i).getTitle().equals(clubName)){
    				tempGroup = tempAdapter.getItem(i);
    				continue;
    			}
    		}
    	
    	roster.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
//    			try{profileDialog.dismiss();}
//    			catch(NullPointerException e){ //we hate null pointers!
//    				}
    			createRosterPopup();
    		}
    	});
    	
    	profilePhoto.setBackgroundDrawable(tempGroup.getPhoto());
    	profileName.setText(tempGroup.getTitle());
    	profilePresident.setText(tempGroup.getPres());
    	profileVP.setText(tempGroup.getVP());
    	profileEmail.setText(tempGroup.getEmail());
    	profileDescription.setText(tempGroup.getDesc());
    }
    
    public void populateGroupLists(){
    	//try{
    	//	BackEndConnection.getUser()
    //	}
    //	for(int i=0;i<){}
//    	groupMemberAdapter.add(testMGroup);
//    	groupMemberAdapter.add(testMGroup4);
//    	groupMemberAdapter.add(testMGroup5);
//    	groupMemberAdapter.add(testMGroup2);
//    	groupMemberAdapter.add(testMGroup3);
//    	
//    	groupAdminAdapter.add(testMGroup);
//    	groupAdminAdapter.add(testMGroup2);
//    	groupAdminAdapter.add(testMGroup3);
    }
    
    public void setupTabs(){
    	Resources res = getResources();
        tabHost = getTabHost();
        TabHost.TabSpec spec;
        
        tabHost.setup();
     
        spec = tabHost.newTabSpec("member").setIndicator("Member",
                          res.getDrawable(R.drawable.group_bw)).setContent(R.id.groups_tab_one);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("administrator").setIndicator("Admin",
                          res.getDrawable(R.drawable.administrator)).setContent(R.id.groups_tab_two);
        tabHost.addTab(spec);
 
        tabHost.setCurrentTab(0);
        
        TabWidget tw = getTabWidget();
    	for (int i = 0; i < tw.getChildCount(); i++){
    		TextView text = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
    		text.setTextColor(Color.WHITE);
    	}
    }
    
    public void onClick(View v){
    	switch (v.getId()){
    		case R.id.create_group_button: 
    			startActivity(new Intent(getApplicationContext(), AddGroupActivity.class));
    			break;
    	}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.groups_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.delete_group:
	        
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
    
	
    public void onBackPressed(){
    	super.onBackPressed();
    	if (!isProfileOpen)
    		startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    	else
    		isProfileOpen = true;
    }
}