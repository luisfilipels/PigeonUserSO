package uiApp;

import animacao.PomboAnimacao;
import animacao.UsuarioAnimacao;
import correio.Buffer;
import correio.Pombo;
import correio.Escritor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    public AnchorPane idJanela;

    @FXML
    private ImageView idMapa;

    @FXML
    public Button btPombo;

    @FXML
    private AnchorPane idBuffer;

    @FXML
    private Button btCriaUsuario;

    @FXML
    private Button btMataUsuario;

    @FXML
    public GridPane TabelaUsuarios;

    @FXML
    public TableView<Usuario> tableUsers = new TableView<Usuario>();

    @FXML
    public TableColumn<Usuario, String> tableID;

    @FXML
    public TableColumn<Usuario, String> tableStatus;

    @FXML
    public Label testLabel;

    final static Image ADDUSNORM01 = new Image(PomboAnimacao.class.getResource("/AdicionaUsuario1x.png").toString());
    final static Image ADDUSHOVER01 = new Image(PomboAnimacao.class.getResource("/AdicionaUsuarioHver1x.png").toString());
    final static Image KILLUS01 = new Image(PomboAnimacao.class.getResource("/ExcluiUsuario1xNormal.png").toString());
    final static Image KILLUSHOVER01 = new Image(PomboAnimacao.class.getResource("/ExcluiUsuario1x.png").toString());

    final static Image ADDPB01 = new Image(PomboAnimacao.class.getResource("/PomboNormal1x.png").toString());
    final static Image KILLPB01 = new Image(PomboAnimacao.class.getResource("/DeletaPomboNormal1x.png").toString());
    final static Image ADDPBHOVER01 = new Image(PomboAnimacao.class.getResource("/PomboHover1x.png").toString());
    final static Image KILLPBHOVER01 = new Image(PomboAnimacao.class.getResource("/DeletaPomboHover1x.png").toString());

    public ControllerPopupUsuario popupUsuario;
    public ControllerPopupPombo popupPombo;
    public ControllerPopupM popupM;

    public Group grupoUsuario = new Group();
    public Group grupoPombo = new Group();

    public ObservableList<Usuario> listaUsuarios;

    @FXML
    private void handleButtonCriaUsuarioMouseIn () {
        ImageView addUsuario01 = new ImageView(ADDUSHOVER01);
        btCriaUsuario.setGraphic(addUsuario01);
    }

    @FXML
    private void handleButtonCriaUsuarioMouseOut () {
        ImageView addUsuario01 = new ImageView(ADDUSNORM01);
        btCriaUsuario.setGraphic(addUsuario01);
    }

    @FXML
    private void handleButtonMataUsuarioMouseIn () {
        ImageView addUsuario01 = new ImageView(KILLUSHOVER01);
        btMataUsuario.setGraphic(addUsuario01);
    }

    @FXML
    private void handleButtonMataUsuarioMouseOut () {
        ImageView addUsuario01 = new ImageView(KILLUS01);
        btMataUsuario.setGraphic(addUsuario01);
    }

    @FXML
    private void handleButtonPomboMouseIn () {
        if (pomboVivo) {
            ImageView mataPombo01 = new ImageView(KILLPBHOVER01);
            btPombo.setGraphic(mataPombo01);
        } else {
            ImageView addPombo01 = new ImageView(ADDPBHOVER01);
            btPombo.setGraphic(addPombo01);
        }

    }

    @FXML
    private void handleButtonPomboMouseOut () {
        if (pomboVivo) {
            ImageView mataPombo01 = new ImageView(KILLPB01);
            btPombo.setGraphic(mataPombo01);
        } else {
            ImageView addPombo01 = new ImageView(ADDPB01);
            btPombo.setGraphic(addPombo01);
        }
    }

	@FXML
	private void initialize() {
		ImageView addUsuario01 = new ImageView(ADDUSNORM01);
		btCriaUsuario.setGraphic(addUsuario01);
		btCriaUsuario.setBackground(null);
		
		ImageView killUsuario01 = new ImageView(KILLUS01);
		btMataUsuario.setGraphic(killUsuario01);
		btMataUsuario.setBackground(null);
		testLabel = new Label();
		testLabel.setText("0");
		testLabel.setTranslateX(315);
		testLabel.setTranslateY(553);
		testLabel.setScaleX(3);
		testLabel.setScaleY(3);
		idJanela.getChildren().add(testLabel);
		ImageView addPombo01 = new ImageView(ADDPB01);
		btPombo.setGraphic(addPombo01);
		btPombo.setBackground(null);
		idJanela.getChildren().add(grupoUsuario);
		idJanela.getChildren().add(grupoPombo);

		ControllerPopupUsuario.mainController = this;
		ControllerPopupPombo.mainController = this;
		ControllerPopupM.mainController = this;
        try {
            popupM();

        } catch (Exception e) {
            e.printStackTrace();
        }
        TabelaUsuarios.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        listaUsuarios = FXCollections.observableArrayList();
        tableID.setCellValueFactory(new PropertyValueFactory<Usuario, String>("id"));
        tableUsers.setItems(listaUsuarios);



	}
	public void atualizaTabela () {
	    Platform.runLater(new Runnable() {
            @Override
            public void run() {
                testLabel.setText(Integer.toString(buffer.mensagens));
            }
        });
    }


	public void popupM () throws Exception{
        Stage popup = new Stage();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("popupCorreio.fxml")
        );
        ControllerPopupM secController = loader.getController();
        Parent root = loader.load();
        popup.setTitle("Criar Buffer");
        popup.setResizable(false);
        popup.setScene(new Scene(root, 600, 275));
        popup.show();
        popup.setAlwaysOnTop(true);
        popup.toFront();
    }
    public Buffer buffer = new Buffer(10, 4, this);

    public static boolean pomboVivo = false;
    public ArrayList<Escritor> listaEscritores = new ArrayList<Escritor>();
    public ArrayList<UsuarioAnimacao> animacaosEscritor = new ArrayList<UsuarioAnimacao>();
    public static Pombo pombo;
    public static PomboAnimacao animacaoPombo;
    public int currentID, tc=5, tv=5, td=2;

    public int getTc() {
        return tc;
    }
    public int getTv() {
        return tv;
    }
    public int getTd() {
        return td;
    }
    public Buffer getBuffer() {
        return buffer;
    }

    @FXML
    public Button botaoCriarPombo;

    public void popUpPombo () throws IOException {
        Stage popup = new Stage();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("popupPombo.fxml")
        );
        ControllerPopupPombo secController = loader.getController();

        Parent root = loader.load();
        popup.setTitle("Criar pombo");
        popup.setResizable(false);
        popup.setScene(new Scene(root, 500, 375));
        popup.show();
    }

    private void criarPombo() throws Exception{
        popUpPombo();
    }

    @FXML
    void criarEscritor(ActionEvent event) throws IOException{
        Stage popup = new Stage();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("popupUsuario.fxml")
        );
        ControllerPopupUsuario secController = loader.getController();
        secController.mainController = this;
        Parent root = loader.load();
        popup.setTitle("Criar Usuario");
        popup.setResizable(false);
        popup.setScene(new Scene(root, 500, 375));
        popup.show();

    }

    @FXML
    TextField fieldDeletaUsuario;

    @FXML
    void matarEscritor(ActionEvent event) {
        String idLido = fieldDeletaUsuario.getText();
        System.out.println(grupoUsuario.getChildren().size());
        for (int i = 0; i < listaEscritores.size(); i++) {
            if (listaEscritores.get(i).getsId().equals(idLido)) {
                listaEscritores.get(i).matar();
                listaEscritores.remove(i);
            }
        }
        for (int i = 0; i < grupoUsuario.getChildren().size(); i++) {
            if (grupoUsuario.getChildren().get(i).getAccessibleText().equals(idLido)) {
                grupoUsuario.getChildren().remove(i);
                System.out.println("Usuario removido");
            }
        }
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId().equals(fieldDeletaUsuario.getText())) {
                listaUsuarios.remove(i);
            }
        }
    }



    private void matarPombo() {
        if (!buffer.pomboCarregando) {
            pombo.matar();
            pombo = null;
            //pombo.matar();
            //pombo = null;
            grupoPombo.getChildren().remove(0);
            System.out.println("Pombo morto...");
            pomboVivo = false;
        } else {
            System.out.println("Pombo não pode ser morto agora!");
        }
    }

    @FXML
    void acaoPombo(ActionEvent event) throws Exception{
        if(!pomboVivo) {
            animacaoPombo = new PomboAnimacao();

            btPombo.setText("Matar Pombo");
            criarPombo();

            //pomboVivo = true;

            //System.out.println("Pombo criado...");
        }
        else
        {
            //pomboVivo = false;
            btPombo.setText("Criar Pombo");
            matarPombo();
        }

    }
}
