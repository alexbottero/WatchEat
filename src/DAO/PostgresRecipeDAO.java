/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Food;
import BL.Ingredient;
import BL.Menu;
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
                System.out.println(ingredient.getConsumable().getName());
                query = "SELECT idconsumable FROM public.consumable WHERE name = '" + ingredient.getConsumable().getName() + "'";
                res = jdbc.select(query);
                res.next();
                int idIngredientConsumable = res.getInt("idconsumable");
                
                query = "SELECT idrecipe FROM public.recipe r, public.consumable c WHERE r.idconsumable = " + idconsumable;
                res = jdbc.select(query);
                res.next();
                int idrecipe = res.getInt("idrecipe");
                
                query = "INSERT INTO public.recipecontain (idconsumable,idrecipe,quantity)VALUES("
                        + idIngredientConsumable + ","
                        + idrecipe + ","
                        + ingredient.getQuantity() + ")";
                jdbc.update(query);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Recipe> getRecipes(){
        return getRecipes("","",999);
    }
    
    public ArrayList<Ingredient> getIngredients(Recipe recipe){
        PostgresFoodDAO foodDAO = new PostgresFoodDAO();
        PostgresRecipeDAO recipeDAO = new PostgresRecipeDAO();
        
        ArrayList<Ingredient> ingredients = null;
        try {
            String query = "SELECT Distinct(name), quantity FROM public.recipecontain rc, public.recipe r, public.consumable c WHERE rc.idrecipe =" +
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
    
    //Julia : pour get les recettes d'un menu

    /**
     *
     * @param m
     * @return
     * @throws SQLException
     */
    
    @Override
    public ArrayList <Recipe> getAllRecipeFromMenu (Menu menu) throws SQLException{
        ArrayList <Recipe> recipes;
        recipes = new ArrayList <Recipe>();
        try {
            String query = "SELECT DISTINCT(c.name)"+
                "FROM public.recipe r, public.menu m, public.consumable c, public.containconsumable cc \n" +
                "WHERE  r.idconsumable = c.idconsumable AND m.idmenu =cc.idmenu AND cc.idconsumable = c.idconsumable AND m.name = '" + menu.getName() + "' \n" +
                "ORDER BY c.name";
            ResultSet res = jdbc.select(query);
            while(res.next()){
               recipes.add(getRecipe(res.getString("name")));
               //m.addConsumable(fDAO.getFood(res.getString("name")));
               //System.out.println(f.getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipes;
    }
    

    @Override
    public ArrayList<Recipe> getRecipes(String name, String type, int timeMax) {
        ArrayList<Recipe> consumables = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT(c.name), r.description, r.instructions, t.title, r.timerecipe, r.peopleamount, \n" +
                "u.lastname, u.firstname, u.mail, u.pwd\n" +
                "FROM public.recipe r, public.type t, public.consumable c, public.recipecontain rc, public.user u\n" +
                "WHERE r.idtype = t.idtype  AND r.idconsumable = c.idconsumable AND r.iduser =u.iduser "
                    + "AND c.name LIKE '%" + name + "%' AND t.title LIKE '%" + type + "%' AND r.timerecipe <= " + timeMax ;
            ResultSet res = jdbc.select(query);
            String currentRecipe;
            
            while(res.next()){
                User user = new User(res.getString("mail"),
                        res.getString("pwd"),
                        res.getString("lastname"),
                        res.getString("firstname"));
                Recipe recipe  = new Recipe(res.getString("name"),
                        res.getString("description"),
                        res.getString("instructions"),
                        Integer.parseInt(res.getString("timeRecipe")),
                        Integer.parseInt(res.getString("peopleAmount")),
                        res.getString("title"),
                        user); 
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
    public ArrayList<Recipe> getRecipes(String name, String type, int timeMax, User user) {
        ArrayList<Recipe> consumables = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT(c.name), r.description, r.instructions, t.title, r.timerecipe, r.peopleamount, \n" +
                "u.lastname, u.firstname, u.mail, u.pwd\n" +
                "FROM public.recipe r, public.type t, public.consumable c, public.recipecontain rc, public.user u\n" +
                "WHERE r.idtype = t.idtype  AND r.idconsumable = c.idconsumable AND r.iduser =u.iduser "
                    + "AND c.name LIKE '%" + name + "%' AND t.title LIKE '%" + type + "%' AND r.timerecipe <= " + timeMax + " AND u.mail = '" + user.getMail() + "';";
            ResultSet res = jdbc.select(query);
            String currentRecipe;

            while(res.next()){
                Recipe recipe  = new Recipe(res.getString("name"),
                        res.getString("description"),
                        res.getString("instructions"),
                        Integer.parseInt(res.getString("timeRecipe")),
                        Integer.parseInt(res.getString("peopleAmount")),
                        res.getString("title"),
                        user); 
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
    public void deleteRecipe(Recipe recipe) {
        try {
            String query = "SELECT idrecipe, c.idconsumable FROM public.recipe r, public.consumable c WHERE r.idconsumable = c.idconsumable AND name = '" + recipe.getName() +"'";
            ResultSet res = jdbc.select(query);
            res.next();
            int idrecipe = res.getInt("idrecipe");
            int idconsumable = res.getInt("idconsumable");
            System.out.println("idrecipe : " + idrecipe);
            System.out.println("idconsumable : " + idconsumable);
            
            query = "DELETE FROM public.recipecontain WHERE idrecipe = " + idrecipe + " OR idconsumable = " + idconsumable + ";"
                    + "DELETE FROM public.comment WHERE idconsumable = " + idconsumable + ";"
                    + "DELETE FROM public.recipe WHERE idrecipe = " + idrecipe + ";"
                    + "DELETE FROM public.consumable WHERE idconsumable = " + idconsumable + ";";
            jdbc.update(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}