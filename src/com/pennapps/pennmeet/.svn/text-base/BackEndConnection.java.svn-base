package com.pennapps.pennmeet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.cloudmine.api.CMAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.pennapps.pennmeet.helpers.Group;
import com.pennapps.pennmeet.helpers.User;


public class BackEndConnection {
	CMAdapter cmadapter = new CMAdapter();
	
	public BackEndConnection(){}
	
	public void putUser(String id, User user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserID", user.getUserID());
		map.put("Groups", new JSONArray(user.getGroups()));
		map.put("Email", user.getEmail());
		map.put("First Name", user.getFirstName());
		map.put("Last Name", user.getLastName());
		map.put("Phone Number", user.getPhoneNumber());
		map.put("Year", user.getYear());
		map.put("Admin Rights", user.getIsAdmin());
		cmadapter.updateValue("user-"+id, map);	
	}
	
	public void putGroup(String id, Group group){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GroupID", group.getGroupID());
		map.put("Title", group.getTitle());
		map.put("Description", group.getDesc());
		map.put("GroupEmail", group.getEmail());
		JSONArray jArray = new JSONArray();
		for (int i = 0; i < group.getMembers().size(); i++){
			jArray.put(group.getMembers().get(i));
		}
		
		map.put("Members", jArray);
		map.put("VP", group.getVP());
		map.put("President", group.getPres());
		cmadapter.updateValue("group-"+id, map);
	}
	
	public User getUser(String id) throws JSONException{
		JSONObject jsonObject = cmadapter.getValues(new String[] {"user-"+id});
		Log.e("BackEndConnection user test", jsonObject.toString());
		JSONArray groups = null;
		User u = null;
		Set<String> temp = new HashSet<String>();
		u = new User((((JSONObject) jsonObject.get("user-"+id)).getString("UserID")), //make a new user with the parameters from the cloud
				(((JSONObject) jsonObject.get("user-"+id)).getString("First Name")),
				(((JSONObject) jsonObject.get("user-"+id)).getString("Last Name")),
				(((JSONObject) jsonObject.get("user-"+id)).getString("Phone Number")),
				(((JSONObject) jsonObject.get("user-"+id)).getBoolean("Admin Rights")),
				(((JSONObject) jsonObject.get("user-"+id)).getString("Email")),
				(((JSONObject) jsonObject.get("user-"+id)).getString("Year")),
				(temp)); //set of groups is empty, at first
		
		groups = (((JSONObject) jsonObject.get("user-"+id)).getJSONArray("Groups"));
		if (groups != null){
			for (int i = 0; i < groups.length(); i++){
				u.addGroup(groups.getString(i)); // iterates through JSONArray and populates user's groups set
			}
		}
		return u;
	}
	
	public void addGroupToUser(String userId, String groupID) throws JSONException{
		JSONObject jsonObject = cmadapter.getValues(new String[] {"user-"+userId});
		Log.e("BackEndConnection addGroupuser test", jsonObject.toString());
		JSONArray groups = null;
		groups = (((JSONObject) jsonObject.get("user-"+userId)).getJSONArray("Groups"));
		groups = groups.put(groupID);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserID", (((JSONObject) jsonObject.get("user-"+userId)).getString("UserID")));
		map.put("Groups", groups);
		map.put("Email",(((JSONObject) jsonObject.get("user-"+userId)).getString("Email")));
		map.put("First Name", (((JSONObject) jsonObject.get("user-"+userId)).getString("First Name")));
		map.put("Last Name", (((JSONObject) jsonObject.get("user-"+userId)).getString("Last Name")));
		map.put("Phone Number", (((JSONObject) jsonObject.get("user-"+userId)).getString("Phone Number")));
		map.put("Year", (((JSONObject) jsonObject.get("user-"+userId)).getString("Year")));
		map.put("Admin Rights", (((JSONObject) jsonObject.get("user-"+userId)).getBoolean("Admin Rights")));
		cmadapter.updateValue("user-"+userId, map);
	}
	
	public Group getGroup(String id) throws JSONException{
		JSONObject jsonObject = cmadapter.getValues(new String[] {"group-"+id});
		ArrayList<String> temp = new ArrayList<String>();
		Log.e("BackEndConnection test", ((JSONObject) jsonObject.get("group-"+id)).getString("GroupID"));
		for (String string : temp){
			Log.e("BackEndConnection test", string);
		}
		JSONArray mems = null;
		Group g = new Group((((JSONObject) jsonObject.get("group-"+id)).getString("GroupID")),
				(((JSONObject) jsonObject.get("group-"+id)).getString("Title")),
				(((JSONObject) jsonObject.get("group-"+id)).getString("Description")),
				(((JSONObject) jsonObject.get("group-"+id)).getString("GroupEmail")),
				(temp),
				(((JSONObject) jsonObject.get("group-"+id)).getString("VP")),
				(((JSONObject) jsonObject.get("group-"+id)).getString("President")),
				null);
		 
		//Log.e("BackEndConnection array test", ((JSONArray) ((JSONObject) jsonObject.get("group-"+id)).get("Members")).getString(0));
		mems = (JSONArray) ((JSONObject) jsonObject.get("group-"+id)).get("Members");
		if (mems != null){
			for (int i = 0; i < mems.length(); i++){
				temp.add(mems.get(i).toString());
			}
		}
		return g;
	}
	
}