/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Model.PerguntaDAO;
import Model.Simulado;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class SimuladoController implements Initializable {

    private Button btn_cancelar;

    @FXML
    private TextField txtDescricaoSimulado;

    @FXML
    private TextField txtDisciplina;

    @FXML
    private TextField txtAssunto;

    @FXML
    private TextField txtNroPerguntas;

    @FXML
    private Button btnGerar;
    @FXML
    private Button btnCancelar;

    private PerguntaDAO dao;

    @FXML
    protected void Cancelar(ActionEvent event) throws Exception {
        Stage s = (Stage) btnGerar.getScene().getWindow();
        s.close();
    }

    public void GerarSimulado() throws Exception {

        try {
            Simulado simulado = new Simulado();

            String nPerguntas = txtNroPerguntas.getText();
            String disciplina = txtDisciplina.getText();
            String assunto = txtAssunto.getText();

            if (verificarCampos(disciplina, nPerguntas)) {
                List<Pergunta> perguntas;
                int nroPerguntas = Integer.parseInt(nPerguntas);

                if (!assunto.equals("")) {
                    perguntas = dao.pesquisarDisciplinaAssunto(disciplina, assunto);
                } else {
                    perguntas = dao.pesquisarDisciplina(disciplina);
                }
                //Valor DEFAULT;
                if (nroPerguntas == 0) {
                    nroPerguntas = 10;
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
                    telaAddQuestao.setScene(cena);
                    telaAddQuestao.showAndWait();

                    Stage window = (Stage) btnGerar.getScene().getWindow();
                    window.close();

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Resultado da Operação:");
                    alerta.setHeaderText("Erro ao gerar o simulado!");

                    String erro = "Perguntas não encontradas para os seguintes parametros:\n\n"
                            + "Disciplina: " + txtDisciplina.getText() + "\n"
                            + "Assunto: " + txtAssunto.getText();

                    alerta.setContentText(erro);
                    alerta.showAndWait();
                }
            }

        } catch (Exception ex) {
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
            listaPerguntas.add(p);
            nroPerguntas--;
        }

        return listaPerguntas;
    }

    public boolean verificarCampos(String p1, String p2) {
        if (p1.equals("")) {
            return false;
        } else if (p2.equals("")) {
            return false;
        } else {
            return true;
        }
    }
}
