import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

import java.util.LinkedList;
import java.util.Random;

public abstract class DefaultScene extends Group {
    private final Random random=new Random();
    private final Scene scene=new Scene(this, 1024, 768);
    private LinkedList<Sphere> stars=new LinkedList<>();
    public DefaultScene() {
        while (stars.size()<80) {
            stars.add(new Sphere(random.nextInt(5),random.nextInt(5)));
            stars.get(stars.size()-1).setTranslateX((double)random.nextInt(1024));
            stars.get(stars.size()-1).setTranslateY((double)random.nextInt(768));
            getChildren().add(stars.get(stars.size()-1));
        }
        scene.setFill(Color.BLACK);
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Sphere sphere:stars) {
                    sphere.setRadius(random.nextInt(5));
                }
            }
        };
        a.start();
    }
    public DefaultScene(boolean isGame) {
        while (stars.size()<80) {
            stars.add(new Sphere(random.nextInt(5),random.nextInt(5)));
            stars.get(stars.size()-1).setTranslateX((double)random.nextInt(1024));
            stars.get(stars.size()-1).setTranslateY((double)random.nextInt(768));
            getChildren().add(stars.get(stars.size()-1));
        }
        scene.setFill(Color.BLACK);
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Sphere sphere:stars) {
                    sphere.setRadius(random.nextInt(5));
                    moveStars();
                }
            }
        };
        a.start();
    }
    private void moveStars() {
        for (Sphere sphere:stars) {
            sphere.setTranslateY(getTranslateY()-10);
            if (sphere.getTranslateY()<0) {
                sphere.setTranslateY(768);
            }
        }
    }

    public LinkedList<Sphere> getStars() {
        return stars;
    }
}
