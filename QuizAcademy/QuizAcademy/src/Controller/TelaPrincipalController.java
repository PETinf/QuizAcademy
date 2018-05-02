/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Telas.AdicionarQuestao;
import Telas.Historico;
import Telas.RemoverQuestao;
import Telas.Simulado;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */



public class TelaPrincipalController implements Initializable{
    
    @FXML
    private Button btnExport;

    @FXML
    private TreeView<?> listDados;

    @FXML
    private Button btnRemoverQuestao;

    @FXML
    private Button btnAddQuestao;

    @FXML
    private Button btnTrocarBD;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnHistorico;

    @FXML
    private TextField tfNumero;

    @FXML
    private ComboBox<?> cbBanco;

    @FXML
    private TextField tfAssunto;

    @FXML
    private Button btnPequisar;

    @FXML
    private TextField tfDisciplina;

    @FXML
    private Button btnGerarHistorico;

    @FXML
    private TextField tfId;
    
    @FXML
    protected void adicionarQuestao(ActionEvent event) throws Exception {
        AdicionarQuestao addQuest = new AdicionarQuestao();
        addQuest.start(new Stage());
    }
    
    @FXML
    protected void removerQuestao(ActionEvent event) throws Exception {
        RemoverQuestao rmvQuest = new RemoverQuestao();
        rmvQuest.start(new Stage());
    }
    
    @FXML
    protected void gerarSimulado(ActionEvent event) throws Exception {
        Simulado simulado = new Simulado();
        simulado.start(new Stage());
    }
    
    @FXML
    protected void historico(ActionEvent event) throws Exception {
        Historico historico = new Historico();
        historico.start(new Stage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
