/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Ingredient;
import BL.Menu;
import BL.Recipe;
import BL.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fabaz
 */
public interface RecipeDAO {
    
    /**
     * Get the all the type in database into array string
     * @return ArrayList<String>
     */
    public ArrayList<String> getTypes();

    /**
     * Insert the recipe into the database
     * @param recipe 
     * @param user User : author of the recipe
     */
    public void createRecipe(Recipe recipe, User user);
    
    /**
     * Return all recipes of the database
     * @return ArrayList<Recipe>
     */
    public ArrayList<Recipe> getRecipes();
    
    /**
     * Get a recipe by name in the database
     * @param name String : name of the recipe
     * @return Recipe : the recipe found
     */
    public Recipe getRecipe(String name);

    /**
     * Get the recipes that correspond to this name (a part of), type and time max from database
     * @param name String : the string that should contain the name of the recipe
     * @param type String : the type of the recipe found
     * @param timeMax int : the maximum time recipes found
     * @return ArrayList<Recipe>
     */
    public ArrayList<Recipe> getRecipes(String name, String type, int timeMax);

    /**
     * Get recipes with a part of the name, a type, a time max and an user author from database
     * @param name String : the name of the recipe should contain this string
     * @param type String : the type of the recipes
     * @param timeMax int : the max time of the recipes
     * @param user User : the author of the recipes
     * @returnArrayList<Recipe> : found recipes
     */
    public ArrayList<Recipe> getRecipes(String name, String type, int timeMax, User user);
    
    /**
     * Get all the recipe of a menu in the database
     * @param menu Menu : the menu in whih we want to find recipes
     * @return ArrayList <Recipe>
     * @throws SQLException
     */
    public ArrayList<Recipe> getAllRecipeFromMenu(Menu menu) throws SQLException;

    /**
     * Delete from the database the recipe
     * @param recipe Recipe
     */
    public void deleteRecipe(Recipe recipe);
    
    /**
     * Delete from database the ingredients of this recipe
     * @param recipe Recipe
     */
    public void deleteIngredients(Recipe recipe);

    /**
     * Upate the recipe with this new caracteristics without ingredients into the database
     * @param recipe Recipe
     */
    public void editRecipe(Recipe recipe);

    /**
     * Insert the ingredients of this recipe into the database
     * @param recipe Recipe
     */
    public void insertIngredients(Recipe recipe);
    
    /**
     * Get all the indregients of a recipe
     * @param recipe Recipe
     * @return ArrayList<Ingredient>
     */
    public ArrayList<Ingredient> getIngredients(Recipe recipe);
}
