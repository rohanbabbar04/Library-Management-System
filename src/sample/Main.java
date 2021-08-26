package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        LoginData.getInstance().loadData();
    }

    @Override
    public void stop() throws Exception {
        LoginData.getInstance().saveData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
