/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private static final String PASSWORD = "93Marieb";
    
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
    

    public static void main(String[] args){
        try {
            String pwd = "gui";
            byte[] pwdBy = pwd.getBytes();
            byte[] pwdHash = null;
            String pwdFinal;
            try {
                pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            pwdFinal = new String(pwdHash);
            JDBC jdbc = new JDBC();
            jdbc.update("INSERT INTO public.user (mail,pwd)VALUES('test@test.fr','"+ pwdFinal +"')");
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
