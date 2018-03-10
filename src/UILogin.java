
import java.util.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 */
public class UILogin extends Application{

    /**
     * Default constructor
     */
    public UILogin() {
    }

    /**
     * 
     */
    public TextField mail_field;

    /**
     * 
     */
    public TextField pwd_field;

    /**
     * 
     */
    public Button login;

    /**
     * 
     */
    public Button forgottenPwd;

    /**
     * @param event
     */
    public void clickLoginButton(ActionEvent event) {
        // TODO implement here
    }

    /**
     * @param event
     */
    public void clickForgottenPwdButton(ActionEvent event) {
        // TODO implement here
    }

    /**
     * @param mail
     */
    public void getFirstNameByMail(String mail) {
        // TODO implement here
    }

    /**
     * @param mail
     */
    public void getLastNameByMail(String mail) {
        // TODO implement here
    }

    @Override
        public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLUILogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}