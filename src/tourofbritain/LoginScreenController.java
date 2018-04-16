/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */




public class LoginScreenController implements Initializable {
    
    
    

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Button userLogin;
    ActionEvent event1;
    @FXML public ComboBox titleBox;

 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

            OperatorController login = new OperatorController();
            GUI customerWindow = new GUI();
            
            
            
            login.CheckConnectionOperator();
            passwordField.setOnKeyPressed(e -> {
            if(e.getCode()== KeyCode.ENTER){
               
                try {
                    login.verifyEmployee(usernameField, passwordField);
                } catch (IOException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            
           
            
            userLogin.setOnAction(e -> {
                try {
                    login.verifyEmployee(usernameField, passwordField);
                } catch (IOException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
          
            
            
        }
    }    
    

