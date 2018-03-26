package Application;






import Helpers.NavigationHelpers;
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
public class WatchEat extends Application{
    
    private static WatchEat app;

    @Override
    public void start(Stage stage) throws Exception {
        new NavigationHelpers().changeScene(stage,"HomePage",null);
    }
    
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Double.parseDouble("1.81"));
        //System.out.println(Double.parseDouble("1,81"));
        launch(args);
    }
}