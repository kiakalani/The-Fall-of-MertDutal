import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import java.io.*;

public class HighScores extends DefaultScene {
    private File[] files;

    public HighScores(PlayerHighScore player) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("highscore/" + System.currentTimeMillis())));
            os.writeObject(player);
            os.close();
        } catch (IOException err) {

        }
        files = new File("highscore").listFiles();
        ObservableList<PlayerHighScore> playerHighScores = FXCollections.observableArrayList();
        for (File file : files) {
            try {
                playerHighScores.add((PlayerHighScore) new ObjectInputStream(new FileInputStream(file)).readObject());
            } catch (Exception err) {

            }
        }
        for (int i = 0; i < playerHighScores.size(); i++) {
            for (int j = 0; j < playerHighScores.size(); j++) {
                PlayerHighScore st = playerHighScores.get(i);
                PlayerHighScore nd = playerHighScores.get(j);
                if (st.getScore() < nd.getScore()) {
                    playerHighScores.set(i, nd);
                    playerHighScores.set(j, st);
                }
            }
        }
        TableView table = new TableView();
        TableColumn<String, PlayerHighScore> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Integer, PlayerHighScore> column2 = new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.setItems(playerHighScores);
        getChildren().add(table);
    }
    public HighScores() {
        files = new File("highscore").listFiles();
        ObservableList<PlayerHighScore> playerHighScores = FXCollections.observableArrayList();
        for (File file : files) {
            try {
                playerHighScores.add((PlayerHighScore) new ObjectInputStream(new FileInputStream(file)).readObject());
            } catch (Exception err) {

            }
        }
        for (int i = 0; i < playerHighScores.size(); i++) {
            for (int j = 0; j < playerHighScores.size(); j++) {
                PlayerHighScore st = playerHighScores.get(i);
                PlayerHighScore nd = playerHighScores.get(j);
                if (st.getScore() > nd.getScore()) {
                    playerHighScores.set(i, nd);
                    playerHighScores.set(j, st);
                }
            }
        }
        TableView table = new TableView();
        TableColumn column0=new TableColumn("#");
        column0.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call( TableColumn p )
            {
                return new TableCell()
                {
                    @Override
                    public void updateItem( Object item, boolean empty )
                    {
                        super.updateItem( item, empty );
                        setGraphic( null );
                        setText( empty ? null : getIndex() + 1 + "" );
                    }
                };
            }
        });

        TableColumn<String, PlayerHighScore> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Integer, PlayerHighScore> column2 = new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));
        table.getColumns().add(column0);
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.setItems(playerHighScores);
        column1.prefWidthProperty().bind(table.widthProperty().multiply(0.7));
        column2.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        column0.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        getChildren().add(table);
        table.setTranslateX(400);
        table.setTranslateY(200);
        getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Run.swapScene(new MainMenu());
            }
        });
    }
}
