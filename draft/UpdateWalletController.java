/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draft;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class UpdateWalletController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane updateWalletPane;
    
    @FXML
    private void cancelWalletUpdate() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("postLogin.fxml"));
        updateWalletPane.getChildren().setAll(pane); 
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
