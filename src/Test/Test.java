/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BL.*;
import BL.Menu;
import BL.User;
import DAO.PostgresFoodDAO;
import DAO.PostgresMenuDAO;
import JDBC.JDBC;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julia
 */
public class Test {
    
    JDBC jdbc = new JDBC();
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
    
    public Menu getMenu(String name, User user) {
        // Hamelina : ajoute liens avec les autres classes
        //System.out.print("Bonjour");
        Menu menu = null;
        try {
            //System.out.println("test");
            //System.out.println(name);
            //System.out.println(user.getFirstName());
            String query = "SELECT m.name, m.description, m.datecreation " +
                    "FROM public.menu m, public.user u " +
                    "WHERE m.name = '"+ name+ "' AND u.firstname = '" + user.getFirstName()+ "' AND m.iduser = u.iduser ;";
            System.out.println("query declared");
            ResultSet res = jdbc.select(query);
            //System.out.println("result set ok");
            while(res.next()){
                System.out.println("enter while");
                System.out.print(res.getDate("datecreation"));
                menu = new Menu(name,
                        res.getString("description"),
                        res.getDate("datecreation"), user);
                //System.out.println("test");
                
            }
        } catch (SQLException ex) {
            System.out.println("bloc catch");
            Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("end bloc catch");
        if (menu != null){
            System.out.println("CC");
            //System.out.println(menu.getDescription());
        }
        return menu;
    }
    
    //pour un nutri
    public ArrayList<Menu> getMenus(User user) {
        // Hamelina : ajoute liens avec les autres classes
        ArrayList<Menu> menus = new ArrayList<>();
        try {
            String query = "SELECT m.name, m.description, m.datecreation, \n" +
             "u.lastname, u.firstname, u.mail, u.pwd\n" +
            "FROM public.menu m, public.user u\n" +
            "WHERE m.iduser =u.iduser AND u.firstname = '"+ user.getFirstName()+ "';";
            ResultSet res = jdbc.select(query);
            
            while(res.next()) {
                /*User u = new User(res.getString("mail"),
                res.getString("pwd"),
                res.getString("lastname"),
                res.getString("firstname"));*/
                menus.add(new Menu(res.getString("name"), res.getString("description"), res.getDate("datecreation"), user));
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menus;
    }
    
    //pour un user pas nutri
    public ArrayList<Menu> getAllMenus(User user) {
        // Hamelina : ajoute liens avec les autres classes
        ArrayList<Menu> menus = new ArrayList<>();
        try {
            String query = "SELECT m.name, m.description, m.datecreation, \n" +
             "u.lastname, u.firstname, u.mail, u.pwd\n" +
            "FROM public.menu m, public.user u\n" +
            "WHERE m.iduser =u.iduser AND u.firstname = '"+ user.getFirstName()+ "';";
            ResultSet res = jdbc.select(query);
            
            while(res.next()) {
                System.out.println(res.getString("name"));
                User u = new User(res.getString("mail"),
                res.getString("pwd"),
                res.getString("lastname"),
                res.getString("firstname"));
                menus.add(new Menu(res.getString("name"), res.getString("description"), res.getDate("datecreation"), u));
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menus;
    }
    
    public void fillConsumableFromMenu(ArrayList<Menu> listMenu){
        for (Menu m: listMenu){
            this.fillWithFoodFromMenu(m);
            this.fillWithRecipeFromMenu(m);
            for(Consumable c: m.getConsumableList()){
                this.fillNFQuantityFromFood(c);
                this.fillIngredientFromRecipe(c);
            }
        }
    }
    
    public void fillNFQuantityFromFood(Consumable c){
        
    }
    
    public void fillIngredientFromRecipe(Consumable c){
        
    }
    
    
    
    public void fillWithRecipeFromMenu(Menu m, User user){
        PostgresRecipeDAO rDAO = new PostgresRecipeDAO();
        ArrayList<Consumable> listConsumable = new ArrayList<Consumable>();
         try {
            String query = "SELECT DISTINCT(c.name), r.description, r.instructions, t.title, r.timerecipe, r.peopleamount, \n" +
                "FROM public.recipe r, public.type t, public.consumable c, public.recipecontain rc, public.user u\n" +
                "WHERE r.idtype = t.idtype  AND r.idconsumable = c.idconsumable AND r.iduser =r.iduser AND u.firstname = '"+ user.getFirstName();
            ResultSet res = jdbc.select(query);
            String currentRecipe;
            
            while(res.next()){
                Consumable recipe  = new Recipe(res.getString("name"),
                        res.getString("description"),
                        res.getString("instructions"),
                        Integer.parseInt(res.getString("timeRecipe")),
                        Integer.parseInt(res.getString("peopleAmount")),
                        res.getString("title"),
                        user); 
                ArrayList<Ingredient> ingredients = rDAO.getIngredients(recipe);
                recipe.setIngredients(ingredients);
                m.addConsumable(recipe);
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return menus;
        //return listConsumable;
    }
    
    
    
    
    public void fillWithFoodFromMenu(Menu m){
        ArrayList<Consumable> listConsumable = new ArrayList<Consumable>();
        try {
            String query = "SELECT name FROM public.consumable, public.menu m, public.user u, public.containconsumabble cc, public.food f, public.nfcontained nf  WHERE cc.idconsumable = c.idconsumable AND cc.menu = m.idmenu AND m.iduser = u.iduser AND f.idconsumable = c.idconsumable AND nf.idfood = f.idfood AND cc.idmenu = m.idmenu AND u.firstname = '"+ m.getCreator().getFirstName() + "' ORDER BY c.name;";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                m.getConsumableList().add(new Food(res.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return menus;
        //return listConsumable;
    }
    
    
    /*public abstract class Consumable implements Comparable {
    String name;
    Food food;
    Recipe recipe;
    
    public Consumable(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    @Override
    public int compareTo(Object c){
        return (this.name.compareTo(((Consumable)c).getName()));
    }

    public abstract void setIngredients(ArrayList<Ingredient> ingredients);
}*/
    
    
    public static void main(String[] args){ 
        Test t;
        t = new Test();
        User u = new User("test","chocolat", "test");
        Menu m;
        m = t.getMenu("Recette test", u);
        String name;
        ArrayList<Menu> a = t.getAllMenus(u);
        
        //name = m.getName();
        //System.out.println(m);
    }
}
