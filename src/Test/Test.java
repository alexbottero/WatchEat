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
import DAO.PostgresRecipeDAO;
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
 * Classe créée pour faire les test sur les requêtes Sql
 * @author julia
 */
//Fichier test pour les menus MenuDAO
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
                    "WHERE m.name = '"+ name+ "' AND u.mail = '" + user.getMail()+ "' AND m.iduser = u.iduser";
            System.out.println("query declared");
            ResultSet res = jdbc.select(query);
            System.out.println("query passed");
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
    public ArrayList<Menu> getMenusFromUser(User user) {
        // Hamelina : ajoute liens avec les autres classes
        ArrayList<Menu> menus = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT(m.name), m.description, m.datecreation, \n" +
             "u.lastname, u.firstname, u.mail, u.pwd\n" +
            "FROM public.menu m, public.user u\n" +
            "WHERE m.iduser =u.iduser AND u.mail = '"+ user.getMail()+ "'\n"+
            "ORDER BY m.name";
            ResultSet res = jdbc.select(query);
            
            while(res.next()) {
                /*User u = new User(res.getString("mail"),
                res.getString("pwd"),
                res.getString("lastname"),
                res.getString("firstname"));*/
                System.out.println(res.getString("name"));
                menus.add(new Menu(res.getString("name"), res.getString("description"), res.getDate("datecreation"), user));
            }
            /*for (Menu m : menus){
                m.addListConsumable(getAllFoodFromMenu(m));
                m.addListConsumable(getAllRecipeFromMenu(m));
            }*/
        }catch (SQLException ex) {
            Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menus;
    }
    PostgresFoodDAO fDAO = new PostgresFoodDAO();
    
    public ArrayList <Consumable> getAllFoodFromMenu(Menu m) throws SQLException{
        ArrayList <Consumable> consumables = new ArrayList <Consumable>();
        try {
            String query = "SELECT DISTINCT(c.name)"+
                "FROM public.food f, public.menu m, public.consumable c, public.containconsumable cc \n" +
                "WHERE  f.idconsumable = c.idconsumable AND m.idmenu =cc.idmenu AND cc.idconsumable = c.idconsumable AND m.name = '" + m.getName() + "' \n"+
                "ORDER BY c.name";
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
            //this.fillWithRecipeFromMenu(m);
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
               System.out.println(res.getString("name"));
               consumables.add(fDAO.getFood(res.getString("name")));
               //m.addConsumable(fDAO.getFood(res.getString("name")));
               //System.out.println(f.getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
    PostgresRecipeDAO rDAO = new PostgresRecipeDAO();
    
    public ArrayList <Consumable> getAllRecipeFromMenu(Menu m) throws SQLException{
        ArrayList <Consumable> consumables = new ArrayList <Consumable>();
        try {
            String query = "SELECT DISTINCT(c.name)"+
                "FROM public.recipe r, public.menu m, public.consumable c, public.containconsumable cc \n" +
                "WHERE  r.idconsumable = c.idconsumable AND m.idmenu =cc.idmenu AND cc.idconsumable = c.idconsumable AND m.name = '" + m.getName() + "' \n" +
                "ORDER BY c.name";
            ResultSet res = jdbc.select(query);
            while(res.next()){
               System.out.println(res.getString("name"));
               consumables.add(rDAO.getRecipe(res.getString("name")));
               //m.addConsumable(fDAO.getFood(res.getString("name")));
               //System.out.println(f.getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
    
    
    /*public static void main(String[] args) throws SQLException{ 
        Test t;
        t = new Test();
        User u = new User("test","chocolat", "test");
        Menu m;
        m = t.getMenu("Recette test", u);
        String name;
        ArrayList<Menu> a = t.getMenusFromUser(u);
        //t.getAllFoodFromMenu(a.get(1));
       // ArrayList <Menu> menus = t.getAllMenus(u);
        
        //name = m.getName();
        //System.out.println(m);
    }*/
}
//Prendre la liste des menus  -- OK
// Pour le nom d'un menu donné, récupérer la liste de ses consommables.
    // - food
    // - recette
