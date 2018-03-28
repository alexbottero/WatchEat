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
     * @return PostgresUserDAO
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

    /**
     * 
     * @return PostgresRecipeDAO
     */
    @Override
    public RecipeDAO createRecipeDAO() {
        return new PostgresRecipeDAO();
    }

    @Override
    public ConsumableDAO createConsumableDAO() {
        return new PostgresConsumableDAO();
    }

    @Override
    public RequestDAO createRequestDAO() {
        return new PostgresRequestDAO(); //To change body of generated methods, choose Tools | Templates.
    }

}