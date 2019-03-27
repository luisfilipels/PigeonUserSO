package uiApp;

import animacao.UsuarioAnimacao;
import correio.Buffer;
import correio.Escritor;
import correio.Pombo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPopupM {

    public static Controller mainController;

    @FXML
    private TextField mField;

    @FXML
    private Label labelValido;

    @FXML
    private Button botaoSair;

    @FXML
    public void sair(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void handleMButton(ActionEvent event) {
        //mainController.buffer = new Buffer()
        int lido = Integer.parseInt(mField.getText());
        if (lido <= 0) {
            labelValido.setText("Insira um valor maior ou igual a 0!");
            return;
        }
        mainController.buffer = new Buffer(lido, 1, mainController);
        /*UsuarioAnimacao animacao = new UsuarioAnimacao(idField.getText());
        mainController.animacaosEscritor.add(animacao);
        mainController.listaEscritores.add(new Escritor(Controller.buffer, idField.getText(), Integer.parseInt(teField.getText()), mainController.animacaosEscritor.get(mainController.animacaosEscritor.size()-1)));
        mainController.idJanela.getChildren().add(animacao.getUsuarioEscrevendo());
        mainController.idJanela.getChildren().add(animacao.getUsuarioEmMovimento());
        //Controller.listaEscritores.add(new Escritor(Controller.buffer, idField.getText(), Integer.parseInt(teField.getText()), Controller.animacaosEscritor.get(Controller.animacaosEscritor.size()-1)));
        //Controller.idJanela.getChildren().add(animacao.getUsuarioEscrevendo());
        //Controller.idJanela.getChildren().add(animacao.getUsuarioEmMovimento());
        //Controller.listaEscritores.add(new Escritor(Controller.buffer, idField.getText(), Integer.parseInt(teField.getText()), Controller.animacaosEscritor.get(Controller.animacaosEscritor.size()-1)));

        System.out.println("Usuario Criado.");
        */
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}