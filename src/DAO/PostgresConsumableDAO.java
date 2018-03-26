/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Consumable;
import JDBC.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String query = "SELECT * FROM public.consumable ORDER BY name";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                Consumable consumable  = new Consumable(res.getString("name"),null,null); 
                consumables.add(consumable);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
    
}
