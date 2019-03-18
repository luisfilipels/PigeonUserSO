package uiApp;

import correio.Buffer;
import correio.Pombo;
import correio.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    private Buffer buffer = new Buffer(10, 3);
    private boolean pomboVivo = false;
    private int idUsuario = 0;
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private Pombo pombo;
    private int currentID;


    @FXML
    void criarUsuario(ActionEvent event) {
        usuarios.add(new Usuario(buffer, usuarios.size()+1));
        System.out.println("Usuario criado.");

    }

    @FXML
    void matarUsuario(ActionEvent event) {
        usuarios.get(currentID).matar();
        usuarios.remove(currentID);
        System.out.println("Usuario morto ");

    }

    @FXML
    void handleID(ActionEvent event) {
        currentID = Integer.parseInt(textField.getText());
        System.out.println("Input recebido");
        System.out.println("ID armazenada: " + currentID);
    }

    private void criarPombo() {
        pombo = new Pombo(buffer);
    }

    private void matarPombo() {
        pombo = null;
    }

    @FXML
    void acaoPombo(ActionEvent event) {
        if(!pomboVivo) {
            pomboVivo = true;
            btPombo.setText("Matar Pombo");

            criarPombo();
            //new Thread(new Pombo(buffer)).start();

            System.out.println("Pombo criado...");
        }
        else
        {
            pomboVivo = false;
            btPombo.setText("Criar Pombo");
            System.out.println("Pombo morto...");
        }

    }
}
