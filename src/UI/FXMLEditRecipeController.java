/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Ingredient;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author fabaz
 */
public class FXMLEditRecipeController implements Initializable, UIController {

    @FXML
    private TextArea descriptionField;
    @FXML
    private TextArea instructionsField;
    @FXML
    private ComboBox<String> typeField;
    @FXML
    private GridPane ingredientsPane;
    @FXML
    private Button addIngredientButton;
    @FXML
    private TextField timeField;
    @FXML
    private TextField peopleAmountField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button rescipesReturn;
    @FXML
    private Label nameRecipeLabel;
    
     /**
     * La liste des ComboBox d'ingrédient
     */
    private ArrayList<ComboBox> ingredientsField;
    
     /**
     * La liste des textFields de quantité d'ingrédient
     */
    private ArrayList<TextField> quantitiesField;
    
    private NavigationHelpers navHelpers;
    
    private ArrayList<Button> deleteButtons;
    
    private ObservableList<String> consumables;
    
    private RecipeFacade recipeFacade;
    
    private ObservableList<String> types;
    
    private Recipe recipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navHelpers = new NavigationHelpers();
        recipeFacade = new RecipeFacade();
        errorMessageLabel.setText("");
        ingredientsField = new ArrayList<ComboBox>();
        quantitiesField = new ArrayList<TextField>();
        deleteButtons = new ArrayList<Button>();
        this.types = recipeFacade.getTypes();
        typeField.setItems(types);
        consumables = recipeFacade.getConsumables();
    }    

    @FXML
    private void editRecipeClicked(ActionEvent event) {
        String description = descriptionField.getText();
        String type = (String) typeField.getValue();
        String timeString = timeField.getText();
        String peopleAmountString = peopleAmountField.getText();
        String instructions = instructionsField.getText();
        boolean allConsumablesOk = true;
        for(ComboBox ingredientField : ingredientsField){
            if(ingredientField.getValue() == null){
                allConsumablesOk = false;
            }
        }
        if("".equals(description) 
            || type == null
            || "".equals(timeString)
            || "".equals(peopleAmountString)
            || "".equals(instructions)
            || !allConsumablesOk){
            errorMessageLabel.setText("Compulsory fields are empty.");
        }
        else{
            try {
                ArrayList<String> ingredientsName = new ArrayList<>();
                ArrayList<Integer> ingredientsQuantity = new ArrayList<>();
                for(ComboBox ingredientField : ingredientsField){
                    ingredientsName.add((String)ingredientField.getValue());
                    ingredientsQuantity.add(Integer.parseInt(quantitiesField.get(ingredientsField.indexOf(ingredientField)).getText()));
                }
                int time = Integer.parseInt(timeField.getText());
                int peopleAmount = Integer.parseInt(peopleAmountString);
                
                this.recipeFacade.editRecipe(recipe.getName(),description,type,time,peopleAmount,instructions,ingredientsName,ingredientsQuantity);
                errorMessageLabel.setText("Recipe edited.");
            } 
            catch (NumberFormatException e) { 
                errorMessageLabel.setText("Recipe people amount, ingredient quantities and recipe time should be numerics."); 
            }
        }
        navHelpers.changeScene(errorMessageLabel, "Recipes", null);
    }
    
    /**
     * Rajoute une ligne au formulaire des ingrédients
     */
    public void addRowIngredient(){
        int indexIngredient = ingredientsField.size();
        
        ComboBox ingredientField = new ComboBox();
        ingredientField.setPromptText("Ingredients");
        ingredientsPane.add(ingredientField, 0, indexIngredient);
        ingredientField.setItems(consumables);
        
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity (g / ml)");
        ingredientsPane.add(quantityField, 1, indexIngredient);
        
        Button deleteButton = new Button("Delete");
        ingredientsPane.add(deleteButton, 2, indexIngredient);
        
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int index  = deleteButtons.indexOf(e.getSource());
                deleteRow(index);
            }
        });
        
        ingredientsField.add(ingredientField);
        quantitiesField.add(quantityField);
        deleteButtons.add(deleteButton);
    }

    /**
     * Supprime une ligne du tableau des ingrédients
     * @param index 
     */
    private void deleteRow(int index) {
        if(deleteButtons.size() > 1){
            //Supression de l'élément des listes de Controls
            ingredientsField.remove(index);
            quantitiesField.remove(index);
            deleteButtons.remove(index);

            //Vidage du GridPane pour le reremplir ensuite
            ingredientsPane.getChildren().removeIf(c -> true);
            index = 0;
            for(ComboBox ingredientField : ingredientsField){
                ingredientsPane.add(ingredientField,0,index);
                ingredientsPane.add(quantitiesField.get(index),1,index);
                ingredientsPane.add(deleteButtons.get(index),2,index);
                index++;
            }
        }
    }
    
    @FXML
    private void addIngredientButtonClicked(ActionEvent event) {
        if(deleteButtons.size() < 11){
            addRowIngredient();
        }
    }

    @FXML
    private void backClicked(ActionEvent event) {
        navHelpers.changeScene(typeField, "HomePage", event);
    }

    @FXML
    private void recipesReturnClicked(ActionEvent event) {
        navHelpers.changeScene(typeField, "Recipes", event);
    }
    @FXML

    private void deconnectionButtonClicked(ActionEvent event) {
        UserFacade.deconnection();
        navHelpers.changeScene(typeField, "UILogin", event);
    }

    @Override
    public void receiveData(Object givenData) {
        this.recipe = (Recipe) givenData;
        this.nameRecipeLabel.setText(recipe.getName());
        this.descriptionField.setText(recipe.getDescription());
        this.instructionsField.setText(recipe.getInstructions());
        this.typeField.getSelectionModel().select(types.indexOf(recipe.getType()));
        this.timeField.setText(Integer.toString(recipe.getTimeRecipe()));
        this.peopleAmountField.setText(Integer.toString(recipe.getPeopleAmount()));
        int index = 0;
        for(Ingredient ingredient : recipe.getIngredients()){
            ComboBox ingredientsComboBox = new ComboBox();
            ingredientsComboBox.setItems(consumables);
            ingredientsComboBox.getSelectionModel().select(consumables.indexOf(ingredient.getConsumable().getName()));
            ingredientsPane.add(ingredientsComboBox,0,index);
            ingredientsField.add(ingredientsComboBox);
            
            TextField quantityField = new TextField();
            quantityField.setText(Integer.toString(ingredient.getQuantity()));
            ingredientsPane.add(quantityField,1,index);
            quantitiesField.add(quantityField);
            
            
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    int index  = deleteButtons.indexOf(e.getSource());
                    deleteRow(index);
                }
            });
            ingredientsPane.add(deleteButton,2,index);
            deleteButtons.add(deleteButton);
            
            index++;
        }
    }
    
}
