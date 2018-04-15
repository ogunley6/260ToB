package draft;


import java.sql.*;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moneymolz
 */
public class person {
    
    // Connects to Customer Database
    public static Connection DbConnector(){
        try {
            Connection conn = null;
            //https://bitbucket.org/xerial/sqlite-jdbc/downloads
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:Test.sqlite");
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            
        }
        return null;
            
    }  
    
   //Connects to employee database
    public static Connection DbConnectorEm(){
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
    
    public String randomGen(){
        Random dice = new Random();
        int number;
        number = dice.nextInt(8000000);
        String nAS = String.format("%06d", number);
        //System.out.println(number +" ");
        return nAS;
    }
    
    
}


