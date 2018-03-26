/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Food;
import BL.Recipe;
import JDBC.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabaz
 */
public class PostgresConsumableDAO implements ConsumableDAO {

    private JDBC jdbc;
    
    public PostgresConsumableDAO() {
        jdbc = JDBC.getInstance();
    }
    
    public ArrayList<Consumable> getConsumables(){
        ArrayList<Consumable> consumables = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT name FROM public.food f, public.consumable c WHERE f.idconsumable = f.idconsumable";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                Food food  = new Food(res.getString("name")); 
                consumables.add(food);
            }
            query = "SELECT * FROM public.recipe r, public.type t, public.consumable c WHERE r.idtype = t.idtype AND r.idconsumable = c.idconsumable";
            res = jdbc.select(query);
            while(res.next()){
                Recipe recipe  = new Recipe(res.getString("name"),
                        res.getString("description"),
                        res.getString("instructions"),
                        res.getString("timeRecipe"),
                        res.getString("peopleAmount"),
                        res.getString("title")); 
                consumables.add(recipe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
    
}
