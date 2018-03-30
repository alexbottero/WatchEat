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

    /**
     *create a request in db
     * @param request Request 
     * @param id String the user id
     */
    public void createRequest(Request request, String id);

    /**
     *get all user's request in db
     * @param id String user id
     * @return ArrayList Request
     */
    public ArrayList<Request> getRequests(String id);

}
