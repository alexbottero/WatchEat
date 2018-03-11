
import java.sql.*;
import java.util.*;

/**
 * 
 */
public abstract class UserDAO {

    /**
     * Default constructor
     */
    public UserDAO() {
    }

    /**
     * 
     */
    public Connection connect;

    /**
     * Find an user in the database, if nothing return null
     * @param mail mail address of the searching user
     * @return the user found
     * @throws java.sql.SQLException 
     */
    public abstract User find(String mail) throws SQLException;

    /**
     * @param mail
     * @param pwd
     * @return 
     */
    public abstract User update(String mail, String pwd);
}