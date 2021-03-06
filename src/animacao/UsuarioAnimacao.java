package animacao;

import java.util.Random;

import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

public class UsuarioAnimacao {

    private int x, y;
    private final static Image US01 = new Image(UsuarioAnimacao.class.getResource("/Dormindo1.png").toString());
    private final static Image US0_SLEEP = new Image(UsuarioAnimacao.class.getResource("/Dormindo3.png").toString());
    private final static Image US0_CARTA = new Image(UsuarioAnimacao.class.getResource("/Entregando.png").toString());
    private final static Image US0_ESC1 = new Image(UsuarioAnimacao.class.getResource("/Escrevendo1.png").toString());
    private final static Image US0_ESC2 = new Image(UsuarioAnimacao.class.getResource("/Escrevendo2.png").toString());
    private final static Image US0_ESC3 = new Image(UsuarioAnimacao.class.getResource("/Escrevendo3.png").toString());

    private ImageView us00, us01;
    private Group usuarioEscrevendo, usuarioEmMovimento;
    private String nomeEscritor;
    private Label label1, label2;

    public UsuarioAnimacao(String nomeEscritor) {
        this.nomeEscritor = nomeEscritor;

        Random random = new Random();
        //this.nome = new Label();
        //this.nome.setText(nome);
        x = random.nextInt(300)+600;
        y = random.nextInt(300)+25;
        //this.nome.setTranslateX(x);
        //this.nome.setTranslateY(y+100);
        us00 = new ImageView(US01);
        label1 = new Label(nomeEscritor);
        label1.setGraphic(us00);
        us01 = new ImageView(US01);
        label2 = new Label(nomeEscritor);
        label2.setGraphic(us00);

        usuarioEscrevendo = new Group(label1);
        usuarioEscrevendo.setTranslateX(x);
        usuarioEscrevendo.setTranslateY(y);
        usuarioEmMovimento = new Group(label2);
        usuarioEmMovimento.setTranslateX(x);
        usuarioEmMovimento.setTranslateY(y);

    }

    public void enviarCarta() {
        us00.setImage(US0_CARTA);
        usuarioEmMovimento.setOpacity(0);
        usuarioEscrevendo.setOpacity(1);

        KeyValue image1 = new KeyValue(us00.imageProperty(), US01);
        KeyValue xValue1 = new KeyValue(label1.layoutXProperty(), 562-x);
        KeyValue yValue1 = new KeyValue(label1.layoutYProperty(), 346-y);
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(500),xValue1, yValue1, image1);

        KeyValue xValue2 = new KeyValue(label1.layoutXProperty(), 0);
        KeyValue yValue2 = new KeyValue(label1.layoutYProperty(), 0);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1000), xValue2, yValue2);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2);
        timeline.play();

    }

    public void escrever(int duracao){
        usuarioEscrevendo.setOpacity(0);
        usuarioEmMovimento.setOpacity(1);
        ImageView us01, us02, us03;

        Label l1, l2, l3;

        us01 = new ImageView(US0_ESC1);
        l1 = new Label(nomeEscritor);
        l1.setGraphic(us01);

        us02 = new ImageView(US0_ESC2);
        l2 = new Label(nomeEscritor);
        l2.setGraphic(us02);

        us03 = new ImageView(US0_ESC3);
        l3 = new Label(nomeEscritor);
        l3.setGraphic(us03);

        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(100),
                (ActionEvent event) -> {
                    usuarioEmMovimento.getChildren().setAll(l1);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent event) -> {
                    usuarioEmMovimento.getChildren().setAll(l2);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent event) -> {
                    usuarioEmMovimento.getChildren().setAll(l3);
                }
        ));
        t.play();

    }


    public Group getUsuarioEscrevendo() {
        return usuarioEscrevendo;
    }

    public Group getUsuarioEmMovimento() {

        return usuarioEmMovimento;
    }

    public void usuarioDormir() {
        usuarioEscrevendo.setOpacity(1);
        usuarioEmMovimento.setOpacity(0);
        us00.setImage(US0_SLEEP);
    }

    public void executando(int duracao) {
        long lastTime = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        while(now-lastTime < duracao) {
            now = System.currentTimeMillis();
        }
    }

    public void setNomeEscritor(String nome) {
        this.nomeEscritor = nome;
    }

}

