/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class Simulado extends Application{
    public static Stage window;
    private static int widthWindow = 400;
    private static int heightWindow = 246;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        Parent fxmlGerarSimulado = FXMLLoader.load(getClass().getResource("../View/Gerar_Simulado.fxml"));
        window.setTitle("Gerar Simulado");
        window.setScene(new Scene(fxmlGerarSimulado,widthWindow, heightWindow));
        window.setMinWidth(widthWindow);
        window.setMinHeight(heightWindow);
        window.show();
    }
}
