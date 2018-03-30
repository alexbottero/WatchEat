/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Request;
import Facade.RequestFacade;
import Helpers.NavigationHelpers;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author alexandre
 */
public class FXMLNewRequestController implements Initializable, UIController {

    @FXML
    private Button sendButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextArea descRequ;
    private RequestFacade rf;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rf = new RequestFacade();// TODO
    }    
    
    /**
     * when you use send button
     * @param event 
     */
    @FXML
    private void clickSendButton(ActionEvent event) {;
        if (!descRequ.getText().equals("")){
            rf.create(descRequ.getText(), "en attente");
            new NavigationHelpers().changeScene(sendButton, "MyRequests", null);
        }
    }
    /**
     * when you use back button
     * @param event 
     */
    @FXML
    private void clickBackButton(ActionEvent event) {
        new NavigationHelpers().changeScene(sendButton, "MyRequests", null);
    }

    @Override
    public void receiveData(Object givenData) {
    }
}
