package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class StudentDataMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studentData.fxml")));
        primaryStage.setTitle("Book Data");
        primaryStage.setScene(new Scene(root, 716, 532));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        BookDetailsData.getInstance().loadBookDetails();
        StudentDetailsData.getInstance().loadStudentData();
        StudentActivityData.getInstance().loadData();
    }

    @Override
    public void stop() throws Exception {
        StudentDetailsData.getInstance().saveStudentData();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void newStage() throws Exception {
        start(new Stage());
        init();
    }
}
