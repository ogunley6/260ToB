/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draft;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.layout.*;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class PostLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //Close the window
    @FXML
    private javafx.scene.control.Button closeButton;
    
//Closes the window
    
    @FXML
    public void closeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
     @FXML
    private AnchorPane postLoginPane;
     
     //Switches scene to Update login details
    @FXML
    private void loginView() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("updateLogin.fxml"));
        postLoginPane.getChildren().setAll(pane);
    }
    
    //Switches scene to Update personal details
    @FXML
    private void personalView() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("updatePersonal.fxml"));
        postLoginPane.getChildren().setAll(pane);
    }
    
    //Switches scene to Wallet personal details
    @FXML
    private void walletView() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("updateWallet.fxml"));
        postLoginPane.getChildren().setAll(pane);
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
