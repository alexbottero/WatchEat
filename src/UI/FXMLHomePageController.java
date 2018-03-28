package UI;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Facade.*;
import Helpers.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fabaz
 */
public class FXMLHomePageController extends AbstractUIController implements Initializable {
    
    private UserFacade uf;

    @FXML
    private Label accueilLabel;
    
    @FXML
    private Button decoButton;
    
    @FXML
    private MenuItem createRecipeButton;
    @FXML
    private MenuItem account;
    @FXML
    private MenuItem myRequestButton;
    @FXML
    private MenuItem newRequestButton;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uf = new UserFacade(); 
    }    
    
    @FXML
    private void deconnection(ActionEvent event){
        uf.deconnection();
        new NavigationHelpers().changeScene(accueilLabel,"UILogin",null);
    }
    
    @FXML
    private void createRecipeButtonClicked(ActionEvent event){
        new NavigationHelpers().changeScene(accueilLabel,"CreateRecipe",null);
    }

    @FXML
    private void clickOnAccountButton(ActionEvent event) {
        new NavigationHelpers().changeScene(accueilLabel, "UIUserAccountManager", null);
    }
    
   @FXML
    private void clickOnMyRequestsButton(ActionEvent event) {
        new NavigationHelpers().changeScene(accueilLabel, "MyRequests", null);
    }
    @FXML
    private void clickNewRequestButton(ActionEvent event) {
        new NavigationHelpers().changeScene(accueilLabel, "NewRequest", null);
    }
}
