import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Run extends Application {
    private final static Audio audio=new Audio("dir/MainMusic.wav");
    /**
     * The stage
     */
    private static Stage stage;

    /**
     * Start method
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        stage.getIcons().add(new Image(getClass().getResourceAsStream("bin/mainIcon.png")));
        swapScene(new MainMenu());
        audio.play();
        stage.show();
    }

    /**
     * Changing the scene
     * @param root
     */
    public static void swapScene(Parent root){
        stage.setScene(root.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
