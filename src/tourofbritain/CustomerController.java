/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;

import java.io.IOException;
import java.sql.*;

import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.*;

import javafx.fxml.*;

import javafx.scene.*;

import javafx.scene.control.*;
import javafx.stage.*;

/**
 *
 * @author moneymolz
 */


public class CustomerController {
    
    //Variables for SQL commands
    Connection conn ;
    PreparedStatement pst;
    ResultSet rs;
    
    final ObservableList <Customer> data = FXCollections.observableArrayList();
    
    
    
    @FXML
    public void verifyCustomer(TextField username, PasswordField password, TextField idField, ComboBox titleBox, TextField firstName, TextField surnameField, TextField dobField, TextField emailField, TextField contactNoField, TextField doorNoField, TextField streetField, TextField postcodeField, ComboBox memberType) throws IOException, SQLException{
        ObservableList<String> titleList = FXCollections.observableArrayList("Mr", "Mrs", "Miss", "Ms");
        ObservableList<String> membershipType = FXCollections.observableArrayList ("Basic", "Premium", "Student+", "65+");
        
        //User is not allowed to edit username
        username.setEditable(false);
        username.setMouseTransparent(true);
        username.setFocusTraversable(false);
        
        
        memberType.setItems(membershipType);
        titleBox.setItems(titleList);
        
        try {
            String query = "SELECT * FROM Customers WHERE Username = ? AND Password = ?";
            conn = DatabaseConnections.DbConnectorCustomer();
            pst = conn.prepareStatement(query);
            
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                idField.setText(rs.getString("ID"));
                username.setText(rs.getString("Username"));
                password.setText(rs.getString("Password"));
                titleBox.setValue(rs.getString("Title"));
                firstName.setText(rs.getString("First Name"));
                surnameField.setText(rs.getString("Last Name"));
                dobField.setText(rs.getString("Date of Birth"));
                emailField.setText(rs.getString("Email"));
                contactNoField.setText(rs.getString("Contact Number"));
                doorNoField.setText(rs.getString("House Number"));
                streetField.setText(rs.getString("Street Name"));
                postcodeField.setText(rs.getString("Postcode"));
                
            }
            
            else{
                System.out.println("Login Unsuccessful");
            }
            
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    //Field Validation for the registry form
   public boolean validateUsername(TextField username){
         Pattern p = Pattern.compile("^[a-zA-Z0-9]{2,30}$");
        Matcher m = p.matcher(username.getText());
        if(m.find() && m.group().equals(username.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Username");
            alert.showAndWait(); 
            username.clear();
            
           
            return false;
        }
    }
    
     public boolean validatePassword(TextField password){
         Pattern p = Pattern.compile("((?=.*\\d)(?=.[a-z])(?=.*[A-Z]).{8,20})");
        Matcher m = p.matcher(password.getText());
        if(m.find() && m.group().equals(password.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one number one uppercase character and one lowercase character, minimum of 8 characters max of 20");
            alert.showAndWait(); 
            password.clear();
            
           
            return false;
        }
    }
     
      public boolean validateStreet(TextField street){
          Pattern p = Pattern.compile("^[a-zA-Z ]{2,31}$");
        Matcher m = p.matcher(street.getText());
        if(m.find() && m.group().equals(street.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Street");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Street");
            alert.showAndWait(); 
            street.clear();
            
           
            return false;
        }
    }
      
       public boolean validatePostcode(TextField postcode){
           Pattern p = Pattern.compile("^[a-zA-Z0-9 ]{5,8}$");
        Matcher m = p.matcher(postcode.getText());
        if(m.find() && m.group().equals(postcode.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Postcode");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Postcode");
            alert.showAndWait(); 
            postcode.clear();
            
           
            return false;
        }
    }
    
    
    //This will be used for contact number validation
    public boolean validateNumber(TextField number){
        Pattern p = Pattern.compile("^[0-9]{11}$");
        Matcher m = p.matcher(number.getText());
        if(m.find() && m.group().equals(number.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Number");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number");
            alert.showAndWait(); 
            number.clear();
            
           
            return false;
        }
        
    }
    
    //Door Number Validation
    public boolean validateDoorNo(TextField doorNo){
        Pattern p = Pattern.compile("^[0-9]{1,4}$");
        Matcher m = p.matcher(doorNo.getText());
        if(m.find() && m.group().equals(doorNo.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Door Number");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid door Number");
            alert.showAndWait(); 
            doorNo.clear();
            
           
            return false;
        }
        
    }
    
    //This will be used for first name validation 
    public boolean validateFirstName(TextField firstName){
        Pattern p = Pattern.compile("^[a-zA-Z]{2,30}$");
        Matcher m = p.matcher(firstName.getText());
        if(m.find() && m.group().equals(firstName.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid First Name");
            alert.showAndWait(); 
            firstName.clear();
            
           
            return false;
        }
        
    }
    
    //Field Validation for the registry form
   public boolean validateDob(TextField dateOfBirth){
         Pattern p = Pattern.compile("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$");
        Matcher m = p.matcher(dateOfBirth.getText());
        if(m.find() && m.group().equals(dateOfBirth.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Date of Birth");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Date of Birth");
            alert.showAndWait(); 
            dateOfBirth.clear();
            
           
            return false;
        }
    }
    
    
    //This will be used for email Validation
    public boolean validateEmail(TextField emailAddress){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailAddress.getText());
        if(m.find() && m.group().equals(emailAddress.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address");
            alert.showAndWait(); 
            emailAddress.clear();
            
           
            return false;
        }
        
    }
    
    //This will be used for last name validation 
    public boolean validateLastName(TextField lastName){
        Pattern p = Pattern.compile("^[a-zA-Z]{2,30}$");
        Matcher m = p.matcher(lastName.getText());
        if(m.find() && m.group().equals(lastName.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Surname");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Surname");
            alert.showAndWait(); 
            lastName.clear();
            
           
            return false;
        }
        
    }
    
   
   
   @FXML
    public boolean validateFields(PasswordField password, TextField firstName, TextField lastName, TextField dob, TextField emailAddress, TextField contactNoField, TextField doorNo, TextField street, TextField postcode, ComboBox title){
        
        if(firstName.getText() .isEmpty() | lastName.getText().isEmpty() | dob.getText().isEmpty()
            | emailAddress.getText().isEmpty() | doorNo.getText().isEmpty() | street.getText().isEmpty()
                | postcode.getText().isEmpty() | title.getSelectionModel().isEmpty()) {
            
            Alert fieldsAlert = new Alert(Alert.AlertType.WARNING);
            fieldsAlert.setTitle("Fill out all fields");
            fieldsAlert.setHeaderText(null);
            fieldsAlert.setContentText("Please fill out all parts of the registration form to complete sign-in");
            fieldsAlert.showAndWait();
            
            return false;
        }
        
        return true;
        
    }
    
    //Update a memebrship
    @FXML
    public void updateMembership(TextField idField, TextField firstnameField, TextField surnameField, TextField dobField, TextField emailField, TextField contactNoField, TextField doorNoField, TextField streetField, TextField postcodeField, ComboBox titleBox, ComboBox memberType, PasswordField password){
        
        try{
            //conn = person.DbConnector();
            String query = "UPDATE Customers SET 'First Name'=?, 'Last Name'=?, 'Date of Birth'=?, 'Email'=?, 'Contact Number'=?, 'House Number'=?, 'Street Name'=?, 'Postcode'=?, 'Title'=?, 'Membership Type'=?, 'Password'=? WHERE ID='"+idField.getText()+"' ";
            pst = conn.prepareStatement(query);
            
            pst.setString(1, firstnameField.getText());
            pst.setString(2, surnameField.getText());
            pst.setString(3, dobField.getText());
            pst.setString(4, emailField.getText());
            pst.setString(5, contactNoField.getText());
            pst.setString(6, doorNoField.getText());
            pst.setString(7, streetField.getText());
            pst.setString(8, postcodeField.getText());
            pst.setString(9, (String) titleBox.getValue());
            pst.setString(10, (String) memberType.getValue());
            pst.setString(11, password.getText());
            
            
            //validateFields();
            
            //This will set up an alert box for when the user has succesfully registered
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Your user details have been updated");
            alert.showAndWait();
            
            pst.execute();
            
            pst.close();
                
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }
        
        }
    
    @FXML
    public void clearFields(ComboBox title1, ComboBox memberType, TextField firstName, TextField lastName, TextField dob, TextField username, PasswordField password, TextField email, TextField number,TextField doorNo, TextField street, TextField postcode, TextField id){
        title1.setValue(null);
        memberType.setValue(null);
        firstName.clear();
        lastName.clear();
        dob.clear();
        username.clear();
        password.clear();
        email.clear();
        number.clear();
        doorNo.clear();
        street.clear();
        postcode.clear();
        id.clear();
        
    }
    
    //Method to delete a membership
    public void deleteMemership(TextField idField){
            try {
                String query = "DELETE FROM Customers WHERE ID = ?";
                pst = conn.prepareStatement(query);
                pst.setString(1, idField.getText());
                pst.executeUpdate();
                pst.close();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cancel Membership");
                alert.setHeaderText(null);
                alert.setContentText("Your membership has been cancelled");
                alert.showAndWait();
                
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }
    
    
    
 
    
}
