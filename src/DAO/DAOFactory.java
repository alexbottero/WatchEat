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
     */
    public int DAO_FACTORY = 0;

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