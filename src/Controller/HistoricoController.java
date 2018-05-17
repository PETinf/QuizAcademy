/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Simulado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Vinicius
 */
public class HistoricoController implements Initializable{
    @FXML
    private Button btnVoltar;
    @FXML
    private ListView<Simulado> listSimulados;
    
    
    @FXML
    void voltar(ActionEvent event) {
        Stage window = (Stage) btnVoltar.getScene().getWindow();
        window.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        Simulado s1 = new Simulado(1,"s1","s1","s1",1.1,"1,2,4");
        Simulado s2 = new Simulado(2,"s2","s2","s2",2.1,"2,4,6");
        Simulado s3 = new Simulado(3,"s3","s3","s3",3.1,"51,23,42");
        Simulado s4 = new Simulado(4,"s4","s4","s4",4.1,"12,323,34");
        
        ObservableList<Simulado> lista = FXCollections.observableArrayList();
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        lista.add(s4);
        
        
        listSimulados.getItems().addAll(s1,s2,s3,s4);
        
        System.out.println(listSimulados.getItems().get(2).getDescricao());
        
    }
}
