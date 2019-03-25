package animacao;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class PomboAnimacao {

    final static Image PB01 = new Image(PomboAnimacao.class.getResource("/pb01.png").toString());
    final static Image PB02 = new Image(PomboAnimacao.class.getResource("/pb02.png").toString());
    final static Image PB03 = new Image(PomboAnimacao.class.getResource("/pb03.png").toString());
    final static Image PB04 = new Image(PomboAnimacao.class.getResource("/pb04.png").toString());
    final static Image PB05 = new Image(PomboAnimacao.class.getResource("/pb05.png").toString());
    final static Image PB06 = new Image(PomboAnimacao.class.getResource("/pb06.png").toString());
    final static Image PB07 = new Image(PomboAnimacao.class.getResource("/pb07.png").toString());
    final static Image PB08 = new Image(PomboAnimacao.class.getResource("/pb08.png").toString());
    final static Image PB09 = new Image(PomboAnimacao.class.getResource("/pb09.png").toString());
    final static Image PBC01 = new Image(PomboAnimacao.class.getResource("/pg_carga01.png").toString());
    final static Image PBC02 = new Image(PomboAnimacao.class.getResource("/pg_carga02.png").toString());
    final static Image PBC03 = new Image(PomboAnimacao.class.getResource("/pg_carga03.png").toString());
    final static Image PB0_SLEEP = new Image(PomboAnimacao.class.getResource("/pb_sleep.png").toString());    
    
    
    private Group pomboVoando;
    private Group pomboCarregando;
    private final ImageView pb00;
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
        
        pb00 = new ImageView(PB0_SLEEP);       
        
        pomboVoando = new Group(pb01);
        x = 500;
        y = 280;
        pomboVoando.setTranslateX(x);
        pomboVoando.setTranslateY(y);
        
        pomboCarregando = new Group(pb00);
        pomboCarregando.setTranslateX(x);
        pomboCarregando.setTranslateY(y);
        
    	pomboVoando.setOpacity(0);
    	pomboCarregando.setOpacity(1);
        
        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb02);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb03);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb04);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb04);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb05);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(600),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb06);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(700),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb07);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(800),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb08);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(900),
                (ActionEvent event) -> {
                    pomboVoando.getChildren().setAll(pb09);
                }
        ));
        t.play();
    }

    public void vooIda(int duracao) {
    	
    	pomboCarregando.setOpacity(0);
    	pomboVoando.setOpacity(1);
    	
    	pomboVoando.setScaleX(1);
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

    }

    public void vooVolta(int duracao) {
    	
    	pomboCarregando.setOpacity(0);
    	pomboVoando.setOpacity(1);
    	
    	pomboVoando.setScaleX(-1);
        Path path = new Path();
        path.getElements().add (new MoveTo (1700, 100));
        path.getElements().add (new LineTo (1350, 190));
        path.getElements().add (new LineTo (780, 220));
        path.getElements().add (new LineTo (640, 230));
        path.getElements().add (new LineTo (590, 246));
        path.getElements().add (new LineTo (550, 250));
        path.getElements().add (new LineTo (x, y));

        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(duracao));

        transition.setPath(path);
        transition.setNode(pomboVoando);
        transition.play();


    }
    
    /*
     * Animação pombo carregando 
     * */
    public void carregando(int duracao) {
    	
    	pomboVoando.setOpacity(0);
    	pomboCarregando.setOpacity(1);
    	
    	ImageView pbc01, pbc02, pbc03;
    	pbc01 = new ImageView(PBC01);
    	pbc02 = new ImageView(PBC02);
    	pbc03 = new ImageView(PBC03);

        Timeline t = new Timeline();
        t.setCycleCount(1);

        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duracao/3),
                (ActionEvent event) -> {
                	pomboCarregando.getChildren().setAll(pbc01);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duracao*(2/3)),
                (ActionEvent event) -> {
                	pomboCarregando.getChildren().setAll(pbc02);
                }
        ));

        t.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duracao),
                (ActionEvent event) -> {
                	pomboCarregando.getChildren().setAll(pbc03);
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
    	
    	pb00.setImage(PB0_SLEEP);
    	
    	
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
}
