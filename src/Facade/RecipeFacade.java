/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BL.Consumable;
import BL.Ingredient;
import BL.Recipe;
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
    private ConsumableDAO consumableDAO;
    
    public RecipeFacade(){
        daoFactory = PostgresDAOFactory.getInstance();
        recipeDAO = daoFactory.createRecipeDAO();
        consumableDAO = daoFactory.createConsumableDAO();
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
            Consumable consumable = consumableDAO.getConsumable(name);
            int quantity = ingredientsQuantity.get(ingredientsName.indexOf(ingredientName));
            ingredients.add(new Ingredient(consumable,quantity));
        }
        int time = Integer.parseInt(timeString);
        int peopleAmount = Integer.parseInt(peopleAmountString);
        Recipe recipe = new Recipe(name,description,instructions,time,peopleAmount,type,ingredients);
        recipeDAO.createRecipe(recipe);
    }

    public ObservableList<String> getStringConsumables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
