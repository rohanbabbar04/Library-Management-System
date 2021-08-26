package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label invalid;

    @FXML
    private Button loginButton;

    public void initialize() {

    }

    public void clickLoginButton() throws Exception {
        LoginItem item = new LoginItem(username.getText(),password.getText());
        if (LoginData.getInstance().getLoginList().contains(item)) {
            System.out.println("Cleared");
            MainLibraryWindow mainLibraryWindow = new MainLibraryWindow();
            mainLibraryWindow.newStage();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }else {
            System.out.println("Not Cleared");
            invalid.setText("Invalid Username or Password");
        }
        username.clear();
        password.clear();
    }
}
