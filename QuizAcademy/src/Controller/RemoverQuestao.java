
package controller;

import static controller.AdicionarQuestao.window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class RemoverQuestao extends Application{
    public static Stage window;
    private static int widthWindow = 296;
    private static int heightWindow = 230;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        Parent fxmlRemoverQuestao = FXMLLoader.load(getClass().getResource("../View/Remover_Questao.fxml"));
        window.setTitle("Remover Questão");
        window.setScene(new Scene(fxmlRemoverQuestao,widthWindow, heightWindow));
        window.setMinWidth(widthWindow);
        window.setMinHeight(heightWindow);
        window.show();
    }
    
}
