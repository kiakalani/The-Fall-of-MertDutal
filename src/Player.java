import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.LinkedList;

public class Player extends ImageView {
    private String name;
    private LinkedList<ImageView> life = new LinkedList<>();
    private int score;
    private final static double speed = 0.1;
    private int currentSpeed = 0;
    private int load = 10;
    private LinkedList<Bullet> bullets = new LinkedList<>();

    public Player(String name) {
        for (int i = 0; i < 3; i++) {
            life.add(new ImageView(new Image("bin/heart.png")));
            life.get(i).setFitHeight(20);
            life.get(i).setFitWidth(20);
            life.get(i).setTranslateX(500 + (25 * i));
        }
        score = 0;
        this.name = name;
        setImage(new Image("bin/main_ship.png"));
        setTranslateX(450);
        setTranslateY(650);
        AnimationTimer a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move();
            }
        };
        a.start();
        reloadTimer.getKeyFrames().add(new KeyFrame(Duration.seconds(2.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    reloadTimer.stop();
                    load = 10;
                    rVal = 0;
            }
        }));
    }

    private int rVal = 0;

    private void move() {
        if (getTranslateX() < 3) {
            setTranslateX(3);
        }
        if (getTranslateX() > 900) {
            setTranslateX(900);
        }
        setTranslateX(getTranslateX() + currentSpeed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case A:
                leftPressed();
                break;
            case D:
                rightPressed();
                break;
        }
    }

    private void rightPressed() {
        if (currentSpeed < 9) {
            currentSpeed = 9;
        }
        currentSpeed += speed;
    }

    private void leftPressed() {
        if (currentSpeed > -9) {
            currentSpeed = -9;
        }
        currentSpeed -= speed;
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case A:
                stopMovement();
                break;
            case D:
                stopMovement();
                break;
            case C:
                if (load > 0) {
                    new Audio("dir/bullet.wav").playNormal();
                    bullets.add(new Bullet(getTranslateX(), getTranslateY()));
                    load--;
                }
                break;
            case V:
                if (load <= 0) {
                    reloadTimer.playFromStart();
                }

        }
    }

    private Timeline reloadTimer = new Timeline();

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public void addScore() {
        score += 50;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addBossScore() {
        score += 1000;
    }

    public LinkedList<ImageView> getLife() {
        return life;
    }

    private void stopMovement() {
        if (currentSpeed > 0) {
            AnimationTimer a = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    currentSpeed -= 0.8;
                    if (currentSpeed <= 0.5) {
                        currentSpeed = 0;
                        this.stop();
                    }
                }
            };
            a.start();
        } else {
            AnimationTimer a = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    currentSpeed += 0.8;
                    if (currentSpeed >= -0.5) {
                        currentSpeed = 0;
                        this.stop();
                    }
                }
            };
            a.start();
        }
    }

    public int getLoad() {
        return load;
    }
}
