/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fabaz
 */
public class NavigationHelpers {
    
    /**
     * Change de page
     * @param event du bouton appuyé necessaire pour obtenir la fenetre actuelle
     * @param page le nom de la page sans FXML ni .fxml
     */
    public void changeScene(ActionEvent event,String page){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../UI/FXML" + page + ".fxml"));
            Scene scene = new Scene(parent);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(scene);
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(NavigationHelpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public NavigationHelpers(){
        
    }
}
