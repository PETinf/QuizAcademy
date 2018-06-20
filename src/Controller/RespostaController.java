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

    @FXML
    private ImageView imgResposta;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblResposta;

    @FXML
    public void voltar() {
        Stage window = (Stage) btnVoltar.getScene().getWindow();
        window.close();
    }

    public void iniciarTela(Pergunta p) throws NullPointerException {

        if (p.getImagemResposta() != null) {
            Image image = new Image("file:///" + caminhoPadrao() + p.getImagemResposta());
            imgResposta.setImage(image);
            if (image.getWidth()<= 600 && image.getHeight() <= 400) {
                imgResposta.setFitWidth(image.getWidth());
                imgResposta.setFitHeight(image.getHeight());
            }else{
                imgResposta.setFitWidth(600);
                imgResposta.setFitHeight(400);
            }

        }
        lblResposta.setText(p.getResposta());

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //...
    }

}
