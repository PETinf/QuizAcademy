/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionFactory;
import Model.Pergunta;
import Model.PerguntaDAO;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Vinicius
 */
public class TelaPrincipalController implements Initializable {

    @FXML private Button btnExport;
    @FXML private Button btnRemoverQuestao;
    @FXML private Button btnAddQuestao;
    @FXML private Button btnAlterarQuestao;
    @FXML private Button btnTrocarBD;
    @FXML private Button btnImport;
    @FXML private Button btnHistorico;
    @FXML private Button btnPequisar;
    @FXML private Button gerarHistorico;
    @FXML private TextField txtAssunto;
    @FXML private TextField txtDisciplina;
    @FXML private TextField txtId;
    @FXML private TextField txtDescricao;
    @FXML private TableColumn<Pergunta, String> colDisciplina;
    @FXML private TableColumn<Pergunta, String> colDescricao;
    @FXML private TableColumn<Pergunta, String> colAssunto;
    @FXML private TableView<Pergunta> tabela;
    @FXML private TableColumn<Pergunta, Integer> colId;
    @FXML private ComboBox<String> cbBanco;
    
    private List<Pergunta> lista;
    private PerguntaDAO pdao;
    private ObservableList perguntas;

    @FXML
    protected void adicionarQuestao() throws Exception {
        /*
        try {
            
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(TelaPrincipalController.class.getResource("/View/Adicionar_Questao.fxml"));

            Stage telaAddQuestao = new Stage();
            Scene cena = new Scene(root);


            AdicionarQuestaoController aqc = loader.getController();

            telaAddQuestao.setScene(cena);
            telaAddQuestao.showAndWait();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        carregarTabela();
        */
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        
        Parent adicionarQuestao = FXMLLoader.load(TelaPrincipalController.class.getResource("/View/Adicionar_Questao.fxml"));
        Scene cena = new Scene(adicionarQuestao);
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    @FXML
    protected void removerQuestao(ActionEvent event) throws Exception {

        Pergunta p = tabela.getSelectionModel().getSelectedItem();
        PerguntaDAO dao = new PerguntaDAO();

        if (p != null) {
            Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Aguardando a confirmação da operação...");
            alerta.setHeaderText("Você realmente deseja excluir esa questão?:");

            String questao = "Questão:\n\n";

            questao += "ID: " + p.getId();

            if (p.getDescricao() != null) {
                questao += "\nDescrição: " + p.getDescricao();
            }
            if (p.getDisciplina() != null) {
                questao += "\nDisciplina: " + p.getDisciplina();
            }
            if (p.getAssunto() != null) {
                questao += "\nAssunto: " + p.getAssunto() + "\n";
            }

            alerta.setContentText(questao);
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    dao.remove(p);
                    carregarTabela();
                } catch (SQLException ex) {
                    TelaPrincipalController.showErrorAsDialog(ex);
                }
            } else {
                alerta.close();
            }
        }

