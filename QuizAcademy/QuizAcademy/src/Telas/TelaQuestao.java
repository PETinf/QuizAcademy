/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Controller.TelaQuestaoController;
import Model.Pergunta;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class TelaQuestao extends Application{
    public static Stage window;
    private static int widthWindow = 600;
    private static int heightWindow = 800;
    
    //********************************** Arrumar
    private static List<Pergunta> perguntas;
    private static int indice;
    //**********************************
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        
        Parent fxmlQuestao = loader.load(getClass().getResource("../View/Selecionar_Questao.fxml").openStream());
        
        TelaQuestaoController tqc = loader.getController();
        
        
        window.setTitle("...");
        window.setScene(new Scene(fxmlQuestao,widthWindow, heightWindow));
        window.setMinWidth(widthWindow);
        window.setMinHeight(heightWindow);
        
        window.show();
        
    }

    public static List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public static void setPerguntas(List<Pergunta> perguntas, int indice) {
        TelaQuestao.perguntas = perguntas;
        TelaQuestao.indice = indice;
    }
    
    public static int getIndice(){
        return indice;
    }
    
    
}