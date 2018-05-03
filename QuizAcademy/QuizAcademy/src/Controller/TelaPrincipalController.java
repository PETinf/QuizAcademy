/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Model.PerguntaDAO;
import Telas.AdicionarQuestao;
import Telas.Historico;
import Telas.RemoverQuestao;
import Telas.Simulado;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */



public class TelaPrincipalController implements Initializable{
    
     @FXML
    private Button btnExport;

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
    private ComboBox<String> cbBanco;

    @FXML
    private TableView<Pergunta> tabela;

    @FXML
    private TextField tfAssunto;

    @FXML
    private Button btnPequisar;

    @FXML
    private TextField tfDisciplina;

    @FXML
    private Button gerarHistorico;

    @FXML
    private TextField tfId;
    
    private ObservableList perguntas;
    
    private PerguntaDAO pdao;
    
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

    public void iniciarTabela(){
        TableColumn colId;
        TableColumn colDisciplina;
        TableColumn colAssunto;
        TableColumn colDescricao;
        
        colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(30);
        
        colDisciplina = new TableColumn<>("Disciplina");
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colDisciplina.setPrefWidth(100);
        
        colAssunto = new TableColumn<>("Assunto");
        colAssunto.setCellValueFactory(new PropertyValueFactory<>("assunto"));
        colAssunto.setPrefWidth(100);
        
        colDescricao = new TableColumn<>("Descricão");
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colDescricao.setPrefWidth(200);
        
        tabela.getColumns().addAll(colId, colDisciplina, colAssunto, colDescricao);
    }
    
    public void carregarTabela(){
        perguntas = FXCollections.observableList(pdao.read());
        tabela.setItems(perguntas);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pdao = new PerguntaDAO();
        iniciarTabela();
        carregarTabela();
    }
    
}
