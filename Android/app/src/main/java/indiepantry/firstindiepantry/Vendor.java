/*
 * 
 *   FILE NAME:  Vendor.java
 *   
 *   DESCRIPTION:  This file contains the constructor and methods associated with the creation of vendor objects.
 *                 It corresponds to the "Vendor" table in the database.
 *   
 *   REFERENCE:  models.py
 *   
 *      DATE       BY       DESCRIPTION  
 *   ==========  =======   =============
 *   04/22/2017  Layne H.  All Constructor and Methods created.
 *   04/25/2017  Layne H.  Added paypal, lat, and lon attributes.
 *                         Removed address attribute.
 *   
 */

package indiepantry.firstindiepantry;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;

import org.json.JSONException;
import org.json.JSONObject;

public class Vendor {
	private String latitude;
	private String longitude;
	private String name;
	private String email;
	private String rating;
	private String paypal;
	private String phone;
	private String password;
	private Boolean approved;
	
	/*
	 * Creates a Vendor object.
	 * 
	 * @param name: String name being given to the vendor object.
	 *        email: String email being given to the vendor object.
	 *        paypal: String paypal address being given to the vendor object.
	*/
	public Vendor(String name, String email, String paypal) {
		String latitude;
		String longitude;
		this.name = name;
		this.email = email;
		String rating;
		String phone;
		this.paypal = paypal;
		String password;
		Boolean approved;
		Location location;
	}

	
	/*
         * Sets a latitude for the Vendor object.
         * 
         * @param rating: String latitude variable of the particular vendor.
         */
	public void setLat(String latitude) {
		this.latitude = latitude;
	}

	/*
         * Sets a longitude for the Vendor object.
         * 
         * @param rating: String longitude variable of the particular vendor.
         */
	public void setLon(String longitude) {
                this.longitude = longitude;
        }

	/*
	 * Sets a rating for the Vendor object.
	 * 
	 * @param rating: String variable of the particular vendor.
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	/*
	 * Sets a phone number for the Vendor object.
	 * 
	 * @param phone: String phone number of the particular vendor.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*
	 * Sets a password for the Vendor object.
	 * 
	 * @param password: String password of the particular vendor.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
         * Sets an approval status for the Vendor object.
         * 
         * @param rating: Boolean approved variable of the particular vendor.
         */
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
	/*
         * Obtains the latitude of a particular Vendor object.
         * 
         * @return latitude String variable
         */
	public String getLat() {
		return latitude;
	}
	
	/*
         * Obtains the longitude of a particular Vendor object.
         * 
         * @return longitude String variable
         */
	public String getLon() {
		return longitude;
	}

	/*
	 * Obtains the name of a particular Vendor object.
	 * 
	 * @return name string
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Obtains the email of a particular Vendor object.
	 * 
	 * @return email string
	 */
	public String getEmail() {
		return email;
	}
	
	/*
	 * Obtains the rating of a particular Vendor object.
	 * 
	 * @return rating String variable
	 */
	public String getRating() {
		return rating;
	}
	
	/*
	 * Obtains the phone number of a particular Vendor object.
	 * 
	 * @return phone number string
	 */
	public String getPhone() {
		return phone;
	}
        
	/*
         * Obtains the paypal address of a particular Vendor object.
         * 
         * @return paypal address string
         */
        public String getPaypal() {
                return paypal;
        }
 	
	/*
	 * Obtains the approval status of a particular Vendor object.
	 * 
	 * @return approved boolean variable
	 */
	public Boolean getApproved() {
		return approved;
	}

	public String toJson(){
        try{
            JSONObject myJson = new JSONObject();
            myJson.put("latV",location.getLatitude());
            myJson.put("lonV",location.getLongitude());
            myJson.put("emailV",email);
            myJson.put("passwordV",password);
            myJson.put("phone",phone);
            myJson.put("paypal","1");
            myJson.put("rating",rating);
            myJson.put("nameV",name);
            myJson.put("approved",true);
            return myJson.toString();
        }catch(JSONException e){
            return e.getMessage();
        }
    }

}
