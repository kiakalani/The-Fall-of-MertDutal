import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.LinkedList;

public class PlayMenu extends DefaultScene {
    private String playerName;
    private int currentBtn;
    private LinkedList<Label> chars=new LinkedList<>();
    private Label name;
    public PlayMenu() {
        playerName="";
        initializeBtns();
        name=new Label();
        name.setTextFill(Color.RED);
        name.setTranslateX(80);
        name.setTranslateY(400);
        name.setFont(new Font(40));
        getChildren().add(name);

        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                playAnimation();
                name.setText("Name: "+playerName);
            }
        };
        a.start();
        setKeyboard();

    }
    private void playAnimation() {
        for (int i=0;i<chars.size();i++) {
            if (i!=currentBtn) {
                chars.get(i).setTextFill(Color.YELLOW);
            } else chars.get(i).setTextFill(Color.BLUE);
        }

    }
    private void initializeBtns() {
        for (int i=0;i<26;i++){
            String wrd=""+(char)(65+i);
            chars.add(new Label(wrd));
        }
        chars.add(13,new Label("Start"));
        chars.add(new Label("DEL"));
        for (Label label:chars){
            getChildren().add(label);
        }
        for (int i=0;i<28;i++){
            chars.get(i).setFont(new Font(40));
            chars.get(i).setTextFill(Color.YELLOW);
            if (i<14){
                chars.get(i).setTranslateY(100);
                chars.get(i).setTranslateX(100+(i*60));
            }else {
                chars.get(i).setTranslateY(200);
                chars.get(i).setTranslateX(100+((i-14)*60));
            }
        }
    }
    private void setKeyboard() {
        getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case D:
                        rightPressed();
                        break;
                    case A:
                        leftPressed();
                        break;
                    case ENTER:
                        enterPressed();
                        break;
                    case W:
                        upPressed();
                        break;
                    case S:
                        downPressed();
                        break;
                }
            }
        });
    }
    private void rightPressed() {
        if (currentBtn+1<chars.size()) {
            currentBtn++;
        }else currentBtn=0;
    }
    private void leftPressed() {
        if (currentBtn-1>=0) {
            currentBtn--;
        }else currentBtn=chars.size()-1;
    }
    private void enterPressed() {
        System.out.println(1);
        if (currentBtn!=13 && currentBtn!=chars.size()-1) {
            playerName+=chars.get(currentBtn).getText();
        }
        if (currentBtn==13) {
            Run.swapScene(new Intro(new Player(playerName)));
        }
        if (currentBtn==chars.size()-1){
            if (playerName.length()>0)
            playerName= playerName.substring(0,playerName.length()-1);
        }
    }
    private void upPressed() {
        if(currentBtn<14) {
            currentBtn+=14;
        } else currentBtn-=14;
    }
    private void downPressed() {
        if(currentBtn>14) {
            currentBtn-=14;
        } else currentBtn+=14;
    }
}
