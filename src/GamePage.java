import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.util.LinkedList;
import java.util.Random;

public class GamePage extends DefaultScene {
    private Player player;
    private final Random random=new Random();
    private LinkedList<Mobs> mobs=new LinkedList<>();
    private Timeline timeline;
    private Label playerScore, load;
    private ImageView exploded;

    private void addMobs() {
        timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mobs.add(new Mobs((double)random.nextInt(850)+5,0));
                getChildren().add(mobs.get(mobs.size()-1));
                if (getChildren().contains(exploded)){
                    getChildren().remove(exploded);
                }
            }
        }));
        timeline.playFromStart();
    }
    public GamePage(Player player) {
        super(true);
        playerScore=new Label("Score: "+player.getScore());
        playerScore.setTranslateX(10);
        playerScore.setTranslateY(20);
        playerScore.setTextFill(Color.GREEN);
        playerScore.setFont(new Font(25));
        load=new Label(""+player.getLoad());
        load.setTranslateX(800);
        load.setTranslateY(20);
        load.setTextFill(Color.GREEN);
        load.setFont(new Font(25));
        getChildren().add(load);
        getChildren().add(playerScore);
        this.player = player;
        getChildren().add(player);
        getChildren().addAll(player.getLife());
        exploded=new ImageView(new Image("bin/explosioniconm.png"));
        keyPressed();
        keyReleased();
        removeBullets();
        addMobs();
    }

    private void removeBullets() {
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                exploded.setTranslateX(player.getTranslateX()-100);
                exploded.setTranslateY(player.getTranslateY()-150);
                if (player.getLoad()>0) {
                    load.setText("Bullets: "+player.getLoad());
                } else load.setText("Press 2 to Reload");
                try {
                    if (player.getLife().size()==0) {
                        timeline.stop();
                        this.stop();
                        Run.swapScene(new GameOver(player));

                    }
                    for (Mobs mobs1 : mobs) {
                        boolean hit=false;
                        for (Bullet bullet : player.getBullets()) {
                            if (mobs1.shouldBeRemoved(mobs1.collidedWithPlayer(player), bullet.bulletHitMob(mobs1, player))) {
                                hit=true;
                                getChildren().remove(mobs1);
                                mobs.remove(mobs1);

                            }
                            if (bullet.shouldBeRemoved(bullet.bulletHitMob(mobs1, player))) {
                                hit=true;
                                getChildren().remove(bullet);
                                player.getBullets().remove(bullet);
                            }
                            if (hit) {
                                break;
                            }
                        }
                        if (hit) {
                            break;
                        }
                        if (mobs1.collidedWithPlayer(player)){
                            getChildren().remove(mobs1);
                            mobs.remove(mobs1);
                            new Audio("dir/PlayerExplosion.wav").playNormal();
                            getChildren().remove(player.getLife().get(player.getLife().size()-1));
                            player.getLife().remove(player.getLife().size()-1);
                            getChildren().add(exploded);
                            break;
                        }


                    }
                }catch (Exception error) {

                }
                playerScore.setText("Score: "+player.getScore());
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
