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

    private final JDBC jdbc;
    
    public PostgresConsumableDAO() {
        jdbc = JDBC.getInstance();
    }
    
    @Override
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
    
    
    @Override
    public Consumable getConsumable(String name){
        Consumable consumable = null;
        try {
            String query = "SELECT name, description, instructions, timerecipe, peopleamount, title, c.idfood "
                    + "FROM public.recipe r, public.type t, public.consumable c, public.recipecontain rc "
                    + "WHERE r.idtype = t.idtype AND rc.idrecipe = r.idrecipe AND c.name = '" + name + "'";
            ResultSet res = jdbc.select(query);
            if(res.next()){
                if(res.getString("idfood") == null){
                    consumable  = new Recipe(res.getString("name"),
                            res.getString("description"),
                            res.getString("instructions"),
                            Integer.parseInt(res.getString("timerecipe")),
                            Integer.parseInt(res.getString("peopleamount")),
                            res.getString("title")); 
                    ArrayList<Ingredient> ingredients = getIngredients((Recipe)consumable);
                    consumable.setIngredients(ingredients);
                }
                else{
                    System.out.print("test :" + name);
                    consumable = new Food(res.getString("name"));
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumable;
    }
    
    @Override
    public ArrayList<Consumable> getRecipes(){
        ArrayList<Consumable> consumables = new ArrayList<>();
        try {
            String query = "SELECT * FROM public.recipe r, public.type t, public.consumable c, public.recipecontain rc "
                    + "WHERE r.idtype = t.idtype  AND r.idrecipe = c.idrecipe";
            ResultSet res = jdbc.select(query);
            String currentRecipe;
            
            while(res.next()){
                Recipe recipe  = new Recipe(res.getString("name"),
                        res.getString("description"),
                        res.getString("instructions"),
                        Integer.parseInt(res.getString("timeRecipe")),
                        Integer.parseInt(res.getString("peopleAmount")),
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
    
    @Override
    public ArrayList<Ingredient> getIngredients(Recipe recipe){
        ArrayList<Ingredient> ingredients = null;
        try {
            String query = "SELECT name, quantity FROM public.recipecontain rc, public.recipe r, public.consumable c WHERE rc.idrecipe =" +
                "(SELECT r2.idrecipe FROM public.recipe r2, public.consumable c2 WHERE r2.idrecipe = c2.idrecipe AND c2.name = '" + recipe.getName() + "')" +
                "AND rc.idconsumable = c.idconsumable";
            ResultSet res = jdbc.select(query);
            ingredients = new ArrayList<>();
            while(res.next()){
                Consumable consumable;
                consumable = getConsumable(res.getString("name"));
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

    @Override
    public ArrayList<String> getStringConsumables() {
        ArrayList<String> consumables = new ArrayList<>();
        try {
            String query = "SELECT name FROM public.consumable ORDER BY name";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                consumables.add(res.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConsumableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
    
}
