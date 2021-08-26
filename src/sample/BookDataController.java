package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BookDataController {

    @FXML
    private AnchorPane addBookAnchorPane;

    @FXML
    private AnchorPane showAllBooksAnchorPane;

    @FXML
    private AnchorPane searchBookAnchorPane;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<String> bookDetailsComboBox;

    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private ObservableList<String> observableList2 = FXCollections.observableArrayList();
    private ObservableList<String> observableList3 = FXCollections.observableArrayList();

    @FXML
    private TableView<BookDetails> tableView;

    @FXML
    private TableColumn<BookDetails,Integer> bookIDColumn;

    @FXML
    private TableColumn<BookDetails,String> titleColumn;

    @FXML
    private TableColumn<BookDetails,String> authorColumn;

    @FXML
    private TableColumn<BookDetails,String> genreColumn;

    @FXML
    private TableColumn<BookDetails,Integer> copiesColumn;

    @FXML
    private TextField bookIDTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField genreTextField;

    @FXML
    private TextField copiesTextField;

    @FXML
    private TextArea bookDetailsTextArea;

    @FXML
    private Button mainMenu;

    @FXML
    private Label copiesNegative;

    @FXML
    private Label bookIDAlreadyPresent;

    public void initialize() {
        copiesNegative.setText("");
        bookIDAlreadyPresent.setText("");
        observableList2.add("Book ID");
        observableList2.add("Title");
        bookDetailsComboBox.setItems(observableList2);
        bookDetailsComboBox.getSelectionModel().selectFirst();
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));
        tableView.setItems(BookDetailsData.getInstance().getBookDetailsList());
        comboBox.setItems(observableList);

        bookDetailsComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(observableList);
                if (bookDetailsComboBox.getSelectionModel().isSelected(0)) {
                    comboBox.setItems(observableList);
                }else {
                    comboBox.setItems(observableList3);
                }
            }
        });

        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                public void handle(ActionEvent actionEvent) {
                if (bookDetailsComboBox.getSelectionModel().getSelectedItem().equals("Book ID")) {
                    String item = comboBox.getSelectionModel().getSelectedItem();
                    for (BookDetails bookDetails : BookDetailsData.getInstance().getBookDetailsList()) {
                        if (item!=null && item.equalsIgnoreCase(bookDetails.getBookID() + "")) {
                            bookDetailsTextArea.setText("Book ID - " +  bookDetails.getBookID() + "\n" +
                                    "Title - " + bookDetails.getTitle() + "\n" +
                                    "Author - " + bookDetails.getAuthor() + "\n" +
                                    "Genre - " + bookDetails.getGenre() + "\n" +
                                    "Copies - " + bookDetails.getCopies());
                            break;
                        }
                    }
                }else if(bookDetailsComboBox.getSelectionModel().getSelectedItem().equals("Title")) {
                    String item = comboBox.getSelectionModel().getSelectedItem();
                    for (BookDetails bookDetails : BookDetailsData.getInstance().getBookDetailsList()) {
                        System.out.println(item);
                        if (item!=null && item.equalsIgnoreCase(bookDetails.getTitle())) {
                            bookDetailsTextArea.setText("Book ID - " +  bookDetails.getBookID() + "\n" +
                                    "Title - " + bookDetails.getTitle() + "\n" +
                                    "Author - " + bookDetails.getAuthor() + "\n" +
                                    "Genre - " + bookDetails.getGenre() + "\n" +
                                    "Copies - " + bookDetails.getCopies());
                            break;
                        }
                    }
                }
            }
        });
    }

    public void addNewBook() {
        addBookAnchorPane.setVisible(true);
        showAllBooksAnchorPane.setVisible(false);
        searchBookAnchorPane.setVisible(false);
    }

    public void addButtonSelected() throws IOException {
        if (Integer.parseInt(copiesTextField.getText()) < 1) {
            copiesNegative.setText("Copies cannot be less than 1");
            return;
        }else {
            for (BookDetails details : BookDetailsData.getInstance().getBookDetailsList()) {
                String bookID = details.getBookID() + "";
                if (bookID.equals(bookIDTextField.getText())) {
                    bookIDAlreadyPresent.setText("BookID Already Present");
                    return;
                }
            }
        }
        copiesNegative.setText("");
        bookIDAlreadyPresent.setText("");
        BookDetails bookDetails = new BookDetails(Integer.parseInt(bookIDTextField.getText()),titleTextField.getText(), authorTextField.getText(), genreTextField.getText(),Integer.parseInt(copiesTextField.getText()));
        BookDetailsData.getInstance().addBookDetails(bookDetails);
        bookIDTextField.clear();
        titleTextField.clear();
        authorTextField.clear();
        genreTextField.clear();
        copiesTextField.clear();
        BookDetailsData.getInstance().saveBookDetails();
    }

    public void clearButton() {
        bookIDTextField.clear();
        titleTextField.clear();
        authorTextField.clear();
        genreTextField.clear();
        copiesTextField.clear();
    }

    public void selectAllBooks() {
        addBookAnchorPane.setVisible(false);
        showAllBooksAnchorPane.setVisible(true);
        searchBookAnchorPane.setVisible(false);
        System.out.println(BookDetailsData.getInstance().getBookDetailsList());
    }

    public void selectSearchBook() {
        addBookAnchorPane.setVisible(false);
        showAllBooksAnchorPane.setVisible(false);
        searchBookAnchorPane.setVisible(true);
        observableList.clear();
        observableList3.clear();
        for (BookDetails details : BookDetailsData.getInstance().getBookDetailsList()) {
            observableList.add(details.getBookID() + "");
            observableList3.add(details.getTitle());
        }
    }

    public void clickDeleteButton() {
        String item = comboBox.getSelectionModel().getSelectedItem();
        for (BookDetails bookDetails : BookDetailsData.getInstance().getBookDetailsList()) {
            if (item.equalsIgnoreCase(bookDetails.getBookID() + "") || item.equalsIgnoreCase(bookDetails.getTitle())) {
                comboBox.getItems().remove(item);
                BookDetailsData.getInstance().deleteBookDetails(bookDetails);
                break;
            }
        }
    }

    public void clickMainMenu() throws Exception {
        MainLibraryWindow window = new MainLibraryWindow();
        window.start(new Stage());
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }
}
