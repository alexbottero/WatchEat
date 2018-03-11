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
 * This class is handles the connection to the database.
 * @author Alexandre Bottero
 * @author Hamelina Ehamelo
 * @author Fabien Turgut
 * @author Marion Rul
 * @version March 2018
 */
public class DBConnection {
    
    //Class variables *************************************************
    
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
    private static final String DATABASE = "";
    
    /**
   * The name of the user.
   */
    private static final String USERNAME = "";
    
    /**
   * The password of the user to connect to the database.
   */
    private static final String PASSWORD = "";
    
    /**
   * The Statement used for executing a static SQL statement and returning the results it produces
   */
    private static Statement stmt = null;
    
    /**
   * The Connection to the database.
   */
    private static Connection c = null;
    
     //Instance variables **********************************************
  
    /**
     * The instance of the Java Database Connection corresponding to the host.
     */
    private final String host;
    
    /**
     * The instance of the Java Database Connection corresponding to port to connect to.
     */
    private final String port;
    
    /**
     * The instance of the Java Database Connection corresponding to the name of the database.
     */
    private final String database;
    
    /**
     * The instance of the Java Database Connection corresponding to the name of the user.
     */
    private final String username;
    
    /**
     * The instance of the Java Database Connection corresponding to the password of the user to connect to the database.
     */
    private final String password;
    
     //Constructors ****************************************************

  /**
   * Constructs an instance of the DBConnection.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   * @param database The database to connect to.
   * @param username The username to connect with. 
   * @param password The password to connect with. 
   */
    public DBConnection(String host, String port, String database, String username, String password){
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    /**
   * Constructs an instance of the DBConnection.
   * 
   */
    
    public DBConnection(){
        this.host = HOST;
        this.port = PORT;
        this.database = DATABASE;
        this.username = USERNAME;
        this.password = PASSWORD;
    }
    
    
    //Instance methods ************************************************
  
  /**
   * This method allows you to connect to the database. 
   * 
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
     * This method update an element or insert a new element in the database. 
     * @param reqSQL The Sql request to execute.
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
     * This method selects data(s) from the database
     * @param reqSQL The Sql statement to execute.
     * @return The result of the selection from the Sql statement. 
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
    
   /**
   * This method is a test so far.
   *
   */
    public static void main(String[] args){
        DBConnection dbConnection = new DBConnection(HOST,PORT,DATABASE,USERNAME,PASSWORD);
        dbConnection.update("INSERT INTO public.user (mail, pwd) VALUES ('ehamelojulia1@gmail.com','chat');");
        dbConnection.update("INSERT INTO public.user (mail, pwd) VALUES ('ehamelojulia2@gmail.com','chat');");
    }
}
