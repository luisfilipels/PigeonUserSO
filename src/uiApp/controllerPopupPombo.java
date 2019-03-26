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


        Controller.tc = Integer.parseInt(tcField.getText());
        Controller.td = Integer.parseInt(tdField.getText());
        Controller.tv = Integer.parseInt(tvField.getText());
        Controller.buffer.setCarga(Integer.parseInt(numCartas.getText()));
        Controller.animacaoPombo = new PomboAnimacao();
        Group pombo = new Group();
        pombo.getChildren().add(Controller.animacaoPombo.getPomboVoando());
        pombo.getChildren().add(Controller.animacaoPombo.getPomboParado());
        mainController.grupoPombo.getChildren().add(pombo);
        //mainController.idJanela.getChildren().add(Controller.animacaoPombo.getPomboVoando());
        //mainController.idJanela.getChildren().add(Controller.animacaoPombo.getPomboParado());

        System.out.println(Controller.buffer.getCarga());

        Controller.pombo = new Pombo(Controller.getBuffer(), Controller.getTc(), Controller.getTv(), Controller.getTd(), Controller.animacaoPombo);
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
