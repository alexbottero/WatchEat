package DAO;


import BL.User;
import java.sql.*;
import java.util.*;

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
    public User updateUserAccount(String firstName, String lastName, String height, String weight, String mailAdress);
    
}
