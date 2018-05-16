/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Model.Pergunta;
import Model.PerguntaDAO;
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
public class TelaPrincipal extends Application {
    
    private static Stage window;
    private static final int WIDTH = 580;
    private static final int HEIGHT = 600;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Tela_Principal.fxml"));
        Parent fxmlTelaPrincipal = loader.load();
        
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
        
        /*
        try{
            PerguntaDAO dao = new PerguntaDAO();
            List<Pergunta> ps = dao.pesquisarDisciplina("CálculoA");
            for(Pergunta p: ps){
                System.out.println(p);
            }
        }catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }finally{
            System.out.println("FIM");
        }
        */
    }
    
}
