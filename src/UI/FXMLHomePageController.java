package UI;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Facade.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fabaz
 */
public class FXMLHomePageController implements Initializable {
    
    private UserFacade uf;

    @FXML
    private Label accueilLabel;
    
    @FXML
    private Button decoButton;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uf = new UserFacade();
        accueilLabel.setText("Accueil :D");
    }    
    
    @FXML
    private void deconnection(ActionEvent event){
        
        try {
            uf.deconnection();
            Parent truc = FXMLLoader.load(getClass().getResource("FXMLUILogin.fxml"));
            Scene homePage = new Scene(truc);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(homePage);
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}