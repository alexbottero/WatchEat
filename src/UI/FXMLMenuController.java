/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Ingredient;
import BL.Menu;
import BL.Recipe;
import Helpers.NavigationHelpers;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    @FXML
    private Label name_label;
    @FXML
    private Label description_label;
    @FXML
    private Label date_label;
    private Menu menu;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
    @FXML
    private Label menuName_label;

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
    
    public void initLabels(){
        menuName_label.setText(menu.getName());
        name_label.setText(menu.getName());
        description_label.setText(menu.getDescription());
        date_label.setText(df.format(menu.getDate()));
    }
    
    public void receiveData(Object givenData) {
        this.menu = (Menu)givenData;
        initLabels();
    }
}
