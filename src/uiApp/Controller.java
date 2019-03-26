package uiApp;

import animacao.PomboAnimacao;
import animacao.UsuarioAnimacao;
import correio.Buffer;
import correio.Pombo;
import correio.Escritor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
    private TextField textField;
    
    final static Image ADDUS01 = new Image(PomboAnimacao.class.getResource("/AdicionaUsuario1x.png").toString());
    final static Image KILLUS01 = new Image(PomboAnimacao.class.getResource("/ExcluiUsuario1x.png").toString());
    final static Image ADDPB01 = new Image(PomboAnimacao.class.getResource("/PomboNormal1x.png").toString());

    public ControllerPopupUsuario popupUsuario;
    public ControllerPopupPombo popupPombo;
    public ControllerPopupM popupM;

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

		ControllerPopupUsuario.mainController = this;
		ControllerPopupM.mainController = this;
        try {
            popupM();

        } catch (Exception e) {
            e.printStackTrace();
        }



		
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
        popup.setScene(new Scene(root, 600, 375));
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
    public static boolean pomboLido;

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

    /*@FXML
    public void handlePomboButton(ActionEvent event) {


        tc = 1000*Integer.parseInt(tcField.getText());
        td = 1000*Integer.parseInt(tdField.getText());
        tv = 1000*Integer.parseInt(tvField.getText());
        buffer.setCarga(Integer.parseInt(numCartas.getText()));

        System.out.println(buffer.getCarga());


        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }*/

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
        //UsuarioAnimacao animacao = new UsuarioAnimacao();
        //animacaosEscritor.add(animacao);
        //idJanela.getChildren().add(animacao.getUsuarioEmMovimento());
        //idJanela.getChildren().add(animacao.getUsuarioEscrevendo());
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


        /*UsuarioAnimacao animacaoEscritor = new UsuarioAnimacao();
        listaEscritores.add(new Escritor(buffer, listaEscritores.size()+1, 3000, animacaoEscritor));
        animacaosEscritor.add(animacaoEscritor);
        idJanela.getChildren().add(animacaoEscritor.getUsuarioEscrevendo());
        idJanela.getChildren().add(animacaoEscritor.getUsuarioEmMovimento());
        System.out.println("Escritor criado.");
        System.out.println(tc + " " + td + " " + tv);*/

    }

    @FXML
    TextField fieldDeletaUsuario;

    @FXML
    void matarEscritor(ActionEvent event) {
        String idLido = fieldDeletaUsuario.getText();
        UsuarioAnimacao animaTemp = null;
        System.out.println(listaEscritores.size());
        int removidos = 0;
        System.out.println("Numero de elementos em idJanela: " + idJanela.getChildren().size());
        for (int i = 0; i < listaEscritores.size(); i++) {
            if (listaEscritores.get(i).getsId().equals(idLido)) {
                animaTemp = animacaosEscritor.get(i);
                listaEscritores.get(i).matar();
                listaEscritores.remove(i);
                removidos++;
            }
        }
        System.out.println("===========");
        for (int i = 0; i < idJanela.getChildren().size(); i++) {
            System.out.println(idJanela.getChildren().get(i).toString());
        }
        System.out.println("===========");
        if (removidos == 0) {
            System.out.println("Usuario não encontrado.");
            return;
        }
        for (int i = 0; i < idJanela.getChildren().size(); i++) {
            if (idJanela.getChildren().get(i).equals(animaTemp.getUsuarioEmMovimento()) || idJanela.getChildren().get(i).equals(animaTemp.getUsuarioEscrevendo())) {
                System.out.println("Encontrada uma animação.");
                idJanela.getChildren().remove(i);
                idJanela.getChildren().remove(i);
                //idJanela.getChildren().remove(animaTemp.getUsuarioEmMovimento());
                //idJanela.getChildren().remove(animaTemp.getUsuarioEscrevendo());
                //idJanela.getChildren().set(i, null);
                //TODO Corrigir a remoção de usuários

            }
        }
        System.out.println("Escritores com id " + idLido + " mortos.");
    }

    @FXML
    void handleID() {
        currentID = Integer.parseInt(textField.getText());
        System.out.println("Input recebido");
        System.out.println("ID armazenada: " + currentID);
    }



    private void matarPombo() {
        pombo.matar();
        pombo = null;
    }

    @FXML
    void acaoPombo(ActionEvent event) throws Exception{
        if(!pomboVivo) {
        	pomboLido = false;
        	animacaoPombo = new PomboAnimacao();
        	idJanela.getChildren().add(animacaoPombo.getPomboVoando());
            idJanela.getChildren().add(animacaoPombo.getPomboParado());

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
