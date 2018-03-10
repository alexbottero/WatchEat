/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @version 1.0
 * @author Fabien
 */
public class DBConnection {
    
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DATABASE = "WatchEat";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "K2vm@0r67";
    
    private static Statement stmt = null;
    
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    
    public DBConnection(String host, String port, String database, String username, String password){
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Permet de se connecter à la base de donnée postgres.
     */
    public void connect() {
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://" + host + ":" + port +"/" + database,
            username, password);
         stmt = c.createStatement();
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }
    
    /**
     * Permet d'ajouter ou de modifier dans la base de donnée (INSERT,UPDATE).
     * @param reqSQL correspond à la requète SQL à effectuer.
     */
    public void update(String reqSQL){
        try {
            stmt.executeUpdate(reqSQL);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Permet de selectionner dans la base de donnée.
     * @param reqSQL correspond à la requète SQL à effectuer.
     * @return les réultats de la requète de type ResultSet qui est un itérateur.
     */
    public ResultSet select(String reqSQL){
        ResultSet res = null;
        try {
            res = stmt.executeQuery(reqSQL);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static void main(String[] args){
        DBConnection dbConnection = new DBConnection(HOST,PORT,DATABASE,USERNAME,PASSWORD);
        dbConnection.connect();
    }
}
