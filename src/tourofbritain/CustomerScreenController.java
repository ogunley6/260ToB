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
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class CustomerScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    //final ObservableList <Customer> data = FXCollections.observableArrayList(); 
    @FXML
    TableView table;
    @FXML private 
    TableColumn idColumn, titleColumn, fnColumn, snColumn, emailColumn, phoneColumn, dobColumn, doorNoColumn, streetColumn, postcodeColumn;
    @FXML
    private ListView idList;
    @FXML 
    private ScrollPane tablescroll;
    @FXML private ComboBox<String> title, membership;
    @FXML private TextField id, firstname, surname, dob, email, contactNo, doorNo, street, postcode, search;
    @FXML private Button deleteMembership, update, register;
    //ObservableList<String> titleList = FXCollections.observableArrayList("Mr", "Mrs", "Miss", "Ms");
    //ObservableList<String> membershipType = FXCollections.observableArrayList ("Basic", "Premium", "Student+", "65+");
    @FXML AnchorPane pane;
    
    Stage stage;
    
   
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        GUI switchScene = new GUI();
        
        OperatorController customerView = new OperatorController();
        
        customerView.refreshTable(table);
        customerView.customerList(idList);
        //title.setItems(titleList);
        //membership.setItems(membershipType);
        
        customerView.customerListClicked(idList, id, title, firstname, surname, dob, email, contactNo, doorNo, street, postcode, membership);
        
        //User is not allowed to edit customer ID
        id.setEditable(false);
        id.setMouseTransparent(true);
        id.setFocusTraversable(false);
        
        //When operator clicks delete it removes the membership and saves changes on the database also clears the fields
        deleteMembership.setOnAction(e ->{
           customerView.deleteMemership(id);
           customerView.refreshTable(table);
           customerView.customerList(idList);
           customerView.clearFields(id, firstname, surname, dob, email, contactNo, doorNo, street, postcode, title, membership);
           customerView.search(search, table);
        });
        
        //When the operator clicks update it saves the changes made to the membership, also clears the fields
        update.setOnAction(e ->{
            if(customerView.validateFirstName(firstname) & customerView.validateLastName(firstname) & customerView.validateEmail(email) & customerView.validateNumber(contactNo) & customerView.validateDoorNo(doorNo) & customerView.validateStreet(street) & customerView.validatePostcode(postcode) & customerView.validateDob(dob) == true){
            customerView.updateMembership(id, firstname, surname, dob, email, contactNo, doorNo, street, postcode, title, membership);
            customerView.refreshTable(table);
            customerView.customerList(idList);
            customerView.clearFields(id, firstname, surname, dob, email, contactNo, doorNo, street, postcode, title, membership);
            customerView.search(search, table);
            }
        });
        
        
        //Opens a new window which allows the operator to register a new membership
        register.setOnAction(e ->{
            
            try {
                switchScene.registerScreen(table, idList);
                customerView.search(search, table);
            } catch (IOException ex) {
                Logger.getLogger(CustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
           
                
        });
        

//refreshTable();
        
        //Sets up and initiates the table
        idColumn.setCellValueFactory(new PropertyValueFactory<> ("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<> ("title"));
        fnColumn.setCellValueFactory(new PropertyValueFactory<> ("firstName"));
        snColumn.setCellValueFactory(new PropertyValueFactory<> ("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<> ("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<> ("contactNo"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<> ("dob"));
        doorNoColumn.setCellValueFactory(new PropertyValueFactory<> ("doorNo"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<> ("street"));
        postcodeColumn.setCellValueFactory(new PropertyValueFactory<> ("postcode"));
        
    }    
    
}
