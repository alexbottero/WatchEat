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
    
    public ArrayList<Menu> getMenus();
    
    public Menu getMenu(String name);
    
    public ArrayList<Menu> getAllMenusFromUser(User user);
    
    /**
     * @param Menu
     */
    public void deleteMenu(Menu menu);

    

    /**
     * @param name 
     * @param desc 
     * @param consumables
     */
    public void updateMenu(String name, String desc, ArrayList<Consumable> consumables) ;

    /**
     * @param name 
     * @param desc 
     * @param consumables 
     * @param dateMenu 
     * @param author;Nutritionist 
     * @return
     */
    public void createMenu(String name, String desc, ArrayList<Consumable> consumables, Date dateMenu, User author);
    
}
