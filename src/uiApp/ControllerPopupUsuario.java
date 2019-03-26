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
        mainController.TabelaNumCartas.setText("FUNCIONA :D");
        mainController.listaEscritores.add(new Escritor(Controller.buffer, idField.getText(), 1000*Integer.parseInt(teField.getText()), mainController.animacaosEscritor.get(mainController.animacaosEscritor.size()-1), mainController));
        Group animacaoUsuario = new Group();
        animacaoUsuario.setAccessibleText(idField.getText());
        animacaoUsuario.getChildren().add(animacao.getUsuarioEmMovimento());
        animacaoUsuario.getChildren().add(animacao.getUsuarioEscrevendo());
        mainController.grupoUsuario.getChildren().add(animacaoUsuario);
        mainController.listaUsuarios.add(new Usuario(idField.getText(), "Ocioso"));
        //mainController.listaUsuarios.
        //mainController.tableUsers.
        //Group linha = new Group();
        //linha.setAccessibleText(idField.getText());
        //linha.getChildren().add(new Label(idField.getText()));
        //linha.getChildren().add(new Label("STATUS"));
        //mainController.tableUsers.addRow(mainController.tableUsers.getRowCount()+1, new Label(idField.getText()), new Label("STATUS"));
        //mainController.tableUsers.addRow((Node) linha);
        //mainController.tableUsers.add(new Label(idField.getText()), 0, 0);
        //mainController.tableUsers.add(new Label("STATUS"), 0, 1);

        System.out.println("Usuario Criado.");

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
