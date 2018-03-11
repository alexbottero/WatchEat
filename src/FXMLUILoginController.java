/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author alexandre
 */
public class FXMLUILoginController implements Initializable {

    @FXML
    private TextField inputMail;
    @FXML
    private TextField inputPwd;
    @FXML
    private Button btLogIn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==btLogIn){
            if(!inputMail.getText().equals("") && !inputPwd.getText().equals("")){
                UserFacade uf=new UserFacade(inputMail.getText(),inputPwd.getText());
            }
            else{
                System.out.println("pas plein");
            }
        }

        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
