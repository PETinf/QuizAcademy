/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionFactory;
import Model.Pergunta;
import Model.PerguntaDAO;
import Telas.Historico;
import Telas.Simulado;
import Telas.TelaPrincipal;
import Telas.TelaQuestao;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

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
    private Button btnAlterarQuestao;

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
    
    @FXML
    private TableColumn<Pergunta,String> colDisciplina;

    @FXML
    private TableColumn<Pergunta,String> colDescricao;

    @FXML
    private TableColumn<Pergunta,String> colAssunto;
    
    @FXML
    private TableColumn<Pergunta,Integer> colId;
    
    
    
    private List<Pergunta> lista; 
    private PerguntaDAO pdao;
    private ObservableList perguntas;
    
    
    @FXML
    protected void adicionarQuestao() throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TelaPrincipalController.class.getResource("../View/Adicionar_Questao.fxml"));
        Parent root = loader.load();
        
        Stage telaAddQuestao = new Stage();
        Scene cena = new Scene(root);
        
        AdicionarQuestaoController aqc = loader.getController();
        
        telaAddQuestao.setScene(cena);
        telaAddQuestao.showAndWait();
        
        carregarTabela();
    }
    
    @FXML
    protected void removerQuestao(ActionEvent event) throws Exception {
        
        Pergunta p = tabela.getSelectionModel().getSelectedItem();
        PerguntaDAO dao = new PerguntaDAO();
        
        if(p != null){
            Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Aguardando a confirmação da operação...");
            alerta.setHeaderText("Você realmente deseja excluir esa questão?:");
            
            String questao = "Questão:\n\n";

            questao += "ID: "+p.getId();
            
            if(p.getDescricao() != null){
                questao += "\nDescrição: "+p.getDescricao();
            }
            if(p.getDisciplina() != null){
                questao += "\nDisciplina: "+p.getDisciplina();
            }
            if(p.getAssunto() != null){
                questao += "\nAssunto: "+p.getAssunto()+"\n";
            }
            
            alerta.setContentText(questao);
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.get() == ButtonType.OK){
                dao.remove(p);
                carregarTabela();
            } else {
               alerta.close();
            }
        }
        
        
        carregarTabela();
    }
    
    public void alterarQuestao() throws IOException{
        
        Pergunta pergunta = tabela.getSelectionModel().getSelectedItem();
        
        if(pergunta != null){
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TelaPrincipalController.class.getResource("../View/Alterar_Questao.fxml"));
            Parent root = loader.load();

            Stage telaAddQuestao = new Stage();
            Scene cena = new Scene(root);

            AlterarQuestaoController c = loader.getController();
            c.setPergunta(pergunta);
            c.carregarDados();

            telaAddQuestao.setScene(cena);
            telaAddQuestao.showAndWait();

            carregarTabela();
        }
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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colAssunto.setCellValueFactory(new PropertyValueFactory<>("assunto"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }
    
    public void carregarTabela(){
        lista = pdao.read();
        perguntas = FXCollections.observableList(lista);
        tabela.setItems(perguntas);
    }
    
    public void selecionarQuestao(){
        //...
    }
    
    public void listarbancos(){
        
        File pasta = new File(System.getProperty("user.dir")+"/db/");
        List<String> bds = new ArrayList<>();
        File[] arquivos = pasta.listFiles();
        
        for(File arq: arquivos){
            if(arq.getName().endsWith(".db")){
                bds.add(arq.getName());
            }
        }
        ObservableList obs = FXCollections.observableArrayList(bds);
        cbBanco.setItems(obs);
        
        
    }
    public void selecionarBancoDeDados(){
        String bd = cbBanco.getSelectionModel().getSelectedItem();
        
        if(bd != null){
            ConnectionFactory.setBanco(bd);
            carregarTabela();
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Operação finalizada:");
            alerta.setContentText("O Banco de Dados foi alterado para : "+bd+" com sucesso!");
            alerta.showAndWait();
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Erro na operação:");
            alerta.setContentText("Banco de Dados não selecionado ou não encontrado!");
            alerta.showAndWait();
        }
    }
    
    
    public void importarBanco(){
        
        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Banco de Dados", "."));
        File arquivo = fc.showOpenDialog(new Stage());
        if(arquivo != null && arquivo.getName().endsWith(".db")){
            String dir = System.getProperty("user.dir")+"/db/";
            arquivo.renameTo(new File(dir, arquivo.getName()));
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Operação finalizada:");
            alerta.setContentText("O Banco de Dados "+arquivo.getName()+" importado com sucesso!");
            alerta.showAndWait();
            
            listarbancos();
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Erro na operação:");
            alerta.setContentText("Não foi possivel importar o banco de dados!");
            alerta.showAndWait();
        }
        
        
    }
    public void exportarBanco(){
        
        DirectoryChooser dc = new DirectoryChooser();
        File dir = dc.showDialog(new Stage());
        
        File bd = new File(System.getProperty("user.dir")+"/db/"+ConnectionFactory.getBanco());
        
        if(dir != null){
        }
    }
    public void gerarSimulado(){
        //....
    }
    public void historico(){
        //...
    }
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pdao = new PerguntaDAO();
        iniciarTabela();
        carregarTabela();
        listarbancos();
    }
    
    
    
}


