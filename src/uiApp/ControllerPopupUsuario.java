package uiApp;

import animacao.UsuarioAnimacao;
import correio.Escritor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPopupUsuario {

    public static Controller mainController;

    @FXML
    private TextField teField, idField;

    @FXML
    public void handleUsuarioButton(ActionEvent event) {
        UsuarioAnimacao animacao = new UsuarioAnimacao();
        mainController.animacaosEscritor.add(animacao);
        mainController.listaEscritores.add(new Escritor(mainController.buffer, idField.getText(), 1000*Integer.parseInt(teField.getText()), mainController.animacaosEscritor.get(mainController.animacaosEscritor.size()-1), mainController));
        Group animacaoUsuario = new Group();
        animacaoUsuario.setAccessibleText(idField.getText());
        animacaoUsuario.getChildren().add(animacao.getUsuarioEmMovimento());
        animacaoUsuario.getChildren().add(animacao.getUsuarioEscrevendo());
        mainController.grupoUsuario.getChildren().add(animacaoUsuario);
        mainController.listaUsuarios.add(new Usuario(idField.getText(), "Ocioso"));

        System.out.println("Usuario Criado.");

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
