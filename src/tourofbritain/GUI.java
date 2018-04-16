/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author moneymolz
 */
public class GUI {

    OperatorController registerWindow = new OperatorController();
    
    /**
     *
     * @param table1
     * @param list
     * @throws IOException
     */
    
    
     //final Stage stage;
    
    @FXML
    public void registerScreen(TableView table1, ListView list1) throws IOException{
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registry.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Tour of Britain - Membership Registration");
        
  
        
        stage.setScene(new Scene(root2));
        stage.show();
               
        stage.setOnCloseRequest(e -> {
        registerWindow.refreshTable(table1);
        registerWindow.customerList(list1);
        stage.close();    
        });
    }
    
    @FXML
    public void customerLoginWindow() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerLogin.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Tour of Britain - Customer Login");
        
  
        
                stage.setScene(new Scene(root));
                stage.show();
               
        
    }
    
    @FXML
    public void openCustomer(Button customerButton) throws IOException{
            final Stage stage;
            
            stage = (Stage) customerButton.getScene().getWindow();
            stage.close();
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerView.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.setTitle("Tour of Britain - Membership Management");
        
            stage1.setScene(new Scene(root2));
            stage1.show();
    }
 
    @FXML
    public void openEmployee(Button employeeButton) throws IOException{
            final Stage stage;
            
            stage = (Stage) employeeButton.getScene().getWindow();
            stage.close();
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setTitle("Tour of Britain - Employee Sign-in");
        
                stage.setScene(new Scene(root2));
                stage.show();
    }
    
    @FXML
    public void customerRegister(Button registerButton) throws IOException{
        final Stage stage;
            
        stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registry.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Tour of Britain - Membership Registration");
        
  
        
        stage1.setScene(new Scene(root2));
        stage1.show();
               
        stage1.setOnCloseRequest(e -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registered!");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to Tour of Britain, your membership has been created!");
        alert.showAndWait();
        stage1.close();    
        });
    }
    
}
