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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
    @FXML
    private GridPane menus_GridPane;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navHelpers = new NavigationHelpers();
        menuFacade = new MenuFacade();
        menus = menuFacade.getMenus();
        initMenus();
    }

    private void initMenus() {
        int index = 1;
        for(Menu menu : menus){
            String nameCreator = menu.getCreator().getFirstName() + " " + menu.getCreator().getLastName().substring(0, 1);
            menus_GridPane.add(new Label(menu.getName()),0,index);
            menus_GridPane.add(new Label(menu.getDescription()),1,index);
            menus_GridPane.add(new Label(df.format(menu.getDate())),2,index);
            menus_GridPane.add(new Label(nameCreator),3,index);
            Button seeButton = new Button("See");
            seeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    navHelpers.changeScene(homePage_button, "MyMenus", menu);
                }
            });
            menus_GridPane.add(seeButton,4,index);
            index++;
        }
    }    

    @FXML
    private void clickOnHomePage(ActionEvent event) {
       navHelpers.changeScene(homePage_button, "HomePage", null);
    }

    @FXML
    private void clickOnMyProposals(ActionEvent event) {
    }
    
}
