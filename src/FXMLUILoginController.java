/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
    private PasswordField inputPwd;
    @FXML
    private Button btLogIn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        if(event.getSource()==btLogIn){
            if(!inputMail.getText().equals("") && !inputPwd.getText().equals("")){
                UserFacade uf=new UserFacade(inputMail.getText(),inputPwd.getText());
                if(uf.login(inputMail.getText(),inputPwd.getText())){
                    System.out.println("Connecté");
                    inputMail.setText("Connecté");
                }
                
            }
            else{
                System.out.println("Des champs sont vides");
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
