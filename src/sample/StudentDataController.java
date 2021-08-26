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
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentDataController {

    @FXML
    private AnchorPane returnBookAnchorPane;
    @FXML
    private ComboBox<String> returnBookID;
    @FXML
    private ComboBox<String> returnStudentID;
    @FXML
    private TextArea returnDetailsTextArea;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Label studentIDLabel;
    @FXML
    private ComboBox<String> studentIDComboBoxSearch;
    @FXML
    private TableView<StudentActivityDetails> studentActivityTableView;
    @FXML
    private TableColumn<StudentActivityDetails, LocalDate> dateOfReturnColumn;
    @FXML
    private TableColumn<StudentActivityDetails, LocalDate> dateOfIssueColumn;
    @FXML
    private TableColumn<StudentActivityDetails, String> nameColumn;
    @FXML
    private TableColumn<StudentActivityDetails, Integer> studentIDColumn;
    @FXML
    private TableColumn<StudentActivityDetails, String> bookTitleColumn;
    @FXML
    private TableColumn<StudentActivityDetails, Integer> bookIDColumn;
    @FXML
    private AnchorPane studentActivityAnchorPane;
    @FXML
    private TextArea bookDetailsTextArea;
    @FXML
    private TextArea studentDetailsTextArea;
    @FXML
    private ComboBox<BookDetails> bookIDComboBox;
    @FXML
    private ComboBox<StudentDetails> studentIDComboBox;
    @FXML
    private Button issueButton;
    @FXML
    private AnchorPane issueBookAnchorPane;
    @FXML
    private AnchorPane addNewStudentAnchorPane;
    @FXML
    private Button addButton;
    @FXML
    private TextField studentID;
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private ComboBox<String> semesterComboBox;
    private ObservableList<String> courseObservableList;
    private ObservableList<String> semesterObservableList;
    private ObservableList<BookDetails> deletedBookDetails = FXCollections.observableArrayList();
    private ObservableList<String> comboBoxSearchStudentIDObservableList = FXCollections.observableArrayList();
    @FXML
    private String returnButtonBookID;
    @FXML
    private String returnButtonStudentID;

    public void initialize() {
        bookIDComboBox.setPromptText("Select Book ID");
        studentIDComboBox.setPromptText("Select Student ID");
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        dateOfIssueColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfIssue"));
        dateOfReturnColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));
        studentActivityTableView.setItems(StudentActivityData.getInstance().getObservableList());
        courseObservableList = FXCollections.observableArrayList();
        courseObservableList.addAll("Select Course", "BSc", "BTech", "BComm(Hons)", "B.A.", "MSc", "MTech", "M.B.A");
        courseComboBox.setItems(courseObservableList);
        semesterObservableList = FXCollections.observableArrayList();

        courseComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (courseComboBox.getSelectionModel().getSelectedItem().contains("M")) {
                    semesterObservableList.clear();
                    semesterObservableList.addAll("Select Semester", "First", "Second", "Third", "Fourth");
                } else if (courseComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("BTech")) {
                    semesterObservableList.clear();
                    semesterObservableList.addAll("Select Semester", "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth");
                } else {
                    semesterObservableList.clear();
                    semesterObservableList.addAll("Select Semester", "First", "Second", "Third", "Fourth", "Fifth", "Sixth");
                }
                semesterComboBox.setItems(semesterObservableList);
            }
        });

        studentIDComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (studentIDComboBox.getSelectionModel().getSelectedItem() != null) {
                    String string = studentIDComboBox.getSelectionModel().getSelectedItem().toString().trim();
                    for (StudentDetails student : StudentDetailsData.getInstance().getStudentDetailsList()) {
                        if (student.getStudentID() == Integer.parseInt(string)) {
                            studentDetailsTextArea.setText("Student ID : " + student.getStudentID() + "\n" +
                                    "Name : " + student.getName() + "\n" +
                                    "Course : " + student.getCourse() + "\n" +
                                    "Semester : " + student.getSemester());
                            break;
                        }
                    }
                    if (!bookDetailsTextArea.getText().isEmpty() && !studentDetailsTextArea.getText().isEmpty()) {
                        issueButton.setDisable(false);
                    }
                }
            }
        });

        bookIDComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (bookIDComboBox.getSelectionModel().getSelectedItem() != null) {
                    String string = bookIDComboBox.getSelectionModel().getSelectedItem().toString().trim();
                    System.out.println(string);
                    for (BookDetails book : BookDetailsData.getInstance().getBookDetailsList()) {
                        if (book.getBookID() == Integer.parseInt(string)) {
                            bookDetailsTextArea.setText("Book ID : " + book.getBookID() + "\n"
                                    + "Title : " + book.getTitle() + "\n"
                                    + "Author : " + book.getAuthor() + "\n"
                                    + "Genre : " + book.getGenre());
                            break;
                        }
                    }
                }
            }
        });

        returnStudentID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fillReturnTextArea();
                returnButtonStudentID = returnStudentID.getSelectionModel().getSelectedItem();
                returnButtonBookID = returnBookID.getSelectionModel().getSelectedItem();
            }
        });

        studentIDComboBoxSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String string = studentIDComboBoxSearch.getSelectionModel().getSelectedItem();
                if (string != null) {
                    if (string.equalsIgnoreCase("All")) {
                        studentActivityTableView.setItems(StudentActivityData.getInstance().getObservableList());
                    } else {
                        ObservableList<StudentActivityDetails> observableList = FXCollections.observableArrayList();
                        for (StudentActivityDetails details : StudentActivityData.getInstance().getObservableList()) {
                            if (details.getStudentID() == Integer.parseInt(string.trim())) {
                                observableList.add(details);
                                studentActivityTableView.setItems(observableList);
                            }
                        }
                    }
                }
            }
        });

        returnBookID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String string = returnBookID.getSelectionModel().getSelectedItem();
                if (string != null) {
                    ObservableList<String> studentIDObservableList = FXCollections.observableArrayList();
                    for (StudentActivityDetails details : StudentActivityData.getInstance().getObservableList()) {
                        String string1 = details.getStudentID() + "";
                        if (!studentIDObservableList.contains(string1) && details.getBookID() == Integer.parseInt(string)) {
                            studentIDObservableList.add(string1);
                        }
                    }
                    returnStudentID.setItems(studentIDObservableList);
                }
            }
        });
    }


    public void clickNewStudent() {
        studentActivityAnchorPane.setVisible(false);
        issueBookAnchorPane.setVisible(false);
        addNewStudentAnchorPane.setVisible(true);
        studentIDComboBoxSearch.setVisible(false);
        studentIDLabel.setVisible(false);

    }

    public void clickAdd() throws IOException {
        StudentDetails studentDetails = new StudentDetails(Integer.parseInt(studentID.getText()), name.getText(), courseComboBox.getSelectionModel().getSelectedItem(), semesterComboBox.getSelectionModel().getSelectedItem());
        StudentDetailsData.getInstance().addNewStudent(studentDetails);
        studentID.clear();
        name.clear();
        courseComboBox.getSelectionModel().selectFirst();
        semesterComboBox.getSelectionModel().selectFirst();
        StudentDetailsData.getInstance().saveStudentData();
        System.out.println(StudentDetailsData.getInstance().getStudentDetailsList());
    }

    public void clickIssueBook() {
        issueBookAnchorPane.setVisible(true);
        addNewStudentAnchorPane.setVisible(false);
        studentActivityAnchorPane.setVisible(false);
        returnBookAnchorPane.setVisible(false);
        ObservableList<BookDetails> observableList = FXCollections.observableArrayList();
        for (BookDetails bookDetails : BookDetailsData.getInstance().getBookDetailsList()) {
            if (bookDetails.getCopies()!=0) {
                observableList.add(bookDetails);
            }
        }
        bookIDComboBox.setItems(observableList);
        studentIDComboBox.setItems(StudentDetailsData.getInstance().getStudentDetailsList());
        studentIDComboBoxSearch.setVisible(false);
        studentIDLabel.setVisible(false);
    }

    public void clickIssueButton() throws IOException {
        BookDetails book = bookIDComboBox.getSelectionModel().getSelectedItem();
        book.setCopies(book.getCopies() - 1);
        System.out.println(BookDetailsData.getInstance().getBookDetailsList());
        BookDetailsData.getInstance().saveBookDetails();
        StudentDetails student = studentIDComboBox.getSelectionModel().getSelectedItem();
        System.out.println(student.getName() + " " + book.getTitle());
        StudentActivityData.getInstance().addToObservableList(new StudentActivityDetails(book.getBookID(), book.getTitle(), student.getStudentID(), student.getName(), LocalDate.now(), LocalDate.now().plusDays(7)));
        StudentActivityData.getInstance().saveData();
        studentDetailsTextArea.clear();
        bookDetailsTextArea.clear();
    }

    public void clickStudentActivity() throws IOException {
        addNewStudentAnchorPane.setVisible(false);
        issueBookAnchorPane.setVisible(false);
        studentActivityAnchorPane.setVisible(true);
        studentIDComboBoxSearch.setVisible(true);
        returnBookAnchorPane.setVisible(false);
        studentIDLabel.setVisible(true);
        comboBoxSearchStudentIDObservableList.clear();
        comboBoxSearchStudentIDObservableList.add("All");
        ArrayList<String> arrayList = new ArrayList<>();
        for (StudentActivityDetails studentActivityDetails : StudentActivityData.getInstance().getObservableList()) {
            arrayList.add(studentActivityDetails.getStudentID() + "");
        }
        System.out.println(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (arrayList.get(i).equalsIgnoreCase(arrayList.get(j)) && i != j) {
                    arrayList.set(j, 0 + "");
                }
            }
        }
        System.out.println(arrayList);
        for (String string : arrayList) {
            if (!string.trim().equals("0")) {
                comboBoxSearchStudentIDObservableList.add(string);
            }
        }
        System.out.println(comboBoxSearchStudentIDObservableList);
        studentIDComboBoxSearch.setItems(comboBoxSearchStudentIDObservableList);

    }

    public void clickReturnBook() {
        returnStudentID.setPromptText("Select Student ID");
        returnBookID.setPromptText("Select Book ID");
        returnBookAnchorPane.setVisible(true);
        issueBookAnchorPane.setVisible(false);
        studentActivityAnchorPane.setVisible(false);
        addNewStudentAnchorPane.setVisible(false);
        studentIDComboBoxSearch.setVisible(false);
        studentIDLabel.setVisible(false);
        ObservableList<String> bookIDObservableList = FXCollections.observableArrayList();
        for (StudentActivityDetails studentActivityDetails : StudentActivityData.getInstance().getObservableList()) {
            String string1 = studentActivityDetails.getBookID() + "";
            if (!bookIDObservableList.contains(string1)) {
                bookIDObservableList.add(string1);
            }

        }
        returnBookID.setItems(bookIDObservableList);

    }

    public void clickReturnButton() throws IOException {
        for (StudentActivityDetails details : StudentActivityData.getInstance().getObservableList()) {
            String bookID = details.getBookID() + "";
            String studentID = details.getStudentID() + "";
            if (bookID.equals(returnBookID.getSelectionModel().getSelectedItem()) && studentID.equals(returnStudentID.getSelectionModel().getSelectedItem())) {
                for (BookDetails details1 : BookDetailsData.getInstance().getBookDetailsList()) {
                    if (details1.getBookID() == details.getBookID()) {
                        details1.setCopies(details1.getCopies() + 1);
                        break;
                    }
                }
                StudentActivityData.getInstance().removeFromObservableList(details);
                break;
            }
        }
        returnDetailsTextArea.clear();
        StudentActivityData.getInstance().saveData();
        BookDetailsData.getInstance().saveBookDetails();
    }

    public void clickRenewButton() throws IOException {
        int count = 0;
        for (StudentActivityDetails details : StudentActivityData.getInstance().getObservableList()) {
            String bookID = details.getBookID() + "";
            String studentID = details.getStudentID() + "";
            if (bookID.equals(returnBookID.getSelectionModel().getSelectedItem()) && studentID.equals(returnStudentID.getSelectionModel().getSelectedItem())) {
                details.setDateOfReturn(details.getDateOfReturn().plusDays(7));
                studentActivityTableView.getItems().set(count,new StudentActivityDetails(details.getBookID(),details.getTitle(),details.getStudentID(),details.getStudentName(),details.getDateOfIssue(),details.getDateOfReturn()));
                break;
            }
            count++;
        }
        returnDetailsTextArea.clear();
        StudentActivityData.getInstance().saveData();
    }

    public void clickMainMenuButton() throws Exception {
        System.out.println(BookDetailsData.getInstance().getBookDetailsList());
        Stage stage = (Stage) mainMenuButton.getScene().getWindow();
        MainLibraryWindow window = new MainLibraryWindow();
        stage.close();
        window.start(new Stage());
    }

    public void fillReturnTextArea() {
        String book = returnBookID.getSelectionModel().getSelectedItem();
        String student = returnStudentID.getSelectionModel().getSelectedItem();
        for (StudentActivityDetails studentActivityDetails : StudentActivityData.getInstance().getObservableList()) {
            String bookID = studentActivityDetails.getBookID() + "";
            String studentID = studentActivityDetails.getStudentID() + "";
            System.out.println(returnDetailsTextArea);
            if (returnDetailsTextArea!=null && bookID.equals(book) && studentID.equals(student) && returnDetailsTextArea.getText().isEmpty()) {
                returnDetailsTextArea.setText("Book ID : " + bookID + "\n"
                             + "Title : " + studentActivityDetails.getTitle() + "\n"
                             + "StudentID : " + studentActivityDetails.getStudentID() + "\n"
                             + "Student Name : " + studentActivityDetails.getStudentName() + "\n"
                             +  "DATE OF RETURN : " + studentActivityDetails.getDateOfReturn());
                break;
            }
        }
    }

}
