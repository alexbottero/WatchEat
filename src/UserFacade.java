
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public boolean login(String mail, String pwd) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] pwdBy = pwd.getBytes();
        byte[] pwdHash = null;
        pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
        User user = new User(mail,new String(pwdHash, "UTF-8"));
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
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String pwd = "chocolat";
        byte[] pwdBy = pwd.getBytes();
        byte[] pwdHash = null;
        pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
        System.out.println(new String(pwdBy, "UTF-8"));
    }

}