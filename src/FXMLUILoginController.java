/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label labelStatut;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        if(event.getSource()==btLogIn){
            if(!inputMail.getText().equals("") && !inputPwd.getText().equals("")){
                UserFacade uf=new UserFacade(inputMail.getText(),inputPwd.getText());
                if(uf.login(inputMail.getText(),inputPwd.getText())){
                    System.out.println("Connecté");
                    inputMail.setText("Connecté");
                }
                
            }
            else{
                labelStatut.setText("Empty fields");
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
