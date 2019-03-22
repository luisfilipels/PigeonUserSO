package uiApp;

import animacao.PomboAnimacao;
import animacao.UsuarioAnimacao;
import correio.Buffer;
import correio.Pombo;
import correio.Escritor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private AnchorPane idJanela;

    @FXML
    private ImageView idMapa;

    @FXML
    private Button btPombo;

    @FXML
    private AnchorPane idBuffer;

    @FXML
    private TextField textField;

    private static Buffer buffer = new Buffer(10, 3);
    private boolean pomboVivo = false;
    private int idUsuario = 0;
    private ArrayList<Escritor> listaEscritores = new ArrayList<Escritor>();
    private ArrayList<UsuarioAnimacao> animacaosEscritor = new ArrayList<UsuarioAnimacao>();
    private static Pombo pombo;
    private static PomboAnimacao animacaoPombo;
    private static int currentID, tc, tv, td;

    public Buffer getBuffer() {
        return buffer;
    }

    @FXML
    private TextField tvField, tcField, tdField, numCartas;

    @FXML
    public Button botaoCriarPombo;

    @FXML
    public void handlePomboButton(ActionEvent event) {
        //PomboAnimacao animacaoPombo = new PomboAnimacao();
        animacaoPombo = new PomboAnimacao();
        tc = 1000*Integer.parseInt(tcField.getText());
        td = 1000*Integer.parseInt(tdField.getText());
        tv = 1000*Integer.parseInt(tvField.getText());
        buffer.setCarga(Integer.parseInt(numCartas.getText()));

        System.out.println(buffer.getCarga());

        idJanela.getChildren().add(animacaoPombo.getPombo());
        pombo = new Pombo(this.buffer, tc, tv, td, animacaoPombo);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    public void popUpPombo () throws IOException {
        Stage popup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("popupPombo.fxml"));
        popup.setTitle("Criar pombo");
        popup.setResizable(false);
        popup.setScene(new Scene(root, 500, 375));
        popup.show();
    }

    private void criarPombo() throws Exception{

        popUpPombo();

    }

    @FXML
    void criarEscritor(ActionEvent event) {
        UsuarioAnimacao animacaoEscritor = new UsuarioAnimacao();
        listaEscritores.add(new Escritor(buffer, listaEscritores.size()+1, 3000, animacaoEscritor));
        animacaosEscritor.add(animacaoEscritor);
        idJanela.getChildren().add(animacaoEscritor.getUsuarioEscrevendo());
        idJanela.getChildren().add(animacaoEscritor.getUsuarioEmMovimento());
        System.out.println("Escritor criado.");
        System.out.println(tc + " " + td + " " + tv);

    }

    @FXML
    void matarEscritor(ActionEvent event) {
        listaEscritores.get(currentID).matar();
        listaEscritores.remove(currentID);
        idJanela.getChildren().remove(animacaosEscritor.get(currentID).getUsuarioEscrevendo());
        animacaosEscritor.remove(currentID);
        System.out.println("Escritor morto ");

    }

    @FXML
    void handleID() {
        currentID = Integer.parseInt(textField.getText());
        System.out.println("Input recebido");
        System.out.println("ID armazenada: " + currentID);
    }



    private void matarPombo() {
        this.pombo.matar();
        this.pombo = null;
    }

    @FXML
    void acaoPombo(ActionEvent event) throws Exception{
        if(!pomboVivo) {
            pomboVivo = true;
            btPombo.setText("Matar Pombo");

            criarPombo();

            System.out.println("Pombo criado...");
        }
        else
        {
            pomboVivo = false;
            btPombo.setText("Criar Pombo");
            matarPombo();
            System.out.println("Pombo morto...");
        }

    }
}
