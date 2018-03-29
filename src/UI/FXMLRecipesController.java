/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Recipe;
import Facade.RecipeFacade;
import Facade.UserFacade;
import Helpers.NavigationHelpers;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author fabaz
 */
public class FXMLRecipesController implements Initializable, UIController {

    @FXML
    private Button createRecipeButton;
    
    @FXML
    private GridPane recipesGridPane;
    
    private NavigationHelpers navHelpers;
    private RecipeFacade recipeFacade;
    private ArrayList<Recipe> recipes;
    
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;
    
    private ObservableList<Node> childrens;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navHelpers = new NavigationHelpers();
        recipeFacade = new RecipeFacade();
        recipes = recipeFacade.getRecipes();
        childrens = recipesGridPane.getChildren();
        initRecipes();
    }

    @FXML
    public void createRecipeButtonClicked(){
        new NavigationHelpers().changeScene(createRecipeButton,"CreateRecipe",null);
    }

    private void initRecipes() {
        //recipesGridPane.getChildren().setAll(childrens);
        childrens.clear();
        ArrayList<String> attributs = new ArrayList<>();
        attributs.add("Nom");
        attributs.add("Description");
        attributs.add("Type");
        attributs.add("Time");
        attributs.add("Creator");
        attributs.add("Action");
        int index = 0;
        for(String attribut : attributs){
            Label label = new Label(attribut);
            label.setStyle("-fx-font-weight: bold");
            recipesGridPane.add(label,index,0);
            index++;
        }
        
        index = 1;
        for(Recipe recipe : recipes){
            String nameCreator = recipe.getCreator().getFirstName() + " " + recipe.getCreator().getLastName().substring(0, 1);
            recipesGridPane.add(new Label(recipe.getName()),0,index);
            recipesGridPane.add(new Label(recipe.getDescription()),1,index);
            recipesGridPane.add(new Label(recipe.getType()),2,index);
            recipesGridPane.add(new Label(Integer.toString(recipe.getTimeRecipe()) + " min"),3,index);
            recipesGridPane.add(new Label(nameCreator),4,index);
            Button seeButton = new Button("See");
            seeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    navHelpers.changeScene(createRecipeButton, "Recipe", recipe);
                }
            });
            recipesGridPane.add(seeButton,5,index);
            index++;
        }
    }
    
    @FXML
    public void homePageButtonClicked(){
        navHelpers.changeScene(createRecipeButton, "HomePage", null);
    }
    
    @FXML
    public void deconnectionButtonClicked(){
        navHelpers.changeScene(createRecipeButton,"UILogin",null);
        UserFacade.deconnection();
    }

    @Override
    public void receiveData(Object givenData) {
    }

    @FXML
    private void searchButtonClicked(ActionEvent event) {
        recipes = recipeFacade.getRecipes(searchField.getText());
        initRecipes();
    }
    
}
