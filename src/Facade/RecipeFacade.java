/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BL.Consumable;
import BL.Food;
import BL.Ingredient;
import BL.Recipe;
import BL.User;
import DAO.*;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fabaz
 */
public class RecipeFacade {
    
    private DAOFactory daoFactory;
    private RecipeDAO recipeDAO;
    private FoodDAO foodDAO;
    private ConsumableDAO consumableDAO;
    
    public RecipeFacade(){
        daoFactory = PostgresDAOFactory.getInstance();
        recipeDAO = daoFactory.createRecipeDAO();
        consumableDAO = daoFactory.createConsumableDAO();
        foodDAO = daoFactory.createFoodDAO();
    }
    
    /**
     * get all the types names into string array
     * @return ObservableList String
     */
    public ObservableList<String> getTypes(){
        ArrayList<String> types = recipeDAO.getTypes();
        return FXCollections.observableArrayList(types);
    }
    
    /**
     * Get all the consumables names into string array
     * @return ObservableList String
     */
    public ObservableList<String> getConsumables(){
        ArrayList<String> consumables = consumableDAO.getStringConsumables();
        Collections.sort(consumables);
        return FXCollections.observableArrayList(consumables);
    }
    
    /**
     * Create a new recipe with this caracteristics
     * @param name String
     * @param description String
     * @param type String
     * @param timeString int
     * @param peopleAmountString int
     * @param instructions String
     * @param ingredientsName ArrayList String
     * @param ingredientsQuantity ArrayList String
     */
    public void createRecipe(String name, String description, String type, String timeString, String peopleAmountString, String instructions, ArrayList<String> ingredientsName, ArrayList<Integer> ingredientsQuantity) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for(String ingredientName : ingredientsName){
            Consumable consumable;
            Food food = foodDAO.getFood(ingredientName);
            if(food != null){
                consumable = food;
                
            }else{
                consumable = recipeDAO.getRecipe(ingredientName);
            }
            int quantity = ingredientsQuantity.get(ingredientsName.indexOf(ingredientName));
            ingredients.add(new Ingredient(consumable,quantity));
        }
        int time = Integer.parseInt(timeString);
        int peopleAmount = Integer.parseInt(peopleAmountString);
        Recipe recipe = new Recipe(name,description,instructions,time,peopleAmount,type,ingredients);
        recipeDAO.createRecipe(recipe,UserFacade.connectedUser);
    }

    /**
     * get all the recipes
     * @return ArrayList Recipe
     */
    public ArrayList<Recipe> getRecipes() {
        return recipeDAO.getRecipes();
    }

    /**
     * Get the recipes wich contain this string in name, this type, under this time max and written by this user
     * @param name String
     * @param type String
     * @param timeMax int 
     * @param connectedUser User
     * @return ArrayList Recipe
     */
    public ArrayList<Recipe> getRecipes(String name, String type, int timeMax, User connectedUser) {
        name = (name == null ? "" : name);
        type = (type == null || type.equals("None") ? "" : type);
        timeMax = (timeMax == 0 ? 999 : timeMax);
        if(connectedUser == null){
            return recipeDAO.getRecipes(name,type,timeMax);
        }
        else{
            return recipeDAO.getRecipes(name,type,timeMax,connectedUser);
        }
    }

    /**
     * Delete this recipe
     * @param recipe Recipe 
     */
    public void deleteRecipe(Recipe recipe) {
        recipeDAO.deleteRecipe(recipe);
    }

    /**
     * Edit the recipe of this name with the other paramaters
     * @param name String
     * @param description String
     * @param type String
     * @param time int
     * @param peopleAmount int
     * @param instructions String
     * @param ingredientsName ArrayList Strin 
     * @param ingredientsQuantity ArrayList Integer
     */
    public void editRecipe(String name, String description, String type, int time, int peopleAmount, String instructions, ArrayList<String> ingredientsName, ArrayList<Integer> ingredientsQuantity) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for(String ingredientName : ingredientsName){
            Consumable consumable;
            Food food = foodDAO.getFood(ingredientName);
            if(food != null){
                consumable = food;
                
            }else{
                consumable = recipeDAO.getRecipe(ingredientName);
            }
            int quantity = ingredientsQuantity.get(ingredientsName.indexOf(ingredientName));
            ingredients.add(new Ingredient(consumable,quantity));
        }
        Recipe recipe = new Recipe(name,description,instructions,time,peopleAmount,type,ingredients);
        
        recipeDAO.deleteIngredients(recipe);
        recipeDAO.editRecipe(recipe);
        recipeDAO.insertIngredients(recipe);
    }
}
