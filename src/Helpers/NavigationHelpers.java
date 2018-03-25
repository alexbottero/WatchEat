/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import UI.AbstractUIController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
     * @param givenData la donné à transmettre à la page suivante
     */
    public void changeScene(ActionEvent event,String page, Object givenData){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../UI/FXML" + page + ".fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(scene);
            AbstractUIController controller = loader.getController();
            controller.setGivenData(givenData);
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(NavigationHelpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public NavigationHelpers(){
        
    }
}
