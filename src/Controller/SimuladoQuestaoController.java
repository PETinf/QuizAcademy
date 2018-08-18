/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Model.Simulado;
import Model.SimuladoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class SimuladoQuestaoController implements Initializable {

    @FXML private Button btnVoltar;
    @FXML private Button btnAvancar;
    @FXML private Button btnComitar;
    @FXML private Button btnFinalizar;
    @FXML private Label lblDisciplina;
    @FXML private Label lblAssunto;
    @FXML private Label lblDescricao;
    @FXML private Label lblEnunciado;
    @FXML private ImageView imgEnunciado;
    @FXML private ImageView imgConfirm;
    @FXML private TextField txtResposta;
    private Simulado simulado;
    private List<Pergunta> perguntas;
    private int nroPergunta;
    private Integer[] respostas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public void carregarCampos(int nroPergunta) {
        Pergunta p = perguntas.get(nroPergunta);
        lblDisciplina.setText(p.getDisciplina());
        lblAssunto.setText(p.getAssunto());
        lblDescricao.setText(p.getDescricao());
        lblEnunciado.setText(p.getEnunciado());
        if (p.getImagemEnunciado() != null) {
            imgEnunciado.setImage(new Image("file:///"+caminhoPadrao() + p.getImagemEnunciado()));
        } else {
            imgEnunciado.setImage(null);
        }
    }

    public void iniciarSimulado(List<Pergunta> perguntas, Simulado simulado) {

        this.perguntas = perguntas;
        this.simulado = simulado;
        respostas = new Integer[perguntas.size()];
        nroPergunta = 0;
        carregarCampos(nroPergunta);

    }

    public void next() {
        if (nroPergunta < perguntas.size() - 1) {
            carregarCampos(++nroPergunta);
            atualizarResposta();
        }
    }

    public void atualizarResposta() {
        txtResposta.setText("");
        if (respostas[nroPergunta] != null) {
            imgConfirm.setImage(new Image("file:///"+caminhoPadrao()+ respostas[nroPergunta] + ".png"));
            btnComitar.setDisable(true);
        } else {
            imgConfirm.setImage(null);
            btnComitar.setDisable(false);
        }
    }

    public void prev() {
        if (nroPergunta > 0) {
            carregarCampos(--nroPergunta);
            atualizarResposta();
        }
    }

    public void comitar() {
        Pergunta p = perguntas.get(nroPergunta);
        String resposta = p.getResposta();
        if (txtResposta.getText().equals(resposta)) {
            respostas[nroPergunta] = 1;
            imgConfirm.setImage(new Image("file:///"+caminhoPadrao() + 1 + ".png"));
        } else {
            respostas[nroPergunta] = 0;
            imgConfirm.setImage(new Image("file:///"+caminhoPadrao()+ 0 + ".png"));
        }
        btnComitar.setDisable(true);
    }

    public void finalizarSimulado() {
        double resultado = 0;
        String ids = "";

        for (Integer resposta : respostas) {
            if (resposta != null) {
                resultado += resposta;
            }
        }
        for (Pergunta p : perguntas) {
            ids += "," + p.getId();
        }
        ids = ids.substring(1, ids.length());

        resultado = (int) ((resultado / perguntas.size()) * 100);

        simulado.setNota(resultado);
        simulado.setIdPerguntas(ids);

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Resultado do Simulado");
        alerta.setHeaderText("Nota: " + resultado);
        alerta.setContentText("Deseja salvar esse simulado?");

        Optional<ButtonType> opcao = alerta.showAndWait();
        if (opcao.get() == ButtonType.OK) {
            salvarSimulado();
        }

        Stage window = (Stage) btnFinalizar.getScene().getWindow();
        window.close();
    }

    public void salvarSimulado() {
        SimuladoDAO dao = new SimuladoDAO();
        try {
            dao.insert(simulado);
        } catch (SQLException ex) {
            TelaPrincipalController.showErrorAsAlert(ex);
        }
    }
    
    public static String caminhoPadrao(){
        String path = System.getProperty("user.dir") + "/Imagens/";
        return path;
    }
}
