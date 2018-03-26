/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BL.Consumable;
import DAO.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fabaz
 */
public class RecipeFacade {
    
    private DAOFactory daoFactory;
    private RecipeDAO recipeDAO;
    private ConsumableDAO consumableDAO;
    
    public RecipeFacade(){
        daoFactory = PostgresDAOFactory.getInstance();
        recipeDAO = daoFactory.createRecipeDAO();
        consumableDAO = daoFactory.createConsumableDAO();
    }
    
    public ObservableList<String> getTypes(){
        ArrayList<String> types = recipeDAO.getTypes();
        return FXCollections.observableArrayList(types);
    }
    
    public ObservableList<String> getConsumables(){
        ArrayList<Consumable> consumables = consumableDAO.getConsumables();
        ArrayList<String> nameConsumables = new ArrayList<String>();
        consumables.forEach((c) -> {
            nameConsumables.add(c.getName());
        });
        return FXCollections.observableArrayList(nameConsumables);
    }
}
