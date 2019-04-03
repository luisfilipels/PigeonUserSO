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

/*
    Controller da tela principal.
 */

public class Controller {

    /* INICIO ELEMENTOS DA JANELA */

    @FXML
    public AnchorPane idJanela; // Janela principal

    @FXML
    private ImageView idMapa; // Mapa

    @FXML
    public Button btPombo; // Botao do pombo, tanto para criar, quanto para matar

    @FXML
    private Button btCriaUsuario; // Botao de criacao de usuarios

    @FXML
    private Button btMataUsuario; // Botao de matar usuarios

    @FXML
    public GridPane TabelaUsuarios; // Tabela por completo, incluindo as IDs dos usuarios, contagem de mensagens e labels USUARIOS e CARTAS

    @FXML
    public TableView<Usuario> tableUsers = new TableView<Usuario>(); // Lista de IDs de usuarios visivel na janela

    @FXML
    public TableColumn<Usuario, String> tableID; // Como foi usado TableView, esta é a coluna de IDs do TableView

    @FXML
    public Label contaMensagens; // Label contendo a contagem de mensagens

    @FXML
    TextField fieldDeletaUsuario; // Entrada de dados para exclusao de um usuario, exibido na janela principal

    /*FIM ELEMENTOS DA JANELA */
    /*===============================================================================================================*/
    /*INICIO CARREGAMENTO DE IMAGENS*/

    final static Image ADDUSNORM01 = new Image(PomboAnimacao.class.getResource("/AdicionaUsuario1x.png").toString());       // Botao de criacao de usuarios, sem hover
    final static Image ADDUSHOVER01 = new Image(PomboAnimacao.class.getResource("/AdicionaUsuarioHver1x.png").toString());  // Botao de criacao de usuarios, hover
    final static Image KILLUS01 = new Image(PomboAnimacao.class.getResource("/ExcluiUsuario1xNormal.png").toString());      // Botao de exclusao de usuarios, sem hover
    final static Image KILLUSHOVER01 = new Image(PomboAnimacao.class.getResource("/ExcluiUsuario1x.png").toString());       // Botao de criacao de usuarios, hover
    final static Image ADDPB01 = new Image(PomboAnimacao.class.getResource("/PomboNormal1x.png").toString());               // Botao de criacao de pombo, sem hover
    final static Image KILLPB01 = new Image(PomboAnimacao.class.getResource("/DeletaPomboNormal1x.png").toString());        // Botao de exclusao de usuarios, sem hover
    final static Image ADDPBHOVER01 = new Image(PomboAnimacao.class.getResource("/PomboHover1x.png").toString());           // Botao de criacao de pombo, hover
    final static Image KILLPBHOVER01 = new Image(PomboAnimacao.class.getResource("/DeletaPomboHover1x.png").toString());    // Botao de exclusao de usuarios, sem hover

    /*FIM CARREGAMENTO DE IMAGENS */
    /*===============================================================================================================*/
    /*INICIO AGRUPAMENTO DE DADOS*/

    public Group grupoUsuario = new Group();                                        // Grupo contendo as imagens de cada um dos usuarios, a ser exibido em idJanela
    public Group grupoPombo = new Group();                                          // Grupo contendo as imagens do pombo, a ser exibido em idJanela
    public ObservableList<Usuario> listaUsuarios;                                   // Lista de usuarios a serem exibidos na tabela
    public Buffer buffer = new Buffer(10, 4, this);    // Referencia ao buffer (caixa de correios)
    public static boolean pomboVivo = false;                                        // A ser utilizado na escolha de botoes do pombo a serem exibidos na interface
    public ArrayList<Escritor> listaEscritores = new ArrayList<Escritor>();                // Arraylist com referencias as threads de escritores
    public ArrayList<UsuarioAnimacao> animacaosEscritor = new ArrayList<UsuarioAnimacao>();       // Arraylist com referencias as imagens (sprites) e animacoes dos escritores
    public static Pombo pombo;                                                      // Referencia a thread do pombo
    public static PomboAnimacao animacaoPombo;                                      // Referencia aos sprites e animacoes do pombo
    public int currentID, tc=5, tv=5, td=2;                                         // Inteiros para uso temporario na criacao de um novo pombo

    /*FIM AGRUPAMENTO DE DADOS*/
    /*===============================================================================================================*/
    /*INICIO FUNCOES DE IMAGENS DE BOTOES*/

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

    /*FIM FUNCOES DE IMAGENS DE BOTOES*/
    /*===============================================================================================================*/
    /*INICIO FUNCOES PRINCIPAIS*/

