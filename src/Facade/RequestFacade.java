/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;
import DAO.RequestDAO;
import BL.Request;
import DAO.DAOFactory;
import DAO.PostgresDAOFactory;
import DAO.UserDAO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alexandre
 */
public class RequestFacade {
    
   private RequestDAO requestDAO;
   private DAOFactory daoFactory;
   private UserDAO userDAO;
   
   public RequestFacade(){
        daoFactory = PostgresDAOFactory.getInstance();
        requestDAO = daoFactory.createRequestDAO();
        userDAO=daoFactory.createUserDAO();
        
   }
   public void create(String desc,String etat){
       Request request = new Request(desc,etat);       
       String id = userDAO.selectUser(UserFacade.connectedUser.getMail());
       requestDAO.createRequest(request,id);
   }
   
   public ObservableList<Request> getRequests(){
        String id = userDAO.selectUser(UserFacade.connectedUser.getMail());
        ArrayList<Request> requests = requestDAO.getRequests(id);
        return FXCollections.observableArrayList(requests);
    }
   
}
