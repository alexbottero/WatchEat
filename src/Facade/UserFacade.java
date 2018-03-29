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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class UserFacade {
    
    private UserDAO userDAO;
    public static User connectedUser;

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
    
    public boolean signUp(String mail,String pwd,String lastName , String firstName , String gender , Date dateOfBirth ) throws SQLException{
        java.sql.Date sDate = convertUtilToSql(dateOfBirth);
        User user= userDAO.find(mail);
        byte[] pwdBy = pwd.getBytes();
        byte[] pwdHash = null;
        String pwdFinal;
        try{
            pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
            pwdFinal = new String(pwdHash);
            if(user==null){
                 user = userDAO.create(mail,pwdFinal, lastName, firstName,gender,sDate);
                 if (user!=null){
                     this.connectedUser = user;
                     return true;
                 }
                 else{
                     return false;
                 }
            }
            else{
                return false;
            }
        }
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
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
    
    /**
     * 
     */
    public static void deconnection() {
        UserFacade.connectedUser = null;
    }
    
    /**
     * @param firstName 
     * @param lastName 
     * @param height 
     * @param weight 
     * @return  
     */
    public User updateUserAccount(String firstName, String lastName, String height, String weight) {
        User user = userDAO.updateUserAccount(firstName, lastName, height, weight, connectedUser.getMail());
        connectedUser = user;
        return connectedUser;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException{
        
    }
    
    public User updatePwd(String mail, String pwd) {
        User user = null;
        try {
            byte[] pwdBy = pwd.getBytes();
            byte[] pwdHash = null;
            String pwdFinal;
            pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
            pwdFinal = new String(pwdHash);
            user = userDAO.updatePwd(connectedUser.getMail(), pwdFinal);
            connectedUser = user;
            return connectedUser;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

     private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}