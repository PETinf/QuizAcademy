/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class SimuladoQuestaoController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnAvancar;
    @FXML
    private Button btnComitar;
    @FXML
    private Button btnFinalizar;
    @FXML
    private Label lblDisciplina;
    @FXML
    private Label lblAssunto;
    @FXML
    private Label lblDescricao;
    @FXML
    private Label lblEnunciado;
    @FXML
    private ImageView imgEnunciado;
    @FXML
    private ImageView imgConfirm;
    @FXML
    private TextField txtResposta;

    private List<Pergunta> perguntas;
    private int nroPergunta;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nroPergunta = 0;
    }    
    
    public void setPerguntas(List<Pergunta> perguntas){
        this.perguntas = perguntas;
    }
    
    public void carregarCampos(int nroPergunta){
        Pergunta p = perguntas.get(nroPergunta);
        lblDisciplina.setText(p.getDisciplina());
        lblAssunto.setText(p.getAssunto());
        lblDescricao.setText(p.getDescricao());
        lblEnunciado.setText(p.getEnunciado());
        if(p.getImagemEnunciado() != null){
            imgEnunciado.setImage(new Image("file:///"+Pergunta.PATHENUNCIADO+p.getImagemEnunciado()));
        }else{
            imgEnunciado.setImage(null);
        }
    }
    
    public void next(){
        if(nroPergunta < perguntas.size()-1){
            carregarCampos(++nroPergunta);
        }
    }
    
    public void prev(){
        if(nroPergunta > 0){
            carregarCampos(--nroPergunta);
        }
    }
    
    public void comitar(){
        //.......
    }
}
