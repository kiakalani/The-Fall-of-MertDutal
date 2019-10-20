import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Mobs extends ImageView {
    private AnimationTimer a;

    public Mobs(double x, double y) {
        String path;
        boolean chooser=new Random().nextBoolean();
        if (chooser) {
            path="bin/aliemone.png";
        }else path="bin/eyebutthebiggerbetterverion.png";
        setTranslateX(x);
        setTranslateY(y);
        setImage(new Image(path));
        a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                setTranslateY(getTranslateY() + 7);
            }
        };
        a.start();
    }
    public boolean shouldBeRemoved(boolean collidedWithPlayer, boolean collidedWithBullet) {
        if (collidedWithPlayer||getTranslateY()>1024||collidedWithBullet) {
            a.stop();
            return true;
        }else return false;
    }
    public boolean collidedWithPlayer(Player player) {
        boolean b=false;
        if (getBoundsInParent().intersects(player.getBoundsInParent())) {
            b = true;
        }

        return b;
    }

}
