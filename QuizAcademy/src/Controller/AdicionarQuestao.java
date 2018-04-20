
package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdicionarQuestao extends Application{
    public static Stage window;
    private static int widthWindow = 396;
    private static int heightWindow = 410;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        Parent fxmlTelaPrincipal = FXMLLoader.load(getClass().getResource("../View/Adicionar_Questao.fxml"));
        window.setTitle("Adicionar Questão");
        window.setScene(new Scene(fxmlTelaPrincipal,widthWindow, heightWindow));
        window.setMinWidth(widthWindow);
        window.setMinHeight(heightWindow);
        window.show();
    }
    
}
