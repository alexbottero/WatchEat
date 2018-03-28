/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Food;
import java.util.ArrayList;

/**
 *
 * @author fabaz
 */
public interface FoodDAO {
    
    public Food getFood(String name);
    
    public ArrayList<Food> getFood();
}
