package uiApp;

import correio.Pombo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPopupPombo {

    @FXML
    private TextField tvField, tcField, tdField, numCartas;

    public Controller mainController;

    @FXML
    public void handlePomboButton(ActionEvent event) {


        Controller.tc = Integer.parseInt(tcField.getText());
        Controller.td = Integer.parseInt(tdField.getText());
        Controller.tv = Integer.parseInt(tvField.getText());
        Controller.buffer.setCarga(Integer.parseInt(numCartas.getText()));

        System.out.println(Controller.buffer.getCarga());

        Controller.pombo = new Pombo(Controller.getBuffer(), Controller.getTc(), Controller.getTv(), Controller.getTd(), Controller.animacaoPombo);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
