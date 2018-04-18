/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;

/**
 *
 * @author Vinicius
 */
public class TelaPrincipalController implements Initializable{
    @FXML
    protected void teste(ActionEvent event) {
        System.out.println("BTN");
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
