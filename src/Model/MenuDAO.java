
import BL.*;
import Model.Menu;
import java.util.*;

/**
 * 
 */
public class MenuDAO {

    /**
     * Default constructor
     */
    public MenuDAO() {
    }

    /**
     * @param Menu
     */
    public void deleteMenu(Menu menu) {
        // TODO implement here
    }

    /**
     * @param user 
     * @return
     */
    public ArrayList<Menu> findMenus(Nutritionist user) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @param desc 
     * @param consumables
     */
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
    public void createMenu(String name, String desc, ArrayList<Consumable> consumables, Date dateMenu, Nutritionist author) {
        // TODO implement here

    }

}