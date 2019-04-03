package uiApp;

import animacao.PomboAnimacao;
import correio.Pombo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerPopupPombo {

    @FXML
    private TextField tvField, tcField, tdField, numCartas;

    @FXML
    private Label AvisoNumCartas = new Label("AAAA");

    public static Controller mainController;

    @FXML
    public void handlePomboButton(ActionEvent event) {

        if (Integer.parseInt(numCartas.getText()) > mainController.buffer.tamanho) {
            AvisoNumCartas.setText("Número de cartas deve ser menor que o máximo de cartas!");
            return;
        } else if (Integer.parseInt(tcField.getText()) < Integer.parseInt(numCartas.getText())) {
            AvisoNumCartas.setText("Tempo de carga deve ser maior ou igual à carga!");
            return;
        }


        mainController.tc = Integer.parseInt(tcField.getText());
        mainController.td = Integer.parseInt(tdField.getText());
        mainController.tv = Integer.parseInt(tvField.getText());
        mainController.buffer.setCarga(Integer.parseInt(numCartas.getText()));
        mainController.animacaoPombo = new PomboAnimacao();
        Group pombo = new Group();
        Controller.pomboVivo = true;
        pombo.getChildren().add(mainController.animacaoPombo.getPomboMapa());
        pombo.getChildren().add(mainController.animacaoPombo.getPomboVoando());
        pombo.getChildren().add(mainController.animacaoPombo.getPomboParado());
        mainController.grupoPombo.getChildren().add(pombo);
        ImageView mataPombo01 = new ImageView(mainController.KILLPB01);
        mainController.btPombo.setGraphic(mataPombo01);

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
