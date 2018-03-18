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
    private final String className;
    private final String connectionSettings;
    private final String userName;
    private final String password;
    
       public JDBC(String className, String connectionSettings, String userName, String password){
        this.className = className;
        this.connectionSettings = connectionSettings;
        this.userName = userName;
        this.password = password;
    }


    
       
            
    public void connect() {
      try {
         Class.forName(className);
          connect = DriverManager
                  .getConnection(connectionSettings, userName, password);
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
       } static final String HOST = "localhost";
    

    /** public static void main(String[] args){
         JDBC jdbc = new JDBC("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/WatchEat",
            "postgres", "garabla2996");

     }**/
}
