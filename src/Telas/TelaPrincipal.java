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
    
    private static Stage window;
    private static final int WIDTH = 580;
    private static final int HEIGHT = 600;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        
        Parent fxmlTelaPrincipal = FXMLLoader.load(TelaPrincipal.class.getResource("/View/Tela_Principal.fxml"));
        
        window.setTitle("QuizAcademy");
        window.setScene(new Scene(fxmlTelaPrincipal,WIDTH, HEIGHT));
        window.setMinWidth(WIDTH);
        window.setMinHeight(HEIGHT);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
