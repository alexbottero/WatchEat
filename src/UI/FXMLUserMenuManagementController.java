/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Menu;
import BL.Recipe;
import Facade.MenuFacade;
import Helpers.NavigationHelpers;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Polytech
 */
public class FXMLUserMenuManagementController implements Initializable {

    @FXML
    private Button homePage_button;
    private NavigationHelpers navHelpers;
    @FXML
    private Button myProposals_button;
    private MenuFacade menuFacade;
    private ArrayList<Menu> menus;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navHelpers = new NavigationHelpers();
        menuFacade = new MenuFacade();
        menus = menuFacade.getMenus();
    }    

    @FXML
    private void clickOnHomePage(ActionEvent event) {
       navHelpers.changeScene(homePage_button, "HomePage", null);
    }

    @FXML
    private void clickOnMyProposals(ActionEvent event) {
    }
    
}
