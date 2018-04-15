/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draft;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author moneymolz
 */
public class CustomerTableController implements Initializable {
    
    //configure the table
    @FXML private TableView<FakeCustomer> tableView;
    
    @FXML private TableColumn<FakeCustomer, String> firstNameColumn;
    @FXML private TableColumn<FakeCustomer, String> lastNameColumn;
    @FXML private TableColumn<FakeCustomer, LocalDate> birthdayColumn;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //sets up the coloumns in the table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<FakeCustomer, String> ("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<FakeCustomer, String> ("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<FakeCustomer, LocalDate>("birthday"));
        
        //Load some data
        tableView.setItems(getPeople());
        
    }
        
        //will return an observable list of people objects 
        public ObservableList<FakeCustomer> getPeople(){
            
            ObservableList<FakeCustomer> people = FXCollections.observableArrayList();
            people.add(new FakeCustomer("Tolu", "Gbeke", LocalDate.of(1995, Month.OCTOBER, 24)));
            people.add(new FakeCustomer("Sarah", "Hamie", LocalDate.of(1984, Month.FEBRUARY, 17)));
            people.add(new FakeCustomer("TChala", "Wakanda", LocalDate.of(1995, Month.DECEMBER, 9)));
            
            return people;
        }
}


    
    

