import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Sphere;

import java.util.LinkedList;

public class Player extends ImageView {
    private String name;
    private int health;
    private int score;
    private final static double speed=0.1;
    private int currentSpeed=0;
    private LinkedList<Bullet> bullets=new LinkedList<>();
    public Player(String name) {
        health=3;
        score=0;
        this.name=name;
        setImage(new Image("bin/main_ship.png"));
        setTranslateX(450);
        setTranslateY(650);
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                move();
            }
        };
        a.start();
    }

    private void move() {
        if (getTranslateX()<3) {
            setTranslateX(3);
            System.out.println("Low");
        }
        if (getTranslateX()>900) {
            setTranslateX(900);
            System.out.println("High");
        }
        setTranslateX(getTranslateX()+currentSpeed);
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
        if (currentSpeed<9) {
            currentSpeed=9;
        }
        currentSpeed+=speed;
    }
    private void leftPressed() {
        if (currentSpeed>-9) {
            currentSpeed=-9;
        }
        currentSpeed-=speed;
    }
    public void keyReleased(KeyEvent e){
        switch (e.getCode()) {
            case A:
                stopMovement();
                break;
            case D:
                stopMovement();
                break;
            case C:
                bullets.add(new Bullet(getTranslateX(),getTranslateY()));
                break;
        }
    }

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }
    public void addScore() {
        score+=100;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addBossScore() {
        score+=1000;
    }

    private void stopMovement() {
        if (currentSpeed>0) {
            AnimationTimer a=new AnimationTimer() {
                @Override
                public void handle(long now) {
                    currentSpeed-=0.8;
                    if (currentSpeed<=0.5) {
                        currentSpeed=0;
                        this.stop();
                    }
                }
            };
            a.start();
        }else {
            AnimationTimer a=new AnimationTimer() {
                @Override
                public void handle(long now) {
                    currentSpeed+=0.8;
                    if (currentSpeed>=-0.5) {
                        currentSpeed=0;
                        this.stop();
                    }
                }
            };
            a.start();
        }
    }
}
