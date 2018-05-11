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
public class Historico extends Application{
    public static Stage window;
    private static int widthWindow = 444;
    private static int heightWindow = 538;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        Parent fxmlHistorico = FXMLLoader.load(getClass().getResource("../View/Historico_Simulados.fxml"));
        window.setTitle("Gerar Simulado");
        window.setScene(new Scene(fxmlHistorico,widthWindow, heightWindow));
        window.setMinWidth(widthWindow);
        window.setMinHeight(heightWindow);
        window.show();
    }
}
