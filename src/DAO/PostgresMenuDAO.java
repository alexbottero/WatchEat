/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


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
                try {
                    Menu menu = new Menu(res.getString("name"),
                            res.getString("description"),
                            (Date) df.parse(res.getString("datecreation")),
                            user);
                } catch (ParseException ex) {
                    Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                try {
                    menu = new Menu(name,
                            res.getString("description"),
                            (Date) df.parse(res.getString("datecreation")));
                } catch (ParseException ex) {
                    Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("test");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresMenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menu;
    }


}
