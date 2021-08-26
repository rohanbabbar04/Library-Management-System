package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLibraryWindowController {

    @FXML
    private Button bookData;

    @FXML
    private Button studentData;

    @FXML
    public void initialize() throws IOException {
    }

    @FXML
    public void clickBookData() throws Exception {
        BookDataMain main = new BookDataMain();
        main.newStage();
        Stage stage = (Stage)bookData.getScene().getWindow();
        stage.close();
    }

    public void clickStudentData() throws Exception {
        StudentDataMain main = new StudentDataMain();
        main.newStage();
        Stage stage = (Stage) studentData.getScene().getWindow();
        stage.close();
    }

}
