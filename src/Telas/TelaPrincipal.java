package Telas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class TelaPrincipal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Parent fxmlTelaPrincipal = FXMLLoader.load(getClass().getResource("/View/TelaPrincipal.fxml"));
        
        primaryStage.setTitle("QuizAcademy");
        primaryStage.setScene(new Scene(fxmlTelaPrincipal));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
