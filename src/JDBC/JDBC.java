/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.DriverManager;

/**
 *
 * @author julia
 */
public class JDBC {
    
    private Connection connect;
    
    static JDBC singleton;
    
     /**
   * The host of the database.
   */
    private static final String HOST = "localhost";
    
    /**
   * The port to connect on.
   */
    private static final String PORT = "5432";
    
    /**
   * The name of the database to which we need to connect to.
   */
    private static final String DATABASE = "WatchEat";
    
    /**
   * The name of the user.
   */
    private static final String USERNAME = "postgres";
    
    /**
   * The password of the user to connect to the database.
   */
    private static final String PASSWORD = "K2vm@0r67";
    
    private static final String CLASSNAME = "org.postgresql.Driver";
    
    private JDBC(){
    }


    public static JDBC getInstance(){
        if(singleton != null){
            return singleton;
        }
        else{
            return new JDBC();
        }
    }       
            
    public void connect() {
      try {
         Class.forName(CLASSNAME);
          connect = DriverManager
                  .getConnection("jdbc:postgresql://" + HOST + ":" + PORT +"/" + DATABASE, USERNAME, PASSWORD);
         System.out.println("Opened database successfully");
      } catch (ClassNotFoundException | SQLException e) {
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
    }      
        
    public void update(String reqSQL) throws SQLException{
        connect();
        connect.createStatement().executeUpdate(reqSQL);
        connect.close();
    }
      
    public ResultSet select(String reqSQL) throws SQLException{
        ResultSet res = null;
        connect();
        res = connect.createStatement().executeQuery(reqSQL);
        connect.close();
        return res;
    }
    

    /** public static void main(String[] args){
         JDBC jdbc = new JDBC("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/WatchEat",
            "postgres", "garabla2996");

     }**/
}
