package DAO;


import java.util.*;

/**
 * 
 */
public abstract class DAOFactory {
    DAOFactory singleton;
    
    UserDAO userDao;
    /**
     * Default constructor
     */
    public DAOFactory() {
    }

    /**
     * 
     * @return 
     */
    public abstract UserDAO createUserDAO();

    /**
     * @param type
     * @return 
     */
    public abstract DAOFactory getFactory();
}