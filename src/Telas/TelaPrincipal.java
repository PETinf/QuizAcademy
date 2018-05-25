/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Model.Pergunta;
import Model.PerguntaDAO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        FXMLLoader loader = new FXMLLoader();
        Parent fxmlTelaPrincipal = loader.load(getClass().getResource("/View/Tela_Principal.fxml"));
        
        window.setTitle("QuizAcademy");
        window.setScene(new Scene(fxmlTelaPrincipal,WIDTH, HEIGHT));
        window.setMinWidth(WIDTH);
        window.setMinHeight(HEIGHT);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
