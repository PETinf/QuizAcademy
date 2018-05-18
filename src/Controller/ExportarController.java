/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class ExportarController implements Initializable {

    @FXML
    private TextField txtDiretorio;
    @FXML
    private TextField txtNomeBD;
    @FXML
    private Button btnEscolherPasta;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void escolherPsta(MouseEvent event) {
        
        
    }

    @FXML
    private void confirmar(MouseEvent event) {
        
        
        
    }

    @FXML
    private void cancelar(MouseEvent event) {
        
        Stage janela = (Stage) btnCancelar.getScene().getWindow();
        janela.close();
        
    }
    
}
