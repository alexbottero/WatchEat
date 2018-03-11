package DAO;


import java.util.*;

/**
 * 
 */
public class PostgresDAOFactory extends DAOFactory {

    /**
     * Default constructor
     */
    public PostgresDAOFactory() {
    }

    
    /**
     * 
     * @return 
     */
    public UserDAO createUserDAO() {
        return new PostgresUserDAO();
    }

}