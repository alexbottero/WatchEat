/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BL.Request;
import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
public interface RequestDAO {
    public void createRequest(Request request, String id);
    public ArrayList<Request> getRequests(String id);

}
