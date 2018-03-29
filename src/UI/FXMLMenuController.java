/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Helpers.NavigationHelpers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Marion
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Button homePage_button;
    @FXML
    private Button returnMyMenus_button;
    private NavigationHelpers navHelpers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navHelpers = new NavigationHelpers();
    }    

    @FXML
    private void clickOnHomePage(ActionEvent event) {
        navHelpers.changeScene(homePage_button,"HomePage",null);
    }

    @FXML
    private void ClickOnReturn(ActionEvent event) {
        navHelpers.changeScene(returnMyMenus_button,"UserMenuManagement",null);
    }
    
}
