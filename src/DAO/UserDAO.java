package DAO;


import BL.User;
import java.sql.*;

/**
 * 
 */
public interface UserDAO {

    /**
     * Find an user in the database thanks to his mail, if nothing return null
     * @param mail mail address of the searching user
     * @return the user found
     * @throws java.sql.SQLException 
     */
    public User find(String mail) throws SQLException;

    /**
     * @param mail
     * @param pwd
     * @return 
     */
    public User update(String mail, String pwd);
    
    public User create(String mail,String pwd, String lastName , String firstName , String gender , Date dateOfBirth, String nutriDesc);
    public User updateUserAccount(String firstName, String lastName, String height, String weight, String mailAdress);
    
}
