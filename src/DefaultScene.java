import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public abstract class DefaultScene extends Group {
    private final Scene scene=new Scene(this, 1024, 768);
    public DefaultScene() {
        scene.setFill(Color.BLACK);
    }
}
