package DAO;

import BL.User;
import JDBC.JDBC;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class PostgresUserDAO implements UserDAO {
    
    private JDBC jdbc; 
    
    public PostgresUserDAO(){
        this.jdbc= JDBC.getInstance();
    }
    
    @Override
    public User find(String mail) throws SQLException {
        String query = "SELECT mail, pwd FROM public.user WHERE mail = '" + mail +"'";
        ResultSet res = jdbc.select(query);
        
        if(res.next()){
            User user  = new User(res.getString("mail"),res.getString("pwd"));
            return user; 
        }else{
            return null;
        }      
    }
    
    @Override
    public User update(String mail, String pwd) {
        String query = "UPDATE public.user SET pwd = '" + pwd + "' WHERE mail='" + mail + "'";
        try {
            jdbc.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user  = new User();
        user.setMail(mail);
        user.setPwd(pwd);
        return user;
    }

    public User updateUserAccount(String firstName, String lastName, String mailAdress, Double height, Double weight) {
        String query = "UPDATE public.user SET firstName = '" + firstName + "' lastName = '" + lastName + "' height = '" + height + "' weight = '" + weight +"' WHERE mail = '" + mailAdress + "'";
        try {
           jdbc.update(query);
        } catch(SQLException ex) {
            Logger.getLogger(PostgresUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = new User();
        user.setMail(mailAdress);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setHeight(height);
        user.setWeight(weight);
        return user;
     
    }
    
    public static void main(String[] args) throws SQLException{
        UserDAO userDAO = new PostgresUserDAO();
    } 
}
