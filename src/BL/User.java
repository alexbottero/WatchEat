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
    }
     
    public User(String mail,String password, String firstName, String lastName, Double height, Double weight) {
        this.mail=mail;
        this.pwd=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.height = height;
        this.weight= weight;
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
     * Le prénom de l'utilisateur.
     */
    private String firstName = "";
    
    /**
     * Le nom de l'utilisateur.
     */
    private String lastName = "";
    
    /**
     * La taille de l'utilisateur.
     */
    private Double height;
    
    /**
     * Le poids de l'utilisateur.
     */
    private Double weight;

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

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the height
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
   /** public static void main(String[] args) throws SQLException{
        User user = new User("fabazad@live.fr","chocolat");
        
        System.out.print(user.login());
    }**/

}