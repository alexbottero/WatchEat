
import BL.Consumable;
import BL.User;
import JDBC.JDBC;
import Model.Menu;
import java.sql.ResultSet;
import java.util.*;

/**
 * 
 */
public class PostgresMenuDAO extends MenuDAO {

    private JDBC jdbc;
    
    public PostgresMenuDAO(){
        jdbc = JDBC.getInstance();
    }

    /**
     * @param user 
     * @return
     */
    @Override
    public ArrayList<Menu> findMenus(Nutritionist user) {
        // TODO implement here
        String query = "SELECT name, description, n.firstName, n.lastname, n.mail, n.dateofbith,  datecreation FROM public.user WHERE mail = '" + mail +"'";
        ResultSet res = jdbc.select(query);
        
        if(res.next()){
            User user  = new User(res.getString("mail"),res.getString("pwd"));
            return user; 
        }else{
            return null;
        }  
        
        return null;
    }
    
    
    /**
     * @param Menu
     */
    @Override
    public void deleteMenu(Menu menu) {
        // TODO implement here
    }

    

    /**
     * @param name 
     * @param desc 
     * @param consumables
     */
    @Override
    public void updateMenu(String name, String desc, ArrayList<Consumable> consumables) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param desc 
     * @param consumables 
     * @param dateMenu 
     * @param author;Nutritionist 
     * @return
     */
    @Override
    public void createMenu(String name, String desc, ArrayList<Consumable> consumables, Date dateMenu, Nutritionist author) {
        // TODO implement here

    }


}