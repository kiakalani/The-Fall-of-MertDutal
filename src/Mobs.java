import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Mobs extends ImageView {
    private AnimationTimer a;

    public Mobs(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
        setImage(new Image("bin/aliemone.png"));
        a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                setTranslateY(getTranslateY() + 9);
            }
        };
        a.start();
    }
    public boolean shouldBeRemoved(boolean collidedWithPlayer) {
        if (collidedWithPlayer||getTranslateY()>1024) {
            a.stop();
            return true;
        }else return false;
    }

}
