package Model;

/***********************************************************************
 * Module:  Contact.java
 * Author:  HuynhChau
 * Purpose: Defines the Class Contact
 ***********************************************************************/

import java.util.*;

/** @pdOid 0d378724-332e-486e-9250-4788cecc82ce */
public class Contact {
   /** @pdOid afddd4dc-8148-4b33-97cb-240f039cc451 */
   private String fullname;
   /** @pdOid e1fa71ab-93a1-4df8-9fca-5e41be61517e */
   private String email;
   /** @pdOid 04f82c05-ce48-4fe0-be24-c5188fb6b029 */
   private String phone;
   /** @pdOid 5d0e059a-f712-425a-8a70-22e2a8444ec1 */
   /** @pdOid 0da5114d-006b-420f-9196-37e714f441fb */
   private String content;
   /** @pdOid 8d06a334-b0ec-4e77-89ef-c1ecfde5e7da */
   private String contactDate;
   
   private int state;
   /** @pdOid cc51e46b-ef36-4149-84ef-f9b5bd0c43cd */
   
   public void submit_contact() {
      // TODO: implement
   }
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getContactDate() {
	return contactDate;
}
public void setContactDate(String contactDate) {
	this.contactDate = contactDate;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}