 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import BL.User;
import Facade.UserFacade;
import UI.UIController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

/**
 *
 * @author fabaz
 */
public class NavigationHelpers {
    
    /**
     * Change the scene
     * @param stage Stage : stage of the controller it come from
     * @param page String the name of the target scene without FXML and .fxml
     * @param givenData Object : the data to give to the next controller
     */
    public void changeScene(Stage stage,String page, Object givenData){
        try {
            //A enlever pour les tests, sinon vous devrez vous reconnecter à chaque fois
            if((page == "HomePage" 
                || page == "CreateRecipe"
                || page == "UIUserAccountManager")
                && UserFacade.connectedUser == null){
                page = "UILogin";
            }
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../UI/FXML" + page + ".fxml"));
            Parent parent = loader.load();
            UIController nextController = loader.getController();
            nextController.receiveData(givenData);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(NavigationHelpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Change scene with a Control instead of a stage
     * @param control Control a item of your scene however the one
     * @param page String the name of the target scene without FXML and .fxml
     * @param givenData Object : the data to give to the next controller
     */
    public void changeScene(Control control,String page, Object givenData){
        Stage stage = (Stage) ((Node) control).getScene().getWindow();
        changeScene(stage,page,givenData);
    }
    
    /**
     *
     */
    public NavigationHelpers(){
    }
}
