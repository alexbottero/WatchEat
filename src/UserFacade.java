
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class UserFacade {

    /**
     * Default constructor
     */
    public UserFacade(String mail,String password) {
        connectedUser=new User(mail,password);
    }

    /**
     * 
     */
    public User connectedUser;

    /**
     * @param mail 
     * @param pwd
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean login(String mail, String pwd) throws SQLException {
        User user = new User(mail,pwd);
        boolean connect = user.login();
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

}