/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Model.PerguntaDAO;
import Model.Simulado;
import Model.Util;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class SimuladoController implements Initializable {

    private Button btn_cancelar;

    @FXML private TextField txtDescricaoSimulado;
    @FXML private Button btnGerar;
    @FXML private Button btnCancelar;
    @FXML private ComboBox<String> cbDisciplina;
    @FXML private ComboBox<String> cbAssunto;
    @FXML private ComboBox<Integer> cmNroPerguntas;
    
    private PerguntaDAO dao;
    
    @FXML
    protected void Cancelar(ActionEvent event) throws Exception {
        Stage s = (Stage) btnGerar.getScene().getWindow();
        s.close();
    }

    @FXML
    public void GerarSimulado() throws Exception {

        try {
            Simulado simulado = new Simulado();

            String disciplina = cbDisciplina.getSelectionModel().getSelectedItem();
            String assunto = cbAssunto.getSelectionModel().getSelectedItem();
            Integer nroPerguntas = cmNroPerguntas.getValue();
            
            if (verificarCampos(disciplina, nroPerguntas)) {
                
                List<Pergunta> perguntas;
                
                if (!assunto.equals("TODOS")) {
                    perguntas = dao.pesquisarDisciplinaAssunto(disciplina, assunto);
                } else {
                    perguntas = dao.pesquisarDisciplina(disciplina);
                }
                
                perguntas = escolherPerguntasAleatoriamente(perguntas, nroPerguntas);
                
                simulado.setDescricao(txtDescricaoSimulado.getText());
                simulado.setDisciplina(disciplina);
                simulado.setAssunto(assunto);

                if (!perguntas.isEmpty()) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/Simulado.fxml"));
                    Parent root = loader.load();

                    SimuladoQuestaoController sqc = loader.getController();
                    sqc.iniciarSimulado(perguntas, simulado);

                    Stage telaAddQuestao = new Stage();
                    Scene cena = new Scene(root);
                    telaAddQuestao.setResizable(false);
                    telaAddQuestao.setScene(cena);
                    telaAddQuestao.showAndWait();

                    Stage window = (Stage) btnGerar.getScene().getWindow();
                    window.close();

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Resultado da Operação:");
                    alerta.setHeaderText("Erro ao gerar o simulado!");

                    String erro = "Perguntas não encontradas para os seguintes parametros:\n\n"
                            + "Disciplina: " + disciplina + "\n"
                            + "Assunto: " + assunto;

                    alerta.setContentText(erro);
                    alerta.showAndWait();
                }
            }

        } catch (IOException | NumberFormatException | SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Resultado da Operação:");
            alerta.setHeaderText("Erro ao gerar o simulado!");
            alerta.setContentText("Informações inválidas encontradas nos compos obrigatórios!");
            alerta.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new PerguntaDAO();
        iniciarCampos();
        iniciarFuncoes();
    }

    public List<Pergunta> escolherPerguntasAleatoriamente(List<Pergunta> perguntas, int nroPerguntas) {
        Random random = new Random();
        List<Pergunta> listaPerguntas = new ArrayList<>();
        Pergunta p;
        int aux;
        
        while (nroPerguntas > 0 && !perguntas.isEmpty()) {
            aux = random.nextInt(perguntas.size());
            p = perguntas.get(aux);
            perguntas.remove(aux);
            if(p.isSimulavel()){
                listaPerguntas.add(p);
                nroPerguntas--;
            }
        }
        
        return listaPerguntas;
    }

    public boolean verificarCampos(String p1, Integer p2) {
        if (p1 == null){
            return false;
        }else{
            return p2 != null;
        }
    }
    
    private void iniciarCampos(){
        cbDisciplina.setItems(FXCollections.observableList(Util.disciplinas()));
    }
    
    private void iniciarFuncoes(){
        cbDisciplina.setOnAction(e -> {
            String disciplina = cbDisciplina.getSelectionModel().getSelectedItem();
            if(disciplina != null){
                cbAssunto.setItems(FXCollections.observableList(Util.assuntos(disciplina)));
                atualizarNroPerguntas();
            }
        });
        cbAssunto.setOnAction(e -> {
            atualizarNroPerguntas();
        });
    }
    
    private void atualizarNroPerguntas(){
        List<Integer> numeros = new LinkedList<>();
        String disciplina = cbDisciplina.getSelectionModel().getSelectedItem();
        String assunto = cbAssunto.getSelectionModel().getSelectedItem();
        
        int n = Util.nroPerguntas(disciplina, assunto);
        for(int i=1; i<=n; i++){
            numeros.add(i);
        }
        
        cmNroPerguntas.setItems(FXCollections.observableList(numeros));
    }
}
