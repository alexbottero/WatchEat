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
    
    public ObservableList<String> getTypes(){
        ArrayList<String> types = recipeDAO.getTypes();
        return FXCollections.observableArrayList(types);
    }
    
    public ObservableList<String> getConsumables(){
        ArrayList<String> consumables = consumableDAO.getStringConsumables();
        Collections.sort(consumables);
        return FXCollections.observableArrayList(consumables);
    }
    
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

    public ArrayList<Recipe> getRecipes() {
        return recipeDAO.getRecipes();
    }

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

    public void deleteRecipe(Recipe recipe) {
        recipeDAO.deleteRecipe(recipe);
    }

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
