/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private static int widthWindow = 580;
    private static int heightWindow = 600;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        
        Parent fxmlTelaPrincipal = FXMLLoader.load(getClass().getResource("../View/Tela_Principal.fxml"));
        window.setTitle("QuizAcademy");
        window.setScene(new Scene(fxmlTelaPrincipal,widthWindow, heightWindow));
        window.setMinWidth(widthWindow);
        window.setMinHeight(heightWindow);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
