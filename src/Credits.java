import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Credits extends DefaultScene {
    private Label label;
    private AnimationTimer a;
    public Credits() {
        label=new Label("CREDITS:\n" +
                "Chief Executive Developer: Kia Kalani\n" +
                "Lead Graphic Designer: Vanya Chirkov\n" +
                "Lead Music Developer: Royalty Free Music from Bensound\n" +
                "\n" +
                "       In Memory of\n" +
                "       MertDutal\n");
        label.setTextFill(Color.BLUE);
        label.setFont(new Font(30));
        label.setTranslateX(20);
        label.setTranslateY(900);
        getChildren().add(label);
        a=new AnimationTimer() {
            @Override
            public void handle(long now) {
                label.setTranslateY(label.getTranslateY()-5);
                stopIt();
            }
        };
        a.start();

    }
    private void stopIt() {
        if (label.getTranslateY()<-400) {
            a.stop();
            Run.swapScene(new MainMenu());
        }
    }
}
