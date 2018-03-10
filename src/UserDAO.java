
import java.sql.Connection;
import java.util.*;

/**
 * 
 */
public class UserDAO {

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
     * @param mail
     * @return 
     */
    public User find(String mail) {
        User user = new User();
        user.setMail("fabazad@live.fr");
        user.setMail("chocolat");
        return user;
        // TODO implement here
        
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

}