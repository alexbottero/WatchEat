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
    MenuDAO menuDAO;
    
    /**
     * Default constructor
     */
    public DAOFactory() {
    }

    /**
     * 
     * @return userDAO
     */
    public abstract UserDAO createUserDAO();

    /**
     * @return DAOFactory
     */
    public abstract DAOFactory getFactory();

    public abstract RecipeDAO createRecipeDAO();

    public abstract ConsumableDAO createConsumableDAO();
    
    public abstract FoodDAO createFoodDAO();
    
    public abstract RequestDAO createRequestDAO();
    
    public abstract MenuDAO createMenuDAO();
    
    
}