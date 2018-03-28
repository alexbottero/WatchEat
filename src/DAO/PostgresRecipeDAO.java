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
import BL.User;
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
    public void createRecipe(Recipe recipe, User user) {
        try {
            String query = "SELECT idtype FROM public.type WHERE title = '" + recipe.getType() + "'";
            ResultSet res = jdbc.select(query);
            res.next();
            int idtype = Integer.parseInt(res.getString("idtype"));
            
            //Inserer le consomable
            query = "INSERT INTO public.consumable (name)VALUES('" + recipe.getName() + "');";
            jdbc.update(query);
            
            // Recuperer l'id du consomable
            query = "SELECT MAX(idconsumable) as idconsumable FROM public.consumable;";
            res = jdbc.select(query);
            res.next();
            int idconsumable = Integer.parseInt(res.getString("idconsumable"));
            
            // Recuperer l'id de l'user
            query = "SELECT iduser FROM public.user WHERE mail = '" + user.getMail() + "';";
            res = jdbc.select(query);
            res.next();
            int iduser = Integer.parseInt(res.getString("iduser"));
            
            //Crée la recette
            query = "INSERT INTO public.recipe (description,instructions,timerecipe,peopleamount,idtype,idconsumable,iduser)"
                    + "VALUES('" + recipe.getDescription() + "',"
                    + "'" + recipe.getInstructions() + "',"
                    + recipe.getTimeRecipe() + ","
                    + recipe.getPeopleAmount() + ","
                    + idtype + ","
                    + idconsumable + ","
                    + iduser + ");";
            jdbc.update(query);
            
            for(Ingredient ingredient : recipe.getIngredients()){
                query = "INSERT INTO public.recipecontain (idconsumbale,idrecipe,quantity)VALUES("
                        + "(SELECT idconsumable FROM public.consumable WHERE name = '" + ingredient.getConsumable().getName() + "'),"
                        + "(SELECT idrecipe FROM public.recipe r, public.consumable c WHERE r.idconsumable = " + idconsumable + "),"
                        + ingredient.getQuantity() + ")";
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> consumables = new ArrayList<>();
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
    
    public ArrayList<Ingredient> getIngredients(Recipe recipe){
        PostgresFoodDAO foodDAO = new PostgresFoodDAO();
        PostgresRecipeDAO recipeDAO = new PostgresRecipeDAO();
        
        ArrayList<Ingredient> ingredients = null;
        try {
            String query = "SELECT name, quantity FROM public.recipecontain rc, public.recipe r, public.consumable c WHERE rc.idrecipe =" +
                "(SELECT r2.idrecipe FROM public.recipe r2, public.consumable c2 WHERE r2.idconsumable = c2.idconsumable AND c2.name = '" + recipe.getName() + "')" +
                "AND rc.idconsumable = c.idconsumable";
            ResultSet res = jdbc.select(query);
            ingredients = new ArrayList<>();
            while(res.next()){
                Consumable consumable;
                Food food = foodDAO.getFood(res.getString("name"));
                if(food != null){
                    consumable = food;
                }else{
                    consumable = recipeDAO.getRecipe(res.getString("name"));
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
    public Recipe getRecipe(String name) {
        Recipe recipe = null;
        try {
            String query = "SELECT c.name, r.description, r.instructions, r.timerecipe, r.peopleamount, t.title " +
                    "FROM public.consumable c, public.recipe r, public.type t " +
                    "WHERE c.idconsumable = r.idconsumable AND r.idtype = t.idtype AND c.name = '" + name + "';";
            ResultSet res = jdbc.select(query);
            if(res.next()){
                recipe = new Recipe(name,
                    res.getString("description"),
                    res.getString("instructions"),
                    Integer.parseInt(res.getString("timerecipe")),
                    Integer.parseInt(res.getString("peopleamount")),
                    res.getString("title"));
                System.out.println("test");
                recipe.setIngredients(getIngredients(recipe));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipe;
    }
}
