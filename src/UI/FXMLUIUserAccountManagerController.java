/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Facade.UserFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Polytech
 */
public class FXMLUIUserAccountManagerController implements Initializable {

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.uf = new UserFacade();
    }    

    @FXML
    private void click_validate(ActionEvent event) {
        uf.updateUserAccount(firstName_textField.getText(), lastName_textField.getText(), height_textField.getText(), weight_textField.getText());
    }
    
}
