/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Facade.UserFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author julia
 */
public class FXMLMyMenusController implements Initializable {

    //private MenuManagerFacade mnf;

    @FXML
    private Label content_label;
    
    @FXML
    private Button back_button;
    
    @FXML
    private ComboBox name_menu;
    
    @FXML
    private Button edit_button;
    
    @FXML
    private Button delete_button;
    
    @FXML
    private Button create_button;
    
     @FXML
    private Button search_button;
     
     @FXML
    private ComboBox consumable_combobox;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
