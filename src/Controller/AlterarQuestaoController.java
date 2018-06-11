/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Model.PerguntaDAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private TextField txtResposta;
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
    private File fileImagemEnunciado;
    private File fileImagemResposta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void iniciarTela(Pergunta p) {
        try {
            pdao = new PerguntaDAO();
            fileImagemEnunciado = null;
            fileImagemResposta = null;
            pergunta = p;
            carregarDados();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void cancelar() {
        Stage janela = (Stage) btnCancelar.getScene().getWindow();
        janela.close();
    }
    
    @FXML
    public void escolherImagemEnunciado() {
        
        FileChooser fc = new FileChooser();
        String caminho = caminhoPadrao();
        fc.setInitialDirectory(new File(caminho));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.png", "*.jpeg"));
        File arquivo = fc.showOpenDialog(new Stage());
        if (arquivo != null) {
            imageEnunciado.setImage(new Image("file:///" + arquivo.getAbsolutePath()));
            pergunta.setImagemEnunciado(arquivo.getName());
            fileImagemEnunciado = arquivo;
        }
    }
    @FXML
    public void escolherImagemResposta() {
        FileChooser fc = new FileChooser();
        String caminho = caminhoPadrao();
        fc.setInitialDirectory(new File(caminho));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.png", "*.jpeg"));
        File arquivo = fc.showOpenDialog(new Stage());
        if (arquivo != null) {
            imageResposta.setImage(new Image("file:///" + arquivo.getAbsolutePath()));
            pergunta.setImagemResposta(arquivo.getName());
            fileImagemResposta = arquivo;
        }
    }
    
    public void alterar() {
        try {
            atualizarPergunta();
            pdao.update(pergunta);
            
            Stage s = (Stage) btnAlterar.getScene().getWindow();
            s.close();
        } catch (SQLException ex) {
            TelaPrincipalController.showErrorAsAlert(ex);
        }
    }
    
    public void carregarDados() {
        txtDisciplina.setText(pergunta.getDisciplina());
        txtAssunto.setText(pergunta.getAssunto());
        txtDescricao.setText(pergunta.getDescricao());
        txtEnunciado.setText(pergunta.getEnunciado());
        txtResposta.setText(pergunta.getResposta());
        
        if (pergunta.getImagemEnunciado() != null) {
            imageEnunciado.setImage(new Image("file:///" + caminhoPadrao() + pergunta.getImagemEnunciado()));
        }
        if (pergunta.getImagemResposta() != null) {
            imageResposta.setImage(new Image("file:///" + caminhoPadrao() + pergunta.getImagemResposta()));
        }
        
    }
    
    public void atualizarPergunta() {
        try {
            pergunta.setDisciplina(txtDisciplina.getText());
            pergunta.setAssunto(txtAssunto.getText());
            pergunta.setDescricao(txtDescricao.getText());
            pergunta.setEnunciado(txtEnunciado.getText());
            pergunta.setResposta(txtResposta.getText());
            
            if (fileImagemEnunciado != null) {
                Path pathArquivo = Paths.get(fileImagemEnunciado.getAbsolutePath());
                Path pathDestino = Paths.get(caminhoPadrao() + "//" + (pathArquivo.toFile()).getName());
                Files.copy(pathArquivo, pathDestino, StandardCopyOption.REPLACE_EXISTING);
            }
            
            if (fileImagemResposta != null) {
                Path pathArquivo = Paths.get(fileImagemResposta.getAbsolutePath());
                Path pathDestino = Paths.get(caminhoPadrao() + "//" + (pathArquivo.toFile()).getName());
                Files.copy(pathArquivo, pathDestino, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            TelaPrincipalController.showErrorAsAlert(new IOException("Erro ao inserir a imagem!"));
        }
    }
    
    public static String caminhoPadrao() {
        String path = System.getProperty("user.dir");
        if (path.contains("dist")) {
            path += "/../src/Imagens/";
        } else {
            path += "/Imagens/";
        }
        return path;
    }
}
