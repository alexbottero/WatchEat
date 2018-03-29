/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

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
        
    public ObservableList<String> getTypes(){
        ArrayList<String> types = recipeDAO.getTypes();
        return FXCollections.observableArrayList(types);
    }
    
        public ObservableList<String> getConsumables(){
        ArrayList<String> consumables = consumableDAO.getStringConsumables();
        Collections.sort(consumables);
        return FXCollections.observableArrayList(consumables);
    }
        
    public ArrayList<Recipe> getRecipes() {
        return recipeDAO.getRecipes();
    }
    
}