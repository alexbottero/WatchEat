/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import BL.Consumable;
import BL.Menu;
import BL.Recipe;
import BL.User;
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
 * @author Polytech
 */
public class PostgresMenuDAO implements MenuDAO {
    
    private JDBC jdbc;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
    
    public PostgresMenuDAO(){
        jdbc = JDBC.getInstance();
    }

    @Override
    public ArrayList<Menu> getMenus() {
        // Hamelina : ajoute liens avec les autres classes
        ArrayList<Menu> menus = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT(m.name), m.description, m.datecreation, \n" +
             "u.lastname, u.firstname, u.mail, u.pwd\n" +
            "FROM public.menu m, public.user u\n" +
            "WHERE m.iduser =u.iduser";
            ResultSet res = jdbc.select(query);
            
            while(res.next()) {
                User user = new User(res.getString("mail"),
                        res.getString("pwd"),
                        res.getString("lastname"),
                        res.getString("firstname"));
                    Menu menu = new Menu(res.getString("name"),
                            res.getString("description"),
                            res.getDate("datecreation"),
                            user);
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menus;
    }
    

    @Override
    public Menu getMenu(String name) {
        // Hamelina : ajoute liens avec les autres classes
        Menu menu = null;
        try {
            String query = "SELECT m.name, m.description, m.datecreation " +
                    "FROM public.menu m " +
                    "WHERE m.name = '" + name + "';";
            ResultSet res = jdbc.select(query);
            if(res.next()){
                    menu = new Menu(name,
                            res.getString("description"),
                            res.getDate("datecreation"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menu;
    }
    
    
    //JULIA 
    public Menu getMenu(String name, User user) {
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

    /**
     *
     * @param user
     * @return
     */
    @Override
    public ArrayList<Menu> getAllMenusFromUser(User user) {
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

     /**
     * @param Menu
     */
    @Override
    public void deleteMenu(Menu menu) {
        // TODO implement here
    }

    

    /**
     * @param name 
     * @param desc 
     * @param consumables
     */
    @Override
    public void updateMenu(String name, String desc, ArrayList<Consumable> consumables) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param desc 
     * @param consumables 
     * @param dateMenu 
     * @param author;Nutritionist 
     * @return
     */
    @Override
    public void createMenu(String name, String desc, ArrayList<Consumable> consumables, java.util.Date dateMenu, User author) {
        // TODO implement here

    }

    
 


}
