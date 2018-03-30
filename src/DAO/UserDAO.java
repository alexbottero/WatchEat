package DAO;


import BL.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public interface UserDAO {

    /**
     * Find an user in the database thanks to his mail, if nothing return null
     * @param mail mail address of the searching user
     * @return the user found
     * @throws java.sql.SQLException .
     */
    public User find(String mail) throws SQLException;

    /**
     * Update the password of the user whose mail is on parameter
     * @param mail String
     * @param pwd String
     * @return User
     */
    public User update(String mail, String pwd);
    
    /**
     * Create an user with this parameters
     * @param mail String
     * @param pwd String
     * @param lastName String
     * @param firstName String
     * @param gender String
     * @param dateOfBirth Date
     * @return User
     */
    public User create(String mail,String pwd, String lastName , String firstName , String gender , Date dateOfBirth);
    
    /**
     * Update the parameters of the user whose mail is on parameter
     * @param firstName String
     * @param lastName String
     * @param height String
     * @param weight String
     * @param mailAdress String
     * @return User
     */
    public User updateUserAccount(String firstName, String lastName, String height, String weight, String mailAdress);
    
    
    public String selectUser(String Mail);
    
    /**
     * Update the password of the user whose mail is on parameter
     * @param mail String
     * @param pwd String
     * @return User
     */
    public User updatePwd(String mail, String pwd);
    
}
