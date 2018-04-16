/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author moneymolz
 */
public class Customer {
    
    private final SimpleStringProperty id, title, firstName, surname, dob, email, contactNo, doorNo, street, postcode;
    
    public Customer(String id1, String title1, String fname, String lname, String dofb, String email1, String phoneNo, String houseNo, String street1, String postcode1){
        this.id = new SimpleStringProperty(id1);
        this.title = new SimpleStringProperty(title1);
        this.firstName = new SimpleStringProperty(fname);
        this.surname = new SimpleStringProperty(lname);
        this.dob = new SimpleStringProperty(dofb);
        this.email = new SimpleStringProperty(email1);
        this.contactNo = new SimpleStringProperty(phoneNo);
        this.doorNo = new SimpleStringProperty(houseNo);
        this.street = new SimpleStringProperty(street1);
        this.postcode = new SimpleStringProperty(postcode1);
        
        
    }

    Customer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//Get and set methods for things in the table
    public String getId() {
        return id.get();
    }
    
    public void setId(String id1) {
        id.set(id1);
    }
    
    public String getTitle() {
        return title.get();
    }
    
    public void setTitle(String title1) {
        title.set(title1);
    }
    //bbcxvb

    public String getFirstName() {
        return firstName.get();
    }
    
    public void setFirstName(String fname) {
        firstName.set(fname);
    }
    
     public String getSurname() {
        return surname.get();
    }
    
    public void setSurname(String lname) {
        surname.set(lname);
    }
    
    public String getDob() {
        return dob.get();
    }
    
    public void setDob(String dofb) {
        surname.set(dofb);
    }

    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String email1) {
        email.set(email1);
    }

    public String getContactNo() {
        return contactNo.get();
    }
    
    public void setContactNo(String phoneNo) {
        contactNo.set(phoneNo);
    }

    public String getDoorNo() {
        return doorNo.get();
    }
    
    public void setDoorNo(String houseNo) {
        doorNo.set(houseNo);
    }

    public String getStreet() {
        return street.get();
    }
    
    public void setStreet(String street1) {
        street.set(street1);
    }

    public String getPostcode() {
        return postcode.get();
    }
    
    public void setPostcode(String postcode1) {
        postcode.set(postcode1);
    }

    
}