	@FXML
	private void initialize() {                                 //Funcao executada automaticamente uma vez, na inicializacao do programa
		ImageView addUsuario01 = new ImageView(ADDUSNORM01);    // Carregando imagens para botoes de criacao de usuarios...
		btCriaUsuario.setGraphic(addUsuario01);
		btCriaUsuario.setBackground(null);
		
		ImageView killUsuario01 = new ImageView(KILLUS01);      // ... e para matar usuarios
		btMataUsuario.setGraphic(killUsuario01);
		btMataUsuario.setBackground(null);

		contaMensagens = new Label();                           // Inicializando o contador de mensagens
		contaMensagens.setText("0");
		contaMensagens.setTranslateX(315);
		contaMensagens.setTranslateY(553);
		contaMensagens.setScaleX(3);
		contaMensagens.setScaleY(3);
		idJanela.getChildren().add(contaMensagens);

		ImageView addPombo01 = new ImageView(ADDPB01);          // Carregando imagens para botoes de criacao de usuarios
		btPombo.setGraphic(addPombo01);
		btPombo.setBackground(null);

		idJanela.getChildren().add(grupoUsuario);               // Para exibir as animacoes e sprites dos usuarios...
		idJanela.getChildren().add(grupoPombo);                 // ... e pombo

		ControllerPopupUsuario.mainController = this;           // Quando os popups de entrada de dados forem inicializados, eles terao uma referencia a este controller
		ControllerPopupPombo.mainController = this;
		ControllerPopupM.mainController = this;
        try {
            popupM();                                           // Lendo o numero maximo de cartas (M)
        } catch (Exception e) {
            e.printStackTrace();
        }

        TabelaUsuarios.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        listaUsuarios = FXCollections.observableArrayList();
        tableID.setCellValueFactory(new PropertyValueFactory<Usuario, String>("id"));
        tableUsers.setItems(listaUsuarios);                                                              // Configurando tableUsers para que exibida listaUsuarios



	}

	public void atualizaTabela () {                             // Atualizar o contador de mensagens com o valor atual de mensagens na caixa de correio
	    Platform.runLater(new Runnable() {
            @Override
            public void run() {
                contaMensagens.setText(Integer.toString(buffer.mensagens));
            }
        });
    }


	public void popupM () throws Exception{                     // Chamar o popup de M
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

    /* Getters diversos */
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
    /*=================*/

    public void popUpPombo () throws IOException { // Popup exibido quando o pombo nao esta vivo. Pega os dados com que o novo pombo sera criado, e o cria com esses dados
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

    @FXML
    void criarEscritor(ActionEvent event) throws IOException{ // Popup de leitura de dados de um novo escritor
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
    void matarEscritor(ActionEvent event) {  // Funcao que pega a string que esta presentemente escrita em fieldDeletaUsuario
        String idLido = fieldDeletaUsuario.getText();
        System.out.println(grupoUsuario.getChildren().size());
        for (int i = 0; i < listaEscritores.size(); i++) {          // Fazendo uma busca sequencial em listaEscritores (threads de usuarios)
            if (listaEscritores.get(i).getsId().equals(idLido)) {   // Se for encontrado uma thread cuja ID seja a que foi lida...
                listaEscritores.get(i).matar();                     // ... mate a thread ...
                listaEscritores.remove(i);                          // ... e a retire da lista
            }
        }
        for (int i = 0; i < grupoUsuario.getChildren().size(); i++) {                   // Fazendo uma busca sequencial em grupoUsuario (sprites e animacoes dos usuarios)
            if (grupoUsuario.getChildren().get(i).getAccessibleText().equals(idLido)) { // Se for encontrado um grupo de animacaoUsuario com atributo AccessibleText tal qual o que foi lido..
                grupoUsuario.getChildren().remove(i);                                   // ... retirar a animacao
                System.out.println("Usuario removido");
            }
        }
        for (int i = 0; i < listaUsuarios.size(); i++) {                                // Fazendo uma busca sequencial em listaUsuarios (lista de usuarios da tabela)
            if (listaUsuarios.get(i).getId().equals(fieldDeletaUsuario.getText())) {
                listaUsuarios.remove(i);
            }
        }
    }



    private void matarPombo() {                                         // Funcao para matar pombo caso esteja vivo e não esteja carregando cartas. Caso esteja...
        if (!buffer.pomboCarregando) {
            pombo.matar();
            pombo = null;
            grupoPombo.getChildren().remove(0);
            System.out.println("Pombo morto...");
            pomboVivo = false;
        } else {                                                       // ... fazer nada
            System.out.println("Pombo não pode ser morto agora!");
        }
    }

    @FXML
    void acaoPombo(ActionEvent event) throws Exception{                 // Funcao utilizada ao clicar no botao do pombo
        if(!pomboVivo) {
            animacaoPombo = new PomboAnimacao();

            btPombo.setText("Matar Pombo");
            popUpPombo();
        }
        else
        {
            btPombo.setText("Criar Pombo");
            matarPombo();
        }

    }
}
