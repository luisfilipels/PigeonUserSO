package uiApp;

import animacao.PomboAnimacao;
import animacao.UsuarioAnimacao;
import correio.Buffer;
import correio.Pombo;
import correio.Escritor;
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
    private Button btPombo;

    @FXML
    private AnchorPane idBuffer;

    @FXML
    private Button btCriaUsuario;

    @FXML
    private Button btMataUsuario;

    @FXML
    public Label TabelaNumCartas;

    @FXML
    public GridPane TabelaUsuarios;

    @FXML
    public TableView<Usuario> tableUsers = new TableView<Usuario>();

    @FXML
    public TableColumn<Usuario, String> tableID;

    @FXML
    public TableColumn<Usuario, String> tableStatus;


    @FXML
    private TextField textField;
    
    final static Image ADDUS01 = new Image(PomboAnimacao.class.getResource("/AdicionaUsuario1x.png").toString());
    final static Image KILLUS01 = new Image(PomboAnimacao.class.getResource("/ExcluiUsuario1x.png").toString());
    final static Image ADDPB01 = new Image(PomboAnimacao.class.getResource("/PomboNormal1x.png").toString());

    public ControllerPopupUsuario popupUsuario;
    public ControllerPopupPombo popupPombo;
    public ControllerPopupM popupM;

    public Group grupoUsuario = new Group();
    public Group grupoPombo = new Group();

    //public ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    public ObservableList<Usuario> listaUsuarios;

	@FXML
	private void initialize() {
		ImageView addUsuario01 = new ImageView(ADDUS01);
		btCriaUsuario.setGraphic(addUsuario01);
		btCriaUsuario.setBackground(null);
		
		ImageView killUsuario01 = new ImageView(KILLUS01);
		btMataUsuario.setGraphic(killUsuario01);
		btMataUsuario.setBackground(null);
		
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
        //tableID = new TableColumn("id");
        //tableStatus = new TableColumn("status");
        //tableUsers.getColumns().addAll(tableID, tableStatus);
        TabelaUsuarios.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        listaUsuarios = FXCollections.observableArrayList();
        tableID.setCellValueFactory(new PropertyValueFactory<Usuario, String>("id"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<Usuario, String>("status"));
        //tableUsers.setEditable(true);
        tableUsers.setItems(listaUsuarios);
        //tableID.setCellValueFactory(new PropertyValueFactory<Usuario, String>("id"));
		//tableStatus.setCellValueFactory(new PropertyValueFactory<Usuario, String>("status"));



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

    public static Buffer buffer = new Buffer(10, 4);
    public static boolean pomboVivo = false;
    public int idUsuario = 0;
    public ArrayList<Escritor> listaEscritores = new ArrayList<Escritor>();
    public ArrayList<UsuarioAnimacao> animacaosEscritor = new ArrayList<UsuarioAnimacao>();
    public static Pombo pombo;
    public static PomboAnimacao animacaoPombo;
    public static int currentID, tc=5, tv=5, td=2;

    public static int getTc() {
        return tc;
    }
    public static int getTv() {
        return tv;
    }
    public static int getTd() {
        return td;
    }
    public static Buffer getBuffer() {
        return buffer;
    }

    @FXML
    private TextField tvField, tcField, tdField, numCartas;

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
        //secController.setMainController(this);
        popup.setScene(new Scene(root, 500, 375));
        popup.show();
    }

    private void criarPombo() throws Exception{

        popUpPombo();

    }

    public void adicionarAJanela (UsuarioAnimacao animacao) {
        idJanela.getChildren().add(animacao.getUsuarioEmMovimento());
        idJanela.getChildren().add(animacao.getUsuarioEscrevendo());
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
        //for (int i = 0; i < tableUsers.getRowCount(); i++) {
        //    if (tableUsers.getChildren().get(i).)
        //}
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
    }

    @FXML
    void handleID() {
        currentID = Integer.parseInt(textField.getText());
        System.out.println("Input recebido");
        System.out.println("id armazenada: " + currentID);
    }



    private void matarPombo() {
        if (!buffer.pomboCarregando) {
            pombo.matar();
            pombo = null;
            grupoPombo.getChildren().remove(0);
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

            pomboVivo = true;

            System.out.println("Pombo criado...");
        }
        else
        {
            pomboVivo = false;
            btPombo.setText("Criar Pombo");
            matarPombo();
            System.out.println("Pombo morto...");
        }

    }
}