        carregarTabela();
    }

    public void alterarQuestao() throws IOException {

        Pergunta pergunta = tabela.getSelectionModel().getSelectedItem();

        if (pergunta != null) {
            Stage window = new Stage();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TelaPrincipalController.class.getResource("/View/Alterar_Questao.fxml"));
            
            Parent alterarQuestao = loader.load();
            Scene cena = new Scene(alterarQuestao);
            window.setScene(cena);
            
            AlterarQuestaoController controller = loader.getController();
            controller.iniciarTela(pergunta);
            
            window.showAndWait();

            carregarTabela();
        }
    }
    public void iniciarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colAssunto.setCellValueFactory(new PropertyValueFactory<>("assunto"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }

    public void carregarTabela() {
        try {
            lista = pdao.read();
            perguntas = FXCollections.observableList(lista);
            tabela.setItems(perguntas);
        } catch (SQLException ex) {
            showErrorAsDialog(ex);
        } catch (NullPointerException ex) {
            System.out.println("TelaPrincipalController.carregarTabela: "+ex.getMessage());
        }
    }

    public void selecionarQuestao() throws IOException {
        Pergunta p = tabela.getSelectionModel().getSelectedItem();
        if (p != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Questao.fxml"));
            Parent root = loader.load();

            TelaQuestaoController tqc = loader.getController();
            tqc.iniciarQuestao(lista, p.getId());

            Scene cena = new Scene(root);
            Stage window = new Stage();
            window.setScene(cena);
            window.showAndWait();
        }
    }

    public void listarbancos() {
        try {
            File pasta = new File(System.getProperty("user.dir") + "/db/");
            List<String> bds = new ArrayList<>();
            File[] arquivos = pasta.listFiles();

            for (File arq : arquivos) {
                if (arq.getName().endsWith(".db")) {
                    bds.add(arq.getName());
                }
            }
            ObservableList obs = FXCollections.observableArrayList(bds);
            cbBanco.setItems(obs);
        } catch (NullPointerException ex) {
            System.out.println("TelaPrincipalController.listabancos: "+ex.getMessage());
        }

    }

    public void selecionarBancoDeDados() {
        String bd = cbBanco.getSelectionModel().getSelectedItem();

        if (bd != null) {
            ConnectionFactory.setBanco(bd);
            carregarTabela();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Operação finalizada:");
            alerta.setContentText("O Banco de Dados foi alterado para : " + bd + " com sucesso!");
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Erro na operação:");
            alerta.setContentText("Banco de Dados não selecionado ou não encontrado!");
            alerta.showAndWait();
        }
    }

    public void importarBanco() {

        FileChooser fc = new FileChooser();
        File arquivo = fc.showOpenDialog(new Stage());
        if (arquivo != null && arquivo.getName().endsWith(".db")) {
            String dir = System.getProperty("user.dir") + "/db/";
            arquivo.renameTo(new File(dir, arquivo.getName()));

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Operação finalizada:");
            alerta.setContentText("O Banco de Dados " + arquivo.getName() + " importado com sucesso!");
            alerta.showAndWait();

            listarbancos();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Relatório de operação");
            alerta.setHeaderText("Erro na operação:");
            alerta.setContentText("Não foi possivel importar o banco de dados!");
            alerta.showAndWait();
        }

    }

    public void exportarBanco() {

        DirectoryChooser dc = new DirectoryChooser();

        try {
            String c = dc.showDialog(new Stage()).getAbsolutePath();
            String nome = setNomeArquivo();
            Path destino = Paths.get(c + "//" + nome + ".db");
            Path arquivo = Paths.get(System.getProperty("user.dir") + "/db/" + ConnectionFactory.getBanco());

            Files.copy(arquivo, destino, StandardCopyOption.REPLACE_EXISTING);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Resultado da operacao:");
            alerta.setHeaderText("Operacao finalizada:");
            alerta.setContentText("Banco de dados exportado com sucesso!");
            alerta.showAndWait();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Resultado da operacao:");
            alerta.setHeaderText("ERRO!");
            alerta.setContentText("Erro ao exportar o banco de dados!");
            alerta.showAndWait();
        }

    }

    @FXML
    protected void gerarSimulado(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Gerar_Simulado.fxml"));

        Parent root = loader.load();
        Scene cena = new Scene(root);
        Stage window = new Stage();

        window.setScene(cena);
        window.showAndWait();

    }

    public void historico() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Historico_Simulados.fxml"));
        Parent root = loader.load();

        Stage telaHistorico = new Stage();
        Scene cena = new Scene(root);

        HistoricoController hc = loader.getController();
        hc.carregarSimulados();

        telaHistorico.setScene(cena);
        telaHistorico.showAndWait();
    }

    public String setNomeArquivo() {
        TextInputDialog alerta = new TextInputDialog();
        alerta.setTitle("Aguardando ...:");
        alerta.setHeaderText("Nome do novo arquivo!");
        alerta.setContentText("Insira o nome do novo banco a ser gerado: ");
        Optional<String> nome = alerta.showAndWait();
        return nome.get();
    }

    public static void showErrorAsDialog(SQLException ex) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Resultado da operação:");
        alerta.setHeaderText("Erro:");
        alerta.setContentText(ex.getMessage());
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            pdao = new PerguntaDAO();
            iniciarTabela();
            carregarTabela();
            listarbancos();
        }catch(Exception ex){
            System.out.println("TelPrincipalController.initialize: "+ex.getMessage());
        }
    }
}
