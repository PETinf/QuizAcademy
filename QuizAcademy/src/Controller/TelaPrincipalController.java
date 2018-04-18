/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class TelaPrincipalController implements Initializable{
    @FXML
    protected void btnAdicionarQuestao(ActionEvent event) throws Exception {
        AdicionarQuestao addQuest = new AdicionarQuestao();
        addQuest.start(new Stage());
    }
    
    @FXML
    protected void btnRemoverQuestao(ActionEvent event) throws Exception {
        RemoverQuestao rmvQuest = new RemoverQuestao();
        rmvQuest.start(new Stage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
