/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Vinicius
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class RemoverQuestaoController implements Initializable{
    
    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_remover;
    
    @FXML
    protected void Adicionar(ActionEvent event) {
        System.out.println("BTN");
    }
    
    @FXML
    protected void Cancelar(ActionEvent event) throws Exception {
        Stage janelaAtual = (Stage) btn_cancelar.getScene().getWindow();
        janelaAtual.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
