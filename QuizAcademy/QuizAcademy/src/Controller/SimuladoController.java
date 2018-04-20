/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class SimuladoController implements Initializable{
    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_gerar_simulado;

    @FXML
    private TextField txtin_numero_perguntas;

    @FXML
    private TextField txtin_disciplina;

    @FXML
    private TextField txtin_nome_simulado;

    @FXML
    private TextField txtin_assunto;

    @FXML
    protected void Cancelar(ActionEvent event) throws Exception {
        Stage janelaAtual = (Stage) btn_cancelar.getScene().getWindow();
        janelaAtual.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
