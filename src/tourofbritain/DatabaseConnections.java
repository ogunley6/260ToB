/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourofbritain;
import java.sql.*;
/**
 *
 * @author moneymolz
 */
public class DatabaseConnections {
    
    // Connects to Customer Database
    public static Connection DbConnectorCustomer(){
        try {
            Connection conn = null;
            //https://bitbucket.org/xerial/sqlite-jdbc/downloads
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:Customers.sqlite");
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            
        }
        return null;
            
    }  
    
   //Connects to employee database
    public static Connection DbConnectorEmployees(){
        try {
            Connection conn = null;
            //https://bitbucket.org/xerial/sqlite-jdbc/downloads
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:Employees.sqlite");
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            
        }
        return null;
    }
    
}
