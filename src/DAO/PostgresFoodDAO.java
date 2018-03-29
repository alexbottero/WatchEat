/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Food;
import BL.NFQuantity;
import BL.NutritiveValue;
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
public class PostgresFoodDAO implements FoodDAO {
    private final JDBC jdbc;
    
    public PostgresFoodDAO() {
        jdbc = JDBC.getInstance();
    }

    @Override
    public Food getFood(String name) {
        Food food = null;
        try {
            String query = "SELECT c.name as namefood, nfc.quantity, nv.name as nameNv, nv.unity FROM public.food f, public.consumable c, public.nfcontained nfc, public.nutritiveValue nv " +
                    "WHERE f.idconsumable = c.idconsumable AND f.idfood = nfc.idfood AND nfc.idNutritiveValue = nv.idNutritiveValue AND c.name='" + name + "'";
            ResultSet res = jdbc.select(query);
            if(res.next()){
                String foodName = res.getString("namefood");
                ArrayList<NFQuantity> nutritiveValues = new ArrayList<>();
                NutritiveValue nutritiveValue = new NutritiveValue(res.getString("nameNv"), res.getString("unity"));
                NFQuantity nfQuantity = new NFQuantity(nutritiveValue,Integer.parseInt(res.getString("quantity")));
                nutritiveValues.add(nfQuantity);
                while(res.next()){
                    nutritiveValue = new NutritiveValue(res.getString("nameNv"), res.getString("unity"));
                    nfQuantity = new NFQuantity(nutritiveValue,Integer.parseInt(res.getString("quantity")));
                    nutritiveValues.add(nfQuantity);
                }
                food = new Food(foodName,nutritiveValues);
            }
            //Ca peut ne reint renvoyé juste parce qu'il n'y a pas de valeur nutritive lié, donc on test simplement
            else{
                query = "SELECT name FROM public.food f, public.consumable c " +
                    "WHERE f.idconsumable = c.idconsumable AND c.name='" + name + "'";
                res = jdbc.select(query);
                if(res.next()){
                    food = new Food(res.getString("name"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return food;
    }

    @Override
    public ArrayList<Food> getFood(){
        ArrayList<Food> consumables = new ArrayList<>();
        try {
            String query = "SELECT name FROM public.food";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                consumables.add(getFood(res.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumables;
    }
}
