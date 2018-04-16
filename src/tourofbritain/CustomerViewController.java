/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class CustomerViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML private TextField username, dob, email, contactNo, id, firstname, surname, doorNo, street, postcode;
    @FXML private PasswordField password;
    @FXML private ComboBox title, membership;
    @FXML private Button update, cancel, deleteMember;
    ObservableList<String> titleList = FXCollections.observableArrayList("Mr", "Mrs", "Miss", "Ms");
    ObservableList<String> membershipType = FXCollections.observableArrayList ("Basic", "Premium", "Student+", "65+");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //User is not allowed to edit customer ID
        id.setEditable(false);
        id.setMouseTransparent(true);
        id.setFocusTraversable(false);
        
        
        
        CustomerController customerFunction = new CustomerController();
        password.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode()== KeyCode.ENTER){
                try {
                    customerFunction.verifyCustomer(username, password, id, title, firstname, surname, dob, email, contactNo, doorNo, street, postcode, membership);
                    
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        
        update.setOnAction(e ->{
            if(customerFunction.validateFirstName(firstname) & customerFunction.validateLastName(firstname) & customerFunction.validateEmail(email) & customerFunction.validateNumber(contactNo) & customerFunction.validateDoorNo(doorNo) & customerFunction.validateStreet(street) & customerFunction.validatePostcode(postcode) & customerFunction.validateDob(dob) == true){
            customerFunction.updateMembership(id, firstname, surname, dob, email, contactNo, doorNo, street, postcode, title, membership, password);
            customerFunction.clearFields(title, membership, firstname, surname, dob, username, password, email, contactNo, doorNo, street, postcode, id);
            }
        });
        
        deleteMember.setOnAction(e ->{
            customerFunction.deleteMemership(id);
            customerFunction.clearFields(title, membership, firstname, surname, dob, username, password, email, contactNo, doorNo, street, postcode, id);
        });
        
        
    
    }
}
