/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;

/**
 *
 * @author moneymolz
 */

import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;




public class OperatorController {
    
    //Setting the variables
    final ObservableList <Customer> data = FXCollections.observableArrayList(); 
    final ObservableList options = FXCollections.observableArrayList();
    
    FilteredList<Customer> filteredList = new FilteredList<>(data, e-> true);
    
    @FXML
    TableView table1;
    //@FXML 
    //private ListView customerList;
    
    Connection conn ;
    PreparedStatement pst;
    ResultSet rs;
    
    
    //This method checks whether the connection to the database is successful
    public void CheckConnectionOperator(){
        conn = DatabaseConnections.DbConnectorEmployees();
        if(conn == null){
            System.out.println("Connection not successful");
            System.exit(1);  
        }
        else{
            System.out.println("Connection successful");
        }
    }
    
    
    //This verifies the login details if the details don't match or aren't in the database the user will not be allowed to login
    @FXML
    public void verifyEmployee(TextField username, PasswordField password) throws IOException{
        try{
            //conn = person.DbConnector();
            String change = "SELECT * FROM Employees WHERE Username=? AND Password=?";
            conn = DatabaseConnections.DbConnectorEmployees();
            pst = conn.prepareStatement(change);
            
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();
            
            //if password is correct membership window is opened 
            if(rs.next()){
              
                System.out.println("Login Successful");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Tour of Britain - Membership Management");
        
  
        
                stage.setScene(new Scene(root2));
        
                /*stage.setOnCloseRequest(e -> {
                System.out.println("You have been logged out");
                stage.close();
                }); */
        
                stage.show();
                }
            
          
                else{
                System.out.println("Login Unsuccessful");
            }
            
            username.clear();
            password.clear();
            pst.close();
            rs.close();
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }
    }
    
    
    @FXML
    public void customerList(ListView customerList){
        options.clear();
         try {
            String query = "SELECT * FROM Customers";
            conn = DatabaseConnections.DbConnectorCustomer();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                options.add(rs.getString("ID")); 
              
                
                customerList.setItems(options);
            }
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void customerListClicked(ListView customerList, TextField idField, ComboBox titleBox, TextField firstnameField, TextField surnameField, TextField dobField, TextField emailField, TextField contactNoField, TextField doorNoField, TextField streetField, TextField postcodeField, ComboBox membership){
        ObservableList<String> titleList = FXCollections.observableArrayList("Mr", "Mrs", "Miss", "Ms");
        ObservableList<String> membershipType = FXCollections.observableArrayList ("Basic", "Premium", "Student+", "65+");
    
        membership.setItems(membershipType);
        titleBox.setItems(titleList);
        
        customerList.setOnMouseClicked(e ->{
             try {
            String query = "SELECT * FROM Customers WHERE ID = ?";
            conn = DatabaseConnections.DbConnectorCustomer();
            pst = conn.prepareStatement(query);
            //this.data1 = FXCollections.observableArrayList();
            pst.setString(1, (String) customerList.getSelectionModel().getSelectedItem() );
            rs = pst.executeQuery();
            
            while(rs.next()){
                idField.setText(rs.getString("ID"));
                titleBox.setValue(rs.getString("Title"));
                firstnameField.setText(rs.getString("First Name"));
                surnameField.setText(rs.getString("Last Name"));
                dobField.setText(rs.getString("Date of Birth"));
                emailField.setText(rs.getString("Email"));
                contactNoField.setText(rs.getString("Contact Number"));
                doorNoField.setText(rs.getString("House Number"));
                streetField.setText(rs.getString("Street Name"));
                postcodeField.setText(rs.getString("Postcode"));
                membership.setValue(rs.getString("Membership Type"));
            }
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             
            
        });
    }
    
    //Method to delete a membership
    public void deleteMemership(TextField idField){
            try {
                String query = "DELETE FROM Customers WHERE ID = ?";
                pst = conn.prepareStatement(query);
                pst.setString(1, idField.getText());
                pst.executeUpdate();
                pst.close();
                
                System.out.println("User has been deleted");
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //clearFields();
            //refreshTable(table1);
            //customerList(customerList);
    }
    
    //Clear the Customer membership text fields
    @FXML
    public void clearFields(TextField idField, TextField firstnameField, TextField surnameField, TextField dobField, TextField emailField, TextField contactNoField, TextField doorNoField, TextField streetField, TextField postcodeField, ComboBox titleBox, ComboBox memberType){
        idField.clear();
        firstnameField.clear();
        surnameField.clear(); 
        dobField.clear(); 
        emailField.clear(); 
        contactNoField.clear(); 
        doorNoField.clear();
        streetField.clear();
        postcodeField.clear();
        titleBox.setValue(null);
        memberType.setValue(null);
    }
    
    
    //Update a memebrship
    @FXML
    public void updateMembership(TextField idField, TextField firstnameField, TextField surnameField, TextField dobField, TextField emailField, TextField contactNoField, TextField doorNoField, TextField streetField, TextField postcodeField, ComboBox titleBox, ComboBox memberType){
        
        try{
            //conn = person.DbConnector();
            String query = "UPDATE Customers SET 'First Name'=?, 'Last Name'=?, 'Date of Birth'=?, 'Email'=?, 'Contact Number'=?, 'House Number'=?, 'Street Name'=?, 'Postcode'=?, 'Title'=?, 'Membership Type'=?  WHERE ID='"+idField.getText()+"' ";
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
    
    // Use to show and refresh table
     @FXML
    public void refreshTable(TableView table1){
        data.clear();
        try{
            String query = "SELECT * FROM Customers ";
            conn = DatabaseConnections.DbConnectorCustomer();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                data.add(new Customer(
                        rs.getString("ID"),
                        rs.getString("Title"),
                        rs.getString("First Name"),
                        rs.getString("Last Name"),
                        rs.getString("Date of Birth"),
                        rs.getString("Email"),
                        rs.getString("Contact Number"),
                        rs.getString("House Number"),
                        rs.getString("Street Name"),
                        rs.getString("Postcode")
                ));
                table1.setItems(data);
            }
            pst.close();
            rs.close();
            
        }catch(SQLException e2){
            System.err.println(e2);
        }
}
    
//Search Function
   @FXML
   public void search(TextField searchBox, TableView table){
       searchBox.textProperty().addListener((observableValue, oldValue, newValue) -> {
           filteredList.setPredicate((Predicate<? super Customer>) customer->{
               if(newValue == null || newValue.isEmpty()){
                   return true;
               }
               String lowerCaseFilter = newValue.toLowerCase();
               if(customer.getId().contains(newValue)){
                   return true;
               }else if (customer.getFirstName().toLowerCase().contains(lowerCaseFilter)){
                   return true;
               }else if (customer.getSurname().toLowerCase().contains(lowerCaseFilter)){
                   return true;
               }
               
               return false;
              
           });
           
       });
       
       SortedList<Customer> sortedCus = new SortedList<>(filteredList);
       sortedCus.comparatorProperty().bind(table.comparatorProperty());
       table.setItems(sortedCus);
        
   }
 
   //Produces a random integer for the customer ID
   public String idGenerator(){
        Random dice = new Random();
        int number;
        number = dice.nextInt(8000000);
        String nAS = String.format("%06d", number);
        //System.out.println(number +" ");
        return nAS;
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
   public boolean validateTitle(ComboBox title){
       if(title.getSelectionModel().isEmpty()){
    
            Alert fieldsAlert = new Alert(Alert.AlertType.WARNING);
            fieldsAlert.setTitle("Fill out all fields");
            fieldsAlert.setHeaderText(null);
            fieldsAlert.setContentText("Please fill out all parts of the registration form to complete sign-in");
            fieldsAlert.showAndWait();
            return false;
       }
       return true;
   }
   
    @FXML
   public boolean validateMembeship(ComboBox memberType){
       if(memberType.getSelectionModel().isEmpty()){
    
            Alert fieldsAlert = new Alert(Alert.AlertType.WARNING);
            fieldsAlert.setTitle("Fill out all fields");
            fieldsAlert.setHeaderText(null);
            fieldsAlert.setContentText("Please fill out all parts of the registration form to complete sign-in");
            fieldsAlert.showAndWait();
            return false;
       }
       return true;
   }
    
   
   @FXML
    public boolean validateFields(TextField firstName, TextField lastName, TextField dob, TextField username, TextField password, TextField emailAddress, TextField contactNoField, TextField doorNo, TextField street, TextField postcode, ComboBox title, ComboBox memberType){
        
        if(firstName.getText() .isEmpty() | lastName.getText().isEmpty() | dob.getText().isEmpty()
            | emailAddress.getText().isEmpty() | username.getText().isEmpty()
                | password.getText().isEmpty() 
                | doorNo.getText().isEmpty() | street.getText().isEmpty()
                | postcode.getText().isEmpty() | title.getSelectionModel().isEmpty() | memberType.getSelectionModel().isEmpty()) {
            
            Alert fieldsAlert = new Alert(Alert.AlertType.WARNING);
            fieldsAlert.setTitle("Fill out all fields");
            fieldsAlert.setHeaderText(null);
            fieldsAlert.setContentText("Please fill out all parts of the registration form to complete sign-in");
            fieldsAlert.showAndWait();
            
            return false;
        }
        
        return true;
        
    }
    
    //The method that registers a user when the "Register" button is clicked
    @FXML
    public void registerUser(TextField firstName, TextField lastName, TextField dob, TextField emailAddress, TextField username, PasswordField password, TextField number, TextField doorNo, TextField street, TextField postcode, TextField idGen, ComboBox title, ComboBox memberType){
        
        //if(validateFields() & validateNumber() & validateFirstName() & validateLastName() & validateDoorNo() & validateEmail() & validateStreet() & validateUsername() & validatePostcode() & validatePassword()){
        try{
            //conn = person.DbConnector();
            String query = "INSERT INTO Customers ('First Name', 'Last Name', 'Date of Birth', Email, Username, Password, 'Contact Number', 'House Number', 'Street Name', 'Postcode', 'ID', 'Title', 'Membership Type') VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Connection conn = DatabaseConnections.DbConnectorCustomer();
            pst = conn.prepareStatement(query);
            
            pst.setString(1, firstName.getText());
            pst.setString(2, lastName.getText());
            pst.setString(3, dob.getText());
            pst.setString(4, emailAddress.getText());
            pst.setString(5, username.getText());
            pst.setString(6, password.getText());
            pst.setString(7, number.getText());
            pst.setString(8, doorNo.getText());
            pst.setString(9, street.getText());
            pst.setString(10, postcode.getText());
            pst.setString(11, idGen.getText());
            pst.setString(12, (String) title.getValue());
            pst.setString(13, (String) memberType.getValue());
          
            
            //validateFields();
            
            //This will set up an alert box for when the user has succesfully registered
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Complete!");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for registering with Tour of Britain");
            alert.showAndWait();
            
            pst.execute();
            
            pst.close();
            //clearFields();
           
            
          
                
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }
        
        }
    
    /**
     *
     * @param title1
     * @param memberType
     * @param firstName
     * @param lastName
     * @param dob
     * @param username
     * @param password
     * @param email
     * @param number
     * @param doorNo
     * @param street
     * @param postcode
     */
    @FXML
    public void clearRegistry(ComboBox title1, ComboBox memberType, TextField firstName, TextField lastName, TextField dob, TextField username, PasswordField password, TextField email, TextField number,TextField doorNo, TextField street, TextField postcode){
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
        
    }
    
    }
    
