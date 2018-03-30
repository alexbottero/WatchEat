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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
    
    @FXML
    private ComboBox<String> typeComboBox;
    
    @FXML
    private TextField timeMaxField;
    @FXML
    private Text errorMessageLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navHelpers = new NavigationHelpers();
        recipeFacade = new RecipeFacade();
        recipes = recipeFacade.getRecipes();
        childrens = recipesGridPane.getChildren();
        ObservableList<String> types = recipeFacade.getTypes();
        types.add("None");
        typeComboBox.setItems(types);
        errorMessageLabel.setText("");
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
            
            if(recipe.getCreator().getMail().equals(UserFacade.connectedUser.getMail())){
                Button editButton = new Button("Edit");
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        navHelpers.changeScene(createRecipeButton, "EditRecipe", recipe);
                    }
                });
                editButton.setStyle("-fx-background-color: #ffb200");
                recipesGridPane.add(editButton,6,index);
                
                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        recipeFacade.deleteRecipe(recipe);
                        recipes = recipeFacade.getRecipes();
                        initRecipes();
                    }
                });
                deleteButton.setStyle("-fx-background-color: #FF0000");
                recipesGridPane.add(deleteButton,7,index);
            }
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
        int time = verifSearch();
        recipes = recipeFacade.getRecipes(searchField.getText(),typeComboBox.getValue(),time,null);
        initRecipes();
    }

    @FXML
    private void showOwnRecipesButton(ActionEvent event) {
        int time = verifSearch();
        recipes = recipeFacade.getRecipes(searchField.getText(),typeComboBox.getValue(),time,UserFacade.connectedUser);
        
        initRecipes();
    }
    
    public int verifSearch(){
        errorMessageLabel.setText("");
        int time = 0;
        if(!timeMaxField.getText().equals("")){
            try{
            time = Integer.parseInt(timeMaxField.getText());
            }
            catch(Exception e){
                errorMessageLabel.setText("Time max field should be numeric.");
            }
        }
        return time;
    }
    
}
