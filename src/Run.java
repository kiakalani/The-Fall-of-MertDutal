import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application {
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
        swapScene(new MainMenu());
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
