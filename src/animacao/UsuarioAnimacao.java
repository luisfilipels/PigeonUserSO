package animacao;

import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;

public class UsuarioAnimacao {

    private int x, y;
    private final static Image US01 = new Image(UsuarioAnimacao.class.getResource("/lolli_50cent.png").toString());;
    private ImageView us01;
    private Group usuario;

    public UsuarioAnimacao() {
        Random random = new Random();
        x = random.nextInt(316)+758;
        y = random.nextInt(370)+100;
        us01 = new ImageView(US01);
        usuario = new Group(us01);
        usuario.setTranslateX(x);
        usuario.setTranslateY(y);

    }

    public void play(int duracao) {

        Path path1 = new Path();
        path1.getElements().add (new MoveTo (x, y));
        path1.getElements().add (new LineTo (600, 400));
        path1.getElements().add(new ClosePath());

        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(1));

        transition.setPath(path1);
        transition.setNode(usuario);

        //   	transition.setCycleCount(2);
        //    	transition.setAutoReverse(true);
        transition.play();


    }

    public Group getUsuario() {
        return usuario;
    }

}
