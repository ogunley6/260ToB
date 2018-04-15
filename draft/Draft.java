/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draft;

import java.sql.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author moneymolz
 */
public class Draft extends Application {
    
    Connection conn;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        stage.setTitle("Tour of Britain - Login");
        Scene scene = new Scene(root);
        
        CheckConnectionEm();
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    //This method checks whether the connection to the database is successful
    public void CheckConnection(){
        conn = person.DbConnector();
        if(conn == null){
            System.out.println("Connection not successful");
            System.exit(1);  
        }
        else{
            System.out.println("Connection successful");
        }
    }
    
    //Checks connection to employee database
    public void CheckConnectionEm(){
        conn = person.DbConnectorEm();
        if(conn == null){
            System.out.println("Connection not successful");
            System.exit(1);  
        }
        else{
            System.out.println("Connection to Employee Database successful");
        }
    }
    
    //Method to clear fields after filling out forms, need to find a way for me to just past any amount of parameters and it does the rest
    /*public void clearFields(){
        
        
    }*/
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    
}
