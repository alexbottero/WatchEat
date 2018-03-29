/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Menu;
import java.util.ArrayList;
/**
 *
 * @author Polytech
 */
public interface MenuDAO {
    
    public ArrayList<Menu> getMenus();
    
    public Menu getMenu(String name);
    
}
