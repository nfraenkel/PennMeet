package com.pennapps.pennmeet.helpers;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pennapps.pennmeet.R;

public class RosterAdapter extends ArrayAdapter<User> {

    private final List<User> items;
    private final Context callingContext;

    public RosterAdapter(Context context, int textViewResourceId, List<User> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.callingContext = context;
    }
    
    @Override
    public View getView(int position, View view, ViewGroup parent) {
            View v = view;
            TextView name;
            TextView email;
            TextView year;
            
            final User userItem = items.get(position);
            
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater)callingContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.roster_list_item, null);
                v.setBackgroundResource(android.R.color.transparent);
            }
            
            if (userItem != null) {
            		
                    //set year
                	year = (TextView) v.findViewById(R.id.roster_year);
                    if (year != null) {
                    	year.setText(userItem.getYear());
                        }
                         
                    //set name
                    name = (TextView) v.findViewById(R.id.roster_name);
                    if (name != null){
                    	name.setText(userItem.getFirstName() + " " + userItem.getLastName());
                   }
                    	  
                   	//set email
                   	email = (TextView) v.findViewById(R.id.roster_email);
                   	if (email != null){
                   		email.setText(userItem.getEmail());
                   	}
            
            }
            return v;
    }
}
