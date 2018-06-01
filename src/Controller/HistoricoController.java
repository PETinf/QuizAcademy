/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Simulado;
import Model.SimuladoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class HistoricoController implements Initializable{
    @FXML private Button btnVoltar;
    @FXML private TableView<Simulado> tableSimulado;
    @FXML private TableColumn<Simulado, Double> colNota; 
    @FXML private TableColumn<Simulado, String> colDisciplina; 
    @FXML private TableColumn<Simulado, String> colDescricao; 
    @FXML private TableColumn<Simulado, String> colAssunto; 
    @FXML private TableColumn<Simulado, String> colId; 
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML 
    public void voltar(ActionEvent event) {
        Stage window = (Stage) btnVoltar.getScene().getWindow();
        window.close();
    }
    
    public void carregarSimulados(){
        SimuladoDAO dao = new SimuladoDAO();
        
        try{
            List<Simulado> aux = dao.read();
        
            ObservableList listaSimulados = FXCollections.observableList(aux);
            formatarTabela();
        
            tableSimulado.getItems().addAll(listaSimulados);
        }catch(SQLException ex){
            TelaPrincipalController.showErrorAsAlert(ex);
        }
    }
    
    public void formatarTabela(){
        colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colAssunto.setCellValueFactory(new PropertyValueFactory<>("assunto"));
        colId.setCellValueFactory(new PropertyValueFactory<>("idPerguntas"));
    }
    
}
