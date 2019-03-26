package uiApp;

import animacao.UsuarioAnimacao;
import correio.Escritor;
import correio.Pombo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPopupUsuario {

    public static Controller mainController;

    @FXML
    private TextField teField, idField;

    @FXML
    public void handleUsuarioButton(ActionEvent event) {
        UsuarioAnimacao animacao = new UsuarioAnimacao(idField.getText());
        mainController.animacaosEscritor.add(animacao);
        mainController.listaEscritores.add(new Escritor(Controller.buffer, idField.getText(), Integer.parseInt(teField.getText()), mainController.animacaosEscritor.get(mainController.animacaosEscritor.size()-1)));
        Group animacaoUsuario = new Group();
        animacaoUsuario.setAccessibleText(idField.getText());
        animacaoUsuario.getChildren().add(animacao.getUsuarioEmMovimento());
        animacaoUsuario.getChildren().add(animacao.getUsuarioEscrevendo());
        mainController.grupoUsuario.getChildren().add(animacaoUsuario);
        //mainController.idJanela.getChildren().add(animacaoUsuario);
        //mainController.grupoUsuario.getChildren().add(animacao.getUsuarioEmMovimento());
        //mainController.grupoUsuario.getChildren().add(animacao.getUsuarioEscrevendo());
        //mainController.idJanela.getChildren().add(animacao.getUsuarioEscrevendo());
        //mainController.idJanela.getChildren().add(animacao.getUsuarioEmMovimento());
        //Controller.listaEscritores.add(new Escritor(Controller.buffer, idField.getText(), Integer.parseInt(teField.getText()), Controller.animacaosEscritor.get(Controller.animacaosEscritor.size()-1)));
        //Controller.idJanela.getChildren().add(animacao.getUsuarioEscrevendo());
        //Controller.idJanela.getChildren().add(animacao.getUsuarioEmMovimento());
        //Controller.listaEscritores.add(new Escritor(Controller.buffer, idField.getText(), Integer.parseInt(teField.getText()), Controller.animacaosEscritor.get(Controller.animacaosEscritor.size()-1)));

        System.out.println("Usuario Criado.");

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
