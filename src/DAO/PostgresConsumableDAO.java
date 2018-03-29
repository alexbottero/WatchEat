/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Food;
import BL.Ingredient;
import BL.NFQuantity;
import BL.NutritiveValue;
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
    public Consumable getConsumable(String name){
        /*Consumable consumable = null;
        try {
            
            //Cherche dans la table food
            Food food = getFood(name);
            if(food != null){
                return food;
            }
            else{
                getRecipe(name);
            }
            
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
                    consumable = new Food(res.getString("name"));
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumable;*/
        return null;
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

}