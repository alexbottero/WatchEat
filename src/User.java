


import java.util.*;

/**
 * 
 */
public class User {

    /**
     * Default constructor
     */
     public User() {;
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

    /**
     * @param mail 
     * @param pwd
     */
    public void login(String mail, String pwd) {
        // TODO implement here
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

}