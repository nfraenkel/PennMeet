package com.pennapps.pennmeet.helpers;

import java.util.ArrayList;

import org.json.JSONException;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.pennapps.pennmeet.BackEndConnection;
public class Group {
	private String groupID;
	private String title;
	private String description;
	private String groupEmail;
	private ArrayList<String> members;
	private String vicePres;
	private String Pres;
	private Drawable photo;
	
	private BackEndConnection bconnection = new BackEndConnection();
	private QREncoder qrEnc;
	
	public Group(String groupID, String groupTitle, String summary, String listServ, ArrayList<String> all_members, String vp, String pres, Drawable photo){
		this.groupID = groupID;
		title = groupTitle;
		description = summary;
		groupEmail = listServ;
		members = all_members;
		vicePres = vp;
		Pres = pres;
		this.photo = photo;
		
		encodeQR();
	}
	
	private String generateID(){
		Log.e("Group,java", "GroupID generated = " + groupID);
		return Long.toHexString(Double.doubleToLongBits(Math.random()));
	}
	
	private void encodeQR(){
		qrEnc = new QREncoder(groupID);
		try {
			//qrEnc.encode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Group Exception", e.getMessage());
		}
	}
	
	public void regenerateQRCode(){
		qrEnc = new QREncoder(groupID);
		try {
			qrEnc.encode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Group Exception", e.getMessage());
		}
	}
	
	public void setPhoto(Drawable photo){
		this.photo = photo;
	}
	
	public Drawable getPhoto(){
		return photo;
	}
	
	public void addMem(String userId) throws JSONException{
		Group thisGroup = (bconnection.getGroup(groupID));
		thisGroup.members.add(userId);
		bconnection.putGroup(groupID, thisGroup);
		((User) bconnection.getUser(userId)).addGroup(groupID);
	}
	//REWORK REMOVE SINCE IT'S A HASHMAP NOW NOT A HASHSET.  WE NEED TO MAP FOR EASY RETRIEVAL OF DATA.
	public void removeMember(String key){
		if(members.contains(key)){
			members.remove(key);
			
		}
		members.remove(key);
		//key.removeGroup(groupID);
	}
	
	private void getDataFromServer(){
		//fill in once we've created backend connection to the hashmap
	}
	
	public String getPres(){
		return Pres;
	}
	public ArrayList<String> getMembers(){
		ArrayList<String> test = new ArrayList<String>();
		test.add("hello");
		test.add("hello2");
		return test;
	}
	public String getGroupID(){
		return groupID;
	}
	public String getDesc(){
		return description;
	}
	
	public void setDesc(String desc){
		description = desc;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String newTitle){
		title = newTitle;
	}
	
	public String getEmail(){
		return groupEmail;
	}
	
	public void setEmail(String newEmail){
		groupEmail = newEmail;
	}
	
	public String getVP(){
		return vicePres;
	}
	
	public void setVP(String newVP){
		vicePres = newVP;
	}
	
	
	
}
