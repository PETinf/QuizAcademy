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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdicionarQuestaoController implements Initializable {

    @FXML private Button btnAdicionar;
    @FXML private TextField txtAssunto;
    @FXML private ImageView imageResposta;
    @FXML private Button btnCancelar;
    @FXML private TextArea txtEnunciado;
    @FXML private TextField txtDescricao;
    @FXML private ImageView imageEnunciado;
    @FXML private TextField txtDisciplina;
    @FXML private TextField txtResposta;
    
    private PerguntaDAO pdao;
    private Pergunta p;
    private File fileImagemEnunciado;
    private File fileImagemResposta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pdao = new PerguntaDAO();
        p = new Pergunta();
        fileImagemEnunciado = null;
        fileImagemResposta = null;
        
        Image image = new Image(AdicionarQuestaoController.class.getResourceAsStream("/Imagens/default.jpg"));
        imageEnunciado.setImage(image);
        imageResposta.setImage(image);
    }

    @FXML
    protected void adicionar(ActionEvent event) {

        p.setDisciplina(txtDisciplina.getText());
        p.setAssunto(txtAssunto.getText());
        p.setDescricao(txtDescricao.getText());
        p.setEnunciado(txtEnunciado.getText());
        p.setResposta(txtResposta.getText());

        try {
            pdao.insert(p);
            if(fileImagemEnunciado != null){
                Path pathArquivo = Paths.get(fileImagemEnunciado.getAbsolutePath());
                Path pathDestino = Paths.get(caminhoPadrao()+"//"+(pathArquivo.toFile()).getName());
                System.out.println(pathArquivo);
                System.out.println(pathDestino);
                Files.copy(pathArquivo, pathDestino, StandardCopyOption.REPLACE_EXISTING);
            }
            
            if(fileImagemResposta != null){
                Path pathArquivo = Paths.get(fileImagemResposta.getAbsolutePath());
                Path pathDestino = Paths.get(caminhoPadrao()+"//"+(pathArquivo.toFile()).getName());
                Files.copy(pathArquivo, pathDestino, StandardCopyOption.REPLACE_EXISTING);
            }
            
            cancelar();
        } catch (SQLException ex) {
            TelaPrincipalController.showErrorAsAlert(new SQLException("Erro ao inserir a questão no banco de dados!"));
        } catch (IOException ex) {
            TelaPrincipalController.showErrorAsAlert(new IOException("Erro ao copiar a imagem escolhida!"));
        } 
    }

    @FXML
    protected void cancelar() {
        Stage janelaAtual = (Stage) btnCancelar.getScene().getWindow();
        janelaAtual.close();
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
            p.setImagemEnunciado(arquivo.getName());
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
            p.setImagemResposta(arquivo.getName());
            fileImagemResposta = arquivo;
        }
    }
    
    public String caminhoPadrao(){
        String path = System.getProperty("user.dir");
        if(path.contains("dist")){
            path += "/../src/Imagens/";
        }else{
            path += "/Imagens/";
        }
        return path;
    }
}
