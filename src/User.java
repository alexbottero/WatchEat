


import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class User {

    /**
     * Default constructor
     */
    public User() {
    public User() {
        fact = new PostgresDAOFactory();
    }
     
    public User(String mail,String password) {
        this.mail=mail;
        this.pwd=password;
    }

    /**
     * L'adresse mail de l'utilisateur.
     */
    private String mail;

    /**
     * Le mot de passe de l'utilisateur.
     */
    private String pwd;
    
    private DAOFactory fact;

    /**
     * @param mail 
     * @param pwd
     */
    public boolean login(String mail, String pwd) throws SQLException {
        UserDAO userDAO = fact.createUserDAO();
        User user = userDAO.find(mail);
        return (user != null && user.getPwd().equals(pwd));
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
    
    public static void main(String[] args) throws SQLException{
        User user = new User();
        
        System.out.print(user.login("fabazad@live.fr","chocolat"));
    }

}