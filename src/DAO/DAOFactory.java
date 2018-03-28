package DAO;


import java.util.*;

/**
 * 
 */
public abstract class DAOFactory {
    DAOFactory singleton;
    
    UserDAO userDAO;
    RecipeDAO recipeDAO;
    ConsumableDAO consumableDAO;
    RequestDAO requestDAO;
    
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

    public abstract RecipeDAO createRecipeDAO();

    public abstract ConsumableDAO createConsumableDAO();
    
    public abstract RequestDAO createRequestDAO();
}