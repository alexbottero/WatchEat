/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import BL.Food;
import BL.Ingredient;
import BL.NFQuantity;
import BL.NutritiveValue;
import BL.Recipe;
import JDBC.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabaz
 */
public class PostgresConsumableDAO implements ConsumableDAO {

    private final JDBC jdbc;
    
    public PostgresConsumableDAO() {
        jdbc = JDBC.getInstance();
    }    
    
    /**
     * get all consumables in string array
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getStringConsumables() {
        ArrayList<String> consumables = new ArrayList<>();
        try {
            String query = "SELECT name FROM public.consumable ORDER BY name";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                consumables.add(res.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConsumableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }    

}

