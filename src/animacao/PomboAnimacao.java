package animacao;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class PomboAnimacao {

    final static Image VOO1 = new Image(PomboAnimacao.class.getResource("/Voo1.png").toString());
    final static Image VOO2 = new Image(PomboAnimacao.class.getResource("/Voo2.png").toString());
    final static Image VOO3 = new Image(PomboAnimacao.class.getResource("/Voo3.png").toString());
    final static Image VOO4 = new Image(PomboAnimacao.class.getResource("/Voo4.png").toString());
    final static Image VOO5 = new Image(PomboAnimacao.class.getResource("/Voo5.png").toString());
    final static Image VOO6 = new Image(PomboAnimacao.class.getResource("/Voo6.png").toString());
    final static Image VOO7 = new Image(PomboAnimacao.class.getResource("/Voo7.png").toString());
    final static Image VOO8 = new Image(PomboAnimacao.class.getResource("/Voo8.png").toString());

    final static Image VOUTA1 = new Image(PomboAnimacao.class.getResource("/Voo1.png").toString());
    final static Image VOUTA2 = new Image(PomboAnimacao.class.getResource("/Volta2.png").toString());
    final static Image VOUTA3 = new Image(PomboAnimacao.class.getResource("/Volta3.png").toString());
    final static Image VOUTA4 = new Image(PomboAnimacao.class.getResource("/Volta4.png").toString());
    final static Image VOUTA5 = new Image(PomboAnimacao.class.getResource("/Volta5.png").toString());
    final static Image VOUTA6 = new Image(PomboAnimacao.class.getResource("/Volta6.png").toString());
    final static Image VOUTA7 = new Image(PomboAnimacao.class.getResource("/Volta7.png").toString());
    final static Image VOUTA8 = new Image(PomboAnimacao.class.getResource("/Volta8.png").toString());


    final static Image CARREGAR1 = new Image(PomboAnimacao.class.getResource("/Carregar1.png").toString());
    final static Image CARREGAR2 = new Image(PomboAnimacao.class.getResource("/Carregar2.png").toString());
    final static Image CARREGAR3 = new Image(PomboAnimacao.class.getResource("/Carregar3.png").toString());
    final static Image CARREGAR4 = new Image(PomboAnimacao.class.getResource("/Carregar4.png").toString());
    final static Image CARREGAR5 = new Image(PomboAnimacao.class.getResource("/Carregar5.png").toString());
    final static Image CARREGAR6 = new Image(PomboAnimacao.class.getResource("/Carregar6.png").toString());
    final static Image CARREGAR7 = new Image(PomboAnimacao.class.getResource("/Carregar7.png").toString());
    final static Image CARREGAR8 = new Image(PomboAnimacao.class.getResource("/Carregar8.png").toString());
    final static Image CARREGAR9 = new Image(PomboAnimacao.class.getResource("/Carregar9.png").toString());
    final static Image CARREGAR10 = new Image(PomboAnimacao.class.getResource("/Carregar10.png").toString());
    final static Image CARREGAR11 = new Image(PomboAnimacao.class.getResource("/Carregar11.png").toString());
    final static Image CARREGAR12 = new Image(PomboAnimacao.class.getResource("/Carregar12.png").toString());
    final static Image CARREGAR13 = new Image(PomboAnimacao.class.getResource("/Carregar13.png").toString());



    final static Image DORMIR = new Image(PomboAnimacao.class.getResource("/dormir4.png").toString());

    //aviãozinho do mapa
    final static Image PBMAPA = new Image(PomboAnimacao.class.getResource("/VooCima1.png").toString());
    private ImageView pb_mapa;


    private Group pomboVoando;
    private Group pomboCarregando;
    private Group pomboMapa; //pombo no mapa
    private final ImageView pb00;
    private int x, y;

    public PomboAnimacao() {

        pb00 = new ImageView(DORMIR);
        final ImageView voo1 = new ImageView(VOO1);

        pomboVoando = new Group();
        x = 500;
        y = 280;
        pomboVoando.setTranslateX(x);
        pomboVoando.setTranslateY(y);

        pomboCarregando = new Group(pb00);
        pomboCarregando.setTranslateX(x);
        pomboCarregando.setTranslateY(y);

        pomboVoando.setOpacity(0);
        pomboCarregando.setOpacity(1);



        /*
         * Inicializacao no pombo no mapa
         * */
        pb_mapa = new ImageView(PBMAPA);
        pb_mapa.setLayoutX(197);
        pb_mapa.setLayoutY(281);
        pomboMapa = new Group(pb_mapa);

    }



    public void vooIda(int duracao) {

        pomboCarregando.setOpacity(0);
        pomboVoando.setOpacity(1);


        final ImageView voo1 = new ImageView(VOO1);
        final ImageView voo2 = new ImageView(VOO2);
        final ImageView voo3 = new ImageView(VOO3);
        final ImageView voo4 = new ImageView(VOO4);
        final ImageView voo5 = new ImageView(VOO5);
        final ImageView voo6 = new ImageView(VOO6);
        final ImageView voo7 = new ImageView(VOO7);
        final ImageView voo8 = new ImageView(VOO8);


        Timeline t = new Timeline();
        t.setCycleCount(duracao);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(0),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo1);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo1);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo2);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo3);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo4);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo5);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(600),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo6);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(700),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo7);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(800),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(voo8);
                }
        ));

        t.play();

        Path path = new Path();
        path.getElements().add (new MoveTo (x, y));
        path.getElements().add (new LineTo (550, 250));
        path.getElements().add (new LineTo (590, 246));
        path.getElements().add (new LineTo (640, 230));
        path.getElements().add (new LineTo (780, 220));
        path.getElements().add (new LineTo (1350, 190));
        path.getElements().add (new LineTo (1700, 100));


        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(duracao));

        transition.setPath(path);
        transition.setNode(pomboVoando);
        transition.play();

        pomboMapa.setScaleX(1);
        Path path2 = new Path();
        path2.getElements().add (new MoveTo (197, 291));
        path2.getElements().add (new LineTo (133, 235));
        path2.getElements().add (new LineTo (120, 161));

        PathTransition transition2 = new PathTransition();
        transition2.setDuration(Duration.seconds(duracao));

        transition2.setPath(path2);
        transition2.setNode(pomboMapa);
        transition2.play();

    }

    public void vooVolta(int duracao) {

        pomboCarregando.setOpacity(0);
        pomboVoando.setOpacity(1);


        final ImageView vouta1 = new ImageView(VOUTA1);
        final ImageView vouta2 = new ImageView(VOUTA2);
        final ImageView vouta3 = new ImageView(VOUTA3);
        final ImageView vouta4 = new ImageView(VOUTA4);
        final ImageView vouta5 = new ImageView(VOUTA5);
        final ImageView vouta6 = new ImageView(VOUTA6);
        final ImageView vouta7 = new ImageView(VOUTA7);
        final ImageView vouta8 = new ImageView(VOUTA8);

        Timeline t = new Timeline();
        t.setCycleCount(duracao);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(0),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta1);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta1);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta2);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta3);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta4);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta5);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(600),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta6);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(700),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta7);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(800),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(vouta8);
                }
        ));

        t.play();


        Path path = new Path();
        path.getElements().add (new MoveTo (1700, 100));
        path.getElements().add (new LineTo (1350, 190));
        path.getElements().add (new LineTo (780, 220));
        path.getElements().add (new LineTo (640, 230));
        path.getElements().add (new LineTo (590, 246));
        path.getElements().add (new LineTo (590, 246));
        path.getElements().add (new LineTo (x, y));

        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(duracao));

        transition.setPath(path);
        transition.setNode(pomboVoando);
        transition.play();

        pomboMapa.setScaleX(-1);
        Path path2 = new Path();
        path2.getElements().add (new MoveTo (120, 161));
        path2.getElements().add (new LineTo (133, 235));
        path2.getElements().add (new LineTo (197, 291));

        PathTransition transition2 = new PathTransition();
        transition2.setDuration(Duration.seconds(duracao));

        transition2.setPath(path2);
        transition2.setNode(pomboMapa);
        transition2.play();

    }

    /*
     * Animação pombo carregando
     * */
    public void carregando(int duracao) {

        pomboVoando.setOpacity(0);
        pomboCarregando.setOpacity(1);

        ImageView carregar1, carregar2, carregar3, carregar4, carregar5, carregar6, carregar7, carregar8, carregar9, carregar10, carregar11, carregar12, carregar13;
        //       carregar1 = new ImageView(CARREGAR1);
        carregar2 = new ImageView(CARREGAR2);
        //       carregar3 = new ImageView(CARREGAR3);
        carregar4 = new ImageView(CARREGAR4);
        //       carregar5 = new ImageView(CARREGAR5);
        //       carregar6 = new ImageView(CARREGAR6);
        carregar7 = new ImageView(CARREGAR7);
        //       carregar8 = new ImageView(CARREGAR8);
        //       carregar9 = new ImageView(CARREGAR9);
        carregar10 = new ImageView(CARREGAR10);
        //      carregar11 = new ImageView(CARREGAR11);
        //       carregar12 = new ImageView(CARREGAR12);
        //       carregar13 = new ImageView(CARREGAR13);


        Timeline t = new Timeline();
        t.setCycleCount(1);


        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duracao*0.25),
                (ActionEvent event) -> {
                    pomboCarregando.getChildren().setAll(carregar2);
                }
        ));


        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duracao*0.5),
                (ActionEvent event) -> {
                    pomboCarregando.getChildren().setAll(carregar4);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duracao*7.5),
                (ActionEvent event) -> {
                    pomboCarregando.getChildren().setAll(carregar7);
                }
        ));


        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duracao),
                (ActionEvent event) -> {
                    pomboCarregando.getChildren().setAll(carregar10);
                }
        ));


        t.play();


    	/*
		KeyValue image1 = new KeyValue(pb00.imageProperty(), PBC02);
		KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(duracao/3),image1);

		KeyValue image2 = new KeyValue(pb00.imageProperty(), PBC03);
		KeyFrame keyFrame2 = new KeyFrame(Duration.seconds((2/3)*duracao), image2);

		KeyValue image3 = new KeyValue(pb00.imageProperty(), PBC01);
		KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(duracao), image3);


		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
		timeline.play();
		*/
    }

    public void dormir() {

        pomboVoando.setOpacity(0);
        pomboCarregando.setOpacity(1);

        pb00.setImage(DORMIR);


        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(5),
                (ActionEvent event) -> {
                    pomboCarregando.getChildren().setAll(pb00);
                }
        ));
        t.play();
    }

    public Group getPomboVoando() {
        return pomboVoando;
    }

    public Group getPomboParado() {
        return pomboCarregando;
    }

    public Group getPomboMapa() {
        return pomboMapa;
    }
}
