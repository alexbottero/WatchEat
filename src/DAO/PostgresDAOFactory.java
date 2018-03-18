package DAO;


import java.util.*;

/**
 * 
 */
public class PostgresDAOFactory extends DAOFactory {

    /**
     * Default constructor
     */
    /**public PostgresDAOFactory() {
        }
    }**/

    
    /**
     * 
     * @return 
     */
    @Override
    public UserDAO createUserDAO() {
        return new PostgresUserDAO();
    }
    
    
    @Override
    public PostgresDAOFactory getFactory(){
        if (this.singleton ==null){
            this.singleton = new PostgresDAOFactory();
        }
        return (PostgresDAOFactory) singleton;
    }

}