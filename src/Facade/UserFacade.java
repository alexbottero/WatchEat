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

    /**
     * Default constructor
     */
    public UserFacade(String mail,String password) {
        //connectedUser=new User(mail,password);
        fact = new PostgresDAOFactory();
    }

    /**
     * 
     */
    public User connectedUser;
    private DAOFactory fact; 

    /**
     * @param mail 
     * @param pwd
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean login(String mail, String pwd) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] pwdBy = pwd.getBytes();
        byte[] pwdHash = null;
        pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
        //User user = new User(mail,new String(pwdHash, "UTF-8"));
        fact = new PostgresDAOFactory();
        UserDAO uDao= fact.createUserDAO();
        User user = uDao.find(mail, pwd);
        boolean connect =  user!= null && user.getPwd().equals(pwd);
       // boolean connect = (connectedUser.equals(uDao.find(mail, pwd)));
        //boolean connect = user.login();
        if(connect){
            connectedUser = user;
            return true;
        }else{
            connectedUser = null;
            return false;
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
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException{
        String pwd = "chocolat";
        byte[] pwdBy = pwd.getBytes();
        byte[] pwdHash = null;
        pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
        System.out.println(new String(pwdBy, "UTF-8"));
        UserFacade uf = new UserFacade("ehamelojulia@gmail.com", "chat");
        
        System.out.println(uf.login("ehamelojulia@gmail.com", "chat"));
    }

}