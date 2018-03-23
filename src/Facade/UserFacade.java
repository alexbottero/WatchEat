package Facade;


import BL.User;
import DAO.DAOFactory;
import DAO.PostgresDAOFactory;
import DAO.UserDAO;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 */
public class UserFacade {
    
    private UserDAO userDAO;
    private User connectedUser;

    /**
     * Default constructor
     */
    public UserFacade() {
        //connectedUser=new User(mail,password);
        DAOFactory fact = PostgresDAOFactory.getInstance();
        userDAO = fact.createUserDAO();
    }


    /**
     * @param mail 
     * @param pwd
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean login(String mail, String pwd) throws SQLException{
        User user = userDAO.find(mail);
        if(user == null){
            return false;
        }
        else{
            boolean succeedLogin = user.login(mail,pwd);
            if(succeedLogin){
                this.connectedUser = user;
            }
            return succeedLogin;
        }
        
    }

    /**
     * @param mail
     */
    public void getFirstNameByMail(String mail) {
        // TODO implement here
    }

    /**
     * @param mail
     */
    public void getLastNameByMail(String mail) {
        // TODO implement here
    }
    
    /**
     * 
     */
    public void deconnection() {
        this.connectedUser = null;
    }
    
        /**
     * @param firstName 
     * @param lastName 
     * @param mailAdress 
     * @param height 
     * @param weight 
     * @param user
     */
    public void updateUserAccount(String firstName, String lastName, String mailAdress, Double height, Double weight) {
        // TODO implement here
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException{
        
    }

}