/*
 * 
 *   FILE NAME:  JSONCalls.java
 *   
 *   DESCRIPTION:  This file contains all methods associated with calling the REST api and parsing JSON data.
 *   
 *   REFERENCE:  
 *   
 *     DATE       BY       DESCRIPTION  
 *   =========  =======   =============
 *   4/22/2017  Layne H.  Created createVendorList method.
 *   
 *   
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

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
    public static ArrayList<Vendor> createVendorList(String s) throws IOException {
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

        JSONArray myJson = new JSONArray(stringBuilder.toString());

        for(int i=0; i < myJson.length(); i++) {
            JSONObject jObj = (JSONObject) myJson.get(i);
            Vendor vendor = new Vendor(jObj.getString("nameV"), jObj.getString("addressV"), jObj.getString("emailV"));
            vendor.setRating(jObj.getString("ratingV"));
            vendor.setPhone(jObj.getString("phoneV"));
            vendor.setPassword(jObj.getString("passwordV"));
            vendor.setApproved(jObj.getBoolean("approved"));
            vendorList.add(vendor);
        }
        return vendorList;
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Vendor> vendors = createVendorList("http://env2.zs6znymmyc.us-east-1.elasticbeanstalk.com/api/vendors/");
        Arrays.toString(vendors.toArray());
    }
}