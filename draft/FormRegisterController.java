/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draft;

import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.stage.*;


public class FormRegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Connection conn = person.DbConnector();
    PreparedStatement pst;
    ResultSet rs;
    
    
    
    
    //This is declaring all the variables for the registration form
    @FXML
    private TextField firstName, lastName, dob, emailAddress, username, number, doorNo, street, postcode, idGen; 
    @FXML
    private PasswordField password;
    
    
    //This method is called when the text fields need to be cleared
    @FXML
    private void clearFields(){
        firstName.clear();
        lastName.clear();
        emailAddress.clear();
        dob.clear();
        username.clear();
        password.clear();
        number.clear();
        doorNo.clear();
        street.clear();
        postcode.clear();  
    }
    
    //Closes the window
    @FXML
    private Button closeRegButton;
    
    @FXML
    public void closeWindow(){
        Stage stage = (Stage) closeRegButton.getScene().getWindow();
        stage.close();
    }
    /*All the validation fields are below I will put a comment for when they are done
    */
    
    private boolean validateUsername(){
         Pattern p = Pattern.compile("^[a-zA-Z0-9]{2,30}$");
        Matcher m = p.matcher(username.getText());
        if(m.find() && m.group().equals(username.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Username");
            alert.showAndWait(); 
            username.clear();
            
           
            return false;
        }
    }
    
     private boolean validatePassword(){
         Pattern p = Pattern.compile("((?=.*\\d)(?=.[a-z])(?=.*[A-Z]).{8,20})");
        Matcher m = p.matcher(password.getText());
        if(m.find() && m.group().equals(password.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one number one uppercase character and one lowercase character, minimum of 8 characters max of 20");
            alert.showAndWait(); 
            password.clear();
            
           
            return false;
        }
    }
     
      private boolean validateStreet(){
          Pattern p = Pattern.compile("^[a-zA-Z ]{2,31}$");
        Matcher m = p.matcher(street.getText());
        if(m.find() && m.group().equals(street.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Street");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Street");
            alert.showAndWait(); 
            street.clear();
            
           
            return false;
        }
    }
      
       private boolean validatePostcode(){
           Pattern p = Pattern.compile("^[a-zA-Z0-9 ]{5,8}$");
        Matcher m = p.matcher(postcode.getText());
        if(m.find() && m.group().equals(postcode.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Postcode");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Postcode");
            alert.showAndWait(); 
            postcode.clear();
            
           
            return false;
        }
    }
    
    
    //This will be used for contact number validation
    private boolean validateNumber(){
        Pattern p = Pattern.compile("^[0-9]{11}$");
        Matcher m = p.matcher(number.getText());
        if(m.find() && m.group().equals(number.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Number");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number");
            alert.showAndWait(); 
            number.clear();
            
           
            return false;
        }
        
    }
    
    //Door Number Validation
    private boolean validateDoorNo(){
        Pattern p = Pattern.compile("^[0-9]{1,4}$");
        Matcher m = p.matcher(doorNo.getText());
        if(m.find() && m.group().equals(doorNo.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Door Number");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid door Number");
            alert.showAndWait(); 
            doorNo.clear();
            
           
            return false;
        }
        
    }
    
    //This will be used for first name validation 
    private boolean validateFirstName(){
        Pattern p = Pattern.compile("^[a-zA-Z]{2,30}$");
        Matcher m = p.matcher(firstName.getText());
        if(m.find() && m.group().equals(firstName.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid First Name");
            alert.showAndWait(); 
            firstName.clear();
            
           
            return false;
        }
        
    }
    
    //This will be used for email Validation
    private boolean validateEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailAddress.getText());
        if(m.find() && m.group().equals(emailAddress.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address");
            alert.showAndWait(); 
            emailAddress.clear();
            
           
            return false;
        }
        
    }
    
    //This will be used for last name validation 
    private boolean validateLastName(){
        Pattern p = Pattern.compile("^[a-zA-Z]{2,30}$");
        Matcher m = p.matcher(lastName.getText());
        if(m.find() && m.group().equals(lastName.getText() )){
           return true; 
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Surname");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid Surname");
            alert.showAndWait(); 
            lastName.clear();
            
           
            return false;
        }
        
    }
    
    //This function below validates the fields of the registration form so that if not all the 
    //required parts are filled in an alert box appears, user cannot regsiter until all fields complete
    
    @FXML
    private boolean validateFields(){
        
        if(firstName.getText() .isEmpty() | lastName.getText().isEmpty() | dob.getText().isEmpty()
            | emailAddress.getText().isEmpty() | username.getText().isEmpty()
                | password.getText().isEmpty() 
                | doorNo.getText().isEmpty() | street.getText().isEmpty()
                | postcode.getText().isEmpty() ) {
            
            Alert fieldsAlert = new Alert(AlertType.WARNING);
            fieldsAlert.setTitle("Fill out all fields");
            fieldsAlert.setHeaderText(null);
            fieldsAlert.setContentText("Please fill out all parts of the registration form to complete sign-in");
            fieldsAlert.showAndWait();
            
            return false;
        }
        
        return true;
        
    }
    
    //VALIDATION FIELDS ENDED
    
    //Function that refreshes the table when a new member is added
    /*@FXML
    public void refreshTable(){
        data.clear();
    }*/
   
    
    

    //The method that registers a user when the "Register" button is clicked
    @FXML
    public void registerUser(){
        
        if(validateFields() & validateNumber() & validateFirstName() & validateLastName() & validateDoorNo() & validateEmail() & validateStreet() & validateUsername() & validatePostcode() & validatePassword()){
        try{
            //conn = person.DbConnector();
            String query = "INSERT INTO Customer ('First Name', 'Last Name', 'Date of Birth', Email, Username, Password, 'Contact Number', 'House Number', 'Street Name', 'Postcode', 'ID') VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            
            pst.setString(1, firstName.getText());
            pst.setString(2, lastName.getText());
            pst.setString(3, dob.getText());
            //pst.setDate(0, x);
            pst.setString(4, emailAddress.getText());
            pst.setString(5, username.getText());
            pst.setString(6, password.getText());
            pst.setString(7, number.getText());
            pst.setString(8, doorNo.getText());
            pst.setString(9, street.getText());
            pst.setString(10, postcode.getText());
            pst.setString(11, idGen.getText());
            
            //validateFields();
            
            //This will set up an alert box for when the user has succesfully registered
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Complete!");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for registering with Tour of Britain");
            alert.showAndWait();
            
            pst.execute();
            
            pst.close();
            clearFields();
           
            
          
                
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }
        
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //validateNumber();

        idGen.setEditable(false);
        idGen.setMouseTransparent(true);
        idGen.setFocusTraversable(false);
        
        //To take methods from another class
        person class1 = new person();
        class1.randomGen();
        
        idGen.setText(class1.randomGen());
        
        
        
    }

    }
    
    
   
    

