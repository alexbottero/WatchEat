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
public class LoginFacade {
    
    private UserDAO userDAO;

    /**
     * Default constructor
     */
    public LoginFacade() {
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
        return userDAO.login(mail,pwd);
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
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException{
        
    }

}