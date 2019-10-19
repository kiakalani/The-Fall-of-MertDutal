import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePage extends DefaultScene {
    private Player player;
    private final Random random=new Random();
    private LinkedList<Mobs> mobs=new LinkedList<>();
    private Timeline timeline;
    private void addMobs() {
        timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mobs.add(new Mobs((double)random.nextInt(850)+5,0));
                getChildren().add(mobs.get(mobs.size()-1));
            }
        }));
        timeline.playFromStart();
    }
    public GamePage(Player player) {
        super(true);
        this.player = player;
        getChildren().add(player);
        keyPressed();
        keyReleased();
        removeBullets();
        addMobs();
    }

    private void removeBullets() {
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Bullet bullet:player.getBullets()) {
                    if (bullet.shouldBeRemoved(false)) {
                        getChildren().remove(bullet);
                        player.getBullets().remove(bullet);
                        break;
                    }
                }
                for (Mobs mobs1:mobs) {
                    if (mobs1.shouldBeRemoved(false)) {
                        getChildren().remove(mobs1);
                        mobs.remove(mobs1);
                        break;
                    }
                }
            }
        };
        a.start();

    }

    private void keyPressed() {
        getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.keyPressed(event);
            }
        });
    }
    private void keyReleased() {
        getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.keyReleased(event);
                for (Bullet bullet:player.getBullets()) {
                    if (!getChildren().contains(bullet)) {
                        getChildren().add(bullet);
                        break;
                    }
                }
            }
        });
    }
}
