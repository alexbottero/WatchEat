
import JDBC.*;
import java.sql.*;
import java.util.*;

/**
 * 
 */
public class UserDAO {

    /**
     * Default constructor
     */
    public UserDAO() {
        this.connect = new DBPostgresConnection();
    }

    /**
     * 
     */
    public DBConnection connect;

    /**
     * Find an user in the database, if nothing return null
     * @param mail mail address of the searching user
     * @return the user found
     * @throws java.sql.SQLException 
     */
    public User find(String mail) throws SQLException {
        User user = new User();
        ResultSet res = connect.select("SELECT mail, pwd FROM public.user WHERE mail = '" + mail + "'");
        
        if(res.next()){
            user.setMail(res.getString("mail"));
            user.setPwd(res.getString("pwd"));
            return user; 
        }else{
            return null;
        }      
    }

    /**
     * @param pwd
     */
    public void update(String pwd) {
        // TODO implement here
    }

    /**
     * @param mail
     */
    public void createByMail(String mail) {
        // TODO implement here
    }
    
    public static void main(String[] args) throws SQLException{
        UserDAO userDAO = new UserDAO();
        User user = userDAO.find("fabazad@live.fr");
        if(user != null){System.out.println(user.getMail() + " : " + user.getPwd());}
    }

}