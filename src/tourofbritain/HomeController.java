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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Button employee, customer, register;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GUI homeControl = new GUI();
        
        employee.setOnAction(e -> {
            try {
                homeControl.openEmployee(employee);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        customer.setOnAction(e ->{
            try {
                homeControl.openCustomer(customer);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        register.setOnAction(e ->{
            try {
                homeControl.customerRegister(register);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }    
    
}
