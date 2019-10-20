import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.LinkedList;

public class MainMenu extends DefaultScene {
    private LinkedList<Label> buttons = new LinkedList<>();
    private PlayCases currBtn;

    private enum PlayCases {
        PLAY, CREDITS, HIGHSCORE, QUIT
    }

    public MainMenu() {
        addButtons();
        currBtn = PlayCases.PLAY;
        AnimationTimer a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                initializeCurrBtn();
            }
        };
        a.start();
        setBtnActions();
    }

    private void addButtons() {
        for (int i = 0; i < 4; i++) {
            buttons.add(new Label(PlayCases.values()[i].toString()));
            getChildren().add(buttons.get(i));
            buttons.get(i).setTranslateX(400);
            buttons.get(i).setTranslateY(100 + (100 * i));
            buttons.get(i).setFont(new Font(45));
            buttons.get(i).setTextFill(Color.YELLOW);
        }
    }

    private void initializeCurrBtn() {
        for (int i = 0; i < buttons.size(); i++) {
            if (i != currBtn.ordinal()) {
                buttons.get(i).setTextFill(Color.YELLOW);
            }
        }
        buttons.get(currBtn.ordinal()).setTextFill(Color.BLUE);
    }

    private void setBtnActions() {
        getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        moveUp();
                        break;
                    case S:
                        moveDown();
                        break;
                    case ENTER:
                        enterPressed();
                        break;
                }
            }
        });
    }

    private void moveUp() {
        if (currBtn.ordinal() - 1 >= 0) {
            currBtn = PlayCases.values()[currBtn.ordinal() - 1];
        } else currBtn = PlayCases.QUIT;
    }

    private void moveDown() {
        if (currBtn.ordinal() + 1 < PlayCases.values().length) {
            currBtn = PlayCases.values()[currBtn.ordinal() + 1];
        } else currBtn = PlayCases.PLAY;
    }
    private void enterPressed(){
        switch (currBtn){
            case PLAY:
                Run.swapScene(new PlayMenu());
                break;
            case CREDITS:
                Run.swapScene(new Credits());
                break;
            case HIGHSCORE:
                Run.swapScene(new HighScores());
                break;
            case QUIT:
                System.exit(0);
        }
    }
}
