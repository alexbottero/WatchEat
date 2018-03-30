/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.User;
import Facade.UserFacade;
import Helpers.NavigationHelpers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Polytech
 */
public class FXMLUIUserAccountManagerController implements Initializable, UIController {

    @FXML
    private Button Validate;
    @FXML
    private TextField firstName_textField;
    @FXML
    private TextField lastName_textField;
    @FXML
    private TextField height_textField;
    @FXML
    private TextField weight_textField;
    @FXML
    private Button changePassword_button;
    
    private UserFacade uf;
    @FXML
    private Label labelText;
    @FXML
    private Button backButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.uf = new UserFacade();
        User user = UserFacade.connectedUser;
        firstName_textField.setText(user.getFirstName());
        lastName_textField.setText(user.getLastName());
        if(!(user.getHeight()==0)) {
        String h = Integer.toString(user.getHeight());
        height_textField.setText(h);
        }
        if(!(user.getWeight()==0)){
        String w = Integer.toString(user.getWeight());
        weight_textField.setText(w);
        }
        
    }    

    /**
     * Changes the page when we click on the validate button if the updateUserAccount method is executed
     * @param event ActionEvent : event that triggers the action
     */
    @FXML
    private void click_validate(ActionEvent event) {
        if(uf.updateUserAccount(firstName_textField.getText(), lastName_textField.getText(), height_textField.getText(), weight_textField.getText())!= null){
            new NavigationHelpers().changeScene(Validate,"HomePage",null);
            labelText.setText("Changements réussis");
        }
        else{
                labelText.setText("Erreur");
                }
    }

    @Override
    public void receiveData(Object givenData) {
    }
    
    /**
     * Lors du click sur le bouton retour, refait venir à la page précédente
     * @param event Action event : event that triggers the action
     */
    @FXML
    public void backClicked(ActionEvent event){
        new NavigationHelpers().changeScene(Validate,"HomePage",null);
    }

    /**
     * Changes the page when we click on the change password button
     * @param event ActionEvent : events that triggers the action
     */
    @FXML
    private void clickOnChangePwd(ActionEvent event) {
        new NavigationHelpers().changeScene(changePassword_button,"ChangePassword",null);
    }
    
}

