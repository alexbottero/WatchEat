/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Request;
import Facade.UserFacade;
import JDBC.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandre
 */
public class PostgresRequestDAO implements RequestDAO {
     private JDBC jdbc;
     
     public PostgresRequestDAO() {
        jdbc = JDBC.getInstance();
    }
    @Override
    public void createRequest(Request request, String id) {
        String query= "INSERT INTO public.menurequest(description, state, iduser) VALUES('"+ request.getDesc() +"','"+request.getEtat()+"',"+id+")";
        try {
            jdbc.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Request> getRequests(String id) {
         ArrayList<Request> requests = new ArrayList<>();
        try {
            String query = "SELECT * FROM public.menurequest where iduser='"+id+"'";
            ResultSet res = jdbc.select(query);
            while(res.next()){
                String desc = res.getString("description");
                String state = res.getString("State");
                requests.add(new Request(desc,state));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConsumableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }
}
