/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Ingredient;
import BL.NFQuantity;
import BL.Recipe;
import Facade.UserFacade;
import Helpers.NavigationHelpers;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author fabaz
 */
public class FXMLRecipeController implements Initializable, UIController {

    @FXML
    private Label recipeNameLabel;
    
    private Recipe recipe;
    
    private NavigationHelpers navHelpers;
    @FXML
    private Label nameLabel;
    @FXML
    private Label peopleAmountLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label instructionsLabel;
    @FXML
    private GridPane ingredientsGridPane;
    @FXML
    private GridPane nutritiveValuesPane;
    @FXML
    private Label authorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navHelpers = new NavigationHelpers();
    }    
    
    @FXML
    public void homePageButtonClicked(){
        navHelpers.changeScene(recipeNameLabel,"HomePage",null);
    }
    
    @FXML
    public void recipesButtonClicked(){
        navHelpers.changeScene(recipeNameLabel,"Recipes",null);
    }
    
    @FXML
    public void deconnectionButtonClicked(){
        navHelpers.changeScene(recipeNameLabel,"UILogin",null);
        UserFacade.deconnection();
    }
    
    public void initLabels(){
        //les informations
        recipeNameLabel.setText(recipe.getName());
        nameLabel.setText(recipe.getName());
        descriptionLabel.setText(recipe.getDescription());
        instructionsLabel.setText(recipe.getInstructions());
        peopleAmountLabel.setText(Integer.toString(recipe.getPeopleAmount()) + " person(s)");
        timeLabel.setText(Integer.toString(recipe.getTimeRecipe()) + " minutes");
        authorLabel.setText(recipe.getCreator().getFullName());
        
        //Les ingrédients
        int index = 1;
        System.out.println(recipe.getIngredients().size());
        for(Ingredient ingredient : recipe.getIngredients()){
            ingredientsGridPane.add(new Label(ingredient.getConsumable().getName()),0,index);
            ingredientsGridPane.add(new Label(Integer.toString(ingredient.getQuantity()) + " g/ml"),1,index);
            if(ingredient.getConsumable() instanceof Recipe){
                Button seeButton = new Button("See");
                ingredientsGridPane.add(seeButton,2,index);
                seeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        navHelpers.changeScene(recipeNameLabel, "Recipe", ingredient.getConsumable());
                    }
                });
            }
            index++;
        }
        
        //Les valeurs nutritives 
        ArrayList<NFQuantity> nutritiveValues = recipe.getNutritiveValues();
        index = 1;
        for(NFQuantity nfQuantity : nutritiveValues){
            System.out.println("num nv : " + Integer.toString(nfQuantity.getQuantity()));
            nutritiveValuesPane.add(new Label(nfQuantity.getNutritiveValue().getName()), 0, index);
            nutritiveValuesPane.add(new Label(Integer.toString(nfQuantity.getQuantity())), 1, index);
            nutritiveValuesPane.add(new Label(nfQuantity.getNutritiveValue().getUnity()), 2, index);
            index++;
        }
    }

    @Override
    public void receiveData(Object givenData) {
        this.recipe = (Recipe)givenData;
        initLabels();
    }
}
