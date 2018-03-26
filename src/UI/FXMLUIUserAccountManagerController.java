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
public class FXMLUIUserAccountManagerController extends AbstractUIController implements Initializable {

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
        if(!(user.getHeight()==null)) {
        String h = Double.toString(user.getHeight());
        height_textField.setText(h);
        }
        if(!(user.getWeight()==null)){
        String w = Double.toString(user.getWeight());
        weight_textField.setText(w);
        }
        
    }    

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
    
}

