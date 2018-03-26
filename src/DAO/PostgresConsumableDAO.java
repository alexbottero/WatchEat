/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Food;
import BL.Ingredient;
import BL.Recipe;
import JDBC.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabaz
 */
public class PostgresConsumableDAO implements ConsumableDAO {

    private JDBC jdbc;
    
    public PostgresConsumableDAO() {
        jdbc = JDBC.getInstance();
    }
    
    public ArrayList<Consumable> getFood(){
        ArrayList<Consumable> consumables = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT name FROM public.food f, public.consumable c WHERE f.idconsumable = c.idconsumable";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                Food food  = new Food(res.getString("name")); 
                consumables.add(food);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
    public Recipe getRecipe(String name){
        Recipe recipe = null;
        try {
            String query = "SELECT * FROM public.recipe r, public.type t, public.consumable c, public.recipecontain rc "
                    + "WHERE r.idtype = t.idtype AND rc.idrecipe = r.idrecipe AND r.idconsumable = c.idconsumable AND c.name =" + name;
            ResultSet res = jdbc.select(query);
            if(res.next()){
                recipe  = new Recipe(res.getString("name"),
                        res.getString("description"),
                        res.getString("instructions"),
                        res.getString("timeRecipe"),
                        res.getString("peopleAmount"),
                        res.getString("title")); 
                getIngredients(recipe);
                recipe.setIngredients(getIngredients(recipe));
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipe;
    }
    
    public ArrayList<Consumable> getRecipes(){
        ArrayList<Consumable> consumables = new ArrayList<>();
        try {
            String query = "SELECT * FROM public.recipe r, public.type t, public.consumable c, public.recipecontain rc "
                    + "WHERE r.idtype = t.idtype AND rc.idrecipe = r.idrecipe AND r.idconsumable = c.idconsumable";
            ResultSet res = jdbc.select(query);
            String currentRecipe;
            
            while(res.next()){
                Recipe recipe  = new Recipe(res.getString("name"),
                        res.getString("description"),
                        res.getString("instructions"),
                        res.getString("timeRecipe"),
                        res.getString("peopleAmount"),
                        res.getString("title")); 
                ArrayList<Ingredient> ingredients = getIngredients(recipe);
                recipe.setIngredients(ingredients);
                consumables.add(recipe);
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
    
    public ArrayList<Ingredient> getIngredients(Recipe recipe){
        ArrayList<Ingredient> ingredients = null;
        try {
            String query = "SELECT name, quantity, c.idrecipe FROM public.recipecontain rc, public.recipe r, public.consumable c WHERE rc.idrecipe =" +
                "(SELECT r2.idrecipe FROM public.recipe r2, public.consumable c2 WHERE r2.idconsumable = c2.idconsumable AND c2.name = '" + recipe.getName() + "')" +
                "AND rc.idconsumable = c.idconsumable";
            ResultSet res = jdbc.select(query);
            ingredients = new ArrayList<>();
            while(res.next()){
                Consumable consumable;
                if(res.getString("idrecipe") == null){
                    consumable = new Food(res.getString("idrecipe"));
                }else{
                    consumable = getRecipe(res.getString("name"));
                }
                Ingredient ingredient = new Ingredient(consumable,Integer.parseInt(res.getString("quantity")));
                ingredients.add(ingredient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingredients;
    }

    @Override
    public ArrayList<Consumable> getConsumables() {
        ArrayList<Consumable> res = getRecipes();
        res.addAll(getFood());
        return res;
    }
    
}
