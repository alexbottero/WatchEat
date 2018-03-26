/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Consumable;
import Facade.RecipeFacade;
import Facade.UserFacade;
import Helpers.NavigationHelpers;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author fabaz
 */
public class FXMLCreateRecipeController extends AbstractUIController implements Initializable {
    
    private UserFacade uf;
    
    /**
     * La donnée envoyés par la scene précédente
     */
    private Object givenData;

    @FXML
    private Button addIngredientButton;
    
    @FXML
    private GridPane ingredientsPane;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextArea descriptionField;
    
    @FXML
    private ComboBox typeField;
    
    @FXML
    private TextField timeField;
    
    @FXML
    private TextField peopleAmountField;
    
    @FXML
    private TextArea instructionsField;
    
    @FXML
    private Label errorMessageLabel;
    
    /**
     * La liste des ComboBox d'ingrédient
     */
    private ArrayList<ComboBox> ingredientsField;
    
     /**
     * La liste des textFields de quantité d'ingrédient
     */
    private ArrayList<TextField> quantitiesField;
    
     /**
     * La liste des boutons de suppression d'ingrédient
     */
    private ArrayList<Button> deleteButtons;
    
    private RecipeFacade recipeFacade;
    
    private ObservableList<String> consumables;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recipeFacade = new RecipeFacade();
        errorMessageLabel.setText("");
        ingredientsField = new ArrayList<ComboBox>();
        quantitiesField = new ArrayList<TextField>();
        deleteButtons = new ArrayList<Button>();
        addRowIngredient();
        ObservableList<String> types = recipeFacade.getTypes();
        typeField.setItems(types);
        consumables = recipeFacade.getConsumables();
        ingredientsField.get(0).setItems(consumables);     

    }
    
    /**
     * @param event
     */
    public void addIngredientButtonClicked(ActionEvent event){
        if(deleteButtons.size() < 11){
            addRowIngredient();
        }
    }
    
    /**
     * Rajoute une ligne au formulaire des ingrédients
     */
    public void addRowIngredient(){
        int indexIngredient = ingredientsField.size();
        ComboBox ingredientField = new ComboBox();
        ingredientField.setPromptText("Ingrédients");
        ingredientsPane.add(ingredientField, 0, indexIngredient);
        ingredientField.setItems(consumables);
        
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantité (g / ml)");
        ingredientsPane.add(quantityField, 1, indexIngredient);
        
        Button deleteButton = new Button("Supprimer");
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
     * Lors du click sur le bouton retour, refait venir à la page précédente
     * @param event
     */
    public void backClicked(ActionEvent event){
        new NavigationHelpers().changeScene(nameField,"HomePage",null);
    }
    
    /**
     * Vérification et création de la recette
     * @param event clique sur le bouton créer recette
     */
    public void createRecipeClicked(ActionEvent event){
        String name = nameField.getText();
        String description = descriptionField.getText();
        String type = (String) typeField.getValue();
        String timeString = timeField.getText();
        String peopleAmountString = peopleAmountField.getText();
        String instructions = instructionsField.getText();
        if("".equals(name) 
            || "".equals(description) 
            || type == null
            || "".equals(timeString)
            || "".equals(peopleAmountString)
            || "".equals(instructions)){
            errorMessageLabel.setText("Des champs obligatoirs sont vides.");
        }
        else{
            try { 
                int time = Integer.parseInt(timeField.getText());
                int peopleAmount = Integer.parseInt(peopleAmountString);
                errorMessageLabel.setText("Recette créée.");
            } 
            catch (NumberFormatException e) { 
                errorMessageLabel.setText("Le nombre de personne et le temps de la recette doit être un nombre entier représentant le nombre de minute."); 
            }
        }
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
}
