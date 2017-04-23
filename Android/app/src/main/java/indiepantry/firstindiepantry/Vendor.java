/*
 * 
 *   FILE NAME:  Vendor.java
 *   
 *   DESCRIPTION:  This file contains the constructor and methods associated with the creation of vendor objects.
 *                 It corresponds to the "Vendor" table in the database.
 *   
 *   REFERENCE:  models.py
 *   
 *     DATE       BY       DESCRIPTION  
 *   =========  =======   =============
 *   4/22/2017  Layne H.  All Constructor and Methods created.
 *   
 *   
 */

package indiepantry.firstindiepantry;

public class Vendor {
	private String address;
	private String name;
	private String email;
	private String rating;
	private String phone;
	private String password;
	private Boolean approved;
	
	/*
	 * Creates a Vendor object.
	 * 
	 * @param name: String name being given to the vendor object.
	 *        address: String address being given to the vendor object.
	 *        email: String email being given to the vendor object.
	 */
	public Vendor(String name, String address, String email) {
		this.address = address;
		this.name = name;
		this.email = email;
		String rating;
		String phone;
		String password;
		Boolean approved;
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
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
	/*
	 * Obtains the address of a particular Vendor object.
	 * 
	 * @return address string
	 */
	public String getAddress() {
		return address;
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
	 * Obtains the approval status of a particular Vendor object.
	 * 
	 * @return approved boolean variable
	 */
	public Boolean getApproved() {
		return approved;
	}
}
