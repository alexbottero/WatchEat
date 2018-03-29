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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author alexandre
 */
public class FXMLMyRequestController implements Initializable, UIController {

    @FXML
    private Button newRequestButton;
    @FXML
    private Button backButton;

    private RequestFacade rf;
    @FXML
    private GridPane requestPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rf = new RequestFacade();
        ObservableList<Request> requests = rf.getRequests();
        int i = 0;
        for(Request r:requests){
            TextArea areaPane = new TextArea();
            areaPane.setText(r.getDesc());
            Label labelPan = new Label();
            labelPan.setText(r.getEtat());
            requestPane.add(areaPane, 0,i );
            requestPane.add(labelPan, 1, i);
            i++;
        }// TODO
    }    
    @FXML
    private void clickNewRequestButton(ActionEvent event) {
        new NavigationHelpers().changeScene(newRequestButton, "NewRequest", null);
    }
    @FXML
    private void clickBackButton(ActionEvent event){
        new NavigationHelpers().changeScene(backButton, "HomePage", null);
    }

    @Override
    public void receiveData(Object givenData) {
    }
}
