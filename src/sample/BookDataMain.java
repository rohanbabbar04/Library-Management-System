package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class BookDataMain extends Application {

    Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bookData.fxml")));
        primaryStage.setTitle("Book Data");
        primaryStage.setScene(new Scene(root, 716, 532));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        BookDetailsData.getInstance().loadBookDetails();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void newStage() throws Exception {
        start(stage);
        init();
    }
}
