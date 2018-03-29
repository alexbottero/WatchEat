/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Recipe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author fabaz
 */
public class FXMLRecipeController implements Initializable, UIController {

    @FXML
    private Label recipeNameLabel;
    
    private Recipe recipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @Override
    public void receiveData(Object givenData) {
        this.recipe = (Recipe)givenData;
        recipeNameLabel.setText(recipe.getName());
    }
    
}
