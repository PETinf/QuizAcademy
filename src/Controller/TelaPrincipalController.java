package Controller;

import Model.ConnectionFactory;
import Model.Pergunta;
import Model.PerguntaDAO;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Button;
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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TelaPrincipalController implements Initializable {

    @FXML
    private Button btnExport;
    @FXML
    private Button btnRemoverQuestao;
    @FXML
    private Button btnAddQuestao;
    @FXML
    private Button btnAlterarQuestao;
    @FXML
    private JFXButton btnTrocarBD;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnHistorico;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button gerarHistorico;
    @FXML
    private JFXButton btnAtualizar;
    @FXML
    private TextField txtAssunto;
    @FXML
    private TextField txtDisciplina;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TableColumn<Pergunta, String> colDisciplina;
    @FXML
    private TableColumn<Pergunta, String> colDescricao;
    @FXML
    private TableColumn<Pergunta, String> colAssunto;
    @FXML
    private TableView<Pergunta> tabela;
    @FXML
    private TableColumn<Pergunta, Integer> colId;
    @FXML
    private ComboBox<String> cbBanco;

    private List<Pergunta> lista;
    private PerguntaDAO pdao;

    public void adicionarQuestao() {
        try {
            Stage primaryStage = new Stage();
            
            FXMLLoader loader = new FXMLLoader();
            Parent adicionarQuestao = loader.load(TelaPrincipalController.class.getResource("/View/Adicionar_Questao.fxml"));
            Scene cena = new Scene(adicionarQuestao);
            primaryStage.setScene(cena);
            primaryStage.setResizable(false);
            primaryStage.showAndWait();

            refreshTable();
        } catch (IOException ex) {
            showErrorAsAlert(ex);
        }
    }

    public void removerQuestao() {

        try {
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
                    dao.remove(p);
                } else {
                    alerta.close();
                }
            }

            refreshTable();
        } catch (SQLException ex) {
            showErrorAsAlert(ex);
        }

    }

    public void alterarQuestao() {

        try {
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

                window.setResizable(false);
                window.showAndWait();

                refreshTable();
            }
        } catch (IOException ex) {
            showErrorAsAlert(ex);
        }
    }

    public void selecionarQuestao() {
        try {
            Pergunta p = tabela.getSelectionModel().getSelectedItem();
            if (p != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/Questao.fxml"));
                Parent root = loader.load();

                QuestaoController tqc = loader.getController();
                tqc.iniciarQuestao(lista, p.getId());

                Scene cena = new Scene(root);
                Stage window = new Stage();
                
                window.setResizable(false);
                window.setScene(cena);
                window.showAndWait();
            }
        } catch (IOException ex) {
            showErrorAsAlert(new IOException("Erro ao executar a questão!"));
        } catch (NullPointerException ex) {
            showErrorAsAlert(new NullPointerException("Erro ao carregar a imagem da questão!"));
        }
    }

    public void importarBanco() {

        try {
            FileChooser fc = new FileChooser();
            File arquivo = fc.showOpenDialog(new Stage());
            String dir = System.getProperty("user.dir") + "/db/";
            arquivo.renameTo(new File(dir, arquivo.getName()));
            if (arquivo.getName().endsWith(".db")) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Relatório de operação");
                alerta.setHeaderText("Operação finalizada:");
                alerta.setContentText("O Banco de Dados " + arquivo.getName() + " importado com sucesso!");
                alerta.showAndWait();

                listarbancos();
            } else {
                throw new Exception("Esse aquivo não é um banco SQLITE!");
            }

        } catch (Exception ex) {
            showErrorAsAlert(ex);
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

        } catch (IOException | NullPointerException ex) {
            showErrorAsAlert(ex);
        }

    }

    public void gerarSimulado() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Gerar_Simulado.fxml"));

            Parent root = loader.load();
            Scene cena = new Scene(root);
            Stage window = new Stage();

            window.setResizable(false);
            window.setScene(cena);
            window.showAndWait();
        } catch (IOException ex) {
            showErrorAsAlert(ex);
        }
    }

    public void historico() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Historico_Simulados.fxml"));
            Parent root = loader.load();

            Stage telaHistorico = new Stage();
            Scene cena = new Scene(root);

            HistoricoController hc = loader.getController();
            hc.carregarSimulados();

            telaHistorico.setResizable(false);
            telaHistorico.setScene(cena);
            telaHistorico.showAndWait();
        } catch (IOException ex) {
            showErrorAsAlert(ex);
        } catch (Exception ex){
            showErrorAsAlert(new Exception("Erro desconhecido!"));
        }
    }

    public void filtrar() {
        String disciplina = txtDisciplina.getText();
        String assunto = txtAssunto.getText();
        List<Pergunta> listaAux = null;

        try {
            if (disciplina.length() != 0 && assunto.length() != 0) {
                listaAux = pdao.pesquisarDisciplinaAssunto(disciplina, assunto);
            } else if (disciplina.length() != 0) {
                listaAux = pdao.pesquisarDisciplina(disciplina);
            } else if (assunto.length() != 0) {
                listaAux = pdao.pesquisarAssunto(assunto);
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Resultado da operacao:");
                alerta.setHeaderText("Filtragem inválida:");
                alerta.setContentText("Campos vázios.");
                alerta.showAndWait();
                listaAux = pdao.read();
            }

            if (listaAux.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Resultado da operacao:");
                alerta.setHeaderText("Filtro não encontrado:");
                alerta.setContentText("Os parâmetros da pesquisa retornaram uma lista vazia.");
                alerta.showAndWait();
                listaAux = pdao.read();
            }
            refreshTable(listaAux);
        } catch (SQLException ex) {
            showErrorAsAlert(ex);
        }
    }

    public void pesquisar() {
        String id = txtId.getText();
        String descricao = txtDescricao.getText();
        Pergunta p;

        try {
            p = searchByDescricao(descricao);
            if (p == null && !id.equals("")) {
                int idInt = Integer.parseInt(id);
                p = searchById(idInt);
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Questao.fxml"));
            Parent root = loader.load();

            QuestaoController tqc = loader.getController();
            tqc.iniciarQuestao(lista, p.getId());

            Scene cena = new Scene(root);
            Stage window = new Stage();
            window.setResizable(false);
            window.setScene(cena);
            window.showAndWait();

        } catch (NumberFormatException ex) {
            showErrorAsAlert(new NumberFormatException("Valor inserido no campo 'id' inválido!"));
        } catch (NullPointerException ex) {
            showErrorAsAlert(new NullPointerException("Pergunta não encontrada!"));
        } catch (IOException ex) {
            showErrorAsAlert(new IOException("Erro ao executar o método 'pesquisa'!"));
        } catch (Exception ex){
            showErrorAsAlert(new Exception("Erro desconhecido!"));
        }

    }

    public void iniciarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colAssunto.setCellValueFactory(new PropertyValueFactory<>("assunto"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
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
        } catch (Exception ex) {
            showErrorAsAlert(ex);
        }

    }

    public void selecionarBancoDeDados() {
        try {
            String bd = cbBanco.getSelectionModel().getSelectedItem();

            if (bd != null) {
                ConnectionFactory.setBanco(bd);
                refreshTable();

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
        } catch (Exception ex) {
            showErrorAsAlert(ex);
        }
    }

    public String setNomeArquivo() {
        TextInputDialog alerta = new TextInputDialog();
        alerta.setTitle("Aguardando ...:");
        alerta.setHeaderText("Nome do novo arquivo!");
        alerta.setContentText("Insira o nome do novo banco a ser gerado: ");
        Optional<String> nome = alerta.showAndWait();
        return nome.get();
    }

    public static void showErrorAsAlert(Exception ex) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Resultado da operação:");
        alerta.setHeaderText("Erro ao executar a operacão:");
        alerta.setContentText(ex.getMessage());
        alerta.showAndWait();
    }

    public void refreshTable(List<Pergunta> listaPerguntas) {
        try {
            tabela.setItems(FXCollections.observableList(listaPerguntas));
        } catch (Exception ex) {
            showErrorAsAlert(new Exception("Erro ao atualizar a tabela!"));
        }
    }

    public void refreshTable() {
        try {
            lista = pdao.read();
            refreshTable(lista);
        } catch (SQLException ex) {
            showErrorAsAlert(new Exception("Erro ao atualizar a tabela!"));
        } catch (Exception ex){
            showErrorAsAlert(new Exception("Erro desconhecido!"));
        }
    }

    public Pergunta searchByDescricao(String descricao) {
        Pergunta p = null;
        try {
            p = pdao.pesquisarPorDescricao(descricao);
        } catch (SQLException ex) {
            /// Apenas para não interromper o programa
        }finally {
            return p;
        }
    }

    public Pergunta searchById(int id) {
        Pergunta p = null;
        try {
            p = pdao.pesquisarPorId(id);
        } catch (SQLException ex) {
            /// Apenas para não interromper o programa
        } finally {
            return p;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pdao = new PerguntaDAO();
        iniciarTabela();
        refreshTable();
        listarbancos();
        
        //FUnções de teclas:
        //Enter para pesquisa;
        txtDescricao.setOnKeyPressed((KeyEvent key) -> {
            if(KeyCode.ENTER.equals(key.getCode())){
                pesquisar();
            }
        });
        //Pesquisar Id
        txtId.setOnKeyPressed((KeyEvent key) -> {
            if(KeyCode.ENTER.equals(key.getCode())){
                pesquisar();
            }
        });
        //Filtrar enquanto o usuário digita
        txtDisciplina.setOnKeyReleased((Event e) -> {
            List<Pergunta> aux = lista.stream().
                    filter((p)->p.getDisciplina().contains(txtDisciplina.getText())).
                    collect(Collectors.toList());
            tabela.setItems(FXCollections.observableList(aux));
        });
        //Filtrar
        txtAssunto.setOnKeyReleased((Event e) -> {
            List<Pergunta> aux = lista.stream().
                    filter((p)->p.getAssunto().contains(txtAssunto.getText())).
                    collect(Collectors.toList());
            tabela.setItems(FXCollections.observableList(aux));
        });
        
        tabela.setOnMouseClicked((MouseEvent me) -> {
            if(me.getClickCount() == 2){
                selecionarQuestao();
            }
        });
    }
    
}
