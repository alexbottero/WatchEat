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
public class DBPostgresConnection extends DBConnection{
    
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DATABASE = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    
    private static Statement stmt = null;
    private static Connection c = null;
    
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    
    public DBPostgresConnection(String host, String port, String database, String username, String password){
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    public DBPostgresConnection(){
        this.host = HOST;
        this.port = PORT;
        this.database = DATABASE;
        this.username = USERNAME;
        this.password = PASSWORD;
    }
    
    /**
     * Permet de se connecter à la base de donnée postgres.
     */
    public void connect() {
      
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://" + host + ":" + port +"/" + database,
            username, password);
         stmt = c.createStatement();
         System.out.println("Opened database successfully");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
   }
    
    /**
     * Permet d'ajouter ou de modifier dans la base de donnée (INSERT,UPDATE).
     * @param reqSQL correspond à la requète SQL à effectuer.
     */
    public void update(String reqSQL){
        try {
            connect();
            stmt.executeUpdate(reqSQL);
            c.close();
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
            connect();
            res = stmt.executeQuery(reqSQL);
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static void main(String[] args){
        DBPostgresConnection dbConnection = new DBPostgresConnection(HOST,PORT,DATABASE,USERNAME,PASSWORD);
        dbConnection.update("INSERT INTO public.user (mail, pwd) VALUES ('ehamelojulia1@gmail.com','chat');");
        dbConnection.update("INSERT INTO public.user (mail, pwd) VALUES ('ehamelojulia2@gmail.com','chat');");
    }
}
