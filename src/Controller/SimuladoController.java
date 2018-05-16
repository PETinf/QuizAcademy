/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pergunta;
import Model.PerguntaDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Vinicius
 */
public class SimuladoController implements Initializable{
    private Button btn_cancelar;

    @FXML
    private TextField txtDescricaoSimulado;

    @FXML
    private TextField txtDisciplina;

    @FXML
    private TextField txtAssunto;

    @FXML
    private TextField txtNroPerguntas;

    @FXML
    private Button btnGerar;
    @FXML
    private Button btnCancelar;
    
    private PerguntaDAO dao;

    @FXML
    protected void Cancelar(ActionEvent event) throws Exception {
        Stage s = (Stage) btnGerar.getScene().getWindow();
        s.close();
    }
    
    public void GerarSimulado() throws Exception{
        int nroPerguntas = Integer.parseInt(txtNroPerguntas.getText());
        String disciplina = txtDisciplina.getText();
        String assunto = txtAssunto.getText();
        
        List<Pergunta> perguntas = null;
        
        if(!disciplina.equals("")){
            if(!assunto.equals("")){
                System.out.println(disciplina + "  "+ assunto);
                perguntas = dao.pesquisarDisciplinaAssunto(disciplina, assunto);
            }else{
                System.out.println(disciplina + "  "+ assunto);
                perguntas = dao.pesquisarDisciplina(disciplina);
            }
        }
        
        perguntas = escolherPerguntasAleatoriamente(perguntas, nroPerguntas);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/Simulado.fxml"));
        Parent root = loader.load();
        
        SimuladoQuestaoController sqc = loader.getController();
        sqc.setPerguntas(perguntas);
        sqc.carregarCampos(0);
        
        Stage telaAddQuestao = new Stage();
        Scene cena = new Scene(root);
        
        telaAddQuestao.setScene(cena);
        
        telaAddQuestao.showAndWait();
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new PerguntaDAO();
    }
    
    public List<Pergunta> escolherPerguntasAleatoriamente(List<Pergunta> perguntas, int nroPerguntas){
        Random random = new Random();
        List<Pergunta> listaPerguntas = new ArrayList<>();
        Pergunta p;
        int aux;
        while(nroPerguntas>0 && !perguntas.isEmpty()){
            aux = random.nextInt(perguntas.size());
            p = perguntas.get(aux);
            perguntas.remove(aux);
            listaPerguntas.add(p);
            nroPerguntas--;
        }
        
        return listaPerguntas;
    }
}
