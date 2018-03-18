/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import DAO.DAOFactory;
import DAO.PostgresDAOFactory;
import DAO.UserDAO;

/**
 *
 * @author fabaz
 */
public class HomePageFacade {
    
    private UserDAO userDAO;
    
    public HomePageFacade(){
        DAOFactory fact = PostgresDAOFactory.getInstance();
        this.userDAO = fact.createUserDAO();
    }
    
    public void deconnection() {
        userDAO.deconnection();
    }
    
}
