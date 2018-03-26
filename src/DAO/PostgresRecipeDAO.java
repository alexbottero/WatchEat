/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Ingredient;
import BL.Recipe;
import JDBC.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabaz
 */
public class PostgresRecipeDAO implements RecipeDAO{
    
    private JDBC jdbc;
    
    public PostgresRecipeDAO(){
        jdbc = JDBC.getInstance();
    }

    @Override
    public ArrayList<String> getTypes() {
        ArrayList<String> types = new ArrayList<String>();
        try {
            String query = "SELECT * FROM public.type";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                String type  = res.getString("title"); 
                types.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }

    @Override
    public void createRecipe(Recipe recipe) {
        try {
            String query = "SELECT idtype FROM public.type WHERE title = '" + recipe.getType() + "'";
            ResultSet res = jdbc.select(query);
            res.next();
            int idtype = Integer.parseInt(res.getString("idtype"));
            
            query = "INSERT INTO public.recipe (description,instructions,timerecipe,peopleamount,idtype)"
                    + "VALUES('" + recipe.getName() + "',"
                    + "'" + recipe.getInstructions() + "',"
                    + recipe.getTimeRecipe() + ","
                    + recipe.getPeopleAmount() + ","
                    + idtype + ");";
            jdbc.update(query);
            
            query = "SELECT MAX(idrecipe) as idrecipe FROM public.recipe;";
            res = jdbc.select(query);
            res.next();
            int idrecipe = Integer.parseInt(res.getString("idrecipe"));
            
            query = "INSERT INTO public.consumable (idrecipe,name)VALUES(" + idrecipe + ", '"+ recipe.getName() +"')";
            jdbc.update(query);
            
            for(Ingredient ingredient : recipe.getIngredients()){
                System.out.println(ingredient.getConsumable());
                /*query = "SELECT idconsumable FROM public.consumable WHERE name = '" + ingredient.getConsumable().getName() + "'";
                res = jdbc.select(query);
                res.next();
                int idconsumable = Integer.parseInt(res.getString("idconsumable"));
                
                int quantity = ingredient.getQuantity();
                query = "INSERT INTO public.recipecontain (idrecipe,ideconsumable,quantity)VALUES("
                        + idrecipe + "," + idconsumable + "," + quantity + ");";*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
