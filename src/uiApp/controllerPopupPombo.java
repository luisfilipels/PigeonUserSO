package uiApp;

import animacao.PomboAnimacao;
import correio.Pombo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPopupPombo {

    @FXML
    private TextField tvField, tcField, tdField, numCartas;

    public static Controller mainController;

    @FXML
    public void handlePomboButton(ActionEvent event) {


        mainController.tc = Integer.parseInt(tcField.getText());
        mainController.td = Integer.parseInt(tdField.getText());
        mainController.tv = Integer.parseInt(tvField.getText());
        mainController.buffer.setCarga(Integer.parseInt(numCartas.getText()));
        mainController.animacaoPombo = new PomboAnimacao();
        Group pombo = new Group();
        pombo.getChildren().add(mainController.animacaoPombo.getPomboVoando());
        pombo.getChildren().add(mainController.animacaoPombo.getPomboParado());
        mainController.grupoPombo.getChildren().add(pombo);

        System.out.println(mainController.buffer.getCarga());

        Controller.pombo = new Pombo(mainController.getBuffer(), mainController.getTc(), mainController.getTv(), mainController.getTd(), mainController.animacaoPombo);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
    @FXML
    public void cancelar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
