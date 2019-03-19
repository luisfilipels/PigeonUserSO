package animacao;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class PomboAnimacao {

    final static Image PB01 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb01.png").toString());
    final static Image PB02 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb02.png").toString());
    final static Image PB03 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb03.png").toString());
    final static Image PB04 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb04.png").toString());
    final static Image PB05 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb05.png").toString());
    final static Image PB06 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb06.png").toString());
    final static Image PB07 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb07.png").toString());
    final static Image PB08 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb08.png").toString());
    final static Image PB09 = new javafx.scene.image.Image(PomboAnimacao.class.getResource("/pb09.png").toString());
    private Group pombo;
    private int x, y;

    public PomboAnimacao() {
        final ImageView pb01 = new ImageView(PB01);
        final ImageView pb02 = new ImageView(PB02);
        final ImageView pb03 = new ImageView(PB03);
        final ImageView pb04 = new ImageView(PB04);
        final ImageView pb05 = new ImageView(PB05);
        final ImageView pb06 = new ImageView(PB06);
        final ImageView pb07 = new ImageView(PB07);
        final ImageView pb08 = new ImageView(PB08);
        final ImageView pb09 = new ImageView(PB09);

        pombo = new Group(pb01);
        x = 500;
        y = 280;
        pombo.setTranslateX(x);
        pombo.setTranslateY(y);

        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb02);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb03);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb04);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb04);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb05);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(600),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb06);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(700),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb07);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(800),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb08);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(900),
                (ActionEvent event) -> {
                    pombo.getChildren().setAll(pb09);
                }
        ));
        t.play();
    }

    public void vooIda(int duracao) {
        Path path = new Path();
        path.getElements().add (new MoveTo (x, y));
        path.getElements().add (new LineTo (550, 250));
        path.getElements().add (new LineTo (590, 246));
        path.getElements().add (new LineTo (640, 230));
        path.getElements().add (new LineTo (780, 220));
        path.getElements().add (new LineTo (1350, 210));
        path.getElements().add (new LineTo (1700, 200));

        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(duracao/1000));

        transition.setPath(path);
        transition.setNode(pombo);
        transition.play();

    }

    public void vooVolta(int duracao) {
        Path path = new Path();
        path.getElements().add (new MoveTo (-50, 210));
        path.getElements().add (new LineTo (10, 220));
        path.getElements().add (new LineTo (150, 230));
        path.getElements().add (new LineTo (210, 250));
        path.getElements().add (new LineTo (300, 270));
        path.getElements().add (new LineTo (450, 275));
        path.getElements().add (new LineTo (x, y));

        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(duracao/1000));

        transition.setPath(path);
        transition.setNode(pombo);
        transition.play();
    }

    public Group getUsuario() {
        return pombo;
    }
}
