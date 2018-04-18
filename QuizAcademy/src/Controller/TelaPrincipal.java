/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.HashSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class TelaPrincipal extends Application {
    
    private static Stage window;
    private static int widthWindow = 581;
    private static int heightWindow = 505;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        
        Parent fxmlTelaPrincipal = FXMLLoader.load(getClass().getResource("../View/Tela_Principal.fxml"));
        window.setTitle("QuizAcademy");
        window.setScene(new Scene(fxmlTelaPrincipal,widthWindow, heightWindow));
        window.setMinWidth(600);
        window.setMinHeight(505);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
