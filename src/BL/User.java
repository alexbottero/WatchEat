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
     * Le genre de l'utilisateur.
     */
    private String gender = "";
    
    /**
     * birth of user de l'utilisateur.
     */
    private Date dateOfBirth ;
    /**
     * La taille de l'utilisateur.
     */
    private int height;
    
    /**
     * Le poids de l'utilisateur.
     */
    private int weight;
  
    
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
    
    public User(String mail,String password, String lastName, String firstName) {
        this.mail=mail;
        this.pwd=password;
        this.lastName = lastName;
        this.firstName = firstName;
    }
     
    public User(String mail,String password, String firstName, String lastName, int height, int weight) {
        this.mail=mail;
        this.pwd=password;      
        this.firstName=firstName;
        this.lastName=lastName;
        this.height = height;
        this.weight= weight;
    }
    
    public User(String mail,String password,String firstName,String lastName,String gender,Date date) {
        this.mail=mail;
        this.pwd=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.dateOfBirth=date;
        this.height = 0;
        this.weight =0;
     }
    public User(String mail,String password,String firstName) {
        this.mail=mail;
        this.pwd=password;
        this.firstName=firstName;
     }
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
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Test if the user can login with this identifiants
     * @param mail String
     * @param pwd String
     * @return boolean
     */
    public boolean login(String mail, String pwd) {
        byte[] pwdBy = pwd.getBytes();
        byte[] pwdHash = null;
        String pwdFinal;
        try {
            pwdHash = MessageDigest.getInstance("MD5").digest(pwdBy);
            pwdFinal = new String(pwdHash);
            if(this.getMail().equals(mail) && this.getPwd().equals(pwdFinal)){
                return true;
            }
            else{
                return false;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get the full name ( lastname + firstname)
     * @return String
     */
    public String getFullName() {
        return this.lastName + " " + this.firstName;
    }

}