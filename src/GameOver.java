import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOver extends DefaultScene {
    private Player player;
    private Label label;
    public GameOver(Player player) {
        this.player=player;
        new Audio("dir/GameOver.wav").playNormal();
        label=new Label("<<<GAME OVER...>>>");
        label.setFont(new Font(50));
        label.setTextFill(Color.GREEN);
        label.setTranslateX(250);
        label.setTranslateY(900);
        getChildren().add(label);
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                label.setTranslateY(label.getTranslateY()-2);
                if (label.getTranslateY()<-100){
                    this.stop();
                    //Go to highScores
                    Run.swapScene(new HighScores(new PlayerHighScore(player.getName(),player.getScore())));
                }
            }
        };
        a.start();
    }
}
