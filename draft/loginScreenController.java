/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draft;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;

/**
 *
 * @author moneymolz
 */
public class loginScreenController implements Initializable {
    
    /*@FXML
    public void closeWindow(){
        //Stage stage = (Stage) closeButton.getScene().getWindow();
        System.out.println("You have been loogged out");
        window.close();
    }*/
    
    //PrepareStatement
  
    
    
    
    //Logs user in and opens new window
    /*@FXML
    private void loginUser() throws IOException, SQLException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("postLogin.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Tour of Britain - Membership Managem");
        
  
        
        stage.setScene(new Scene(root2));
        
        stage.setOnCloseRequest(e -> {
            System.out.println("You have been logged out");
            stage.close();
        });
        
        stage.show();
    }
    
    public void CheckConnection(){
        conn = person.DbConnector();
        if(conn == null){
            System.out.println("Login Unsuccesful");
            System.exit(1);  
        }
        else{
            System.out.println("Login Sucessful");
        }
    }*/
    //Connects the login page to database so that the user can move to next window
   
    
    
    
    //Opens customer regsistration in a new window
    /*@FXML
    private void registerUser() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("formRegister.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Tour of Britain - Registration");
        
     
        
        stage1.setScene(new Scene(root2));
        
        /*stage1.setOnCloseRequest(e -> {
            System.out.println("Registration Cancelled");
            stage1.close();
        });
        
        stage1.show();
    } */
    
    Connection conn = person.DbConnectorEm();
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private TextField username;
    //String usernameRet = username.getText();
    @FXML
    private PasswordField password;
       
    
    
    @FXML
    public void verifyUser(ActionEvent event) throws IOException{
        try{
            //conn = person.DbConnector();
            String change = "SELECT * FROM Employees WHERE Username=? AND Password=?";
            pst = conn.prepareStatement(change);
            
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();
            
            //if password is correct membership window is opened 
            if(rs.next()){
              
                System.out.println("Login Successful");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Customer Table1.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Tour of Britain - Membership Management");
        
  
        
                stage.setScene(new Scene(root2));
        
                /*stage.setOnCloseRequest(e -> {
                System.out.println("You have been logged out");
                stage.close();
                }); */
        
                stage.show();
                }
            
          
                else{
                System.out.println("Login Unsuccessful");
            }
            
            username.clear();
            password.clear();
            pst.close();
            rs.close();
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }
    }
    
    //When user presses enter
   /* @FXML
    public void passwordKeyEnter(KeyEvent evt){
        password.setOnKeyPressed(e  ->{
            if(e.getCode() == KeyCode.ENTER){
                try{
            //conn = person.DbConnector();
            String change = "SELECT * FROM Customer WHERE username=? AND password=?";
            pst = conn.prepareStatement(change);
            
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();
            
            //if password is correct membership window is opened 
            if(rs.next()){
              
                System.out.println("Login Successful");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("postLogin.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Tour of Britain - Membership Management");
        
                stage.setScene(new Scene(root2));
                stage.show();
                }
            
          
                else{
                System.out.println("Login Unsuccessful");
            }
            
            username.clear();
            password.clear();
            pst.close();
            rs.close();
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }       catch (IOException ex) {
                    Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
         
            
            
        });
        
    }*/

    //Trying to find way to connect username to next window
    public void getU(){
        String usernameRet = username.getText();
        System.out.println(usernameRet);
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        password.setOnKeyPressed(e -> {
            if(e.getCode()== KeyCode.ENTER){
                try{
            //conn = person.DbConnector();
            String change = "SELECT * FROM Employees WHERE Username=? AND Password=?";
            pst = conn.prepareStatement(change);
            
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();
            
            //if password is correct membership window is opened 
            if(rs.next()){
              
                System.out.println("Login Successful");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Customer Table1.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Tour of Britain - Membership Management");
        
  
        
                stage.setScene(new Scene(root2));
        
                /*stage.setOnCloseRequest(e -> {
                System.out.println("You have been logged out");
                stage.close();
                }); */
        
                stage.show();
                }
            
          
                else{
                System.out.println("Login Unsuccessful");
            }
            
            username.clear();
            password.clear();
            pst.close();
            rs.close();
        }catch(SQLException e1){
            System.out.println("SQL Error");
            System.err.println(e1);
        }       catch (IOException ex) {
                    Logger.getLogger(loginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }    
    
}
