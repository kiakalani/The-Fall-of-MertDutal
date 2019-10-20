import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends ImageView {
    private AnimationTimer moveBullet;
    public Bullet(double x, double y) {
        setTranslateX(x+25);
        setTranslateY(y);
        setFitWidth(15);
        setImage(new Image("bin/laserbullet.png"));
        moveBullet=new AnimationTimer() {
            @Override
            public void handle(long now) {
                setTranslateY(getTranslateY()-20);
            }
        };
        moveBullet.start();

    }
    private void stopTimer() {
        if (getTranslateY()<-20) {
            moveBullet.stop();
        }
    }
    public boolean shouldBeRemoved(boolean hitTheMob) {
        if (hitTheMob || getTranslateY()<-20) {
            stopTimer();
            return true;
        }else return false;
    }
    public boolean bulletHitMob(Mobs mobs, Player player) {
        boolean b=false;
        if (getBoundsInParent().intersects(mobs.getBoundsInParent())){
            b = true;
            System.out.println(5);
            player.addScore();
        }
        return b;
    }
}
