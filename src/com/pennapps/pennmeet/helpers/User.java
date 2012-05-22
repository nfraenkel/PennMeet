package com.pennapps.pennmeet.helpers;

import java.util.Set;


public class User {
	private String userID;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String year;
	private Set<String> groups;
	private boolean isAdmin;
	
	public User(String userID, String name1, String name2, String number, boolean isAnAdmin, String emailAddress, String year, Set<String> group){
		email = emailAddress;
		firstName = name1;
		lastName = name2;
		phoneNumber = number;
		isAdmin = isAnAdmin;
		this.year = year;
		this.userID = userID;
		groups = group;
	}
	
	public void addGroup(String groupID){
		groups.add(groupID);
	}
	
	public void removeGroup(int groupNum){
		groups.remove(groupNum);
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public Boolean getIsAdmin(){
		return isAdmin;
	} 
	public Set<String> getGroups(){
		return groups;
	}
	public String getUserID(){
		return userID;
	}
	public void setEmail(String newMail){
		email = newMail;
	}
	
	public void setFirstName(String newName1){
		firstName = newName1;
	}
	
	public void setLastName(String newName2){
		lastName = newName2;
	}
	
	public void setPhoneNumber(String phoneNum){
		phoneNumber = phoneNum;
	}
	
	public void setBool(boolean newBool){
		isAdmin = newBool;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYear() {
		return year;
	}
}
