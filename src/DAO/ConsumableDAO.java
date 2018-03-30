/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Food;
import BL.Ingredient;
import BL.Recipe;
import java.util.ArrayList;

/**
 *
 * @author fabaz
 */
public interface ConsumableDAO {
    
    public ArrayList<String> getStringConsumables();
}
