package DAO;


import java.util.*;

/**
 * 
 */
public abstract class DAOFactory {

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
    public DAOFactory getFactory(int type) {
        return null;
        // TODO implement here
    }

}