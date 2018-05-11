/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Model.PerguntaDAO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class AlterarQuestaoController implements Initializable {

    @FXML
    private TextField txtDisciplina;
    @FXML
    private TextField txtAssunto;
    @FXML
    private TextArea txtEnunciado;
    @FXML
    private TextField txtDescricao;
    @FXML
    private Button btnEscolherEnunciado;
    @FXML
    private Button btnEscolherResposta;
    @FXML
    private ImageView imageEnunciado;
    @FXML
    private ImageView imageResposta;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAlterar;
    private Pergunta pergunta;
    private PerguntaDAO pdao;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pdao = new PerguntaDAO();
    }    

    public void cancelar() {
        Stage janela = (Stage) btnCancelar.getScene().getWindow();
        janela.close();
    }

    public void escolherEnunciado() {
        
        FileChooser fc = new FileChooser();
        String caminho = Pergunta.PATHENUNCIADO;
        fc.setInitialDirectory(new File(caminho));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens","*.jpg","*.png","*.jpeg"));
        File arquivo = fc.showOpenDialog(new Stage());
        if(arquivo != null){
            imageEnunciado.setImage(new Image("file:///"+arquivo.getAbsolutePath()));
            pergunta.setImagemEnunciado(arquivo.getName());
        }
    }
    

    public void escolherResposta() {
        
        FileChooser fc = new FileChooser();
        String caminho = Pergunta.PATHRESPOSTA;
        fc.setInitialDirectory(new File(caminho));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens","*.jpg","*.png","*.jpeg"));
        File arquivo = fc.showOpenDialog(new Stage());
        if(arquivo != null){
            imageResposta.setImage(new Image("file:///"+arquivo.getAbsolutePath()));
            pergunta.setImagemResposta(arquivo.getName());
        }
        
    }

    public void alterar() {
        atualizarPergunta();
        pdao.update(pergunta);
    }

    
    public void setPergunta(Pergunta p){
        pergunta = p;
    }
    
    public void carregarDados(){
        txtDisciplina.setText(pergunta.getDisciplina());
        txtAssunto.setText(pergunta.getAssunto());
        txtDescricao.setText(pergunta.getDescricao());
        txtEnunciado.setText(pergunta.getEnunciado());
        
        imageEnunciado.setImage(new Image("file:///"+Pergunta.PATHENUNCIADO+pergunta.getImagemEnunciado()));
        imageResposta.setImage(new Image("file:///"+Pergunta.PATHRESPOSTA+pergunta.getImagemResposta()));
    }
    
    public void atualizarPergunta(){
        pergunta.setDisciplina(txtDisciplina.getText());
        pergunta.setAssunto(txtAssunto.getText());
        pergunta.setDescricao(txtDescricao.getText());
        pergunta.setEnunciado(txtEnunciado.getText());
    }
}