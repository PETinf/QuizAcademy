/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class RespostaController implements Initializable {

    @FXML private ImageView imgResposta;
    @FXML private Button btnVoltar;
    @FXML private Label lblResposta;

    
    @FXML
    public void voltar(){
        Stage window = (Stage) btnVoltar.getScene().getWindow();
        window.close();
    }
    
    public void iniciarTela(Pergunta p) throws NullPointerException{
        
        if(p.getImagemResposta() != null){
            imgResposta.setImage(new Image(getClass().getResourceAsStream("/ImagemResposta/"+p.getImagemResposta())));
        }
        lblResposta.setText(p.getResposta());
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //...
    }    
    
}
