import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Intro extends DefaultScene {
    private Label introduction;
    private Player player;
    public Intro(Player player) {
        this.player=player;
        introduction=new Label("It is the year 2153.\n" +
                "\n" +
                "It has been 7 years since the corruption of the chained alliances. \n" +
                "\n" +
                "There has been tension that an incoming alien invasion is upon the planet. \n" +
                "\n" +
                "It is up to you, "+player.getName()+", to defend this Earth. \n" +
                "\n" +
                "You are given to pilot the legendary ship called MertDutal.\n" +
                "\n" +
                "But be careful, you will be followed and watched. \n");
        introduction.setFont(new Font(25));
        introduction.setTextFill(Color.BLUE);
        introduction.setTranslateX(50);
        introduction.setTranslateY(900);
        getChildren().add(introduction);
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                runGame(this);
            }

        };
        a.start();
    }
    private void runGame(AnimationTimer a) {
        introduction.setTranslateY(introduction.getTranslateY()-10);
        if (introduction.getTranslateY()<-550){
            a.stop();
            Run.swapScene(new GamePage(player));
        }
    }
}
