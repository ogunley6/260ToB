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
import javafx.fxml.Initializable;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class UpdateLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //When the cancel button is pressed it sets the scene back the to the post login scene
    @FXML
    private AnchorPane updateLoginPane;
    
    @FXML
    private void cancelLoginUpdate() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("postLogin.fxml"));
        updateLoginPane.getChildren().setAll(pane); 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
