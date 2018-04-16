/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class RegistryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML PasswordField password;
    @FXML ComboBox title, membership;
    @FXML TextField id, firstName, lastName, dob, username, email, number, doorNo, street, postcode ;
    @FXML Button register;
    ObservableList<String> titleList = FXCollections.observableArrayList("Mr", "Mrs", "Miss", "Ms");
    ObservableList<String> membershipType = FXCollections.observableArrayList ("Basic", "Premium", "Student+", "65+");        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OperatorController registerFunction = new OperatorController();
        
        //Set the values for the title and membershp ComboBox
        title.setItems(titleList);
        membership.setItems(membershipType);
        
        id.setEditable(false);
        id.setMouseTransparent(true);
        id.setFocusTraversable(false);
        id.setText(registerFunction.idGenerator());
        
        register.setOnAction((ActionEvent e) -> {
            if(registerFunction.validateNumber(number) & registerFunction.validateFirstName(firstName) & registerFunction.validateLastName(lastName) & registerFunction.validateDoorNo(doorNo) & registerFunction.validateEmail(email) & registerFunction.validateStreet(street) & registerFunction.validateUsername(username) & registerFunction.validatePostcode(postcode) & registerFunction.validatePassword(password) & registerFunction.validateDob(dob) & registerFunction.validateTitle(title) & registerFunction.validateMembeship(membership) == true){
                registerFunction.registerUser(firstName, lastName, dob, email, username, password, number, doorNo, street, postcode, id, title, membership);
                registerFunction.clearRegistry(title, membership, firstName, lastName, dob, username, password, email, number, doorNo, street, postcode);
            }
        });
    }    
    
}
