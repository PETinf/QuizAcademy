/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class TelaQuestaoController implements Initializable {

    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private Label lblDisciplina;
    @FXML
    private Label lblAssunto;
    @FXML
    private Button btnResposta;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblDescricao;
    @FXML
    private Label lblEnunciado;
    @FXML
    private ImageView imgEnunciado;
    private Pergunta pergunta;
    private List<Pergunta> perguntas;
    private ListIterator<Pergunta> it;
    
    
    
    public void voltar(){
        Stage window = (Stage) btnVoltar.getScene().getWindow();
        window.close();
    }
    
    
    
    public void iniciarQuestao(List<Pergunta> perguntas, int id){
        this.perguntas = perguntas;
        it = perguntas.listIterator();
        
        while(it.hasNext()){
            pergunta = it.next();
            if(pergunta.getId() == id){
                break;
            }
        }
        
        carregarDados();
    }
    
    public void carregarDados(){
        lblDisciplina.setText(pergunta.getDisciplina());
        lblAssunto.setText(pergunta.getAssunto());
        lblDescricao.setText(pergunta.getDescricao());
        lblEnunciado.setText(pergunta.getEnunciado());
        if(pergunta.getImagemEnunciado() != null){
            imgEnunciado.setImage(new Image(getClass().getResourceAsStream("/ImagemEnunciado/"+pergunta.getImagemEnunciado())));
        }
        
    }
    
    public void next(){
        if(it.hasNext()){
            pergunta = it.next();
            carregarDados();
        }
    }
    
    public void prev(){
        if(it.hasPrevious()){
            pergunta = it.previous();
            carregarDados();
        }
    }
    
    public void exibirResposta() throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Resposta.fxml"));
        Parent root = loader.load();
        
        RespostaController rc = loader.getController();
        rc.iniciarTela(pergunta);
        
        Scene cena = new Scene(root);
        Stage window = new Stage();
        
        window.setScene(cena);
        window.showAndWait();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
