package com.pennapps.pennmeet.helpers;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pennapps.pennmeet.R;

public class GroupAdapter extends ArrayAdapter<Group> {

    private final List<Group> items;
    private final Context callingContext;

    public GroupAdapter(Context context, int textViewResourceId, List<Group> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.callingContext = context;
    }
    
    @Override
    public View getView(int position, View view, ViewGroup parent) {
            View v = view;
            TextView name;
            TextView email;
            ImageView photo;
            
            final Group groupItem = items.get(position);
            
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater)callingContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.groups_list_item, null);
                v.setBackgroundResource(android.R.color.transparent);
            }
            
            if (groupItem != null) {
            		
                    //set photo
                	photo = (ImageView) v.findViewById(R.id.group_list_photo);
                    if (photo != null) {
                    	photo.setBackgroundDrawable(groupItem.getPhoto());
                        }
                         
                    //set name
                    name = (TextView) v.findViewById(R.id.group_list_name);
                    if (name != null){
                    	name.setText(groupItem.getTitle());
                   }
                    	  
                   	//set email
                   	email = (TextView) v.findViewById(R.id.group_list_email);
                   	if (email != null){
                   		email.setText(groupItem.getEmail());
                   	}
            
            }
            return v;
    }
}
