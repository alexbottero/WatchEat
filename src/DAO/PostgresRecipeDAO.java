/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class PostgresRecipeDAO implements RecipeDAO{
    
    private JDBC jdbc;
    
    public PostgresRecipeDAO(){
        jdbc = JDBC.getInstance();
    }

    @Override
    public ArrayList<String> getTypes() {
        ArrayList<String> types = new ArrayList<String>();
        try {
            String query = "SELECT * FROM public.type";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                String type  = res.getString("title"); 
                types.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresRecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
    
}
