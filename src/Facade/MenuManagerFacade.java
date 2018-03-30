package Facade;


import BL.Menu;
import BL.User;
import DAO.ConsumableDAO;
import DAO.DAOFactory;
import DAO.FoodDAO;
import DAO.MenuDAO;
import DAO.PostgresDAOFactory;
import DAO.RecipeDAO;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class MenuManagerFacade {

    private DAOFactory daoFactory;
    private MenuDAO menuDAO;
    private FoodDAO foodDAO;
    private RecipeDAO recipeDAO;
    private ConsumableDAO consumableDAO;
    /**
     * Default constructor
     */
    public MenuManagerFacade() {
        daoFactory = PostgresDAOFactory.getInstance();
        menuDAO = daoFactory.createMenuDAO();
        recipeDAO = daoFactory.createRecipeDAO();
        consumableDAO = daoFactory.createConsumableDAO();
        foodDAO = daoFactory.createFoodDAO();
    }


    /**
     * @param Menu
     */
   // public void delMenu(void Menu) {
        // TODO implement here
    //}

    /**
     * 
     * @param user User : nutritionist 
     * @return ArrayList Menu
     * @throws java.sql.SQLException .
     */
    public ArrayList<Menu> getAllMenu(User user) throws SQLException {
        ArrayList<Menu> menus = menuDAO.getAllMenusFromUser(user);
        for (Menu m : menus){
                m.addListFood(foodDAO.getAllFoodFromMenu(m));
                m.addListRecipe(recipeDAO.getAllRecipeFromMenu(m));
        }
        return menus;
    }

}