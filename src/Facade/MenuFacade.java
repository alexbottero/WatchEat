/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BL.Menu;
import BL.Recipe;
import DAO.ConsumableDAO;
import DAO.DAOFactory;
import DAO.FoodDAO;
import DAO.MenuDAO;
import DAO.PostgresDAOFactory;
import DAO.RecipeDAO;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Polytech
 */
public class MenuFacade {
    
    private DAOFactory daoFactory;
    private MenuDAO menuDAO;
    private RecipeDAO recipeDAO;
    private FoodDAO foodDAO;
    private ConsumableDAO consumableDAO;
    
    public MenuFacade(){
        daoFactory = PostgresDAOFactory.getInstance();
        recipeDAO = daoFactory.createRecipeDAO();
        consumableDAO = daoFactory.createConsumableDAO();
        foodDAO = daoFactory.createFoodDAO();
    }

    /**
     * return all the recipes
     * @return ArrayList Recipe
     */
    public ArrayList<Recipe> getRecipes() {
        return recipeDAO.getRecipes();
    }
    
    /**
     * return all the menus
     * @return ArrayList Menu
     */
    public ArrayList<Menu> getMenus() {
        return menuDAO.getMenus();
    }
    
}
