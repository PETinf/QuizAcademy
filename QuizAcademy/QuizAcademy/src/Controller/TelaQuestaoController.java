/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Telas.TelaQuestao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Vinicius
 */
public class TelaQuestaoController implements Initializable{
    
    @FXML
    private Button btnResposta;

    @FXML
    private Label lblDescricao;

    @FXML
    private Label lblEnunciado;

    @FXML
    private Label lblDisciplina;

    @FXML
    private ImageView imagemEnunciado;

    @FXML
    private Label lblAssunto;

    @FXML
    private Button btnPrev;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnNext;
    
    //******************************* Arrumar
    private static List<Pergunta> perguntas;
    private static int indice;
    //*******************************
    
    public TelaQuestaoController(){
        
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarDados();
        System.out.println(indice);
    }
    
    
    public boolean next(){
        return true;
    }
    
    public void atualizar(){
        /*
        Pergunta pergunta = perguntas.get(0);
        
        lblDisciplina.setText(pergunta.getDisciplina());
        lblAssunto.setText(pergunta.getAssunto());
        lblDescricao.setText(pergunta.getId()+") "+pergunta.getDescricao());
        lblEnunciado.setText(pergunta.getEnunciado());
        String caminhoImagem = pergunta.getImagemEnunciado();
        if(caminhoImagem!=null){
            imagemEnunciado.setImage(new Image(caminhoImagem));
        }
        */
    }
    
    public void carregarDados(){
        perguntas = TelaQuestao.getPerguntas();
        indice = TelaQuestao.getIndice();
    }
   
}
