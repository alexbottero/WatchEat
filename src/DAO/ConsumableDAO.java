/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Ingredient;
import BL.Recipe;
import java.util.ArrayList;

/**
 *
 * @author fabaz
 */
public interface ConsumableDAO {
    
    public ArrayList<Consumable> getConsumables();
    public Consumable getConsumable(String name);
    public ArrayList<Consumable> getFood();
    public ArrayList<Consumable> getRecipes();
    public ArrayList<Ingredient> getIngredients(Recipe recipe);

    public ArrayList<String> getStringConsumables();
}
