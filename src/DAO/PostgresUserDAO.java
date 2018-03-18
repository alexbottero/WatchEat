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

    @Override
    public boolean login(String mail, String pwd) {
        User user;
        try {
            user = find(mail);
            return user.login(mail,pwd);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void deconnection() {
        User user = new User();
        user.deconnection();
    }
    

    public static void main(String[] args) throws SQLException{
        UserDAO userDAO = new PostgresUserDAO();
        userDAO.deconnection();
    } 
}
