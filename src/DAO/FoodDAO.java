/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Food;
import BL.Menu;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fabaz
 */
public interface FoodDAO {
    
    public Food getFood(String name);
    
    public ArrayList<Food> getFood();
    
    public ArrayList<Food> getAllFoodFromMenu(Menu menu)throws SQLException;
}
