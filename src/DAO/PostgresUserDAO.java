package DAO;

import BL.User;
import JDBC.JDBC;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class PostgresUserDAO extends UserDAO {
    
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
    private static final String PASSWORD = "garabla2996";
    
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
    private String className;
    private String connectionSettings; 
    
    private JDBC jdbc; 
    
    
    public PostgresUserDAO(){
        this.host = HOST;
        this.port = PORT;
        this.database = DATABASE;
        this.username = USERNAME;
        this.password = PASSWORD;
        this.className = "org.postgresql.Driver";
        this.connectionSettings = "jdbc:postgresql://" + host + ":" + port +"/" + database;
        this.jdbc= new JDBC(className,connectionSettings,username, password);
    }
    
    /**
   * Constructs an instance of the DBConnection.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   * @param database The database to connect to.
   * @param username The username to connect with. 
   * @param password The password to connect with. 
   */
    public PostgresUserDAO(String host, String port, String database, String username, String password){
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.className = "org.postgresql.Driver";
        this.connectionSettings = "jdbc:postgresql://" + host + ":" + port +"/" + database;
        this.jdbc= new JDBC(className,connectionSettings, username, password);
    }
    
    @Override
    public User find(String mail, String pwd) throws SQLException {
        String query = "SELECT mail, pwd FROM public.user WHERE mail = '" + mail +"' AND pwd = '" + pwd + "'";
        ResultSet res = jdbc.select(query);
        
        if(res.next()){
            User user  = new User();
            user.setMail(res.getString("mail"));
            user.setPwd(res.getString("pwd"));
            return user; 
        }else{
            return null;
        }      
    }
    
    @Override
    public User update(String mail, String pwd) {
        String query = "UPDATE public.user SET pwd = '" + pwd + "' WHERE mail='" + mail + "'";
        try {
            jdbc.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user  = new User();
        user.setMail(mail);
        user.setPwd(pwd);
        return user;
    }
    
    /* Fonctions d'aides*/
    
    /**
     * Permet de se connecter à la base de donnée postgres.
     
    public void connect() {
      try {
         Class.forName("org.postgresql.Driver");
         connect = DriverManager
            .getConnection("jdbc:postgresql://" + host + ":" + port +"/" + database,
            username, password);
         System.out.println("Opened database successfully");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
   }**/
    
    /**
     * Permet d'ajouter ou de modifier dans la base de donnée (INSERT,UPDATE).
     * @param reqSQL correspond à la requète SQL à effectuer.
     
    public void update(String reqSQL) throws SQLException{
        
        connect();
        connect.createStatement().executeUpdate(reqSQL);
        connect.close();
    }**/
    
    /**
     * Permet de selectionner dans la base de donnée.
     * @param reqSQL correspond à la requète SQL à effectuer.
     * @return les réultats de la requète de type ResultSet qui est un itérateur.
     
    public ResultSet select(String reqSQL) throws SQLException{
        ResultSet res = null;
        connect();
        res = connect.createStatement().executeQuery(reqSQL);
        connect.close();
        return res;
    }**/

    /**public static void main(String[] args) throws SQLException{
        PostgresUserDAO userDAO = new PostgresUserDAO();
        User u = new User("ehamelojulia@gmail.com", "chat");
        
        //User user = userDAO.find("fabazad@live.fr");
        if(user != null){System.out.println(user.getMail() + " : " + user.getPwd());}
      **/  
}
