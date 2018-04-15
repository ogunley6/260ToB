/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draft;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class CustomerTable1Controller implements Initializable {
    
    @FXML private TableView table;
    @FXML private ScrollPane tablescroll;
    @FXML Button loadDataButton, delete, save;
    final ObservableList <Customer> data = FXCollections.observableArrayList();    
    @FXML private TableColumn idColumn, titleColumn, fnColumn, snColumn, emailColumn, phoneColumn, dobColumn, doorNoColumn, streetColumn, postcodeColumn;
    @FXML private ComboBox<String> titleBox;
    @FXML private TextField idField, fnField, snField, dobField, emailField, contactNoField, doorNoField, streetField, postcodeField, searchBox;
    @FXML private ListView customerList;
    final ObservableList options = FXCollections.observableArrayList();
    ObservableList<String> titleList = FXCollections.observableArrayList("Mr", "Mrs", "Miss", "Ms");
    FilteredList<Customer> filteredList = new FilteredList<>(data, e-> true);
    
    
    
    Connection conn = person.DbConnector();
    PreparedStatement pst;
    ResultSet rs;
     
    //Register a new user
    @FXML
    private void registerUser() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("formRegister.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Tour of Britain - Registration");
        
     
        
        stage1.setScene(new Scene(root2));
        stage1.show();
        
          stage1.setOnCloseRequest(e -> {
            refreshTable();
            refreshCustomerList();
            stage1.close();
        });
        
    }
    
    
    //Load data into combo
    /*@FXML
    public void fillCombo(){
        options.clear();
        
        
        try {
            String query = "SELECT * FROM Customer";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                options.add(rs.getString("Username")); 
                
                customerCombo.setItems(options);
            }
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTable1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
  
    
    
    @FXML
    public void customerCombo(){
    
        //options.clear();
        try {
            String query = "SELECT * FROM Customer WHERE Username = ?";
            pst = conn.prepareStatement(query);
            //this.data1 = FXCollections.observableArrayList();
            pst.setString(1, (String) customerCombo.getSelectionModel().getSelectedItem() );
            rs = pst.executeQuery();
            
            while(rs.next()){
                idField.setText(rs.getString("ID"));
                fnField.setText(rs.getString("First Name"));
                snField.setText(rs.getString("Last Name"));
                dobField.setText(rs.getString("Date of Birth"));
                emailField.setText(rs.getString("Email"));
                contactNoField.setText(rs.getString("Contact Number"));
                doorNoField.setText(rs.getString("House Number"));
                streetField.setText(rs.getString("Street Name"));
                postcodeField.setText(rs.getString("Postcode"));
            }
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTable1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
    
    
    
    
    
    /*To update a user
    NEED TO ADD VALIDATION
    */
     @FXML
    public void updateUser(){
        
        try{
            //conn = person.DbConnector();
            String query = "UPDATE Customer SET 'First Name'=?, 'Last Name'=?, 'Date of Birth'=?, 'Email'=?, 'Contact Number'=?, 'House Number'=?, 'Street Name'=?, 'Postcode'=?, 'Title'=? WHERE ID='"+idField.getText()+"' ";
            pst = conn.prepareStatement(query);
            
            pst.setString(1, fnField.getText());
            pst.setString(2, snField.getText());
            pst.setString(3, dobField.getText());
            pst.setString(4, emailField.getText());
            pst.setString(5, contactNoField.getText());
            pst.setString(6, doorNoField.getText());
            pst.setString(7, streetField.getText());
            pst.setString(8, postcodeField.getText());
            pst.setString(9, titleBox.getValue());
            
            
            //validateFields();
            
            //This will set up an alert box for when the user has succesfully registered
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Your user details have been updated");
            alert.showAndWait();
            
            pst.execute();
            
            pst.close();
            clearFields();
           
            
          
                
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }
        refreshTable();
        refreshCustomerList();
        }
    
    //search function
   @FXML
   private void search(){
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
    
    
    
    //Clear text fields
    @FXML
    private void clearFields(){
        idField.clear();
        fnField.clear();
        snField.clear(); 
        dobField.clear(); 
        emailField.clear(); 
        contactNoField.clear(); 
        doorNoField.clear();
        streetField.clear();
        postcodeField.clear();
        titleBox.setValue(null);
    }
    
    //refresh table
    @FXML
    public void refreshTable(){
        data.clear();
        try{
            String query = "SELECT * FROM Customer ";
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
                table.setItems(data);
            }
            pst.close();
            rs.close();
            
        }catch(Exception e2){
            System.err.println(e2);
        }
}
   
    //Update a membership
    
    
    
    //This method will load the customer data into the table for employee to see
    @FXML
    private void loadData(){
        /* try{
            String query = "SELECT * FROM Customer ";
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
                table.setItems(data);
            }
            pst.close();
            rs.close();
            
        }catch(Exception e2){
            System.err.println(e2);
        } */
        refreshTable();
        
        
    }
    
    //Fill the data from the customer database into the listview
    private void customerList(){
         try {
            String query = "SELECT * FROM Customer";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                options.add(rs.getString("ID")); 
              
                
                customerList.setItems(options);
            }
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTable1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   //final ObservableList options = FXCollections.observableArrayList()
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //final ObservableList <Customer> data = FXCollections.observableArrayList();
        //titleBox(options);
        loadData();
        customerList();
        idField.setEditable(false);
        idField.setMouseTransparent(true);
        idField.setFocusTraversable(false);
        
        delete.setOnAction(e -> {
            try {
                String query = "DELETE FROM Customer WHERE ID = ?";
                pst = conn.prepareStatement(query);
                pst.setString(1, idField.getText());
                pst.executeUpdate();
                pst.close();
                
                System.out.println("User has been deleted");
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(CustomerTable1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            clearFields();
            refreshTable();
            refreshCustomerList();
            
            
        });
        
        titleBox.setValue("");
        //titleBox.setValue("Mrs");
        //titleBox.setValue("Miss");
        //titleBox.setValue("Ms");
        titleBox.setItems(titleList);
        
        //searchBox.setOnKeyReleased(KeyEvent e ->{
            
       
        //list on mouse clicked
        customerList.setOnMouseClicked(e ->{
             try {
            String query = "SELECT * FROM Customer WHERE ID = ?";
            pst = conn.prepareStatement(query);
            //this.data1 = FXCollections.observableArrayList();
            pst.setString(1, (String) customerList.getSelectionModel().getSelectedItem() );
            rs = pst.executeQuery();
            
            while(rs.next()){
                idField.setText(rs.getString("ID"));
                titleBox.setValue(rs.getString("Title"));
                fnField.setText(rs.getString("First Name"));
                snField.setText(rs.getString("Last Name"));
                dobField.setText(rs.getString("Date of Birth"));
                emailField.setText(rs.getString("Email"));
                contactNoField.setText(rs.getString("Contact Number"));
                doorNoField.setText(rs.getString("House Number"));
                streetField.setText(rs.getString("Street Name"));
                postcodeField.setText(rs.getString("Postcode"));
            }
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTable1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
       
        //When the delete button is pressed
        //delete.setOnAction(e ->{
            
        
        

        //Table set up
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
    
    @FXML
    private void deleteUser(){
        
        }
    
    
    @FXML
    private void refreshCustomerList() {
        options.clear();     
        try {
            String query = "SELECT * FROM Customer";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                options.add(rs.getString("ID")); 
                
                customerList.setItems(options);
            }
            pst.close();
            rs.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTable1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
