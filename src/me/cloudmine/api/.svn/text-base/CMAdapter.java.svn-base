package me.cloudmine.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.util.Log;

public class CMAdapter {
	private static String API_SERVER = "http://nosslapi.cloudmine.me";
	private static String APP_ID = "f3d1baf8555c44a48e40f5d2ae63a52b";
	private static String API_KEY = "c60c64cd954f48d78dfb7fba04da1f1b";

	public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize);
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }


	public JSONObject getValues(){
		return getValues(null);	
	}
	
	public JSONObject getValues(String[] keys){
     	// make request to API_SERVER/v1/app/APP_ID/text/
		String uri = API_SERVER + "/v1/app/" + APP_ID + "/text/";
		
		if(keys != null){
			uri += "?keys=" + join(keys, ',');
		}
		
		RawRESTClient client = new RawRESTClient();
		client.setHeader("X-CloudMine-ApiKey", API_KEY);

		if( client.makeRequest(RESTClient.GET, uri) == null ){
			return null;
		}
		
		if( client.getStatusCode() >= 300 ){
			Log.d("JSON", "GET call returned HTTP code: " + client.getStatusCode());
        	return null;
		}

		String content = client.getBody();
		if(content == null){
			return null;
		}

		JSONObject response; 
		try {
			response = new JSONObject(content);
			JSONObject success = response.getJSONObject("success");

			return success;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ContentValues jsonToContentValues(JSONObject json){
		ContentValues values = new ContentValues(json.length());
		Iterator<String> keys = json.keys();
		while(keys.hasNext()){
			String key = keys.next();
			try {
				values.put(key, json.getString(key));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return values;
	}
	
	public Map contentValuesToMap(ContentValues value){
		Map m = new HashMap<String, Object>();
		for(Entry<String, Object> e : value.valueSet()){
			m.put(e.getKey(), e.getValue());
		}
		return m;
	}
	
	public String updateValue(String key, Map values){
		JSONObject value = new JSONObject(values);
		return updateValue(key, value);
	}
	
	public String updateValue(String key, ContentValues values){
		JSONObject value = new JSONObject(contentValuesToMap(values));
		return updateValue(key, value);
	}
	
	public String updateValue(String key, JSONObject value){
		JSONObject dataobj = new JSONObject();
		try {
			dataobj.put(key, value);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String data= "";
		try {
			data = dataobj.toString(4);
			System.out.println("Putting value: " + data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String uri = API_SERVER + "/v1/app/" + APP_ID + "/text/";
		
		RawRESTClient client = new RawRESTClient();
		client.setHeader("X-CloudMine-ApiKey", API_KEY);
		client.setHeader("content-type", "application/json");
		client.makeRequest(RESTClient.POST, uri, data);
		if(client.getResponse() == null){
			return null;
		}

		Log.d("JSON", "PUT call returned HTTP code: " + client.getStatusCode() + " " + client.getStatusMessage());

		String content = client.getBody();
		if(content == null){
			return null;
		}
		Log.d("JSON", "Content: " + content);

		if(client.getStatusCode() >= 300){
			Log.d("JSON", "HTTP content: " + content);
			return null;
		}

		JSONObject response; 
		try {
			response = new JSONObject(content);
			JSONObject success = response.getJSONObject("success");

			String key_ret = success.keys().next().toString();
			return key_ret;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
        return null;
	}
	
	public void deleteKeys(){
		deleteKeys(null);	
	}
	
	public void deleteKeys(String[] keys){
		String uri = API_SERVER + "/v1/app/" + APP_ID + "/data/";
		
		if(keys != null){
			uri += "?keys=" + join(keys, ',');
		}
		
		RawRESTClient client = new RawRESTClient();
		client.setHeader("X-CloudMine-ApiKey", API_KEY);
		if( client.makeRequest(RESTClient.DELETE, uri) == null ){
			return;
		}

		Log.d("JSON", "DELETE call returned HTTP code: " + client.getStatusCode());
        	
	}
}
