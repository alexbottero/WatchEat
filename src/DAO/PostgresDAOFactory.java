package DAO;


import java.util.*;

/**
 * 
 */
public class PostgresDAOFactory extends DAOFactory {
    
    PostgresUserDAO userDAO;
    static PostgresDAOFactory singleton;

    public static DAOFactory getInstance() {
        if(singleton != null){
            return singleton;
        }
        else{
            return new PostgresDAOFactory();
        }
    }

    /**
     * Default constructor
     */
    private PostgresDAOFactory() {
        
    }    
    

    
    /**
     * 
     * @return 
     */
    public UserDAO createUserDAO() {
        return new PostgresUserDAO();
    }
    
    
    public PostgresDAOFactory getFactory(){
        if (this.singleton ==null){
            this.singleton = new PostgresDAOFactory();
        }
        return (PostgresDAOFactory) singleton;
    }

}