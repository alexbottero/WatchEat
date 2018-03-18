package BL;




import DAO.UserDAO;
import DAO.PostgresDAOFactory;
import DAO.DAOFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class User {

    public static User connectedUser = null;
    
    /**
     * Default constructor
     */
    public User() {
        //fact = new PostgresDAOFactory();
    }
     
    public User(String mail,String password) {
        this.mail=mail;
        this.pwd=password;
        //fact = new PostgresDAOFactory();
    }

    /**
     * L'adresse mail de l'utilisateur.
     */
    private String mail = "";

    /**
     * Le mot de passe de l'utilisateur.
     */
    private String pwd = "";

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean login(String mail, String pwd) {
        byte[] pwdBy = pwd.getBytes();
        byte[] pwdHash = null;
        String pwdFinal;
        try {
            pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
            pwdFinal = new String(pwdHash);
            if(this.mail.equals(mail) && this.pwd.equals(pwdFinal)){
                connectedUser = this;
                return true;
            }
            else{
                connectedUser = null;
                return false;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deconnection() {
        connectedUser = null;
    }
    
   /** public static void main(String[] args) throws SQLException{
        User user = new User("fabazad@live.fr","chocolat");
        
        System.out.print(user.login());
    }**/

}