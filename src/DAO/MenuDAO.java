/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Menu;
import BL.User;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Polytech
 */
public interface MenuDAO {
    
    /**
     * get all menus
     * @return ArrayList<Menu>
     */
    public ArrayList<Menu> getMenus();
    
    /**
     * Get a menu with his name
     * @param name of the menu
     * @return Menu
     */
    public Menu getMenu(String name);
    
    /**
     * Get all menus written by this user
     * @param user User
     * @return ArrayList<Menu>
     */
    public ArrayList<Menu> getAllMenusFromUser(User user);
    
    /**
     * Get the menu link to this name and this user author
     * @param name of the menu
     * @param user creator of the menu
     * @return Menu
     */
    public Menu getMenu(String name, User user);
    
    /**
     * Delete the menu in param from the database
     * @param Menu to delete
     */
    public void deleteMenu(Menu menu);

    

    /**
     * Update the menu with this parameters
     * @param name String
     * @param desc String
     * @param consumables ArrayList<Consumable>
     */
    public void updateMenu(String name, String desc, ArrayList<Consumable> consumables) ;

    /**
     * Create a menu with this parameters
     * @param name String : name of the menu
     * @param desc String : the description
     * @param consumables ArrayList<Consumable>
     * @param dateMenu Date
     * @param author Nutritionist 
     * @return
     */
    public void createMenu(String name, String desc, ArrayList<Consumable> consumables, Date dateMenu, User author);
    
}
