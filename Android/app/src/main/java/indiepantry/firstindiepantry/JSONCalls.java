/*
 * 
 *   FILE NAME:  JSONCalls.java
 *   
 *   DESCRIPTION:  This file contains a method for obtaining an ArrayList of Vendor objects.
 *                 These vendor objects are constructed from data in the REST API.
 *   
 *   REFERENCE:  
 *   
 *      DATE       BY       DESCRIPTION  
 *   ==========  =======   =============
 *   04/22/2017  Layne H.  Created createVendorList method.
 *   04/25/2017  Layne H.  Edited Vendor list creation
 *   
 */

package indiepantry.firstindiepantry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONCalls {
	
	/*
	 * Creates and returns ArrayList of all vendor objects in the REST API.
	 * 
	 * @return ArrayList of vendor objects from the REST API
	 * @param s: String url for the REST API webpage containing all vendors stored in the database.
	 * @throws IOException
	 */
	public static ArrayList<Vendor> getVendorList(String s) throws IOException {
		ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
		StringBuilder stringBuilder = new StringBuilder();
		URL url = new URL(s);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
	    
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String output;
		while ((output = br.readLine()) != null)
			stringBuilder.append(output);
		conn.disconnect();
		try{
			JSONArray myJson = new JSONArray(stringBuilder.toString());

			for(int i=0; i < myJson.length(); i++) {
				JSONObject jObj = (JSONObject) myJson.get(i);
				Vendor vendor = new Vendor(jObj.getString("nameV"), jObj.getString("emailV"), jObj.getString("paypal"));
				vendor.setLat(jObj.getString("latV"));
				vendor.setLon(jObj.getString("lonV"));
				vendor.setRating(jObj.getString("rating"));
				vendor.setPhone(jObj.getString("phone"));
				vendor.setPassword(jObj.getString("passwordV"));
				vendor.setApproved(jObj.getBoolean("approved"));
				vendorList.add(vendor);
			}
		}catch (JSONException e){
			return vendorList;
		}


		return vendorList;
	}
}
